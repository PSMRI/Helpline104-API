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
package com.iemr.helpline104.controller.organDonation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.data.organDonation.M_DonatableOrgan;
import com.iemr.helpline104.data.organDonation.M_DonationType;
import com.iemr.helpline104.data.organDonation.OrganDonations;
import com.iemr.helpline104.data.organDonation.T_OrganDonation;
import com.iemr.helpline104.service.organDonation.OrganDonationServiceImpl;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.annotations.ApiParam;

@RequestMapping(value = "/beneficiary")
@RestController
public class OrganDonationController {
	InputMapper inputMapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(OrganDonationController.class);
	
	@Autowired
	private OrganDonationServiceImpl organDonationServiceImpl;

	@CrossOrigin
	@RequestMapping(value = "/save/organDonationRequestDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String saveOrganDonationDetails(@RequestBody String request) {
		OutputResponse output= new OutputResponse();
		try{
		OrganDonations organDonations = inputMapper.gson().fromJson(request, OrganDonations.class);
		logger.info("saveOrganDonationDetails request " + organDonations.toString());
		
		
			String organDonationResponse = organDonationServiceImpl.save(organDonations);
			output.setResponse(organDonationResponse.toString());
		} catch(Exception e){
			logger.error("saveOrganDonationDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}	
	
	@CrossOrigin
	@RequestMapping(value = "/update/organDonationRequestDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String updateOrganDonationDetails(@RequestBody String request) {
		OutputResponse output= new OutputResponse();
		try{
			T_OrganDonation organDonations = inputMapper.gson().fromJson(request, T_OrganDonation.class);
		    logger.info("updateOrganDonationDetails request " + organDonations.toString());
		
		
			String organDonationResponse = organDonationServiceImpl.update(organDonations);
			output.setResponse(organDonationResponse.toString());
		} catch(Exception e){
			logger.error("updateOrganDonationDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}	
	

	@CrossOrigin
	@RequestMapping(value = "/save/organDonationInstituteDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String saveOrganDonationInstituteDetails(@RequestBody String request) {
		OutputResponse output= new OutputResponse();
		try{
			T_OrganDonation organDonation = inputMapper.gson().fromJson(request, T_OrganDonation.class);
		logger.info("saveOrganDonationInstituteDetails request " + organDonation.toString());
		
		
			String organDonationResponse = organDonationServiceImpl.saveInstituteDetails(organDonation);
			output.setResponse(organDonationResponse.toString());
		} catch(Exception e){
			logger.error("saveOrganDonationInstituteDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		return output.toString();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/get/organDonationRequestDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getOrganDonationDetails(@ApiParam(
			value = "{\"beneficiaryRegID\":\"optional long\",   \"benCallID\":\" Optional long\",   \"requestID\":\" Optional string\"}") @RequestBody String request) {
		OutputResponse output= new OutputResponse();
		try {
		T_OrganDonation t_organDonation = inputMapper.gson().fromJson(request, T_OrganDonation.class);
		logger.info("getOrganDonationDetails request " + t_organDonation.toString());
		
		
		List<T_OrganDonation> organDonationRequest = null;
		
			organDonationRequest = organDonationServiceImpl.getOrganDonationRequests(t_organDonation.getBeneficiaryRegID(), t_organDonation.getBenCallID(), t_organDonation.getRequestID());
			output.setResponse(organDonationRequest.toString());
			logger.info("getOrganDonationDetails response size: " + ((organDonationRequest.size()>0) ? organDonationRequest.size() : "No Beneficiary Found"));
		} catch (Exception e) {
			logger.error("getOrganDonationDetails failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		
		return output.toString();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/get/organDonationTypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getOrganDonationTypes() {
		logger.info("getOrganDonationTypes request ");
		OutputResponse output= new OutputResponse();
		List<M_DonationType> organDonationTypes = null;
		try {
			organDonationTypes = organDonationServiceImpl.getDonationTypes();
			output.setResponse(organDonationTypes.toString());
		} catch (Exception e) {
			logger.error("getOrganDonationTypes failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getOrganDonationTypes response: " + output);
		return output.toString();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/get/DonatableOrgans", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getDonatableOrgans() {
		logger.info("getDonatableOrgans request ");
		OutputResponse output= new OutputResponse();
		List<M_DonatableOrgan> donatableOrgans = null;
		try {
			donatableOrgans = organDonationServiceImpl.getDonatableOrgans();
			output.setResponse(donatableOrgans.toString());
		} catch (Exception e) {
			logger.error("getDonatableOrgans failed with error " + e.getMessage(), e);
			output.setError(e);
		}

		logger.info("getDonatableOrgans response: " + output);
		return output.toString();
	}
	
}
