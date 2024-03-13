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
package com.iemr.helpline104.repository.location;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.location.Districts;

@Repository
@RestResource(exported = false)
public interface LocationDistrictRepository extends CrudRepository<Districts, Integer> {

	@Query("select districtID, districtName from Districts where stateID = :id")
	public ArrayList<Object[]> findBy(@Param("id") int id);
	
	@Query("select D.districtID, D.districtName, S.stateName, S.stateID from Districts D, States S where D.districtID = :id and S.stateID = D.stateID")
	public ArrayList<Object[]> findStateDistrictBy(@Param("id") int id);

	
	@Query("select districtName from Districts where districtID = :id")
	public String findByDistrictID(@Param("id") int id);
	
	@Query("select stateID from Districts where districtID = :id")
	public Integer findStateByDistrictID(@Param("id") int id);

}
