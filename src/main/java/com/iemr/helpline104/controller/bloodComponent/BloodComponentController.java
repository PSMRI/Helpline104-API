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
package com.iemr.helpline104.controller.bloodComponent;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.iemr.helpline104.data.bloodComponent.M_Component;
import com.iemr.helpline104.service.bloodComponent.BloodComponentService;
import com.iemr.helpline104.service.bloodComponent.BloodComponentServiceImpl;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RequestMapping(value = "/beneficiary")
@RestController
public class BloodComponentController {

	private InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(BloodComponentController.class);

	@Autowired
	private BloodComponentService bloodComponentService;

	@Operation(summary = "Save blood component details")
	@PostMapping(value = "/save/bloodComponentDetails", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String saveBloodComponentDetails(
			@Parameter(description = "{\"component\":\"String\",\"componentDesc\":\"String\",\"deleted\":\"boolean\",\"createdBy\":\"String\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			M_Component m_component = inputMapper.gson().fromJson(request, M_Component.class);
			logger.info("saveBloodComponentDetails request " + m_component.toString());

			M_Component bloodComponent = bloodComponentService.save(m_component);
			output.setResponse(bloodComponent.toString());
			logger.info("saveBloodComponentDetails response: " + output);
		} catch (Exception e) {
			logger.error("saveBloodComponentDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}

	@Operation(summary = "Fetch blood component details")
	@PostMapping(value = "/get/bloodComponentDetails", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getBloodComponentDetails(
			@Parameter(description = "{\"componentID\":\"Integer\"}") @RequestBody String request) {

		OutputResponse output = new OutputResponse();
		try {
			M_Component m_component = inputMapper.gson().fromJson(request, M_Component.class);
			logger.info("getBloodComponentDetails request " + m_component.toString());

			List<M_Component> bloodComponent = null;

			bloodComponent = bloodComponentService.getBloodComponents(m_component.getComponentID());
			output.setResponse(bloodComponent.toString());
			logger.info("getBloodComponentDetails response size: "
					+ ((bloodComponent.size() > 0) ? bloodComponent.size() : "No Beneficiary Found"));
		} catch (Exception e) {
			logger.error("getBloodComponentDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}

		return output.toString();
	}

}
