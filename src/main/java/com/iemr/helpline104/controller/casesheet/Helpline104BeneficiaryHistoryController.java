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
import org.springframework.web.bind.annotation.*;
import com.iemr.helpline104.data.casesheet.COVIDHistory;
import com.iemr.helpline104.data.casesheet.H104BenMedHistory;
import com.iemr.helpline104.service.casesheet.H104BenHistoryService;
import com.iemr.helpline104.service.casesheet.H104BenHistoryServiceImpl;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RequestMapping(value = "/beneficiary")
@RestController
public class Helpline104BeneficiaryHistoryController {
	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(Helpline104BeneficiaryHistoryController.class);

	@Autowired
	private H104BenHistoryService h104BenHistoryService;

	
	@Operation(summary = "Retrieves case record")
	@PostMapping(value = "/getBenCaseSheet", headers = "Authorization")
	public String getBenCaseSheet(
			@Parameter(description = "{\"beneficiaryRegID\":\"optional long\", \"benCallID\":\" Optional long\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {

			H104BenMedHistory smpleBenreq = inputMapper.gson().fromJson(request, H104BenMedHistory.class);
			logger.info("getBenCaseSheet request " + smpleBenreq.toString());

			List<H104BenMedHistory> smpleBenHistory = h104BenHistoryService
					.geSmpleBenHistory(smpleBenreq.getBeneficiaryRegID(), smpleBenreq.getBenCallID());
			output.setResponse(smpleBenHistory.toString());
			logger.info("getBenCaseSheet response: " + output);
		} catch (Exception e) {
			logger.error("getBenCaseSheet failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}

	
	@Operation(summary = "Stores case record")
	@PostMapping(value = "/save/benCaseSheet", headers = "Authorization")
	public String saveBenCaseSheet(
			@Parameter(description = "{\"algorithm\":\"string\",\"diseaseSummary\":\"string\",\"addedAdvice\":\"string\",\"prescription\":\"string\",\"actionByHAO\":\"string\","
					+ "\"actionByCO\":\"string\",\"actionByMO\":\"string\",\"remarks\":\"string\",\"deleted\":\"boolean\",\"createdBy\":\"string\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			H104BenMedHistory smpleBenHistory = inputMapper.gson().fromJson(request, H104BenMedHistory.class);
			logger.info("addBeneficiaryRelation request " + smpleBenHistory.toString());
			COVIDHistory covidHistory = inputMapper.gson().fromJson(request, COVIDHistory.class);

			H104BenMedHistory benmedhistory = h104BenHistoryService.saveSmpleBenHistory(smpleBenHistory, covidHistory);

			if (benmedhistory.getActionByCO() != null || benmedhistory.getActionByPD() != null) {
				String requestID = "MH/" + benmedhistory.getDistrictID() + "/"
						+ new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTimeInMillis()) + "/"
						+ benmedhistory.getBenHistoryID();
				benmedhistory.setRequestID(requestID);
				benmedhistory = h104BenHistoryService.saveSmpleBenHistory(benmedhistory, covidHistory);
			}

			output.setResponse(benmedhistory.toString());
			logger.info("addBeneficiaryRelation response: " + output);
		} catch (Exception e) {
			logger.error("save/benCaseSheet failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}

	
	@Operation(summary = "Retrieves present case record")
	@PostMapping(value = "/getPresentCaseSheet", headers = "Authorization")
	public String getPresentCaseSheet(
			@Parameter(description = "{\"beneficiaryRegID\":\"long\",\"callID\":\"String\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {

			H104BenMedHistory smpleBenreq = inputMapper.gson().fromJson(request, H104BenMedHistory.class);
			logger.info("getPresentCaseSheet request " + smpleBenreq.toString());

			List<H104BenMedHistory> smpleBenHistory = h104BenHistoryService
					.getPresentCasesheet(smpleBenreq.getBeneficiaryRegID(), smpleBenreq.getCallID().trim());
			output.setResponse(smpleBenHistory.toString());
			logger.info("getPresentCaseSheet response: " + output);
		} catch (Exception e) {
			logger.error("getPresentCaseSheet failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}

}
