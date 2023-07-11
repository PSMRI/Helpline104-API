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
package com.iemr.helpline104.controller.healthCareWorkerType;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.iemr.helpline104.data.healthCareWorkerType.M_HealthCareWorker;
import com.iemr.helpline104.service.healthCareWorkerType.HealthCareWorkerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class HealthCareWorkerTypeControllerTest {

	@InjectMocks
	HealthCareWorkerTypeController healthCareWorkerTypeController;
	
	@Mock
	HealthCareWorkerServiceImpl healthCareWorkerServiceImpl;
	
	@Test
	public void getHealthCareWorkerTypesTest()
	{
		List<M_HealthCareWorker> i_BeneficiaryTypes=Lists.newArrayList();
		M_HealthCareWorker m_HealthCareWorker=new M_HealthCareWorker();
		m_HealthCareWorker.setHealthCareWorkerType("Asha");
		i_BeneficiaryTypes.add(m_HealthCareWorker);
		doReturn(i_BeneficiaryTypes).when(healthCareWorkerServiceImpl).getHealthCareWorkerTypes();
		String response=healthCareWorkerTypeController.getHealthCareWorkerTypes();
		assertTrue(response.contains("\"healthCareWorkerType\":\"Asha\""));
	}
}
