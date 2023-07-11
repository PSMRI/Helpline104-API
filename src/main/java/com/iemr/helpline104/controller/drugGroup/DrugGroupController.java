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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.data.drugGroup.M_DrugGroup;
import com.iemr.helpline104.data.drugMapping.M_104drugmapping;
import com.iemr.helpline104.repository.drugGroup.DrugGroupRepository;
import com.iemr.helpline104.service.drugGroup.DrugGroupServiceImpl;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RequestMapping(value = "/beneficiary")
@RestController
public class DrugGroupController {

	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(DrugGroupController.class);
	
	@Autowired
	private DrugGroupServiceImpl drugGroupServiceImpl;
	
	
	@CrossOrigin
	@ApiOperation(
			value = "provides drug list",
			consumes = "application/json",
			produces = "application/json")
	@RequestMapping(value = "/get/drugGroups", method = RequestMethod.POST, headers = "Authorization")
	public String getDrugGroups(@ApiParam(
			value = "{\"serviceProviderID\":\"integer\"}") @RequestBody String request) {
		OutputResponse output= new OutputResponse();
		try {
		M_DrugGroup m_DrugGroup = inputMapper.gson().fromJson(request, M_DrugGroup.class);		
		
		List<M_DrugGroup> drugList = null;
		
			drugList = drugGroupServiceImpl.getDrugGroups(m_DrugGroup.getServiceProviderID());
			
			output.setResponse(drugList.toString());
		} catch (Exception e) {
			logger.error("getDrugList failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getDrugList response: " + output);
		return output.toString();
	}
	
	
	
	@CrossOrigin
	@ApiOperation(
			value = "provides drug list",
			consumes = "application/json",
			produces = "application/json")
	@RequestMapping(value = "/get/drugList", method = RequestMethod.POST, headers = "Authorization")
	public String getDrugList(@ApiParam(
			value = "{\"providerServiceMapID\":\"integer\"}") @RequestBody String request) {
		OutputResponse output= new OutputResponse();
		try {
		M_104drugmapping m_104drugmapping = inputMapper.gson().fromJson(request, M_104drugmapping.class);
		logger.info("getDrugList request ");
		
		List<M_104drugmapping> drugList = null;
		
			drugList = drugGroupServiceImpl.getDrugList(m_104drugmapping.getProviderServiceMapID(),m_104drugmapping.getDrugGroupID());
			
			output.setResponse(drugList.toString());
			logger.info("getDrugList response size: " + ((drugList.size()>0) ? drugList.size() : "No Beneficiary Found"));
		} catch (Exception e) {
			logger.error("getDrugList failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		
		return output.toString();
	}	
	
	@CrossOrigin
	@ApiOperation(
			value = "provides drug frequency details",
			consumes = "application/json",
			produces = "application/json")
	@RequestMapping(value = "/get/drugFrequency", method = RequestMethod.POST, headers = "Authorization")
	public String getDrugFrequency() {
		OutputResponse output= new OutputResponse();
		try {		
		
			ArrayList<Objects[]> drugFrequency= drugGroupServiceImpl.getDrugFrequency();			
			output.setResponse(drugFrequency.toString());
			
		} catch (Exception e) {
			logger.error("getDrugFrequency failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getDrugFrequency response: " + output);
		return output.toString();
	}	
	
	@CrossOrigin
	@ApiOperation(
			value = "provides drug frequency details",
			consumes = "application/json",
			produces = "application/json")
	@RequestMapping(value = "/get/drugStrength", method = RequestMethod.POST, headers = "Authorization")
	public String getDrugStrength(@ApiParam(
			value = "{\"serviceProviderID\":\"integer\"}") @RequestBody String request) {
		OutputResponse output= new OutputResponse();
		try {
			
			M_DrugGroup m_DrugGroup = inputMapper.gson().fromJson(request, M_DrugGroup.class);	
		
			ArrayList<Objects[]> drugStrength = drugGroupServiceImpl.getDrugStrength(m_DrugGroup.getServiceProviderID());			
			output.setResponse(drugStrength.toString());
			
		} catch (Exception e) {
			logger.error("getDrugStrength failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getDrugStrength response: " + output);
		return output.toString();
	}	
	

	@CrossOrigin
	@ApiOperation(value = "provides drug list",consumes = "application/json",produces = "application/json")
	@RequestMapping(value = "/getDrugDetailList", method = RequestMethod.POST, headers = "Authorization")
	public String getDrugNameList(@ApiParam(
			value = "{\"providerServiceMapID\":\"integer\"}") @RequestBody String request) {
		OutputResponse output= new OutputResponse();
		try {
		M_104drugmapping m_104drugmapping = inputMapper.gson().fromJson(request, M_104drugmapping.class);
		logger.info("getDrugDetailList request ");
		
		List<M_104drugmapping> drugList = null;
		
			drugList = drugGroupServiceImpl.getDrugDetailList(m_104drugmapping.getProviderServiceMapID());
			
			output.setResponse(drugList.toString());
		} catch (Exception e) {
			logger.error("getDrugDetailList failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		//logger.info("getDrugDetailList response: " + output);
		return output.toString();
	}	
	
}
