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

	@Autowired
	private void setBeneficiaryCallRepository(BeneficiaryCallRepository beneficiaryCallRepository) {
		this.beneficiaryCallRepository = beneficiaryCallRepository;
	}

	@Override
	public BeneficiaryCall createCall(BeneficiaryCall beneficiaryCall) {
		beneficiaryCall.setIs1097(false);
		BeneficiaryCall savedCalls = beneficiaryCallRepository.save(beneficiaryCall);
		return savedCalls;
	}

	@Override
	public Integer updateBeneficiaryIDInCall(Long benCallID, Long beneficiaryRegID) {
		Integer updateCounts = 0;

		updateCounts = beneficiaryCallRepository.updateBeneficiaryIDInCall(benCallID, beneficiaryRegID);
		return updateCounts;
	}
}
