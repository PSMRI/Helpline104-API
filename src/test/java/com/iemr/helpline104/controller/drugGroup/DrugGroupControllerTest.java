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
package com.iemr.helpline104.controller.drugGroup;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.drugGroup.M_DrugGroup;
import com.iemr.helpline104.data.drugMapping.M_104drugmapping;
import com.iemr.helpline104.service.drugGroup.DrugGroupServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DrugGroupControllerTest {

	@InjectMocks
	DrugGroupController drugGroupController;
	
	@Mock
	DrugGroupServiceImpl drugGroupServiceImpl;
	
	@Test
	public void getDrugGroupsTest()
	{
		M_DrugGroup m_DrugGroup=new M_DrugGroup();
		m_DrugGroup.setDrugGroup("drugGroupName");
		List<M_DrugGroup> drugList=Lists.newArrayList();
		drugList.add(m_DrugGroup);
		try {
			doReturn(drugList).when(drugGroupServiceImpl).getDrugGroups(Mockito.anyInt());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=drugGroupController.getDrugGroups(m_DrugGroup.toString());
		assertTrue(response.contains("\"drugGroup\":\"drugGroupName\""));
		
	}
	@Test
	public void getDrugGroupsExceptionTest()
	{
		M_DrugGroup m_DrugGroup=new M_DrugGroup();
		m_DrugGroup.setDrugGroup("drugGroupName");
		try {
			doThrow(Exception.class).when(drugGroupServiceImpl).getDrugGroups(Mockito.anyInt());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=drugGroupController.getDrugGroups(m_DrugGroup.toString());
		assertFalse(response.contains("\"drugGroup\":\"drugGroupName\""));
		
	}
	@Test
	public void getDrugListTest()
	{
		M_104drugmapping m_104drugmapping=new M_104drugmapping();
		m_104drugmapping.setDrugGroupID(101);
		List<M_104drugmapping> drugList=Lists.newArrayList();
		drugList.add(m_104drugmapping);
		try {
			doReturn(drugList).when(drugGroupServiceImpl).getDrugList(Mockito.anyInt(),Mockito.anyInt());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=drugGroupController.getDrugList(m_104drugmapping.toString());
		assertTrue(response.contains("\"drugGroupID\":101"));
		
	}
	@Test
	public void getDrugListExceptionTest()
	{
		M_104drugmapping m_104drugmapping=new M_104drugmapping();
		m_104drugmapping.setDrugGroupID(101);
		try {
			doThrow(Exception.class).when(drugGroupServiceImpl).getDrugList(Mockito.anyInt(),Mockito.anyInt());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=drugGroupController.getDrugList(m_104drugmapping.toString());
		assertFalse(response.contains("\"drugGroupID\":101"));
		
	}
/*	@Test
	public void getDrugFrequencyTest()
	{

		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[15];
		array[0]=101;
		array[1]=102;
		list.add(array);
		try {
			doReturn(list).when(drugGroupServiceImpl).getDrugFrequency();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=drugGroupController.getDrugFrequency();
		assertTrue(response.contains("\"status\":\"Success\""));	
	}
	@Test
	public void getDrugFrequencyTest1()
	{

		ArrayList<Object[]> list=Lists.newArrayList();
		try {
			doReturn(list).when(drugGroupServiceImpl).getDrugFrequency();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=drugGroupController.getDrugFrequency();
		assertTrue(response.contains("\"status\":\"Success\""));	
	}*/
}
