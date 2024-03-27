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
package com.iemr.helpline104.controller.balVivha;

import jakarta.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iemr.helpline104.controller.feedback.FeedbackController;
import com.iemr.helpline104.data.balVivah.BalVivahComplaint;
import com.iemr.helpline104.service.balVivah.BalVivahComplaintService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping(value = "/beneficiary")
@RestController
public class BalVivahController {
	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	@Autowired
	private BalVivahComplaintService balVivahComplaintService;

	@CrossOrigin()
	@Operation(summary = "Save bal vivah complaint")
	@PostMapping(value = "/saveBalVivahComplaint", produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String balVivahComplaint(@RequestBody String request, HttpServletRequest httpRequest) {
		OutputResponse output = new OutputResponse();
		try {
			BalVivahComplaint balVivahComplaint = InputMapper.gson().fromJson(request, BalVivahComplaint.class);
			String vivahComplaint = balVivahComplaintService.save(balVivahComplaint, httpRequest);
			output.setResponse(vivahComplaint);
		} catch (Exception e) {
			output.setError(e);
		}
		return output.toString();
	}

	@CrossOrigin()
	@Operation(summary = "Get bal vivah list")
	@PostMapping(value = "/getBalVivahList", produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String feedbackReuest(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {

			BalVivahComplaint balVivahComplaint = InputMapper.gson().fromJson(request, BalVivahComplaint.class);
			String balVivahComplaintList = balVivahComplaintService.getWorklistRequests(
					balVivahComplaint.getBeneficiaryRegID(), balVivahComplaint.getPhoneNum(),
					balVivahComplaint.getRequestID());
			response.setResponse(balVivahComplaintList);
		} catch (Exception e) {
			logger.error("", e);
			response.setError(e);
		}
		return response.toString();
	}

	@CrossOrigin()
	@Operation(summary = "Update bal vivah complaint")
	@PostMapping(value = "/update/BalVivahComplaint", produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String updateBalVivahComplaint(@RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			logger.info("update bal vivah request " + request);
			BalVivahComplaint balVivahComplaint = InputMapper.gson().fromJson(request, BalVivahComplaint.class);

			if (balVivahComplaint != null && balVivahComplaint.getRequestID() != null) {
				String result = balVivahComplaintService.updateBalVivahRequest(balVivahComplaint);

				if (result != null)
					output.setResponse(result);
				else
					output.setError(5000, "Error in data update");
			} else
				output.setError(5000, "Invalid request. Request ID is mandatory to update details");

		} catch (Exception e) {
			logger.error("update bal vivah failed with error " + e.getMessage());
			output.setError(5000, e.getMessage());
		}
		return output.toString();
	}

}
