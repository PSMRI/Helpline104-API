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
package com.iemr.helpline104.controller.IMRMMR;

import java.util.HashMap;
import java.util.List;

import jakarta.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.iemr.helpline104.data.IMRMMR.IMRMMR;
import com.iemr.helpline104.data.IMRMMR.M_facilities;
import com.iemr.helpline104.data.IMRMMR.M_supportServices;
import com.iemr.helpline104.data.IMRMMR.m_iMRMMRBaseCommunity;
import com.iemr.helpline104.data.IMRMMR.m_iMRMMRHealthworker;
import com.iemr.helpline104.data.IMRMMR.m_iMRMMRTransitType;
import com.iemr.helpline104.service.IMRMMR.IMRMMRService;
import com.iemr.helpline104.service.IMRMMR.IMRMMRServiceImpl;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping(value = "/beneficiary")
@RestController
public class IMRMMRController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private IMRMMRService imrmmrService;

	@Operation(summary = "Save IMR MMR")
	@PostMapping(value = "/saveIMRMMR", headers = "Authorization", produces = {
			"application/json" })
	public String saveIMRMMR(@RequestBody String request,
			@RequestHeader(value = "Authorization") String Authorization) {
		logger.info("saveIMRMMR request " + request);
		OutputResponse response = new OutputResponse();
		try {
			if (request != null) {
				String res = imrmmrService.saveIMRMMR(request, Authorization);
				if (res != null)
					response.setResponse(res);
				else
					response.setError(5000, "error in saving IMR/MMR data");
			} else
				logger.error("saveIMRMMR failed as request is empty");
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}

	@Operation(summary = "Fetch support services")
	@GetMapping(value = "/fetchimrmmrmasters", produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String fetchSupportServices() {
		OutputResponse response = new OutputResponse();
		try {
			List<M_supportServices> supportServicesList = imrmmrService.getsupportServices();
			List<M_facilities> facilityList = imrmmrService.getFacilities();
			List<m_iMRMMRBaseCommunity> baseCommunity = imrmmrService.getBaseCommunities();
			List<m_iMRMMRTransitType> transitType = imrmmrService.getTransitType();
			List<m_iMRMMRHealthworker> healthWorker = imrmmrService.getHealthWorker();

			HashMap<String, Object> hmap = new HashMap<String, Object>();
			hmap.put("supportServicesList", supportServicesList);
			hmap.put("facilityList", facilityList);
			hmap.put("baseCommunityList", baseCommunity);
			hmap.put("transitTypeList", transitType);
			hmap.put("healthWorkerList", healthWorker);

			response.setResponse(new Gson().toJson(hmap));
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}

	@Operation(summary = "Feedback request")
	@PostMapping(value = "/getIMRMMRList", produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String feedbackReuest(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {

			IMRMMR imrmmr = InputMapper.gson().fromJson(request, IMRMMR.class);
			String imrmmrdeatchtList = imrmmrService.getWorklistRequests(imrmmr.getBeneficiaryRegID(),
					imrmmr.getPhoneNum(), imrmmr.getRequestID());
			response.setResponse(imrmmrdeatchtList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}

	@Operation(summary = "Update IMR MMR complaint")
	@PostMapping(value = "/update/ImrMmrComplaint", produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String updateImrMmrComplaint(@RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			logger.info("update immrmmr request " + request);
			IMRMMR complaint = InputMapper.gson().fromJson(request, IMRMMR.class);
			if (complaint != null && complaint.getRequestID() != null) {
				String result = imrmmrService.updateImrMmrRequest(complaint);
				if (result != null)
					output.setResponse(result);
				else
					output.setError(5000, "Error in data update");
			} else
				output.setError(5000, "Invalid request. Request ID is mandatory to update details");
		} catch (Exception e) {
			logger.error("update complaint failed with error " + e.getMessage());
			output.setError(5000, e.getMessage());
		}
		return output.toString();
	}

}
