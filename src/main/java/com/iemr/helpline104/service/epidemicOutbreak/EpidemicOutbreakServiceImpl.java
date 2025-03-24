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
package com.iemr.helpline104.service.epidemicOutbreak;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iemr.helpline104.controller.epidemicOutbreak.EpidemicOutbreakController;
import com.iemr.helpline104.data.epidemicOutbreak.T_EpidemicOutbreak;
import com.iemr.helpline104.data.feedback.FeedbackDetails;
import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.repository.beneficiarycall.BenCallRepository;
import com.iemr.helpline104.repository.epidemicOutbreak.EpidemicOutbreakRepository;
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

@Service
public class EpidemicOutbreakServiceImpl implements EpidemicOutbreakService {

	@Autowired
	private EpidemicOutbreakRepository epidemicOutbreakRepository;

	@Autowired
	private LocationCityRepository locationCityRepository;

	@Autowired
	private LocationDistrictBlockRepository locationDistrictBlockRepository;

	@Autowired
	private LocationDistrictRepository locationDistrictRepository;

	@Autowired
	FeedbackTypeRepository feedbackRepositorty;

	@Autowired
	private BenCallRepository benCallRepository;
	@Autowired
	private CookieUtil cookieUtil;

	private InputMapper inputMapper = new InputMapper();

	private ConfigProperties properties;

	private Logger logger = LoggerFactory.getLogger(EpidemicOutbreakController.class);

	@Override
	public T_EpidemicOutbreak save(T_EpidemicOutbreak t_epidemicOutbreak, HttpServletRequest request) throws Exception {

		t_epidemicOutbreak = epidemicOutbreakRepository.save(t_epidemicOutbreak);
		String requestID = "EC/" + t_epidemicOutbreak.getAffectedDistrictID() + "/"
				+ new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTimeInMillis()) + "/"
				+ t_epidemicOutbreak.getOutbreakComplaintID();
		t_epidemicOutbreak.setRequestID(requestID);
		t_epidemicOutbreak = epidemicOutbreakRepository.save(t_epidemicOutbreak);

		FeedbackDetails feedbackDetails = new FeedbackDetails();

		feedbackDetails.setBeneficiaryRegID(t_epidemicOutbreak.getBeneficiaryRegID());
		feedbackDetails.setCreatedBy(t_epidemicOutbreak.getCreatedBy());
		feedbackDetails.setBenCallID(t_epidemicOutbreak.getBenCallID());
		feedbackDetails.setRequestID(t_epidemicOutbreak.getRequestID());

		String blockName = locationDistrictBlockRepository.findById(t_epidemicOutbreak.getAffectedDistrictBlockID()).toString();

		String districtName = locationDistrictRepository.findByDistrictID(t_epidemicOutbreak.getAffectedDistrictID());

		String epidemicOutbreakString = "Outbreak ComplaintID: " + t_epidemicOutbreak.getOutbreakComplaintID() + ", "
				+ "Nature Of Complaint: " + t_epidemicOutbreak.getNatureOfComplaint() + ", " + "Total People Affected: "
				+ t_epidemicOutbreak.getTotalPeopleAffected() + ", " + "Affected District: " + districtName + ", "
				+ ", " + "Affected Sub-District: " + blockName + ", " + "Remarks: " + t_epidemicOutbreak.getRemarks();

		feedbackDetails.setFeedback(epidemicOutbreakString);
		feedbackDetails.setServiceID(t_epidemicOutbreak.getServiceID());

		M_FeedbackType feedbackType = feedbackRepositorty.findEpidemicFeedbackTypeID(t_epidemicOutbreak.getServiceID());
		logger.info("feedbackType: " + feedbackType.toString());

		if (feedbackType != null) {
			feedbackDetails.setFeedbackTypeID(feedbackType.getFeedbackTypeID());
		}

		FeedbackDetails[] feedbacks = new FeedbackDetails[1];
		feedbacks[0] = feedbackDetails;

		logger.info(
				"EpidemicOutbreak createFeedback request " + new OutputMapper().gson().toJson(feedbacks).toString());

		createFeedback(new OutputMapper().gson().toJson(feedbacks).toString(), request);

