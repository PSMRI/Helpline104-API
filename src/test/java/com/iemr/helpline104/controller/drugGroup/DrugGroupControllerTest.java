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
package com.iemr.helpline104.controller.drugGroup;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.drugGroup.M_DrugGroup;
import com.iemr.helpline104.data.drugMapping.M_104drugmapping;
import com.iemr.helpline104.data.drugMaster.DrugFrequency;
import com.iemr.helpline104.data.drugMaster.DrugStrength;
import com.iemr.helpline104.service.drugGroup.DrugGroupService;

/**
 * The drug master endpoints wrap the service answer in an OutputResponse, and
 * report a failure as the error the response carries.
 */
@ExtendWith(MockitoExtension.class)
class DrugGroupControllerTest {

	private static final String REQUEST = "{\"serviceProviderID\":1,\"providerServiceMapID\":1,\"drugGroupID\":2}";

	@Mock
	private DrugGroupService drugGroupService;

	@InjectMocks
	private DrugGroupController controller;

	@Test
	void getDrugGroupsReturnsSuccess() throws Exception {
		when(drugGroupService.getDrugGroups(any())).thenReturn(Collections.singletonList(new M_DrugGroup()));

		assertTrue(controller.getDrugGroups(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getDrugGroupsReportsAFailure() throws Exception {
		when(drugGroupService.getDrugGroups(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getDrugGroups(REQUEST).contains("db down"));
	}

	@Test
	void getDrugListReturnsSuccess() throws Exception {
		when(drugGroupService.getDrugList(any(), any()))
				.thenReturn(Collections.singletonList(new M_104drugmapping()));

		assertTrue(controller.getDrugList(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getDrugListReportsNoDrugFound() throws Exception {
		when(drugGroupService.getDrugList(any(), any())).thenReturn(Collections.emptyList());

		assertTrue(controller.getDrugList(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getDrugListReportsAFailure() throws Exception {
		when(drugGroupService.getDrugList(any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getDrugList(REQUEST).contains("db down"));
	}

	@Test
	void getDrugFrequencyReturnsSuccess() throws Exception {
		when(drugGroupService.getDrugFrequency())
				.thenReturn(new ArrayList<>(Collections.singletonList(new DrugFrequency())));

		assertTrue(controller.getDrugFrequency().contains("\"statusCode\":200"));
	}

	@Test
	void getDrugFrequencyReportsAFailure() throws Exception {
		when(drugGroupService.getDrugFrequency()).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getDrugFrequency().contains("db down"));
	}

	@Test
	void getDrugStrengthReturnsSuccess() throws Exception {
		when(drugGroupService.getDrugStrength(any()))
				.thenReturn(new ArrayList<>(Collections.singletonList(new DrugStrength())));

		assertTrue(controller.getDrugStrength(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getDrugStrengthReportsAFailure() throws Exception {
		when(drugGroupService.getDrugStrength(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getDrugStrength(REQUEST).contains("db down"));
	}

	@Test
	void getDrugNameListReturnsSuccess() throws Exception {
		when(drugGroupService.getDrugDetailList(any()))
				.thenReturn(Collections.singletonList(new M_104drugmapping()));

		assertTrue(controller.getDrugNameList(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getDrugNameListReportsAFailure() throws Exception {
		when(drugGroupService.getDrugDetailList(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getDrugNameList(REQUEST).contains("db down"));
	}
}
