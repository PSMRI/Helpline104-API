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
package com.iemr.helpline104.service.cdss;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.iemr.helpline104.data.cdss.Symptoms;
import com.iemr.helpline104.data.cdss.SymptomsWrapper;
import com.iemr.helpline104.repository.cdss.DBConnect;

/**
 * The decision-support service reads stored algorithms as JSON and accepts new
 * ones as YAML, reporting the first thing it cannot find in the uploaded
 * algorithm. Each rejection message is driven from an algorithm missing exactly
 * one part.
 */
@ExtendWith(MockitoExtension.class)
class CDSSServiceImplTest {

	private static final String ALGORITHM = "{\"Question Set\":{\"Do you have chills?\":[0,1],"
			+ "\"Any rash?\":[2]},\"Diseases\":[{\"Malaria\":{\"Symptoms\":[\"chills\"],"
			+ "\"Information\":[\"spread by mosquitoes\"],\"Do & Donts\":[\"rest\"],"
			+ "\"Self-Care\":[\"fluids\"],\"Action\":[\"refer\"]}},{\"Dengue\":{\"Symptoms\":[\"rash\"],"
			+ "\"Information\":[\"spread by mosquitoes\"],\"Do & Donts\":[\"rest\"],"
			+ "\"Self-Care\":[\"fluids\"],\"Action\":[\"refer\"]}}]}";

	@Mock
	private DBConnect dbConnect;

	private CDSSServiceImpl cdssService;

	@BeforeEach
	void setUp() {
		cdssService = new CDSSServiceImpl();
		cdssService.setDbConnect(dbConnect);
	}

	/** A complete algorithm as it is uploaded: YAML wrapped in a JSON message. */
	private static String upload(String yaml) {
		return "{\"Msg\":\"" + yaml.replace("\"", "\\\"").replace("\n", "\\n") + "\"}";
	}

	private static String yamlAlgorithm(String... lines) {
		return String.join("\n", lines);
	}

	private static String completeAlgorithm() {
		return yamlAlgorithm("Chief Complaint: Fever", "Gender: [Male, Female]", "Age: [1, 99]", "Question Set:",
				"  Do you have chills?: [0, 1]", "Diseases:", "  - Malaria:", "      Symptoms: [chills]",
				"      Information: [spread by mosquitoes]", "      \"Do & Donts\": [rest]",
				"      Self-Care: [fluids]", "      Action: [refer]");
	}

	@Test
	void getSymptomsListsEverySymptom() {
		when(dbConnect.getSymptoms()).thenReturn(Arrays.asList("fever", "cough"));

		assertEquals(Arrays.asList("fever", "cough"), cdssService.getSymptoms());
	}

	@Test
	void getSymptomsForAPatientTitleCasesEveryWord() {
		when(dbConnect.getSymptoms("Male", 30)).thenReturn(Arrays.asList("high fever", "dry-cough", null, ""));

		List<String> symptoms = cdssService.getSymptoms(new SymptomsWrapper("1", "fever", "Male", 30));

		assertEquals(Arrays.asList("High Fever", "Dry-Cough"), symptoms);
	}

	@Test
	void getSymptomsForAPatientReturnsNothingWhenNoneMatch() {
		when(dbConnect.getSymptoms("Male", 30)).thenReturn(Collections.emptyList());

		assertNull(cdssService.getSymptoms(new SymptomsWrapper("1", "fever", "Male", 30)));
	}

	@Test
	void getQuestionsFlagsTheEmergencyQuestions() {
		Symptoms stored = new Symptoms();
		stored.setSymptomId(4);
		stored.setSymptom("Fever");
		stored.setData(ALGORITHM);
		when(dbConnect.getQuestions("Fever", 30, "Male")).thenReturn(stored);

		String questions = cdssService.getQuestions("Fever", 30, "Male");

		assertTrue(questions.contains("\"disease\": \"Fever\""));
		assertTrue(questions.contains("\"isEmergency\": true"));
		assertTrue(questions.contains("\"isEmergency\": false"));
	}

