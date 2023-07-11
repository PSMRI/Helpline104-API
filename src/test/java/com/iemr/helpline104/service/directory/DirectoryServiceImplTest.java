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
package com.iemr.helpline104.service.directory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.iemr.helpline104.data.directory.Directory;
import com.iemr.helpline104.data.directory.Directoryservice;
import com.iemr.helpline104.repository.directory.DirectoryRepository;
import com.iemr.helpline104.repository.directory.DirectoryserviceRepository;

@RunWith(MockitoJUnitRunner.class)
public class DirectoryServiceImplTest {

	@InjectMocks
	DirectoryServiceImpl directoryServiceImpl;
	
	@Mock
	DirectoryRepository directoryRepository;
	
	@Mock
	DirectoryserviceRepository directoryserviceRepository;
	
	@Test
	public void getDirectorySearchHistory()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[5];
		array[0]=101L;
		array[1]="directory1";
		array[2]="directory2";
		array[3]="directory3";
		array[4]="directory4";
		
		list.add(array);
		try {
			doReturn(list).when(directoryserviceRepository).getBenSearchHistory(Mockito.anyLong());
			List<Directoryservice> directoryservice= directoryServiceImpl.getDirectorySearchHistory(Mockito.anyLong(),Mockito.anyLong());
			assertTrue(directoryservice.toString().contains("\"instituteDirectoryName\":\"directory3\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getDirectorySearchHistory2()
	{
		List<Directoryservice> list=directoryServiceImpl.getDirectorySearchHistory(Mockito.anyLong(),Mockito.anyLong());
		assertTrue(list.isEmpty());
	}
	@Test
	public void getDirectorySearchHistory1()
	{
		List<Objects[]> directoryServices=Lists.newArrayList();
		Objects[] array=new Objects[5];
		directoryServices.add(array);
		doReturn(directoryServices).when(directoryserviceRepository).getBenSearchHistory(Mockito.anyLong());
		List<Directoryservice> list=directoryServiceImpl.getDirectorySearchHistory(Mockito.anyLong(),Mockito.anyLong());
		assertTrue(list.toString().contains("\"beneficiaryRegID\":null"));
	}
	
	@Test
	public void saveDirectorySearchHistoryTest()
	{
		Directoryservice[] array=new Directoryservice[2];
		Directoryservice directoryservice=new Directoryservice(101L,"institutionName","address","instituteDirectoryName","instituteSubDirectoryName");
		array[0]=directoryservice;
		List<Directoryservice> list=Lists.newArrayList();
		list.add(directoryservice);
		 doReturn(list).when(directoryserviceRepository).save(Mockito.anyList());
		 String response=directoryServiceImpl.saveDirectorySearchHistory(array);
		 assertTrue(response.toString().contains("\"directoryServiceID\":101"));
		
		
	}
	
	@Test
	public void saveDirectorySearchHistoryTest1()
	{
		Directoryservice[] array=new Directoryservice[2];
		Directoryservice directoryservice=new Directoryservice(101L,"institutionName","address","instituteDirectoryName","instituteSubDirectoryName");
		array[0]=directoryservice;
		
		List<Directoryservice> list=Lists.newArrayList();
		 doReturn(list).when(directoryserviceRepository).save(Mockito.anyList());
		 String response=directoryServiceImpl.saveDirectorySearchHistory(array);
		 assertFalse(response.toString().contains("\"directoryServiceID\":101"));
		
		
	}
	
	@Test
	public void getDirectoriesTest()
	{
		List<Directory> list=directoryServiceImpl.getDirectories();
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void getDirectoriesTest1()
	{
		Set<Objects[]> directoryServices=Sets.newHashSet();
		Objects[] array=new Objects[5];
		directoryServices.add(array);
		doReturn(directoryServices).when(directoryRepository).findAciveDirectories();
		List<Directory> list=directoryServiceImpl.getDirectories();
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void getDirectoriesTest2()
	{
		Set<Object[]> set=Sets.newHashSet();
		Object[] array=new Object[2];
		array[0]=101;
		array[1]="directory";
		
		set.add(array);
		try {
			doReturn(set).when(directoryRepository).findAciveDirectories();
			List<Directory> directory= directoryServiceImpl.getDirectories();
			assertTrue(directory.toString().contains("\"instituteDirectoryName\":\"directory\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
