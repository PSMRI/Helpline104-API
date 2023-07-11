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
package com.iemr.helpline104.service.callqamapping;

import java.util.List;

import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;
import com.iemr.helpline104.data.callqamapping.CallqaMappings;
import com.iemr.helpline104.data.callqamapping.M_104callqamapping;

public interface CallqamappingService {
	
	public List<M_104callqamapping> save(CallqaMappings callqaMappings) throws Exception;
	
	public List<M_104callqamapping> getCDIQuestionScores(M_104callqamapping m_104callqamapping) throws Exception;
}
