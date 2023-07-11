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
package com.iemr.helpline104.service.location;

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

import com.iemr.helpline104.data.location.CityDetails;
import com.iemr.helpline104.data.location.DistrictBlock;
import com.iemr.helpline104.data.location.DistrictBranchMapping;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.location.States;
import com.iemr.helpline104.repository.location.LocationDistrictBlockRepository;
import com.iemr.helpline104.repository.location.LocationDistrictRepository;
import com.iemr.helpline104.repository.location.LocationStateRepository;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceImplTest {
	
	@InjectMocks
	LocationServiceImpl locationServiceImpl;
	
	@Mock
	LocationStateRepository locationStateRepository;
	
	@Mock
	LocationDistrictRepository locationDistrictRepository;
	
	@Mock
	LocationDistrictBlockRepository locationDistrictBlockRepository;
	
	@Test
	public void getStatesTest()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[9];
		array[0]=101;
		array[1]="stateName";
		list.add(array);
		doReturn(list).when(locationStateRepository).findBy(Mockito.anyInt());
		List<States> states= locationServiceImpl.getStates(Mockito.anyInt());
		States state=states.get(0);
		assertTrue(state.getStateID()==101);
		
	}
	
	@Test
	public void getStatesTest1()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		doReturn(list).when(locationStateRepository).findBy(Mockito.anyInt());
		List<States> list1= locationServiceImpl.getStates(Mockito.anyInt());
		assertFalse(!list1.isEmpty());
		
	}
	@Test
	public void getStatesTest2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[2];
		array[0]=101;
		array[1]="Aasam";

		list.add(array);
		try {
			doReturn(list).when(locationStateRepository).findBy(Mockito.anyInt());
			List<States> states= locationServiceImpl.getStates(Mockito.anyInt());
			assertTrue(states.toString().contains("\"stateName\":\"Aasam\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void getDistrictsTest()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[9];
		array[0]=101;
		array[1]="districtName";
		list.add(array);
		doReturn(list).when(locationDistrictRepository).findBy(Mockito.anyInt());
		List<Districts> list1= locationServiceImpl.getDistricts(Mockito.anyInt());
		Districts districts=list1.get(0);
		assertTrue(districts.getDistrictID() ==101);
	}
	
	@Test
	public void getDistrictsTest1()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		doReturn(list).when(locationDistrictRepository).findBy(Mockito.anyInt());
		List<Districts> list1= locationServiceImpl.getDistricts(Mockito.anyInt());
		assertFalse(!list1.isEmpty());
	}
	@Test
	public void getDistrictsTest2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[2];
		array[0]=101;
		array[1]="Bangalore";

		list.add(array);
		try {
			doReturn(list).when(locationDistrictRepository).findBy(Mockito.anyInt());
			List<Districts> list1= locationServiceImpl.getDistricts(Mockito.anyInt());
			assertTrue(list1.toString().contains("\"districtName\":\"Bangalore\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getDistrictBlocksTest()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[9];
		array[0]=101;
		array[1]="districtBlockName";
		list.add(array);
		doReturn(list).when(locationDistrictBlockRepository).findBy(Mockito.anyInt());
		List<DistrictBlock> list1= locationServiceImpl.getDistrictBlocks(Mockito.anyInt());
		DistrictBlock districtBlock=list1.get(0);
		assertTrue(districtBlock.getBlockID() ==101);
	}
	
	@Test
	public void getDistrictBlocksTest1()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		doReturn(list).when(locationDistrictBlockRepository).findBy(Mockito.anyInt());
		List<DistrictBlock> list1= locationServiceImpl.getDistrictBlocks(Mockito.anyInt());
		assertFalse(!list1.isEmpty());
	}
	@Test
	public void getDistrictBlocksTest2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[2];
		array[0]=101;
		array[1]="Kormangala";

		list.add(array);
		try {
			doReturn(list).when(locationDistrictBlockRepository).findBy(Mockito.anyInt());
			List<DistrictBlock> list1= locationServiceImpl.getDistrictBlocks(Mockito.anyInt());
			assertTrue(list1.toString().contains("\"blockName\":\"Kormangala\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void findStateDistrictByTest()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[9];
		array[0]=101;
		array[1]="districtName";
		array[3]=102;
		array[2]="stateName";
		list.add(array);
		doReturn(list).when(locationDistrictRepository).findStateDistrictBy(Mockito.anyInt());
		 List<Districts> list1= locationServiceImpl.findStateDistrictBy(Mockito.anyInt());
		 Districts district=list1.get(0);
		assertTrue(district.getDistrictID() ==101);
	}
	
	@Test
	public void findStateDistrictByTest1()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		doReturn(list).when(locationDistrictRepository).findStateDistrictBy(Mockito.anyInt());
		 List<Districts> list1= locationServiceImpl.findStateDistrictBy(Mockito.anyInt());
		 assertFalse(!list1.isEmpty());
	}
	@Test
	public void findStateDistrictByTest2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[4];
		array[0]=101;
		array[1]="Kormangala";
		array[2]="Karnataka";
		array[3]=101;
		
		list.add(array);
		try {
			doReturn(list).when(locationDistrictRepository).findStateDistrictBy(Mockito.anyInt());
			 List<Districts> list1= locationServiceImpl.findStateDistrictBy(Mockito.anyInt());
			assertTrue(list1.toString().contains("\"stateName\":\"Karnataka\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getCitiesTest()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[9];
		array[0]=101;
		array[1]="cityName";
		list.add(array);
		doReturn(list).when(locationDistrictBlockRepository).findBy(Mockito.anyInt());
		 List<CityDetails> list1= locationServiceImpl.getCities(Mockito.anyInt());
		 CityDetails cityDetails=list1.get(0);
		assertTrue(cityDetails.getCityID() ==101);
	}
	
	@Test
	public void getCitiesTest1()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		doReturn(list).when(locationDistrictBlockRepository).findBy(Mockito.anyInt());
		 List<CityDetails> list1= locationServiceImpl.getCities(Mockito.anyInt());
		 assertFalse(!list1.isEmpty());
	}
	@Test
	public void getCitiesTest2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[2];
		array[0]=101;
		array[1]="Manglore";
		
		list.add(array);
		try {
			doReturn(list).when(locationDistrictBlockRepository).findBy(Mockito.anyInt());
			 List<CityDetails> list1= locationServiceImpl.getCities(Mockito.anyInt());
			assertTrue(list1.toString().contains("\"cityName\":\"Manglore\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getDistrilctBranchsTest()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[9];
		array[0]=101;
		array[1]="cityName";
		list.add(array);
		doReturn(list).when(locationDistrictBlockRepository).findBy(Mockito.anyInt());
		List<DistrictBranchMapping> list1= locationServiceImpl.getDistrilctBranchs(Mockito.anyInt());
		DistrictBranchMapping districtBranchMapping=list1.get(0);
		assertTrue(districtBranchMapping.getDistrictBranchID() ==101);
	}
	
	@Test
	public void getDistrilctBranchsTest1()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		doReturn(list).when(locationDistrictBlockRepository).findBy(Mockito.anyInt());
		List<DistrictBranchMapping> list1= locationServiceImpl.getDistrilctBranchs(Mockito.anyInt());
		assertFalse(!list1.isEmpty());
	}
	@Test
	public void getDistrilctBranchsTest2()
	{
		ArrayList<Object[]> list=Lists.newArrayList();
		Object[] array=new Object[2];
		array[0]=101;
		array[1]="AgaraVillage";
		
		list.add(array);
		try {
			doReturn(list).when(locationDistrictBlockRepository).findBy(Mockito.anyInt());
			List<DistrictBranchMapping> list1= locationServiceImpl.getDistrilctBranchs(Mockito.anyInt());
			assertTrue(list1.toString().contains("\"villageName\":\"AgaraVillage\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
