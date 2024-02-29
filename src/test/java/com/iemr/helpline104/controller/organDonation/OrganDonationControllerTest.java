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
package com.iemr.helpline104.controller.organDonation;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import com.google.common.collect.Lists;
import com.iemr.helpline104.data.organDonation.M_DonatableOrgan;
import com.iemr.helpline104.data.organDonation.M_DonationType;
import com.iemr.helpline104.data.organDonation.OrganDonations;
import com.iemr.helpline104.data.organDonation.T_OrganDonation;
import com.iemr.helpline104.service.organDonation.OrganDonationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrganDonationControllerTest {

	@InjectMocks
	OrganDonationController organDonationController;
	
	@Mock
	OrganDonationServiceImpl organDonationServiceImpl;
	
	@Test
	public void saveOrganDonationDetailsTest()
	{
		OrganDonations organDonations=new OrganDonations();
		T_OrganDonation t_organDonations=new T_OrganDonation();
		t_organDonations.setBeneficiaryRegID(new Long("101"));
		t_organDonations.setDonatableOrganID(102);
		List<T_OrganDonation> list=new ArrayList<T_OrganDonation>();
		list.add(t_organDonations);
		organDonations.setT_organDonations(list);
		try {
			doReturn(list.toString()).when(organDonationServiceImpl).save(Mockito.any());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=organDonationController.saveOrganDonationDetails("{\"organDonationID\":107}");
		assertTrue(response.contains("\"beneficiaryRegID\":101"));
	}
	@Test
	public void saveOrganDonationDetailsExceptionTest()
	{

		try {
			doThrow(Exception.class).when(organDonationServiceImpl).save(Mockito.any());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=organDonationController.saveOrganDonationDetails("{\"organDonationID\":107}");
		assertFalse(response.contains("beneficiaryRegID"));
		assertTrue(response.contains("Failed with null"));
	}
	@Test
	public void getOrganDonationDetailsTest()
	{
		T_OrganDonation t_organDonations=new T_OrganDonation();
		t_organDonations.setBeneficiaryRegID(new Long("101"));
		t_organDonations.setDonatableOrganID(102);
		List<T_OrganDonation> list=new ArrayList<T_OrganDonation>();
		list.add(t_organDonations);
		
		try {
			doReturn(list).when(organDonationServiceImpl).getOrganDonationRequests(Mockito.anyLong(),null,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=organDonationController.getOrganDonationDetails("{\"organDonationID\":107}");
		assertTrue(response.contains("\"beneficiaryRegID\":101"));
	}
	@Test
	public void getOrganDonationDetailsExceptionTest()
	{
		
		try {
			doThrow(Exception.class).when(organDonationServiceImpl).getOrganDonationRequests(Mockito.anyLong(),null,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=organDonationController.getOrganDonationDetails("{\"organDonationID\":107}");
		assertFalse(response.contains("\"beneficiaryRegID\":101"));
		assertTrue(response.contains("Failed with null"));
	}
	@Test
	public void getOrganDonationTypesTest()
	{
		List<M_DonationType> list=Lists.newArrayList();
		M_DonationType m_DonationType=new M_DonationType();
		m_DonationType.setDonationType("donationType");
		list.add(m_DonationType);
		try {
			doReturn(list).when(organDonationServiceImpl).getDonationTypes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=organDonationController.getOrganDonationTypes();
		assertTrue(response.contains("\"donationType\":\"donationType\""));
	}
	@Test
	public void getOrganDonationTypesExceptionTest()
	{
		try {
			doThrow(Exception.class).when(organDonationServiceImpl).getDonationTypes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=organDonationController.getOrganDonationTypes();
		assertFalse(response.contains("\"donationType\":\"donationType\""));
		assertTrue(response.contains("Failed with null"));
	}
	
	@Test
	public void getDonatableOrgansTest()
	{
		List<M_DonatableOrgan> list =Lists.newArrayList();
		M_DonatableOrgan m_DonatableOrgan=new M_DonatableOrgan();
		m_DonatableOrgan.setDonatableOrgan("Kindney");
		list.add(m_DonatableOrgan);
		
		try {
			doReturn(list).when(organDonationServiceImpl).getDonatableOrgans();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=organDonationController.getDonatableOrgans();
		assertTrue(response.contains("\"donatableOrgan\":\"Kindney\""));
	}
	
	@Test
	public void getDonatableOrgansExceptionTest()
	{
		
		try {
			doThrow(Exception.class).when(organDonationServiceImpl).getDonatableOrgans();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response=organDonationController.getDonatableOrgans();
		assertFalse(response.contains("\"donatableOrgan\":\"Kindney\""));
		assertTrue(response.contains("Failed with null"));
	}
}
