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
package com.iemr.helpline104.controller.location;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.controller.feedback.FeedbackController;
import com.iemr.helpline104.service.location.CountryCityService;
import com.iemr.helpline104.utils.response.OutputResponse;

@RequestMapping(value =  "/countryCityController")
@RestController
public class CountryCityController {

	@Autowired
	private CountryCityService countryCityService;
	private Logger logger = LoggerFactory.getLogger(FeedbackController.class);
	@CrossOrigin
	@RequestMapping(value = { "/getCountry" }, method = RequestMethod.GET, 
							produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getCountry() 
	{
		OutputResponse response = new OutputResponse();
		try {
		String data = countryCityService.getCountry();
		response.setResponse(data);
	} catch (Exception e) {
		logger.error(e.getMessage());
		response.setError(e);

	}
	/**
	 * sending the response...
	 */
	return response.toString();
	}
	
	@CrossOrigin
	@RequestMapping(value = { "/getCities/{id}" }, method = RequestMethod.GET, 
							produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getCities(@PathVariable("id") Integer id) 
	{
		OutputResponse response = new OutputResponse();
		try {
		String data = countryCityService.getCities(id);
		response.setResponse(data);
	} catch (Exception e) {
		logger.error(e.getMessage());
		response.setError(e);

	}
	/**
	 * sending the response...
	 */
	return response.toString();
	}
}
