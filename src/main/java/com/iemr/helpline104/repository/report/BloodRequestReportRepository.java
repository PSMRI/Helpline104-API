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
package com.iemr.helpline104.repository.report;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.report.BloodrequestReport;

@Repository
@RestResource(exported = false)
public interface BloodRequestReportRepository extends CrudRepository<BloodrequestReport, Long>
{

	@Query("select t.bloodReqID, t.requestID, t.beneficiaryRegID, t.benCallID, t.recipientBeneficiaryID, t.recipientName, t.recipientAge, t.recipientGenderName, "
			+ "t.bloodGroupName, t.componentTypeName, t.componentName, t.unitRequired, t.hospitalAdmitted, t.hospitalDistrictName, t.outboundNeeded, t.outboundDate, t.bloodBankAddress, t.bloodBankPersonName, t.bloodBankMobileNo, t.bloodBankPersonDesignation, t.remarks, t.feedback, t.isSelf, t.createdDate, b.beneficiaryID, b.firstName, b.lastName, b.healthCareWorker, b.gender, b.dob, b.permVillage, b.permSubDistrict, b.permDistrict, "
			+ "c.callTypeName, c.callSubTypeName, c.phoneNo, c.callReceivedUserID, c.callEndUserID, c.agentID, user.firstName "
			+ "from BloodrequestReport t "
			+ " LEFT JOIN t.beneficiaryReport b " + " LEFT JOIN t.bencallReport c "
			+ "left join c.userReportObj user "			
			+ "where t.providerServiceMapID=:providerServiceMapID "
			+ "and t.createdDate > :startDateTime " + "and t.createdDate < :endDateTime " + "order by t.createdDate desc")
	public List<Object[]> getBloodRequestReportByDate(
			@Param("providerServiceMapID") Integer providerServiceMapID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime);
	
	@Query("select provider.stateID from M_ProviderServiceMapping provider "
			+ "where provider.providerServiceMapID=:providerServiceMapID ")
	public Integer getStateByProvider(@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query("select districts from Districts districts "
			+ "where districts.stateID=:stateID order by districts.districtName asc")
	public List<Districts> getDistrictByStateID(@Param("stateID") Integer stateID);
	
	
	@Query("select t.question, t.answer, t.score, b.beneficiaryID, b.firstName, b.lastName, b.healthCareWorker, "
			+ "b.gender, b.dob, b.permVillage, b.permSubDistrict, b.permDistrict, c.callTypeName, c.callSubTypeName, c.phoneNo, c.agentID, user.firstName "
			+ "from BenCDIResponseReport t "
			+ "LEFT JOIN t.beneficiaryReport b " 
			+ "LEFT JOIN t.bencallReport c "
			+ "left join c.userReportObj user "	
			+ "where t.providerServiceMapID = :providerServiceMapID "
			+ "and t.createdDate >= :startDateTime " + "and t.createdDate <= :endDateTime " + "order by t.createdDate desc ")
	public List<Object[]> getCDIResponseReport(
			@Param("providerServiceMapID") Integer providerServiceMapID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime);
	
	@Query("select t.question, t.answer, t.score, b.beneficiaryID, b.firstName, b.lastName, b.healthCareWorker, "
			+ "b.gender, b.dob, b.permVillage, b.permSubDistrict, b.permDistrict, c.callTypeName, c.callSubTypeName, c.phoneNo, c.agentID, user.firstName "
			+ "from BenCDIResponseReport t "
			+ "LEFT JOIN t.beneficiaryReport b " 
			+ "LEFT JOIN t.bencallReport c " 
			+ "left join c.userReportObj user "	
			+ "where t.providerServiceMapID = :providerServiceMapID "
			+ "and t.createdDate >= :startDateTime and t.createdDate <= :endDateTime and c.agentID = :agentID " 
			+ "order by t.createdDate desc ")
	public List<Object[]> getCDIResponseReportByAgentID(
			@Param("providerServiceMapID") Integer providerServiceMapID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime, @Param("agentID") String agentID);

}
