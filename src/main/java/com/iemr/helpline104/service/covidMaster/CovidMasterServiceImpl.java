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
package com.iemr.helpline104.service.covidMaster;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.iemr.helpline104.data.covidMaster.Covid19BenFeedback;
import com.iemr.helpline104.repository.covidMaster.Covid19BenFeedbackRepo;

import com.iemr.helpline104.repository.covidMaster.SymptomsMasterRepo;

import com.iemr.helpline104.data.covidMaster.SymptomsMaster;
//import com.iemr.mmu.data.covid19.Covid19BenFeedback;
//import com.iemr.mmu.data.nurse.BeneficiaryVisitDetail;
//import com.iemr.mmu.data.nurse.CommonUtilityClass;
import com.iemr.helpline104.utils.mapper.InputMapper;




@Service
public class CovidMasterServiceImpl implements CovidMasterService {

	@Autowired
	private SymptomsMasterRepo symptomsMasterRepo;
	
	
	@Autowired
	private Covid19BenFeedbackRepo covid19BenFeedbackRepo;
	
	
	
	@Override
	public String getMaster(Integer providerServiceMapID) {
		Map<String, ArrayList<Map<String,String>>> resMap = new HashMap<String, ArrayList<Map<String,String>>>();
		resMap.put("testingPersonMaster",myMethod("TestingPerson"));
		resMap.put("healthConditionsMaster",myMethod("HealthConditions"));
		resMap.put("symptomsMaster", myMethod("Symptoms"));
		resMap.put("otherSymptoms", myMethod("OtherSymptoms"));
		resMap.put("feverDuration", myMethod("FeverDuration"));
		resMap.put("feverStats", myMethod("FeverStats"));
		resMap.put("feverPattern", myMethod("FeverPattern"));
		resMap.put("covid19Category", myMethod("COVID-19 Category"));
		resMap.put("medicalAssistance", myMethod("Medical Assistance"));
		resMap.put("foodSupply", myMethod("Food Supply"));
		resMap.put("lpgSupply", myMethod("LPG Supply"));
		resMap.put("strandedAssistance", myMethod("Stranded Assistance"));
		resMap.put("lawAndOrder", myMethod("Law & Order"));
		resMap.put("essentialServicese", myMethod("Essential Services"));
		resMap.put("transportation", myMethod("Transportation"));
		resMap.put("covidReliefFund", myMethod("COVID Relief Fund"));
		
		
		
		return new Gson().toJson(resMap);
	}
	
