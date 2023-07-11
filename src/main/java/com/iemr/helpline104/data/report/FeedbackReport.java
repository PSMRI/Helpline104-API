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
@Table(name = "db_reporting.fact_feedback", schema = "db_reporting")

public class FeedbackReport
{

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Fact_FeedbackID")
	private Long fact_FeedbackID;

	@Expose
	@Column(name = "FeedbackID")
	private Long feedbackID;

	
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;

	@Expose
	@Column(name = "RequestID")
	private String requestID;

	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;

	@Expose
	@Column(name = "FeedbackAgainst")
	private String feedbackAgainst;

	
	@Column(name = "DesignationID")
	private Integer designationID;

	@Expose
	@Column(name = "DesignationName")
	private String designation;

	
	@Column(name = "InstitutionID")
	private Integer institutionID;

	
	@Column(name = "InstitutionName")
	private String institutionName;

	
	@Column(name = "StateID")
	private Integer stateID;

	@Expose
	@Column(name = "StateName")
	private String stateName;

	
	@Column(name = "DistrictID")
	private Integer districtID;

	@Expose
	@Column(name = "DistrictName")
	private String districtName;

	
	@Column(name = "BlockID")
	private Integer blockID;

	@Expose
	@Column(name = "BlockName")
	private String blockName;

	
	@Column(name = "DistrictBranchID")
	private Integer districtBranchID;

	@Expose
	@Column(name = "DistrictBranchName")
	private String districtBranchName;

	
	@Column(name = "InstitutionTypeID")
	private Integer institutionTypeID;

	@Expose
	@Column(name = "InstituteTypeName")
	private String instituteTypeName;

	
	@Column(name = "SeverityID")
	private Integer severityID;

	@Expose
	@Column(name = "SeverityName")
	private String severityName;

	
	@Column(name = "FeedbackTypeID")
	private Short feedbackTypeID;	

	@Expose
	@Column(name = "FeedbackTypeName")
	private String feedbackTypeName;
	
	
	@Column(name = "FeedbackNatureID")
	private Integer feedbackNatureID;

	@Expose
	@Column(name = "FeedbackNatureName")
	private String feedbackNatureName;

	
	@Column(name = "FeedbackStatusID")
	private Integer feedbackStatusID;

	@Expose
	@Column(name = "FeedbackStatusName")
	private String feedbackStatusName;

	@Expose
	@Column(name = "Feedback")
	private String feedback;

	
	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;

	@Expose
	@Column(name = "UserID")
	private Integer userID;

	@Expose
	@Column(name = "SMSPhoneNo")
	private String smsPhoneNo;
	
	@Column(name = "ServiceAvailDate")
	private Timestamp serviceAvailDate;
	
	@Expose	
	@Transient
	private String serviceAvailedDate;

	@Expose
	@Column(name = "EmailStatusID")
	private Integer emailStatusID;

	
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
	@Expose
	private String agentID;
	
	@Expose
	@Transient
	private String agentName;

	public FeedbackReport(
			Long feedbackID, String requestID, Long beneficiaryRegID, Long benCallID, String feedbackAgainst, String designation,
			String institutionName, String stateName, String districtName, String blockName, String districtBranchName, String instituteTypeName,
			String severityName, String feedbackTypeName, String feedbackNatureName, String feedbackStatusName, String feedback, Integer userID,
			String smsPhoneNo, Timestamp serviceAvailDate, Timestamp createdDate, Long beneficiaryID, String firstName, String lastName, String healthCareWorker, String gender, Timestamp dob,
			String permVillage, String permSubDistrict, String permDistrict, String callTypeName, String callSubTypeName, String phoneNo,
			Integer callReceivedUserID, Integer callEndUserID, String agentID, String agentFirstName)
	{
		super();
		this.feedbackID = feedbackID;
		this.requestID = requestID;
		this.beneficiaryRegID = beneficiaryRegID;
		this.benCallID = benCallID;
		this.feedbackAgainst = feedbackAgainst;
		this.designation = designation;
		this.institutionName = institutionName;
		this.stateName = stateName;
		this.districtName = districtName;
		this.blockName = blockName;
		this.districtBranchName = districtBranchName;
		this.instituteTypeName = instituteTypeName;
		this.severityName = severityName;
		this.feedbackTypeName = feedbackTypeName;
		this.feedbackNatureName = feedbackNatureName;
		this.feedbackStatusName = feedbackStatusName;
		this.feedback = feedback;
		this.userID = userID;
		this.smsPhoneNo = smsPhoneNo;
		this.serviceAvailDate = serviceAvailDate;
		if(serviceAvailDate != null) {
		this.serviceAvailedDate = dateFormat.format(serviceAvailDate);
		}
		this.createdDate = createdDate;
		this.date = dateFormat.format(createdDate);
		this.beneficiaryID = beneficiaryID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.healthCareWorker = healthCareWorker;
		this.gender = gender;
		this.dob = dob;
		if(dob != null) {
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
	
	public Short getFeedbackTypeID()
	{
		return feedbackTypeID;
	}
	
	public String getAgentID()
	{
		return agentID;
	}
	
	


}
