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
package com.iemr.helpline104.repository.bloodRequest;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;

import jakarta.transaction.Transactional;
@Service
@Repository
@RestResource(exported = false)
public interface BloodRequestRepository extends CrudRepository<T_BloodRequest, Long> {
	@Autowired(required=true)

	@Query("select t.bloodReqID, t.requestID, t.beneficiaryRegID, t.recipientBeneficiaryID, t.recipientName, t.recipientAge, t.recipientGenderID, g.genderName, "
			+ "t.typeOfRequest, bg.bloodGroupID, bg.bloodGroup, bg.bloodGroupDesc, c.componentTypeID, c.componentType, c.componentTypeDesc, "
			+ "t.componentID, mc.component, mc.componentDesc, t.unitRequired, t.hospitalAdmitted, t.districtID, d.districtName, t.outboundNeeded, t.outboundDate, t.bloodBankAddress, "
			+ "t.bbMobileNo, t.bbPersonName, t.bbPersonDesignation, t.remarks, t.feedback " + "from T_BloodRequest t "
			+ " LEFT JOIN t.m_bloodGroup bg " + " LEFT JOIN t.m_componentType c " + " LEFT JOIN t.m_gender g "
			+ " LEFT JOIN t.m_component mc " + " LEFT JOIN t.m_district d "
			+ "where t.deleted=false and t.beneficiaryRegID=:beneficiaryRegID order by t.bloodReqID desc")
	public List<Objects[]> getBloodRequestsByBeneficiaryRegID(@Param("beneficiaryRegID") Long beneficiaryRegID)
			throws Exception;

	@Query("select t.bloodReqID, t.requestID, t.beneficiaryRegID, t.recipientBeneficiaryID, t.recipientName, t.recipientAge, t.recipientGenderID, g.genderName, "
			+ "t.typeOfRequest, bg.bloodGroupID, bg.bloodGroup, bg.bloodGroupDesc, c.componentTypeID, c.componentType, c.componentTypeDesc, "
			+ "t.componentID, mc.component, mc.componentDesc, t.unitRequired, t.hospitalAdmitted, t.districtID, d.districtName, t.outboundNeeded, t.outboundDate, t.bloodBankAddress, "
			+ "t.bbMobileNo, t.bbPersonName, t.bbPersonDesignation, t.remarks, t.feedback " + "from T_BloodRequest t "
			+ " LEFT JOIN t.m_bloodGroup bg " + " LEFT JOIN t.m_componentType c " + " LEFT JOIN t.m_gender g "
			+ " LEFT JOIN t.m_component mc " + " LEFT JOIN t.m_district d "
			+ "where t.deleted=false and t.requestID=:requestID order by t.bloodReqID desc")
	public List<Objects[]> getBloodRequestsByRequestID(@Param("requestID") String requestID) throws Exception;
	
	@Query("select t.bloodReqID, t.requestID, t.beneficiaryRegID, t.recipientBeneficiaryID, t.recipientName, t.recipientAge, t.recipientGenderID, g.genderName, "
			+ "t.typeOfRequest, bg.bloodGroupID, bg.bloodGroup, bg.bloodGroupDesc, c.componentTypeID, c.componentType, c.componentTypeDesc, "
			+ "t.componentID, mc.component, mc.componentDesc, t.unitRequired, t.hospitalAdmitted, t.districtID, d.districtName, t.outboundNeeded, t.outboundDate, t.bloodBankAddress, "
			+ "t.bbMobileNo, t.bbPersonName, t.bbPersonDesignation, t.remarks, t.feedback " + "from T_BloodRequest t "
			+ " LEFT JOIN t.m_bloodGroup bg " + " LEFT JOIN t.m_componentType c " + " LEFT JOIN t.m_gender g "
			+ " LEFT JOIN t.m_component mc " + " LEFT JOIN t.m_district d " 
			+ "where t.deleted=false and t.benCallID=:benCallID order by t.bloodReqID desc")
	public List<Objects[]> getBloodRequestsByBenCallID(@Param("benCallID") Long benCallID) throws Exception;

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE T_BloodRequest u set u.bloodBankAddress =:bloodBankAddress, u.bbPersonName =:bbPersonName, u.bbPersonDesignation =:bbPersonDesignation, u.bbMobileNo =:bbMobileNo, u.remarks =:remarks, u.outboundNeeded =:outboundNeeded, u.outboundDate =:outboundDate where u.bloodReqID =:bloodReqID")
	int updateBloodBankDetails(@Param("bloodBankAddress") String bloodBankAddress,
			@Param("bbPersonName") String bbPersonName, @Param("bbPersonDesignation") String bbPersonDesignation,
			@Param("bbMobileNo") String bbMobileNo, @Param("bloodReqID") Long bloodReqID,
			@Param("remarks") String remarks, @Param("outboundNeeded") Boolean outboundNeeded, @Param("outboundDate") Timestamp outboundDate);
}
