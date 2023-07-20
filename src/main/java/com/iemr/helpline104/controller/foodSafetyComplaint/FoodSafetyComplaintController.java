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
package com.iemr.helpline104.controller.foodSafetyComplaint;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.data.foodSafetyCopmlaint.T_FoodSafetyCopmlaint;
import com.iemr.helpline104.service.foodSafetyCopmlaint.FoodSafetyCopmlaintService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RequestMapping(value = "/beneficiary")
@RestController
public class FoodSafetyComplaintController {
	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(FoodSafetyComplaintController.class);

	@Autowired
	private FoodSafetyCopmlaintService foodSafetyCopmlaintService;

	@CrossOrigin
	@ApiOperation(value = "Stores food safety complaint details", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/save/foodComplaintDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String saveFoodComplaintDetails(
			@ApiParam(value = "{\"typeOfRequest\":\"string\",\"isDiarrhea\":\"byte\",\"isVomiting\":\"byte\",\"isAbdominalPain\":\"byte\",\"isChillsOrRigors\":\"byte\","
					+ "\"isGiddiness\":\"byte\",\"isDehydration\":\"byte\",\"isRashes\":\"byte\",\"fromWhen\":\"timestamp\",\"historyOfDiet\":\"String\","
					+ "\"isFoodConsumed\":\"byte\",\"typeOfFood\":\"String\",\"foodConsumedFrom\":\"String\",\"associatedSymptoms\":\"String\","
					+ "\"feedbackTypeID\":\"short\",\"districtID\":\"Integer\",\"districtBlockID\":\"Integer\",\"villageID\":\"Integer\",\"remarks\":\"String\",\"deleted\":\"boolean\",\"createdBy\":\"String\"}") @RequestBody String request,
			HttpServletRequest httpRequest) {
		OutputResponse output = new OutputResponse();
		try {
			T_FoodSafetyCopmlaint t_foodSafetyCopmlaint = inputMapper.gson().fromJson(request,
					T_FoodSafetyCopmlaint.class);
			logger.info("saveFoodComplaintDetails request " + t_foodSafetyCopmlaint.toString());

			T_FoodSafetyCopmlaint foodComplaint;

			t_foodSafetyCopmlaint.setDeleted(false);
			foodComplaint = foodSafetyCopmlaintService.save(t_foodSafetyCopmlaint, httpRequest);
			output.setResponse(foodComplaint.toString());
		} catch (Exception e) {
			logger.error("saveFoodComplaintDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("saveFoodComplaintDetails response: " + output);
		return output.toString();
	}

	@CrossOrigin
	@ApiOperation(value = "Provides food safety complaints history", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/get/foodComplaintDetails", method = RequestMethod.POST, headers = "Authorization")
	public String getFoodComplaintDetails(
			@ApiParam(value = "{\"beneficiaryRegID\":\"optional long\",   \"benCallID\":\" Optional long\",   \"requestID\":\" Optional string\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			T_FoodSafetyCopmlaint t_foodSafetyCopmlaint = inputMapper.gson().fromJson(request,
					T_FoodSafetyCopmlaint.class);
			logger.info("saveFoodComplaintDetails request " + t_foodSafetyCopmlaint.toString());

			List<T_FoodSafetyCopmlaint> foodComplaint = null;

			foodComplaint = foodSafetyCopmlaintService.getFoodSafetyComplaints(
					t_foodSafetyCopmlaint.getBeneficiaryRegID(), t_foodSafetyCopmlaint.getBenCallID(),
					t_foodSafetyCopmlaint.getRequestID(), t_foodSafetyCopmlaint.getPhoneNo());
			output.setResponse(foodComplaint.toString());
			logger.info("getFoodComplaintDetails response size: "
					+ ((foodComplaint.size() > 0) ? foodComplaint.size() : "No Beneficiary Found"));
		} catch (Exception e) {
			logger.error("getFoodComplaintDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}

		return output.toString();
	}

}
