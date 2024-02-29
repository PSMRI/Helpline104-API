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
package com.iemr.helpline104.controller.bloodRequest;

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
import com.iemr.helpline104.data.bloodComponentType.M_BloodGroup;
import com.iemr.helpline104.data.bloodComponentType.M_ComponentType;
import com.iemr.helpline104.data.bloodRequest.BloodBank;
import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;
import com.iemr.helpline104.service.bloodComponentType.BloodComponentTypeServiceImpl;
import com.iemr.helpline104.service.bloodRequest.BloodRequestServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BloodRequestControllerTest {

	
	@InjectMocks
	BloodRequestController bloodRequestController;
	
	@Mock
	BloodRequestServiceImpl bloodRequestServiceImpl;
	
	@Mock
	BloodComponentTypeServiceImpl componentTypeServiceImpl;
	
	@Test
	public void saveBloodRequestDetailsTest()
	{
		T_BloodRequest t_BloodRequest=new T_BloodRequest();
		t_BloodRequest.setBeneficiaryRegID(new Long("101"));
		try {
			doReturn(t_BloodRequest).when(bloodRequestServiceImpl).save(Mockito.any());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodRequestController.saveBloodRequestDetails(t_BloodRequest.toString());
		assertTrue(response.contains("\"beneficiaryRegID\":101"));
	}
	@Test
	public void saveBloodRequestDetailsExceptionTest()
	{
		T_BloodRequest t_BloodRequest=new T_BloodRequest();
		t_BloodRequest.setBeneficiaryRegID(new Long("101"));
		try {
			doThrow(Exception.class).when(bloodRequestServiceImpl).save(Mockito.any());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodRequestController.saveBloodRequestDetails(t_BloodRequest.toString());
		assertFalse(response.contains("\"beneficiaryRegID\":101"));
	}
	@Test
	public void getbloodRequestDetailsTest()
	{
		T_BloodRequest t_BloodRequest=new T_BloodRequest();
		t_BloodRequest.setBeneficiaryRegID(new Long("101"));
		List<T_BloodRequest> list=Lists.newArrayList();
		list.add(t_BloodRequest);
		try {
			doReturn(list).when(bloodRequestServiceImpl).getBloodRequest(Mockito.anyLong(),Mockito.anyString(),null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodRequestController.getbloodRequestDetails(t_BloodRequest.toString());
		assertTrue(response.contains("\"beneficiaryRegID\":101"));
	
	}
	@Test
	public void getbloodRequestDetailsExceptionTest()
	{
		T_BloodRequest t_BloodRequest=new T_BloodRequest();
		t_BloodRequest.setBeneficiaryRegID(new Long("101"));
		List<T_BloodRequest> list=Lists.newArrayList();
		list.add(t_BloodRequest);
		try {
			doThrow(Exception.class).when(bloodRequestServiceImpl).getBloodRequest(Mockito.anyLong(),Mockito.anyString(),null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodRequestController.getbloodRequestDetails(t_BloodRequest.toString());
		assertFalse(response.contains("\"beneficiaryRegID\":101"));
	
	}
	@Test
	public void getBloodComponentTypesTest()
	{
		M_ComponentType m_ComponentType=new M_ComponentType();
		m_ComponentType.setComponentTypeID(101);
		List<M_ComponentType> list=Lists.newArrayList();
		list.add(m_ComponentType);
		try {
			doReturn(list).when(componentTypeServiceImpl).getBloodComponentTypes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodRequestController.getBloodComponentTypes();
		assertTrue(response.contains("\"componentTypeID\":101"));
	
	}
	@Test
	public void getBloodComponentTypesExceptionTest()
	{
		M_ComponentType m_ComponentType=new M_ComponentType();
		m_ComponentType.setComponentTypeID(101);
		List<M_ComponentType> list=Lists.newArrayList();
		list.add(m_ComponentType);
		try {
			doThrow(Exception.class).when(componentTypeServiceImpl).getBloodComponentTypes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodRequestController.getBloodComponentTypes();
		assertFalse(response.contains("\"componentTypeID\":101"));
	
	}
	@Test
	public void getBloodGroupsTest()
	{
		M_BloodGroup m_BloodGroup=new M_BloodGroup();
		m_BloodGroup.setBloodGroup("A");
		List<M_BloodGroup> list=Lists.newArrayList();
		list.add(m_BloodGroup);
		try {
			doReturn(list).when(componentTypeServiceImpl).getBloodGroups();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodRequestController.getBloodGroups();
		assertTrue(response.contains("\"bloodGroup\":\"A\""));
	
	}
	@Test
	public void getBloodGroupsExceptionTest()
	{
		M_BloodGroup m_BloodGroup=new M_BloodGroup();
		m_BloodGroup.setBloodGroup("A");
		List<M_BloodGroup> list=Lists.newArrayList();
		list.add(m_BloodGroup);
		try {
			doThrow(Exception.class).when(componentTypeServiceImpl).getBloodGroups();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodRequestController.getBloodGroups();
		assertFalse(response.contains("\"bloodGroup\":\"A\""));
	
	}
	
	@Test
	public void getBloodBankURLTest()
	{
		BloodBank bloodBank=new BloodBank();
		bloodBank.setInstitutionName("104BloodBank");
		try {
			doReturn(bloodBank).when(bloodRequestServiceImpl).getBloodBankURL(Mockito.anyString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodRequestController.getBloodBankURL("104BloodBank");
		//assertTrue(response.contains("\"bloodBank\":\"institute\""));
	}
	@Test
	public void getBloodBankURLTest1()
	{
		String response=bloodRequestController.getBloodBankURL(Mockito.anyString());
		assertTrue(response.contains("Blood bank URL is not configured"));
	}
	@Test
	public void getBloodBankURLExceptionTest()
	{
		BloodBank bloodBank=new BloodBank();
		bloodBank.setInstitutionName("institute");
		try {
			doThrow(Exception.class).when(bloodRequestServiceImpl).getBloodBankURL(Mockito.anyString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodRequestController.getBloodBankURL(Mockito.anyString());
		assertFalse(response.contains("\"bloodBank\":\"institute\""));
	}
	@Test
	public void saveBloodBankURLTest()
	{
		BloodBank bloodBank=new BloodBank();
		bloodBank.setInstitutionName("104BloodBank");
		
		try {
			doReturn(bloodBank).when(bloodRequestServiceImpl).saveBloodBankURL(Mockito.any());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodRequestController.saveBloodBankURL("{\"institutionID\":101,\"institutionName\":\"104BloodBank\",\"providerServiceMapID\":105, \"createdBy\":\"author\",\"website\":\"www\"}");
		assertTrue(response.contains("\"status\":\"Success\""));

	}
	@Test
	public void saveBloodBankURLExceptionTest() 
	{
		BloodBank bloodBank=new BloodBank();
		try {
			doThrow(Exception.class).when(bloodRequestServiceImpl).saveBloodBankURL(Mockito.any());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=bloodRequestController.saveBloodBankURL(bloodBank.toString());
		assertFalse(response.contains("\"bloodBank\":\"104BloodBank\""));

	}

}
