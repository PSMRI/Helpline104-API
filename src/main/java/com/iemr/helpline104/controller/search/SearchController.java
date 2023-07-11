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
package com.iemr.helpline104.controller.search;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.iemr.helpline104.service.search.SearchOnId;
import com.iemr.helpline104.service.search.SearchOnIdImpl;
import com.iemr.helpline104.utils.response.OutputResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin
@RestController
@RequestMapping(value = "/search", headers = "Authorization")

public class SearchController {
	
	private Logger logger = LoggerFactory.getLogger(SearchController.class);
	@Autowired
	private SearchOnId searchOnId;

	@Deprecated
		@CrossOrigin()
		@ApiOperation(value = "Search for the Beneficiary by BeneficiaryID", consumes = "application/json", produces = "application/json")
		@RequestMapping(value = { "/searchOnId" }, method = { RequestMethod.POST })
		public String quickSearchBeneficiary(@RequestBody String requestOBJ) {
			OutputResponse response = new OutputResponse();
			logger.info("quickSearchBeneficiary request:" + requestOBJ);
			try {

				if (requestOBJ != null) 
				response.setResponse(searchOnId.getQuickSearchData(requestOBJ));
				else
					response.setError(5000, "Invalid request");
				
				logger.info("quickSearchBeneficiary response:" + response);
			} catch (Exception e) {
				logger.error("Error in quickSearchBeneficiary :" + e);
				response.setError(e);
			}
			return response.toString();
		}
	
}
