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
package com.iemr.helpline104.controller.healthCareWorkerType;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.data.healthCareWorkerType.M_HealthCareWorker;
import com.iemr.helpline104.service.healthCareWorkerType.HealthCareWorkerService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;


@RequestMapping(value = "/beneficiary")
@RestController
public class HealthCareWorkerTypeController {
	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(HealthCareWorkerTypeController.class);

	@Autowired
	private HealthCareWorkerService healthCareWorkerService;

	@Operation(summary = "Get health care worker types")
	@PostMapping(value = "/get/healthCareWorkerTypes", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getHealthCareWorkerTypes() {
		logger.info("getHealthCareWorkerTypes request ");
		OutputResponse output = new OutputResponse();

		List<M_HealthCareWorker> i_BeneficiaryTypes = healthCareWorkerService.getHealthCareWorkerTypes();
		output.setResponse(i_BeneficiaryTypes.toString());
		logger.info("getHealthCareWorkerTypes response: " + output);
		return output.toString();
	}
}
