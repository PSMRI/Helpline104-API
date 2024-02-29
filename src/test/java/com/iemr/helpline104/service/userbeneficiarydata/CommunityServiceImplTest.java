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


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.userbeneficiarydata.M_Community;
import com.iemr.helpline104.repository.beneficiary.CommunityRepository;

@ExtendWith(MockitoExtension.class)
public class CommunityServiceImplTest {
	
	@InjectMocks
	CommunityServiceImpl communityServiceImpl;
	
	@Mock
	CommunityRepository communityRepository;
	
	@Test
	public void getActiveCommunitiesTest()
	{
		Set<Object[]> set=Sets.newHashSet();
		Object[] array=new Object[2];
		array[0]=101;
		array[1]="community";
		set.add(array);
		try {
			doReturn(set).when(communityRepository).findAciveCommunities();
			List<M_Community> M_Communitys= communityServiceImpl.getActiveCommunities();
			assertTrue(M_Communitys.toString().contains("\"CommunityType\":\"community\""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getActiveCommunitiesTest1()
	{
		Set<Object[]> set=Sets.newHashSet();
		try {
			doReturn(set).when(communityRepository).findAciveCommunities();
			List<M_Community> M_Communitys= communityServiceImpl.getActiveCommunities();
			assertTrue(M_Communitys.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
