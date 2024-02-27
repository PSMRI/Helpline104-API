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
package com.iemr.helpline104.controller.sioHistory;

import jakarta.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iemr.helpline104.data.sioHistory.SioHistory;
import com.iemr.helpline104.service.bloodComponentType.BloodComponentTypeServiceImpl;
import com.iemr.helpline104.service.bloodRequest.BloodRequestServiceImpl;
import com.iemr.helpline104.service.directory.DirectoryServiceImpl;
import com.iemr.helpline104.service.epidemicOutbreak.EpidemicOutbreakServiceImpl;
import com.iemr.helpline104.service.foodSafetyCopmlaint.FoodSafetyCopmlaintServiceImpl;
import com.iemr.helpline104.service.organDonation.OrganDonationServiceImpl;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;

/**
 * Controller class to get the Sio history data for a particular beneficiary
 */
@RequestMapping(value = "/beneficiary")
@RestController
public class ServiceImprovementOfficerController {

	InputMapper mapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(ServiceImprovementOfficerController.class);

	@Autowired
	private BloodRequestServiceImpl bloodRequestServiceImpl;
	@Autowired
	private BloodComponentTypeServiceImpl bloodComponentTypeServiceImpl;
	@Autowired
	private EpidemicOutbreakServiceImpl epidemicOutbreakServiceImpl;
	@Autowired
	private OrganDonationServiceImpl organDonationServiceImpl;
	@Autowired
	private DirectoryServiceImpl directoryServiceImpl;
	@Autowired
	private FoodSafetyCopmlaintServiceImpl foodSafetyCopmlaintServiceImpl;

	@CrossOrigin
	@Operation(summary = "Get sio history")
	@PostMapping(value = "/getSioHistory", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getSioHistory(@RequestBody String benificiary) {

		logger.info("getSioHistory request " + benificiary.toString());
		SioHistory sioHistoryData = new SioHistory();
		OutputResponse output = new OutputResponse();
		try {
			JSONObject j = new JSONObject(benificiary);
			Long benificiaryRegID = j.getLong("benificiaryRegID");
			sioHistoryData.setT_bloodRequest(bloodRequestServiceImpl.getBloodRequest(benificiaryRegID, null, null));
			sioHistoryData.setM_componentType(bloodComponentTypeServiceImpl.getBloodComponentTypes());
			sioHistoryData.setM_bloodGroup(bloodComponentTypeServiceImpl.getBloodGroups());
			sioHistoryData.setT_epidemicOutbreak(
					epidemicOutbreakServiceImpl.getEpidemicOutbreakComplaints(benificiaryRegID, null, null, null));
			sioHistoryData.setT_organDonation(
					organDonationServiceImpl.getOrganDonationRequests(benificiaryRegID, null, null));
			sioHistoryData.setM_donationType(organDonationServiceImpl.getDonationTypes());
			sioHistoryData.setM_donatableOrgan(organDonationServiceImpl.getDonatableOrgans());
			sioHistoryData.setDirectory(directoryServiceImpl.getDirectories());
			sioHistoryData.setT_foodSafetyCopmlaint(
					foodSafetyCopmlaintServiceImpl.getFoodSafetyComplaints(benificiaryRegID, null, null, null));
			output.setResponse(sioHistoryData.toString());
		} catch (JSONException e) {
			logger.error("getSioHistory failed with error " + e.getMessage(), e);
			output.setError(e);
		} catch (Exception e) {
			logger.error("getSioHistory failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getSioHistory response: " + output);
		return output.toString();
	}

}
