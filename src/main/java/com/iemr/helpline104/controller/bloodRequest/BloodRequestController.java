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
import org.springframework.web.bind.annotation.*;
import com.iemr.helpline104.data.bloodComponentType.M_BloodGroup;
import com.iemr.helpline104.data.bloodComponentType.M_ComponentType;
import com.iemr.helpline104.data.bloodRequest.BloodBank;
import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;
import com.iemr.helpline104.service.bloodComponentType.BloodComponentTypeService;
import com.iemr.helpline104.service.bloodComponentType.BloodComponentTypeServiceImpl;
import com.iemr.helpline104.service.bloodRequest.BloodRequestService;
import com.iemr.helpline104.service.bloodRequest.BloodRequestServiceImpl;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RequestMapping(value = "/beneficiary")
@RestController
public class BloodRequestController {

	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(BloodRequestController.class);

	@Autowired
	private BloodRequestService bloodRequestService;

	@Autowired
	private BloodComponentTypeService componentTypeService;

	@Operation(summary = "Save blood request details")
	@PostMapping(value = "/save/bloodRequestDetails", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String saveBloodRequestDetails(@RequestBody String request) {

		OutputResponse output = new OutputResponse();
		try {
			T_BloodRequest t_bloodRequest = inputMapper.gson().fromJson(request, T_BloodRequest.class);
			logger.info("saveBloodRequestDetails request " + t_bloodRequest.toString());

			T_BloodRequest bloodRequest = null;

			bloodRequest = bloodRequestService.save(t_bloodRequest);
			output.setResponse(bloodRequest.toString());
		} catch (Exception e) {
			logger.error("saveBloodRequestDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("saveBloodRequestDetails response: " + output);
		return output.toString();
	}

	@Operation(summary = "Get blood request details")
	@PostMapping(value = "/get/bloodRequestDetails", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getbloodRequestDetails(
			@Parameter(description = "{\"beneficiaryRegID\":\" Optional long\",\"requestID\":\" Optional string\", \"benCallID\":\" Optional long\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			T_BloodRequest t_bloodRequest = inputMapper.gson().fromJson(request, T_BloodRequest.class);
			logger.info("getbloodRequestDetails request " + t_bloodRequest.toString());

			List<T_BloodRequest> bloodRequest = null;

			bloodRequest = bloodRequestService.getBloodRequest(t_bloodRequest.getBeneficiaryRegID(),
					t_bloodRequest.getRequestID(), t_bloodRequest.getBenCallID());
			output.setResponse(bloodRequest.toString());
			logger.info("getbloodRequestDetails response size: "
					+ ((bloodRequest.size() > 0) ? bloodRequest.size() : "No Beneficiary Found"));
		} catch (Exception e) {
			logger.error("getbloodRequestDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}

		return output.toString();
	}

	@Operation(summary = "Get blood component types")
	@PostMapping(value = "/get/bloodComponentTypes", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getBloodComponentTypes() {
		logger.info("getBloodComponentTypes request ");
		OutputResponse output = new OutputResponse();
		List<M_ComponentType> bloodComponentTypes = null;
		try {
			bloodComponentTypes = componentTypeService.getBloodComponentTypes();
			output.setResponse(bloodComponentTypes.toString());
		} catch (Exception e) {
			logger.error("getBloodComponentTypes failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getBloodComponentTypes response: " + output);
		return output.toString();
	}

	@Operation(summary = "Get blood groups")
	@PostMapping(value = "/get/bloodGroups", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getBloodGroups() {
		logger.info("getBloodGroups request ");
		OutputResponse output = new OutputResponse();
		List<M_BloodGroup> bloodGroups = null;
		try {
			bloodGroups = componentTypeService.getBloodGroups();
			output.setResponse(bloodGroups.toString());
		} catch (Exception e) {
			logger.error("getBloodGroups failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getBloodGroups response: " + output);
		return output.toString();
	}

	@Operation(summary = "Get blood bank URL")
	@PostMapping(value = "/get/bloodBankURL", headers = "Authorization")
	public String getBloodBankURL(
			@Parameter(description = "{\"providerServiceMapID\":\"integer\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {

			BloodBank bloodBank = null;

			bloodBank = bloodRequestService.getBloodBankURL("104BloodBank");

			if (bloodBank == null)
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

	@Operation(summary = "Save blood bank URL")
	@PostMapping(value = "/save/bloodBankURL", headers = "Authorization")
	public String saveBloodBankURL(
			@Parameter(description = "{\"institutionID\":\"integer - Optional. mandatory for update\",\"providerServiceMapID\":\"integer\", \"website\":\"string\", \"createdBy\":\"string\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {

			BloodBank bloodBank = inputMapper.gson().fromJson(request, BloodBank.class);
			bloodBank.setInstitutionName("104BloodBank");

			bloodBank = bloodRequestService.saveBloodBankURL(bloodBank);

			output.setResponse(bloodBank.toString());

		} catch (Exception e) {
			logger.error("saveBloodBankURL failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("saveBloodBankURL response: " + output);
		return output.toString();
	}

}
