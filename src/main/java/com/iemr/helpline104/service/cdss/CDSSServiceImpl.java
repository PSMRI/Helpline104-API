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
package com.iemr.helpline104.service.cdss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.LongSerializationPolicy;
import com.iemr.helpline104.controller.cdss.ClinicalDecisionSupportController;
import com.iemr.helpline104.data.cdss.Symptoms;
import com.iemr.helpline104.data.cdss.SymptomsWrapper;
import com.iemr.helpline104.repository.cdss.DBConnect;

/**
 * 
 */
@Service
public class CDSSServiceImpl implements CDSSService {

	private DBConnect dbConnect;
	private Logger logger = LoggerFactory.getLogger(ClinicalDecisionSupportController.class);
	@Autowired
	public void setDbConnect(DBConnect dbConnect) {
		this.dbConnect = dbConnect;
	}

	@Override
	public List<String> getSymptoms() {
		// TODO Auto-generated method stub

		return dbConnect.getSymptoms();
	}

	@Override
	public List<String> getSymptoms(SymptomsWrapper symptomsDetails) {
		List<String> symptoms = dbConnect.getSymptoms(symptomsDetails.getGender(),symptomsDetails.getAge());
		if(null != symptoms && !ObjectUtils.isEmpty(symptoms)) {
			return getCamelCaseSymtoms(symptoms);
		}
		return null;
	}
	private List<String> getCamelCaseSymtoms(List<String> symptoms) {
		List<String> camalCaseSymtoms = new ArrayList<>();
		for (String symptom : symptoms) {
			if (null != symptom && !StringUtils.isEmpty(symptom)) {
				String lowecase = symptom.toLowerCase(); 
				char[] charArray = lowecase.toCharArray();
				boolean foundSpace = true;
				for (int i = 0; i < charArray.length; i++) {
					if (Character.isLetter(charArray[i])) {
						if (foundSpace) {
							charArray[i] = Character.toUpperCase(charArray[i]);
							foundSpace = false;
						}
					} else {
						foundSpace = true;
					}
				}
				String message = String.valueOf(charArray);
				camalCaseSymtoms.add(message);
			}
		}
		return camalCaseSymtoms;
	}
	public String getQuestions(String symptom, int age, String gender) {
		// TODO Auto-generated method stub
		JsonObject questions = new JsonObject();
		JsonObject questTransfer = new JsonObject();
		Symptoms symptomData = dbConnect.getQuestions(symptom, age, gender);
		if (symptomData != null) {
			JsonParser parse = new JsonParser();
			// System.out.println(symptomData);
			questTransfer = parse.parse(symptomData.getData()).getAsJsonObject();
			questions.addProperty("id", symptomData.getSymptomId());
			questions.addProperty("disease", symptomData.getSymptom());

			JsonObject questionArray = questTransfer.get("Question Set").getAsJsonObject();

			Set<Entry<String, JsonElement>> keys = questionArray.entrySet();
			JsonPrimitive zeroElement = new JsonPrimitive(0);
			JsonArray questionA = new JsonArray();
			for (Entry<String, JsonElement> key : keys) {
				String eQuestion = key.getKey();
				boolean isEmergency = questionArray.getAsJsonArray(eQuestion).contains(zeroElement);
				JsonObject quest = new JsonObject();
				quest.addProperty("question", eQuestion);
				quest.addProperty("isEmergency", isEmergency);
				questionA.add(quest);
				System.out.println();
			}

			questions.add("Questions", questionA);

		} else {

			questions.addProperty("Msg", "No Question Found");

		}
		Gson gson = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).setPrettyPrinting().serializeNulls().create();

