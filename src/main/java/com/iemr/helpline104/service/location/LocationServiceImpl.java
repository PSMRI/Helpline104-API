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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.location.CityDetails;
import com.iemr.helpline104.data.location.DistrictBlock;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.location.DistrictBranchMapping;
import com.iemr.helpline104.data.location.States;
import com.iemr.helpline104.repository.location.LocationCityRepository;
import com.iemr.helpline104.repository.location.LocationDistrictBlockRepository;
import com.iemr.helpline104.repository.location.LocationDistrictRepository;
import com.iemr.helpline104.repository.location.LocationDistrilctBranchRepository;
import com.iemr.helpline104.repository.location.LocationStateRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Override
	public List<States> getStates(int id) {
		List<States> stateList = new ArrayList<States>();
		ArrayList<Objects[]> lists = locationStateRepository.findBy(id);
	
		for (Object[] objects : lists) {
			if (objects!=null && objects.length > 0) {
				stateList.add(new States((Integer)objects[0], (String)objects[1]));
			}
		}
		
		return stateList;
	}
	
	@Override
	public List<Districts> getDistricts(int id) {

		List<Districts> districtList = new ArrayList<Districts>();
		Iterable<Objects[]> lists = locationDistrictRepository.findBy(id);
	
		for (Object[] objects : lists) {
			if (objects!=null && objects.length > 0) {
				districtList.add(new Districts((Integer)objects[0], (String)objects[1]));
			}
		}
		
		return districtList;
	}
	
	@Override
	public List<DistrictBlock> getDistrictBlocks(int id) {
		
		List<DistrictBlock> districtBlockList = new ArrayList<DistrictBlock>();
		ArrayList<Objects[]> lists = locationDistrictBlockRepository.findBy(id);
	
		for (Object[] objects : lists) {
			if (objects!=null && objects.length > 0) {
				districtBlockList.add(new DistrictBlock((Integer)objects[0], (String)objects[1]));
			}
		}
		
		return districtBlockList;
	}

	/**
	 * Stete repository
	 */
	private LocationStateRepository locationStateRepository;
	
	/**
	 * 
	 * @param LocationStateRepository
	 */
	@Autowired(required = false)
	public void setLocationStateRepository(LocationStateRepository locationStateRepository){
		
		this.locationStateRepository = locationStateRepository;
	}
	
	/**
	 * Districts Repository
	 */
	private LocationDistrictRepository locationDistrictRepository;
	
	@Autowired(required = false)
	public void setLocationDistrictRepository(LocationDistrictRepository locationDistrictRepository){
		
		this.locationDistrictRepository = locationDistrictRepository;
	}
	
	/**
	 * District block repository
	 */
	private LocationDistrictBlockRepository locationDistrictBlockRepository;
	
	@Autowired(required = false)
	public void setLocationDistrictBlockRepository(LocationDistrictBlockRepository locationDistrictBlockRepository){
		
		this.locationDistrictBlockRepository = locationDistrictBlockRepository;
	}

	@Override
	public List<Districts> findStateDistrictBy(int id) {
		List<Districts> stateDistrictList = new ArrayList<Districts>();
		ArrayList<Objects[]> lists = locationDistrictRepository.findStateDistrictBy(id);
	
		for (Object[] objects : lists) {
			if (objects!=null && objects.length > 3) {
				System.out.println(objects);
				stateDistrictList.add(new Districts((Integer)objects[0], (String)objects[1], (Integer)objects[3], (String)objects[2]));
			}
		}
		
		return stateDistrictList;
	}	
	@Override
	public List<CityDetails> getCities(int id) {
		
		List<CityDetails> cityList = new ArrayList<CityDetails>();
		ArrayList<Objects[]> lists = locationDistrictBlockRepository.findBy(id);
	
		for (Object[] objects : lists) {
			if (objects!=null && objects.length > 0) {
				cityList.add(new CityDetails((Integer)objects[0], (String)objects[1]));
			}
		}
		
		return cityList;
	}

	@Override
	public List<DistrictBranchMapping> getDistrilctBranchs(int id) {
		
		List<DistrictBranchMapping> distrilctBranchsList = new ArrayList<DistrictBranchMapping>();
		ArrayList<Objects[]> lists = locationDistrictBlockRepository.findBy(id);
	
		for (Object[] objects : lists) {
			if (objects!=null && objects.length > 0) {
				distrilctBranchsList.add(new DistrictBranchMapping((Integer)objects[0], (String)objects[1]));
			}
		}
		
		return distrilctBranchsList;
	}

	
	private LocationCityRepository locationCityRepository;
	
	@Autowired(required = false)
	public void setLocationCityRepository(LocationCityRepository locationCityRepository){
		
		this.locationCityRepository = locationCityRepository;
	}
	
	private LocationDistrilctBranchRepository locationDistrilctBranchRepository;
	
	@Autowired(required = false)
	public void setLocationDistrilctBranchRepository(LocationDistrilctBranchRepository locationDistrilctBranchRepository){
		
		this.locationDistrilctBranchRepository = locationDistrilctBranchRepository;
	}
}
