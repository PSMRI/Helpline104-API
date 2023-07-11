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

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.iemr.helpline104.data.grievance.T_FeedbackRequset;
import com.iemr.helpline104.repository.grievance.FeedbackRequest;

@RunWith(MockitoJUnitRunner.class)

public class FeedbackRequestServiceTest {

	@InjectMocks
	FeedbackRequestService feedbackRequestService;
	
	@Mock
	FeedbackRequest feedbackrequest;
	
	@Test
	public void updateTest()
	{
		T_FeedbackRequset tfeedbackrequest=new T_FeedbackRequset();
		tfeedbackrequest.setFeedbackRequestID(101);
		int i=feedbackRequestService.update(tfeedbackrequest);
		assertTrue(i==101);
		
	}
	@Test
	public void updateTest1()
	{
		T_FeedbackRequset tfeedbackrequest=new T_FeedbackRequset();
		int i=feedbackRequestService.update(tfeedbackrequest);
		assertTrue(i==0);
		
	}
	@Test
	public void getAllFeedbackTest()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		doReturn(list).when(feedbackrequest).getAllFeedbackByID(Mockito.anyInt());
		ArrayList<Object[]> list1=feedbackRequestService.getAllFeedback(Mockito.anyInt());
		assertTrue(list1.isEmpty());
	}
	
	@Test
	public void getdataByIdTest()
	{
		T_FeedbackRequset t_FeedbackRequset=new T_FeedbackRequset();
		t_FeedbackRequset.setFeedbackRequestID(101);
		doReturn(t_FeedbackRequset).when(feedbackrequest).getdataById(Mockito.anyInt());
		T_FeedbackRequset t_FeedbackRequset1=feedbackRequestService.getdataById(Mockito.anyInt());
		assertTrue(t_FeedbackRequset1.getFeedbackRequestID()==101);
	}
	
	@Test
	public void getdataByIdTest1()
	{
		T_FeedbackRequset t_FeedbackRequset1=feedbackRequestService.getdataById(Mockito.anyInt());
		assertTrue(t_FeedbackRequset1==null);
	}
	
	@Test
	public void getdataById1Test()
	{
		T_FeedbackRequset t_FeedbackRequset=new T_FeedbackRequset();
		t_FeedbackRequset.setFeedbackRequestID(101);
		doReturn(t_FeedbackRequset).when(feedbackrequest).getdataById1(Mockito.anyInt());
		T_FeedbackRequset t_FeedbackRequset1=feedbackRequestService.getdataById1(Mockito.anyInt());
		assertTrue(t_FeedbackRequset1.getFeedbackRequestID()==101);
	}
	
	@Test
	public void getdataById1Test1()
	{
		T_FeedbackRequset t_FeedbackRequset1=feedbackRequestService.getdataById1(Mockito.anyInt());
		assertTrue(t_FeedbackRequset1==null);
	}
}
