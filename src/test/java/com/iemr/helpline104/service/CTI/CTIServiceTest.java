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
package com.iemr.helpline104.service.CTI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * The CTI service drives the dialler over its HTTP handler; each call hands
 * back whatever the dialler answered.
 */
class CTIServiceTest {

	private final CTIService ctiService = new CTIService();

	private static MockedConstruction<RestTemplate> answeringDialler() {
		return mockConstruction(RestTemplate.class, (template, context) -> when(
				template.getForEntity(anyString(), eq(String.class))).thenReturn(ResponseEntity.ok("OK")));
	}

	@Test
	void loginAgentAsksTheDiallerToLogTheAgentIn() throws Exception {
		try (MockedConstruction<RestTemplate> dialler = answeringDialler()) {
			assertEquals("\"OK\"", ctiService.loginAgent("agent-7"));
			assertEquals(1, dialler.constructed().size());
		}
	}

	@Test
	void logoutAgentAsksTheDiallerToLogTheAgentOut() throws Exception {
		try (MockedConstruction<RestTemplate> dialler = answeringDialler()) {
			assertEquals("\"OK\"", ctiService.logoutAgent("agent-7"));
		}
	}

	@Test
	void transferCallAsksTheDiallerToMoveTheCall() throws Exception {
		try (MockedConstruction<RestTemplate> dialler = answeringDialler()) {
			assertTrue(ctiService.transferCall("agent-7", "agent-8").contains("OK"));
		}
	}
}
