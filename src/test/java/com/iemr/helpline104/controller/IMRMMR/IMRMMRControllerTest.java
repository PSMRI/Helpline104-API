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
package com.iemr.helpline104.controller.IMRMMR;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.helpline104.data.IMRMMR.M_facilities;
import com.iemr.helpline104.data.IMRMMR.M_supportServices;
import com.iemr.helpline104.service.IMRMMR.IMRMMRService;

/**
 * The death registration endpoints wrap the service answer in an
 * OutputResponse. An update needs a request ID, and reports a save that
 * changed nothing.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class IMRMMRControllerTest {

	private static final String REQUEST = "{\"beneficiaryRegID\":12,\"requestID\":\"MDSR-CDR/9/01012024/77\","
			+ "\"phoneNum\":\"9999999999\"}";

	@Mock
	private IMRMMRService imrmmrService;

	@InjectMocks
	private IMRMMRController controller;

	@Test
	void saveIMRMMRReturnsSuccess() throws Exception {
		when(imrmmrService.saveIMRMMR(anyString(), anyString())).thenReturn("MDSR/CDR Data Saved Successfully");

		assertTrue(controller.saveIMRMMR(REQUEST, "Bearer token").contains("\"statusCode\":200"));
	}

	@Test
	void saveIMRMMRReportsASaveThatAnsweredNothing() throws Exception {
		when(imrmmrService.saveIMRMMR(anyString(), anyString())).thenReturn(null);

		assertTrue(controller.saveIMRMMR(REQUEST, "Bearer token").contains("error in saving IMR/MMR data"));
	}

	@Test
	void saveIMRMMRReportsAFailure() throws Exception {
		when(imrmmrService.saveIMRMMR(anyString(), anyString())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveIMRMMR(REQUEST, "Bearer token").contains("db down"));
	}

	@Test
	void fetchSupportServicesServesEveryMaster() throws Exception {
		when(imrmmrService.getsupportServices()).thenReturn(Collections.singletonList(new M_supportServices()));
		when(imrmmrService.getFacilities()).thenReturn(Collections.singletonList(new M_facilities()));
		when(imrmmrService.getBaseCommunities()).thenReturn(Collections.emptyList());
		when(imrmmrService.getTransitType()).thenReturn(Collections.emptyList());
		when(imrmmrService.getHealthWorker()).thenReturn(Collections.emptyList());

		String response = controller.fetchSupportServices();

		assertTrue(response.contains("supportServicesList"));
		assertTrue(response.contains("healthWorkerList"));
	}

	@Test
	void fetchSupportServicesReportsAFailure() throws Exception {
		when(imrmmrService.getsupportServices()).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.fetchSupportServices().contains("db down"));
	}

	@Test
	void feedbackReuestReturnsTheWorklist() {
		when(imrmmrService.getWorklistRequests(any(), any(), any())).thenReturn("[]");

		assertTrue(controller.feedbackReuest(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void feedbackReuestReportsAFailure() {
		when(imrmmrService.getWorklistRequests(any(), any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.feedbackReuest(REQUEST).contains("db down"));
	}

	@Test
	void updateImrMmrComplaintReturnsSuccess() throws Exception {
		when(imrmmrService.updateImrMmrRequest(any())).thenReturn("Data updated successfully");

		assertTrue(controller.updateImrMmrComplaint(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void updateImrMmrComplaintReportsAnUpdateThatChangedNothing() throws Exception {
		when(imrmmrService.updateImrMmrRequest(any())).thenReturn(null);

		assertTrue(controller.updateImrMmrComplaint(REQUEST).contains("Error in data update"));
	}

	@Test
	void updateImrMmrComplaintNeedsARequestId() {
		assertTrue(controller.updateImrMmrComplaint("{\"beneficiaryRegID\":12}").contains("Request ID is mandatory"));
	}

	@Test
	void updateImrMmrComplaintReportsAFailure() throws Exception {
		when(imrmmrService.updateImrMmrRequest(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.updateImrMmrComplaint(REQUEST).contains("db down"));
	}
}
