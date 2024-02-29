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



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
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
import com.iemr.helpline104.repository.callqamapping.CallqamappingRepository;

@ExtendWith(MockitoExtension.class)
public class CallqamappingServiceImplTest {

	@InjectMocks
	CallqamappingServiceImpl callqamappingServiceImpl;
	
	@Mock
	CallqamappingRepository callqamappingRepository;
	
	@Mock
	CallqaMappings callqaMappings;
	
	@Test
	public void saveTest()
	{
		List<M_104callqamapping> list=Lists.newArrayList();
		M_104callqamapping mapping=new M_104callqamapping();
		mapping.setBeneficiaryRegID(new Long("101"));
		list.add(mapping);
		
		M_104callqamapping mapping1=new M_104callqamapping();
		mapping1.setBeneficiaryRegID(new Long("101"));
		
		CallqaMappings callqaMappings1 =new CallqaMappings();
		callqaMappings1.setM_104callqamapping(list);
		doReturn(list).when(callqaMappings).getM_104callqamapping();
		doReturn(mapping1).when(callqamappingRepository).save(mapping);
		try {
			List<M_104callqamapping> list1=callqamappingServiceImpl.save(callqaMappings1);
			assertTrue(list1.toString().contains("\"beneficiaryRegID\":101"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void saveTest1()
	{
		List<M_104callqamapping> list=Lists.newArrayList();
		M_104callqamapping mapping=new M_104callqamapping();
		mapping.setBeneficiaryRegID(new Long("101"));
		list.add(mapping);
		
		CallqaMappings callqaMappings1 =new CallqaMappings();
		callqaMappings1.setM_104callqamapping(list);
		doReturn(list).when(callqaMappings).getM_104callqamapping();
		
		try {
			List<M_104callqamapping> list1=callqamappingServiceImpl.save(callqaMappings1);
			assertFalse(list1.toString().contains("\"beneficiaryRegID\":101"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void saveTest2()
	{
		List<M_104callqamapping> list=Lists.newArrayList();
		M_104callqamapping mapping=new M_104callqamapping();
		mapping.setBeneficiaryRegID(new Long("101"));
		
		CallqaMappings callqaMappings1 =new CallqaMappings();
		callqaMappings1.setM_104callqamapping(list);
		try {
			List<M_104callqamapping> list1=callqamappingServiceImpl.save(callqaMappings1);
			assertFalse(list1.toString().contains("\"beneficiaryRegID\":101"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getCDIQuestionScoresTest()
	{
		try {
			M_104callqamapping m_104callqamapping=new M_104callqamapping();
			List<M_104callqamapping> callqamappings=callqamappingServiceImpl.getCDIQuestionScores(m_104callqamapping);
			assertTrue(callqamappings.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getCDIQuestionScoresTest1()
	{
		M_104callqamapping m_104callqamapping=new M_104callqamapping();
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[5];
		array[0]=101L;
		array[1]="questionScore";
		array[2]=102;
		array[3]="";
		array[4]="";
		
		list.add(array);
		try {
			doReturn(list).when(callqamappingRepository).getCDIQuestionScores(Mockito.anyLong(),Mockito.anyLong());
			List<M_104callqamapping> m_Religion= callqamappingServiceImpl.getCDIQuestionScores(m_104callqamapping);
			assertTrue(m_Religion.toString().contains("\"answer\":\"questionScore\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
