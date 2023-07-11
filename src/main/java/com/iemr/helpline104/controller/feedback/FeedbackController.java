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

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.data.feedback.FeedbackDetails;
import com.iemr.helpline104.service.feedback.FeedbackService;
import com.iemr.helpline104.service.feedback.FeedbackServiceImpl;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

@RequestMapping(value = "/beneficiary")
@RestController
public class FeedbackController {
	private static final String request = null;
	private InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	/**
	 * feedback service
	 */
	private FeedbackService feedbackService;

	private FeedbackServiceImpl feedbackServiceImpl;

	@Autowired
	public void setFeedbackServiceImpl(FeedbackServiceImpl feedbackServiceImpl) {
		this.feedbackServiceImpl = feedbackServiceImpl;
	}

	@Autowired
	public void setFeedbackService(FeedbackService feedbackService) {

		this.feedbackService = feedbackService;
	}

	@CrossOrigin()
	@RequestMapping(value = "/getfeedbacklist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
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
	@RequestMapping(value = "/setfeedback", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	@Deprecated
	public String feedbackCreate(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
			FeedbackDetails feedbackDetails = inputMapper.gson().fromJson(request, FeedbackDetails.class);
			FeedbackDetails savedDetails = feedbackService.createFeedback(feedbackDetails);
			response.setResponse("success: " + savedDetails.toString());
		} catch (Exception e) {
			logger.error("", e);
			response.setError(e);
		}
		return response.toString();
	}

	@CrossOrigin()
	@RequestMapping(value = "/getfeedback/{feedbackID}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
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
	@RequestMapping(value = "/updatefeedback", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
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

	// @CrossOrigin()
	// @RequestMapping(value = "/saveBenFeedback", method = RequestMethod.POST,
	// produces = MediaType.APPLICATION_JSON)
	// // public String saveFeedback(@RequestBody Iterable<FeedbackDetails>
	// // feedbackDetails) {
	// public String saveFeedback(@RequestBody String feedbackDetails) {
	//
	// OutputResponse response = new OutputResponse();
	// try {
	// String s = feedbackServiceImpl.saveFeedbackFromCustomer(feedbackDetails);
	// response.setResponse(s);
	// } catch (Exception e) {
	// logger.error("", e);
	// response.setError(e);
	// }
	// return response.toString();
	// }

	@CrossOrigin()
	@RequestMapping(value = "/saveBenFeedback", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String saveBenFeedback(@RequestBody String feedbackRequest, HttpServletRequest request) {
		OutputResponse response = new OutputResponse();
		try {
			String savedFeedback = feedbackServiceImpl.saveFeedbackFromCustomer(feedbackRequest, request);
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
