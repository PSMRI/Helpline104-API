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
package com.iemr.helpline104.secondary.repo.report;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.secondary.data.BencallReport;




@Repository
@RestResource(exported = false)
public interface SecondaryReportRepo extends CrudRepository<BencallReport, Long> {




	@Query(value="call db_reporting.Pr_104BeneficiaryReport(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getRegisterationReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
			@Param("providerServiceMapID") Integer providerServiceMapID);
	

	@Query(value="call db_reporting.Pr_104HAOReport(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getHAOReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
			@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query(value="call db_reporting.Pr_104MOReport(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getMOReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
			@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query(value="call db_reporting.Pr_104MODetailsReport(:startDateTime,:endDateTime,"
			+ ":districtID,:subDistrictID,:villageID,:roleID,:locationID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getMODetailsReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,
			@Param("providerServiceMapID") Integer providerServiceMapID,@Param("districtID") Long districtID,
			@Param("subDistrictID") Long subDistrictID,@Param("villageID") Long villageID,
			@Param("roleID") Long roleID,@Param("locationID") Long locationID);
	
	@Query(value="call db_reporting.Pr_104COReport(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getCOReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
			@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query(value="call db_reporting.Pr_104FoodsafetyComplaint(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getFoodSafetyReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
			@Param("providerServiceMapID") Integer providerServiceMapID);
	
	
	@Query(value="call db_reporting.Pr_104CODetailGuidelinesReport(:startDateTime,:endDateTime,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getGuidelinesReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,
			@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query(value="call db_reporting.Pr_104CODetailGCategoryReport(:startDateTime,:endDateTime,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getCategoryReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,
			@Param("providerServiceMapID") Integer providerServiceMapID);
	

	@Query(value="call db_reporting.Pr_104GrievanceReport(:startDateTime,:endDateTime,:feedbackTypeID,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getGrievanceReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
			@Param("providerServiceMapID") Integer providerServiceMapID,@Param("feedbackTypeID") Short feedbackTypeID);
    
	@Query(value="call db_reporting.Pr_104SurveyorReport(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getCDIResponseReport(@Param("startDateTime") Timestamp startDateTime,
	@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
	@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query(value="call db_reporting.Pr_104HealthSchemes(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getSchemeServiceReport(@Param("startDateTime") Timestamp startDateTime, 
			@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
			@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query(value="call db_reporting.Pr_104DirectoryServiceReport(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getDirectoryserviceReport(@Param("startDateTime") Timestamp startDateTime, 
			@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
			@Param("providerServiceMapID") Integer providerServiceMapID);
	

	@Query(value="call db_reporting.Pr_104PrescriptionReport(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getPrescriptionReport(@Param("startDateTime") Timestamp startDateTime,
	@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
	@Param("providerServiceMapID") Integer providerServiceMapID);

	@Query(value="call db_reporting.Pr_104BoodRequest(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getBloodRequestReport(@Param("startDateTime") Timestamp startDateTime,
	@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
	@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query(value="call db_reporting.Pr_104BoodRequestDetails_Componentwise(:startDateTime,:endDateTime,:districtID,:subDistrictID,:villageID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getComponentReport(@Param("startDateTime") Timestamp startDateTime,
	@Param("endDateTime") Timestamp endDateTime,@Param("districtID") Integer districtID, @Param("subDistrictID") Integer subDistrictID,
	@Param("villageID") Integer villageID, @Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query(value="call db_reporting.Pr_104BoodRequestDetails_Groupwise(:startDateTime,:endDateTime,:districtID,:subDistrictID,:villageID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getGroupReport(@Param("startDateTime") Timestamp startDateTime,
	@Param("endDateTime") Timestamp endDateTime,@Param("districtID") Integer districtID, @Param("subDistrictID") Integer subDistrictID,
	@Param("villageID") Integer villageID,@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query(value="call db_reporting.Pr_104BoodRequestDetails_Componentwise_Pivot(:startDateTime,:endDateTime,:districtID,:subDistrictID,:villageID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getDistrictComponentReport(@Param("startDateTime") Timestamp startDateTime,
	@Param("endDateTime") Timestamp endDateTime,@Param("districtID") Integer districtID, @Param("subDistrictID") Integer subDistrictID,
	@Param("villageID") Integer villageID,@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query(value="call db_reporting.Pr_104BoodRequestDetails_Groupwise_Pivot(:startDateTime,:endDateTime,:districtID,:subDistrictID,:villageID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getDistrictGroupReport(@Param("startDateTime") Timestamp startDateTime,
	@Param("endDateTime") Timestamp endDateTime,@Param("districtID") Integer districtID, @Param("subDistrictID") Integer subDistrictID,
	@Param("villageID") Integer villageID,@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query(value="call db_reporting.Pr_104Epidemicoutbreak(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getEpidemicoutbreakReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
			@Param("providerServiceMapID") Integer providerServiceMapID);
			
	@Query(value="call db_reporting.Pr_104OrganDonation(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getOrgandonationReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
			@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query(value="call db_reporting.Pr_104psychiatrist(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Object[]> getPDSummaryReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,@Param("agentID") String agentID,
			@Param("providerServiceMapID") Integer providerServiceMapID);
}

