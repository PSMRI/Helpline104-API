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
package com.iemr.helpline104.controller.callqamapping;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.callqamapping.M_104callqamapping;
import com.iemr.helpline104.service.callqamapping.CallqamappingService;

/**
 * The CDI answer endpoints wrap the service answer in an OutputResponse, and
 * report a failure as the error the response carries.
 */
@ExtendWith(MockitoExtension.class)
class CallQAMappingControllerTest {

	private static final String REQUEST = "{\"beneficiaryRegID\":12,\"benCallID\":4}";

	@Mock
	private CallqamappingService callqamappingService;

	@InjectMocks
	private CallQAMappingController controller;

	@Test
	void saveCallqamappingReturnsSuccess() throws Exception {
		when(callqamappingService.save(any())).thenReturn(Collections.singletonList(new M_104callqamapping()));

		assertTrue(controller.saveCallqamapping(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void saveCallqamappingReportsAFailure() throws Exception {
		when(callqamappingService.save(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveCallqamapping(REQUEST).contains("db down"));
	}

	@Test
	void getCDIqamappingReturnsSuccess() throws Exception {
		when(callqamappingService.getCDIQuestionScores(any()))
				.thenReturn(Collections.singletonList(new M_104callqamapping()));

		assertTrue(controller.getCDIqamapping(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getCDIqamappingReportsNoAnswersFound() throws Exception {
		when(callqamappingService.getCDIQuestionScores(any())).thenReturn(Collections.emptyList());

		assertTrue(controller.getCDIqamapping(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getCDIqamappingReportsAFailure() throws Exception {
		when(callqamappingService.getCDIQuestionScores(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getCDIqamapping(REQUEST).contains("db down"));
	}
}
