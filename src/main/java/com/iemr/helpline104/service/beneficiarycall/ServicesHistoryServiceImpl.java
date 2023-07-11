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
	/*
	@Override
	public List<BenCallServicesMappingHistory> getServiceHistory(Long id) {

		ArrayList<BenCallServicesMappingHistory> serviceHistoryList = new ArrayList<BenCallServicesMappingHistory>();
		List<Objects[]> lists = serviceHistoryRepository.findCallDetailsForBeneficiary(id);

		for (Object[] objects : lists) {
			if (objects != null && objects.length >= 15) {
				BenCallServicesMappingHistory call = new BenCallServicesMappingHistory((Long) objects[0],
						(Long) objects[1], (Long) objects[2], (Integer) objects[3], (ServicesMaster1097) objects[4],
						(Integer) objects[5], (CategoryDetails) objects[6], (Integer) objects[7],
						(SubCategoryDetails) objects[8], (Long) objects[9], (FeedbackDetails) objects[10],
						(Long) objects[11], (DirectoryMapping) objects[12], (String) objects[13], (Date) objects[14]);
				serviceHistoryList.add(call);
			}
		}
		return serviceHistoryList;
	} */

	/*
	@Override
	public List<BenCallServicesMappingHistory> getCallSummary(Long id) {
		ArrayList<BenCallServicesMappingHistory> serviceHistoryList = new ArrayList<BenCallServicesMappingHistory>();
		List<Objects[]> lists = serviceHistoryRepository.getCallSummary(id);
		for (Object[] objects : lists) {
			if (objects != null && objects.length >= 15) {
				BenCallServicesMappingHistory srh = new BenCallServicesMappingHistory((Long) objects[0],
						(Long) objects[1], (Long) objects[2], (Integer) objects[3], (ServicesMaster1097) objects[4],
						(Integer) objects[5], (CategoryDetails) objects[6], (Integer) objects[7],
						(SubCategoryDetails) objects[8], (Long) objects[9], (FeedbackDetails) objects[10],
						(Long) objects[11], (DirectoryMapping) objects[12], (String) objects[13], (Date) objects[14]);
				serviceHistoryList.add(srh);
			}
		}
		return serviceHistoryList;
	} */

	/*
	@Override
	public List<BenCallServicesMappingHistory> getReferralsHistory(Long id, int pageNo, int rows) {

		ArrayList<BenCallServicesMappingHistory> serviceHistoryList = new ArrayList<BenCallServicesMappingHistory>();
		List<Objects[]> lists = serviceHistoryRepository.findReferralsForBeneficiary(id, new PageRequest(pageNo, rows));

		for (Object[] objects : lists) {
			if (objects != null && objects.length >= 9) {
				BenCallServicesMappingHistory call = new BenCallServicesMappingHistory((Long) objects[0],
						(Long) objects[1], (Long) objects[2], (Integer) objects[3], (ServicesMaster1097) objects[4],
						(Long) objects[5], (DirectoryMapping) objects[6], (String) objects[7], (Date) objects[8]);
				serviceHistoryList.add(call);
			}
		}
		return serviceHistoryList;
	} */
    /*
	@Override
	public List<BenCallServicesMappingHistory> getFeedbacksHistory(Long id, int pageNo, int rows) {

		ArrayList<BenCallServicesMappingHistory> serviceHistoryList = new ArrayList<BenCallServicesMappingHistory>();
		List<Objects[]> lists = serviceHistoryRepository.findFeedbacksForBeneficiary(id, new PageRequest(pageNo, rows));

		for (Object[] objects : lists) {
			if (objects != null && objects.length >= 9) {
				BenCallServicesMappingHistory call = new BenCallServicesMappingHistory((Long) objects[0],
						(Long) objects[1], (Long) objects[2], (Integer) objects[3], (ServicesMaster1097) objects[4],
						(Long) objects[5], (FeedbackDetails) objects[6], (String) objects[7], (Date) objects[8]);
				serviceHistoryList.add(call);
			}
		}
		return serviceHistoryList;
	} */
    
	/*
	@Override
	public List<BenCallServicesMappingHistory> getInformationsHistory(Long id, int pageNo, int rows) {

		ArrayList<BenCallServicesMappingHistory> serviceHistoryList = new ArrayList<BenCallServicesMappingHistory>();
		List<Objects[]> lists = serviceHistoryRepository.findInformationsForBeneficiary(id,
				new PageRequest(pageNo, rows));

		for (Object[] objects : lists) {
			if (objects != null && objects.length >= 11) {
				BenCallServicesMappingHistory call = new BenCallServicesMappingHistory((Long) objects[0],
						(Long) objects[1], (Long) objects[2], (Integer) objects[3], (ServicesMaster1097) objects[4],
						(Integer) objects[5], (CategoryDetails) objects[6], (Integer) objects[7],
						(SubCategoryDetails) objects[8], (String) objects[9], (Date) objects[10]);
				serviceHistoryList.add(call);
			}
		}
		return serviceHistoryList;
	} */
	
	@Override
	public List<Objects[]> getServices(Integer providerServiceMapID) {

		List<Objects[]> services = new ArrayList<Objects[]>();
		
		//services = serviceMasterRepository.getServices();
		
		return serviceMasterRepository.getServices(providerServiceMapID);
	}
	
	@Override    
    public String saveCallDisconnectedData(String requestObj) throws Exception {
        String response = "";
        try {
            BenCallDisconnectedData  benCallDisconnectedOBJ = InputMapper.gson()
                    .fromJson(requestObj, BenCallDisconnectedData.class);
            
            if (benCallDisconnectedOBJ != null) {
                BenCallDisconnectedData resultSetObj = benCallDisconnectedDataRepo.save(benCallDisconnectedOBJ);

 

                if(resultSetObj.getcZentricLogID() != null) {
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
