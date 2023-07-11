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
package com.iemr.helpline104.controller.snomedct;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.data.snomedct.SCTDescription;
import com.iemr.helpline104.service.snomedct.SnomedService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RequestMapping(value = "/snomed")
@RestController
public class SnomedController {

	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(SnomedController.class);

	private SnomedService snomedService;

	@Autowired
	public void setSnomedService(SnomedService snomedService) {
		this.snomedService = snomedService;
	}

	@CrossOrigin
	@ApiOperation(value = "retrives SnomedCT Record (Entire term case insensitive)", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getSnomedCTRecord", method = RequestMethod.POST, headers = "Authorization")
	public String getSnomedCTRecord(@ApiParam(value = "{\"term\":\"String\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {

			SCTDescription sctdescription = inputMapper.gson().fromJson(request, SCTDescription.class);

			logger.info("getSnomedCTRecord request " + sctdescription.toString());

			List<SCTDescription> sctdescriptions = snomedService.findSnomedCTRecordFromTerm(sctdescription.getTerm());

			SCTDescription recentRecord = null;
			
			for (SCTDescription sct : sctdescriptions) {				
				recentRecord = sct;
				//  recent & case insensitive id is 900000000000448009
				if(sct.getCaseSignificanceID() == "900000000000448009")
				break;
			}

			if(recentRecord == null)
				output.setResponse("No Records Found");
			else
			output.setResponse(recentRecord.toString());
			
			logger.info("ggetSnomedCTRecord response: " + output);
		} catch (Exception e) {
			logger.error("ggetSnomedCTRecord failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}

	@CrossOrigin
	@ApiOperation(value = "retrives SnomedCT Records", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getSnomedCTRecords", method = RequestMethod.POST, headers = "Authorization")
	public String getSnomedCTRecords(@ApiParam(value = "{\"term\":\"String\"}") @RequestBody String request) {
		OutputResponse output = new OutputResponse();
		try {

			SCTDescription sctdescription = inputMapper.gson().fromJson(request, SCTDescription.class);

			logger.info("getSnomedCTRecord request " + sctdescription.toString());

			List<SCTDescription> sctdescriptions = snomedService.findSnomedCTRecordFromTerm(sctdescription.getTerm());

			output.setResponse(sctdescriptions.toString());
			logger.info("ggetSnomedCTRecord response: " + output);
		} catch (Exception e) {
			logger.error("ggetSnomedCTRecord failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}

}
