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
package com.iemr.helpline104.controller.NKSHPscheme;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.iemr.helpline104.controller.scheme.SchemeController;
import com.iemr.helpline104.data.scheme.T_Schemeservice;
import com.iemr.helpline104.service.scheme.SchemeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class NKSHPschemeControllerTest {

	@InjectMocks
	SchemeController nKSHPschemeController;
	
	@Mock
	SchemeServiceImpl nkshpschemeServiceImpl;
	
	@Test
	public void getNKSHPschemeListTest()
	{
		T_Schemeservice m_scheme=new T_Schemeservice();
		//m_scheme.setSchemeName("scheme");
		
		ArrayList<Object[]> obj=Lists.newArrayList();
		Object[] array=new Object[2];
		array[0]=m_scheme;
		obj.add(array);
		
		try {
			doReturn(obj).when(nkshpschemeServiceImpl).saveSchemeSearchHistory(Mockito.any());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=nKSHPschemeController.saveSchemeSearchHistory(m_scheme.toString());
		assertTrue(response.contains("\"status\":\"Success\""));
	}
	@Test
	public void getNKSHPschemeListTest1()
	{	
		T_Schemeservice m_scheme=new T_Schemeservice();
		try {
			doReturn(null).when(nkshpschemeServiceImpl).saveSchemeSearchHistory(Mockito.any());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=nKSHPschemeController.saveSchemeSearchHistory(m_scheme.toString());
		assertTrue(response.contains("No schemes available"));
	}
	@Test
	public void getNKSHPschemeListExceptionTest()
	{	
		T_Schemeservice m_scheme=new T_Schemeservice();
		try {
			doThrow(Exception.class).when(nkshpschemeServiceImpl).saveSchemeSearchHistory(Mockito.any());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=nKSHPschemeController.saveSchemeSearchHistory(m_scheme.toString());
		assertFalse(response.contains("\"status\":\"Success\""));
	}
}
