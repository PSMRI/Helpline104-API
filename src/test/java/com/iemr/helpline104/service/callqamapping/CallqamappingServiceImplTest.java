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
package com.iemr.helpline104.service.callqamapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.helpline104.data.callqamapping.CallqaMappings;
import com.iemr.helpline104.data.callqamapping.M_104callqamapping;
import com.iemr.helpline104.repository.callqamapping.CallqamappingRepository;

/**
 * CDI answers are stored one row per question and read back as an eight-column
 * projection of the question, the answer and the score behind it.
 */
@ExtendWith(MockitoExtension.class)
class CallqamappingServiceImplTest {

	@Mock
	private CallqamappingRepository callqamappingRepository;

	@InjectMocks
	private CallqamappingServiceImpl callqamappingService;

	@Test
	void saveStoresEveryAnswerInTheRequest() throws Exception {
		M_104callqamapping answer = new M_104callqamapping();
		CallqaMappings request = new CallqaMappings();
		request.setM_104callqamapping(Collections.singletonList(answer));
		when(callqamappingRepository.save(answer)).thenReturn(answer);

		assertEquals(1, callqamappingService.save(request).size());
	}

	@Test
	void getCDIQuestionScoresProjectsEveryRow() throws Exception {
		M_104callqamapping request = new M_104callqamapping();
		ReflectionTestUtils.setField(request, "beneficiaryRegID", 12L);
		ReflectionTestUtils.setField(request, "benCallID", 4L);
		when(callqamappingRepository.getCDIQuestionScores(12L, 4L)).thenReturn(Collections.singletonList(
				new Object[] { 9L, "CDI", 2, "Do you feel low?", "Yes", "3", "agent", "note" }));

		assertEquals(1, callqamappingService.getCDIQuestionScores(request).size());
	}
}
