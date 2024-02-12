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
package com.iemr.helpline104.controller.epidemicOutbreak;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.iemr.helpline104.data.epidemicOutbreak.T_EpidemicOutbreak;
import com.iemr.helpline104.service.epidemicOutbreak.EpidemicOutbreakServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EpidemicOutbreakControllerTest {

	@InjectMocks
	EpidemicOutbreakController EpidemicOutbreakController;

	@Mock
	EpidemicOutbreakServiceImpl epidemicOutbreakServiceImpl;

	@Test
	public void saveEpidemicOutbreakComplaintTest() {
		T_EpidemicOutbreak t_EpidemicOutbreak = new T_EpidemicOutbreak();
		t_EpidemicOutbreak.setOutbreakComplaintID(new Long("101"));
		HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
		try {
			doReturn(t_EpidemicOutbreak).when(epidemicOutbreakServiceImpl).save(Mockito.any(),
					Mockito.any(HttpServletRequest.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = EpidemicOutbreakController.saveEpidemicOutbreakComplaint(t_EpidemicOutbreak.toString(),
				httpServletRequest);
		assertTrue(response.contains("\"outbreakComplaintID\":101"));
	}

	@Test
	public void saveEpidemicOutbreakComplaintExceptionTest() {
		T_EpidemicOutbreak t_EpidemicOutbreak = new T_EpidemicOutbreak();
		t_EpidemicOutbreak.setOutbreakComplaintID(new Long("101"));
		HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
		try {
			doThrow(Exception.class).when(epidemicOutbreakServiceImpl).save(Mockito.any(),
					Mockito.any(HttpServletRequest.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = EpidemicOutbreakController.saveEpidemicOutbreakComplaint(t_EpidemicOutbreak.toString(),
				httpServletRequest);
		assertFalse(response.contains("\"outbreakComplaintID\":101"));
	}

	@Test
	public void getEpidemicOutbreakComplaintTest() {
		T_EpidemicOutbreak t_EpidemicOutbreak = new T_EpidemicOutbreak();
		t_EpidemicOutbreak.setOutbreakComplaintID(new Long("101"));
		List<T_EpidemicOutbreak> list = Lists.newArrayList();
		list.add(t_EpidemicOutbreak);
		try {
			doReturn(list).when(epidemicOutbreakServiceImpl).getEpidemicOutbreakComplaints(Mockito.anyLong(), null,
					null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = EpidemicOutbreakController.getEpidemicOutbreakComplaint(t_EpidemicOutbreak.toString());
		assertTrue(response.contains("\"outbreakComplaintID\":101"));
	}

	@Test
	public void getEpidemicOutbreakComplaintExceptionTest() {
		T_EpidemicOutbreak t_EpidemicOutbreak = new T_EpidemicOutbreak();
		t_EpidemicOutbreak.setOutbreakComplaintID(new Long("101"));
		try {
			doThrow(Exception.class).when(epidemicOutbreakServiceImpl).getEpidemicOutbreakComplaints(Mockito.anyLong(),
					null, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = EpidemicOutbreakController.getEpidemicOutbreakComplaint(t_EpidemicOutbreak.toString());
		assertFalse(response.contains("\"outbreakComplaintID\":101"));
	}
}
