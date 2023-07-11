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
package com.iemr.helpline104.service.bloodRequest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.bloodRequest.BloodBank;
import com.iemr.helpline104.data.bloodRequest.RequestedBloodBank;
import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;
import com.iemr.helpline104.repository.beneficiarycall.BenCallRepository;
import com.iemr.helpline104.repository.bloodRequest.BloodRequestRepository;
import com.iemr.helpline104.repository.bloodRequest.InstituteRepository;
import com.iemr.helpline104.repository.bloodRequest.RequestedBloodBankRepository;

@Service
public class BloodRequestServiceImpl implements BloodRequestService
{

	private Logger logger = LoggerFactory.getLogger(BloodRequestService.class);

	@Autowired
	private BloodRequestRepository bloodRequestRepository;

	@Autowired
	private InstituteRepository instituteRepository;

	@Autowired
	private BenCallRepository benCallRepository;
	
	@Autowired
	private RequestedBloodBankRepository requestedBloodBankRepository;

	@Override
	public List<T_BloodRequest> getBloodRequest(Long benificiaryRegID, String bloodReqID, Long benCallID) throws Exception
	{
		List<T_BloodRequest> bloodRequestList = new ArrayList<T_BloodRequest>();

		logger.info("benificiaryRegID: " + benificiaryRegID + " ,bloodReqID: " + bloodReqID);

		List<Objects[]> bloodRequest = null;

		if (benificiaryRegID != null)
			bloodRequest = bloodRequestRepository.getBloodRequestsByBeneficiaryRegID(benificiaryRegID);
		else if (bloodReqID != null)
			bloodRequest = bloodRequestRepository.getBloodRequestsByRequestID(bloodReqID);
		else if (benCallID != null)
			bloodRequest = bloodRequestRepository.getBloodRequestsByBenCallID(benCallID);

		T_BloodRequest bRequest;
		for (Object[] objects : bloodRequest)
		{
			if (objects != null && objects.length > 0)
			{
				

				bRequest = new T_BloodRequest(
						(Long) objects[0], (String) objects[1], (Long) objects[2], (Long) objects[3], (String) objects[4],
						(Integer) objects[5], (Short) objects[6], (String) objects[7], (String) objects[8], (Integer) objects[9],
						(String) objects[10], (String) objects[11], (Integer) objects[12], (String) objects[13], (String) objects[14],
						(Integer) objects[15], (String) objects[16], (String) objects[17], (String) objects[18], (String) objects[19],
						(Integer) objects[20], (String) objects[21], (Boolean) objects[22], (Timestamp) objects[23], (String) objects[24],
						(String) objects[25], (String) objects[26], (String) objects[27], (String) objects[28], (String) objects[29]);
				
				if (benCallID != null)
				bRequest.setBenCall(benCallRepository.findByBenCallID(benCallID));
				
				//List<RequestedBloodBank> requestedBloodBanks = ;
				bRequest.setRequestedBloodBank(requestedBloodBankRepository.findByBloodReqIDAndDeleted((Long) objects[0],false));
				
				bloodRequestList.add(bRequest);
			}
		}
		
	
		
		return bloodRequestList;
	}

	@Override
	public T_BloodRequest save(T_BloodRequest t_bloodRequest) throws Exception
	{
		List<RequestedBloodBank> requestedBloodBanks = t_bloodRequest.getRequestedBloodBank();
		
		if (t_bloodRequest.getBloodReqID() == null)
		{
			logger.info("T_BloodRequest: save");
			t_bloodRequest = bloodRequestRepository.save(t_bloodRequest);
			String requestID = "BR/"
					+ t_bloodRequest.getDistrictID() + "/" + new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTimeInMillis()) + "/"
					+ t_bloodRequest.getBloodReqID();
			t_bloodRequest.setRequestID(requestID);
			t_bloodRequest = bloodRequestRepository.save(t_bloodRequest);
		} else
		{
			t_bloodRequest = update(t_bloodRequest);
		}
		
