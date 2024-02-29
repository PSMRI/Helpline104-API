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
package com.iemr.helpline104.controller.bloodComponent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.Lists;
import com.iemr.helpline104.data.bloodComponent.M_Component;
import com.iemr.helpline104.service.bloodComponent.BloodComponentServiceImpl;
@ExtendWith(MockitoExtension.class)
public class BloodComponentControllerTest {
	
	@InjectMocks
	BloodComponentController bloodComponentController;
	
	@Mock
	BloodComponentServiceImpl bloodComponentServiceImpl;
	

	
	@Test
	public void saveBloodComponentDetailsTest()
	{
		M_Component m_component=new M_Component();
		m_component.setComponent("component");
		//Mockito.when(bloodComponentServiceImpl.save(Mockito.anyObject())).thenReturn(m_component);
		doReturn(m_component).when(bloodComponentServiceImpl).save(Mockito.any());
		//Mockito.when(m_component1.toString()).thenReturn("hello");
		String response=bloodComponentController.saveBloodComponentDetails(m_component.toString()); 
		assertTrue(response.contains("\"component\":\"component\""));
		
	}
	@Test
	public void saveBloodComponentDetailsExceptionTest()
	{
		M_Component m_component=new M_Component();
		m_component.setComponent("component");
		//Mockito.when(bloodComponentServiceImpl.save(Mockito.anyObject())).thenReturn(m_component);
		doThrow(Exception.class).when(bloodComponentServiceImpl).save(Mockito.any());
		//Mockito.when(m_component1.toString()).thenReturn("hello");
		String response=bloodComponentController.saveBloodComponentDetails(m_component.toString()); 
		assertFalse(response.contains("\"component\":\"component\""));
		
	}
	@Test
	public void getBloodComponentDetailsTest()
	{
		M_Component m_component=new M_Component();
		m_component.setComponent("component");
		List<M_Component> bloodComponent =Lists.newArrayList();
		bloodComponent.add(m_component);
		try {
			doReturn(bloodComponent).when(bloodComponentServiceImpl).getBloodComponents(Mockito.anyInt());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodComponentController.getBloodComponentDetails(m_component.toString());
		assertTrue(response.contains("\"component\":\"component\""));
		
	}
	
	@Test
	public void getBloodComponentDetailsExceptionTest()
	{
		M_Component m_component=new M_Component();
		m_component.setComponent("component");
		List<M_Component> bloodComponent =Lists.newArrayList();
		bloodComponent.add(m_component);
		try {
			doThrow(Exception.class).when(bloodComponentServiceImpl).getBloodComponents(Mockito.anyInt());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodComponentController.getBloodComponentDetails(m_component.toString());
		assertFalse(response.contains("\"component\":\"component\""));
		
	}
}
