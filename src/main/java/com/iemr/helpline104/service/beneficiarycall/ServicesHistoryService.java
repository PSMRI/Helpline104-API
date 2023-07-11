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

import java.util.List;
import java.util.Objects;

import com.iemr.helpline104.data.beneficiarycall.BenCallServicesMappingHistory;
import com.iemr.helpline104.data.beneficiarycall.ServicesMaster104;

public interface ServicesHistoryService {

	/**
	 * 
	 * @return service history list
	 */
	//List<BenCallServicesMappingHistory> getServiceHistory(Long id);

	/**
	 * 
	 * @param service104HistoryDetails
	 * @return saved object
	 */
	BenCallServicesMappingHistory createServiceHistory(BenCallServicesMappingHistory service104HistoryDetails);

	//List<BenCallServicesMappingHistory> getCallSummary(Long id);

	//List<BenCallServicesMappingHistory> getReferralsHistory(Long id, int pageNo, int rows);

	//List<BenCallServicesMappingHistory> getFeedbacksHistory(Long id, int pageNo, int rows);

	//List<BenCallServicesMappingHistory> getInformationsHistory(Long id, int pageNo, int rows);
	
	List<Objects[]> getServices(Integer providerServiceMapID);
	
    public String saveCallDisconnectedData(String requestObj) throws Exception;


	
}
