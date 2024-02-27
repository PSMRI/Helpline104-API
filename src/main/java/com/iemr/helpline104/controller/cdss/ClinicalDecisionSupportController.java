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
package com.iemr.helpline104.controller.cdss;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.LongSerializationPolicy;
import com.iemr.helpline104.data.cdss.SymptomsWrapper;
import com.iemr.helpline104.service.cdss.CDSSService;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping(value = "/CDSS")
@RestController
public class ClinicalDecisionSupportController {
	@Autowired
	private CDSSService cDSSService;
	private Logger logger = LoggerFactory.getLogger(ClinicalDecisionSupportController.class);

	@CrossOrigin()
	@Operation(summary = "Get symptoms")
	@PostMapping(value = "/Symptoms", produces = "application/json", headers = "Authorization")
	public String getSymptomsPost(@RequestBody SymptomsWrapper symptomsDetails) {
		OutputResponse output = new OutputResponse();
		try {
			String sympReturn;
			List<String> symptoms = cDSSService.getSymptoms(symptomsDetails);
			Gson gson = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).setPrettyPrinting()
					.create();
			if (symptoms != null && symptoms.size() > 0) {
				sympReturn = gson.toJson(symptoms);
			} else {
				sympReturn = "{\"Msg\":\"No Symptoms Found\"}";
			}

			output.setResponse(sympReturn);

		} catch (Exception e) {
			logger.error("getSymptoms failed with error " + e.getMessage(), e);
			output.setError(e);
		}

		return output.toString();

	}

	@CrossOrigin()
	@Operation(summary = "Get questions by symptom, age and gender")
	@PostMapping(value = "/getQuestions", produces = "application/json", headers = "Authorization")
	public String getQuestion(@RequestBody SymptomsWrapper symptomsDetails) {
		OutputResponse output = new OutputResponse();
		try {
			String questions = "{\"Msg\":\"InSufficent Data\"}";
			String symptom = symptomsDetails.getSymptom();
			String gender = symptomsDetails.getGender();
			int age = symptomsDetails.getAge();

			if (symptom != null && gender != null && age >= 0) {
				questions = cDSSService.getQuestions(symptom, age, gender);

				output.setResponse(questions);
			}

		} catch (Exception e) {
			logger.error("getQuestions failed with error " + e.getMessage(), e);
			output.setError(e);
		}

		return output.toString();

	}

	@CrossOrigin()
	@Operation(summary = "Get result based on compliant id")
	@PostMapping(value = "/getResult", produces = "application/json", headers = "Authorization")
	public String getResult(@RequestBody String userAnswer) {
		OutputResponse output = new OutputResponse();
		try {
			JsonParser parse = new JsonParser();
			JsonObject userAnswerJson = (JsonObject) parse.parse(userAnswer);

			ArrayList<Integer> response = new ArrayList<Integer>();

			int complaintId = userAnswerJson.get("complaintId").getAsInt();
			int selected = userAnswerJson.get("selected").getAsInt();

			String result = cDSSService.getResult(complaintId, selected);
			output.setResponse(result);

		} catch (Exception e) {
			logger.error("getResult failed with error " + e.getMessage(), e);
			output.setError(e);
		}

		return output.toString();

	}

	@CrossOrigin()
	@Operation(summary = "Save symptom")
	@PostMapping(value = "/saveSymptom", produces = "application/json", headers = "Authorization")
	public String saveSymptom(@RequestBody String inputData) {
		OutputResponse output = new OutputResponse();
		try {
			String result = cDSSService.saveSymptom(inputData);
			output.setResponse(result);
		} catch (Exception e) {
			logger.error("saveSymptom failed with error " + e.getMessage(), e);
			output.setError(e);
		}

		return output.toString();

	}
}
