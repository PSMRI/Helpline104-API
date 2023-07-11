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
package com.iemr.helpline104.service.feedback;

import static org.junit.Assert.assertFalse;
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

import com.iemr.helpline104.data.drugMapping.M_104drugmapping;
import com.iemr.helpline104.data.feedback.FeedbackDetails;
import com.iemr.helpline104.data.feedback.FeedbackRequestDetails;
import com.iemr.helpline104.repository.beneficiarycall.BeneficiaryCallRepository;
import com.iemr.helpline104.repository.beneficiarycall.ServicesHistoryRepository;
import com.iemr.helpline104.repository.feedback.FeedbackRepository;
import com.iemr.helpline104.repository.feedbackType.FeedbackTypeRepository;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackServiceImplTest {

	@InjectMocks
	FeedbackServiceImpl feedbackServiceImpl;
	
	@Mock
	BeneficiaryCallRepository beneficiaryCallRepository;
	
	@Mock
	FeedbackTypeRepository feedbackRepositorty;
	
	@Mock
	FeedbackRepository feedbackRepository;
	
	@Mock
	ServicesHistoryRepository servicesHistoryRepository;
	
	
	@Test
	public void getFeedbackRequestsTest()
	{
		ArrayList<Objects[]> list=Lists.newArrayList();
		Objects[] array=new Objects[12];
		list.add(array);
		doReturn(list).when(feedbackRepository).findByBeneficiaryID(Mockito.anyLong());
		List<FeedbackDetails> feedbackList=feedbackServiceImpl.getFeedbackRequests(Mockito.anyLong());
		assertTrue(feedbackList.toString().contains("\"feedbackTypeID\":null"));
	}
	
	@Test
	public void getFeedbackRequestsTest1()
	{
		List<FeedbackDetails> feedbackList=feedbackServiceImpl.getFeedbackRequests(12);
		assertTrue(feedbackList.isEmpty());
	}
	
	@Test
	public void getFeedbackRequestsTest2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[7];
		Short s=12;
		array[0]=101L;
		array[1]=s;
		array[2]=s;
		array[3]=s;
		array[4]="feedback";
		array[5]="remark";
		array[6]="";
		list.add(array);
		try {
			doReturn(list).when(feedbackRepository).findByBeneficiaryID(Mockito.anyLong());
			List<FeedbackDetails> feedbackList=feedbackServiceImpl.getFeedbackRequests(Mockito.anyLong());
			assertTrue(feedbackList.toString().contains("\"feedbackID\":101"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getFeedbackRequestTest()
	{
		ArrayList<Objects[]> list=Lists.newArrayList();
		Objects[] array=new Objects[12];
		list.add(array);
		doReturn(list).when(feedbackRepository).findByFeedbackID(Mockito.anyLong());
		List<FeedbackDetails> feedbackList=feedbackServiceImpl.getFeedbackRequest(Mockito.anyLong());
		assertTrue(feedbackList.toString().contains("\"feedbackTypeID\":null"));
	}
	@Test
	public void getFeedbackRequestTest1()
	{
		List<FeedbackDetails> feedbackList=feedbackServiceImpl.getFeedbackRequest(Mockito.anyLong());
		assertTrue(feedbackList.isEmpty());
	}
	@Test
	public void getFeedbackRequestTest2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[7];
		Short s=12;
		array[0]=101L;
		array[1]=s;
		array[2]=s;
		array[3]=s;
		array[4]="feedback";
		array[5]="remark";
		array[6]="";
		list.add(array);
		try {
			doReturn(list).when(feedbackRepository).findByFeedbackID(Mockito.anyLong());
			List<FeedbackDetails> feedbackList=feedbackServiceImpl.getFeedbackRequest(Mockito.anyLong());
			assertTrue(feedbackList.toString().contains("\"feedbackID\":101"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void createFeedbackTest()
	{
		List<FeedbackRequestDetails> feedbackRequestList=Lists.newArrayList();
		FeedbackRequestDetails feedbackRequestDetails=new FeedbackRequestDetails();
		feedbackRequestDetails.setFeedbackRequestID(new Long("101"));
		feedbackRequestList.add(feedbackRequestDetails);
		
		FeedbackDetails feedbackDetails=new FeedbackDetails();
		feedbackDetails.setFeedbackRequestDetails(feedbackRequestList);
		
		doReturn(feedbackDetails).when(feedbackRepository).save(feedbackDetails);
		FeedbackDetails feedbackDetail=feedbackServiceImpl.createFeedback(feedbackDetails);
		assertTrue(feedbackDetail.getFeedbackRequestDetails().get(0).getFeedbackRequestID()==101);
	}
	
	@Test
	public void createFeedbackTest1()
	{
		List<FeedbackRequestDetails> feedbackRequestList=Lists.newArrayList();
		
		FeedbackDetails feedbackDetails=new FeedbackDetails();
		feedbackDetails.setFeedbackRequestDetails(feedbackRequestList);
		
		FeedbackDetails feedbackDetail=feedbackServiceImpl.createFeedback(feedbackDetails);
		assertFalse(feedbackDetail!=null);
	}
}
