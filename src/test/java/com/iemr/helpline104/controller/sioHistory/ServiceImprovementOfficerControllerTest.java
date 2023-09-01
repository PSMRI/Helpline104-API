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
package com.iemr.helpline104.controller.sioHistory;

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
import org.mockito.runners.MockitoJUnitRunner;

import com.iemr.helpline104.data.bloodComponentType.M_ComponentType;
import com.iemr.helpline104.service.bloodComponentType.BloodComponentTypeServiceImpl;
import com.iemr.helpline104.service.bloodRequest.BloodRequestServiceImpl;
import com.iemr.helpline104.service.directory.DirectoryServiceImpl;
import com.iemr.helpline104.service.epidemicOutbreak.EpidemicOutbreakServiceImpl;
import com.iemr.helpline104.service.foodSafetyCopmlaint.FoodSafetyCopmlaintServiceImpl;
import com.iemr.helpline104.service.organDonation.OrganDonationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ServiceImprovementOfficerControllerTest {

	@InjectMocks
	ServiceImprovementOfficerController sioHistoryController;
	
	@Mock
	BloodRequestServiceImpl bloodRequestServiceImpl;
	
	@Mock
	BloodComponentTypeServiceImpl bloodComponentTypeServiceImpl;
	
	@Mock
	EpidemicOutbreakServiceImpl epidemicOutbreakServiceImpl;
	
	@Mock
	OrganDonationServiceImpl organDonationServiceImpl;
	
	@Mock
	DirectoryServiceImpl directoryServiceImpl;
	
	@Mock
	FoodSafetyCopmlaintServiceImpl foodSafetyCopmlaintServiceImpl;
	
	
	@Test
	public void getSioHistoryTest()
	{
		List<M_ComponentType> list=Lists.newArrayList();
		M_ComponentType m_ComponentType=new M_ComponentType();
		m_ComponentType.setComponentType("type");
		list.add(m_ComponentType);
		try {
			doReturn(list).when(bloodComponentTypeServiceImpl).getBloodComponentTypes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=sioHistoryController.getSioHistory("{\"benificiaryRegID\":101}");
		assertTrue(response.contains("\"componentType\":\"type\""));
	}
	
	@Test
	public void getSioHistoryExceptionTest()
	{
		List<M_ComponentType> list=Lists.newArrayList();
		M_ComponentType m_ComponentType=new M_ComponentType();
		m_ComponentType.setComponentType("type");
		list.add(m_ComponentType);
		try {
			doThrow(Exception.class).when(bloodComponentTypeServiceImpl).getBloodComponentTypes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=sioHistoryController.getSioHistory("{\"benificiaryRegID\":101}");
		assertFalse(response.contains("\"componentType\":\"type\""));
	}
	@Test
	public void getSioHistoryJSONExceptionTest()
	{
		List<M_ComponentType> list=Lists.newArrayList();
		M_ComponentType m_ComponentType=new M_ComponentType();
		m_ComponentType.setComponentType("type");
		list.add(m_ComponentType);
		try {
			doThrow(JSONException.class).when(bloodComponentTypeServiceImpl).getBloodComponentTypes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=sioHistoryController.getSioHistory("{\"benificiaryRegID\":101}");
		assertTrue(response.contains("Invalid object conversion"));
	}
}
