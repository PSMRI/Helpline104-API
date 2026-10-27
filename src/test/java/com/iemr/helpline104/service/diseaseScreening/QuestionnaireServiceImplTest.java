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
package com.iemr.helpline104.service.diseaseScreening;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.diseaseScreening.M_Questionnaire;
import com.iemr.helpline104.repository.diseaseScreening.QuestionnaireRepository;

/**
 * Screening questions come from the question type when one of the three known
 * types is asked for, and from the provider's own set otherwise.
 */
@ExtendWith(MockitoExtension.class)
class QuestionnaireServiceImplTest {

	@Mock
	private QuestionnaireRepository questionnaireRepository;

	@InjectMocks
	private QuestionnaireServiceImpl questionnaireService;

	private static List<M_Questionnaire> questions() {
		return Collections.singletonList(new M_Questionnaire());
	}

	@Test
	void fetchQuestionsReadsTheQuestionTypeSet() throws Exception {
		when(questionnaireRepository.fetchQuestionsOnQuestionID(2)).thenReturn(questions());

		assertEquals(1, questionnaireService.fetchQuestions(2, 1).size());
	}

	@Test
	void fetchQuestionsFallsBackToTheProvidersSet() throws Exception {
		when(questionnaireRepository.fetchQuestions(1)).thenReturn(questions());

		assertEquals(1, questionnaireService.fetchQuestions(null, 1).size());
	}

	@Test
	void fetchQuestionsFallsBackToTheProvidersSetForAnUnknownQuestionType() throws Exception {
		when(questionnaireRepository.fetchQuestions(1)).thenReturn(questions());

		assertEquals(1, questionnaireService.fetchQuestions(9, 1).size());
	}
}
