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
package com.iemr.helpline104.controller.feedback;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.feedback.FeedbackDetails;
import com.iemr.helpline104.service.feedback.FeedbackService;
import com.iemr.helpline104.service.feedback.FeedbackServiceImpl;
@ExtendWith(MockitoExtension.class)
public class FeedbackControllerTest {

	@InjectMocks
	FeedbackController FeedbackController;
	
	@Mock
	FeedbackServiceImpl feedbackServiceImpl;
	
	@Mock
	FeedbackService feedbackService;
	
	@Test
	public void feedbackReuestTest()
	{
		FeedbackDetails feedbackDetails=new FeedbackDetails();
		feedbackDetails.setFeedbackID(new Long("101"));
		feedbackDetails.setBeneficiaryRegID(new Long("102"));
		List<FeedbackDetails> feedbackList=Lists.newArrayList();
		feedbackList.add(feedbackDetails);
		doReturn(feedbackList).when(feedbackService).getFeedbackRequests(Mockito.anyLong());
		String response=FeedbackController.feedbackReuest(feedbackDetails.toString());
		assertTrue(response.contains("\"feedbackID\":101"));
	}
	@Test
	public void feedbackReuestExceptionTest()
	{
		FeedbackDetails feedbackDetails=new FeedbackDetails();
		feedbackDetails.setFeedbackID(new Long("101"));
		feedbackDetails.setBeneficiaryRegID(new Long("102"));
		doThrow(Exception.class).when(feedbackService).getFeedbackRequests(Mockito.anyLong());
		String response=FeedbackController.feedbackReuest(feedbackDetails.toString());
		assertFalse(response.contains("\"feedbackID\":101"));
	}
	/*@SuppressWarnings("deprecation")
	@Test
	public void feedbackCreateTest()
	{
		FeedbackDetails feedbackDetails=new FeedbackDetails();
		//feedbackDetails.setFeedbackID(new Long("101"));
		feedbackDetails.setBeneficiaryRegID(new Long("102"));
		doReturn(feedbackDetails).when(feedbackService).createFeedback(Mockito.anyObject());
		String response=FeedbackController.feedbackCreate("{\"feedbackID\":101, \"beneficiaryRegID\":102},{\"instituteType\":{},{\"feedbackNatureDetail\":{}");
		assertTrue(response.contains("\"feedbackID\":101"));
	}*/
	@Test
	public void feedbackCreateExceptionTest()
	{
		FeedbackDetails feedbackDetails=new FeedbackDetails();
		feedbackDetails.setFeedbackID(new Long("101"));
		feedbackDetails.setBeneficiaryRegID(new Long("102"));
		doThrow(Exception.class).when(feedbackService).createFeedback(Mockito.any());
		//String response=FeedbackController.feedbackCreate(feedbackDetails.toString());
		//assertFalse(response.contains("\"feedbackID\":101"));
	}
	@Test
	public void getFeedbackByPostTest()
	{
		FeedbackDetails feedbackDetails=new FeedbackDetails();
		feedbackDetails.setFeedbackID(new Long("101"));
		feedbackDetails.setBeneficiaryRegID(new Long("102"));
		List<FeedbackDetails> feedbackList=Lists.newArrayList();
		feedbackList.add(feedbackDetails);
		doReturn(feedbackList).when(feedbackService).getFeedbackRequests(Mockito.anyInt());
		String response=FeedbackController.getFeedbackByPost(Mockito.anyInt());
		assertTrue(response.contains("\"feedbackID\":101"));
	}
	@Test
	public void getFeedbackByPostExceptionTest()
	{
		doThrow(Exception.class).when(feedbackService).getFeedbackRequests(Mockito.anyInt());
		String response=FeedbackController.getFeedbackByPost(Mockito.anyInt());
		assertFalse(response.contains("\"feedbackID\":101"));
	}
	@Test
	public void updateFeedbackTest() throws Exception
	{
		FeedbackDetails feedbackDetails=new FeedbackDetails();
		feedbackDetails.setFeedbackID(new Long("101"));
		feedbackDetails.setBeneficiaryRegID(new Long("102"));
		doReturn(feedbackDetails.toString()).when(feedbackService).updateFeedback(Mockito.any());
		String response=FeedbackController.updateFeedback(feedbackDetails.toString());
		assertTrue(response.contains("\"feedbackID\":101"));
	}
	@Test
	public void updateFeedbackExceptionTest() throws Exception
	{
		FeedbackDetails feedbackDetails=new FeedbackDetails();
		feedbackDetails.setFeedbackID(new Long("101"));
		feedbackDetails.setBeneficiaryRegID(new Long("102"));
		doThrow(Exception.class).when(feedbackService).updateFeedback(Mockito.any());
		String response=FeedbackController.updateFeedback(feedbackDetails.toString());
		assertFalse(response.contains("\"feedbackID\":101"));
	}

}
