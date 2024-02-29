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
package com.iemr.helpline104.service.grievance;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.grievance.T_Feedbackresponse;
import com.iemr.helpline104.repository.grievance.T_FeedbackresponseRepo;

@ExtendWith(MockitoExtension.class)
public class T_FeedbackresponseServiceTest {

	
	@InjectMocks
	T_FeedbackresponseService t_FeedbackresponseService;
	
	@Mock
	T_FeedbackresponseRepo feedbackresponseRepo;
	
	@Test
	public void updateResponceTest()
	{
		T_Feedbackresponse feedbackresponce=new T_Feedbackresponse();
		feedbackresponce.setFeedbackResponseID(101);
		doReturn(feedbackresponce).when(feedbackresponseRepo).save(feedbackresponce);
		t_FeedbackresponseService.updateResponce(feedbackresponce);
	}
	
	@Test
	public void getdataByIdTest()
	{
		ArrayList<Object[]> resultSet=Lists.newArrayList();
		Object[] array=new Object[25];
		array[0]=12;
		resultSet.add(array);
		doReturn(resultSet).when(feedbackresponseRepo).getdatabyId(Mockito.anyInt());
		ArrayList<Object[]> list=t_FeedbackresponseService.getdataById(Mockito.anyInt());
		Object[] array1=list.get(0);
		assertTrue(array1[0].toString().contains("12"));
	}
	
	@Test
	public void getdataByIdTest1()
	{
		ArrayList<Object[]> list=t_FeedbackresponseService.getdataById(Mockito.anyInt());
		assertFalse(!list.isEmpty());
	}
}
