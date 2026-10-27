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
package com.iemr.helpline104.controller.scheme;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.scheme.T_Schemeservice;
import com.iemr.helpline104.service.scheme.SchemeService;

/**
 * The scheme endpoints wrap the service answer in an OutputResponse, and report
 * a failure as the error the response carries.
 */
@ExtendWith(MockitoExtension.class)
class SchemeControllerTest {

	private static final String REQUEST = "{\"beneficiaryRegID\":12,\"benCallID\":4}";

	@Mock
	private SchemeService schemeService;

	@InjectMocks
	private SchemeController controller;

	@Test
	void saveSchemeSearchHistoryReturnsSuccess() {
		when(schemeService.saveSchemeSearchHistory(any())).thenReturn("[]");

		assertTrue(controller.saveSchemeSearchHistory("[" + REQUEST + "]").contains("\"statusCode\":200"));
	}

	@Test
	void saveSchemeSearchHistoryReportsAFailure() {
		when(schemeService.saveSchemeSearchHistory(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveSchemeSearchHistory("[" + REQUEST + "]").contains("db down"));
	}

	@Test
	void getBenSchemeHistoryReturnsSuccess() {
		when(schemeService.getSchemeSearchHistory(any(), any()))
				.thenReturn(Collections.singletonList(new T_Schemeservice()));

		assertTrue(controller.getBenSchemeHistory(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getBenSchemeHistoryReportsAFailure() {
		when(schemeService.getSchemeSearchHistory(any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getBenSchemeHistory(REQUEST).contains("db down"));
	}
}
