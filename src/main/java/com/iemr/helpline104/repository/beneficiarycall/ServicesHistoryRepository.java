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
package com.iemr.helpline104.repository.beneficiarycall;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.beneficiarycall.BenCallServicesMappingHistory;

@Repository
@RestResource(exported = false)
public interface ServicesHistoryRepository extends CrudRepository<BenCallServicesMappingHistory, Integer> {

	/*
	@Query("select " + "benCall104ServiceMapID, beneficiaryRegID, benCallID, serviceID104, "
			+ "createdBy, createdDate "
			+ "from BenCallServicesMappingHistory " + "where beneficiaryRegID = :beneficiaryRegID")
	public Set<Objects[]> findBy(@Param("beneficiaryRegID") Long beneficiaryRegID);  */

	/*
	@Query("select " + "b.benCall104ServiceMapID, b.beneficiaryRegID, b.benCallID, "
			+ "b.serviceID104, b.servicesMaster104, "			
			+ "b.createdBy, b.createdDate  " + "from BenCallServicesMappingHistory b "
			+ "join b.servicesMaster104 sm " 			
			+ "where b.benCallID = :benCallID ")
	public List<Objects[]> getCallSummary(@Param("benCallID") Long benCallID); */

	/*
	@Query("select " + "b.benCall104ServiceMapID, b.beneficiaryRegID, b.benCallID, "
			+ "b.serviceID104, b.servicesMaster104, "			
			+ "b.createdBy, b.createdDate " + "from BenCallServicesMappingHistory b "
			+ "left join b.servicesMaster104 sm " 			
			+ "where b.beneficiaryRegID = :beneficiaryRegID ")
	public List<Objects[]> findCallDetailsForBeneficiary(@Param("beneficiaryRegID") Long beneficiaryRegID); */

	/*
	@Query("select " + "b.benCall104ServiceMapID, b.beneficiaryRegID, b.benCallID, "
			+ "b.serviceID104, b.servicesMaster104, "
			+ "b.createdBy, b.createdDate " + "from BenCallServicesMappingHistory b "
			+ "left join b.servicesMaster104 sm "			
			+ "where b.beneficiaryRegID = :beneficiaryRegID ")
	public List<Objects[]> findReferralsForBeneficiary(@Param("beneficiaryRegID") Long beneficiaryRegID, Pageable page); */

	/*
	@Query("select " + "b.benCall104ServiceMapID, b.beneficiaryRegID, b.benCallID, "
			+ "b.serviceID104, b.servicesMaster104, b.createdBy, b.createdDate "
			+ "from BenCallServicesMappingHistory b " + "left join b.servicesMaster104 sm "
			+ "join b.feedbackDetails f " + "where b.beneficiaryRegID = :beneficiaryRegID ")
	public List<Objects[]> findFeedbacksForBeneficiary(@Param("beneficiaryRegID") Long beneficiaryRegID, Pageable page); */

	/*
	@Query("select " + "b.benCall104ServiceMapID, b.beneficiaryRegID, b.benCallID, "
			+ "b.serviceID104, b.servicesMaster104, "
			+ "b.createdBy, b.createdDate " + "from BenCallServicesMappingHistory b "
			+ "join b.servicesMaster104 sm " 
			+ "where b.beneficiaryRegID = :beneficiaryRegID ")
	public List<Objects[]> findInformationsForBeneficiary(@Param("beneficiaryRegID") Long beneficiaryRegID,
			Pageable page); */
	
}
