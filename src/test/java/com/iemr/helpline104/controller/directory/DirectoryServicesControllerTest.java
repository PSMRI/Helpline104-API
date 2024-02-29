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
package com.iemr.helpline104.controller.directory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.Lists;
import com.iemr.helpline104.data.directory.Directoryservice;
import com.iemr.helpline104.service.directory.DirectoryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DirectoryServicesControllerTest {

	@InjectMocks
	DirectoryServicesController directoryServicesController;
	
	@Mock
	DirectoryServiceImpl directoryServiceImpl;
	
	@Test
	public void getBenDirectoryHistoryTest()
	{
		Directoryservice directoryservice=new Directoryservice(new Long("101"),"institutionName","address","instituteDirectoryName","instituteSubDirectoryName");
		List<Directoryservice> list=Lists.newArrayList();
		list.add(directoryservice);
		doReturn(list).when(directoryServiceImpl).getDirectorySearchHistory(Mockito.anyLong(),Mockito.anyLong());
		String response=directoryServicesController.getBenDirectoryHistory(directoryservice.toString());
		assertTrue(response.contains("\"directoryServiceID\":101"));
	}
	@Test
	public void getBenDirectoryHistoryExceptionTest()
	{
		Directoryservice directoryservice=new Directoryservice(new Long("101"),"institutionName","address","instituteDirectoryName","instituteSubDirectoryName");
		doThrow(Exception.class).when(directoryServiceImpl).getDirectorySearchHistory(Mockito.anyLong(),Mockito.anyLong());
		String response=directoryServicesController.getBenDirectoryHistory(directoryservice.toString());
		assertFalse(response.contains("\"directoryServiceID\":101"));
	}
	@Test
	public void directorySearchHistoryTest()
	{
		Directoryservice directoryservice=new Directoryservice(new Long("101"),"institutionName","address","instituteDirectoryName","instituteSubDirectoryName");
		List<Directoryservice> list=Lists.newArrayList();
		list.add(directoryservice);
		doReturn(list.toString()).when(directoryServiceImpl).saveDirectorySearchHistory(Mockito.any(Directoryservice[].class));
		String response=directoryServicesController.directorySearchHistory(list.toString());
		assertTrue(response.contains("\"directoryServiceID\":101"));
	}
	@Test
	public void directorySearchHistoryExceptionTest()
	{
		Directoryservice directoryservice=new Directoryservice(new Long("101"),"institutionName","address","instituteDirectoryName","instituteSubDirectoryName");
		List<Directoryservice> list=Lists.newArrayList();
		list.add(directoryservice);
		doThrow(Exception.class).when(directoryServiceImpl).saveDirectorySearchHistory(Mockito.any(Directoryservice[].class));
		String response=directoryServicesController.directorySearchHistory(list.toString());
		assertFalse(response.contains("\"directoryServiceID\":101"));
	}
}
