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
package com.iemr.helpline104.repository.drugGroup;

import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.drugGroup.M_DrugGroup;

@Repository
@RestResource(exported = false)
public interface DrugGroupRepository extends CrudRepository<M_DrugGroup, Long>{
	
		@Query("select d.drugGroupID,d.drugGroup,d.drugGroupDesc,d.serviceProviderID from M_DrugGroup d"
				+ " where d.deleted=false and d.serviceProviderID=:serviceProviderID")
		public List<Object[]> getDrugGroups(@Param("serviceProviderID") Integer serviceProviderID) throws Exception;
		
		/*
		@Query("select d from M_DrugGroup d"
				+ " INNER JOIN d.m_drugMaster m where d.deleted=false and m.deleted=false")
		public M_DrugGroup getDrugsList() throws Exception;*/
		
		/*@Query("select d from M_DrugGroup d"
				+ " INNER JOIN d.m_drugMaster m where d.deleted=false and m.deleted=false and d.providerServiceMapID=:providerServiceMapID group by d.drugGroupID")
		public List<Object[]> getDrugsList(@Param("providerServiceMapID") Integer providerServiceMapID) throws Exception;*/
		

		
		
		@Query("select d.drugMapID,d.drugId,d.drugName,d.drugGroupID,d.drugGroupName,m.remarks,d.providerServiceMapID "
				+ "from M_104drugmapping d  "
				+ " INNER JOIN d.m_drugMaster m "
				+ "where d.deleted=false and m.deleted= false and d.providerServiceMapID=:providerServiceMapID and d.drugGroupID=:drugGroupID")
		public List<Object[]>  getDrugList(@Param("providerServiceMapID") Integer providerServiceMapID, @Param("drugGroupID") Integer drugGroupID) throws Exception;
		
		
		@Query("select d.drugMapID,d.drugId,d.drugName,d.drugGroupID,d.drugGroupName,m.remarks,d.providerServiceMapID "
				+ "from M_104drugmapping d  "
				+ " INNER JOIN d.m_drugMaster m "
				+ "where d.deleted=false and m.deleted= false and d.providerServiceMapID=:providerServiceMapID order by d.drugName ")
		public List<Object[]>  getDrugDetailList(@Param("providerServiceMapID") Integer providerServiceMapID) throws Exception;
		
}
