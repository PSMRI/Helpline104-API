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
package com.iemr.helpline104.controller.beneficiarycall;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.beneficiarycall.BenCallServicesMappingHistory;
import com.iemr.helpline104.data.beneficiarycall.BeneficiaryCall;
import com.iemr.helpline104.data.beneficiarycall.M_subservice;
import com.iemr.helpline104.service.beneficiarycall.BeneficiaryCallService;
import com.iemr.helpline104.service.beneficiarycall.ServicesHistoryService;

/**
 * The call endpoints wrap the service answer in an OutputResponse. Attaching a
 * beneficiary to a call is skipped when the caller did not send one.
 */
@ExtendWith(MockitoExtension.class)
class BeneficiaryCallControllerTest {

	private static final String REQUEST = "{\"beneficiaryRegID\":12,\"benCallID\":4,\"callID\":\"call-1\","
			+ "\"providerServiceMapID\":1}";

	@Mock
	private ServicesHistoryService servicesHistoryService;

	@Mock
	private BeneficiaryCallService beneficiaryCallService;

	private BeneficiaryCallController controller;

	@BeforeEach
	void setUp() {
		controller = new BeneficiaryCallController();
		controller.setService1097HistoryService(servicesHistoryService);
		controller.setBeneficiaryCallService(beneficiaryCallService);
	}

	@Test
	void startCallReturnsTheStartedCall() {
		when(beneficiaryCallService.createCall(any())).thenReturn(new BeneficiaryCall());

		assertTrue(controller.startCall(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void startCallReportsAFailure() {
		when(beneficiaryCallService.createCall(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.startCall(REQUEST).contains("db down"));
	}

	@Test
	void updateBeneficiaryIDInCallReportsTheRowsItTouched() {
		when(beneficiaryCallService.updateBeneficiaryIDInCall(4L, 12L)).thenReturn(1);

		assertTrue(controller.updateBeneficiaryIDInCall(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void updateBeneficiaryIDInCallIsSkippedWithoutABeneficiary() {
		assertTrue(controller.updateBeneficiaryIDInCall("{\"benCallID\":4}").contains("Update skipped"));
		verify(beneficiaryCallService, never()).updateBeneficiaryIDInCall(any(), any());
	}

	@Test
	void updateBeneficiaryIDInCallReportsAFailure() {
		when(beneficiaryCallService.updateBeneficiaryIDInCall(4L, 12L)).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.updateBeneficiaryIDInCall(REQUEST).contains("db down"));
	}

	@Test
	void getServicesReturnsTheProvidersSubServices() {
		when(servicesHistoryService.getServices(1)).thenReturn(Collections.singletonList(new M_subservice()));

		assertTrue(controller.getServices(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getServicesReportsAFailure() {
		when(servicesHistoryService.getServices(1)).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getServices(REQUEST).contains("db down"));
	}

	@Test
	void setServiceHistoryRecordsTheServiceAvailed() {
		when(servicesHistoryService.createServiceHistory(any())).thenReturn(new BenCallServicesMappingHistory());

		assertTrue(controller.setServiceHistory(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void setServiceHistoryReportsAFailure() {
		when(servicesHistoryService.createServiceHistory(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.setServiceHistory(REQUEST).contains("db down"));
	}
}
