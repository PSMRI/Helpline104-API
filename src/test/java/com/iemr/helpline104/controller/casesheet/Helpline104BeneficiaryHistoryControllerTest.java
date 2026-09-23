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
package com.iemr.helpline104.controller.casesheet;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.helpline104.data.casesheet.H104BenMedHistory;
import com.iemr.helpline104.service.casesheet.H104BenHistoryService;

/**
 * The case sheet endpoints wrap the service answer in an OutputResponse. A
 * saved case sheet that a counsellor or psychiatrist acted on is stored a
 * second time, to carry the MH request ID minted from its generated key.
 */
@ExtendWith(MockitoExtension.class)
class Helpline104BeneficiaryHistoryControllerTest {

	private static final String REQUEST = "{\"beneficiaryRegID\":12,\"benCallID\":4,\"callID\":\" call-1 \"}";

	@Mock
	private H104BenHistoryService h104BenHistoryService;

	@InjectMocks
	private Helpline104BeneficiaryHistoryController controller;

	private static H104BenMedHistory history(String actionByCO) {
		H104BenMedHistory history = new H104BenMedHistory();
		ReflectionTestUtils.setField(history, "benHistoryID", 9L);
		ReflectionTestUtils.setField(history, "actionByCO", actionByCO);
		return history;
	}

	@Test
	void getBenCaseSheetReturnsSuccess() {
		when(h104BenHistoryService.geSmpleBenHistory(any(), any()))
				.thenReturn(Collections.singletonList(history(null)));

		assertTrue(controller.getBenCaseSheet(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getBenCaseSheetReportsAFailure() {
		when(h104BenHistoryService.geSmpleBenHistory(any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getBenCaseSheet(REQUEST).contains("db down"));
	}

	@Test
	void saveBenCaseSheetStoresAnUnactionedCaseSheetOnce() {
		when(h104BenHistoryService.saveSmpleBenHistory(any(), any())).thenReturn(history(null));

		assertTrue(controller.saveBenCaseSheet(REQUEST).contains("\"statusCode\":200"));
		verify(h104BenHistoryService, times(1)).saveSmpleBenHistory(any(), any());
	}

	@Test
	void saveBenCaseSheetMintsTheRequestIdForAnActionedCaseSheet() {
		when(h104BenHistoryService.saveSmpleBenHistory(any(), any())).thenReturn(history("Counselled"));

		assertTrue(controller.saveBenCaseSheet(REQUEST).contains("\"statusCode\":200"));
		verify(h104BenHistoryService, times(2)).saveSmpleBenHistory(any(), any());
	}

	@Test
	void saveBenCaseSheetReportsAFailure() {
		when(h104BenHistoryService.saveSmpleBenHistory(any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveBenCaseSheet(REQUEST).contains("db down"));
	}

	@Test
	void getPresentCaseSheetReturnsSuccess() {
		when(h104BenHistoryService.getPresentCasesheet(any(), any()))
				.thenReturn(Collections.singletonList(history(null)));

		assertTrue(controller.getPresentCaseSheet(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getPresentCaseSheetReportsAFailure() {
		when(h104BenHistoryService.getPresentCasesheet(any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getPresentCaseSheet(REQUEST).contains("db down"));
	}
}
