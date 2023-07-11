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
package com.iemr.helpline104.controller.beneficiarycall;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.List;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.iemr.helpline104.data.beneficiarycall.BenCallServicesMappingHistory;
import com.iemr.helpline104.data.beneficiarycall.M_subservice;
import com.iemr.helpline104.service.beneficiarycall.ServicesHistoryService;
import com.iemr.helpline104.utils.exception.IEMRException;

@RunWith(MockitoJUnitRunner.class)
public class BeneficiarycallControllerTest {

	@InjectMocks
	BeneficiarycallController beneficiarycallController;
	
	@Mock
	ServicesHistoryService servicesHistoryService;
	
	
	@Test
	public void getServicesTest()
	{
		M_subservice m_subservice=new M_subservice();
		m_subservice.setProviderServiceMapID(101);
		List<Objects[]> services=Lists.newArrayList();
		Objects[] obj=new Objects[3];
		services.add(0, obj);
		doReturn(services).when(servicesHistoryService).getServices(Mockito.anyInt());
		String response=beneficiarycallController.getServices(m_subservice.toString());
		assertTrue(response.contains("\"status\":\"Success\""));
	}
	@Test
	public void getServicesExceptionTest()
	{
		M_subservice m_subservice=new M_subservice();
		m_subservice.setProviderServiceMapID(101);
		doThrow(IEMRException.class).when(servicesHistoryService).getServices(Mockito.any());
		String response=beneficiarycallController.getServices(m_subservice.toString());
		assertTrue(response.contains("User login failed"));
	}
	@Test
	public void setServiceHistoryTest()
	{
		BenCallServicesMappingHistory history=new BenCallServicesMappingHistory(new Long("101"),new Long("102"),103,true,"createdBy");
		doReturn(history).when(servicesHistoryService).createServiceHistory(Mockito.any());
		String response=beneficiarycallController.setServiceHistory(history.toString());
		assertTrue(response.contains("\"beneficiaryRegID\":101"));
	}
	@Test
	public void setServiceHistoryExceptionTest()
	{
		BenCallServicesMappingHistory history=new BenCallServicesMappingHistory(new Long("101"),new Long("102"),103,true,"createdBy");
		doThrow(IEMRException.class).when(servicesHistoryService).createServiceHistory(Mockito.any());
		String response=beneficiarycallController.setServiceHistory(history.toString());
		assertTrue(response.contains("User login failed"));
	}

}
