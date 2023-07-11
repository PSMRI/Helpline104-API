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
package com.iemr.helpline104.service.userbeneficiarydata;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.iemr.helpline104.data.userbeneficiarydata.M_PhoneType;
import com.iemr.helpline104.repository.userbeneficiarydata.PhoneTypeRepository;
@RunWith(MockitoJUnitRunner.class)
public class PhoneTypeServiceImplTest {
	
	@InjectMocks
	PhoneTypeServiceImpl phoneTypeServiceImpl;
	
	@Mock
	PhoneTypeRepository phoneTypeRepository;
	
	@Test
	public void getActivePhoneTypes()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[20];
		Short s=101;
		array[0]=s;
		array[1]="request";
		
		
		list.add(array);
		try {
			doReturn(list).when(phoneTypeRepository).findAcivePhoneTypes();
			List<M_PhoneType> m_PhoneType= phoneTypeServiceImpl.getActivePhoneTypes();
			M_PhoneType m_Phone=m_PhoneType.get(0);
			assertTrue(m_Phone.getPhoneTypeID()==101);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getActivePhoneTypes1()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		try {
			doReturn(list).when(phoneTypeRepository).findAcivePhoneTypes();
			List<M_PhoneType> m_PhoneType= phoneTypeServiceImpl.getActivePhoneTypes();
			assertFalse(!m_PhoneType.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
