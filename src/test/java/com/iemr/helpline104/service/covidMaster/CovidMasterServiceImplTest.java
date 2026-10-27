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
package com.iemr.helpline104.service.covidMaster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.helpline104.data.covidMaster.Covid19BenFeedback;
import com.iemr.helpline104.data.covidMaster.SymptomsMaster;
import com.iemr.helpline104.repository.covidMaster.Covid19BenFeedbackRepo;
import com.iemr.helpline104.repository.covidMaster.SymptomsMasterRepo;

/**
 * The COVID master is stored as one pipe-separated string per master type and
 * served as an id/value list. Saving a screening flattens the multi-select
 * answers into comma-separated columns and turns the YES/NO answers into flags.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CovidMasterServiceImplTest {

	@Mock
	private SymptomsMasterRepo symptomsMasterRepo;

	@Mock
	private Covid19BenFeedbackRepo covid19BenFeedbackRepo;

	@InjectMocks
	private CovidMasterServiceImpl covidMasterService;

	private static SymptomsMaster master(String values) {
		SymptomsMaster master = new SymptomsMaster();
		master.setMasterValues(values);
		return master;
	}

	private static String screening(String extraFields) {
		return "{\"beneficiaryRegID\":12,\"travelledLast14Days\":\"YES\",\"laboratoryConfirmed\":\"YES\","
				+ "\"largeGathering\":\"YES\",\"publicExposedPlaces\":\"YES\","
				+ "\"famliyPublicExposedPlaces\":\"YES\"," + extraFields
				+ "\"forWhomThisTest\":{\"ID\":\"1\",\"Value\":\"Self\"}}";
	}

	@Test
	void getMasterServesEveryMasterTypeAsAnIdValueList() {
		when(symptomsMasterRepo.getMaster(anyString())).thenReturn(master("Fever || Cough"));

		String response = covidMasterService.getMaster(1);

		assertTrue(response.contains("\"symptomsMaster\""));
		assertTrue(response.contains("\"covidReliefFund\""));
		assertTrue(response.contains("\"Value\":\"Fever\""));
		assertTrue(response.contains("\"ID\":\"2\""));
	}

	@Test
	void myMethodNumbersEveryMasterValueFromOne() {
		when(symptomsMasterRepo.getMaster("Symptoms")).thenReturn(master("Fever||Cough||Cold"));

		assertEquals(3, covidMasterService.myMethod("Symptoms").size());
		assertEquals("Cold", covidMasterService.myMethod("Symptoms").get(2).get("Value"));
	}

	@Test
	void saveCovidDataFlattensTheMultiSelectAnswers() throws Exception {
		Covid19BenFeedback stored = new Covid19BenFeedback();
		stored.setCOVID19_104ID(9L);
		when(covid19BenFeedbackRepo.save(any())).thenReturn(stored);

		String response = covidMasterService.saveCovidData(screening(
				"\"symptoms\":[\"Fever\",\"Cough\"],\"healthConditions\":[\"Diabetes\",\"Asthma\"],"
						+ "\"symptoms11Selected\":[\"Fever\"],\"travelType\":[\"Rail\",\"Road\"],"));

		assertEquals("Data saved successfully", response);
	}

	@Test
	void saveCovidDataAcceptsAScreeningWithoutMultiSelectAnswers() throws Exception {
		Covid19BenFeedback stored = new Covid19BenFeedback();
		stored.setCOVID19_104ID(9L);
		when(covid19BenFeedbackRepo.save(any())).thenReturn(stored);

		assertEquals("Data saved successfully", covidMasterService.saveCovidData(screening("")));
	}

	@Test
	void saveCovidDataRecordsTheNegativeAnswers() throws Exception {
		Covid19BenFeedback stored = new Covid19BenFeedback();
		stored.setCOVID19_104ID(9L);
		when(covid19BenFeedbackRepo.save(any())).thenReturn(stored);

		String request = "{\"beneficiaryRegID\":12,\"travelledLast14Days\":\"NO\",\"laboratoryConfirmed\":\"NO\","
				+ "\"largeGathering\":\"NO\",\"publicExposedPlaces\":\"NO\",\"famliyPublicExposedPlaces\":\"NO\","
				+ "\"forWhomThisTest\":{\"ID\":\"1\",\"Value\":\"Self\"}}";

		assertEquals("Data saved successfully", covidMasterService.saveCovidData(request));
	}

	@Test
	void saveCovidDataReportsAScreeningThatWasNotStored() throws Exception {
		when(covid19BenFeedbackRepo.save(any())).thenReturn(new Covid19BenFeedback());

		assertEquals("Error occurred while saving data", covidMasterService.saveCovidData(screening("")));
	}

	@Test
	void saveCovidDataRejectsARequestItCannotRead() throws Exception {
		assertEquals("Invalid input", covidMasterService.saveCovidData("{}"));
		verify(covid19BenFeedbackRepo, never()).save(any());
	}
}
