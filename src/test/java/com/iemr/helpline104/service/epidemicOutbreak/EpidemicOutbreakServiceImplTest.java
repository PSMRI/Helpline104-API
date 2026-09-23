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
package com.iemr.helpline104.service.epidemicOutbreak;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

import com.iemr.helpline104.data.beneficiarycall.BenCall;
import com.iemr.helpline104.data.epidemicOutbreak.T_EpidemicOutbreak;
import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.repository.beneficiarycall.BenCallRepository;
import com.iemr.helpline104.repository.epidemicOutbreak.EpidemicOutbreakRepository;
import com.iemr.helpline104.repository.feedbackType.FeedbackTypeRepository;
import com.iemr.helpline104.repository.location.LocationCityRepository;
import com.iemr.helpline104.repository.location.LocationDistrictBlockRepository;
import com.iemr.helpline104.repository.location.LocationDistrictRepository;

import jakarta.servlet.http.HttpServletRequest;

/**
 * An outbreak complaint is stored twice - once to mint the EC request ID from
 * the generated key - and then raised as a grievance with the common service.
 * Complaints are searched by beneficiary, call, request ID or phone number,
 * whichever the caller knows.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EpidemicOutbreakServiceImplTest {

	@Mock
	private EpidemicOutbreakRepository epidemicOutbreakRepository;

	@Mock
	private LocationCityRepository locationCityRepository;

	@Mock
	private LocationDistrictBlockRepository locationDistrictBlockRepository;

	@Mock
	private LocationDistrictRepository locationDistrictRepository;

	@Mock
	private FeedbackTypeRepository feedbackRepositorty;

	@Mock
	private BenCallRepository benCallRepository;

	@InjectMocks
	private EpidemicOutbreakServiceImpl epidemicOutbreakService;

	/** The thirteen columns of the outbreak complaint projection. */
	private static List<Object[]> complaintRows() {
		return Arrays.asList(null, new Object[0],
				new Object[] { 9L, "EC/2/01012024/9", 12L, "cholera", 40, 2, "Pune", 3, "Haveli", 4, "Wagholi",
						"contaminated water", new Timestamp(1_700_000_000_000L) });
	}

	private static T_EpidemicOutbreak stored() {
		T_EpidemicOutbreak stored = new T_EpidemicOutbreak();
		stored.setOutbreakComplaintID(9L);
		stored.setRequestID("EC/2/01012024/9");
		stored.setAffectedDistrictID(2);
		stored.setAffectedDistrictBlockID(3);
		stored.setServiceID(1);
		return stored;
	}

	@Test
	void saveMintsTheRequestIdAndRaisesTheGrievance() throws Exception {
		T_EpidemicOutbreak complaint = stored();
		M_FeedbackType feedbackType = new M_FeedbackType();
		when(epidemicOutbreakRepository.save(any())).thenReturn(complaint);
		when(locationDistrictBlockRepository.findById(3)).thenReturn(Optional.empty());
		when(locationDistrictRepository.findByDistrictID(anyInt())).thenReturn("Pune");
		when(feedbackRepositorty.findEpidemicFeedbackTypeID(1)).thenReturn(feedbackType);
		HttpServletRequest request = mock(HttpServletRequest.class);

		try (MockedConstruction<RestTemplate> restTemplate = mockConstruction(RestTemplate.class,
				(template, context) -> when(template.exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class)))
						.thenReturn(ResponseEntity.ok("{}")))) {

			T_EpidemicOutbreak response = epidemicOutbreakService.save(complaint, request);

			assertTrue(response.getRequestID().startsWith("EC/2/"));
			assertTrue(response.getRequestID().endsWith("/9"));
			assertEquals(1, restTemplate.constructed().size());
		}
	}

	@Test
	void getEpidemicOutbreakComplaintsSearchesByBeneficiary() throws Exception {
		when(epidemicOutbreakRepository.getEpidemicOutbreakComplaints(12L)).thenReturn(complaintRows());

		List<T_EpidemicOutbreak> complaints = epidemicOutbreakService.getEpidemicOutbreakComplaints(12L, null, null,
				null);

		assertEquals(1, complaints.size());
		assertEquals("cholera", complaints.get(0).getNatureOfComplaint());
	}

	@Test
	void getEpidemicOutbreakComplaintsSearchesByCallAndAttachesTheCall() throws Exception {
		BenCall call = new BenCall();
		when(epidemicOutbreakRepository.getEpidemicOutbreakComplaintsByBenCallID(4L)).thenReturn(complaintRows());
		when(benCallRepository.findByBenCallID(4L)).thenReturn(call);

		List<T_EpidemicOutbreak> complaints = epidemicOutbreakService.getEpidemicOutbreakComplaints(null, 4L, null,
				null);

		assertEquals(1, complaints.size());
		verify(benCallRepository).findByBenCallID(4L);
	}

	@Test
	void getEpidemicOutbreakComplaintsSearchesByTheTrailingRequestId() throws Exception {
		when(epidemicOutbreakRepository.getEpidemicOutbreakComplaintsByRequestIDNew(9L)).thenReturn(complaintRows());

		assertEquals(1,
				epidemicOutbreakService.getEpidemicOutbreakComplaints(null, null, "EC/2/01012024/9", null).size());
	}

	@Test
	void getEpidemicOutbreakComplaintsSearchesByPhoneNumber() throws Exception {
		when(epidemicOutbreakRepository.findByPhoneNum("9999999999"))
				.thenReturn(new ArrayList<>(Collections.singletonList(BigInteger.valueOf(12L))));
		when(epidemicOutbreakRepository.findByBenRegIDs(Collections.singletonList(12L))).thenReturn(complaintRows());

		assertEquals(1, epidemicOutbreakService.getEpidemicOutbreakComplaints(null, null, null, "9999999999").size());
	}

	@Test
	void updateEpidemicOutbreakRequestWritesEverySentField() throws Exception {
		T_EpidemicOutbreak stored = stored();
		T_EpidemicOutbreak update = new T_EpidemicOutbreak();
		update.setRequestID("EC/2/01012024/9");
		update.setAffectedDistrictID(5);
		update.setAffectedDistrictBlockID(6);
		update.setAffectedVillageID(7);
		update.setNatureOfComplaint("dengue");
		update.setTotalPeopleAffected(80);
		update.setRemarks("stagnant water");
		when(epidemicOutbreakRepository.searchByRequestID("EC/2/01012024/9")).thenReturn(stored);
		when(epidemicOutbreakRepository.save(stored)).thenReturn(stored);

		assertEquals("Data updated successfully", epidemicOutbreakService.UpdateEpidemicOutbreakRequest(update));
		assertEquals("dengue", stored.getNatureOfComplaint());
		assertEquals(Integer.valueOf(80), stored.getTotalPeopleAffected());
	}

	@Test
	void updateEpidemicOutbreakRequestLeavesTheStoredComplaintAloneWhenNothingIsSent() throws Exception {
		T_EpidemicOutbreak stored = stored();
		T_EpidemicOutbreak update = new T_EpidemicOutbreak();
		update.setRequestID("EC/2/01012024/9");
		when(epidemicOutbreakRepository.searchByRequestID("EC/2/01012024/9")).thenReturn(stored);
		when(epidemicOutbreakRepository.save(stored)).thenReturn(stored);

		assertEquals("Data updated successfully", epidemicOutbreakService.UpdateEpidemicOutbreakRequest(update));
		assertNull(stored.getNatureOfComplaint());
	}

	@Test
	void updateEpidemicOutbreakRequestReturnsNothingWhenTheSaveComesBackAsAnotherComplaint() throws Exception {
		T_EpidemicOutbreak stored = stored();
		T_EpidemicOutbreak other = stored();
		other.setOutbreakComplaintID(10L);
		T_EpidemicOutbreak update = new T_EpidemicOutbreak();
		update.setRequestID("EC/2/01012024/9");
		when(epidemicOutbreakRepository.searchByRequestID("EC/2/01012024/9")).thenReturn(stored);
		when(epidemicOutbreakRepository.save(stored)).thenReturn(other);

		assertNull(epidemicOutbreakService.UpdateEpidemicOutbreakRequest(update));
	}

	@Test
	void updateEpidemicOutbreakRequestRejectsAnUnknownRequestId() {
		when(epidemicOutbreakRepository.searchByRequestID(anyString())).thenReturn(null);
		T_EpidemicOutbreak update = new T_EpidemicOutbreak();
		update.setRequestID("EC/2/01012024/9");

		Exception thrown = assertThrows(Exception.class,
				() -> epidemicOutbreakService.UpdateEpidemicOutbreakRequest(update));

		assertTrue(thrown.getMessage().contains("No record found"));
	}
}
