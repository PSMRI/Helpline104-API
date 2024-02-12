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
package com.iemr.helpline104.service.beneficiarycall;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.iemr.helpline104.data.beneficiarycall.BeneficiaryCall;
import com.iemr.helpline104.data.location.DistrictBlock;
import com.iemr.helpline104.data.location.DistrictBranchMapping;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.repository.beneficiarycall.BeneficiaryCallRepository;

@Service
public class BeneficiaryCallServiceImpl implements BeneficiaryCallService {
	private BeneficiaryCallRepository beneficiaryCallRepository;

	@Autowired(required = false)
	private void setBeneficiaryCallRepository(BeneficiaryCallRepository beneficiaryCallRepository) {
		this.beneficiaryCallRepository = beneficiaryCallRepository;
	}

	/*
	 * @Override public List<BeneficiaryCall> addCalltype(Long benificiaryCallId) {
	 * List<BeneficiaryCall> benCalls = new ArrayList<BeneficiaryCall>();
	 * ArrayList<Objects[]> queryResult =
	 * beneficiaryCallRepository.findCallsByBenefeciaryID(benificiaryCallId, new
	 * PageRequest(0, 1000)); for (Object[] objects : queryResult) { if (queryResult
	 * != null && queryResult.size() >= 8) { String benCallServicesMappingHistories
	 * = new Gson().toJson(objects[1]); BeneficiaryCall beneficiaryCall = new
	 * BeneficiaryCall((Long) objects[0], benCallServicesMappingHistories, (Integer)
	 * objects[2], (Boolean) objects[3], (Date) objects[4], (String) objects[5],
	 * (String) objects[6], (Integer) objects[7]); benCalls.add(beneficiaryCall); }
	 * } return benCalls; }
	 */
	@Override
	public BeneficiaryCall createCall(BeneficiaryCall beneficiaryCall) {
		beneficiaryCall.setIs1097(false);
		BeneficiaryCall savedCalls = beneficiaryCallRepository.save(beneficiaryCall);
		return savedCalls;
	}
	/*
	 * @Override public Integer closeCall(String request) { Integer updateCounts =
	 * 0; BeneficiaryCall benificiaryCallId = new Gson().fromJson(request,
	 * BeneficiaryCall.class); updateCounts =
	 * beneficiaryCallRepository.closeCall(benificiaryCallId.getBenCallID(),
	 * benificiaryCallId.getRemarks(), new
	 * Date(Calendar.getInstance().getTimeInMillis()),
	 * benificiaryCallId.getCallClosureType(),
	 * benificiaryCallId.getDispositionStatusID()); return updateCounts; }
	 */
	/*
	 * @Override public List<BeneficiaryCall> findBeneficiaryByCallID(String
	 * benCallID, Integer pageNo, Integer rows) { List<BeneficiaryCall> benDetails =
	 * new ArrayList<BeneficiaryCall>(); ArrayList<Objects[]> queryResult =
	 * beneficiaryCallRepository.findBeneficiaryByCallID(benCallID, new
	 * PageRequest(pageNo, rows));
	 * 
	 * for (Object[] objects : queryResult) { if (objects != null && objects.length
	 * >= 7) {
	 * 
	 * 
	 * 
	 * // bc.benCallID, bc.callReceivedUserID, b.beneficiaryRegID, b.firstName,
	 * b.middleName, b.lastName, b.genderID," // +
	 * " g.genderName, b.dOB,  c.cityID, c.cityName, m.benRelationshipID, r.benRelationshipType,b.i_bendemographics, "
	 * // + "i.districts, i.districtBlock, i.districtBranchMapping" // + "
	 * BeneficiaryCall beneficiaryCall = new BeneficiaryCall((Long) objects[0],
	 * (Integer) objects[1], (Long) objects[2], (String) objects[3], (String)
	 * objects[4], (String) objects[5], (Short) objects[6], (String)
	 * objects[7],(Date) objects[8],(Integer) objects[9],(String)
	 * objects[10],(Short) objects[11],(String) objects[12], (Integer)
	 * objects[13],(Districts)objects[14], (DistrictBlock)objects[15],
	 * (DistrictBranchMapping)objects[16]); benDetails.add(beneficiaryCall);
	 * 
	 * // I_Beneficiary benDetail =new I_Beneficiary((String) objects[3]); //
	 * benDetails.add(benDetail); } } return benDetails; }
	 */

	/*
	 * @Override public List<BeneficiaryCall> getBeneficiaryCallsHistory(Long
	 * benificiaryCallId, Integer pageNo, Integer rows) { List<BeneficiaryCall>
	 * benCalls = new ArrayList<BeneficiaryCall>(); ArrayList<Objects[]> queryResult
	 * =
	 * beneficiaryCallRepository.findCallsHistoryByBenefeciaryID(benificiaryCallId,
	 * new PageRequest(pageNo, rows)); for (Object[] objects : queryResult) { if
	 * (objects != null && objects.length >= 7) { BeneficiaryCall beneficiaryCall =
	 * new BeneficiaryCall((Long) objects[0], (Date) objects[1], (String)
	 * objects[2], (Long) objects[3], (Long) objects[4], (Long) objects[5], (Date)
	 * objects[6]); benCalls.add(beneficiaryCall); } } return benCalls; }
	 */

	/*
	 * @Override public List<BeneficiaryCall> getCallSummaryByCallID(Long benCallID)
	 * { List<BeneficiaryCall> benCalls = new ArrayList<BeneficiaryCall>();
	 * ArrayList<Objects[]> queryResult =
	 * beneficiaryCallRepository.findCallSummaryByCallID(benCallID); for (Object[]
	 * objects : queryResult) { if (objects != null && objects.length >= 7) {
	 * BeneficiaryCall beneficiaryCall = new BeneficiaryCall((Long) objects[0],
	 * (Date) objects[1], (String) objects[2], (Long) objects[3], (Long) objects[4],
	 * (Long) objects[5], (Date) objects[6]); benCalls.add(beneficiaryCall); } }
	 * return benCalls; }
	 */

	@Override
	public Integer updateBeneficiaryIDInCall(Long benCallID, Long beneficiaryRegID) {
		Integer updateCounts = 0;

		updateCounts = beneficiaryCallRepository.updateBeneficiaryIDInCall(benCallID, beneficiaryRegID);
		return updateCounts;
	}
}
