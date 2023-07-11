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

import com.iemr.helpline104.data.location.DistrictBlock;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.location.States;
import com.iemr.helpline104.service.location.LocationService;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value =  "/location")
@RestController
public class LocationController {
	private LocationService locationService;

	@CrossOrigin
	@RequestMapping(value = { "/states/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getStates(@PathVariable("id") int id) {
		List<States> stateList = this.locationService.getStates(id);
		return stateList.toString();
	}

	@CrossOrigin
	@RequestMapping(value = { "/districts/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getDistricts(@PathVariable("id") int id) {
		List<Districts> districtsList = this.locationService.getDistricts(id);
		System.out.println(districtsList);
		return districtsList.toString();
	}


	@CrossOrigin
	@RequestMapping(value = {
			"/statesDistricts/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String geStatetDistricts(@PathVariable("id") int id) {
		List<Districts> districtsList = this.locationService.findStateDistrictBy(id);
		return districtsList.toString();
	}

	@CrossOrigin
	@RequestMapping(value = { "/taluks/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getDistrictBlocks(@PathVariable("id") int id) {
		List<DistrictBlock> districtBlockList = this.locationService.getDistrictBlocks(id);
		return districtBlockList.toString();
	}

	@CrossOrigin
	@RequestMapping(value = { "/city/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getCity(@PathVariable("id") int id) {
		List<DistrictBlock> districtBlockList = this.locationService.getDistrictBlocks(id);
		return districtBlockList.toString();
	}

	@CrossOrigin
	@RequestMapping(value = { "/village/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getVillages(@PathVariable("id") int id) {
		List<DistrictBlock> districtBlockList = this.locationService.getDistrictBlocks(id);
		return districtBlockList.toString();
	}

/*	@CrossOrigin
	@RequestMapping(value = { "/directories/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public String getDirectories(@PathVariable("id") int id) {
		List<DistrictBlock> districtBlockList = this.locationService.getDistrictBlocks(id);
		return districtBlockList.toString();
	}
*/
	@Autowired
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
}