		if(requestedBloodBanks != null)
		{
			
		for (RequestedBloodBank bloodBank : requestedBloodBanks)
		{
			bloodBank.setBloodReqID(t_bloodRequest.getBloodReqID());
			bloodBank.setCreatedBy(t_bloodRequest.getCreatedBy());
		}		
		
		requestedBloodBankRepository.save(requestedBloodBanks);
		
		}
		
		return t_bloodRequest;

	}

	private T_BloodRequest update(T_BloodRequest t_bloodRequest) throws Exception
	{

		logger.info("T_BloodRequest: update");

		// T_BloodRequest bloodRequest =
		// bloodRequestRepository.findOne(t_bloodRequest.getBloodReqID());
		// T_BloodRequest bloodRequest =
		// getBloodRequest(null,t_bloodRequest.getRequestID()).get(0);
		// logger.info("T_BloodRequest:"+bloodRequest.toString());
		/*
		 * if(bloodRequest != null) { logger.info("T_BloodRequest: updating");
		 * bloodRequest.setBloodBank(t_bloodRequest.getBloodBank());
		 * bloodRequest.setMobileNo(t_bloodRequest.getMobileNo());
		 * bloodRequest.setBbPersonName(t_bloodRequest.getBbPersonName());
		 * bloodRequest.setBbPersonDesignation(t_bloodRequest. getBbPersonDesignation()); t_bloodRequest =
		 * bloodRequestRepository.save(bloodRequest); }
		 */
		bloodRequestRepository.updateBloodBankDetails(
				t_bloodRequest.getBloodBank(), t_bloodRequest.getBbPersonName(), t_bloodRequest.getBbPersonDesignation(),
				t_bloodRequest.getMobileNo(), t_bloodRequest.getBloodReqID(), t_bloodRequest.getRemarks(), t_bloodRequest.getOutboundNeeded(),
				t_bloodRequest.getOutboundDate());

		return t_bloodRequest;

	}

	/*
	 * @Override public T_BloodOutboundDetails save(T_BloodOutboundDetails t_bloodOutboundDetails) throws Exception {
	 * return bloodOutboundDetailsRepository.save(t_bloodOutboundDetails); }
	 * 
	 * @Override public Integer updateBloodOutBoundDetails(T_BloodOutboundDetails t_bloodOutboundDetails) throws
	 * Exception {
	 * 
	 * return bloodOutboundDetailsRepository.update(t_bloodOutboundDetails.
	 * getBloodOutboundDetailID(),t_bloodOutboundDetails.getCzentrixCallID()); }
	 * 
	 * @Override public List<T_BloodOutboundDetails> getBloodOutBoundDetails(T_BloodOutboundDetails
	 * t_bloodOutboundDetails) throws Exception {
	 * 
	 * 
	 * List<T_BloodOutboundDetails> bloodOutboundDetailsList = new ArrayList<T_BloodOutboundDetails>();
	 * 
	 * List<Objects[]> bloodOutboundDetails = bloodOutboundDetailsRepository.getBloodOutBoundDetails(
	 * t_bloodOutboundDetails.getBloodOutboundDetailID(),t_bloodOutboundDetails. getBloodReqID());
	 * 
	 * for (Object[] objects : bloodOutboundDetails) { if (objects!=null && objects.length > 0) {
	 * 
	 * bloodOutboundDetailsList.add(new T_BloodOutboundDetails((Long)objects[0], (String)objects[1],
	 * (Integer)objects[2], (Integer)objects[3], (String)objects[4], (String)objects[5], (String)objects[6],
	 * (String)objects[7],(Boolean)objects[8] , (String)objects[9], (String)objects[10], (String)objects[11],
	 * (String)objects[12], (Boolean)objects[13])); } } return bloodOutboundDetailsList;
	 * 
	 * }
	 */

	@Override
	public BloodBank getBloodBankURL(String bloodBankName) throws Exception
	{

		return instituteRepository.findBloodBankURL(bloodBankName);

	}

	@Override
	public BloodBank saveBloodBankURL(BloodBank bloodBank) throws Exception
	{
		return instituteRepository.save(bloodBank);

	}

}
