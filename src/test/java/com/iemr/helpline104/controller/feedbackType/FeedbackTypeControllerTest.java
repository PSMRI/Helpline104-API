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
package com.iemr.helpline104.controller.feedbackType;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.List;

import org.assertj.core.util.Lists;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.service.feedbackType.FeedbackTypeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackTypeControllerTest {

	@InjectMocks
	FeedbackTypeController feedbackTypeController;
	
	@Mock
	FeedbackTypeServiceImpl feedbackTypeServiceImpl;
	
	@Test
	public void getNatureOfComplaintTypesTest()
	{
		M_FeedbackType m_feedbackType=new M_FeedbackType();
		m_feedbackType.setFeedbackTypeName("typeName");
		List<M_FeedbackType> m_feedbackTypes=Lists.newArrayList();
		m_feedbackTypes.add(m_feedbackType);
		try {
			doReturn(m_feedbackTypes).when(feedbackTypeServiceImpl).getNatureOfComplaintTypes(Mockito.anyInt(),Mockito.anyShort());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=feedbackTypeController.getNatureOfComplaintTypes(m_feedbackType.toString());
		assertTrue(response.contains("\"feedbackTypeName\":\"typeName\""));
	}
	@Test
	public void getNatureOfComplaintTypesJsonTest()
	{
		M_FeedbackType m_feedbackType=new M_FeedbackType();
		m_feedbackType.setFeedbackTypeName("typeName");
		try {
			doThrow(JSONException.class).when(feedbackTypeServiceImpl).getNatureOfComplaintTypes(Mockito.anyInt(),Mockito.anyShort());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=feedbackTypeController.getNatureOfComplaintTypes(m_feedbackType.toString());
		assertFalse(response.contains("\"feedbackTypeName\":\"typeName\""));
		assertTrue(response.contains("Invalid object conversion"));
	}
	@Test
	public void getNatureOfComplaintTypesExceptionTest()
	{
		M_FeedbackType m_feedbackType=new M_FeedbackType();
		m_feedbackType.setFeedbackTypeName("typeName");
		try {
			doThrow(Exception.class).when(feedbackTypeServiceImpl).getNatureOfComplaintTypes(Mockito.anyInt(),Mockito.anyShort());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=feedbackTypeController.getNatureOfComplaintTypes(m_feedbackType.toString());
		assertFalse(response.contains("\"feedbackTypeName\":\"typeName\""));
		assertTrue(response.contains("Failed with null"));
	}
}
