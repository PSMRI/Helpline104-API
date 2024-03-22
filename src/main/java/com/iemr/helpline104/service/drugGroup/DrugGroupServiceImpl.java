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
package com.iemr.helpline104.service.drugGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.drugGroup.M_DrugGroup;
import com.iemr.helpline104.data.drugMapping.M_104drugmapping;
import com.iemr.helpline104.data.drugMaster.DrugStrength;
import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.repository.drugGroup.DrugFrequencyRepository;
import com.iemr.helpline104.repository.drugGroup.DrugGroupRepository;
import com.iemr.helpline104.repository.drugGroup.DrugStrengthRepository;

@Service
public class DrugGroupServiceImpl implements DrugGroupService{

	@Autowired
	private DrugGroupRepository drugGroupRepository;
	
	@Autowired
	private DrugFrequencyRepository drugFrequencyRepository;
	
	@Autowired
	private DrugStrengthRepository drugStrengthRepository;
	
	/*@Override
	public List<Object[]> getDrugList(Integer providerServiceMapID) throws Exception {
		List<M_DrugGroup> drugsList = new ArrayList<M_DrugGroup>();
		List<Object[]> drugs = drugGroupRepository.getDrugsList(providerServiceMapID);
		
		for (Object[] objects : drugs) {
			if (objects!=null && objects.length > 0) {
				
		
				drugsList.add(new M_DrugGroup((Integer)objects[0], (String)objects[1], (String)objects[2], (Integer)objects[3], (String)objects[4], 
						(String)objects[5], (String)objects[6], (String)objects[7], (String)objects[8]));
			}
		}
		return drugs;
	}*/
	
	
	@Override
	public List<M_DrugGroup> getDrugGroups(Integer providerServiceMapID) throws Exception {
		
		List<M_DrugGroup> drugGroups = new ArrayList<M_DrugGroup>();
		List<Object[]> drugs = drugGroupRepository.getDrugGroups(providerServiceMapID);
		
		for (Object[] objects : drugs) {
			if (objects!=null && objects.length > 0) {
				
		
				drugGroups.add(new M_DrugGroup((Integer)objects[0], (String)objects[1], (String)objects[2]));
			}
		}
		
		return drugGroups;
	}
	@Override
	public List<M_104drugmapping> getDrugList(Integer providerServiceMapID, Integer drugGroupID) throws Exception {
		List<M_104drugmapping> drugsList = new ArrayList<M_104drugmapping>();
		List<Object[]> drugs = drugGroupRepository.getDrugList(providerServiceMapID, drugGroupID);
		
		for (Object[] objects : drugs) {
			if (objects!=null && objects.length > 0) {
				
				drugsList.add(new M_104drugmapping((Integer)objects[0], (Integer)objects[1], (String)objects[2],
						(Integer)objects[3], (String)objects[4], (String)objects[5],
						(Integer)objects[6]));
			}
		}
		
		return drugsList;
	}
	
	@Override
	public ArrayList<Object[]> getDrugFrequency() {
		
			
		return  drugFrequencyRepository.getDrugFrequency();
	}
	
	@Override
	public ArrayList<DrugStrength> getDrugStrength(Integer serviceProviderID) {
		
			
		return  drugStrengthRepository.getDrugStrength(serviceProviderID);
	}

	@Override
	public List<M_104drugmapping> getDrugDetailList(Integer providerServiceMapID) throws Exception {
		List<M_104drugmapping> drugsList = new ArrayList<M_104drugmapping>();
		List<Object[]> drugs = drugGroupRepository.getDrugDetailList(providerServiceMapID);
		
		for (Object[] objects : drugs) {
			if (objects!=null && objects.length > 0) {
				
				drugsList.add(new M_104drugmapping((Integer)objects[0], (Integer)objects[1], (String)objects[2],
						(Integer)objects[3], (String)objects[4], (String)objects[5],
						(Integer)objects[6]));
			}
		}
		
		return drugsList;
	}
}
