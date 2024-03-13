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
package com.iemr.helpline104.service.bloodComponentType;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.bloodComponentType.M_BloodGroup;
import com.iemr.helpline104.data.bloodComponentType.M_ComponentType;
import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;
import com.iemr.helpline104.repository.bloodComponentType.BloodComponentTypeRepository;

@Service
public class BloodComponentTypeServiceImpl implements BloodComponentTypeService {

	@Autowired
	private BloodComponentTypeRepository componentTypeRepository;
	@Override
	public List<M_ComponentType> getBloodComponentTypes() throws Exception{
		List<M_ComponentType> componentTypeList = new ArrayList<M_ComponentType>();
		List<Object[]> componentTypes = componentTypeRepository.getComponentTypes();
		
		for (Object[] objects : componentTypes) {
			if (objects!=null && objects.length > 0) {

				componentTypeList.add(new M_ComponentType((Integer)objects[0], (String)objects[1], (String)objects[2]));
			}
		}
		return componentTypeList;
	}

	@Override
	public List<M_BloodGroup> getBloodGroups() throws Exception {
		List<M_BloodGroup> bloodGroupsList = new ArrayList<M_BloodGroup>();
		List<Object[]> bloodgroups = componentTypeRepository.getBloodGroups();
		
		for (Object[] objects : bloodgroups) {
			if (objects!=null && objects.length > 0) {

				bloodGroupsList.add(new M_BloodGroup((Integer)objects[0], (String)objects[1], (String)objects[2]));
			}
		}
		return bloodGroupsList;
	}
	
}
