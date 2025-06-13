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
package com.iemr.helpline104.controller.hihl;

import jakarta.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iemr.helpline104.service.hihl.HIHLMasters;
import com.iemr.helpline104.service.hihl.HIHLMastersImpl;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/hihl", headers = "Authorization")
public class HIHLController {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private HIHLMasters hIHLMasters;

	@Operation(summary = "Master data for 104 HIHL")
	@GetMapping(value = "/get/masters", produces = MediaType.APPLICATION_JSON)
	public String getHihlMasters() {

		OutputResponse response = new OutputResponse();
		try {
			response.setResponse(hIHLMasters.getHihlMasters());
		} catch (Exception e) {
			response.setError(5000, e.getLocalizedMessage());
		}

		return response.toString();
	}

	@Operation(summary = "Save HIHL casesheet")
	@PostMapping(value = "/save/casesheet", produces = MediaType.APPLICATION_JSON)
	public String saveHihlCasesheet(@RequestBody String request) {

		OutputResponse response = new OutputResponse();
		try {
			response.setResponse(hIHLMasters.saveHihlCasesheet(request));
		} catch (Exception e) {
			response.setError(5000, e.getLocalizedMessage());
		}

		return response.toString();
	}

	@Operation(summary = "Get HIHL casesheet history info")
	@GetMapping(value = "/getHihlCasesheetHistoryInfo/{benRegId}", produces = MediaType.APPLICATION_JSON)
	public String getHihlCasesheetHistoryInfo(@PathVariable Long benRegId) {

		OutputResponse response = new OutputResponse();
		try {
			response.setResponse(hIHLMasters.getHihlCasesheetHistoryInfo(benRegId));
		} catch (Exception e) {
			response.setError(5000, e.getLocalizedMessage());
		}
		return response.toString();
	}

	@Operation(summary = "Get HIHL casesheet data")
	@GetMapping(value = "/getHihlCasesheetData/{casesheetId}", produces = MediaType.APPLICATION_JSON)
	public String getHihlCasesheetData(@PathVariable Long casesheetId) {

		OutputResponse response = new OutputResponse();
		try {
			response.setResponse(hIHLMasters.getHihlCasesheetHistoryInfo(casesheetId));
		} catch (Exception e) {
			response.setError(5000, e.getLocalizedMessage());
		}

		return response.toString();
	}
}
