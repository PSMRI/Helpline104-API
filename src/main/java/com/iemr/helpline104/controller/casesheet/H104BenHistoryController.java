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
package com.iemr.helpline104.controller.casesheet;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.data.casesheet.COVIDHistory;
import com.iemr.helpline104.data.casesheet.H104BenMedHistory;
import com.iemr.helpline104.service.casesheet.H104BenHistoryServiceImpl;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RequestMapping(value = "/beneficiary")
@RestController
public class H104BenHistoryController {
	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(H104BenHistoryController.class);
	
	@Autowired
	private H104BenHistoryServiceImpl h104BenHistoryServiceImpl;
	
	@CrossOrigin
	@ApiOperation(
			value = "retrives cashesheet history",
			consumes = "application/json",
			produces = "application/json")
	@RequestMapping(value = "/getBenCaseSheet", method = RequestMethod.POST, headers = "Authorization")
	public String getBenCaseSheet(@ApiParam(
			value = "{\"beneficiaryRegID\":\"optional long\", \"benCallID\":\" Optional long\"}") @RequestBody String request) {
		OutputResponse output= new OutputResponse();
		try {
			
		H104BenMedHistory smpleBenreq = inputMapper.gson().fromJson(request,
				H104BenMedHistory.class);
		logger.info("getBenCaseSheet request " + smpleBenreq.toString());
		
		
	//	ArrayList<Objects[]> smpleBenHistory = h104BenHistoryServiceImpl.geSmpleBenHistory(smpleBenreq.getBeneficiaryRegID(), smpleBenreq.getBenCallID());
		List<H104BenMedHistory> smpleBenHistory = h104BenHistoryServiceImpl.geSmpleBenHistory(smpleBenreq.getBeneficiaryRegID(), smpleBenreq.getBenCallID());
		output.setResponse(smpleBenHistory.toString());
		logger.info("getBenCaseSheet response: " + output);
		} catch (Exception e) {
			logger.error("getBenCaseSheet failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}
		
	@CrossOrigin
	@ApiOperation(
			value = "stores case sheet data",
			consumes = "application/json",
			produces = "application/json")
	@RequestMapping(value = "/save/benCaseSheet", method = RequestMethod.POST, headers = "Authorization")
	public String saveBenCaseSheet(@ApiParam(
			value = "{\"algorithm\":\"string\",\"diseaseSummary\":\"string\",\"addedAdvice\":\"string\",\"prescription\":\"string\",\"actionByHAO\":\"string\","
					+ "\"actionByCO\":\"string\",\"actionByMO\":\"string\",\"remarks\":\"string\",\"deleted\":\"boolean\",\"createdBy\":\"string\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
		H104BenMedHistory smpleBenHistory = inputMapper.gson().fromJson(request,
				H104BenMedHistory.class);
		logger.info("addBeneficiaryRelation request " + smpleBenHistory.toString());
		COVIDHistory covidHistory = inputMapper.gson().fromJson(request,
				COVIDHistory.class);
		
		H104BenMedHistory benmedhistory = h104BenHistoryServiceImpl.saveSmpleBenHistory(smpleBenHistory, covidHistory);
		
		if(benmedhistory.getActionByCO() != null || benmedhistory.getActionByPD() != null)
		{
		String requestID = "MH/"+benmedhistory.getDistrictID()+"/"+new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTimeInMillis())+"/"+benmedhistory.getBenHistoryID();	     	    
		benmedhistory.setRequestID(requestID);
		benmedhistory = h104BenHistoryServiceImpl.saveSmpleBenHistory(benmedhistory,covidHistory);
		}
		
		
		output.setResponse(benmedhistory.toString());
		logger.info("addBeneficiaryRelation response: " + output);
		}catch (Exception e) {
			logger.error("save/benCaseSheet failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}
	
	@CrossOrigin
	@ApiOperation(
			value = "retrives present cashesheet",
			consumes = "application/json",
			produces = "application/json")
	@RequestMapping(value = "/getPresentCaseSheet", method = RequestMethod.POST, headers = "Authorization")
	public String getPresentCaseSheet(@ApiParam(
			value = "{\"beneficiaryRegID\":\"long\",\"callID\":\"String\"}") @RequestBody String request) {
		OutputResponse output= new OutputResponse();
		try {
			
		H104BenMedHistory smpleBenreq = inputMapper.gson().fromJson(request,
				H104BenMedHistory.class);
		logger.info("getPresentCaseSheet request " + smpleBenreq.toString());
		
		
		List<H104BenMedHistory> smpleBenHistory = h104BenHistoryServiceImpl.getPresentCasesheet(smpleBenreq.getBeneficiaryRegID(),smpleBenreq.getCallID().trim());
		output.setResponse(smpleBenHistory.toString());
		logger.info("getPresentCaseSheet response: " + output);
		} catch (Exception e) {
			logger.error("getPresentCaseSheet failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}	
	
	
	
}

