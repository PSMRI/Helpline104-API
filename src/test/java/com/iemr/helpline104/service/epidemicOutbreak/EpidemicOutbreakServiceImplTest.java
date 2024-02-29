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
package com.iemr.helpline104.service.epidemicOutbreak;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.epidemicOutbreak.T_EpidemicOutbreak;
import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.data.location.DistrictBlock;
import com.iemr.helpline104.repository.epidemicOutbreak.EpidemicOutbreakRepository;
import com.iemr.helpline104.repository.feedbackType.FeedbackTypeRepository;
import com.iemr.helpline104.repository.location.LocationCityRepository;
import com.iemr.helpline104.repository.location.LocationDistrictBlockRepository;
import com.iemr.helpline104.repository.location.LocationDistrictRepository;
import com.iemr.helpline104.utils.http.HttpUtils;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
public class EpidemicOutbreakServiceImplTest {

	@InjectMocks
	EpidemicOutbreakServiceImpl epidemicOutbreakServiceImpl;

	@Mock
	EpidemicOutbreakRepository epidemicOutbreakRepository;

	@Mock
	LocationCityRepository locationCityRepository;

	@Mock
	LocationDistrictBlockRepository locationDistrictBlockRepository;

	@Mock
	LocationDistrictRepository locationDistrictRepository;

	@Mock
	FeedbackTypeRepository feedbackRepositorty;

	@Mock
	HttpUtils utils;

	@Test
	public void saveTest() {
		T_EpidemicOutbreak t_epidemicOutbreak = new T_EpidemicOutbreak();
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		t_epidemicOutbreak.setAffectedDistrictID(101);
		t_epidemicOutbreak.setOutbreakComplaintID(102L);
		t_epidemicOutbreak.setAffectedDistrictBlockID(103);
		doReturn(t_epidemicOutbreak).when(epidemicOutbreakRepository).save(t_epidemicOutbreak);
		DistrictBlock districtBlock = new DistrictBlock();
		districtBlock.setBlockID(103);
		doReturn(districtBlock).when(locationDistrictBlockRepository)
				.findById(t_epidemicOutbreak.getAffectedDistrictBlockID());

		M_FeedbackType feedbackType = new M_FeedbackType();
		feedbackType.setFeedbackTypeName("feedbacktype");
		doReturn(feedbackType).when(feedbackRepositorty).findEpidemicFeedbackTypeID(Mockito.anyInt());
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("Authorization", "auth");
		doReturn("response").when(utils).post("", "", header);
		try {
			// epidemicOutbreakServiceImpl.save(t_epidemicOutbreak, request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getEpidemicOutbreakComplaintsTest() {
		List<Objects[]> list = Lists.newArrayList();
		Objects[] array = new Objects[12];
		list.add(array);

		try {
			doReturn(list).when(epidemicOutbreakRepository).getEpidemicOutbreakComplaints(Mockito.anyLong());
			List<T_EpidemicOutbreak> list1 = epidemicOutbreakServiceImpl
					.getEpidemicOutbreakComplaints(Mockito.anyLong(), null, null, null);
			assertTrue(list1.toString().contains("\"beneficiaryRegID\":null"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void getEpidemicOutbreakComplaintsTest1() {

		try {
			List<T_EpidemicOutbreak> list = epidemicOutbreakServiceImpl.getEpidemicOutbreakComplaints(Mockito.anyLong(),
					null, null, null);
			assertTrue(list.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void getEpidemicOutbreakComplaintsTest2() {
		ArrayList<Object[]> list = Lists.newArrayList();
		Object[] array = new Object[11];
		array[0] = 101L;
		array[1] = "requestIDValue";
		array[2] = 102L;
		array[3] = "";
		array[4] = 104;
		array[5] = 105;
		array[6] = "";
		array[7] = 104;
		array[8] = "";
		array[9] = "";
		array[10] = new Timestamp(Calendar.getInstance().getTimeInMillis());

		list.add(array);
		try {
			doReturn(list).when(epidemicOutbreakRepository).getEpidemicOutbreakComplaints(Mockito.anyLong());
			List<T_EpidemicOutbreak> list1 = epidemicOutbreakServiceImpl
					.getEpidemicOutbreakComplaints(Mockito.anyLong(), null, null, null);
			assertTrue(list1.toString().contains("\"requestID\":\"requestIDValue\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
