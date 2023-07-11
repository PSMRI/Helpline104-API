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
package com.iemr.helpline104.controller.bloodRequest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.data.bloodComponentType.M_BloodGroup;
import com.iemr.helpline104.data.bloodComponentType.M_ComponentType;
import com.iemr.helpline104.data.bloodRequest.BloodBank;

import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;
import com.iemr.helpline104.service.bloodComponentType.BloodComponentTypeServiceImpl;
import com.iemr.helpline104.service.bloodRequest.BloodRequestServiceImpl;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RequestMapping(value = "/beneficiary")
@RestController
public class BloodRequestController {

	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(BloodRequestController.class);

	@Autowired
	private BloodRequestServiceImpl bloodRequestServiceImpl;

	@Autowired
	private BloodComponentTypeServiceImpl componentTypeServiceImpl;

	@CrossOrigin
	@RequestMapping(value = "/save/bloodRequestDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String saveBloodRequestDetails(@RequestBody String request) {

		OutputResponse output = new OutputResponse();
		try {
		T_BloodRequest t_bloodRequest = inputMapper.gson().fromJson(request, T_BloodRequest.class);
		logger.info("saveBloodRequestDetails request " + t_bloodRequest.toString());
		

		T_BloodRequest bloodRequest = null;
		
			bloodRequest = bloodRequestServiceImpl.save(t_bloodRequest);
			output.setResponse(bloodRequest.toString());
		} catch (Exception e) {
			logger.error("saveBloodRequestDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("saveBloodRequestDetails response: " + output);
		return output.toString();
	}

	@CrossOrigin
	@RequestMapping(value = "/get/bloodRequestDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getbloodRequestDetails(
			@ApiParam(value = "{\"beneficiaryRegID\":\" Optional long\",\"requestID\":\" Optional string\", \"benCallID\":\" Optional long\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
		T_BloodRequest t_bloodRequest = inputMapper.gson().fromJson(request, T_BloodRequest.class);
		logger.info("getbloodRequestDetails request " + t_bloodRequest.toString());
		

		List<T_BloodRequest> bloodRequest = null;
		
			bloodRequest = bloodRequestServiceImpl.getBloodRequest(t_bloodRequest.getBeneficiaryRegID(),
					t_bloodRequest.getRequestID(), t_bloodRequest.getBenCallID());
			output.setResponse(bloodRequest.toString());
			logger.info("getbloodRequestDetails response size: " + ((bloodRequest.size()>0) ? bloodRequest.size() : "No Beneficiary Found"));
		} catch (Exception e) {
			logger.error("getbloodRequestDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		
		return output.toString();
	}

	@CrossOrigin
	@RequestMapping(value = "/get/bloodComponentTypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getBloodComponentTypes() {
		logger.info("getBloodComponentTypes request ");
		OutputResponse output = new OutputResponse();
		List<M_ComponentType> bloodComponentTypes = null;
		try {
			bloodComponentTypes = componentTypeServiceImpl.getBloodComponentTypes();
			output.setResponse(bloodComponentTypes.toString());
		} catch (Exception e) {
			logger.error("getBloodComponentTypes failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getBloodComponentTypes response: " + output);
		return output.toString();
	}

	@CrossOrigin
	@RequestMapping(value = "/get/bloodGroups", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getBloodGroups() {
		logger.info("getBloodGroups request ");
		OutputResponse output = new OutputResponse();
		List<M_BloodGroup> bloodGroups = null;
		try {
			bloodGroups = componentTypeServiceImpl.getBloodGroups();
			output.setResponse(bloodGroups.toString());
		} catch (Exception e) {
			logger.error("getBloodGroups failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getBloodGroups response: " + output);
		return output.toString();
	}
    /*
	@CrossOrigin
	@ApiOperation(value = "Stores Blood Outbound call Details", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/save/bloodBankDetails", method = RequestMethod.POST, headers = "Authorization")
	public String saveBloodBankDetails(
			@ApiParam(value = "{\"bloodReqID\":\"integer\", \"bloodBankaddress\":\"string\",\"bloodBankPersonName\":\"string\", \"bloodBankMobileNo\":\"string\","
					+ "\"bBPersonDesignation\":\"string\", \"sendSMS\":\"boolean\", \"callType\":\"string\", \"isRequestFulfilled\":\"boolean\",\"callSubType\":\"string\", "
					+ "\"remarks\":\"string\", \"feedback\":\"string\", \"deleted\":\"boolean\",\"createdBy\":\"string\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
		T_BloodOutboundDetails t_bloodOutboundDetails = inputMapper.gson().fromJson(request,
				T_BloodOutboundDetails.class);
		logger.info("saveBloodBankDetails request " + t_bloodOutboundDetails.toString());
		

		T_BloodOutboundDetails bloodOutboundDetails = null;
		
			bloodOutboundDetails = bloodRequestServiceImpl.save(t_bloodOutboundDetails);
			output.setResponse(bloodOutboundDetails.toString());
		} catch (Exception e) {
			logger.error("saveBloodBankDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("saveBloodBankDetails response: " + output);
		return output.toString();
	} 

	@CrossOrigin
	@ApiOperation(value = "update cZentrixCallerId in  BloodOutbound call Details", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/update/bloodBankDetails", method = RequestMethod.POST, headers = "Authorization")
	public String updateBloodBankDetails(
			@ApiParam(value = "{\"iD\":\"long\", \"czentrixCallID\":\"string\"}") @RequestBody String request) {
		
		OutputResponse output = new OutputResponse();
		try {
		T_BloodOutboundDetails t_bloodOutboundDetails = inputMapper.gson().fromJson(request,
				T_BloodOutboundDetails.class);
		logger.info("updateBloodBankDetails request " + t_bloodOutboundDetails.toString());
		

		Integer updateResponse = null;
		
			String response;
			updateResponse = bloodRequestServiceImpl.updateBloodOutBoundDetails(t_bloodOutboundDetails);
			if (updateResponse == 1) {
				response = "cZentrixCallerId added successfully";
			} else {
				response = "Failed to add cZentrixCallerId";
			}
			output.setResponse(response);
		} catch (Exception e) {
			logger.error("updateBloodBankDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("updateBloodBankDetails response: " + output);
		return output.toString();
	}  
	
	@CrossOrigin
	@ApiOperation(value = "get bloodBankDetails", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/get/bloodBankDetails", method = RequestMethod.POST, headers = "Authorization")
	public String getBloodBankDetails(
			@ApiParam(value = "{\"bloodOutboundDetailID\":\"long\", \"bloodReqID\":\"integer\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
		T_BloodOutboundDetails t_bloodOutboundDetails = inputMapper.gson().fromJson(request,
				T_BloodOutboundDetails.class);
		logger.info("getBloodBankDetails request " + t_bloodOutboundDetails.toString());
		

		List<T_BloodOutboundDetails> bloodOutboundDetails = null;
		
			bloodOutboundDetails = bloodRequestServiceImpl.getBloodOutBoundDetails(t_bloodOutboundDetails);
			
			output.setResponse(bloodOutboundDetails.toString());
		} catch (Exception e) {
			logger.error("getBloodBankDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getBloodBankDetails response: " + output);
		return output.toString();
	}  */
	
	
	@CrossOrigin
	@ApiOperation(value = "Fetch  the blood bank URL", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/get/bloodBankURL", method = RequestMethod.POST, headers = "Authorization")
	public String getBloodBankURL(
			@ApiParam(value = "{\"providerServiceMapID\":\"integer\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			
		/*	BloodBank bloodBank = inputMapper.gson().fromJson(request,
					BloodBank.class); */		
		

			BloodBank bloodBank  = null;
		
			bloodBank = bloodRequestServiceImpl.getBloodBankURL("104BloodBank");
			
			if(bloodBank == null)			
			output.setResponse("Blood bank URL is not configured");
			else
			output.setResponse(bloodBank.toString());
			
		} catch (Exception e) {
			logger.error("getBloodBankURL failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getBloodBankURL response: " + output);
		return output.toString();
	}
	
	@CrossOrigin
	@ApiOperation(value = "Save blood bank URL", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/save/bloodBankURL", method = RequestMethod.POST, headers = "Authorization")
	public String saveBloodBankURL(
			@ApiParam(value = "{\"institutionID\":\"integer - Optional. mandatory for update\",\"providerServiceMapID\":\"integer\", \"website\":\"string\", \"createdBy\":\"string\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			
			BloodBank bloodBank = inputMapper.gson().fromJson(request,
					BloodBank.class); 
			bloodBank.setInstitutionName("104BloodBank");
		
			bloodBank = bloodRequestServiceImpl.saveBloodBankURL(bloodBank);
			
			
			output.setResponse(bloodBank.toString());
			
		} catch (Exception e) {
			logger.error("saveBloodBankURL failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("saveBloodBankURL response: " + output);
		return output.toString();
	}


}
