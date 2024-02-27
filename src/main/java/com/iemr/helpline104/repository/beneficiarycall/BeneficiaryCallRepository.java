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


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.beneficiarycall.BeneficiaryCall;

import jakarta.transaction.Transactional;

@Repository
@RestResource(exported = false)
public interface BeneficiaryCallRepository extends CrudRepository<BeneficiaryCall, Long> {
	/*
	@Query("select " + "b.benCallID, b.benCallServicesMappingHistories, b.calledServiceID, b.is1097, "
			+ "b.callTime, b.remarks, b.callClosureType, b.dispositionStatusID from BeneficiaryCall b "
			+ "where b.benCallID = :id")
	public ArrayList<Objects[]> findCallsByBenefeciaryID(@Param("id") long id, Pageable page); */

	
	/*@Query("select b.benCallID, b.callTime, b.remarks,  "
			+ "b.createdDate from BeneficiaryCall b "
			+ "left join b.benCallServicesMappingHistories m "
			+ "where b.beneficiaryRegID = :id group by b.benCallID")
	public ArrayList<Objects[]> findCallsHistoryByBenefeciaryID(@Param("id") Long id, Pageable page); */
/*
	SELECT b.beneficiaryID, b.firstName, b.middleName, b.lastName, b.genderID, g.genderName, b.dOB,  c.cityID, c.cityName"
			+ " FROM I_Beneficiary b"
			+ " INNER JOIN b.i_bendemographics i"
			+ " LEFT JOIN i.m_state s "
			+ " LEFT JOIN i.m_city c "
			+ " INNER JOIN b.m_gender g "
			+ " WHERE b.firstName like %:firstName% and b.lastName like %:lastName% and (b.fatherName like %:fatherName% or b.spouseName like %:fatherName%) "
			+ " and b.genderID=:gender and b.beneficiaryID like %:beneficiaryId%"
			+ " and  (i.stateID IS NULL or cast(i.stateID as string) like %:districtId%) and (i.cityID IS NULL or cast(i.cityID as string) like %:cityId%)")
*/
	/*
	@Query("SELECT bc.benCallID, bc.callReceivedUserID, b.beneficiaryRegID, b.firstName, b.middleName, b.lastName, b.genderID,"
			+ " g.genderName, b.dOB,  c.cityID, c.cityName, m.benRelationshipID, r.benRelationshipType,i.healthCareWorkerID, "
			+ "i.districts, i.districtBlock, i.districtBranchMapping "
			+ " FROM BeneficiaryCall bc"
			+ " INNER JOIN bc.i_beneficiary b "
			+ " INNER JOIN b.i_bendemographics i"
			+ " LEFT JOIN i.m_state s "
			+ " LEFT JOIN i.m_city c "
			+ " LEFT JOIN i.districts d "
			+ " LEFT JOIN i.districtBlock db "
			+ " LEFT JOIN i.districtBranchMapping dbm "
			+ " LEFT JOIN b.benPhoneMaps m "
			+ " LEFT JOIN m.i_benRelationshipType r "
			+ " INNER JOIN b.m_gender g "
			+ " WHERE bc.callID=:benCallID group by i.beneficiaryRegID,m.benRelationshipID")
	public ArrayList<Objects[]> findBeneficiaryByCallID(@Param("benCallID") String benCallID, Pageable page);  */
    /*
	@Query("select b.benCallID, b.callTime, b.remarks, "
			+ "b.createdDate from BeneficiaryCall b "
			+ "left join b.benCallServicesMappingHistories m "
			+ "where b.benCallID = :id group by b.benCallID")
	public ArrayList<Objects[]> findCallSummaryByCallID(@Param("id") Long id); */

    /*
	@Transactional
	@Modifying
	@Query("update BeneficiaryCall set callTime = :callTime, remarks = :remarks, "
			+ "callClosureType = :callClosureType, "
			+ "dispositionStatusID = :dispositionStatusID where benCallID = :id")
	public int closeCall(@Param("id") Long id, @Param("remarks") String remarks, @Param("callTime") Date callTime,
			@Param("callClosureType") String callClosureType,
			@Param("dispositionStatusID") Integer dispositionStatusID);  */
	
	@Transactional
	@Modifying
	@Query("update BeneficiaryCall set beneficiaryRegID = :beneficiaryRegID where benCallID = :id")
	public int updateBeneficiaryIDInCall(@Param("id") Long id, @Param("beneficiaryRegID") Long beneficiaryRegID); 
	
	@Query("select callID from BeneficiaryCall where benCallID = :benCallID")
	public String findCallIDFromBenCallID(@Param("benCallID") Long benCallID);

}
