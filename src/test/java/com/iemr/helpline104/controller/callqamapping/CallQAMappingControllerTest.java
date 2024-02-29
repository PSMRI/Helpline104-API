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
package com.iemr.helpline104.controller.callqamapping;

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

import com.iemr.helpline104.data.callqamapping.CallqaMappings;
import com.iemr.helpline104.data.callqamapping.M_104callqamapping;
import com.iemr.helpline104.service.callqamapping.CallqamappingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CallQAMappingControllerTest {

	
	@InjectMocks
	CallQAMappingController callqamappingController;
	
	@Mock
	CallqamappingServiceImpl callqamappingServiceImpl;
	
	@Test
	public void saveCallqamappingTest()
	{
		CallqaMappings  callqaMappings=new CallqaMappings();
		
		M_104callqamapping m_104callqamapping=new M_104callqamapping();
		m_104callqamapping.setBeneficiaryRegID(new Long("101"));
		
		List<M_104callqamapping> mapList=Lists.newArrayList();
		mapList.add(m_104callqamapping);
		
		callqaMappings.setM_104callqamapping(mapList);
		
		try {
			doReturn(mapList).when(callqamappingServiceImpl).save(Mockito.any());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=callqamappingController.saveCallqamapping(callqaMappings.toString());
		assertTrue(response.contains("\"beneficiaryRegID\":101"));	
		
	}
	
	@Test
	public void saveCallqamappingExceptionTest()
	{
		CallqaMappings  callqaMappings=new CallqaMappings();
		
		try {
			doThrow(Exception.class).when(callqamappingServiceImpl).save(Mockito.any());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=callqamappingController.saveCallqamapping(callqaMappings.toString());
		assertFalse(response.contains("\"beneficiaryRegID\":101"));	}

	@Test
	public void getCDIqamappingTest()
	{
		M_104callqamapping m_104callqamapping=new M_104callqamapping();
		m_104callqamapping.setBeneficiaryRegID(new Long("101"));

		List<M_104callqamapping> list=Lists.newArrayList();
		list.add(m_104callqamapping);
		
		try {
			doReturn(list).when(callqamappingServiceImpl).getCDIQuestionScores(Mockito.any());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=callqamappingController.getCDIqamapping(m_104callqamapping.toString());
		assertTrue(response.contains("\"beneficiaryRegID\":101"));	
		
	}
	@Test
	public void getCDIqamappingExceptionTest()
	{
		M_104callqamapping m_104callqamapping=new M_104callqamapping();
		m_104callqamapping.setBeneficiaryRegID(new Long("101"));
		try {
			doThrow(Exception.class).when(callqamappingServiceImpl).getCDIQuestionScores(Mockito.any());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=callqamappingController.getCDIqamapping(m_104callqamapping.toString());
		assertFalse(response.contains("\"beneficiaryRegID\":101"));	
		
	}

	}


	
