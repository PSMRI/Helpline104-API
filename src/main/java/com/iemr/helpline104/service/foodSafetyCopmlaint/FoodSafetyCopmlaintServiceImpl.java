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
package com.iemr.helpline104.service.foodSafetyCopmlaint;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.feedback.FeedbackDetails;
import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.data.foodSafetyCopmlaint.T_FoodSafetyCopmlaint;
import com.iemr.helpline104.repository.beneficiarycall.BenCallRepository;
import com.iemr.helpline104.repository.feedbackType.FeedbackTypeRepository;
import com.iemr.helpline104.repository.foodSafetyCopmlaint.FoodSafetyCopmlaintRepository;
import com.iemr.helpline104.utils.config.ConfigProperties;
import com.iemr.helpline104.utils.exception.IEMRException;
import com.iemr.helpline104.utils.http.HttpUtils;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.mapper.OutputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

@Service
public class FoodSafetyCopmlaintServiceImpl implements FoodSafetyCopmlaintService {

	@Autowired
	private FoodSafetyCopmlaintRepository foodSafetyCopmlaintRepository;

	@Autowired
	FeedbackTypeRepository feedbackRepositorty;

	@Autowired
	private BenCallRepository benCallRepository;

	private InputMapper inputMapper = new InputMapper();

	private ConfigProperties properties;

	private Logger logger = LoggerFactory.getLogger(FoodSafetyCopmlaintService.class);

	@Override
	public List<T_FoodSafetyCopmlaint> getFoodSafetyComplaints(Long benificiaryRegID, Long benCallID, String requestID,
			String phoneNo) throws Exception {
		List<T_FoodSafetyCopmlaint> foodSafetyComplaintsList = new ArrayList<T_FoodSafetyCopmlaint>();

		List<Objects[]> foodSafetyComplaints = null;
		if (benificiaryRegID != null)
			foodSafetyComplaints = foodSafetyCopmlaintRepository.getFoodSafetyRequests(benificiaryRegID);
		else if (benCallID != null)
			foodSafetyComplaints = foodSafetyCopmlaintRepository.getFoodSafetyRequestsByBenCallID(benCallID);
		else if (requestID != null) {

			// foodSafetyComplaints =
			// foodSafetyCopmlaintRepository.getFoodSafetyRequestsByRequestID(requestID);
			String ar[] = requestID.split("/");
			if (ar.length > 0)
				foodSafetyComplaints = foodSafetyCopmlaintRepository
						.getFoodSafetyRequestsByRequestIDNew(Long.parseLong(ar[ar.length - 1]));
		}

		else if (phoneNo != null) {
			ArrayList<BigInteger> benRegIds = foodSafetyCopmlaintRepository.findByPhoneNum(phoneNo);
			List<Long> list1 = new ArrayList<>();
			for (BigInteger bi : benRegIds) {
				list1.add(bi.longValue());
			}
			if (list1 != null && list1.size() > 0)
				foodSafetyComplaints = foodSafetyCopmlaintRepository.findByBenRegIDs(list1);
//			else
//				throw new IEMRException("No generic grievance found with this phone number");

		}
		T_FoodSafetyCopmlaint tFoodSafetyCopmlaint;

		for (Object[] objects : foodSafetyComplaints) {
			if (objects != null && objects.length > 0) {

				tFoodSafetyCopmlaint = new T_FoodSafetyCopmlaint((Long) objects[0], (String) objects[1],
						(Long) objects[2], (String) objects[3], (Byte) objects[4], (Byte) objects[5], (Byte) objects[6],
						(Byte) objects[7], (Byte) objects[8], (Byte) objects[9], (Byte) objects[10],
						(Timestamp) objects[11], (String) objects[12], (Byte) objects[13], (String) objects[14],
						(String) objects[15], (String) objects[16], (Short) objects[17], (String) objects[18],
						(String) objects[19], (Date) objects[20], (Boolean) objects[21], (Integer) objects[22],
						(String) objects[23], (Integer) objects[24], (String) objects[25], (Integer) objects[26],
						(String) objects[27]);

				if (benCallID != null)
					tFoodSafetyCopmlaint.setBenCall(benCallRepository.findByBenCallID(benCallID));

				foodSafetyComplaintsList.add(tFoodSafetyCopmlaint);
			}
		}
		return foodSafetyComplaintsList;
	}

