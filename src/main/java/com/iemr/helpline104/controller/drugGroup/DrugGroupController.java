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
package com.iemr.helpline104.controller.drugGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.iemr.helpline104.data.drugGroup.M_DrugGroup;
import com.iemr.helpline104.data.drugMapping.M_104drugmapping;
import com.iemr.helpline104.data.drugMaster.DrugFrequency;
import com.iemr.helpline104.data.drugMaster.DrugStrength;
import com.iemr.helpline104.service.drugGroup.DrugGroupService;
import com.iemr.helpline104.service.drugGroup.DrugGroupServiceImpl;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RequestMapping(value = "/beneficiary")
@RestController
public class DrugGroupController {

	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(DrugGroupController.class);

	@Autowired
	private DrugGroupService drugGroupService;

	
	@Operation(summary= "Fetch drug groups")
	@PostMapping(value = "/get/drugGroups", headers = "Authorization")
	public String getDrugGroups(@Parameter(description = "{\"serviceProviderID\":\"integer\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			M_DrugGroup m_DrugGroup = inputMapper.gson().fromJson(request, M_DrugGroup.class);

			List<M_DrugGroup> drugList = null;

			drugList = drugGroupService.getDrugGroups(m_DrugGroup.getServiceProviderID());

			output.setResponse(drugList.toString());
		} catch (Exception e) {
			logger.error("getDrugList failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getDrugList response: " + output);
		return output.toString();
	}

	
	@Operation(summary= "Fetch drug list")
	@PostMapping(value = "/get/drugList", headers = "Authorization")
	public String getDrugList(@Parameter(description = "{\"providerServiceMapID\":\"integer\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			M_104drugmapping m_104drugmapping = inputMapper.gson().fromJson(request, M_104drugmapping.class);
			logger.info("getDrugList request ");

			List<M_104drugmapping> drugList = null;

			drugList = drugGroupService.getDrugList(m_104drugmapping.getProviderServiceMapID(),
					m_104drugmapping.getDrugGroupID());

			output.setResponse(drugList.toString());
			logger.info(
					"getDrugList response size: " + ((drugList.size() > 0) ? drugList.size() : "No Beneficiary Found"));
		} catch (Exception e) {
			logger.error("getDrugList failed with error " + e.getMessage(), e);
			output.setError(e);
		}

		return output.toString();
	}

	
	@Operation(summary= "Fetch drug frequency details")
	@PostMapping(value = "/get/drugFrequency", headers = "Authorization")
	public String getDrugFrequency() {
		OutputResponse output = new OutputResponse();
		try {

			ArrayList<DrugFrequency> drugFrequency = drugGroupService.getDrugFrequency();
			output.setResponse(drugFrequency.toString());

		} catch (Exception e) {
			logger.error("getDrugFrequency failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getDrugFrequency response: " + output);
		return output.toString();
	}

	
	@Operation(summary= "Fetch drug strength details")
	@PostMapping(value = "/get/drugStrength", headers = "Authorization")
	public String getDrugStrength(
			@Parameter(description = "{\"serviceProviderID\":\"integer\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {

			M_DrugGroup m_DrugGroup = inputMapper.gson().fromJson(request, M_DrugGroup.class);

			ArrayList<DrugStrength> drugStrength = drugGroupService.getDrugStrength(m_DrugGroup.getServiceProviderID());
			
			output.setResponse(drugStrength.toString());

		} catch (Exception e) {
			logger.error("getDrugStrength failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getDrugStrength response: " + output);
		return output.toString();
	}

	
	@Operation(summary= "Fetch drug name list")
	@PostMapping(value = "/getDrugDetailList", headers = "Authorization")
	public String getDrugNameList(
			@Parameter(description = "{\"providerServiceMapID\":\"integer\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			M_104drugmapping m_104drugmapping = inputMapper.gson().fromJson(request, M_104drugmapping.class);
			logger.info("getDrugDetailList request ");

			List<M_104drugmapping> drugList = null;

			drugList = drugGroupService.getDrugDetailList(m_104drugmapping.getProviderServiceMapID());

			output.setResponse(drugList.toString());
		} catch (Exception e) {
			logger.error("getDrugDetailList failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}

}
