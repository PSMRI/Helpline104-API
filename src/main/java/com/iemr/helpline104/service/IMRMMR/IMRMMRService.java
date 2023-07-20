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
package com.iemr.helpline104.service.IMRMMR;

import java.util.List;

import com.iemr.helpline104.data.IMRMMR.IMRMMR;
import com.iemr.helpline104.data.IMRMMR.M_facilities;
import com.iemr.helpline104.data.IMRMMR.M_supportServices;
import com.iemr.helpline104.data.IMRMMR.m_iMRMMRBaseCommunity;
import com.iemr.helpline104.data.IMRMMR.m_iMRMMRHealthworker;
import com.iemr.helpline104.data.IMRMMR.m_iMRMMRTransitType;

public interface IMRMMRService {

	public String saveIMRMMR(String request,String Authorization) throws Exception;
	
	public List<M_supportServices> getsupportServices() throws Exception;
	
	public List<M_facilities> getFacilities() throws Exception;

	String updateImrMmrRequest(IMRMMR complaint) throws Exception;

	public List<m_iMRMMRBaseCommunity> getBaseCommunities() throws Exception;

	public List<m_iMRMMRTransitType> getTransitType() throws Exception;

	public List<m_iMRMMRHealthworker> getHealthWorker() throws Exception;

	public String getWorklistRequests(Integer beneficiaryRegID, String phoneNum, String requestID);
}
