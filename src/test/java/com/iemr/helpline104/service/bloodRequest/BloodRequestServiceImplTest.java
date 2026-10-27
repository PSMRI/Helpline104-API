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
package com.iemr.helpline104.service.bloodRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.helpline104.data.beneficiarycall.BenCall;
import com.iemr.helpline104.data.bloodRequest.BloodBank;
import com.iemr.helpline104.data.bloodRequest.RequestedBloodBank;
import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;
import com.iemr.helpline104.repository.beneficiarycall.BenCallRepository;
import com.iemr.helpline104.repository.bloodRequest.BloodRequestRepository;
import com.iemr.helpline104.repository.bloodRequest.InstituteRepository;
import com.iemr.helpline104.repository.bloodRequest.RequestedBloodBankRepository;

/**
 * A blood request is stored twice on creation - once to mint the BR request ID
 * from the generated key - and updated in place when it already carries an id.
 * Either way the blood banks it was raised against are pointed back at it.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BloodRequestServiceImplTest {

	@Mock
	private BloodRequestRepository bloodRequestRepository;

	@Mock
	private InstituteRepository instituteRepository;

	@Mock
	private BenCallRepository benCallRepository;

	@Mock
	private RequestedBloodBankRepository requestedBloodBankRepository;

	@InjectMocks
	private BloodRequestServiceImpl bloodRequestService;

	/** The thirty columns of the blood request projection. */
	private static List<Object[]> requestRows() {
		return Arrays.asList(null, new Object[0],
				new Object[] { 9L, "BR/2/01012024/9", 12L, 13L, "Asha", 40, (short) 2, "Female", "urgent", 1, "A+ve",
						"A positive", 2, "Whole blood", "whole", 3, "Plasma", "fresh frozen", "2", "District Hospital",
						2, "Pune", Boolean.TRUE, new Timestamp(1_700_000_000_000L), "Blood Bank", "9999999999",
						"Ramesh", "Officer", "remarks", "feedback" });
	}

	private static T_BloodRequest storedRequest() {
		return new T_BloodRequest(9L, "BR/2/01012024/9", 12L, 13L, "Asha", 40, (short) 2, "Female", "urgent", 1,
				"A+ve", "A positive", 2, "Whole blood", "whole", 3, "Plasma", "fresh frozen", "2",
				"District Hospital", 2, "Pune", Boolean.TRUE, new Timestamp(1_700_000_000_000L), "Blood Bank",
				"9999999999", "Ramesh", "Officer", "remarks", "feedback");
	}

	@Test
	void getBloodRequestSearchesByBeneficiaryAndAttachesTheBloodBanks() throws Exception {
		when(bloodRequestRepository.getBloodRequestsByBeneficiaryRegID(12L)).thenReturn(requestRows());
		when(requestedBloodBankRepository.findByBloodReqIDAndDeleted(9L, false))
				.thenReturn(Collections.singletonList(new RequestedBloodBank()));

		List<T_BloodRequest> requests = bloodRequestService.getBloodRequest(12L, null, null);

		assertEquals(1, requests.size());
		assertEquals(1, requests.get(0).getRequestedBloodBank().size());
	}

	@Test
	void getBloodRequestSearchesByRequestId() throws Exception {
		when(bloodRequestRepository.getBloodRequestsByRequestID("BR/2/01012024/9")).thenReturn(requestRows());

		assertEquals(1, bloodRequestService.getBloodRequest(null, "BR/2/01012024/9", null).size());
	}

	@Test
	void getBloodRequestSearchesByCallAndAttachesTheCall() throws Exception {
		when(bloodRequestRepository.getBloodRequestsByBenCallID(4L)).thenReturn(requestRows());
		when(benCallRepository.findByBenCallID(4L)).thenReturn(new BenCall());

		assertEquals(1, bloodRequestService.getBloodRequest(null, null, 4L).size());
		verify(benCallRepository).findByBenCallID(4L);
	}

	@Test
	void saveMintsTheRequestIdForANewRequest() throws Exception {
		T_BloodRequest request = new T_BloodRequest();
		T_BloodRequest saved = storedRequest();
		saved.setRequestedBloodBank(Collections.singletonList(new RequestedBloodBank()));
		request.setRequestedBloodBank(Collections.singletonList(new RequestedBloodBank()));
		when(bloodRequestRepository.save(any())).thenReturn(saved);

		T_BloodRequest response = bloodRequestService.save(request);

		assertTrue(response.getRequestID().startsWith("BR/"));
		verify(bloodRequestRepository, times(2)).save(any());
		verify(requestedBloodBankRepository).saveAll(any());
	}

	@Test
	void saveUpdatesTheBloodBankDetailsOfAnExistingRequest() throws Exception {
		T_BloodRequest request = storedRequest();

		T_BloodRequest response = bloodRequestService.save(request);

		assertSame(request, response);
		verify(bloodRequestRepository, never()).save(any());
		verify(bloodRequestRepository).updateBloodBankDetails("Blood Bank", "Ramesh", "Officer", "9999999999", 9L,
				"remarks", Boolean.TRUE, new Timestamp(1_700_000_000_000L));
	}

	@Test
	void saveAcceptsARequestWithoutAnyBloodBank() throws Exception {
		T_BloodRequest request = storedRequest();

		bloodRequestService.save(request);

		verify(requestedBloodBankRepository, never()).saveAll(any());
	}

	@Test
	void getBloodBankURLReadsTheStoredUrl() throws Exception {
		BloodBank bloodBank = new BloodBank();
		when(instituteRepository.findBloodBankURL("Blood Bank")).thenReturn(bloodBank);

		assertSame(bloodBank, bloodRequestService.getBloodBankURL("Blood Bank"));
	}

	@Test
	void saveBloodBankURLStoresTheUrl() throws Exception {
		BloodBank bloodBank = new BloodBank();
		when(instituteRepository.save(bloodBank)).thenReturn(bloodBank);

		assertSame(bloodBank, bloodRequestService.saveBloodBankURL(bloodBank));
	}
}
