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
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.userbeneficiarydata.M_Community;
import com.iemr.helpline104.repository.beneficiary.CommunityRepository;

@Service
public class CommunityServiceImpl implements CommunityService {
	private CommunityRepository communityRepository;

	@Autowired
	public void setCommunityServiceImpl(CommunityRepository communityRepository) {
		this.communityRepository = communityRepository;
	}

	public List<M_Community> getActiveCommunities() {
		List<M_Community> communitiesList = new ArrayList<M_Community>();
		Set<Object[]> queryReult = communityRepository.findAciveCommunities();
		for (Object[] objects : queryReult) {
			if (objects!=null && objects.length == 2) {
				communitiesList.add(new M_Community((Integer) objects[0], (String) objects[1]));
			}
		}
		return communitiesList;
	}
}
