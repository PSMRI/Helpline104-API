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
package com.iemr.helpline104.service.balVivah;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.iemr.helpline104.data.balVivah.BalVivahComplaint;
import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.repository.balVivah.BalVivahComplaintRepo;
import com.iemr.helpline104.repository.beneficiarycall.BenCallRepository;
import com.iemr.helpline104.repository.epidemicOutbreak.EpidemicOutbreakRepository;
import com.iemr.helpline104.repository.feedback.FeedbackRepository;
import com.iemr.helpline104.repository.feedbackType.FeedbackTypeRepository;
import com.iemr.helpline104.repository.location.LocationCityRepository;
import com.iemr.helpline104.repository.location.LocationDistrictBlockRepository;
import com.iemr.helpline104.repository.location.LocationDistrictRepository;

import jakarta.servlet.http.HttpServletRequest;

/**
 * A child marriage complaint is stored twice - once to mint the BV request ID
 * from the generated key - raised as a grievance with the common service, and
 * updated field by field afterwards.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BalVivahComplaintImplTest {

	@Mock
	private BalVivahComplaintRepo balVivahComplaintRepo;

	@Mock
	private LocationCityRepository locationCityRepository;

	@Mock
	private LocationDistrictBlockRepository locationDistrictBlockRepository;

	@Mock
	private LocationDistrictRepository locationDistrictRepository;

	@Mock
	private FeedbackTypeRepository feedbackRepositorty;

	@Mock
	private FeedbackRepository feedbackRepository;

	@Mock
	private BenCallRepository benCallRepository;

	@Mock
	private EpidemicOutbreakRepository epidemicOutbreakRepository;

	@InjectMocks
	private BalVivahComplaintImpl balVivahService;

	/** The seventeen columns of the complaint projection. */
	private static List<Object[]> complaintRows() {
		return Arrays.asList(null, new Object[0],
				new Object[] { 9L, "BV/2/01012024/9", "Asha", "16", new Date(1_700_000_000_000L), "Ramesh", "agent",
						"child marriage", new Timestamp(1_700_000_000_000L), 1, "Maharashtra", 2, "Pune", 4,
						"Wagholi", (short) 2, "Female" });
	}

	private static BalVivahComplaint stored() {
		BalVivahComplaint stored = new BalVivahComplaint();
		stored.setBalVivaComplaintID(9L);
		stored.setRequestID("BV/2/01012024/9");
		stored.setChildDistrict(2);
		stored.setProviderServiceMapID(1);
		stored.setBeneficiaryRegID(12L);
		stored.setBenCallID(4L);
		stored.setCreatedBy("agent");
		stored.setComplaintDate(new Timestamp(1_700_000_000_000L));
		return stored;
	}

	@Test
	void saveMintsTheRequestIdAndRaisesTheGrievance() throws Exception {
		BalVivahComplaint complaint = stored();
		when(balVivahComplaintRepo.save(any())).thenReturn(complaint);
		when(feedbackRepositorty.findBalVivahFeedbackTypeID(1)).thenReturn(new M_FeedbackType());
		HttpServletRequest request = mock(HttpServletRequest.class);

		try (MockedConstruction<RestTemplate> restTemplate = mockConstruction(RestTemplate.class,
				(template, context) -> when(template.exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class)))
						.thenReturn(ResponseEntity.ok("{}")))) {

			String response = balVivahService.save(complaint, request);

			assertTrue(response.contains("\"balVivahComplaintID\":9"));
			assertTrue(response.contains("BV/2/"));
			assertEquals(1, restTemplate.constructed().size());
		}
	}

	@Test
	void getWorklistRequestsSearchesByBeneficiary() {
		when(balVivahComplaintRepo.findByBeneficiaryID(12L)).thenReturn(complaintRows());

		assertTrue(balVivahService.getWorklistRequests(12L, null, null).contains("Asha"));
	}

	@Test
	void getWorklistRequestsSearchesByPhoneNumber() {
		when(epidemicOutbreakRepository.findByPhoneNum("9999999999"))
				.thenReturn(new ArrayList<>(Collections.singletonList(BigInteger.valueOf(12L))));
		when(balVivahComplaintRepo.findByBeneficiaryRegIDs(Collections.singletonList(12L)))
				.thenReturn(complaintRows());

		assertTrue(balVivahService.getWorklistRequests(null, "9999999999", null).contains("Asha"));
	}

	@Test
	void getWorklistRequestsIsEmptyForAnUnknownPhoneNumber() {
		when(epidemicOutbreakRepository.findByPhoneNum("9999999999")).thenReturn(new ArrayList<>());

		assertEquals("[]", balVivahService.getWorklistRequests(null, "9999999999", null));
	}

	@Test
	void getWorklistRequestsSearchesByTheTrailingRequestId() {
		when(balVivahComplaintRepo.findByRequestID(9L)).thenReturn(complaintRows());

		assertTrue(balVivahService.getWorklistRequests(null, null, "BV/2/01012024/9").contains("Asha"));
	}

	@Test
	void getWorklistRequestsIsEmptyWithoutASearchTerm() {
		assertEquals("[]", balVivahService.getWorklistRequests(null, null, null));
	}

	@Test
	void updateBalVivahRequestWritesEverySentField() throws Exception {
		BalVivahComplaint stored = stored();
		BalVivahComplaint update = new BalVivahComplaint();
		update.setRequestID("BV/2/01012024/9");
		update.setSubjectOfComplaint("child marriage");
		update.setChildName("Asha");
		update.setChildFatherName("Ramesh");
		update.setChildAge("16");
		update.setChildGender((short) 2);
		update.setChildState(1);
		update.setChildFatherState("Maharashtra");
		update.setChildDistrict(2);
		update.setChildFatherDistrict("Pune");
		update.setChildSubDistrict("Haveli");
		update.setChildFatherSubDistrict("Haveli");
		update.setChildVillage(4);
		update.setChildFatherVillage("Wagholi");
		update.setMarriageDate(new Date(1_700_000_000_000L));
		update.setComplaintDate(new Timestamp(1_700_000_000_000L));
		when(balVivahComplaintRepo.searchByRequestID("BV/2/01012024/9")).thenReturn(stored);
		when(balVivahComplaintRepo.save(stored)).thenReturn(stored);

		assertEquals("Data updated successfully", balVivahService.updateBalVivahRequest(update));
		assertEquals("Asha", stored.getChildName());
		assertEquals("16", stored.getChildAge());
	}

	@Test
	void updateBalVivahRequestLeavesTheStoredComplaintAloneWhenNothingIsSent() throws Exception {
		BalVivahComplaint stored = stored();
		BalVivahComplaint update = new BalVivahComplaint();
		update.setRequestID("BV/2/01012024/9");
		when(balVivahComplaintRepo.searchByRequestID("BV/2/01012024/9")).thenReturn(stored);
		when(balVivahComplaintRepo.save(stored)).thenReturn(stored);

		assertEquals("Data updated successfully", balVivahService.updateBalVivahRequest(update));
		assertNull(stored.getChildName());
	}

	@Test
	void updateBalVivahRequestReturnsNothingWhenTheSaveComesBackAsAnotherComplaint() throws Exception {
		BalVivahComplaint stored = stored();
		BalVivahComplaint other = stored();
		other.setBalVivaComplaintID(10L);
		BalVivahComplaint update = new BalVivahComplaint();
		update.setRequestID("BV/2/01012024/9");
		when(balVivahComplaintRepo.searchByRequestID("BV/2/01012024/9")).thenReturn(stored);
		when(balVivahComplaintRepo.save(stored)).thenReturn(other);

		assertNull(balVivahService.updateBalVivahRequest(update));
	}

	@Test
	void updateBalVivahRequestRejectsAnUnknownRequestId() {
		when(balVivahComplaintRepo.searchByRequestID(anyString())).thenReturn(null);
		BalVivahComplaint update = new BalVivahComplaint();
		update.setRequestID("BV/2/01012024/9");

		Exception thrown = assertThrows(Exception.class, () -> balVivahService.updateBalVivahRequest(update));

		assertTrue(thrown.getMessage().contains("No record found"));
	}
}
