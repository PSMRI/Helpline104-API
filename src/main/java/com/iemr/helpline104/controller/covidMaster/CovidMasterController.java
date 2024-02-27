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
package com.iemr.helpline104.controller.covidMaster;

import java.util.List;

import jakarta.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iemr.helpline104.service.covidMaster.CovidMasterService;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping(value = "/master", headers = "Authorization")

public class CovidMasterController {

	private Logger logger = LoggerFactory.getLogger(CovidMasterController.class);
	@Autowired
	private CovidMasterService covidMasterService;

	@Operation(summary= "Master data  for COVID patient")
	@GetMapping(value = {
			"/patient/covidDetails/{providerServiceMapID}" }, produces = MediaType.APPLICATION_JSON)
	public String patientAppMasterData(@PathVariable("providerServiceMapID") Integer providerServiceMapID) {
		logger.info("master Data for beneficiary:");

		OutputResponse response = new OutputResponse();
		response.setResponse(covidMasterService.getMaster(providerServiceMapID));
		logger.info("Nurse master Data for beneficiary:" + response.toString());
		return response.toString();
	}

	@CrossOrigin
	@Operation(summary= "Save COVID data")
	@PostMapping({ "/save/covidScreeningData" })
	public String saveBenCovidDoctorData(@RequestBody String requestObj,
			@RequestHeader(value = "Authorization") String Authorization) {
		OutputResponse response = new OutputResponse();
		try {
			logger.info("Request object for Covid data saving :" + requestObj);

			String s = covidMasterService.saveCovidData(requestObj);

			response.setResponse(s);

		} catch (Exception e) {
			logger.error("Error while saving Covid data :" + e);
			response.setError(5000, "Unable to save data. " + e.getMessage());
		}
		return response.toString();
	}

}