		return gson.toJson(questions);
	}

	public String getResult(int complaintId, int selected) {
		// TODO Auto-generated method stub

		String compalaintData = dbConnect.getResultsById(complaintId);

		JsonParser parse = new JsonParser();

		JsonObject dataSet = parse.parse(compalaintData).getAsJsonObject();
		JsonObject questionSet = dataSet.get("Question Set").getAsJsonObject();

		Set<Entry<String, JsonElement>> keys = questionSet.entrySet();

		ArrayList<Entry<String, JsonElement>> as = new ArrayList<>(keys);

		JsonArray sympIds = as.get(selected).getValue().getAsJsonArray();
		JsonPrimitive zeroElement = new JsonPrimitive(0);
		sympIds.remove(zeroElement);

		JsonArray diseases = dataSet.get("Diseases").getAsJsonArray();

		JsonArray result = new JsonArray();
		for (JsonElement jsonElement : sympIds) {
			int x = jsonElement.getAsInt();
			JsonObject disease_data = diseases.get(x - 1).getAsJsonObject();
			Set<Entry<String, JsonElement>> keys_disease = disease_data.entrySet();
			ArrayList<Entry<String, JsonElement>> ar_disease = new ArrayList<>(keys_disease);
			JsonObject data_disease = new JsonObject();
			data_disease.addProperty("Disease", ar_disease.get(0).getKey());
			JsonObject obj = ar_disease.get(0).getValue().getAsJsonObject();
			data_disease.add("Symptoms", obj.get("Symptoms"));
			data_disease.add("Information", obj.get("Information"));
			data_disease.add("DoDonts", obj.get("Do & Donts"));
			data_disease.add("SelfCare", obj.get("Self-Care"));
			data_disease.add("Action", obj.get("Action"));
			result.add(data_disease);
			System.out.println(keys_disease);
		}
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

		return gson.toJson(result);
	}

	public String saveSymptom(String yamlInput) {

		JsonParser parse = new JsonParser();
		try {
			JsonObject userAnswerJson = (JsonObject) parse.parse(yamlInput);
		} catch (JsonSyntaxException jse) {
			return "{\"message\":\"" + jse.getMessage() + "\"}";
		} catch (Exception e) {
			return "{\"message\":\"" + e.getMessage() + "\"}";
		}
		JsonObject userAnswerJson = (JsonObject) parse.parse(yamlInput);
		String yaml = userAnswerJson.get("Msg").getAsString();

		System.out.println(yaml);

		ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
		Object obj;
		String outputJson = null;
		String result = "{\"message\":\"Couldnt parse the algo.\"}";
		try {
			Symptoms sym = new Symptoms();

			yaml = yaml.replaceAll("\t", "    ");
			obj = yamlReader.readValue(yaml, Object.class);

			ObjectMapper jsonWriter = new ObjectMapper();

			outputJson = jsonWriter.writeValueAsString(obj);

			System.out.println(outputJson);

			JsonObject symptonJson = parse.parse(outputJson).getAsJsonObject();
			result = "{\"message\":\"Couldnt find Chief Complaint.\"}";
			sym.setSymptom(symptonJson.get("Chief Complaint").getAsString());
			result = "{\"message\":\"Couldnt find Gender.\"}";
			JsonElement js_gender = symptonJson.get("Gender");
			result = checkCDSScasting(js_gender, "Gender ", "String");
			sym.setGender(js_gender.getAsJsonArray().toString());

			result = "{\"message\":\"Couldnt find Age.\"}";
			JsonElement js_age = symptonJson.get("Age");
			result = checkCDSScasting(js_age, "Age ", "int");
			sym.setAgeFrom(js_age.getAsJsonArray().get(0).getAsInt());
			sym.setAgeTo(js_age.getAsJsonArray().get(1).getAsInt());

			result = "{\"message\":\"Couldnt find Question Set.\"}";
			JsonElement questionset = getKey(symptonJson, "Question Set", "Algorithm");// symptonJson.get("Question

			// checkCDSScasting(questionset, "Question Set " + "Algorithm",
			// "json");
			result = "{\"message\":\"Error in Question Set. After colon give space and then a sqaure bracket. Inside square bracket place the no.  Ex. ....dise: [0,3,7]\"}";
			Set<Map.Entry<String, JsonElement>> entries = questionset.getAsJsonObject().entrySet();
			result = "{\"message\":\"Some Error Occured Check your format\"}";

			for (Entry<String, JsonElement> entry : entries) {
				JsonElement objElement = entry.getValue();
				String key = entry.getKey();
				checkCDSScasting(objElement, " 'Question Set' '" + key + "'", "int");

			}

			int diseases_size = symptonJson.get("Diseases").getAsJsonArray().size();
			for (int i = 0; i < diseases_size; i++) {
				JsonObject jobjec = symptonJson.get("Diseases").getAsJsonArray().get(i).getAsJsonObject();

				Set<Entry<String, JsonElement>> keyMap = jobjec.entrySet();
				for (Entry<String, JsonElement> entry : keyMap) {
					JsonObject objElement = entry.getValue().getAsJsonObject();
					String key = entry.getKey();
					JsonElement json = getKey(objElement, "Symptoms", key);// objElement.get("Symptoms");
					result = checkCDSScasting(json, "Symptoms " + key, "String");

					json = getKey(objElement, "Information", key);// objElement.get("Information");
					result = checkCDSScasting(json, "Information " + key, "String");

					json = getKey(objElement, "Do & Donts", key);// objElement.get("Do
																	// &
																	// Donts");
					result = checkCDSScasting(json, "Do & Donts " + key, "String");

					json = getKey(objElement, "Self-Care", key);// objElement.get("Self-Care");
					result = checkCDSScasting(json, "Self-Care " + key, "String");

					json = getKey(objElement, "Action", key);// objElement.get("Action");
					result = checkCDSScasting(json, "Action " + key, "String");

				}

			}

			// throw new Exception();
			sym.setData(symptonJson.toString());
			dbConnect.save(sym);
			result = "{\"message\":\"Sucess\"}";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			result = "{\"message\":\"" + e.getMessage() + "\"}";
			// result = "{\"message\":\"InValid YAML file\"}";

		} catch (DataIntegrityViolationException e) {
			logger.error(e.getMessage());
			result = "{\"message\":\"Data Already Exist in Database\"}";
		} catch (CDSSException e) {
			logger.error(e.getMessage());

			result = "{\"message\":\"" + e.getMsg() + "\"}";
			// result = "{\"message\":\"Could not insert into Database.\"}";
		} catch (Exception e) {
			logger.error(e.getMessage());
			// result = "{\"message\":\"Could not insert into Database.\"}";
		}

		// System.out.println(sym);

		return result;
	}

	private JsonElement getKey(JsonObject json, String key, String obj) throws CDSSException {
		JsonElement jsone = null;
		CDSSException exception = new CDSSException();
		exception.setMsg("Could not find '" + key + "' in " + obj);
		try {
			jsone = json.get(key);
		} catch (Exception e) {


			throw exception;
			// TODO: handle exception
		}
		if (jsone == null) {
			throw exception;
		}
		return jsone;
	}

	private String checkCDSScasting(JsonElement json, String key, String type) throws CDSSException {

		CDSSException exception = new CDSSException();
		exception.setMsg("Error in " + key);
		if (!json.isJsonNull()) {
			if (!json.isJsonArray()) {
				exception.setMsg("Expecting a Array in" + key);
				throw exception;
			}
			JsonArray asd = json.getAsJsonArray();
			try {
				for (JsonElement jsonElement : asd) {
					if (type.equalsIgnoreCase("String")) {
						exception.setMsg("Error in " + key
								+ ". Expecting a string(you might have a colon(:) in between so give quotes around the whole sentence/point.)");
						jsonElement.getAsString();
					}
					if (type.equalsIgnoreCase("int")) {
						exception.setMsg("Error in " + key + ". Expecting a integer");
						int a = jsonElement.getAsInt();
					}

				}
			} catch (Exception e) {
				throw exception;
			}
		}
		return exception.getMsg();

	}

}
