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
package com.iemr.helpline104.controller.cdss;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.cdss.SymptomsWrapper;
import com.iemr.helpline104.service.cdss.CDSSService;

/**
 * The decision-support endpoints wrap the service answer in an OutputResponse.
 * Symptoms and questions both say so when the patient details are not enough
 * to answer.
 */
@ExtendWith(MockitoExtension.class)
class ClinicalDecisionSupportControllerTest {

	@Mock
	private CDSSService cdssService;

	@InjectMocks
	private ClinicalDecisionSupportController controller;

	private static SymptomsWrapper patient(String symptom, String gender, int age) {
		return new SymptomsWrapper("1", symptom, gender, age);
	}

	@Test
	void getSymptomsPostAnswersWithTheMatchedSymptoms() {
		when(cdssService.getSymptoms(any(SymptomsWrapper.class))).thenReturn(Arrays.asList("Fever", "Cough"));

		assertTrue(controller.getSymptomsPost(patient("fever", "Male", 30)).contains("Fever"));
	}

	@Test
	void getSymptomsPostSaysSoWhenNothingMatched() {
		when(cdssService.getSymptoms(any(SymptomsWrapper.class))).thenReturn(Collections.emptyList());

		assertTrue(controller.getSymptomsPost(patient("fever", "Male", 30)).contains("No Symptoms Found"));
	}

	@Test
	void getSymptomsPostReportsAFailure() {
		when(cdssService.getSymptoms(any(SymptomsWrapper.class))).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getSymptomsPost(patient("fever", "Male", 30)).contains("db down"));
	}

	@Test
	void getQuestionAnswersWithTheQuestionSet() {
		when(cdssService.getQuestions("fever", 30, "Male")).thenReturn("{\"Questions\":[]}");

		assertTrue(controller.getQuestion(patient("fever", "Male", 30)).contains("\"statusCode\":200"));
	}

	@Test
	void getQuestionNeedsTheSymptomAndTheGender() {
		controller.getQuestion(patient(null, "Male", 30));

		verify(cdssService, never()).getQuestions(anyString(), anyInt(), anyString());
	}

	@Test
	void getQuestionReportsAFailure() {
		when(cdssService.getQuestions(anyString(), anyInt(), anyString())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getQuestion(patient("fever", "Male", 30)).contains("db down"));
	}

	@Test
	void getResultAnswersWithTheDiseasesBehindTheAnswer() {
		when(cdssService.getResult(4, 0)).thenReturn("[]");

		assertTrue(controller.getResult("{\"complaintId\":4,\"selected\":0}").contains("\"statusCode\":200"));
	}

	@Test
	void getResultReportsARequestItCannotRead() {
		assertTrue(controller.getResult("{}").contains("statusCode"));
	}

	@Test
	void saveSymptomAnswersWithTheStoreResult() {
		when(cdssService.saveSymptom(anyString())).thenReturn("{\"message\":\"Sucess\"}");

		assertTrue(controller.saveSymptom("{\"Msg\":\"Chief Complaint: Fever\"}").contains("\"statusCode\":200"));
	}

	@Test
	void saveSymptomReportsAFailure() {
		when(cdssService.saveSymptom(anyString())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveSymptom("{\"Msg\":\"\"}").contains("db down"));
	}
}
