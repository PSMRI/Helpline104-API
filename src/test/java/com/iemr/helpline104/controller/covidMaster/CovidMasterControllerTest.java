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
package com.iemr.helpline104.controller.covidMaster;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.service.covidMaster.CovidMasterService;

/**
 * The COVID master endpoint answers with the master document, and the screening
 * endpoint reports a failed save as an error carrying the reason.
 */
@ExtendWith(MockitoExtension.class)
class CovidMasterControllerTest {

	@Mock
	private CovidMasterService covidMasterService;

	@InjectMocks
	private CovidMasterController controller;

	@Test
	void patientAppMasterDataReturnsSuccess() {
		when(covidMasterService.getMaster(1)).thenReturn("{\"symptomsMaster\":[]}");

		assertTrue(controller.patientAppMasterData(1).contains("\"statusCode\":200"));
	}

	@Test
	void saveBenCovidDoctorDataReturnsSuccess() throws Exception {
		when(covidMasterService.saveCovidData(anyString())).thenReturn("Data saved successfully");

		assertTrue(controller.saveBenCovidDoctorData("{\"beneficiaryRegID\":12}", "Bearer token")
				.contains("\"statusCode\":200"));
	}

	@Test
	void saveBenCovidDoctorDataReportsAFailure() throws Exception {
		when(covidMasterService.saveCovidData(anyString())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveBenCovidDoctorData("{\"beneficiaryRegID\":12}", "Bearer token")
				.contains("Unable to save data"));
	}
}
