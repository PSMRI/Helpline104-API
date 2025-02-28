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
package com.iemr.helpline104.service.balVivah;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.iemr.helpline104.controller.balVivha.BalVivahController;
import com.iemr.helpline104.data.balVivah.BalVivahComplaint;
import com.iemr.helpline104.data.feedback.FeedbackDetails;
import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.repository.balVivah.BalVivahComplaintRepo;
import com.iemr.helpline104.repository.beneficiarycall.BenCallRepository;
import com.iemr.helpline104.repository.epidemicOutbreak.EpidemicOutbreakRepository;
import com.iemr.helpline104.repository.feedback.FeedbackRepository;
import com.iemr.helpline104.repository.feedbackType.FeedbackTypeRepository;
import com.iemr.helpline104.repository.location.LocationCityRepository;
import com.iemr.helpline104.repository.location.LocationDistrictBlockRepository;
import com.iemr.helpline104.repository.location.LocationDistrictRepository;
import com.iemr.helpline104.utils.CookieUtil;
import com.iemr.helpline104.utils.config.ConfigProperties;
import com.iemr.helpline104.utils.exception.IEMRException;
import com.iemr.helpline104.utils.http.HttpUtils;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.mapper.OutputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BalVivahComplaintImpl implements BalVivahComplaintService {

	@Autowired
	private BalVivahComplaintRepo balVivahComplaintRepo;

	@Autowired
	private LocationCityRepository locationCityRepository;

	@Autowired
	private LocationDistrictBlockRepository locationDistrictBlockRepository;

	@Autowired
	private LocationDistrictRepository locationDistrictRepository;

	@Autowired
	FeedbackTypeRepository feedbackRepositorty;

	@Autowired
	FeedbackRepository feedbackRepository;

	@Autowired
	private BenCallRepository benCallRepository;

	@Autowired
	private EpidemicOutbreakRepository epidemicOutbreakRepository;
	@Autowired
	private CookieUtil cookieUtil;

	private InputMapper inputMapper = new InputMapper();

	private ConfigProperties properties;

	private Logger logger = LoggerFactory.getLogger(BalVivahController.class);

	@Transactional(rollbackFor = Exception.class)

	public String save(BalVivahComplaint balVivahComplaint, HttpServletRequest request) throws Exception {

		String response = "";
		Map<String, Object> responseMap = new HashMap<>();

		balVivahComplaint = balVivahComplaintRepo.save(balVivahComplaint);
		String requestID = "BV/" + balVivahComplaint.getChildDistrict() + "/"
				+ new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTimeInMillis()) + "/"
				+ balVivahComplaint.getBalVivaComplaintID();
		balVivahComplaint.setRequestID(requestID);
		balVivahComplaint.setDeleted(false);
		balVivahComplaint = balVivahComplaintRepo.save(balVivahComplaint);

		FeedbackDetails feedbackDetails = new FeedbackDetails();

		feedbackDetails.setBeneficiaryRegID(balVivahComplaint.getBeneficiaryRegID());
		feedbackDetails.setCreatedBy(balVivahComplaint.getCreatedBy());
		feedbackDetails.setBenCallID(balVivahComplaint.getBenCallID());
		feedbackDetails.setRequestID(balVivahComplaint.getRequestID());

		feedbackDetails.setProviderServiceMapID(balVivahComplaint.getProviderServiceMapID());
		feedbackDetails.setServiceID(balVivahComplaint.getProviderServiceMapID());
		Timestamp ts = balVivahComplaint.getComplaintDate();
		Date date = new Date(ts.getTime());
		feedbackDetails.setServiceAvailDate(date);

		M_FeedbackType feedbackType = feedbackRepositorty
				.findBalVivahFeedbackTypeID(balVivahComplaint.getProviderServiceMapID());
		logger.info("feedbackType: " + feedbackType.toString());

		if (feedbackType != null) {
			feedbackDetails.setFeedbackTypeID(feedbackType.getFeedbackTypeID());
		}

		FeedbackDetails[] feedbacks = new FeedbackDetails[1];
		feedbacks[0] = feedbackDetails;

		createFeedback(new OutputMapper().gson().toJson(feedbacks).toString(), request);

		responseMap.put("requestID", balVivahComplaint.getRequestID());
		responseMap.put("balVivahComplaintID", balVivahComplaint.getBalVivaComplaintID());

		return new Gson().toJson(responseMap);

	}

	public String getWorklistRequests(Long beneficiaryRegID, String phoneNo, String requestID) {
		List<BalVivahComplaint> feedbackList = new ArrayList<BalVivahComplaint>();
		List<Object[]> feedback = null;
		BalVivahComplaint BalVivahC;

		if (beneficiaryRegID != null)
			feedback = balVivahComplaintRepo.findByBeneficiaryID(beneficiaryRegID);
		else if (phoneNo != null) {
			ArrayList<BigInteger> benRegIds = epidemicOutbreakRepository.findByPhoneNum(phoneNo);
			List<Long> list1 = new ArrayList<>();
			for (BigInteger bi : benRegIds) {
				list1.add(bi.longValue());
			}
			if (list1 != null && list1.size() > 0)
				feedback = balVivahComplaintRepo.findByBeneficiaryRegIDs(list1);
		} else if (requestID != null) {
			String ar[] = requestID.split("/");
			if (ar.length > 0)
				feedback = balVivahComplaintRepo.findByRequestID(Long.parseLong(ar[ar.length - 1]));
		}

		if (feedback != null)
			for (Object[] objects : feedback) {
				if (objects != null && objects.length > 0) {
					BalVivahC = new BalVivahComplaint((Long) objects[0], (String) objects[1], (String) objects[2],
							(String) objects[3], (Date) objects[4], (String) objects[5], (String) objects[6],
							(String) objects[7], (Timestamp) objects[8], (Integer) objects[9], (String) objects[10],
							(Integer) objects[11], (String) objects[12], (Integer) objects[13], (String) objects[14],
							(Short) objects[15], (String) objects[16]);

					feedbackList.add(BalVivahC);

				}
			}

		return new Gson().toJson(feedbackList);
	}

	private OutputResponse createFeedback(String feedbackDetails, HttpServletRequest request) throws IEMRException, JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper objectMapper = new ObjectMapper();
		String jwtTokenFromCookie = cookieUtil.getJwtTokenFromCookie(request);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/json");
		headers.add("AUTHORIZATION", request.getHeader("Authorization"));
		headers.add("Jwttoken", jwtTokenFromCookie);
		String url = properties.getPropertyByName("common-url") + "/" + properties.getPropertyByName("create-feedback");
		HttpEntity<Object> request1 = new HttpEntity<Object>(feedbackDetails, headers);
		ResponseEntity<String> responseStr = restTemplate.exchange(url, HttpMethod.POST, request1, String.class);
		OutputResponse response = objectMapper.readValue(responseStr.getBody(), OutputResponse.class);

		return response;
	}

	@Override
	public String updateBalVivahRequest(BalVivahComplaint balVivahComplaint) throws Exception {

		BalVivahComplaint resultSet = balVivahComplaintRepo.searchByRequestID(balVivahComplaint.getRequestID());
		if (resultSet != null) {
			if (balVivahComplaint.getSubjectOfComplaint() != null)
				resultSet.setSubjectOfComplaint(balVivahComplaint.getSubjectOfComplaint());
			if (balVivahComplaint.getChildName() != null)
				resultSet.setChildName(balVivahComplaint.getChildName());
			if (balVivahComplaint.getChildFatherName() != null)
				resultSet.setChildFatherName(balVivahComplaint.getChildFatherName());
			if (balVivahComplaint.getChildAge() != null)
				resultSet.setChildAge(balVivahComplaint.getChildAge());
			if (balVivahComplaint.getChildGender() != null)
				resultSet.setChildGender(balVivahComplaint.getChildGender());
			if (balVivahComplaint.getChildState() != null)
				resultSet.setChildState(balVivahComplaint.getChildState());
			if (balVivahComplaint.getChildFatherState() != null)
				resultSet.setChildFatherState(balVivahComplaint.getChildFatherState());
			if (balVivahComplaint.getChildDistrict() != null)
				resultSet.setChildDistrict(balVivahComplaint.getChildDistrict());
			if (balVivahComplaint.getChildFatherDistrict() != null)
				resultSet.setChildFatherDistrict(balVivahComplaint.getChildFatherDistrict());
			if (balVivahComplaint.getChildSubDistrict() != null)
				resultSet.setChildSubDistrict(balVivahComplaint.getChildSubDistrict());
			if (balVivahComplaint.getChildFatherSubDistrict() != null)
				resultSet.setChildFatherSubDistrict(balVivahComplaint.getChildFatherSubDistrict());
			if (balVivahComplaint.getChildVillage() != null)
				resultSet.setChildVillage(balVivahComplaint.getChildVillage());
			if (balVivahComplaint.getChildFatherVillage() != null)
				resultSet.setChildFatherVillage(balVivahComplaint.getChildFatherVillage());
			if (balVivahComplaint.getMarriageDate() != null)
				resultSet.setMarriageDate(balVivahComplaint.getMarriageDate());
			if (balVivahComplaint.getComplaintDate() != null)
				resultSet.setComplaintDate(balVivahComplaint.getComplaintDate());

			balVivahComplaint = balVivahComplaintRepo.save(resultSet);

			if (resultSet.getBalVivaComplaintID() == balVivahComplaint.getBalVivaComplaintID()) {
				return "Data updated successfully";
			} else
				return null;

		} else
			throw new Exception("No record found for given request ID");
	}
}
