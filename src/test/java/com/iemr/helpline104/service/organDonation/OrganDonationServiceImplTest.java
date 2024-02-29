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
package com.iemr.helpline104.service.organDonation;



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

import com.iemr.helpline104.data.organDonation.M_DonatableOrgan;
import com.iemr.helpline104.data.organDonation.M_DonationType;
import com.iemr.helpline104.data.organDonation.OrganDonations;
import com.iemr.helpline104.data.organDonation.T_OrganDonation;
import com.iemr.helpline104.repository.organDonation.OrganDonationRepository;

@ExtendWith(MockitoExtension.class)
public class OrganDonationServiceImplTest {
	
	@InjectMocks
	OrganDonationServiceImpl organDonationServiceImpl;
	
	@Mock
	OrganDonationRepository organDonationRepository;
	
	@Test
	public void getOrganDonationRequestsTest()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[20];
		Short s=10;
		array[0]=101L;
		array[1]="request";
		array[2]=101L;
		array[3]="request";
		array[4]=101;
		array[5]=s;
		array[6]="request";
		array[7]=101;
		array[8]="request";
		array[9]="request";
		array[10]=101;
		array[11]="request";
		array[12]="request";
		array[13]=101;
		array[14]="request";
		
		list.add(array);
		try {
			doReturn(list).when(organDonationRepository).getOrganDonationRequests(Mockito.anyLong());
			List<T_OrganDonation> t_OrganDonations= organDonationServiceImpl.getOrganDonationRequests(Mockito.anyLong(),null,null);
			T_OrganDonation t_OrganDonation=t_OrganDonations.get(0);
			assertTrue(t_OrganDonation.getOrganDonationID()==101L);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void getOrganDonationRequestsTest1()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		
		try {
			doReturn(list).when(organDonationRepository).getOrganDonationRequests(Mockito.anyLong());
			List<T_OrganDonation> list1= organDonationServiceImpl.getOrganDonationRequests(Mockito.anyLong(),null,null);
			assertFalse(!list1.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void saveTest()
	{
		OrganDonations organDonations=new OrganDonations();
		List<T_OrganDonation> list=Lists.newArrayList();
		T_OrganDonation organDonation=new T_OrganDonation(101L,"requestID",102L,"donarName",45,new Short("29"),"genderName",104,"donationType","donationTypeDesc",178,"donatableOrgan","donatableOrganDesc",76,"remarks");
		list.add(organDonation);
		organDonations.setT_organDonations(list);
		try {
			//String response= organDonationServiceImpl.save(organDonations);
			//assertTrue(response.contains("\"beneficiaryRegID\":101"));
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getDonationTypesTest()
	{
		List<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[9];
		array[0]=101;
		array[1]="donationType";
		array[2]="donationTypeDesc";
		list.add(array);
		try {
			doReturn(list).when(organDonationRepository).getDonationTypes();
			List<M_DonationType> M_DonationTypes= organDonationServiceImpl.getDonationTypes();
			M_DonationType M_DonationType=M_DonationTypes.get(0);
			assertTrue(M_DonationType.getDonationTypeID()==101);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	@Test
	public void getDonationTypesTest1()
	{
		List<Object[]> list=Lists.newArrayList();
		try {
			doReturn(list).when(organDonationRepository).getDonationTypes();
			List<M_DonationType> M_DonationTypes= organDonationServiceImpl.getDonationTypes();
			assertFalse(!M_DonationTypes.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	@Test
	public void getDonatableOrgansTest()
	{

		List<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[9];
		array[0]=101;
		array[1]="donatableOrgan";
		array[2]="donatableOrganDesc";
		list.add(array);
		try {
			doReturn(list).when(organDonationRepository).getDonatableOrgans();
			List<M_DonatableOrgan> M_DonatableOrgans= organDonationServiceImpl.getDonatableOrgans();
			M_DonatableOrgan m_DonatableOrgan=M_DonatableOrgans.get(0);
			assertTrue(m_DonatableOrgan.getDonatableOrganID()==101);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getDonatableOrgansTest1()
	{

		List<Object[]> list=Lists.newArrayList();
		try {
			doReturn(list).when(organDonationRepository).getDonatableOrgans();
			List<M_DonatableOrgan> M_DonatableOrgans= organDonationServiceImpl.getDonatableOrgans();
			assertFalse(!M_DonatableOrgans.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