	@Override
	public T_FoodSafetyCopmlaint save(T_FoodSafetyCopmlaint t_foodSafetyCopmlaint, HttpServletRequest request)
			throws Exception {

		t_foodSafetyCopmlaint = foodSafetyCopmlaintRepository.save(t_foodSafetyCopmlaint);

		String requestID = "FS/" + t_foodSafetyCopmlaint.getDistrictID() + "/"
				+ new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTimeInMillis()) + "/"
				+ t_foodSafetyCopmlaint.getFSComplaintID();
		t_foodSafetyCopmlaint.setRequestID(requestID);

		t_foodSafetyCopmlaint = foodSafetyCopmlaintRepository.save(t_foodSafetyCopmlaint);

		FeedbackDetails feedbackDetails = new FeedbackDetails();

		feedbackDetails.setBeneficiaryRegID(t_foodSafetyCopmlaint.getBeneficiaryRegID());
		feedbackDetails.setFeedbackTypeID(t_foodSafetyCopmlaint.getFeedbackTypeID());
		feedbackDetails.setCreatedBy(t_foodSafetyCopmlaint.getCreatedBy());
		feedbackDetails.setBenCallID(t_foodSafetyCopmlaint.getBenCallID());
		feedbackDetails.setRequestID(requestID);

		String foodSafetyCopmlaintString = "FS ComplaintID: " + t_foodSafetyCopmlaint.getFSComplaintID() + ", "
				+ "Type Of Request: " + t_foodSafetyCopmlaint.getTypeOfRequest();

		if (t_foodSafetyCopmlaint.getIsDiarrhea() == 1)
			foodSafetyCopmlaintString += ", Is Diarrhea: Yes";
		if (t_foodSafetyCopmlaint.getIsVomiting() == 1)
			foodSafetyCopmlaintString += ", Is Vomiting: Yes";
		if (t_foodSafetyCopmlaint.getIsAbdominalPain() == 1)
			foodSafetyCopmlaintString += ", Is Abdominal Pain: Yes";
		if (t_foodSafetyCopmlaint.getIsChillsOrRigors() == 1)
			foodSafetyCopmlaintString += ", Is Chills Or Rigors: Yes";
		if (t_foodSafetyCopmlaint.getIsGiddiness() == 1)
			foodSafetyCopmlaintString += ", Is Giddiness: Yes";
		if (t_foodSafetyCopmlaint.getIsDehydration() == 1)
			foodSafetyCopmlaintString += ", Is Dehydration: Yes";
		if (t_foodSafetyCopmlaint.getIsRashes() == 1)
			foodSafetyCopmlaintString += ", Is Rashes: Yes";

		foodSafetyCopmlaintString += ", From When: " + t_foodSafetyCopmlaint.getFromWhen() + ", " + "History Of Diet:"
				+ t_foodSafetyCopmlaint.getHistoryOfDiet();

		if (t_foodSafetyCopmlaint.getIsFoodConsumed() == 1)
			foodSafetyCopmlaintString += ", Is Food Consumed: Yes";

		foodSafetyCopmlaintString += ", Type Of Food:" + t_foodSafetyCopmlaint.getTypeOfFood() + ", "
				+ "Food Consumed From: " + t_foodSafetyCopmlaint.getFoodConsumedFrom() + ", " + "Associated Symptoms: "
				+ t_foodSafetyCopmlaint.getAssociatedSymptoms() + ", " + "Remarks: "
				+ t_foodSafetyCopmlaint.getRemarks();

		feedbackDetails.setFeedback(foodSafetyCopmlaintString);
		feedbackDetails.setServiceID(t_foodSafetyCopmlaint.getServiceID());

		M_FeedbackType feedbackType = feedbackRepositorty
				.findFoodSafetyFeedbackTypeID(t_foodSafetyCopmlaint.getServiceID());
		logger.info("feedbackType: for " + t_foodSafetyCopmlaint.getServiceID());

		if (feedbackType != null) {
			logger.info("feedbackType: " + feedbackType.toString());
			feedbackDetails.setFeedbackTypeID(feedbackType.getFeedbackTypeID());
		}

		FeedbackDetails[] feedbacks = new FeedbackDetails[1];
		feedbacks[0] = feedbackDetails;

		createFeedback(new OutputMapper().gson().toJson(feedbacks).toString(), request);

		return t_foodSafetyCopmlaint;
	}

	private OutputResponse createFeedback(String feedbackDetails, HttpServletRequest request) throws IEMRException {
		HttpUtils utils = new HttpUtils();
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("Authorization", request.getHeader("Authorization"));
		String responseStr = utils.post(
				properties.getPropertyByName("common-url") + "/" + properties.getPropertyByName("create-feedback"),
				feedbackDetails, header);
		OutputResponse response = inputMapper.gson().fromJson(responseStr, OutputResponse.class);
		// response.setResponse(responseStr);
		return response;
	}

}
