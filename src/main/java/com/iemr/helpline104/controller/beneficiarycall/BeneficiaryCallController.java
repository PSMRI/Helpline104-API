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
package com.iemr.helpline104.controller.beneficiarycall;

import java.util.List;
import java.util.Objects;

import jakarta.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iemr.helpline104.data.beneficiarycall.BenCallServicesMappingHistory;
import com.iemr.helpline104.data.beneficiarycall.BeneficiaryCall;
import com.iemr.helpline104.data.beneficiarycall.M_subservice;
import com.iemr.helpline104.service.beneficiarycall.BeneficiaryCallService;
import com.iemr.helpline104.service.beneficiarycall.ServicesHistoryService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(value = "/beneficiary")
public class BeneficiaryCallController {
	InputMapper inputMapper = new InputMapper();

	private Logger logger = LoggerFactory.getLogger(BeneficiaryCallController.class);
	private ServicesHistoryService servicesHistoryService;
	private BeneficiaryCallService beneficiaryCallService;

	@Autowired
	public void setService1097HistoryService(ServicesHistoryService servicesHistoryService) {

		this.servicesHistoryService = servicesHistoryService;
	}

	@Autowired
	public void setBeneficiaryCallService(BeneficiaryCallService beneficiaryCallService) {

		this.beneficiaryCallService = beneficiaryCallService;
	}

	@CrossOrigin()
	@Operation(summary = "Stores callerID to the specific beneficiary who are on call")
	@PostMapping(value = "/startCall", headers = "Authorization")
	public String startCall(
			@Parameter(description = "{\"callClosureType\":\"string\", \"callID\":\"integer\", \"sessionID\":\"integer\", \"calledServiceID\":\"integer\","
					+ " \"category\":\"string\", \"subCategory\":\"string\", \"servicesProvided\":\"string\", \"createdBy\":\"string\"}") @RequestBody String request) {

		OutputResponse output = new OutputResponse();
		try {
			BeneficiaryCall beneficiaryCall = inputMapper.gson().fromJson(request, BeneficiaryCall.class);
			logger.info("searchUserByParameters request " + beneficiaryCall.toString());
			BeneficiaryCall startedCall = beneficiaryCallService.createCall(beneficiaryCall);
			output.setResponse(startedCall.toString());
		} catch (Exception e) {
			logger.error("startCall failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("startCall response: " + output);
		return output.toString();
	}

	@CrossOrigin()
	@Operation(summary = "Update beneficiary reg id to the caller id")
	@PostMapping(value = "update/beneficiaryCallID", headers = "Authorization")
	public String updateBeneficiaryIDInCall(
	        @Parameter(description = "{\"callID\":\"integer\", \"beneficiaryRegID\":\"long\"}") @RequestBody String beneficiaryCall) {
	   
	    OutputResponse output = new OutputResponse();
	    Integer startedCall = null;
	    try {
	        BeneficiaryCall beneficiarycall = inputMapper.gson().fromJson(beneficiaryCall, BeneficiaryCall.class);

	        startedCall = beneficiaryCallService.updateBeneficiaryIDInCall(beneficiarycall.getBenCallID(),
	                beneficiarycall.getBeneficiaryRegID());
	        output.setResponse(startedCall.toString());

	        logger.info("updateBeneficiaryIDInCall was called successfully");
	    } catch (Exception e) {
	        logger.error("updateBeneficiaryIDInCall failed with error " + e.getMessage(), e);
	        output.setError(e);

	    }

	    logger.info("updateBeneficiaryIDInCall completed");

	    return output.toString();
	}


	@CrossOrigin
	@Operation(summary = "Fetch services available in the 104 helpline")
	@PostMapping(value = "/get/services", headers = "Authorization")
	public String getServices(
			@Parameter(description = "{\"providerServiceMapID\":\"integer\"}") @RequestBody String subservice) {
		OutputResponse output = new OutputResponse();
		try {
			M_subservice m_subservice = inputMapper.gson().fromJson(subservice, M_subservice.class);
			logger.info("getServices request ");

			List<Object[]> services = null;

			services = servicesHistoryService.getServices(m_subservice.getProviderServiceMapID());
			output.setResponse(services.toString());
		} catch (Exception e) {
			logger.error("getServices failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getServices response: " + output);
		return output.toString();
	}

	@CrossOrigin()
	@Operation(summary = "Set service history")
	@PostMapping(value = "set/callHistory", produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String setServiceHistory(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
			BenCallServicesMappingHistory serviceHistoryDetails = inputMapper.gson().fromJson(request,
					BenCallServicesMappingHistory.class);
			BenCallServicesMappingHistory savedObj = servicesHistoryService.createServiceHistory(serviceHistoryDetails);
			response.setResponse(savedObj.toString());
		} catch (Exception e) {
			logger.error("", e);
			response.setError(e);
		}
		return response.toString();
	}
}