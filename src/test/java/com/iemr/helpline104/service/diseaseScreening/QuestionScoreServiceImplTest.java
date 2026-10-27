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

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.repository.diseaseScreening.QuestionScoreRepository;

/**
 * The answers to a screening question are served as an id/question/answer/score
 * projection, skipping rows the projection cannot read.
 */
@ExtendWith(MockitoExtension.class)
class QuestionScoreServiceImplTest {

	@Mock
	private QuestionScoreRepository questionScoreRepository;

	@InjectMocks
	private QuestionScoreServiceImpl questionScoreService;

	@Test
	void fetchAnswersProjectsEveryReadableRow() throws Exception {
		when(questionScoreRepository.fetchAnswers(2))
				.thenReturn(new ArrayList<>(Arrays.asList(null, new Object[0], new Object[] { 5, 2, "Yes", 3 })));

		assertEquals(1, questionScoreService.fetchAnswers(2).size());
	}
}
