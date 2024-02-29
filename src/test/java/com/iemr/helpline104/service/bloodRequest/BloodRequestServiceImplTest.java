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
package com.iemr.helpline104.service.bloodRequest;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.bloodRequest.BloodBank;
import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;
import com.iemr.helpline104.repository.bloodRequest.BloodRequestRepository;
import com.iemr.helpline104.repository.bloodRequest.InstituteRepository;

@ExtendWith(MockitoExtension.class)
public class BloodRequestServiceImplTest {
	
	@InjectMocks
	BloodRequestServiceImpl bloodRequestServiceImpl;
	
	@Mock
	BloodRequestRepository bloodRequestRepository;
	
	@Mock
	BloodRequestRepository bloodRequestRepo;
	
	@Mock
	InstituteRepository instituteRepository;
	
	@Test
	public void getBloodRequestTest()
	{
		try {
			List<T_BloodRequest> list=bloodRequestServiceImpl.getBloodRequest(Mockito.anyLong(), "bloodReqID",null);
			assertTrue(list.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void getBloodRequestTest1()
	{
		try {
			List<T_BloodRequest> list=bloodRequestServiceImpl.getBloodRequest(101L, "bloodReqID",null);
			assertTrue(list.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getBloodRequestTest2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[30];
		
		Short s=10;
		Byte b=11;
		
		array[0]=101L;
		array[1]="request";
		array[2]=101L;
		array[3]=101L; array[15]=101;
		array[4]=""; array[16]="";
		array[5]=101; array[17]="";
		array[6]=s; array[18]="";
		array[7]=""; array[19]="";
		array[8]=""; array[20]=101;
		array[9]=101; array[21]="";
		array[10]=""; array[22]=b;
		array[11]=""; array[23]=new Date(Calendar.getInstance().getTimeInMillis());
		array[12]=101; array[24]="";
		array[13]=""; array[25]="";
		array[14]=""; array[26]="";
		array[27]=""; array[28]="";
		array[29]=""; 
		
		
		list.add(array);
		try {
			doReturn(list).when(bloodRequestRepository).getBloodRequestsByBeneficiaryRegID(Mockito.anyLong());
			List<T_BloodRequest> t_BloodRequest= bloodRequestServiceImpl.getBloodRequest(101L,Mockito.anyString(),null);
			assertTrue(t_BloodRequest.toString().contains("\"requestID\":\"request\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void saveTest()
	{
		T_BloodRequest t_bloodRequest=new T_BloodRequest();
		t_bloodRequest.setBeneficiaryRegID(new Long("101"));
		
		when(bloodRequestRepository.save(t_bloodRequest)).then(AdditionalAnswers.returnsFirstArg());
		try {
			T_BloodRequest bloodRequest=bloodRequestServiceImpl.save(t_bloodRequest);
			assertTrue(bloodRequest.getBeneficiaryRegID()==101);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	/*
	@Test
	public void saveTest1()
	{
		T_BloodRequest t_bloodRequest=new T_BloodRequest();
		t_bloodRequest.setBloodReqID(new Long("101"));
		
		doReturn(1).when(bloodRequestRepository).updateBloodBankDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(Timestamp));
		try {
			T_BloodRequest bloodRequest=bloodRequestServiceImpl.save(t_bloodRequest);
			assertTrue(bloodRequest.getBloodReqID()==101);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	} */
	
	@Test
	public void getBloodBankURLTest()
	{
		BloodBank bloodBank=new BloodBank();
		bloodBank.setInstitutionName("instituteName");
		doReturn(bloodBank).when(instituteRepository).findBloodBankURL(Mockito.anyString());
		try {
			BloodBank bloodBank1=bloodRequestServiceImpl.getBloodBankURL(Mockito.anyString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void saveBloodBankURLTest()
	{
		BloodBank bloodBank=new BloodBank();
		bloodBank.setInstitutionName("instituteName");
		doReturn(bloodBank).when(instituteRepository).save(bloodBank);
		try {
			BloodBank bloodBank1=bloodRequestServiceImpl.saveBloodBankURL(bloodBank);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
