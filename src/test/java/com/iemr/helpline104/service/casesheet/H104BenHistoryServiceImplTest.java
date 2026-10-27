/*
* AMRIT – Accessible Medical Records via Integrated Technology
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.helpline104.service.casesheet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.helpline104.data.beneficiarycall.OutboundCallRequest;
import com.iemr.helpline104.data.casesheet.COVIDHistory;
import com.iemr.helpline104.data.casesheet.H104BenMedHistory;
import com.iemr.helpline104.data.prescription.Prescription;
import com.iemr.helpline104.repository.beneficiarycall.BeneficiaryCallRepository;
import com.iemr.helpline104.repository.beneficiarycall.OutboundCallRequestRepository;
import com.iemr.helpline104.repository.casesheet.COVIDRepository;
import com.iemr.helpline104.repository.casesheet.H104BenHistoryRepository;
import com.iemr.helpline104.service.prescription.PrescriptionService;

/**
 * A case sheet read by call carries the outbound call request and, once a
 * medical or psychiatrist officer has acted on it, the prescription. The
 * present case sheet is only served while the beneficiary is still on the call
 * it was written on.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class H104BenHistoryServiceImplTest {

	@Mock
	private H104BenHistoryRepository h104BenHistoryRepository;

	@Mock
	private BeneficiaryCallRepository beneficiaryCallRepository;

	@Mock
	private OutboundCallRequestRepository outboundCallRequestRepository;

	@Mock
	private PrescriptionService prescriptionService;

	@Mock
	private COVIDRepository covidRepository;

	@InjectMocks
	private H104BenHistoryServiceImpl benHistoryService;

	private static H104BenMedHistory history(String actionByMO) {
		H104BenMedHistory history = new H104BenMedHistory();
		ReflectionTestUtils.setField(history, "benHistoryID", 9L);
		ReflectionTestUtils.setField(history, "benCallID", 4L);
		ReflectionTestUtils.setField(history, "actionByMO", actionByMO);
		return history;
	}

	@Test
	void geSmpleBenHistoryReadsTheBeneficiarysHistory() {
		List<H104BenMedHistory> stored = Collections.singletonList(history(null));
		when(h104BenHistoryRepository.getBenHistory(12L)).thenReturn(stored);

		assertEquals(stored, benHistoryService.geSmpleBenHistory(12L, null));
		verify(outboundCallRequestRepository, never()).findByBenCallID(anyLong());
	}

	@Test
	void geSmpleBenHistoryByCallAttachesTheOutboundRequestAndThePrescription() {
		H104BenMedHistory history = history("Prescribed");
		when(h104BenHistoryRepository.findByBenCallID(4L)).thenReturn(Collections.singletonList(history));
		when(outboundCallRequestRepository.findByBenCallID(4L))
				.thenReturn(Collections.singletonList(new OutboundCallRequest()));
		when(prescriptionService.getPrescriptionListByBenCallID(4L))
				.thenReturn(Collections.singletonList(new Prescription()));

		benHistoryService.geSmpleBenHistory(null, 4L);

		verify(prescriptionService).getPrescriptionListByBenCallID(4L);
	}

	@Test
	void geSmpleBenHistoryByCallLeavesAnUnactionedCaseSheetWithoutAPrescription() {
		H104BenMedHistory history = history(null);
		when(h104BenHistoryRepository.findByBenCallID(4L)).thenReturn(Collections.singletonList(history));
		when(outboundCallRequestRepository.findByBenCallID(4L)).thenReturn(Collections.emptyList());

		benHistoryService.geSmpleBenHistory(null, 4L);

		verify(prescriptionService, never()).getPrescriptionListByBenCallID(anyLong());
	}

	@Test
	void getPresentCasesheetServesTheCaseSheetOfTheCurrentCall() {
		when(h104BenHistoryRepository.getPresentBenHistory(12L, PageRequest.of(0, 1)))
				.thenReturn(Collections.singletonList(history(null)));
		when(beneficiaryCallRepository.findCallIDFromBenCallID(4L)).thenReturn(" call-1 ");

		assertEquals(1, benHistoryService.getPresentCasesheet(12L, "call-1").size());
	}

	@Test
	void getPresentCasesheetDropsACaseSheetFromAnEarlierCall() {
		when(h104BenHistoryRepository.getPresentBenHistory(12L, PageRequest.of(0, 1)))
				.thenReturn(new java.util.ArrayList<>(Collections.singletonList(history(null))));
		when(beneficiaryCallRepository.findCallIDFromBenCallID(4L)).thenReturn("call-0");

		assertTrue(benHistoryService.getPresentCasesheet(12L, "call-1").isEmpty());
	}

	@Test
	void getPresentCasesheetIsEmptyForABeneficiaryWithoutACaseSheet() {
		when(h104BenHistoryRepository.getPresentBenHistory(12L, PageRequest.of(0, 1)))
				.thenReturn(Collections.emptyList());

		assertTrue(benHistoryService.getPresentCasesheet(12L, "call-1").isEmpty());
	}

	@Test
	void saveSmpleBenHistoryStoresTheCovidHistoryAlongsideTheCaseSheet() {
		H104BenMedHistory history = history(null);
		COVIDHistory covidHistory = new COVIDHistory();
		covidHistory.setIsCOVIDAvailable(true);
		when(h104BenHistoryRepository.save(history)).thenReturn(history);

		assertSame(history, benHistoryService.saveSmpleBenHistory(history, covidHistory));
		verify(covidRepository).save(covidHistory);
	}

	@Test
	void saveSmpleBenHistoryStoresOnlyTheCaseSheetWithoutACovidHistory() {
		H104BenMedHistory history = history(null);
		when(h104BenHistoryRepository.save(history)).thenReturn(history);

		benHistoryService.saveSmpleBenHistory(history, new COVIDHistory());

		verify(covidRepository, never()).save(any());
	}
}
