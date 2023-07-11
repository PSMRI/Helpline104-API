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
package com.iemr.helpline104.service.healthCareWorkerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.healthCareWorkerType.M_HealthCareWorker;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.location.States;
import com.iemr.helpline104.repository.healthCareWorkerType.HealthCareWorkerTypeRepository;

@Service
public class HealthCareWorkerServiceImpl implements HealthCareWorkerService{

	@Autowired
	private HealthCareWorkerTypeRepository healthCareWorkerTypeRepository;
	@Override
	public List<M_HealthCareWorker> getHealthCareWorkerTypes() {
	
		
		 List<M_HealthCareWorker> healthCareTypesList = new ArrayList<M_HealthCareWorker>();
			Iterable<Objects[]> healthCareTypes = healthCareWorkerTypeRepository.findAciveHealthCareWorkerTypes();
		
			System.out.println(healthCareTypes);
			for (Object[] objects : healthCareTypes) {
				if (objects!=null && objects.length > 0) {
					healthCareTypesList.add(new M_HealthCareWorker((Short)objects[0], (String)objects[1]));
				}
			}
			
		return healthCareTypesList;
	}
	
}
