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
package com.iemr.helpline104.controller.scheme;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.iemr.helpline104.data.scheme.T_Schemeservice;
import com.iemr.helpline104.service.scheme.SchemeService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RequestMapping(value = "/beneficiary")
@RestController
public class SchemeController {
	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(SchemeController.class);

	@Autowired
	private SchemeService schemeService;

	InputMapper mapper = new InputMapper();

	
	@Operation(summary = "Save scheme search history")
	@PostMapping(value = "/save/schemeSearchHistory", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String saveSchemeSearchHistory(
			@Parameter(description = "[{\"beneficiaryRegID\":\"long\",\"benCallID\":\"long\",\"schemeID\":\"integer\","
					+ "\"providerServiceMapID\":\"integer\",\"remarks\":\"string\",\"createdBy\":\"string\"}]") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			T_Schemeservice[] schemeHistory = mapper.gson().fromJson(request, T_Schemeservice[].class);
			logger.info("saveSchemeSearchHistory request " + Arrays.toString(schemeHistory));

			String searchhistory = schemeService.saveSchemeSearchHistory(schemeHistory);

			output.setResponse(searchhistory);

		} catch (Exception e) {
			logger.error("saveSchemeSearchHistory failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("saveSchemeSearchHistory response: " + output);
		return output.toString();
	}

	
	@Operation(summary = "Retrieve scheme search history")
	@PostMapping(value = "/getSchemeSearchHistory", headers = "Authorization")
	public String getBenSchemeHistory(
			@Parameter(description = "{\"beneficiaryRegID\":\"optional long\",  \"benCallID\":\" Optional long\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {

			T_Schemeservice t_schemeservice = inputMapper.gson().fromJson(request, T_Schemeservice.class);
			logger.info("getSchemeSearchHistory request " + t_schemeservice.toString());

			List<T_Schemeservice> searchHistory = schemeService
					.getSchemeSearchHistory(t_schemeservice.getBeneficiaryRegID(), t_schemeservice.getBenCallID());
			output.setResponse(searchHistory.toString());
			logger.info("getSchemeSearchHistory response: " + output);
		} catch (Exception e) {
			logger.error("getSchemeSearchHistory failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}
}
