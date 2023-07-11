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

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.iemr.helpline104.data.diseaseScreening.M_questionairValues;
import com.iemr.helpline104.data.userbeneficiarydata.M_Title;
import com.iemr.helpline104.repository.diseaseScreening.QuestionScoreRepository;

@RunWith(MockitoJUnitRunner.class)
public class QuestionScoreServiceImplTest {

	@InjectMocks
	QuestionScoreServiceImpl questionScoreServiceImpl;
	
	@Mock
	QuestionScoreRepository questionScoreRepository;
	
	@Test
	public void fetchQuestions()
	{
		try {
			List<M_questionairValues> questionList =questionScoreServiceImpl.fetchAnswers(Mockito.anyInt());
			assertTrue(questionList.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void fetchQuestions1()
	{
		List<Objects[]> directoryServices=Lists.newArrayList();
		Objects[] array=new Objects[5];
		directoryServices.add(array);
		doReturn(directoryServices).when(questionScoreRepository).fetchAnswers(Mockito.anyInt());
		
		try {
			List<M_questionairValues> questionList = questionScoreServiceImpl.fetchAnswers(Mockito.anyInt());
			assertTrue(questionList.get(0).toString().equals("{}"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void fetchQuestions2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[4];
		array[0]=101;
		array[1]=101;
		array[2]="correctAnswer";
		array[3]=101;
		list.add(array);
		try {
			doReturn(list).when(questionScoreRepository).fetchAnswers(Mockito.anyInt());
			List<M_questionairValues> m_104QuestionScore= questionScoreServiceImpl.fetchAnswers(Mockito.anyInt());
			assertTrue(m_104QuestionScore.toString().contains("\"answer\":\"correctAnswer\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
