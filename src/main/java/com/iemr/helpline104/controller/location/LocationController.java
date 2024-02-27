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

import java.util.List;

import jakarta.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iemr.helpline104.data.location.DistrictBlock;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.location.States;
import com.iemr.helpline104.service.location.LocationService;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping(value = "/location")
@RestController
public class LocationController {
	private LocationService locationService;

	@CrossOrigin
	@Operation(summary = "Get states")
	@GetMapping(value = {
			"/states/{id}" }, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getStates(@PathVariable("id") int id) {
		List<States> stateList = this.locationService.getStates(id);
		return stateList.toString();
	}

	@CrossOrigin
	@Operation(summary = "Get districts")
	@GetMapping(value = {
			"/districts/{id}" }, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getDistricts(@PathVariable("id") int id) {
		List<Districts> districtsList = this.locationService.getDistricts(id);
		System.out.println(districtsList);
		return districtsList.toString();
	}

	@CrossOrigin
	@Operation(summary = "Get state districts")
	@GetMapping(value = {
			"/statesDistricts/{id}" }, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String geStatetDistricts(@PathVariable("id") int id) {
		List<Districts> districtsList = this.locationService.findStateDistrictBy(id);
		return districtsList.toString();
	}

	@CrossOrigin
	@Operation(summary = "Get district blocks")
	@GetMapping(value = {
			"/taluks/{id}" }, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getDistrictBlocks(@PathVariable("id") int id) {
		List<DistrictBlock> districtBlockList = this.locationService.getDistrictBlocks(id);
		return districtBlockList.toString();
	}

	@CrossOrigin
	@Operation(summary = "Get city")
	@GetMapping(value = {
			"/city/{id}" }, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getCity(@PathVariable("id") int id) {
		List<DistrictBlock> districtBlockList = this.locationService.getDistrictBlocks(id);
		return districtBlockList.toString();
	}

	@CrossOrigin
	@Operation(summary = "Get villages")
	@GetMapping(value = {
			"/village/{id}" }, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getVillages(@PathVariable("id") int id) {
		List<DistrictBlock> districtBlockList = this.locationService.getDistrictBlocks(id);
		return districtBlockList.toString();
	}

	@Autowired
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
}
