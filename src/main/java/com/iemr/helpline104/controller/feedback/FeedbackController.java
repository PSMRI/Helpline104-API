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
package com.iemr.helpline104.controller.feedback;

import java.util.List;

import jakarta.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iemr.helpline104.data.feedback.FeedbackDetails;
import com.iemr.helpline104.service.feedback.FeedbackService;
import com.iemr.helpline104.service.feedback.FeedbackServiceImpl;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping(value = "/beneficiary")
@RestController
public class FeedbackController {
	private static final String request = null;
	private InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	private FeedbackService feedbackService;

	@Autowired
	public void setFeedbackServiceImpl(FeedbackServiceImpl feedbackServiceImpl) {
		this.feedbackService = feedbackServiceImpl;
	}

	@Autowired
	public void setFeedbackService(FeedbackService feedbackService) {

		this.feedbackService = feedbackService;
	}

	@CrossOrigin()
	@Operation(summary = "Fetch feedback request")
	@PostMapping(value = "/getfeedbacklist", produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String feedbackReuest(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
			FeedbackDetails feedbackDetails = inputMapper.gson().fromJson(request, FeedbackDetails.class);
			List<FeedbackDetails> feedbackList = feedbackService
					.getFeedbackRequests(feedbackDetails.getBeneficiaryRegID());
			response.setResponse(feedbackList.toString());
		} catch (Exception e) {
			logger.error("", e);
			response.setError(e);
		}
		return response.toString();
	}

	@CrossOrigin()
	@Operation(summary = "Get feedback by post")
	@PostMapping(value = "/getfeedback/{feedbackID}", produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getFeedbackByPost(@PathVariable("feedbackID") int feedbackID) {
		OutputResponse response = new OutputResponse();
		try {
			logger.info("" + feedbackID);
			List<FeedbackDetails> savedDetails = feedbackService.getFeedbackRequests(feedbackID);
			response.setResponse(savedDetails.toString());
		} catch (Exception e) {
			logger.error("", e);
			response.setError(e);
		}
		return response.toString();
	}

	@CrossOrigin()
	@Operation(summary = "Update feedback")
	@PostMapping(value = "/updatefeedback", produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String updateFeedback(@RequestBody String feedbackDetailsOBJ) {
		OutputResponse response = new OutputResponse();
		try {
			logger.info("update grevience request " + feedbackDetailsOBJ);
			FeedbackDetails feedbackDetails = InputMapper.gson().fromJson(feedbackDetailsOBJ, FeedbackDetails.class);
			if (feedbackDetails != null && feedbackDetails.getRequestID() != null) {
				String result = feedbackService.updateFeedback(feedbackDetails);
				if (result != null)
					response.setResponse(result);
				else
					response.setError(5000, "Error in data update");
			} else
				response.setError(5000, "Invalid request. Request ID is mandatory to update details");
		} catch (Exception e) {
			logger.error("", e);
			response.setError(5000, e.getMessage());
		}
		return response.toString();
	}

	@CrossOrigin()
	@Operation(summary = "Save beneficiary feedback")
	@PostMapping(value = "/saveBenFeedback", produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String saveBenFeedback(@RequestBody String feedbackRequest, HttpServletRequest request) {
		OutputResponse response = new OutputResponse();
		try {
			String savedFeedback = feedbackService.saveFeedbackFromCustomer(feedbackRequest, request);
			if (savedFeedback != null)
				response.setResponse(savedFeedback);
			else
				response.setError(5000, "error in saving feedback. please contact administrator");
		} catch (Exception e) {
			logger.error("saveBenFeedback failed with error " + e.getMessage(), e);
			response.setError(5000, "saving feedback failed with error " + e);
		}

		return response.toString();
	}
}
