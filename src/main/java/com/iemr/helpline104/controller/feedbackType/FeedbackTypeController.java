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
package com.iemr.helpline104.controller.feedbackType;

import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.service.feedbackType.FeedbackTypeService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RequestMapping(value = "/beneficiary")
@RestController
public class FeedbackTypeController {

	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(FeedbackTypeController.class);

	@Autowired
	private FeedbackTypeService feedbackTypeService;

	
	@Operation(summary = "Provides nature of complaints")
	@PostMapping(value = "/get/natureOfComplaintTypes", headers = "Authorization")
	public String getNatureOfComplaintTypes(
			@Parameter(description = "{\"providerServiceMapID\":\"integer\",\"feedbackTypeID\":\"short\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			M_FeedbackType m_feedbackType = inputMapper.gson().fromJson(request, M_FeedbackType.class);
			logger.info("getNatureOfComplaintTypes request " + m_feedbackType.toString());

			List<M_FeedbackType> m_feedbackTypes = feedbackTypeService.getNatureOfComplaintTypes(
					m_feedbackType.getProviderServiceMapID(), m_feedbackType.getFeedbackTypeID());

			output.setResponse(m_feedbackTypes.toString());
		} catch (JSONException e) {
			logger.error("getNatureOfComplaintTypes failed with error " + e.getMessage(), e);
			output.setError(e);
		} catch (Exception e) {
			logger.error("getNatureOfComplaintTypes failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getNatureOfComplaintTypes response: " + output);
		return output.toString();
	}
}
