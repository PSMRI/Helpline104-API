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
package com.iemr.helpline104.controller.prescription;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.iemr.helpline104.data.prescription.Prescription;
import com.iemr.helpline104.service.prescription.PrescriptionService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RequestMapping(value = "/beneficiary")
@RestController
public class PrescriptionController {

	InputMapper mapper = new InputMapper();
	private Logger logger = LoggerFactory.getLogger(PrescriptionController.class);

	@Autowired
	private PrescriptionService prescriptionService;

	@CrossOrigin
	@Operation(summary = "Save prescription")
	@PostMapping(value = "/save/prescription", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String savePrescription(@RequestBody String createRequest) {
		OutputResponse output = new OutputResponse();
		try {
			Prescription t_Prescription = mapper.gson().fromJson(createRequest, Prescription.class);
			logger.info("savePrescription request " + t_Prescription.toString());

			Prescription prescription = null;

			prescription = prescriptionService.savePrescription(t_Prescription);
			output.setResponse(prescription.toString());
		} catch (Exception e) {
			logger.error("savePrescription failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("savePrescription response: " + output);
		return output.toString();
	}

	@CrossOrigin
	@Operation(summary = "Get prescription")
	@PostMapping(value = "/get/prescription", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getPrescription(@RequestBody String createRequest) {
		OutputResponse output = new OutputResponse();
		try {
			Prescription t_Prescription = mapper.gson().fromJson(createRequest, Prescription.class);
			logger.info("getPrescription request " + t_Prescription.toString());

			List<Prescription> prescription = null;

			prescription = prescriptionService.getPrescription(t_Prescription.getBeneficiaryRegID(),
					t_Prescription.getPrescriptionID());
			if (prescription != null) {
				output.setResponse(prescription.toString());
			} else {
				output.setError(5000, "prescription not available with that prescription or beneficiary ID");
			}

		} catch (Exception e) {
			logger.error("getPrescription failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getPrescription response: " + output);
		return output.toString();
	}

	@CrossOrigin
	@Operation(summary = "Get prescription list")
	@PostMapping(value = "/get/prescriptionList", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getPrescriptionList(@RequestBody String createRequest) {
		OutputResponse output = new OutputResponse();
		try {
			Prescription t_Prescription = mapper.gson().fromJson(createRequest, Prescription.class);
			logger.info("getPrescriptionList request " + t_Prescription.toString());

			List<Prescription> prescription = null;

			JSONObject requestObj = new JSONObject(createRequest);
			int page = requestObj.has("page") ? (requestObj.getInt("page") - 1) : 0;
			int size = requestObj.has("size") ? requestObj.getInt("size") : 1000;

			prescription = prescriptionService.getPrescriptionList(t_Prescription.getBeneficiaryRegID(),
					 PageRequest.of(page, size));
			if (prescription != null) {
				output.setResponse(prescription.toString());
			} else {
				output.setError(5000, "prescription not available with that prescription or beneficiary ID");
			}
			logger.info("getPrescriptionList response size: "
					+ (prescription != null ? prescription.size() : "No Beneficiary Found"));

		} catch (Exception e) {
			logger.error("getPrescriptionList failed with error " + e.getMessage(), e);
			output.setError(e);
		}

		return output.toString();
	}

	@CrossOrigin
	@Operation(summary = "Get latest valid pescription")
	@PostMapping(value = "/get/latestValidPescription", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization")
	public String getLatestValidPescription(
			@Parameter(description = "{\"beneficiaryRegID\":\"Integer\"}") @RequestBody String createRequest) {
		OutputResponse output = new OutputResponse();
		try {
			Prescription t_Prescription = mapper.gson().fromJson(createRequest, Prescription.class);
			logger.info("latestValidPescription request " + t_Prescription.toString());

			List<Prescription> prescription = null;

			JSONObject requestObj = new JSONObject(createRequest);
			int page = requestObj.has("page") ? (requestObj.getInt("page") - 1) : 0;
			int size = requestObj.has("size") ? requestObj.getInt("size") : 1000;

			prescription = prescriptionService.getLatestValidPescription(t_Prescription.getBeneficiaryRegID(),
					 PageRequest.of(page, size));
			if (prescription != null) {
				output.setResponse(prescription.toString());
			} else {
				output.setError(5000, "prescription not available with the beneficiaryID");
			}

		} catch (Exception e) {
			logger.error("getPrescriptionList failed with error " + e.getMessage(), e);
			output.setError(e);
		}
		logger.info("getPrescriptionList response: " + output);
		return output.toString();
	}

}