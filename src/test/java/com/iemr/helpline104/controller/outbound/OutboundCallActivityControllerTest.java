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
package com.iemr.helpline104.controller.outbound;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.helpline104.data.comoOutbound.OutboundCallActivity;
import com.iemr.helpline104.data.comoOutbound.T_104CoMoOutboundCallDetails;
import com.iemr.helpline104.service.outbound.OutboundCallActivityService;
import com.iemr.helpline104.utils.CookieUtil;
import com.iemr.helpline104.utils.JwtUtil;
import com.iemr.helpline104.utils.mapper.InputMapper;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The outbound endpoints wrap the service answer in an OutputResponse. Reading
 * an agent's own calls needs a JWT, from the cookie or the header, and reports
 * a request that carries neither.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OutboundCallActivityControllerTest {

	@Mock
	private OutboundCallActivityService activityService;

	@Mock
	private JwtUtil jwtUtil;

	@Mock
	private CookieUtil cookieUtil;

	@InjectMocks
	private OutboundCallActivityController controller;

	private static ArrayList<OutboundCallActivity> activities() {
		OutboundCallActivity activity = new OutboundCallActivity();
		activity.setActivityID(9L);
		activity.setActivityName("Follow up");
		return new ArrayList<>(Collections.singletonList(activity));
	}

	private OutboundCallActivityController controllerWithMapper() {
		org.springframework.test.util.ReflectionTestUtils.setField(controller, "inputMapper", new InputMapper());
		return controller;
	}

	@Test
	void getActiveActivitiesServesTheProvidersActivities() {
		when(activityService.getActiveActivitiesByProvider(1)).thenReturn(activities());

		assertTrue(controller.getActiveActivities("{\"providerServiceMapID\":1}").contains("Follow up"));
		verify(activityService).getActiveActivitiesByProvider(1);
	}

	@Test
	void getActiveActivitiesServesEveryActivityWithoutAProvider() {
		when(activityService.getActiveActivitiesByProvider(null)).thenReturn(activities());

		assertTrue(controller.getActiveActivities("{}").contains("Follow up"));
	}

	@Test
	void getActiveActivitiesReportsAFailure() {
		when(activityService.getActiveActivitiesByProvider(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getActiveActivities("{}").contains("db down"));
	}

	@Test
	void getAllActiveActivitiesServesTheDeletedOnesToo() {
		when(activityService.getAllActivities()).thenReturn(activities());

		assertTrue(controller.getAllActiveActivities().contains("Follow up"));
	}

	@Test
	void getAllActiveActivitiesReportsAFailure() {
		when(activityService.getAllActivities()).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getAllActiveActivities().contains("db down"));
	}

	@Test
	void saveActivityAnswersWithTheGeneratedId() {
		OutboundCallActivity saved = activities().get(0);
		when(activityService.saveActivity(any())).thenReturn(saved);

		assertTrue(controllerWithMapper().saveActivity("{\"activityName\":\"Follow up\"}")
				.contains("Activity saved successfully with ID: 9"));
	}

	@Test
	void saveActivityReportsAFailure() {
		when(activityService.saveActivity(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controllerWithMapper().saveActivity("{\"activityName\":\"Follow up\"}").contains("db down"));
	}

	@Test
	void updateActivityNameReportsTheRowsItTouched() {
		when(activityService.updateActivityName(9L, "Follow up", "agent")).thenReturn(1);

		assertTrue(controller
				.updateActivityName("{\"activityID\":9,\"activityName\":\"Follow up\",\"modifiedBy\":\"agent\"}")
				.contains("Rows affected: 1"));
	}

	@Test
	void updateActivityNameReportsAFailure() {
		assertTrue(controller.updateActivityName("{}").contains("statusCode"));
	}

	@Test
	void toggleActivityStatusReportsTheRowsItTouched() {
		when(activityService.toggleActivityStatus(9L, true, "agent")).thenReturn(1);

		assertTrue(controller
				.toggleActivityStatus("{\"activityID\":9,\"deleted\":true,\"modifiedBy\":\"agent\"}")
				.contains("Rows affected: 1"));
	}

	@Test
	void toggleActivityStatusReportsAFailure() {
		assertTrue(controller.toggleActivityStatus("{}").contains("statusCode"));
	}

	@Test
	void saveCallDetailsAnswersWithTheStoredCall() {
		T_104CoMoOutboundCallDetails saved = new T_104CoMoOutboundCallDetails();
		saved.setCallStatus("Answered");
		when(activityService.saveCallDetails(any())).thenReturn(saved);

		assertTrue(controllerWithMapper().saveCallDetails("{\"callStatus\":\"Answered\"}")
				.contains("\"statusCode\":200"));
	}

	@Test
	void saveCallDetailsReportsARejectedCallStatus() {
		when(activityService.saveCallDetails(any()))
				.thenThrow(new IllegalArgumentException("Invalid call status"));

		assertTrue(controllerWithMapper().saveCallDetails("{\"callStatus\":\"Busy\"}")
				.contains("Invalid call status"));
	}

	@Test
	void getActiveCallDetailsReadsTheJwtFromTheCookie() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(cookieUtil.getCookieValue(request, "Jwttoken")).thenReturn(Optional.of("jwt"));
		when(jwtUtil.extractUsername("jwt")).thenReturn("agent");
		when(activityService.getActiveCallDetailsByUser("agent")).thenReturn(new ArrayList<>());

		assertTrue(controller.getActiveCallDetails(request).contains("\"statusCode\":200"));
	}

	@Test
	void getActiveCallDetailsFallsBackToTheJwtHeader() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(cookieUtil.getCookieValue(request, "Jwttoken")).thenReturn(Optional.empty());
		when(request.getHeader("JwtToken")).thenReturn("jwt");
		when(jwtUtil.extractUsername("jwt")).thenReturn("agent");
		when(activityService.getActiveCallDetailsByUser("agent")).thenReturn(new ArrayList<>());

		assertTrue(controller.getActiveCallDetails(request).contains("\"statusCode\":200"));
	}

	@Test
	void getActiveCallDetailsReportsARequestWithoutAToken() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(cookieUtil.getCookieValue(request, "Jwttoken")).thenReturn(Optional.empty());

		assertTrue(controller.getActiveCallDetails(request).contains("Authentication token not found"));
	}

	@Test
	void getActiveCallDetailsReportsATokenWithoutAUser() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(cookieUtil.getCookieValue(request, "Jwttoken")).thenReturn(Optional.of("jwt"));
		when(jwtUtil.extractUsername(anyString())).thenReturn("");

		assertTrue(controller.getActiveCallDetails(request).contains("Unable to extract user"));
	}
}
