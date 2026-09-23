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
package com.iemr.helpline104.controller.disease;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.service.disease.DiseaseService;

/**
 * Every disease endpoint hands the raw request to the service and wraps its
 * answer in an OutputResponse, reporting a failure as the error the response
 * carries.
 */
@ExtendWith(MockitoExtension.class)
class DiseaseControllerTest {

	private static final String REQUEST = "{\"diseasesummaryID\":4}";

	@Mock
	private DiseaseService diseaseService;

	@InjectMocks
	private DiseaseController controller;

	@Test
	void saveDiseaseReturnsSuccess() {
		when(diseaseService.saveDisease(anyString())).thenReturn("[]");

		assertTrue(controller.saveDisease(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void saveDiseaseReportsAFailure() {
		when(diseaseService.saveDisease(anyString())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveDisease(REQUEST).contains("db down"));
	}

	@Test
	void deleteDiseaseReturnsSuccess() {
		when(diseaseService.deleteDisease(anyString())).thenReturn("deactivated successfully");

		assertTrue(controller.deleteDisease(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void deleteDiseaseReportsAFailure() {
		when(diseaseService.deleteDisease(anyString())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.deleteDisease(REQUEST).contains("db down"));
	}

	@Test
	void getDiseaseReturnsSuccess() throws Exception {
		when(diseaseService.getDisease(anyString())).thenReturn("{DiseaseList=[], totalPages=0}");

		assertTrue(controller.getDisease(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getDiseaseReportsAFailure() throws Exception {
		when(diseaseService.getDisease(anyString())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getDisease(REQUEST).contains("db down"));
	}

	@Test
	void updateDiseaseReturnsSuccess() throws Exception {
		when(diseaseService.updateDisease(anyString())).thenReturn("Updated successfully");

		assertTrue(controller.updateDisease(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void updateDiseaseReportsAFailure() throws Exception {
		when(diseaseService.updateDisease(anyString())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.updateDisease(REQUEST).contains("db down"));
	}

	@Test
	void getAvailableDiseasesReturnsSuccess() throws Exception {
		when(diseaseService.getAvailableDiseases()).thenReturn("[]");

		assertTrue(controller.getAvailableDiseases().contains("\"statusCode\":200"));
	}

	@Test
	void getAvailableDiseasesReportsAFailure() throws Exception {
		when(diseaseService.getAvailableDiseases()).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getAvailableDiseases().contains("db down"));
	}

	@Test
	void getDiseasesByIDReturnsSuccess() throws Exception {
		when(diseaseService.getDiseasesByID(anyString())).thenReturn("{}");

		assertTrue(controller.getDiseasesByID(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getDiseasesByIDReportsAFailure() throws Exception {
		when(diseaseService.getDiseasesByID(anyString())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getDiseasesByID(REQUEST).contains("db down"));
	}
}
