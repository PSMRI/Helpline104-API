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
package com.iemr.helpline104.service.bloodComponent;

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
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.iemr.helpline104.data.bloodComponent.M_Component;
import com.iemr.helpline104.data.userbeneficiarydata.M_Religion;
import com.iemr.helpline104.repository.bloodComponent.BloodComponentRepository;

@RunWith(MockitoJUnitRunner.class)
public class BloodComponentServiceImplTest {

	
	@InjectMocks
	BloodComponentServiceImpl bloodComponentServiceImpl;
	
	@Mock
	BloodComponentRepository bloodComponentRepository;
	
	@Test
	public void saveTest()
	{
		M_Component m_Component=new M_Component();
		m_Component.setComponentID(101);
		
		M_Component m_Component1=new M_Component();
		m_Component1.setComponentID(101);
		
		doReturn(m_Component1).when(bloodComponentRepository).save(m_Component);
		M_Component component=bloodComponentServiceImpl.save(m_Component);
		assertTrue(component.getComponentID()==101);
	}
	
	@Test
	public void saveTest1()
	{
		M_Component m_Component=new M_Component();
		m_Component.setComponentID(101);
		M_Component component=bloodComponentServiceImpl.save(m_Component);
		assertFalse(component!=null);
	}
	
	@Test
	public void getBloodComponentsTest()
	{
		try {
			List<M_Component> list=bloodComponentServiceImpl.getBloodComponents(Mockito.anyInt());
			assertTrue(list.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getBloodComponentsTest1()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[3];
		array[0]=101;
		array[1]="componentName";
		array[2]="componentDescName";
		
		list.add(array);
		try {
			doReturn(list).when(bloodComponentRepository).getBloodComponents(Mockito.anyInt());
			List<M_Component> m_Component= bloodComponentServiceImpl.getBloodComponents(Mockito.anyInt());
			assertTrue(m_Component.toString().contains("\"component\":\"componentName\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
