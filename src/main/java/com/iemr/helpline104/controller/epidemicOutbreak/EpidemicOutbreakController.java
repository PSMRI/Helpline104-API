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
package com.iemr.helpline104.controller.epidemicOutbreak;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.data.epidemicOutbreak.T_EpidemicOutbreak;
import com.iemr.helpline104.service.epidemicOutbreak.EpidemicOutbreakService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RequestMapping(value = "/beneficiary")
@RestController
public class EpidemicOutbreakController {
	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(EpidemicOutbreakController.class);

	@Autowired
	private EpidemicOutbreakService epidemicOutbreakService;

	@CrossOrigin
	@ApiOperation(value = "Store epidemic outbreak complaint", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/save/epidemicOutbreakComplaint", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String saveEpidemicOutbreakComplaint(
			@ApiParam(value = "{\"beneficiaryRegID\":\"long\",\"natureOfComplaint\":\"string\",\"totalPeopleAffected\":\"integer\",\"affectedDistrictID\":\"integer\","
					+ "\"affectedDistrictBlockID\":\"integer\",\"affectedCityID\":\"integer\",\"remarks\":\"string\",\"deleted\":\"boolean\",\"createdBy\":\"string\"}") @RequestBody String request,
			HttpServletRequest httpRequest) {
		OutputResponse output = new OutputResponse();
		try {
			T_EpidemicOutbreak t_epidemicOutbreak = inputMapper.gson().fromJson(request, T_EpidemicOutbreak.class);
			logger.info("saveEpidemicOutbreakComplaint request " + t_epidemicOutbreak.toString());

			T_EpidemicOutbreak epidemicOutbreakComplaint = epidemicOutbreakService.save(t_epidemicOutbreak,
					httpRequest);
			output.setResponse(epidemicOutbreakComplaint.toString());
			logger.info("saveEpidemicOutbreakComplaint response: " + output);
		} catch (Exception e) {
			logger.error("save/epidemicOutbreakComplaint failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}

	@CrossOrigin
	@ApiOperation(value = "Fetch epidemic outbreak complaints", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/get/epidemicOutbreakComplaint", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getEpidemicOutbreakComplaint(
			@ApiParam(value = "{\"beneficiaryRegID\":\"optional long\",  \"benCallID\":\" Optional long\",  \"requestID\":\" Optional string\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			T_EpidemicOutbreak t_epidemicOutbreak = inputMapper.gson().fromJson(request, T_EpidemicOutbreak.class);
			logger.info("getEpidemicOutbreakComplaint request " + t_epidemicOutbreak.toString());

			List<T_EpidemicOutbreak> epidemicOutbreakComplaint = null;

			epidemicOutbreakComplaint = epidemicOutbreakService.getEpidemicOutbreakComplaints(
					t_epidemicOutbreak.getBeneficiaryRegID(), t_epidemicOutbreak.getBenCallID(),
					t_epidemicOutbreak.getRequestID(), t_epidemicOutbreak.getPhoneNum());
			output.setResponse(epidemicOutbreakComplaint.toString());
			logger.info("getEpidemicOutbreakComplaint response size: "
					+ ((epidemicOutbreakComplaint.size() > 0) ? epidemicOutbreakComplaint.size()
							: "No Beneficiary Found"));
		} catch (Exception e) {
			logger.error("getEpidemicOutbreakComplaint failed with error " + e.getMessage(), e);
			output.setError(e);
		}

		return output.toString();
	}

	@CrossOrigin
	@ApiOperation(value = "Update epidemic outbreak complaint", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/update/epidemicOutbreakComplaint", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String updateEpidemicOutbreakComplaint(
			@ApiParam(value = "{\"natureOfComplaint\":\"string\",\"totalPeopleAffected\":\"integer\",\"affectedDistrictID\":\"integer\","
					+ "\"affectedDistrictBlockID\":\"integer\",\"affectedVillageID\":\"integer\",\"remarks\":\"string\"}") @RequestBody String request,
			HttpServletRequest httpRequest) {
		OutputResponse output = new OutputResponse();
		try {
			T_EpidemicOutbreak t_epidemicOutbreak = InputMapper.gson().fromJson(request, T_EpidemicOutbreak.class);
			logger.info("saveEpidemicOutbreakComplaint request " + t_epidemicOutbreak.toString());
			if (t_epidemicOutbreak != null && t_epidemicOutbreak.getRequestID() != null) {
				String result = epidemicOutbreakService.UpdateEpidemicOutbreakRequest(t_epidemicOutbreak);
				if (result != null)
					output.setResponse(result);
				else
					output.setError(5000, "Error in data update");
			} else
				output.setError(5000, "Invalid request. Request ID is mandatory to update details");
			logger.info("update epidemic outbreak response: " + output);
		} catch (Exception e) {
			logger.error("update epidemic outbreak failed with error " + e.getMessage());
			output.setError(5000, e.getMessage());
		}
		return output.toString();
	}

}
