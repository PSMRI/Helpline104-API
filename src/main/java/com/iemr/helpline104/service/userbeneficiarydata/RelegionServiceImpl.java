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
package com.iemr.helpline104.service.userbeneficiarydata;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.userbeneficiarydata.M_Religion;
import com.iemr.helpline104.repository.userbeneficiarydata.ReligionRepository;

@Service
public class RelegionServiceImpl implements RelegionService {
	private ReligionRepository relegionRepository;

	@Autowired(required = false)
	public void setRelegionServiceImpl(ReligionRepository relegionRepository) {
		this.relegionRepository = relegionRepository;
	}

	public List<M_Religion> getActiveReligions() {
		List<M_Religion> activeReligions = new ArrayList();
		List<Objects[]> lists = this.relegionRepository.getActiveReligions();
		for (Object[] objects : lists) {
			if ((objects != null) && (objects.length == 3)) {
				activeReligions.add(
						new M_Religion(((Integer) objects[0]).intValue(), (String) objects[1], (String) objects[2]));
			}
		}
		return activeReligions;
	}
}
