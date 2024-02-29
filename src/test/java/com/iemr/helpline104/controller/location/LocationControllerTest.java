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
package com.iemr.helpline104.controller.location;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.location.DistrictBlock;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.location.States;
import com.iemr.helpline104.service.location.LocationService;

@ExtendWith(MockitoExtension.class)
public class LocationControllerTest {

	@InjectMocks
	LocationController locationController;
	
	@Mock
	LocationService locationService;
	
	@Test
	public void getStatesTest()
	{
		List<States> stateList=Lists.newArrayList();
		States states=new States();
		states.setStateID(101);
		stateList.add(states);
		doReturn(stateList).when(locationService).getStates(Mockito.anyInt());
		String response=locationController.getStates(Mockito.anyInt());
		assertTrue(response.contains("\"stateID\":101"));
	}
	
	@Test
	public void getDistrictsTest()
	{
		List<Districts> districtsList=Lists.newArrayList();
		Districts districts=new Districts();
		districts.setDistrictID(101);
		districtsList.add(districts);
		doReturn(districtsList).when(locationService).getDistricts(Mockito.anyInt());
		String response=locationController.getDistricts(Mockito.anyInt());
		assertTrue(response.contains("\"districtID\":101"));
	}
	
	@Test
	public void geStatetDistrictsTest()
	{
		List<Districts> districtsList=Lists.newArrayList();
		Districts districts=new Districts();
		districts.setDistrictID(101);
		districtsList.add(districts);
		doReturn(districtsList).when(locationService).findStateDistrictBy(Mockito.anyInt());
		String response=locationController.geStatetDistricts(Mockito.anyInt());
		assertTrue(response.contains("\"districtID\":101"));
	}
	
	@Test
	public void getDistrictBlocksTest()
	{
		List<DistrictBlock> districtBlockList=Lists.newArrayList();
		DistrictBlock districtBlock=new DistrictBlock();
		districtBlock.setBlockID(101);
		districtBlockList.add(districtBlock);
		doReturn(districtBlockList).when(locationService).getDistrictBlocks(Mockito.anyInt());
		String response=locationController.getDistrictBlocks(Mockito.anyInt());
		assertTrue(response.contains("\"blockID\":101"));
	}
	
	@Test
	public void getCityTest()
	{
		List<DistrictBlock> districtBlockList=Lists.newArrayList();
		DistrictBlock districtBlock=new DistrictBlock();
		districtBlock.setBlockID(101);
		districtBlockList.add(districtBlock);
		doReturn(districtBlockList).when(locationService).getDistrictBlocks(Mockito.anyInt());
		String response=locationController.getCity(Mockito.anyInt());
		assertTrue(response.contains("\"blockID\":101"));
	}
	
	@Test
	public void getVillagesTest()
	{
		List<DistrictBlock> districtBlockList=Lists.newArrayList();
		DistrictBlock districtBlock=new DistrictBlock();
		districtBlock.setBlockID(101);
		districtBlockList.add(districtBlock);
		doReturn(districtBlockList).when(locationService).getDistrictBlocks(Mockito.anyInt());
		String response=locationController.getVillages(Mockito.anyInt());
		assertTrue(response.contains("\"blockID\":101"));
	}
}
