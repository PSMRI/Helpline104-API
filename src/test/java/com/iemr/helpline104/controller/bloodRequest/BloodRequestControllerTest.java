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
package com.iemr.helpline104.controller.bloodRequest;

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

import com.iemr.helpline104.data.bloodComponentType.M_BloodGroup;
import com.iemr.helpline104.data.bloodComponentType.M_ComponentType;
import com.iemr.helpline104.data.bloodRequest.BloodBank;
import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;
import com.iemr.helpline104.service.bloodComponentType.BloodComponentTypeService;
import com.iemr.helpline104.service.bloodRequest.BloodRequestService;

/**
 * The blood request endpoints wrap the service answer in an OutputResponse, and
 * say so when the blood bank URL has not been configured.
 */
@ExtendWith(MockitoExtension.class)
class BloodRequestControllerTest {

	private static final String REQUEST = "{\"beneficiaryRegID\":12,\"benCallID\":4,"
			+ "\"requestID\":\"BR/2/01012024/9\"}";

	@Mock
	private BloodRequestService bloodRequestService;

	@Mock
	private BloodComponentTypeService componentTypeService;

	@InjectMocks
	private BloodRequestController controller;

	@Test
	void saveBloodRequestDetailsReturnsSuccess() throws Exception {
		when(bloodRequestService.save(any())).thenReturn(new T_BloodRequest());

		assertTrue(controller.saveBloodRequestDetails(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void saveBloodRequestDetailsReportsAFailure() throws Exception {
		when(bloodRequestService.save(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveBloodRequestDetails(REQUEST).contains("db down"));
	}

	@Test
	void getbloodRequestDetailsReturnsSuccess() throws Exception {
		when(bloodRequestService.getBloodRequest(any(), any(), any()))
				.thenReturn(Collections.singletonList(new T_BloodRequest()));

		assertTrue(controller.getbloodRequestDetails(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getbloodRequestDetailsReportsNoRequestFound() throws Exception {
		when(bloodRequestService.getBloodRequest(any(), any(), any())).thenReturn(Collections.emptyList());

		assertTrue(controller.getbloodRequestDetails(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getbloodRequestDetailsReportsAFailure() throws Exception {
		when(bloodRequestService.getBloodRequest(any(), any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getbloodRequestDetails(REQUEST).contains("db down"));
	}

	@Test
	void getBloodComponentTypesReturnsSuccess() throws Exception {
		when(componentTypeService.getBloodComponentTypes())
				.thenReturn(Collections.singletonList(new M_ComponentType()));

		assertTrue(controller.getBloodComponentTypes().contains("\"statusCode\":200"));
	}

	@Test
	void getBloodComponentTypesReportsAFailure() throws Exception {
		when(componentTypeService.getBloodComponentTypes()).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getBloodComponentTypes().contains("db down"));
	}

	@Test
	void getBloodGroupsReturnsSuccess() throws Exception {
		when(componentTypeService.getBloodGroups()).thenReturn(Collections.singletonList(new M_BloodGroup()));

		assertTrue(controller.getBloodGroups().contains("\"statusCode\":200"));
	}

	@Test
	void getBloodGroupsReportsAFailure() throws Exception {
		when(componentTypeService.getBloodGroups()).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getBloodGroups().contains("db down"));
	}

	@Test
	void getBloodBankURLReturnsTheConfiguredUrl() throws Exception {
		when(bloodRequestService.getBloodBankURL(anyString())).thenReturn(new BloodBank());

		assertTrue(controller.getBloodBankURL(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getBloodBankURLSaysSoWhenItIsNotConfigured() throws Exception {
		when(bloodRequestService.getBloodBankURL(anyString())).thenReturn(null);

		assertTrue(controller.getBloodBankURL(REQUEST).contains("Blood bank URL is not configured"));
	}

	@Test
	void getBloodBankURLReportsAFailure() throws Exception {
		when(bloodRequestService.getBloodBankURL(anyString())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getBloodBankURL(REQUEST).contains("db down"));
	}

	@Test
	void saveBloodBankURLReturnsSuccess() throws Exception {
		when(bloodRequestService.saveBloodBankURL(any())).thenReturn(new BloodBank());

		assertTrue(controller.saveBloodBankURL("{\"institutionURL\":\"http://blood.example.org\"}")
				.contains("\"statusCode\":200"));
	}

	@Test
	void saveBloodBankURLReportsAFailure() throws Exception {
		when(bloodRequestService.saveBloodBankURL(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveBloodBankURL("{\"institutionURL\":\"http://blood.example.org\"}")
				.contains("db down"));
	}
}
