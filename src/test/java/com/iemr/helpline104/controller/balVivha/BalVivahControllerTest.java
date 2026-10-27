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
package com.iemr.helpline104.controller.balVivha;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.service.balVivah.BalVivahComplaintService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The child marriage endpoints wrap the service answer in an OutputResponse.
 * An update needs a request ID, and reports a save that changed nothing.
 */
@ExtendWith(MockitoExtension.class)
class BalVivahControllerTest {

	private static final String REQUEST = "{\"beneficiaryRegID\":12,\"requestID\":\"BV/2/01012024/9\","
			+ "\"phoneNum\":\"9999999999\"}";

	@Mock
	private BalVivahComplaintService balVivahComplaintService;

	@InjectMocks
	private BalVivahController controller;

	@Test
	void balVivahComplaintReturnsSuccess() throws Exception {
		when(balVivahComplaintService.save(any(), any())).thenReturn("{\"balVivahComplaintID\":9}");

		assertTrue(controller.balVivahComplaint(REQUEST, mock(HttpServletRequest.class))
				.contains("\"statusCode\":200"));
	}

	@Test
	void balVivahComplaintReportsAFailure() throws Exception {
		when(balVivahComplaintService.save(any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.balVivahComplaint(REQUEST, mock(HttpServletRequest.class)).contains("db down"));
	}

	@Test
	void feedbackReuestReturnsTheWorklist() throws Exception {
		when(balVivahComplaintService.getWorklistRequests(any(), any(), any())).thenReturn("[]");

		assertTrue(controller.feedbackReuest(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void feedbackReuestReportsAFailure() throws Exception {
		when(balVivahComplaintService.getWorklistRequests(any(), any(), any()))
				.thenThrow(new RuntimeException("db down"));

		assertTrue(controller.feedbackReuest(REQUEST).contains("db down"));
	}

	@Test
	void updateBalVivahComplaintReturnsSuccess() throws Exception {
		when(balVivahComplaintService.updateBalVivahRequest(any())).thenReturn("Data updated successfully");

		assertTrue(controller.updateBalVivahComplaint(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void updateBalVivahComplaintReportsAnUpdateThatChangedNothing() throws Exception {
		when(balVivahComplaintService.updateBalVivahRequest(any())).thenReturn(null);

		assertTrue(controller.updateBalVivahComplaint(REQUEST).contains("Error in data update"));
	}

	@Test
	void updateBalVivahComplaintNeedsARequestId() throws Exception {
		assertTrue(controller.updateBalVivahComplaint("{\"beneficiaryRegID\":12}")
				.contains("Request ID is mandatory"));
	}

	@Test
	void updateBalVivahComplaintReportsAFailure() throws Exception {
		when(balVivahComplaintService.updateBalVivahRequest(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.updateBalVivahComplaint(REQUEST).contains("db down"));
	}
}