	@Test
	void getQuestionsReportsAnUnknownSymptom() {
		when(dbConnect.getQuestions(anyString(), anyInt(), anyString())).thenReturn(null);

		assertTrue(cdssService.getQuestions("Fever", 30, "Male").contains("No Question Found"));
	}

	@Test
	void getResultCollectsTheDiseasesBehindTheAnsweredQuestion() {
		when(dbConnect.getResultsById(4)).thenReturn(ALGORITHM);

		String result = cdssService.getResult(4, 0);

		assertTrue(result.contains("\"Disease\": \"Malaria\""));
		assertTrue(result.contains("DoDonts"));
		assertTrue(result.contains("SelfCare"));
	}

	@Test
	void saveSymptomStoresACompleteAlgorithm() {
		String response = cdssService.saveSymptom(upload(completeAlgorithm()));

		assertEquals("{\"message\":\"Sucess\"}", response);
		verify(dbConnect).save(any(Symptoms.class));
	}

	@Test
	void saveSymptomRejectsAnUploadThatIsNotJson() {
		assertTrue(cdssService.saveSymptom("not-json").contains("message"));
		verify(dbConnect, never()).save(any(Symptoms.class));
	}

	@Test
	void saveSymptomRejectsAnAlgorithmWithoutAChiefComplaint() {
		String yaml = yamlAlgorithm("Gender: [Male]", "Age: [1, 99]");

		assertTrue(cdssService.saveSymptom(upload(yaml)).contains("message"));
		verify(dbConnect, never()).save(any(Symptoms.class));
	}

	@Test
	void saveSymptomRejectsAGenderThatIsNotAList() {
		String yaml = yamlAlgorithm("Chief Complaint: Fever", "Gender: Male", "Age: [1, 99]");

		assertTrue(cdssService.saveSymptom(upload(yaml)).contains("Expecting a Array"));
	}

	@Test
	void saveSymptomRejectsAnAgeThatIsNotNumeric() {
		String yaml = yamlAlgorithm("Chief Complaint: Fever", "Gender: [Male]", "Age: [young, old]");

		assertTrue(cdssService.saveSymptom(upload(yaml)).contains("Expecting a integer"));
	}

	@Test
	void saveSymptomRejectsAnAlgorithmWithoutAQuestionSet() {
		String yaml = yamlAlgorithm("Chief Complaint: Fever", "Gender: [Male]", "Age: [1, 99]");

		assertTrue(cdssService.saveSymptom(upload(yaml)).contains("Could not find 'Question Set'"));
	}

	@Test
	void saveSymptomRejectsADiseaseWithoutItsSymptoms() {
		String yaml = yamlAlgorithm("Chief Complaint: Fever", "Gender: [Male]", "Age: [1, 99]", "Question Set:",
				"  Do you have chills?: [0, 1]", "Diseases:", "  - Malaria:",
				"      Information: [spread by mosquitoes]");

		assertTrue(cdssService.saveSymptom(upload(yaml)).contains("Could not find 'Symptoms'"));
	}

	@Test
	void saveSymptomReportsAnAlgorithmThatIsAlreadyStored() {
		when(dbConnect.save(any(Symptoms.class))).thenThrow(new DataIntegrityViolationException("duplicate"));

		assertEquals("{\"message\":\"Data Already Exist in Database\"}",
				cdssService.saveSymptom(upload(completeAlgorithm())));
	}

	@Test
	void saveSymptomReportsAYamlItCannotRead() {
		String yaml = yamlAlgorithm("Chief Complaint: Fever", "\tGender: [Male", "  broken: [");

		assertTrue(cdssService.saveSymptom(upload(yaml)).contains("message"));
		verify(dbConnect, never()).save(any(Symptoms.class));
	}
}
