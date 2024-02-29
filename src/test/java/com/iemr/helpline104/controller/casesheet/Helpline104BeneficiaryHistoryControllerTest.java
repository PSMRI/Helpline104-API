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
package com.iemr.helpline104.controller.casesheet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.Lists;
import com.iemr.helpline104.data.casesheet.H104BenMedHistory;
import com.iemr.helpline104.service.casesheet.H104BenHistoryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class Helpline104BeneficiaryHistoryControllerTest {

	@InjectMocks
	Helpline104BeneficiaryHistoryController smpleBenHistoryController;
	
	@Mock
	H104BenHistoryServiceImpl smpleBenHistoryServiceImpl;
	
	
	@Test
	public void saveBenCaseSheetTest()
	{
		/*H104BenMedHistory smpleBenHistory=new H104BenMedHistory();
		smpleBenHistory.setRequestID("101");
		doReturn(smpleBenHistory).when(smpleBenHistoryServiceImpl).saveSmpleBenHistory(Mockito.anyObject());
		String response=smpleBenHistoryController.saveBenCaseSheet(smpleBenHistory.toString());
		assertTrue(response.contains("\"requestID\":\"101\""));	*/
		
	}
	@Test
	public void saveBenCaseSheetExceptionTest()
	{
		/*H104BenMedHistory smpleBenHistory=new H104BenMedHistory();
		smpleBenHistory.setRequestID("101");
		doThrow(Exception.class).when(smpleBenHistoryServiceImpl).saveSmpleBenHistory(Mockito.anyObject());
		String response=smpleBenHistoryController.saveBenCaseSheet(smpleBenHistory.toString());
		assertFalse(response.contains("\"requestID\":\"101\""));*/	
		
	}
	

	@Test
	public void getBenCaseSheetTest1()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		doReturn(list).when(smpleBenHistoryServiceImpl).geSmpleBenHistory(Mockito.anyLong(),null);
		String response=smpleBenHistoryController.getBenCaseSheet("{\"beneficiaryRegID\":101}");
		assertFalse(response.contains("\"benHistoryID\":\"101\""));	
	}
	@Test
	public void getBenCaseSheetTest2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[15];
		array[0]=101;
		array[1]=102;
		list.add(array);
		
		doReturn(list).when(smpleBenHistoryServiceImpl).geSmpleBenHistory(Mockito.anyLong(),null);
		String response=smpleBenHistoryController.getBenCaseSheet("{\"beneficiaryRegID\":101}");
		assertTrue(response.contains("\"status\":\"Success\""));	
	}
}
