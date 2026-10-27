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
package com.iemr.helpline104.controller.hihl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.service.hihl.HIHLMasters;

/**
 * The HIHL endpoints wrap the service answer in an OutputResponse, and report a
 * failure as the error the response carries.
 */
@ExtendWith(MockitoExtension.class)
class HIHLControllerTest {

	@Mock
	private HIHLMasters hihlMasters;

	@InjectMocks
	private HIHLController controller;

	@Test
	void getHihlMastersReturnsSuccess() {
		when(hihlMasters.getHihlMasters()).thenReturn("{\"m_104appetite\":[]}");

		assertTrue(controller.getHihlMasters().contains("\"statusCode\":200"));
	}

	@Test
	void getHihlMastersReportsAFailure() {
		when(hihlMasters.getHihlMasters()).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getHihlMasters().contains("db down"));
	}

	@Test
	void saveHihlCasesheetReturnsSuccess() throws Exception {
		when(hihlMasters.saveHihlCasesheet(anyString())).thenReturn("data saved successfully with ID : 9");

		assertTrue(controller.saveHihlCasesheet("{\"beneficiaryRegID\":12}").contains("\"statusCode\":200"));
	}

	@Test
	void saveHihlCasesheetReportsAFailure() throws Exception {
		when(hihlMasters.saveHihlCasesheet(anyString())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveHihlCasesheet("{\"beneficiaryRegID\":12}").contains("db down"));
	}

	@Test
	void getHihlCasesheetHistoryInfoReturnsSuccess() {
		when(hihlMasters.getHihlCasesheetHistoryInfo(12L)).thenReturn("[]");

		assertTrue(controller.getHihlCasesheetHistoryInfo(12L).contains("\"statusCode\":200"));
	}

	@Test
	void getHihlCasesheetHistoryInfoReportsAFailure() {
		when(hihlMasters.getHihlCasesheetHistoryInfo(12L)).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getHihlCasesheetHistoryInfo(12L).contains("db down"));
	}

	@Test
	void getHihlCasesheetDataReturnsSuccess() {
		when(hihlMasters.getHihlCasesheetHistoryInfo(9L)).thenReturn("[]");

		assertTrue(controller.getHihlCasesheetData(9L).contains("\"statusCode\":200"));
	}

	@Test
	void getHihlCasesheetDataReportsAFailure() {
		when(hihlMasters.getHihlCasesheetHistoryInfo(9L)).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getHihlCasesheetData(9L).contains("db down"));
	}
}
