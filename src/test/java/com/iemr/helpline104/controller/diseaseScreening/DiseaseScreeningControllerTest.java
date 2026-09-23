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
package com.iemr.helpline104.controller.diseaseScreening;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.diseaseScreening.M_Questionnaire;
import com.iemr.helpline104.data.diseaseScreening.M_questionairValues;
import com.iemr.helpline104.service.diseaseScreening.QuestionScoreService;
import com.iemr.helpline104.service.diseaseScreening.QuestionnaireService;

/**
 * The screening endpoints wrap the service answer in an OutputResponse, and
 * report a failure as the error the response carries.
 */
@ExtendWith(MockitoExtension.class)
class DiseaseScreeningControllerTest {

	private static final String QUESTION_REQUEST = "{\"questionTypeID\":2,\"providerServiceMapID\":1}";
	private static final String ANSWER_REQUEST = "{\"questionID\":5}";

	@Mock
	private QuestionnaireService questionnaireService;

	@Mock
	private QuestionScoreService questionScoreService;

	@InjectMocks
	private DiseaseScreeningController controller;

	@Test
	void fetchQuestionsReturnsSuccess() throws Exception {
		when(questionnaireService.fetchQuestions(any(), any()))
				.thenReturn(Collections.singletonList(new M_Questionnaire()));

		assertTrue(controller.fetchQuestions(QUESTION_REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void fetchQuestionsReportsNoQuestionsFound() throws Exception {
		when(questionnaireService.fetchQuestions(any(), any())).thenReturn(Collections.emptyList());

		assertTrue(controller.fetchQuestions(QUESTION_REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void fetchQuestionsReportsAFailure() throws Exception {
		when(questionnaireService.fetchQuestions(any(), any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.fetchQuestions(QUESTION_REQUEST).contains("db down"));
	}

	@Test
	void fetchAnswersReturnsSuccess() throws Exception {
		when(questionScoreService.fetchAnswers(any()))
				.thenReturn(Collections.singletonList(new M_questionairValues()));

		assertTrue(controller.fetchAnswers(ANSWER_REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void fetchAnswersReportsAFailure() throws Exception {
		when(questionScoreService.fetchAnswers(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.fetchAnswers(ANSWER_REQUEST).contains("db down"));
	}
}
