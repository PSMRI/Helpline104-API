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
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.beneficiarycall.BenCallDisconnectedData;
import com.iemr.helpline104.data.beneficiarycall.BenCallServicesMappingHistory;
import com.iemr.helpline104.data.beneficiarycall.M_subservice;
import com.iemr.helpline104.data.beneficiarycall.ServicesMaster104;
import com.iemr.helpline104.repository.beneficiarycall.BenCallDisconnectedDataRepo;
import com.iemr.helpline104.repository.beneficiarycall.ServiceMasterRepository;
import com.iemr.helpline104.repository.beneficiarycall.ServicesHistoryRepository;
import com.iemr.helpline104.utils.mapper.InputMapper;

@Service
public class ServicesHistoryServiceImpl implements ServicesHistoryService {

	/**
	 * Service history repository
	 */
	private ServicesHistoryRepository serviceHistoryRepository;

	@Autowired
	public void setServiceHistoryRepository(ServicesHistoryRepository serviceHistoryRepository) {

		this.serviceHistoryRepository = serviceHistoryRepository;
	}

	@Autowired
	private ServiceMasterRepository serviceMasterRepository;

	@Override
	public BenCallServicesMappingHistory createServiceHistory(
			BenCallServicesMappingHistory benCallServicesMappingHistory) {

		return serviceHistoryRepository.save(benCallServicesMappingHistory);
	}

	@Autowired
	private BenCallDisconnectedDataRepo benCallDisconnectedDataRepo;
	

	@Override
	public List<M_subservice> getServices(Integer providerServiceMapID) {


		return serviceMasterRepository.getServices(providerServiceMapID);
	}

	@Override
	public String saveCallDisconnectedData(String requestObj) throws Exception {
		String response = "";
		try {
			BenCallDisconnectedData benCallDisconnectedOBJ = InputMapper.gson().fromJson(requestObj,
					BenCallDisconnectedData.class);

			if (benCallDisconnectedOBJ != null) {
				BenCallDisconnectedData resultSetObj = benCallDisconnectedDataRepo.save(benCallDisconnectedOBJ);

				if (resultSetObj.getcZentricLogID() != null) {
					response = "Data saved successfully";
				} else
					response = "Error occurred while saving data";
			}
		} catch (Exception e) {
			response = "Invalid input";
		}
		return response;

	}

}
