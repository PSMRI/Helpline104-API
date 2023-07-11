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

import com.iemr.helpline104.data.userbeneficiarydata.M_Religion;
import com.iemr.helpline104.repository.userbeneficiarydata.ReligionRepository;

@RunWith(MockitoJUnitRunner.class)
public class RelegionServiceImplTest {
	
	@InjectMocks
	RelegionServiceImpl relegionServiceImpl;
	
	@Mock
	ReligionRepository relegionRepository;
	
	@Test
	public void getActiveReligionsTest()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[3];
		array[0]=101;
		array[1]="religion1";
		array[2]="religion2";
		
		list.add(array);
		try {
			doReturn(list).when(relegionRepository).getActiveReligions();
			List<M_Religion> m_Religion= relegionServiceImpl.getActiveReligions();
			assertTrue(m_Religion.toString().contains("\"ReligionType\":\"religion1\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getActiveReligionsTest1()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		try {
			doReturn(list).when(relegionRepository).getActiveReligions();
			List<M_Religion> m_Religion= relegionServiceImpl.getActiveReligions();
			assertFalse(!m_Religion.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
