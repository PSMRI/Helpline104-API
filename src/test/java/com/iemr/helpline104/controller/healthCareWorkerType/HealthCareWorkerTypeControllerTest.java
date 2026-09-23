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
package com.iemr.helpline104.controller.healthCareWorkerType;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.healthCareWorkerType.M_HealthCareWorker;
import com.iemr.helpline104.service.healthCareWorkerType.HealthCareWorkerService;

/**
 * The health care worker master is answered as the rendered list inside an
 * OutputResponse.
 */
@ExtendWith(MockitoExtension.class)
class HealthCareWorkerTypeControllerTest {

	@Mock
	private HealthCareWorkerService healthCareWorkerService;

	@InjectMocks
	private HealthCareWorkerTypeController controller;

	@Test
	void getHealthCareWorkerTypesReturnsSuccess() {
		when(healthCareWorkerService.getHealthCareWorkerTypes())
				.thenReturn(Collections.singletonList(new M_HealthCareWorker()));

		assertTrue(controller.getHealthCareWorkerTypes().contains("\"statusCode\":200"));
	}
}
