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
package com.iemr.helpline104.repository.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.epidemicOutbreak.T_EpidemicOutbreak;


@Repository
@RestResource(exported = false)
public interface SearchOnIdRepo extends CrudRepository<T_EpidemicOutbreak, Long>{

	
	
	@Query("select e.outbreakComplaintID, e.requestID, e.beneficiaryRegID, e.natureOfComplaint, e.totalPeopleAffected, "
			+ "e.affectedDistrictID, d.districtName, e.affectedDistrictBlockID, b.blockName, "
			+ "e.affectedVillageID, dbm.villageName, "
			+ "e.remarks, e.createdDate from T_EpidemicOutbreak e " + "LEFT JOIN e.m_district d "
			+ "LEFT JOIN e.m_districtblock b "
			+ "LEFT JOIN e.districtBranchMapping dbm "
			+ "where e.deleted=false order by e.outbreakComplaintID desc")
	public List<Object[]> getEpidemicOutbreakComplaints(@Param("grievanceId") String grievanceId,@Param("mobileNumber") String mobileNumber
			)
			throws Exception;

	

	

}
