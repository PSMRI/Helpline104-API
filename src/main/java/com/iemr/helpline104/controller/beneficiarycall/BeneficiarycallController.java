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

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.data.beneficiarycall.BenCallServicesMappingHistory;
import com.iemr.helpline104.data.beneficiarycall.BeneficiaryCall;
import com.iemr.helpline104.data.beneficiarycall.M_subservice;
import com.iemr.helpline104.service.beneficiarycall.BeneficiaryCallService;
import com.iemr.helpline104.service.beneficiarycall.ServicesHistoryService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/beneficiary")
public class BeneficiarycallController {
	InputMapper inputMapper = new InputMapper();

	private Logger logger = LoggerFactory.getLogger(BeneficiarycallController.class);
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
	@ApiOperation(value = "Stores callerID to the specific beneficiary who are on call", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/startCall", method = RequestMethod.POST, headers = "Authorization")
	public String startCall(
			@ApiParam(value = "{\"callClosureType\":\"string\", \"callID\":\"integer\", \"sessionID\":\"integer\", \"calledServiceID\":\"integer\","
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
    /*
	@CrossOrigin()
	@ApiOperation(value = "Retrieves beneficiary details by provided callerId", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "get/beneficiaryByCallID", method = RequestMethod.POST, headers = "Authorization")
	private String findBeneficiaryByCallID(
			@ApiParam(value = "{\"callID\":\"long\", \"pageNo\":\"integer\", \"rowsPerPage\":\"integer\"}") @RequestBody String beneficiaryCall) {
		logger.info("findBeneficiaryByCallID request " + beneficiaryCall.toString());
		OutputResponse output = new OutputResponse();
		List<BeneficiaryCall> startedCall = null;
		try {
			JSONObject requestObject = new JSONObject(beneficiaryCall);
			String benCallID = requestObject.getString("callID");
			int pageNumber = requestObject.has("pageNo") ? (requestObject.getInt("pageNo") - 1) : 0;
			int rows = requestObject.has("rowsPerPage") ? requestObject.getInt("rowsPerPage") : 1000;

			startedCall = beneficiaryCallService.findBeneficiaryByCallID(benCallID, pageNumber, rows);
			if (startedCall.size() > 0) {
				output.setResponse(startedCall.get(startedCall.size()-1).toString());
			} else {
				output.setResponse(startedCall.toString());
			}
		} catch (Exception e) {
			logger.error("findBeneficiaryByCallID failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("findBeneficiaryByCallID response: " + output);
		return output.toString();
	} */

	@CrossOrigin()
	@ApiOperation(value = "Update beneficiaryRegId to the callerId", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "update/beneficiaryCallID", method = RequestMethod.POST, headers = "Authorization")
	public String updateBeneficiaryIDInCall(
			@ApiParam(value = "{\"callID\":\"integer\", \"beneficiaryRegID\":\"long\"}") @RequestBody String beneficiaryCall) {
		logger.info("updateBeneficiaryIDInCall request " + beneficiaryCall.toString());
		OutputResponse output = new OutputResponse();
		Integer startedCall = null;
		try {
			BeneficiaryCall beneficiarycall = inputMapper.gson().fromJson(beneficiaryCall, BeneficiaryCall.class);

			startedCall = beneficiaryCallService.updateBeneficiaryIDInCall(beneficiarycall.getBenCallID(),
					beneficiarycall.getBeneficiaryRegID());
			output.setResponse(startedCall.toString());
		} catch (Exception e) {
			logger.error("updateBeneficiaryIDInCall failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("updateBeneficiaryIDInCall response: " + output);
		return output.toString();
	}

	@CrossOrigin
	@ApiOperation(value = "Fetch services available in the 104 helpline", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/get/services", method = RequestMethod.POST, headers = "Authorization")
	public String getServices(
			@ApiParam(value = "{\"providerServiceMapID\":\"integer\"}") @RequestBody String subservice) {
		OutputResponse output = new OutputResponse();
		try {
			M_subservice m_subservice = inputMapper.gson().fromJson(subservice, M_subservice.class);
			logger.info("getServices request ");

			List<Objects[]> services = null;

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
	@RequestMapping(value = "set/callHistory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
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
	
	//Created on 27/10/2020 for getting logs of Calls that get closed automatically while caller is on live call
	@Deprecated
    @CrossOrigin
    @ApiOperation(value = "Save Call Disconnected Data..", consumes = "application/json", produces = "application/json")
    @RequestMapping(value = { "/save/callDisconnectedData" }, method = { RequestMethod.POST })
    public String saveBenCovidDoctorData(@RequestBody String requestObj,
            @RequestHeader(value = "Authorization") String Authorization) {
        OutputResponse response = new OutputResponse();
        try {
            logger.info("Request object for Call Disconnected data :" + requestObj);
            String s = servicesHistoryService.saveCallDisconnectedData(requestObj);
            response.setResponse(s);

        } catch (Exception e) {
            logger.error("Error while saving Call Disconnected data :" + e);
            response.setError(5000, "Unable to save data. " + e.getMessage());
        }
        
        return response.toString();
    }

}
