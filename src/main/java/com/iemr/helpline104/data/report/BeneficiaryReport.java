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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.helpline104.utils.mapper.OutputMapper;

@Entity
@Table(name = "db_reporting.dim_beneficiary", schema = "db_reporting")

public class BeneficiaryReport implements Serializable 
{

	private static final long serialVersionUID = 1L;
	@Transient
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Dim_BeneficiaryID")
	private Long dim_beneficiaryID;

	@Expose
	@Column(name = "BeneficiaryID")
	private Long beneficiaryID;

	
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;

	
	@Column(name = "TitleId")
	private Integer titleId;

	@Expose
	@Column(name = "Title")
	private String title;

	@Expose
	@Column(name = "FirstName")
	private String firstName;

	@Expose
	@Column(name = "MiddleName")
	private String middleName;

	@Expose
	@Column(name = "LastName")
	private String lastName;

	
	@Column(name = "Status")
	private String status;

	
	@Column(name = "GenderId")
	private Integer genderId;

	@Expose
	@Column(name = "Gender")
	private String gender;

	
	@Column(name = "MaritalStatusId")
	private Integer maritalStatusId;

	@Expose
	@Column(name = "MaritalStatus")
	private String maritalStatus;

	@Expose
	@Column(name = "DOB")
	private Timestamp dob;

	@Expose
	@Column(name = "FatherName")
	private String fatherName;

	@Expose
	@Column(name = "MotherName")
	private String motherName;

	
	@Column(name = "SpouseName")
	private String spouseName;

	@Expose
	@Column(name = "EmergencyRegistration")
	private Byte emergencyRegistration;

	
	@Column(name = "HealthCareWorkerId")
	private Integer healthCareWorkerId;

	@Expose
	@Column(name = "HealthCareWorker")
	private String healthCareWorker;

	
	@Column(name = "educationId")
	private Integer educationId;

	@Expose
	@Column(name = "education")
	private String education;

	
	@Column(name = "occupationId")
	private Integer occupationId;

	
	@Column(name = "occupation")
	private String occupation;

	
	@Column(name = "communityId")
	private Integer communityId;

	@Expose
	@Column(name = "community")
	private String community;

	
	@Column(name = "SexualOrientationID")
	private Integer sexualOrientationID;

	
	@Column(name = "SexualOrientationType")
	private String sexualOrientationType;

	@Expose
	@Column(name = "PreferredPhoneNum")
	private String preferredPhoneNum;

	@Expose
	@Column(name = "PhoneNum1")
	private String phoneNum1;

	@Expose
	@Column(name = "PhoneNum2")
	private String phoneNum2;

	@Expose
	@Column(name = "PhoneNum3")
	private String PhoneNum3;

	@Expose
	@Column(name = "PhoneNum4")
	private String phoneNum4;

	
	@Column(name = "EmailId")
	private String emailId;

	
	@Column(name = "PermVillageId")
	private Integer permVillageId;

	@Expose
	@Column(name = "PermVillage")
	private String permVillage;

	
	@Column(name = "PermSubDistrictId")
	private Integer permSubDistrictId;

	@Expose
	@Column(name = "PermSubDistrict")
	private String permSubDistrict;

	
	@Column(name = "PermDistrictId")
	private Integer PermDistrictId;

	@Expose
	@Column(name = "PermDistrict")
	private String permDistrict;

	
	@Column(name = "PermStateId")
	private Integer PermStateId;

	
	@Column(name = "PermPinCode")
	private Integer permPinCode;

	
	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;
	
	
	@Column(name = "CreatedDate")
	private Timestamp createdDate;	
	
	
	
	@Expose	
	@Transient
	private String date;

	/*
	 * // call details from fact_bencall
	 * 
	 * @OneToOne
	 * 
	 * @JoinColumn(updatable = false, insertable = false, name = "benCallID", referencedColumnName = "benCallID")
	 * private BencallReport bencallReport;
	 */

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
	
	@Expose
	@Transient
	private String agentID;
	
	@Expose
	@Transient
	private String agentName;
	
	
	

	public BeneficiaryReport(
			Long beneficiaryID, String title, String firstName, String lastName, String gender, String maritalStatus, Timestamp dob,
			String spouseName, Byte emergencyRegistration, String healthCareWorker, String education, String occupation, String community,
			String emailId, String permVillage, String permSubDistrict, String permDistrict,Timestamp createdDate, String callTypeName, String callSubTypeName,
			String phoneNo, Integer callReceivedUserID, Integer callEndUserID, String agentID, String agentFirstName)
	{
		super();
		this.beneficiaryID = beneficiaryID;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.dob = dob;
		this.spouseName = spouseName;
		this.emergencyRegistration = emergencyRegistration;
		this.healthCareWorker = healthCareWorker;
		this.education = education;
		this.occupation = occupation;
		this.community = community;
		this.emailId = emailId;
		this.permVillage = permVillage;
		this.permSubDistrict = permSubDistrict;
		this.permDistrict = permDistrict;
		this.createdDate = createdDate;
		this.date = dateFormat.format(createdDate);
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

	public BeneficiaryReport()
	{
		
	}
	public BeneficiaryReport(Long BeneficiaryID, String PreferredPhoneNum)
	{
		this.beneficiaryID = BeneficiaryID;
		this.preferredPhoneNum = PreferredPhoneNum;
	}

}
