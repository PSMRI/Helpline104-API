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
package com.iemr.helpline104.controller.snomedct;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.iemr.helpline104.data.snomedct.SCTDescription;
import com.iemr.helpline104.service.snomedct.SnomedService;

@RunWith(MockitoJUnitRunner.class)
public class SnomedControllerTest {

	@InjectMocks
	SnomedController snomedController;
	
	@Mock
	SnomedService snomedService;
	
	@Test
	public void getSnomedCTRecordTest()
	{
		SCTDescription sctdescription =new SCTDescription();
		sctdescription.setCaseSignificanceID("101");
		List<SCTDescription> list=Lists.newArrayList();
		list.add(sctdescription);
		doReturn(list).when(snomedService).findSnomedCTRecordFromTerm(Mockito.anyString());
		String response=snomedController.getSnomedCTRecord(sctdescription.toString());
		assertTrue(response.contains("\"caseSignificanceID\":\"101\""));
	}
	
	@Test
	public void getSnomedCTRecordTest1()
	{
		SCTDescription sctdescription =new SCTDescription();
		sctdescription.setCaseSignificanceID("900000000000448009");
		List<SCTDescription> list=Lists.newArrayList();
		list.add(sctdescription);
		doReturn(list).when(snomedService).findSnomedCTRecordFromTerm(Mockito.anyString());
		String response=snomedController.getSnomedCTRecord(sctdescription.toString());
		assertTrue(response.contains("\"caseSignificanceID\":\"900000000000448009\""));
	}
	
	@Test
	public void getSnomedCTRecordTestException()
	{
		SCTDescription sctdescription =new SCTDescription();
		sctdescription.setCaseSignificanceID("900000000000448009");
		doThrow(Exception.class).when(snomedService).findSnomedCTRecordFromTerm(Mockito.anyString());
		String response=snomedController.getSnomedCTRecord(sctdescription.toString());
		assertFalse(response.contains("\"caseSignificanceID\""));
	}
	
	@Test
	public void getSnomedCTRecordsTest()
	{
		SCTDescription sctdescription =new SCTDescription();
		sctdescription.setCaseSignificanceID("101");
		List<SCTDescription> list=Lists.newArrayList();
		list.add(sctdescription);
		doReturn(list).when(snomedService).findSnomedCTRecordFromTerm(Mockito.anyString());
		String response=snomedController.getSnomedCTRecords(sctdescription.toString());
		assertTrue(response.contains("\"caseSignificanceID\":\"101\""));
	}
	
	@Test
	public void getSnomedCTRecordsExceptionTest()
	{
		SCTDescription sctdescription =new SCTDescription();
		sctdescription.setCaseSignificanceID("101");
		doThrow(Exception.class).when(snomedService).findSnomedCTRecordFromTerm(Mockito.anyString());
		String response=snomedController.getSnomedCTRecords(sctdescription.toString());
		assertFalse(response.contains("\"caseSignificanceID\""));
	}
}
