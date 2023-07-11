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
package com.iemr.helpline104.service.snomedct;

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

import com.iemr.helpline104.data.snomedct.SCTDescription;
import com.iemr.helpline104.repository.snomedct.SnomedRepository;

@RunWith(MockitoJUnitRunner.class)
public class SnomedServiceImplTest {
	
	@InjectMocks
	SnomedServiceImpl snomedServiceImpl;
	
	@Mock
	SnomedRepository snomedRepository;
	
	@Test
	public void findSnomedCTRecordFromTermTest()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[20];
		array[0]="sigID";
		array[1]="term";
		
		list.add(array);
		try {
			doReturn(list).when(snomedRepository).findSnomedCTRecordFromTerm(Mockito.anyString());
			List<SCTDescription> sCTDescription= snomedServiceImpl.findSnomedCTRecordFromTerm(Mockito.anyString());
			SCTDescription description=sCTDescription.get(0);
			assertTrue(description.getTerm().equals("term"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void findSnomedCTRecordFromTermTest1()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		try {
			doReturn(list).when(snomedRepository).findSnomedCTRecordFromTerm(Mockito.anyString());
			List<SCTDescription> sCTDescription= snomedServiceImpl.findSnomedCTRecordFromTerm(Mockito.anyString());
			assertFalse(!sCTDescription.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
