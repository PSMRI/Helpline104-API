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
package com.iemr.helpline104.service.feedbackType;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.repository.feedbackType.FeedbackTypeRepository;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackTypeServiceImplTest {

	@InjectMocks
	FeedbackTypeServiceImpl feedbackServiceImpl;
	
	@Mock
	FeedbackTypeRepository feedbackTypeRepository;
	
	
	@Test
	public void getNatureOfComplaintTypesTest()
	{
		try {
			List<M_FeedbackType> natureOfComplaintsList=feedbackServiceImpl.getNatureOfComplaintTypes(Mockito.anyInt(), Mockito.anyShort());
			assertTrue(natureOfComplaintsList.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getNatureOfComplaintTypesTest1()
	{
		try {
			
			List<Objects[]> list=Lists.newArrayList();
			Objects[] array=new Objects[9];
			list.add(array);
			doReturn(list).when(feedbackTypeRepository).getNatureOfComplaintTypes(Mockito.anyInt(), Mockito.anyShort());
			List<M_FeedbackType> natureOfComplaintsList=feedbackServiceImpl.getNatureOfComplaintTypes(Mockito.anyInt(), Mockito.anyShort());
			assertFalse(natureOfComplaintsList.toString().contains("feedbackTypeID"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getNatureOfComplaintTypesTest2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[7];
		Short s=12;
		array[0]=s;
		array[1]="feedbackTypeNameValue";
		array[2]="";
		array[3]=102;
		array[4]=103;
		array[5]="";
		array[6]="";
		list.add(array);
		try {
			doReturn(list).when(feedbackTypeRepository).getNatureOfComplaintTypes(Mockito.anyInt(), Mockito.anyShort());
			List<M_FeedbackType> natureOfComplaintsList=feedbackServiceImpl.getNatureOfComplaintTypes(Mockito.anyInt(), Mockito.anyShort());
			assertTrue(natureOfComplaintsList.toString().contains("\"feedbackTypeName\":\"feedbackTypeNameValue\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
