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
package com.iemr.helpline104.controller.snomedct;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.snomedct.SCTDescription;
import com.iemr.helpline104.service.snomedct.SnomedService;

/**
 * The single-record endpoint answers with the last match, or says so when the
 * term matched nothing; the list endpoint answers with every match.
 */
@ExtendWith(MockitoExtension.class)
class SnomedControllerTest {

	private static final String REQUEST = "{\"term\":\"fever\"}";

	@Mock
	private SnomedService snomedService;

	private SnomedController controller;

	@BeforeEach
	void setUp() {
		controller = new SnomedController();
		controller.setSnomedService(snomedService);
	}

	@Test
	void getSnomedCTRecordAnswersWithTheMatchedConcept() {
		when(snomedService.findSnomedCTRecordFromTerm("fever"))
				.thenReturn(Arrays.asList(new SCTDescription("386661006", "Fever")));

		assertTrue(controller.getSnomedCTRecord(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getSnomedCTRecordSaysSoWhenTheTermMatchedNothing() {
		when(snomedService.findSnomedCTRecordFromTerm("fever")).thenReturn(Collections.emptyList());

		assertTrue(controller.getSnomedCTRecord(REQUEST).contains("No Records Found"));
	}

	@Test
	void getSnomedCTRecordReportsAFailure() {
		when(snomedService.findSnomedCTRecordFromTerm(anyString())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getSnomedCTRecord(REQUEST).contains("db down"));
	}

	@Test
	void getSnomedCTRecordsAnswersWithEveryMatch() {
		when(snomedService.findSnomedCTRecordFromTerm("fever"))
				.thenReturn(Arrays.asList(new SCTDescription("386661006", "Fever")));

		assertTrue(controller.getSnomedCTRecords(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getSnomedCTRecordsReportsAFailure() {
		when(snomedService.findSnomedCTRecordFromTerm(anyString())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getSnomedCTRecords(REQUEST).contains("db down"));
	}
}
