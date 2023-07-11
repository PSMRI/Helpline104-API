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
package com.iemr.helpline104.data.report;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.helpline104.utils.mapper.OutputMapper;

@Entity
@Table(name = "db_reporting.fact_bloodrequest", schema = "db_reporting")

public class BloodrequestReport implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Fact_BloodRequestID")
	private Long fact_BloodRequestID;

	@Expose
	@Column(name = "BloodReqID")
	private Long bloodReqID;

	@Expose
	@Column(name = "RequestID")
	private String requestID;

	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;

	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;

	@Expose
	@Column(name = "RecipientBeneficiaryID")
	private Long recipientBeneficiaryID;

	@Expose
	@Column(name = "RecipientName")
	private String recipientName;

	@Expose
	@Column(name = "RecipientAge")
	private Integer recipientAge;

	@Column(name = "RecipientGenderID")
	private Integer recipientGenderID;

	@Expose
	@Column(name = "RecipientGenderName")
	private String recipientGenderName;

	@Expose
	@Column(name = "TypeOfRequest")
	private String typeOfRequest;

	@Column(name = "BloodGroupID")
	private Integer bloodGroupID;

	@Expose
	@Column(name = "BloodGroupName")
	private String bloodGroupName;

	@Column(name = "ComponentTypeID")
	private Integer componentTypeID;

	@Expose
	@Column(name = "ComponentTypeName")
	private String componentTypeName;

	@Column(name = "ComponentID")
	private Integer componentID;

	@Column(name = "ComponentName")
	private String componentName;

	@Expose
	@Column(name = "UnitRequired")
	private String unitRequired;

	@Expose
	@Column(name = "HospitalAdmitted")
	private String hospitalAdmitted;

	@Column(name = "DistrictID")
	private Integer districtID;

	@Expose
	@Column(name = "DistrictName")
	private String hospitalDistrictName;

	@Expose
	@Column(name = "OutboundNeeded")
	private Boolean outboundNeeded;

	@Expose
	@Column(name = "OutboundDate")
	private Timestamp outboundDate;
	
	@Expose
	@Transient
	private String outboundCallDate;

	@Expose
	@Column(name = "BloodBankAddress")
	private String bloodBankAddress;

	@Expose
	@Column(name = "BBPersonName")
	private String bloodBankPersonName;

	@Expose
	@Column(name = "BBMobileNo")
	private String bloodBankMobileNo;

	@Expose
	@Column(name = "BBPersonDesignation")
	private String bloodBankPersonDesignation;

	@Expose
	@Column(name = "Remarks")
	private String remarks;

	@Expose
	@Column(name = "Feedback")
	private String feedback;

	@Expose
	@Column(name = "IsSelf")
	private Byte isSelf;

	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "CreatedDate")
	private Timestamp createdDate;

	@Expose
	@Transient
	private String date;

	// beneficiary details from dim_beneficiary
	@OneToOne
	@JoinColumn(updatable = false, insertable = false, name = "beneficiaryRegID", referencedColumnName = "beneficiaryRegID")
	private BeneficiaryReport beneficiaryReport;

	@Expose
	@Transient
	private Long beneficiaryID;

	@Expose
	@Transient
	private String firstName;

	@Expose
	@Transient
	private String lastName;

	@Expose
	@Transient
	private String healthCareWorker;

	@Expose
	@Transient
	private String gender;
	
	@Transient
	private Timestamp dob;
	
	@Expose	
	@Transient
	private String dateOfBirth;

	@Expose
	@Transient
	private String village;

	@Expose
	@Transient
	private String subDistrict;

	@Expose
	@Transient
	private String district;

	// call details from fact_bencall
	@OneToOne
	@JoinColumn(updatable = false, insertable = false, name = "benCallID", referencedColumnName = "benCallID")
	private BencallReport bencallReport;

	@Expose
	@Transient
	private String callType;

	@Expose
	@Transient
	private String callSubType;

	@Expose
	@Transient
	private String phoneNumber;

	@Expose
	@Transient
	private Integer callReceivedUserID;

	@Expose
	@Transient
	private Integer callEndUserID;

	// search params
	@Transient
	private Timestamp startDateTime;

	@Transient
	private Timestamp endDateTime;
	
	@Transient
	private Integer subDistrictID;
	
	@Transient
	private Integer villageID;
	
	@Transient
	private String searchCriteria;
	
	@Expose
	@Transient
	private String agentID;
	
	@Expose
	@Transient
	private String agentName;

	public BloodrequestReport(
			Long bloodReqID, String requestID, Long beneficiaryRegID, Long benCallID, Long recipientBeneficiaryID, String recipientName,
			Integer recipientAge, String recipientGenderName, String bloodGroupName, String componentTypeName, String componentName,
			String unitRequired, String hospitalAdmitted, String hospitalDistrictName, Boolean outboundNeeded, Timestamp outboundDate,
			String bloodBankAddress, String bloodBankPersonName, String bloodBankMobileNo, String bloodBankPersonDesignation, String remarks,
			String feedback, Byte isSelf, Timestamp createdDate, Long beneficiaryID, String firstName, String lastName, String healthCareWorker,
			String gender, Timestamp dob, String permVillage, String permSubDistrict, String permDistrict, String callTypeName,
			String callSubTypeName, String phoneNo, Integer callReceivedUserID, Integer callEndUserID, String agentID, String agentFirstName)
	{
		super();

		this.bloodReqID = bloodReqID;
		this.requestID = requestID;
		this.beneficiaryRegID = beneficiaryRegID;
		this.benCallID = benCallID;
		this.recipientBeneficiaryID = recipientBeneficiaryID;
		this.recipientName = recipientName;
		this.recipientAge = recipientAge;
		this.recipientGenderName = recipientGenderName;
		this.bloodGroupName = bloodGroupName;
		this.componentTypeName = componentTypeName;
		this.componentName = componentName;
		this.unitRequired = unitRequired;
		this.hospitalAdmitted = hospitalAdmitted;
		this.hospitalDistrictName = hospitalDistrictName;
		this.outboundNeeded = outboundNeeded;
		this.outboundDate = outboundDate;
		if(outboundDate != null) {
		this.outboundCallDate = dateFormat.format(outboundDate);
		}
		this.bloodBankAddress = bloodBankAddress;
		this.bloodBankPersonName = bloodBankPersonName;
		this.bloodBankMobileNo = bloodBankMobileNo;
		this.bloodBankPersonDesignation = bloodBankPersonDesignation;
		this.remarks = remarks;
		this.feedback = feedback;
		this.isSelf = isSelf;
		this.createdDate = createdDate;
		this.date = dateFormat.format(createdDate);
		this.beneficiaryID = beneficiaryID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.healthCareWorker = healthCareWorker;
		this.gender = gender;
		this.dob = dob;
		if(dob!=null) {
		this.dateOfBirth = dateFormat.format(dob);
		}
		this.village = permVillage;
		this.subDistrict = permSubDistrict;
		this.district = permDistrict;
		this.callType = callTypeName;
		this.callSubType = callSubTypeName;
		this.phoneNumber = phoneNo;
		this.callReceivedUserID = callReceivedUserID;
		this.callEndUserID = callEndUserID;
		this.agentID=agentID;
		this.agentName=agentFirstName;
	}

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString()
	{
		return outputMapper.gson().toJson(this);
	}

	public Timestamp getStartDateTime()
	{
		return startDateTime;
	}

	public Timestamp getEndDateTime()
	{
		return endDateTime;
	}

	public Integer getProviderServiceMapID()
	{
		return providerServiceMapID;
	}

	public Integer getDistrictID() {
		return districtID;
	}

	public void setDistrictID(Integer districtID) {
		this.districtID = districtID;
	}

	public Integer getSubDistrictID() {
		return subDistrictID;
	}

	public void setSubDistrictID(Integer subDistrictID) {
		this.subDistrictID = subDistrictID;
	}

	public Integer getVillageID() {
		return villageID;
	}

	public void setVillageID(Integer villageID) {
		this.villageID = villageID;
	}

	public BloodrequestReport()
	{
		
	}

	public Long getFact_BloodRequestID() {
		return fact_BloodRequestID;
	}

	public void setFact_BloodRequestID(Long fact_BloodRequestID) {
		this.fact_BloodRequestID = fact_BloodRequestID;
	}

	public String getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
}
