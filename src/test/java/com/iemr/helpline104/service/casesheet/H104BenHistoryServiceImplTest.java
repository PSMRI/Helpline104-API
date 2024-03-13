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
package com.iemr.helpline104.service.casesheet;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.Lists;
import com.iemr.helpline104.data.casesheet.H104BenMedHistory;
import com.iemr.helpline104.repository.casesheet.H104BenHistoryRepository;

@ExtendWith(MockitoExtension.class)
public class H104BenHistoryServiceImplTest {

	@InjectMocks
	H104BenHistoryServiceImpl smpleBenHistoryServiceImpl;
	
	@Mock
	H104BenHistoryRepository smpleBenHistoryRepositoryRepository;
	

	@Test
	public void geSmpleBenHistoryTest() {
		
		ArrayList<Object[]> list=Lists.newArrayList();

		doReturn(list).when(smpleBenHistoryRepositoryRepository).getBenHistory(Mockito.anyLong());
		List<H104BenMedHistory> list1=smpleBenHistoryServiceImpl.geSmpleBenHistory(Mockito.anyLong(),null);
		assertTrue(list1.isEmpty());
	}
	
	@Test
	public void saveSmpleBenHistoryTest1()
	{
	/*	H104BenMedHistory smpleBenHistory =new H104BenMedHistory();
		smpleBenHistory.setRequestID("101");
		doReturn(smpleBenHistory).when(smpleBenHistoryRepositoryRepository).save(smpleBenHistory);
		H104BenMedHistory smpleBenHistory2=smpleBenHistoryServiceImpl.saveSmpleBenHistory(smpleBenHistory);
		assertTrue(smpleBenHistory2.toString().contains("\"requestID\":\"101\""));*/
	}
	
	@Test
	public void saveSmpleBenHistoryTest2()
	{
		/*H104BenMedHistory smpleBenHistory =new H104BenMedHistory();
		smpleBenHistory.setRequestID("101");
		H104BenMedHistory smpleBenHistory2=smpleBenHistoryServiceImpl.saveSmpleBenHistory(smpleBenHistory);
		assertTrue(smpleBenHistory2==null);*/
	}
}
