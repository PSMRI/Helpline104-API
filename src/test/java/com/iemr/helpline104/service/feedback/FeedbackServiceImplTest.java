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
package com.iemr.helpline104.service.feedback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.iemr.helpline104.data.feedback.FeedbackDetails;
import com.iemr.helpline104.data.feedback.FeedbackRequestDetails;
import com.iemr.helpline104.repository.beneficiarycall.BeneficiaryCallRepository;
import com.iemr.helpline104.repository.beneficiarycall.ServicesHistoryRepository;
import com.iemr.helpline104.repository.bloodRequest.InstituteRepository;
import com.iemr.helpline104.repository.feedback.FeedbackRepository;
import com.iemr.helpline104.repository.feedbackType.FeedbackTypeRepository;
import com.iemr.helpline104.utils.config.ConfigProperties;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Feedback is read back as flat projections, created with its request details
 * attached, and updated field by field - only the fields the caller sent are
 * written over the stored grievance.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FeedbackServiceImplTest {

	@Mock
	private FeedbackRepository feedbackRepository;

	@Mock
	private InstituteRepository instituteRepo;

	@Mock
	private FeedbackTypeRepository feedbackTypeRepository;

	@Mock
	private BeneficiaryCallRepository beneficiaryCallRepository;

	@Mock
	private ServicesHistoryRepository servicesHistoryRepository;

	@Mock
	private ConfigProperties configProperties;

	private FeedbackServiceImpl feedbackService;

	@BeforeEach
	void setUp() {
		feedbackService = new FeedbackServiceImpl();
		feedbackService.setFeedbackRepository(feedbackRepository);
		feedbackService.getBenCalServiceCatSubcatMappingRepo(beneficiaryCallRepository);
		feedbackService.setProperties(configProperties);
		org.springframework.test.util.ReflectionTestUtils.setField(feedbackService, "instituteRepo", instituteRepo);
		org.springframework.test.util.ReflectionTestUtils.setField(feedbackService, "feedbackRepositorty",
				feedbackTypeRepository);
		org.springframework.test.util.ReflectionTestUtils.setField(feedbackService, "servicesHistoryRepository",
				servicesHistoryRepository);
	}

	/** The seven columns the flat feedback projection reads. */
	private static ArrayList<Object[]> projection() {
		return new ArrayList<>(Arrays.asList(null, new Object[0],
				new Object[] { 9L, (short) 1, (short) 2, (short) 3, "feedback", "GC/9/01012024/9", "OPEN" }));
	}

	private static FeedbackDetails stored() {
		FeedbackDetails stored = new FeedbackDetails();
		stored.setFeedbackID(9L);
		stored.setRequestID("GC/9/01012024/9");
		return stored;
	}

	/** A grievance carrying every updatable field. */
	private static FeedbackDetails update() {
		FeedbackDetails update = new FeedbackDetails();
		update.setRequestID("GC/9/01012024/9");
		update.setServiceAvailDate(new Date(1_700_000_000_000L));
		update.setInstiName("District Hospital");
		update.setStateID(1);
		update.setDistrictID(2);
		update.setBlockID(3);
		update.setDistrictBranchID(4);
		update.setFeedbackTypeID((short) 5);
		update.setFeedbackNatureID(6);
		update.setDesignationID(7);
		update.setInstituteTypeID(8);
		update.setInstitutionID(11L);
		update.setFeedbackAgainst("staff");
		update.setFeedback("long wait");
		update.setSeverityID((short) 2);
		return update;
	}

	@Test
	void getFeedbackRequestsProjectsEveryRowForTheBeneficiary() {
		when(feedbackRepository.findByBeneficiaryID(12L)).thenReturn(projection());

		List<FeedbackDetails> feedback = feedbackService.getFeedbackRequests(12L);

		assertEquals(1, feedback.size());
		assertEquals(9L, feedback.get(0).getFeedbackID());
	}

	@Test
	void getFeedbackRequestProjectsTheOneFeedback() {
		when(feedbackRepository.findByFeedbackID(9L)).thenReturn(projection());

		assertEquals(1, feedbackService.getFeedbackRequest(9L).size());
	}

	@Test
	void createFeedbackPointsEveryRequestDetailBackAtItsFeedback() {
		FeedbackDetails feedback = stored();
		FeedbackRequestDetails detail = new FeedbackRequestDetails();
		feedback.setFeedbackRequestDetails(new ArrayList<>(Collections.singletonList(detail)));
		when(feedbackRepository.save(feedback)).thenReturn(feedback);

		assertSame(feedback, feedbackService.createFeedback(feedback));
		assertSame(feedback, detail.getFeedback());
	}

	@Test
	void updateFeedbackWritesEverySentFieldOverTheStoredGrievance() throws Exception {
		FeedbackDetails stored = stored();
		when(feedbackRepository.searchByRequestID("GC/9/01012024/9")).thenReturn(stored);
		when(feedbackRepository.save(stored)).thenReturn(stored);
		when(instituteRepo.getInstituteName(11)).thenReturn("Community Health Centre");

		assertEquals("Data updated successfully", feedbackService.updateFeedback(update()));

		assertEquals("Community Health Centre", stored.getInstiName());
		assertEquals("long wait", stored.getFeedback());
		assertEquals(Integer.valueOf(2), stored.getDistrictID());
	}

	@Test
	void updateFeedbackKeepsTheStoredInstituteNameWhenTheIdIsUnknown() throws Exception {
		FeedbackDetails stored = stored();
		when(feedbackRepository.searchByRequestID("GC/9/01012024/9")).thenReturn(stored);
		when(feedbackRepository.save(stored)).thenReturn(stored);
		when(instituteRepo.getInstituteName(11)).thenReturn(null);

		assertEquals("Data updated successfully", feedbackService.updateFeedback(update()));
		assertEquals("District Hospital", stored.getInstiName());
	}

	@Test
	void updateFeedbackLeavesTheStoredGrievanceAloneWhenNothingIsSent() throws Exception {
		FeedbackDetails stored = stored();
		FeedbackDetails empty = new FeedbackDetails();
		empty.setRequestID("GC/9/01012024/9");
		when(feedbackRepository.searchByRequestID("GC/9/01012024/9")).thenReturn(stored);
		when(feedbackRepository.save(stored)).thenReturn(stored);

		assertEquals("Data updated successfully", feedbackService.updateFeedback(empty));
		assertNull(stored.getFeedback());
	}

	@Test
	void updateFeedbackReturnsNothingWhenTheSaveComesBackAsAnotherGrievance() throws Exception {
		FeedbackDetails stored = stored();
		FeedbackDetails other = stored();
		other.setFeedbackID(10L);
		when(feedbackRepository.searchByRequestID("GC/9/01012024/9")).thenReturn(stored);
		when(feedbackRepository.save(stored)).thenReturn(other);

		assertNull(feedbackService.updateFeedback(update()));
	}

	@Test
	void updateFeedbackRejectsAnUnknownRequestId() {
		when(feedbackRepository.searchByRequestID(anyString())).thenReturn(null);

		Exception thrown = assertThrows(Exception.class, () -> feedbackService.updateFeedback(update()));

		assertTrue(thrown.getMessage().contains("No record found"));
	}

	@Test
	void saveFeedbackFromCustomerReportsARejectionFromTheCommonService() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getHeader("Authorization")).thenReturn("Bearer token");

		try (MockedConstruction<RestTemplate> restTemplate = mockConstruction(RestTemplate.class,
				(template, context) -> when(template.exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class)))
						.thenReturn(ResponseEntity.ok("{}")))) {

			Exception thrown = assertThrows(Exception.class,
					() -> feedbackService.saveFeedbackFromCustomer("[{\"feedback\":\"long wait\"}]", request));

			assertTrue(thrown.getMessage().contains("error in saving feedback"));
			assertEquals(1, restTemplate.constructed().size());
		}
	}
}
