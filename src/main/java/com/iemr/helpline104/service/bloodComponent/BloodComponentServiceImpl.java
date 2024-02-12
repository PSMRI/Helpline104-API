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
package com.iemr.helpline104.service.bloodComponent;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.bloodComponent.M_Component;
import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;
import com.iemr.helpline104.repository.bloodComponent.BloodComponentRepository;

@Service
public class BloodComponentServiceImpl implements BloodComponentService{

	@Autowired(required = false)
	private BloodComponentRepository bloodComponentRepository;
	@Override
	public M_Component save(M_Component m_component) {
		return bloodComponentRepository.save(m_component);
	}

	@Override
	public List<M_Component> getBloodComponents(Integer componentID) throws Exception {
		List<M_Component> bloodComponentList = new ArrayList<M_Component>();
		List<Objects[]> bloodComponents = bloodComponentRepository.getBloodComponents(componentID);
		
		for (Object[] objects : bloodComponents) {
			if (objects!=null && objects.length > 0) {

				bloodComponentList.add(new M_Component((Integer)objects[0], (String)objects[1], (String)objects[2]));
			}
		}
		return bloodComponentList;
	}

}
