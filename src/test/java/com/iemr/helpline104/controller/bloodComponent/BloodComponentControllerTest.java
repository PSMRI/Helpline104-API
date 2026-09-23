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
package com.iemr.helpline104.controller.bloodComponent;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.bloodComponent.M_Component;
import com.iemr.helpline104.service.bloodComponent.BloodComponentService;

/**
 * The blood component endpoints wrap the service answer in an OutputResponse,
 * and report a failure as the error the response carries.
 */
@ExtendWith(MockitoExtension.class)
class BloodComponentControllerTest {

	private static final String REQUEST = "{\"componentID\":3}";

	@Mock
	private BloodComponentService bloodComponentService;

	@InjectMocks
	private BloodComponentController controller;

	@Test
	void saveBloodComponentDetailsReturnsSuccess() {
		when(bloodComponentService.save(any())).thenReturn(new M_Component());

		assertTrue(controller.saveBloodComponentDetails(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void saveBloodComponentDetailsReportsAFailure() {
		when(bloodComponentService.save(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveBloodComponentDetails(REQUEST).contains("db down"));
	}

	@Test
	void getBloodComponentDetailsReturnsSuccess() throws Exception {
		when(bloodComponentService.getBloodComponents(3))
				.thenReturn(Collections.singletonList(new M_Component()));

		assertTrue(controller.getBloodComponentDetails(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getBloodComponentDetailsReportsNoComponentFound() throws Exception {
		when(bloodComponentService.getBloodComponents(3)).thenReturn(Collections.emptyList());

		assertTrue(controller.getBloodComponentDetails(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getBloodComponentDetailsReportsAFailure() throws Exception {
		when(bloodComponentService.getBloodComponents(3)).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getBloodComponentDetails(REQUEST).contains("db down"));
	}
}
