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
package com.iemr.helpline104.controller.directory;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.data.directory.Directoryservice;
import com.iemr.helpline104.service.directory.DirectoryServiceService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RequestMapping(value = "/beneficiary")
@RestController
public class DirectoryServicesController {
	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(DirectoryServicesController.class);

	@Autowired
	private DirectoryServiceService directoryServiceService;

	@CrossOrigin
	@Operation(summary = "Retrieve directory search history")
	@RequestMapping(value = "/getdirectorySearchHistory", method = RequestMethod.POST, headers = "Authorization")
	public String getBenDirectoryHistory(
			@Parameter(description = "{\"beneficiaryRegID\":\"optional long\",  \"benCallID\":\" Optional long\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {

			Directoryservice directoryService = inputMapper.gson().fromJson(request, Directoryservice.class);
			logger.info("getdirectorySearchHistory request " + directoryService.toString());

			List<Directoryservice> searchHistory = directoryServiceService
					.getDirectorySearchHistory(directoryService.getBeneficiaryRegID(), directoryService.getBenCallID());
			output.setResponse(searchHistory.toString());
			logger.info("getdirectorySearchHistory response: " + output);
		} catch (Exception e) {
			logger.error("getdirectorySearchHistory failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}

	@CrossOrigin
	@Operation(summary = "Store directory serach history")
	@RequestMapping(value = "/save/directorySearchHistory", method = RequestMethod.POST, headers = "Authorization")
	public String directorySearchHistory(
			@Parameter(description = "[{\"beneficiaryRegID\":\"long\",\"benCallID\":\"long\",\"institutionID\":\"integer\",\"instituteDirectoryID\":\"integer\",\"instituteSubDirectoryID\":\"integer\","
					+ "\"providerServiceMapID\":\"integer\",\"remarks\":\"string\",\"createdBy\":\"string\"}]") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {
			Directoryservice[] directoryHistory = inputMapper.gson().fromJson(request, Directoryservice[].class);
			logger.info("save/directorySearchHistory request " + Arrays.toString(directoryHistory));

			String searchhistory = directoryServiceService.saveDirectorySearchHistory(directoryHistory);

			output.setResponse(searchhistory);
			logger.info("directorySearchHistory response: " + output);
		} catch (Exception e) {
			logger.error("save/directorySearchHistory failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}
}