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
package com.iemr.helpline104.service.grievance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.helpline104.data.grievance.T_FeedbackRequset;
import com.iemr.helpline104.repository.grievance.FeedbackRequest;

/**
 * Grievance request rows are stored and read back by feedback id.
 */
@ExtendWith(MockitoExtension.class)
class FeedbackRequestServiceTest {

	@Mock
	private FeedbackRequest feedbackRequest;

	private FeedbackRequestService feedbackRequestService;

	@BeforeEach
	void setUp() {
		feedbackRequestService = new FeedbackRequestService();
		ReflectionTestUtils.setField(feedbackRequestService, "feedbackrequest", feedbackRequest);
	}

	@Test
	void updateStoresTheRequestAndReportsItsId() {
		T_FeedbackRequset request = new T_FeedbackRequset();
		ReflectionTestUtils.setField(request, "feedbackRequestID", 9);

		assertEquals(9, feedbackRequestService.update(request));
		verify(feedbackRequest).save(request);
	}

	@Test
	void getAllFeedbackReadsEveryRowOfTheFeedback() {
		ArrayList<Object[]> rows = new ArrayList<>(Collections.singletonList(new Object[] { 9, "note" }));
		when(feedbackRequest.getAllFeedbackByID(9)).thenReturn(rows);

		assertEquals(rows, feedbackRequestService.getAllFeedback(9));
	}

	@Test
	void getdataByIdReadsTheOneRequest() {
		T_FeedbackRequset request = new T_FeedbackRequset();
		when(feedbackRequest.getdataById(9)).thenReturn(request);

		assertSame(request, feedbackRequestService.getdataById(9));
	}

	@Test
	void getdataById1ReadsTheOneRequest() {
		T_FeedbackRequset request = new T_FeedbackRequset();
		when(feedbackRequest.getdataById1(9)).thenReturn(request);

		assertSame(request, feedbackRequestService.getdataById1(9));
	}
}
