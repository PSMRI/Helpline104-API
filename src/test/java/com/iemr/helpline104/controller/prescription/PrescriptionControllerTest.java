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
package com.iemr.helpline104.controller.prescription;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import com.iemr.helpline104.data.prescription.Prescription;
import com.iemr.helpline104.service.prescription.PrescriptionService;

/**
 * The prescription endpoints wrap the service answer in an OutputResponse, page
 * the lists on the page and size the caller asked for, and say so when the
 * service found no prescription at all.
 */
@ExtendWith(MockitoExtension.class)
class PrescriptionControllerTest {

	private static final String REQUEST = "{\"beneficiaryRegID\":12,\"prescriptionID\":9}";
	private static final String PAGED_REQUEST = "{\"beneficiaryRegID\":12,\"page\":2,\"size\":5}";

	@Mock
	private PrescriptionService prescriptionService;

	@InjectMocks
	private PrescriptionController controller;

	@Test
	void savePrescriptionReturnsSuccess() {
		when(prescriptionService.savePrescription(any())).thenReturn(new Prescription());

		assertTrue(controller.savePrescription(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void savePrescriptionReportsAFailure() {
		when(prescriptionService.savePrescription(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.savePrescription(REQUEST).contains("db down"));
	}

	@Test
	void getPrescriptionReturnsSuccess() {
		when(prescriptionService.getPrescription(any(), any()))
				.thenReturn(Collections.singletonList(new Prescription()));

		assertTrue(controller.getPrescription(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getPrescriptionSaysSoWhenNoneWasFound() {
		when(prescriptionService.getPrescription(any(), any())).thenReturn(null);

		assertTrue(controller.getPrescription(REQUEST).contains("prescription not available"));
	}

	@Test
	void getPrescriptionReportsAFailure() {
		when(prescriptionService.getPrescription(any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getPrescription(REQUEST).contains("db down"));
	}

	@Test
	void getPrescriptionListReadsThePageTheCallerAskedFor() {
		when(prescriptionService.getPrescriptionList(any(), any()))
				.thenReturn(Collections.singletonList(new Prescription()));

		assertTrue(controller.getPrescriptionList(PAGED_REQUEST).contains("\"statusCode\":200"));
		verify(prescriptionService).getPrescriptionList(12L, PageRequest.of(1, 5));
	}

	@Test
	void getPrescriptionListFallsBackToTheWholeFirstPage() {
		when(prescriptionService.getPrescriptionList(any(), any()))
				.thenReturn(Collections.singletonList(new Prescription()));

		controller.getPrescriptionList(REQUEST);

		verify(prescriptionService).getPrescriptionList(12L, PageRequest.of(0, 1000));
	}

	@Test
	void getPrescriptionListSaysSoWhenNoneWasFound() {
		when(prescriptionService.getPrescriptionList(any(), any())).thenReturn(null);

		assertTrue(controller.getPrescriptionList(REQUEST).contains("prescription not available"));
	}

	@Test
	void getPrescriptionListReportsAFailure() {
		when(prescriptionService.getPrescriptionList(any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getPrescriptionList(REQUEST).contains("db down"));
	}

	@Test
	void getLatestValidPescriptionReadsThePageTheCallerAskedFor() {
		when(prescriptionService.getLatestValidPescription(any(), any()))
				.thenReturn(Collections.singletonList(new Prescription()));

		assertTrue(controller.getLatestValidPescription(PAGED_REQUEST).contains("\"statusCode\":200"));
		verify(prescriptionService).getLatestValidPescription(12L, PageRequest.of(1, 5));
	}

	@Test
	void getLatestValidPescriptionSaysSoWhenNoneWasFound() {
		when(prescriptionService.getLatestValidPescription(any(), any())).thenReturn(null);

		assertTrue(controller.getLatestValidPescription(REQUEST).contains("prescription not available"));
	}

	@Test
	void getLatestValidPescriptionReportsAFailure() {
		when(prescriptionService.getLatestValidPescription(any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getLatestValidPescription(REQUEST).contains("db down"));
	}
}
