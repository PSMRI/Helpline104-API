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
package com.iemr.helpline104.service.search;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.iemr.helpline104.data.epidemicOutbreak.T_EpidemicOutbreak;
import com.iemr.helpline104.data.feedback.FeedbackDetails;
import com.iemr.helpline104.data.foodSafetyCopmlaint.T_FoodSafetyCopmlaint;
import com.iemr.helpline104.data.search.SearchCriterea;
import com.iemr.helpline104.repository.epidemicOutbreak.EpidemicOutbreakRepository;
import com.iemr.helpline104.repository.feedback.FeedbackRepository;
import com.iemr.helpline104.repository.foodSafetyCopmlaint.FoodSafetyCopmlaintRepository;
import com.iemr.helpline104.repository.search.SearchOnFSRepo;
import com.iemr.helpline104.repository.search.SearchOnFeedbackRepo;
import com.iemr.helpline104.repository.search.SearchOnIdRepo;
import com.iemr.helpline104.utils.exception.IEMRException;
import com.iemr.helpline104.utils.mapper.InputMapper;

@Service
public class SearchOnIdImpl implements SearchOnId {

	@Autowired
	private SearchOnIdRepo searchOnIdRepo;
	@Autowired
	private SearchOnFeedbackRepo searchOnFeedbackRepo;
	@Autowired
	private FoodSafetyCopmlaintRepository searchOnFSRepo;
	@Autowired
	private FeedbackRepository feedbackRepository;
	@Autowired
	private EpidemicOutbreakRepository epidemicOutbreakRepository;

	@Override
	public String getQuickSearchData(String requestOBJ) throws Exception {

//		List<Objects[]> reportData = null;
//		SearchCriterea request = InputMapper.gson().fromJson(requestOBJ, SearchCriterea.class);
//		Map<String, Object> resMap = new HashMap<String, Object>();
//		if (request != null && request.getScreenID() != null) {
//			switch (request.getScreenID()) {
//			case 1:
//				List<FeedbackDetails> feedbackList = new ArrayList<FeedbackDetails>();
//				ArrayList<FeedbackDetails> lists = null;
//				if (request.getId() != null) {
//					String ar[] = request.getId().split("/");
//					if (ar.length > 0)
//						lists = feedbackRepository.findByFeedbackIDNew(Long.parseLong(ar[ar.length - 1]));
//					
//				}
//				else if(request.getMobileNumber()!=null)
//				{
//					
//				}
//				resMap.put("feedbackList", feedbackList);
//				break;
//			case 2:
//				List<T_EpidemicOutbreak> epidemicOutbreakList = new ArrayList<T_EpidemicOutbreak>();
//				if (request.getMobileNumber() != null && request.getId() == null)
//					reportData = epidemicOutbreakRepository
//							.getEpidemicOutbreakComplaintsByMobile(request.getMobileNumber());
//				else if (request.getMobileNumber() == null && request.getId() != null)
//					reportData = epidemicOutbreakRepository
//							.getEpidemicOutbreakComplaintsByID(Long.parseLong(request.getId()));
//				T_EpidemicOutbreak tEpidemicOutbreak;
//				for (Object[] objects : reportData) {
//					if (objects != null && objects.length > 0) {
//						tEpidemicOutbreak = new T_EpidemicOutbreak((Long) objects[0], (String) objects[1],
//								(Long) objects[2], (String) objects[3], (Integer) objects[4], (Integer) objects[5],
//								(String) objects[6], (Integer) objects[7], (String) objects[8], (Integer) objects[9],
//								(String) objects[10], (String) objects[11], (Timestamp) objects[12]);
//
////						if (benCallID != null)
////							tEpidemicOutbreak.setBenCall(benCallRepository.findByBenCallID(benCallID));
//
//						epidemicOutbreakList.add(tEpidemicOutbreak);
//					}
//				}
//				resMap.put("epidemicOutbreakList", epidemicOutbreakList);
//
//				break;
//
//			case 3:
//				List<T_FoodSafetyCopmlaint> foodSafetyComplaintsList = new ArrayList<T_FoodSafetyCopmlaint>();
//				if (request.getMobileNumber() != null && request.getId() == null)
//					reportData = searchOnFSRepo.getFoodSafetyComplaintsListByMobile(request.getMobileNumber());
//				else if (request.getMobileNumber() == null && request.getId() != null)
//					reportData = searchOnFSRepo
//							.getFoodSafetyComplaintsListByGrievanceID(Long.parseLong(request.getId()));
//				T_FoodSafetyCopmlaint tFoodSafetyCopmlaint;
//
//				for (Object[] objects : reportData) {
//					if (objects != null && objects.length > 0) {
//
//						tFoodSafetyCopmlaint = new T_FoodSafetyCopmlaint((Long) objects[0], (String) objects[1],
//								(Long) objects[2], (String) objects[3], (Byte) objects[4], (Byte) objects[5],
//								(Byte) objects[6], (Byte) objects[7], (Byte) objects[8], (Byte) objects[9],
//								(Byte) objects[10], (Timestamp) objects[11], (String) objects[12], (Byte) objects[13],
//								(String) objects[14], (String) objects[15], (String) objects[16], (Short) objects[17],
//								(String) objects[18], (String) objects[19], (Date) objects[20], (Boolean) objects[21],
//								(Integer) objects[22], (String) objects[23], (Integer) objects[24],
//								(String) objects[25], (Integer) objects[26], (String) objects[27]);
//
//						if (benCallID != null)
//							tFoodSafetyCopmlaint.setBenCall(benCallRepository.findByBenCallID(benCallID));

//						foodSafetyComplaintsList.add(tFoodSafetyCopmlaint);
//					}
//					resMap.put("foodSafetyComplaintsList", foodSafetyComplaintsList);
//				}
//
//				break;
//
//			default:
//				throw new IEMRException("Invalid Request");
//			}
//		} else
//			throw new IEMRException("Invalid Request");

		return null;

	}

}
