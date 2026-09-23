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
package com.iemr.helpline104.controller.foodSafetyComplaint;

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

import com.iemr.helpline104.data.foodSafetyCopmlaint.T_FoodSafetyCopmlaint;
import com.iemr.helpline104.service.foodSafetyCopmlaint.FoodSafetyCopmlaintService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The food safety endpoints wrap the service answer in an OutputResponse, and
 * report a failure as the error the response carries.
 */
@ExtendWith(MockitoExtension.class)
class FoodSafetyComplaintControllerTest {

	private static final String REQUEST = "{\"beneficiaryRegID\":12,\"benCallID\":4,"
			+ "\"requestID\":\"FS/2/01012024/9\",\"phoneNo\":\"9999999999\"}";

	@Mock
	private FoodSafetyCopmlaintService foodSafetyCopmlaintService;

	@InjectMocks
	private FoodSafetyComplaintController controller;

	@Test
	void saveFoodComplaintDetailsReturnsSuccess() throws Exception {
		when(foodSafetyCopmlaintService.save(any(), any())).thenReturn(new T_FoodSafetyCopmlaint());

		assertTrue(controller.saveFoodComplaintDetails(REQUEST, mock(HttpServletRequest.class))
				.contains("\"statusCode\":200"));
	}

	@Test
	void saveFoodComplaintDetailsReportsAFailure() throws Exception {
		when(foodSafetyCopmlaintService.save(any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveFoodComplaintDetails(REQUEST, mock(HttpServletRequest.class)).contains("db down"));
	}

	@Test
	void getFoodComplaintDetailsReturnsSuccess() throws Exception {
		when(foodSafetyCopmlaintService.getFoodSafetyComplaints(any(), any(), any(), any()))
				.thenReturn(Collections.singletonList(new T_FoodSafetyCopmlaint()));

		assertTrue(controller.getFoodComplaintDetails(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getFoodComplaintDetailsReportsNoComplaintFound() throws Exception {
		when(foodSafetyCopmlaintService.getFoodSafetyComplaints(any(), any(), any(), any()))
				.thenReturn(Collections.emptyList());

		assertTrue(controller.getFoodComplaintDetails(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getFoodComplaintDetailsReportsAFailure() throws Exception {
		when(foodSafetyCopmlaintService.getFoodSafetyComplaints(any(), any(), any(), any()))
				.thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getFoodComplaintDetails(REQUEST).contains("db down"));
	}
}
