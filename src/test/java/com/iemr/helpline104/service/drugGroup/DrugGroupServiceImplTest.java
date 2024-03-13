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
package com.iemr.helpline104.service.drugGroup;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.drugGroup.M_DrugGroup;
import com.iemr.helpline104.data.drugMapping.M_104drugmapping;
import com.iemr.helpline104.repository.drugGroup.DrugFrequencyRepository;
import com.iemr.helpline104.repository.drugGroup.DrugGroupRepository; 


@ExtendWith(MockitoExtension.class)
public class DrugGroupServiceImplTest {

	@InjectMocks
	DrugGroupServiceImpl drugGroupServiceImpl;
	
	@Mock
	DrugGroupRepository drugGroupRepository;
	
	@Mock
	DrugFrequencyRepository drugFrequencyRepository;
	
	@Test
	public void getDrugGroupsTest()
	{
		try {
			List<M_DrugGroup> list=drugGroupServiceImpl.getDrugGroups(Mockito.anyInt());
			assertTrue(list.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getDrugGroupsTest1()
	{
		List<Object[]> directoryServices=Lists.newArrayList();
		Object[] array=new Objects[5];
		directoryServices.add(array);
		try {
			doReturn(directoryServices).when(drugGroupRepository).getDrugGroups(Mockito.anyInt());
			List<M_DrugGroup> list=drugGroupServiceImpl.getDrugGroups(Mockito.anyInt());
			assertTrue(list.get(0).toString().equals("{}"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	@Test
	public void getDrugGroupsTest2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[3];
		array[0]=101;
		array[1]="drugGroupName";;
		array[2]="drugGroupDescName";
		list.add(array);
		try {
			doReturn(list).when(drugGroupRepository).getDrugGroups(Mockito.anyInt());
			List<M_DrugGroup> list1=drugGroupServiceImpl.getDrugGroups(Mockito.anyInt());
			assertTrue(list1.toString().contains("\"drugGroup\":\"drugGroupName\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getDrugListTest()
	{
		try {
			List<M_104drugmapping> list=drugGroupServiceImpl.getDrugList(Mockito.anyInt(),Mockito.anyInt());
			assertTrue(list.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getDrugListTest1()
	{
		List<Object[]> directoryServices=Lists.newArrayList();
		Object[] array=new Objects[7];
		directoryServices.add(array);
		try {
			doReturn(directoryServices).when(drugGroupRepository).getDrugList(Mockito.anyInt(),Mockito.anyInt());
			List<M_104drugmapping> list=drugGroupServiceImpl.getDrugList(Mockito.anyInt(),Mockito.anyInt());
			assertTrue(list.get(0).toString().equals("{}"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void getDrugListTest2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[7];
		array[0]=101;
		array[1]=102;
		array[2]="Paracetamol";
		array[3]=103;
		array[4]="drugGroupName";
		array[5]="remark";
		array[6]=104;
		list.add(array);
		try {
			doReturn(list).when(drugGroupRepository).getDrugList(Mockito.anyInt(),Mockito.anyInt());
			List<M_104drugmapping> list1=drugGroupServiceImpl.getDrugList(Mockito.anyInt(),Mockito.anyInt());
			assertTrue(list1.toString().contains("\"drugName\":\"Paracetamol\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getDrugFrequencyTest()
	{
		ArrayList<Object[]> list=drugGroupServiceImpl.getDrugFrequency();
		assertTrue(list.isEmpty());
	}
}
