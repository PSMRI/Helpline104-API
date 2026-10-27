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
package com.iemr.helpline104.controller.epidemicOutbreak;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.epidemicOutbreak.T_EpidemicOutbreak;
import com.iemr.helpline104.service.epidemicOutbreak.EpidemicOutbreakService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The outbreak endpoints wrap the service answer in an OutputResponse. An
 * update needs a request ID, and reports a save that changed nothing.
 */
@ExtendWith(MockitoExtension.class)
class EpidemicOutbreakControllerTest {

	private static final String REQUEST = "{\"beneficiaryRegID\":12,\"benCallID\":4,"
			+ "\"requestID\":\"EC/2/01012024/9\",\"phoneNum\":\"9999999999\"}";

	@Mock
	private EpidemicOutbreakService epidemicOutbreakService;

	@InjectMocks
	private EpidemicOutbreakController controller;

	@Test
	void saveEpidemicOutbreakComplaintReturnsSuccess() throws Exception {
		when(epidemicOutbreakService.save(any(), any())).thenReturn(new T_EpidemicOutbreak());

		assertTrue(controller.saveEpidemicOutbreakComplaint(REQUEST, mock(HttpServletRequest.class))
				.contains("\"statusCode\":200"));
	}

	@Test
	void saveEpidemicOutbreakComplaintReportsAFailure() throws Exception {
		when(epidemicOutbreakService.save(any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveEpidemicOutbreakComplaint(REQUEST, mock(HttpServletRequest.class))
				.contains("db down"));
	}

	@Test
	void getEpidemicOutbreakComplaintReturnsSuccess() throws Exception {
		when(epidemicOutbreakService.getEpidemicOutbreakComplaints(any(), any(), any(), any()))
				.thenReturn(Collections.singletonList(new T_EpidemicOutbreak()));

		assertTrue(controller.getEpidemicOutbreakComplaint(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getEpidemicOutbreakComplaintReportsNoComplaintFound() throws Exception {
		when(epidemicOutbreakService.getEpidemicOutbreakComplaints(any(), any(), any(), any()))
				.thenReturn(Collections.emptyList());

		assertTrue(controller.getEpidemicOutbreakComplaint(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getEpidemicOutbreakComplaintReportsAFailure() throws Exception {
		when(epidemicOutbreakService.getEpidemicOutbreakComplaints(any(), any(), any(), any()))
				.thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getEpidemicOutbreakComplaint(REQUEST).contains("db down"));
	}

	@Test
	void updateEpidemicOutbreakComplaintReturnsSuccess() throws Exception {
		when(epidemicOutbreakService.UpdateEpidemicOutbreakRequest(any())).thenReturn("Data updated successfully");

		assertTrue(controller.updateEpidemicOutbreakComplaint(REQUEST, mock(HttpServletRequest.class))
				.contains("\"statusCode\":200"));
	}

	@Test
	void updateEpidemicOutbreakComplaintReportsAnUpdateThatChangedNothing() throws Exception {
		when(epidemicOutbreakService.UpdateEpidemicOutbreakRequest(any())).thenReturn(null);

		assertTrue(controller.updateEpidemicOutbreakComplaint(REQUEST, mock(HttpServletRequest.class))
				.contains("Error in data update"));
	}

	@Test
	void updateEpidemicOutbreakComplaintNeedsARequestId() {
		assertTrue(controller
				.updateEpidemicOutbreakComplaint("{\"beneficiaryRegID\":12}", mock(HttpServletRequest.class))
				.contains("Request ID is mandatory"));
	}

	@Test
	void updateEpidemicOutbreakComplaintReportsAFailure() throws Exception {
		when(epidemicOutbreakService.UpdateEpidemicOutbreakRequest(any()))
				.thenThrow(new RuntimeException("db down"));

		assertTrue(controller.updateEpidemicOutbreakComplaint(REQUEST, mock(HttpServletRequest.class))
				.contains("db down"));
	}
}
