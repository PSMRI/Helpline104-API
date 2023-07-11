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
package com.iemr.helpline104.service.healthCareWorkerType;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.iemr.helpline104.data.healthCareWorkerType.M_HealthCareWorker;
import com.iemr.helpline104.repository.healthCareWorkerType.HealthCareWorkerTypeRepository;

@RunWith(MockitoJUnitRunner.class)
public class HealthCareWorkerServiceImplTest {
	
	@InjectMocks
	HealthCareWorkerServiceImpl t_FeedbackresponseService;
	
	@Mock
	HealthCareWorkerTypeRepository healthCareWorkerTypeRepository;
	
	@Test
	public void getHealthCareWorkerTypesTest()
	{
		ArrayList<Objects[]> resultSet=Lists.newArrayList();
		Objects[] array=new Objects[25];
		resultSet.add(array);
		doReturn(resultSet).when(healthCareWorkerTypeRepository).findAciveHealthCareWorkerTypes();
		List<M_HealthCareWorker> list=t_FeedbackresponseService.getHealthCareWorkerTypes();
		assertFalse(list.toString().contains("\"healthCareWorkerDesc\""));
	}
	
	@Test
	public void getHealthCareWorkerTypesTest1()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[2];
		Short s=15;
		array[0]=s;
		array[1]="NGO";

		list.add(array);
		try {
			doReturn(list).when(healthCareWorkerTypeRepository).findAciveHealthCareWorkerTypes();
			List<M_HealthCareWorker> list1=t_FeedbackresponseService.getHealthCareWorkerTypes();
			assertTrue(list1.toString().contains("\"healthCareWorkerType\":\"NGO\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