	 public ArrayList<Map<String,String>> myMethod(String MasterType) {
	
		 
		 ArrayList<Map<String,String>> ar = new ArrayList<Map<String,String>>();
		
		 SymptomsMaster ccList = symptomsMasterRepo.getMaster(MasterType);
		
		 String[] masterValues = ccList.getMasterValues().split("\\|\\|");
		 
		 for(int i = 0;i<masterValues.length;i++ )
			 
		 {
			 Map<String, String> resMap = new HashMap<String, String>();
			 resMap.put("ID", String.valueOf(i+1));
			 resMap.put("Value", masterValues[i].trim());
			 ar.add((Map<String, String>) resMap);
			
		 }
		
		 
		 return ar;		 
	
		
	 }
 
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String saveCovidData(String requestObj) throws Exception {
		String response = "";

				try {
					Covid19BenFeedback covid19BenFeedbackOBJ = InputMapper.gson()
							.fromJson(requestObj, Covid19BenFeedback.class);
					if (covid19BenFeedbackOBJ != null && covid19BenFeedbackOBJ.getSymptoms() != null
							&& covid19BenFeedbackOBJ.getSymptoms().length > 0) {
						StringBuffer sb = new StringBuffer("");
						int pointer = 1;
						for (String s : covid19BenFeedbackOBJ.getSymptoms()) {
							if (pointer == covid19BenFeedbackOBJ.getSymptoms().length)
								sb.append(s);
							else
								sb.append(s + ",");

							pointer++;
						}
						covid19BenFeedbackOBJ.setSymptoms_db(sb.toString());
					}
					
						;
						if (covid19BenFeedbackOBJ != null && covid19BenFeedbackOBJ.getHealthConditions() != null
								&& covid19BenFeedbackOBJ.getHealthConditions().length > 0) {
							StringBuffer sb = new StringBuffer("");
							int pointer = 1;
							for (String s : covid19BenFeedbackOBJ.getHealthConditions()) {
								if (pointer == covid19BenFeedbackOBJ.getHealthConditions().length)
									sb.append(s);
								else
									sb.append(s + ",");

								pointer++;
							}
							covid19BenFeedbackOBJ.setHealthCondition_db(sb.toString());
						}
						
							
							if (covid19BenFeedbackOBJ != null && covid19BenFeedbackOBJ.getSymptoms11Selected() != null
									&& covid19BenFeedbackOBJ.getSymptoms11Selected().length > 0) {
								StringBuffer sb = new StringBuffer("");
								int pointer = 1;
								for (String s : covid19BenFeedbackOBJ.getSymptoms11Selected()) {
									if (pointer == covid19BenFeedbackOBJ.getSymptoms11Selected().length)
										sb.append(s);
									else
										sb.append(s + ",");

									pointer++;
								}
								covid19BenFeedbackOBJ.setSymptoms11Select_db(sb.toString());
							}
							
							if (covid19BenFeedbackOBJ != null && covid19BenFeedbackOBJ.getTravelType() != null
									&& covid19BenFeedbackOBJ.getTravelType().length > 0) {
								StringBuffer sb = new StringBuffer("");
								int pointer = 1;
								for (String s : covid19BenFeedbackOBJ.getTravelType()) {
									if (pointer == covid19BenFeedbackOBJ.getTravelType().length)
										sb.append(s);
									else
										sb.append(s + ",");

									pointer++;
								}
								covid19BenFeedbackOBJ.setTravelTypes_db(sb.toString());
							}	
							
							
							if(covid19BenFeedbackOBJ != null && covid19BenFeedbackOBJ.getForWhomThisTest().get("ID") != null
									&& covid19BenFeedbackOBJ.getForWhomThisTest().get("ID").length() > 0)
							covid19BenFeedbackOBJ.setID(covid19BenFeedbackOBJ.getForWhomThisTest().get("ID"));
							
							
							
							if(covid19BenFeedbackOBJ != null && covid19BenFeedbackOBJ.getForWhomThisTest().get("Value") != null
									&& covid19BenFeedbackOBJ.getForWhomThisTest().get("Value").length() > 0)
							covid19BenFeedbackOBJ.setValue(covid19BenFeedbackOBJ.getForWhomThisTest().get("Value"));	
							
							
							
					if (covid19BenFeedbackOBJ.getTravelledLast14Days().equalsIgnoreCase("YES"))
						covid19BenFeedbackOBJ.setTravelledLast14DaysUI(true);
					else if (covid19BenFeedbackOBJ.getTravelledLast14Days().equalsIgnoreCase("NO"))
						covid19BenFeedbackOBJ.setTravelledLast14DaysUI(false);
					
					
					if (covid19BenFeedbackOBJ.getLaboratoryConfirmed().equalsIgnoreCase("YES"))
						covid19BenFeedbackOBJ.setLaboratoryConfirmedUI(true);
					else if (covid19BenFeedbackOBJ.getLaboratoryConfirmed().equalsIgnoreCase("NO"))
						covid19BenFeedbackOBJ.setLaboratoryConfirmedUI(false);
					
					
					if (covid19BenFeedbackOBJ.getLargeGathering().equalsIgnoreCase("YES"))
						covid19BenFeedbackOBJ.setLargeGatheringUI(true);
					else if (covid19BenFeedbackOBJ.getLargeGathering().equalsIgnoreCase("NO"))
						covid19BenFeedbackOBJ.setLargeGatheringUI(false);
					
					
					if (covid19BenFeedbackOBJ.getPublicExposedPlaces().equalsIgnoreCase("YES"))
						covid19BenFeedbackOBJ.setPublicExposedPlacesUI(true);
					else if (covid19BenFeedbackOBJ.getPublicExposedPlaces().equalsIgnoreCase("NO"))
						covid19BenFeedbackOBJ.setPublicExposedPlacesUI(false);
					
					
					if (covid19BenFeedbackOBJ.getFamliyPublicExposedPlaces().equalsIgnoreCase("YES"))
						covid19BenFeedbackOBJ.setFamliyPublicExposedPlacesUI(true);
					else if (covid19BenFeedbackOBJ.getFamliyPublicExposedPlaces().equalsIgnoreCase("NO"))
						covid19BenFeedbackOBJ.setFamliyPublicExposedPlacesUI(false);

					if (covid19BenFeedbackOBJ != null) {
						Covid19BenFeedback resultSetObj = covid19BenFeedbackRepo.save(covid19BenFeedbackOBJ);

						if(resultSetObj.getCOVID19_104ID() != null) {
							response = "Data saved successfully";
						}
						else
							response = "Error occurred while saving data";
					} 
					}
				catch(Exception e) {
					response="Invalid input";
				}

					
				

		return response;
	}
	
	
	
}
