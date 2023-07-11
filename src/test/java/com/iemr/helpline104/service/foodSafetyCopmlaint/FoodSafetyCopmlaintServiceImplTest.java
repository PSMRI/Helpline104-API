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
package com.iemr.helpline104.service.foodSafetyCopmlaint;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.iemr.helpline104.data.foodSafetyCopmlaint.T_FoodSafetyCopmlaint;
import com.iemr.helpline104.repository.feedbackType.FeedbackTypeRepository;
import com.iemr.helpline104.repository.foodSafetyCopmlaint.FoodSafetyCopmlaintRepository;

@RunWith(MockitoJUnitRunner.class)
public class FoodSafetyCopmlaintServiceImplTest {

	@InjectMocks
	FoodSafetyCopmlaintServiceImpl foodSafetyCopmlaintServiceImpl;

	@Mock
	FoodSafetyCopmlaintRepository foodSafetyCopmlaintRepository;

	@Mock
	FeedbackTypeRepository feedbackRepositorty;

	@Test
	public void getFoodSafetyComplaints() {
		List<Objects[]> list = Lists.newArrayList();
		Objects[] array = new Objects[22];
		list.add(array);
		try {
			doReturn(list).when(foodSafetyCopmlaintRepository).getFoodSafetyRequests(Mockito.anyLong());
			List<T_FoodSafetyCopmlaint> foodSafetyComplaintsList = foodSafetyCopmlaintServiceImpl
					.getFoodSafetyComplaints(Mockito.anyLong(), null, null, null);
			assertFalse(foodSafetyComplaintsList.toString().contains("fSComplaintID"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void getFoodSafetyComplaints1() {
		try {
			List<T_FoodSafetyCopmlaint> foodSafetyComplaintsList = foodSafetyCopmlaintServiceImpl
					.getFoodSafetyComplaints(Mockito.anyLong(), null, null, null);
			assertTrue(foodSafetyComplaintsList.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void getFoodSafetyComplaints2() {
		ArrayList<Object[]> list = Lists.newArrayList();
		Object[] array = new Object[21];
		Byte b = 13;
		Short s = 15;
		array[0] = 101L;
		array[1] = "100";
		array[2] = 102L;
		array[3] = "";
		array[4] = b;
		array[5] = b;
		array[6] = b;
		array[7] = b;
		array[8] = b;
		array[9] = b;
		array[10] = b;
		array[11] = new Timestamp(Calendar.getInstance().getTimeInMillis());
		array[12] = "remark";
		array[13] = b;
		array[14] = "";
		array[15] = "";
		array[16] = "";
		array[17] = s;
		array[18] = "feedback";
		array[19] = "remark";
		array[20] = new Date(Calendar.getInstance().getTimeInMillis());
		;

		list.add(array);
		try {
			doReturn(list).when(foodSafetyCopmlaintRepository).getFoodSafetyRequests(Mockito.anyLong());
			List<T_FoodSafetyCopmlaint> foodSafetyComplaintsList = foodSafetyCopmlaintServiceImpl
					.getFoodSafetyComplaints(Mockito.anyLong(), null, null, null);
			assertTrue(foodSafetyComplaintsList.toString().contains("\"requestID\":\"100\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
