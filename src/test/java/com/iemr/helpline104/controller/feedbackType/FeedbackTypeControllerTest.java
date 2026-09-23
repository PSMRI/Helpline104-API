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
package com.iemr.helpline104.controller.feedbackType;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.service.feedbackType.FeedbackTypeService;

/**
 * The nature-of-complaint endpoint wraps the service answer in an
 * OutputResponse, and reports a failure as the error the response carries.
 */
@ExtendWith(MockitoExtension.class)
class FeedbackTypeControllerTest {

	private static final String REQUEST = "{\"providerServiceMapID\":1,\"feedbackTypeID\":2}";

	@Mock
	private FeedbackTypeService feedbackTypeService;

	@InjectMocks
	private FeedbackTypeController controller;

	@Test
	void getNatureOfComplaintTypesReturnsSuccess() throws Exception {
		when(feedbackTypeService.getNatureOfComplaintTypes(any(), any()))
				.thenReturn(Collections.singletonList(new M_FeedbackType()));

		assertTrue(controller.getNatureOfComplaintTypes(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getNatureOfComplaintTypesReportsAFailure() throws Exception {
		when(feedbackTypeService.getNatureOfComplaintTypes(any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getNatureOfComplaintTypes(REQUEST).contains("db down"));
	}
}
