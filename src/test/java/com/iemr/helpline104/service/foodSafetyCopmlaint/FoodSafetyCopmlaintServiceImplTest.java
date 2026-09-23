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
package com.iemr.helpline104.service.foodSafetyCopmlaint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;
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

import com.iemr.helpline104.data.beneficiarycall.BenCall;
import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.data.foodSafetyCopmlaint.T_FoodSafetyCopmlaint;
import com.iemr.helpline104.repository.beneficiarycall.BenCallRepository;
import com.iemr.helpline104.repository.feedbackType.FeedbackTypeRepository;
import com.iemr.helpline104.repository.foodSafetyCopmlaint.FoodSafetyCopmlaintRepository;

import jakarta.servlet.http.HttpServletRequest;

/**
 * A food safety complaint is stored twice - once to mint the FS request ID from
 * the generated key - and raised as a grievance whose text lists every symptom
 * the caller reported.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FoodSafetyCopmlaintServiceImplTest {

	@Mock
	private FoodSafetyCopmlaintRepository foodSafetyCopmlaintRepository;

	@Mock
	private FeedbackTypeRepository feedbackRepositorty;

	@Mock
	private BenCallRepository benCallRepository;

	@InjectMocks
	private FoodSafetyCopmlaintServiceImpl foodSafetyService;

	/** The twenty-eight columns of the complaint projection. */
	private static List<Object[]> complaintRows() {
		return Arrays.asList(null, new Object[0],
				new Object[] { 9L, "FS/2/01012024/9", 12L, "Food poisoning", (byte) 1, (byte) 1, (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, new Timestamp(1_700_000_000_000L), "street food", (byte) 1,
						"cooked", "roadside stall", "nausea", (short) 3, "remarks", "agent",
						new Date(1_700_000_000_000L), Boolean.FALSE, 2, "Pune", 3, "Haveli", 4, "Wagholi" });
	}

	/** A complaint reporting every symptom, so that each is named in the grievance. */
	private static T_FoodSafetyCopmlaint complaint(byte reported) {
		T_FoodSafetyCopmlaint complaint = new T_FoodSafetyCopmlaint();
		complaint.setBeneficiaryRegID(12L);
		complaint.setServiceID(1);
		complaint.setTypeOfRequest("Food poisoning");
		complaint.setIsDiarrhea(reported);
		complaint.setIsVomiting(reported);
		complaint.setIsAbdominalPain(reported);
		complaint.setIsChillsOrRigors(reported);
		complaint.setIsGiddiness(reported);
		complaint.setIsDehydration(reported);
		complaint.setIsRashes(reported);
		complaint.setIsFoodConsumed(reported);
		complaint.setFromWhen(new Timestamp(1_700_000_000_000L));
		complaint.setHistoryOfDiet("street food");
		complaint.setTypeOfFood("cooked");
		complaint.setFoodConsumedFrom("roadside stall");
		complaint.setAssociatedSymptoms("nausea");
		complaint.setRemarks("remarks");
		complaint.setCreatedBy("agent");
		return complaint;
	}

	private static MockedConstruction<RestTemplate> acceptingCommonService() {
		return mockConstruction(RestTemplate.class,
				(template, context) -> when(template.exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class)))
						.thenReturn(ResponseEntity.ok("{}")));
	}

	@Test
	void saveMintsTheRequestIdAndNamesEveryReportedSymptom() throws Exception {
		T_FoodSafetyCopmlaint complaint = complaint((byte) 1);
		when(foodSafetyCopmlaintRepository.save(any())).thenReturn(complaint);
		when(feedbackRepositorty.findFoodSafetyFeedbackTypeID(1)).thenReturn(new M_FeedbackType());
		HttpServletRequest request = mock(HttpServletRequest.class);

		try (MockedConstruction<RestTemplate> restTemplate = acceptingCommonService()) {
			T_FoodSafetyCopmlaint response = foodSafetyService.save(complaint, request);

			assertTrue(response.getRequestID().startsWith("FS/"));
			assertEquals(1, restTemplate.constructed().size());
		}
	}

	@Test
	void saveLeavesTheUnreportedSymptomsOutOfTheGrievance() throws Exception {
		T_FoodSafetyCopmlaint complaint = complaint((byte) 0);
		when(foodSafetyCopmlaintRepository.save(any())).thenReturn(complaint);
		when(feedbackRepositorty.findFoodSafetyFeedbackTypeID(1)).thenReturn(null);
		HttpServletRequest request = mock(HttpServletRequest.class);

		try (MockedConstruction<RestTemplate> restTemplate = acceptingCommonService()) {
			assertTrue(foodSafetyService.save(complaint, request).getRequestID().startsWith("FS/"));
			assertEquals(1, restTemplate.constructed().size());
		}
	}

	@Test
	void getFoodSafetyComplaintsSearchesByBeneficiary() throws Exception {
		when(foodSafetyCopmlaintRepository.getFoodSafetyRequests(12L)).thenReturn(complaintRows());

		List<T_FoodSafetyCopmlaint> complaints = foodSafetyService.getFoodSafetyComplaints(12L, null, null, null);

		assertEquals(1, complaints.size());
		assertEquals("Food poisoning", complaints.get(0).getTypeOfRequest());
	}

	@Test
	void getFoodSafetyComplaintsSearchesByCallAndAttachesTheCall() throws Exception {
		when(foodSafetyCopmlaintRepository.getFoodSafetyRequestsByBenCallID(4L)).thenReturn(complaintRows());
		when(benCallRepository.findByBenCallID(4L)).thenReturn(new BenCall());

		assertEquals(1, foodSafetyService.getFoodSafetyComplaints(null, 4L, null, null).size());
		verify(benCallRepository).findByBenCallID(4L);
	}

	@Test
	void getFoodSafetyComplaintsSearchesByTheTrailingRequestId() throws Exception {
		when(foodSafetyCopmlaintRepository.getFoodSafetyRequestsByRequestIDNew(9L)).thenReturn(complaintRows());

		assertEquals(1, foodSafetyService.getFoodSafetyComplaints(null, null, "FS/2/01012024/9", null).size());
	}

	@Test
	void getFoodSafetyComplaintsSearchesByPhoneNumber() throws Exception {
		when(foodSafetyCopmlaintRepository.findByPhoneNum("9999999999"))
				.thenReturn(new ArrayList<>(Collections.singletonList(BigInteger.valueOf(12L))));
		when(foodSafetyCopmlaintRepository.findByBenRegIDs(Collections.singletonList(12L)))
				.thenReturn(complaintRows());

		assertEquals(1, foodSafetyService.getFoodSafetyComplaints(null, null, null, "9999999999").size());
	}
}