		return t_epidemicOutbreak;
	}

	@Override
	public List<T_EpidemicOutbreak> getEpidemicOutbreakComplaints(Long benificiaryRegID, Long benCallID,
			String requestID, String phoneNo) throws Exception {
		List<T_EpidemicOutbreak> epidemicOutbreakList = new ArrayList<T_EpidemicOutbreak>();

		List<Object[]> epidemicOutbreak = null;

		if (benificiaryRegID != null)
			epidemicOutbreak = epidemicOutbreakRepository.getEpidemicOutbreakComplaints(benificiaryRegID);
		else if (benCallID != null)
			epidemicOutbreak = epidemicOutbreakRepository.getEpidemicOutbreakComplaintsByBenCallID(benCallID);
		else if (requestID != null) {
			// epidemicOutbreak =
			// epidemicOutbreakRepository.getEpidemicOutbreakComplaintsByRequestID(requestID);
			String ar[] = requestID.split("/");
			if (ar.length > 0)
				epidemicOutbreak = epidemicOutbreakRepository
						.getEpidemicOutbreakComplaintsByRequestIDNew(Long.parseLong(ar[ar.length - 1]));
		} else if (phoneNo != null) {
			ArrayList<BigInteger> benRegIds = epidemicOutbreakRepository.findByPhoneNum(phoneNo);
			List<Long> list1 = new ArrayList<>();
			for (BigInteger bi : benRegIds) {
				list1.add(bi.longValue());
			}
			if (list1 != null && list1.size() > 0)
				epidemicOutbreak = epidemicOutbreakRepository.findByBenRegIDs(list1);

		}
		T_EpidemicOutbreak tEpidemicOutbreak;

		for (Object[] objects : epidemicOutbreak) {
			if (objects != null && objects.length > 0) {
				tEpidemicOutbreak = new T_EpidemicOutbreak((Long) objects[0], (String) objects[1], (Long) objects[2],
						(String) objects[3], (Integer) objects[4], (Integer) objects[5], (String) objects[6],
						(Integer) objects[7], (String) objects[8], (Integer) objects[9], (String) objects[10],
						(String) objects[11], (Timestamp) objects[12]);

				if (benCallID != null)
					tEpidemicOutbreak.setBenCall(benCallRepository.findByBenCallID(benCallID));

				epidemicOutbreakList.add(tEpidemicOutbreak);
			}
		}
		return epidemicOutbreakList;
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
	public String UpdateEpidemicOutbreakRequest(T_EpidemicOutbreak epidemicOutbreakComplaint) throws Exception {
		T_EpidemicOutbreak resultSet = epidemicOutbreakRepository
				.searchByRequestID(epidemicOutbreakComplaint.getRequestID());
		if (resultSet != null) {
			if (epidemicOutbreakComplaint.getAffectedDistrictID() != null)
				resultSet.setAffectedDistrictID(epidemicOutbreakComplaint.getAffectedDistrictID());
			if (epidemicOutbreakComplaint.getAffectedDistrictBlockID() != null)
				resultSet.setAffectedDistrictBlockID(epidemicOutbreakComplaint.getAffectedDistrictBlockID());
			if (epidemicOutbreakComplaint.getAffectedVillageID() != null)
				resultSet.setAffectedVillageID(epidemicOutbreakComplaint.getAffectedVillageID());
			if (epidemicOutbreakComplaint.getNatureOfComplaint() != null)
				resultSet.setNatureOfComplaint(epidemicOutbreakComplaint.getNatureOfComplaint());
			if (epidemicOutbreakComplaint.getTotalPeopleAffected() != null)
				resultSet.setTotalPeopleAffected(epidemicOutbreakComplaint.getTotalPeopleAffected());
			if (epidemicOutbreakComplaint.getRemarks() != null)
				resultSet.setRemarks(epidemicOutbreakComplaint.getRemarks());

			epidemicOutbreakComplaint = epidemicOutbreakRepository.save(resultSet);

			if (resultSet.getOutbreakComplaintID() == epidemicOutbreakComplaint.getOutbreakComplaintID()) {
				return "Data updated successfully";
			} else
				return null;

		} else
			throw new Exception("No record found for given request ID");
	}

}