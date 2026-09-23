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
package com.iemr.helpline104.controller.feedback;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.feedback.FeedbackDetails;
import com.iemr.helpline104.service.feedback.FeedbackService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The grievance endpoints wrap the service answer in an OutputResponse. An
 * update needs a request ID, and both update and save report an answer that
 * came back empty.
 */
@ExtendWith(MockitoExtension.class)
class FeedbackControllerTest {

	private static final String REQUEST = "{\"beneficiaryRegID\":12,\"requestID\":\"GC/9/01012024/9\"}";

	@Mock
	private FeedbackService feedbackService;

	private FeedbackController controller;

	@BeforeEach
	void setUp() {
		controller = new FeedbackController();
		controller.setFeedbackService(feedbackService);
	}

	@Test
	void feedbackReuestReturnsTheBeneficiarysGrievances() {
		when(feedbackService.getFeedbackRequests(anyLong()))
				.thenReturn(Collections.singletonList(new FeedbackDetails()));

		assertTrue(controller.feedbackReuest(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void feedbackReuestReportsAFailure() {
		when(feedbackService.getFeedbackRequests(anyLong())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.feedbackReuest(REQUEST).contains("db down"));
	}

	@Test
	void getFeedbackByPostReturnsTheOneGrievance() {
		when(feedbackService.getFeedbackRequests(9L)).thenReturn(Collections.singletonList(new FeedbackDetails()));

		assertTrue(controller.getFeedbackByPost(9).contains("\"statusCode\":200"));
	}

	@Test
	void getFeedbackByPostReportsAFailure() {
		when(feedbackService.getFeedbackRequests(9L)).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getFeedbackByPost(9).contains("db down"));
	}

	@Test
	void updateFeedbackReturnsSuccess() throws Exception {
		when(feedbackService.updateFeedback(any())).thenReturn("Data updated successfully");

		assertTrue(controller.updateFeedback(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void updateFeedbackReportsAnUpdateThatChangedNothing() throws Exception {
		when(feedbackService.updateFeedback(any())).thenReturn(null);

		assertTrue(controller.updateFeedback(REQUEST).contains("Error in data update"));
	}

	@Test
	void updateFeedbackNeedsARequestId() {
		assertTrue(controller.updateFeedback("{\"beneficiaryRegID\":12}").contains("Request ID is mandatory"));
	}

	@Test
	void updateFeedbackReportsAFailure() throws Exception {
		when(feedbackService.updateFeedback(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.updateFeedback(REQUEST).contains("db down"));
	}

	@Test
	void saveBenFeedbackReturnsSuccess() throws Exception {
		when(feedbackService.saveFeedbackFromCustomer(anyString(), any()))
				.thenReturn("{\"feedBackId\":\"9\"}");

		assertTrue(controller.saveBenFeedback(REQUEST, mock(HttpServletRequest.class))
				.contains("\"statusCode\":200"));
	}

	@Test
	void saveBenFeedbackReportsASaveThatAnsweredNothing() throws Exception {
		when(feedbackService.saveFeedbackFromCustomer(anyString(), any())).thenReturn(null);

		assertTrue(controller.saveBenFeedback(REQUEST, mock(HttpServletRequest.class))
				.contains("error in saving feedback"));
	}

	@Test
	void saveBenFeedbackReportsAFailure() throws Exception {
		when(feedbackService.saveFeedbackFromCustomer(anyString(), any()))
				.thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveBenFeedback(REQUEST, mock(HttpServletRequest.class))
				.contains("saving feedback failed"));
	}
}
