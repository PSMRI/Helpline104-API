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
package com.iemr.helpline104.service.feedback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.iemr.helpline104.data.feedback.FeedbackDetails;
import com.iemr.helpline104.data.feedback.FeedbackRequestDetails;
import com.iemr.helpline104.repository.beneficiarycall.BeneficiaryCallRepository;
import com.iemr.helpline104.repository.beneficiarycall.ServicesHistoryRepository;
import com.iemr.helpline104.repository.bloodRequest.InstituteRepository;
import com.iemr.helpline104.repository.feedback.FeedbackRepository;
import com.iemr.helpline104.repository.feedbackType.FeedbackTypeRepository;
import com.iemr.helpline104.utils.config.ConfigProperties;
import com.iemr.helpline104.utils.exception.IEMRException;
import com.iemr.helpline104.utils.http.HttpUtils;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.mapper.OutputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	private Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

	private BeneficiaryCallRepository beneficiaryCallRepository;

	@Autowired(required = false)
	private InstituteRepository instituteRepo;

	@Autowired(required = false)
	public void getBenCalServiceCatSubcatMappingRepo(BeneficiaryCallRepository benCalServiceCatSubcatMappingRepo) {
		this.beneficiaryCallRepository = benCalServiceCatSubcatMappingRepo;
	}

	@Autowired(required = false)
	private ServicesHistoryRepository servicesHistoryRepository;

	@Autowired(required = false)
	private FeedbackTypeRepository feedbackRepositorty;

	/**
	 * Feedback Repository
	 */
	private FeedbackRepository feedbackRepository;

	@Autowired(required = false)
	public void setFeedbackRepository(FeedbackRepository feedbackRepository) {

		this.feedbackRepository = feedbackRepository;
	}

	private InputMapper inputMapper = new InputMapper();

	private ConfigProperties properties;

	@Autowired(required = false)
	public void setProperties(ConfigProperties properties) {
		this.properties = properties;
	}

	@Override
	public List<FeedbackDetails> getFeedbackRequests(long id) {

		List<FeedbackDetails> feedbackList = new ArrayList<FeedbackDetails>();
		ArrayList<Objects[]> lists = feedbackRepository.findByBeneficiaryID(id);

		for (Object[] objects : lists) {
			if (objects != null && objects.length >= 7) {
				feedbackList.add(new FeedbackDetails((Long) objects[0], (Short) objects[1], (Short) objects[2],
						(Short) objects[3], (String) objects[4], (String) objects[5], (String) objects[6]));
			}
		}
		return feedbackList;
	}

	@Override
	public List<FeedbackDetails> getFeedbackRequest(long id) {

		List<FeedbackDetails> feedbackList = new ArrayList<FeedbackDetails>();
		ArrayList<Objects[]> lists = feedbackRepository.findByFeedbackID(id);

		for (Object[] objects : lists) {
			if (objects != null && objects.length >= 7) {
				feedbackList.add(new FeedbackDetails((Long) objects[0], (Short) objects[1], (Short) objects[2],
						(Short) objects[3], (String) objects[4], (String) objects[5], (String) objects[6]));
			}
		}
		return feedbackList;
	}

	@Override
	public FeedbackDetails createFeedback(FeedbackDetails feedbackDetails) {

		/*
		 * String feedbackDetailsJson = new
		 * Gson().toJson(payload.get("feedbackDetails")); String
		 * feedbackRequestDetailsJson = new
		 * Gson().toJson(payload.get("feedbackRequestDetails"));
		 * 
		 * FeedbackDetails feedbackDetails = (FeedbackDetails) new
		 * Gson().fromJson(feedbackDetailsJson, FeedbackDetails.class);
		 * FeedbackRequestDetails[] feedbackRequestArray = (FeedbackRequestDetails[])
		 * new Gson().fromJson(feedbackRequestDetailsJson,
		 * FeedbackRequestDetails[].class);
		 */

		List<FeedbackRequestDetails> feedbackRequestList = feedbackDetails.getFeedbackRequestDetails();

		for (FeedbackRequestDetails frd : feedbackRequestList) {

			frd.setFeedback(feedbackDetails);
		}

		feedbackDetails.setFeedbackRequestDetails(feedbackRequestList);

		return feedbackRepository.save(feedbackDetails);
	}

	@Override
	public String updateFeedback(FeedbackDetails feedbackDetails) throws Exception {
		String response;
		FeedbackDetails resultSet = feedbackRepository.searchByRequestID(feedbackDetails.getRequestID());
		if (resultSet != null) {
			if (feedbackDetails.getServiceAvailDate() != null)
				resultSet.setServiceAvailDate(feedbackDetails.getServiceAvailDate());
			if (feedbackDetails.getInstiName() != null)
				resultSet.setInstiName(feedbackDetails.getInstiName());
			if (feedbackDetails.getStateID() != null)
				resultSet.setStateID(feedbackDetails.getStateID());
			if (feedbackDetails.getDistrictID() != null)
				resultSet.setDistrictID(feedbackDetails.getDistrictID());
			if (feedbackDetails.getBlockID() != null)
				resultSet.setBlockID(feedbackDetails.getBlockID());
			if (feedbackDetails.getDistrictBranchID() != null)
				resultSet.setDistrictBranchID(feedbackDetails.getDistrictBranchID());
			if (feedbackDetails.getFeedbackTypeID() != null)
				resultSet.setFeedbackTypeID(feedbackDetails.getFeedbackTypeID());
			if (feedbackDetails.getFeedbackNatureID() != null)
				resultSet.setFeedbackNatureID(feedbackDetails.getFeedbackNatureID());
			if (feedbackDetails.getDesignationID() != null)
				resultSet.setDesignationID(feedbackDetails.getDesignationID());
			if (feedbackDetails.getInstituteTypeID() != null)
				resultSet.setInstituteTypeID(feedbackDetails.getInstituteTypeID());
			if (feedbackDetails.getInstitutionID() != null) {
				resultSet.setInstitutionID(feedbackDetails.getInstitutionID());
				String name = instituteRepo.getInstituteName((feedbackDetails.getInstitutionID()).intValue());
				if (name != null)
					resultSet.setInstiName(name);
			}
			if (feedbackDetails.getFeedbackAgainst() != null)
				resultSet.setFeedbackAgainst(feedbackDetails.getFeedbackAgainst());
			if (feedbackDetails.getFeedback() != null)
				resultSet.setFeedback(feedbackDetails.getFeedback());
			if (feedbackDetails.getSeverityID() != null)
				resultSet.setSeverityID(feedbackDetails.getSeverityID());

			feedbackDetails = feedbackRepository.save(resultSet);
			if (resultSet.getFeedbackID() == feedbackDetails.getFeedbackID()) {
				response = "Data updated successfully";
				return response;
			} else
				return null;
		} else
			throw new Exception("No record found for given request ID");

	}

	// @Override
	// // public String saveFeedbackFromCustomer(Iterable<FeedbackDetails>
	// // feedbackDetails) {
	// public String saveFeedbackFromCustomer(String feedbackDetails) {
	//
	// Map<String, Long> resMap = new HashMap<String, Long>();
	// FeedbackDetails[] feedbackDetailsArray =
	// inputMapper.gson().fromJson(feedbackDetails, FeedbackDetails[].class);
	// Iterable<FeedbackDetails> feedbackSavedData = null;
	// GsonBuilder builder = new GsonBuilder();
	// builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
	// // Type listType = new TypeToken<List<FeedbackDetails>>(){}.getType();
	// List<FeedbackDetails> feedbackDetailsList =
	// Arrays.asList(feedbackDetailsArray);
	// // List<FeedbackDetails> feedbackDetailsList=
	// // builder.create().fromJson(feedbackDetails, listType);
	// feedbackSavedData = feedbackRepository.save(feedbackDetailsList);
	// if (feedbackSavedData != null) {
	// List<BenCallServicesMappingHistory> obj = new ArrayList<>();
	// for (FeedbackDetails f : feedbackSavedData) {
	// BenCallServicesMappingHistory benCallServicesMappingHistory = new
	// BenCallServicesMappingHistory(
	// f.getBeneficiaryRegID(), f.getBenCallID(), f.getServiceID(),
	// f.getFeedbackID(), false,
	// f.getCreatedBy());
	// obj.add(benCallServicesMappingHistory);
	// }
	// Iterable<BenCallServicesMappingHistory> dataInserted =
	// benCalServiceCatSubcatMappingRepo.save(obj);
	// for (BenCallServicesMappingHistory m : dataInserted) {
	// resMap.put("feedBackId", m.getFeedbackID());
	// }
	// } else {
	// resMap.put("feedBackId", (long) 0);
	// }
	// System.out.println(resMap.toString());
	// return new Gson().toJson(resMap);
	// }

	@Override
	public String saveFeedbackFromCustomer(String feedbackDetails, HttpServletRequest request) throws Exception {
		Map<String, String> resMap = new HashMap<String, String>();
		OutputResponse response = createFeedback(feedbackDetails, request);
		// resMap.put("feedBackId", "0");
		if (response.isSuccess()) {
			try {
				logger.info(response.getData());
				FeedbackDetails[] feedbackSavedData = inputMapper.gson().fromJson(response.getData(),
						FeedbackDetails[].class);
				// feedbackSavedData = inputMapper.gson().fromJson();
				if (feedbackSavedData != null) {
					/** services availed is already handled in RO level **/
					/*
					 * List<BenCallServicesMappingHistory> obj = new ArrayList<>(); for
					 * (FeedbackDetails f : feedbackSavedData) {
					 * 
					 * BenCallServicesMappingHistory benCallServicesMappingHistory = new
					 * BenCallServicesMappingHistory( f.getBeneficiaryRegID(), f.getBenCallID(),
					 * f.get1097ServiceID(), false, f.getCreatedBy());
					 * obj.add(benCallServicesMappingHistory); }
					 * Iterable<BenCallServicesMappingHistory> dataInserted =
					 * servicesHistoryRepository.save(obj);
					 */

					String requestID = "";

					for (FeedbackDetails feedback : feedbackSavedData) {

						String feedbackTypeCode = feedbackRepositorty
								.findFeedbackTypeCode(feedback.getFeedbackTypeID());

						logger.info("feedbackTypeCode: " + feedbackTypeCode);

						if (feedbackTypeCode == null) {
							feedbackTypeCode = "GC";
						}

						requestID = feedbackTypeCode + "/" + feedback.getDistrictID() + "/"
								+ new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTimeInMillis())
								+ "/" + feedback.getFeedbackID();
						feedback.setRequestID(requestID);
						feedback.setDeleted(false);

					}
					createFeedback(new OutputMapper().gson().toJson(feedbackSavedData).toString(), request);

					for (FeedbackDetails m : feedbackSavedData) {
						resMap.put("feedBackId", m.getFeedbackID() + "");
						resMap.put("requestID", requestID);
					}
				}
			} catch (Exception e) {
				throw new Exception(e);
			}
		} else
			throw new Exception("error in saving feedback, please contact administrator");

		if (resMap.containsKey("feedBackId") && resMap.containsKey("requestID"))
			return new Gson().toJson(resMap);
		else
			return null;
	}

	// private OutputResponse createFeedback(String feedbackDetails) {
	// HttpUtils utils = new HttpUtils();
	// String responseStr = utils.post(
	// properties.getPropertyByName("common-url") + "/" +
	// properties.getPropertyByName("create-feedback"),
	// feedbackDetails);
	// OutputResponse response = null;
	// try {
	// response = inputMapper.gson().fromJson(responseStr, OutputResponse.class);
	// } catch (IEMRException e) {
	//
	// }
	// response.setResponse(responseStr);
	// return response;
	// }

	private OutputResponse createFeedback(String feedbackDetails, HttpServletRequest request) throws IEMRException {

		logger.info("createFeedback request: " + feedbackDetails);

		HttpUtils utils = new HttpUtils();
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("Authorization", request.getHeader("Authorization"));
		String responseStr = utils.post(
				properties.getPropertyByName("common-url") + "/" + properties.getPropertyByName("create-feedback"),
				feedbackDetails, header);
		OutputResponse response = inputMapper.gson().fromJson(responseStr, OutputResponse.class);
		// response.setResponse(responseStr);
		logger.info("createFeedback resonse from common: " + response);
		return response;
	}

}
