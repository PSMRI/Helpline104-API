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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.helpline104.utils.mapper.OutputMapper;

@Entity
@Table(name = "db_reporting.fact_directoryservice", schema = "fact_directoryservice")

public class DirectoryserviceReport
{

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Fact_DirectoryServiceID")
	private Long fact_DirectoryServiceID;

	
	@Column(name = "DirectoryServiceID")
	private Long directoryServiceID;

	
	@Column(name = "RequestID")
	private String requestID;

	
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;

	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;

	
	@Column(name = "InstituteDirectoryID")
	private Integer instituteDirectoryID;

	@Expose
	@Column(name = "InstituteDirectoryName")
	private String instituteDirectoryName;

	
	@Column(name = "InstituteSubDirectoryID")
	private Integer instituteSubDirectoryID;

	@Expose
	@Column(name = "InstituteSubDirectoryName")
	private String instituteSubDirectoryName;

	@Expose
	@Column(name = "InstitutionID")
	private Integer institutionID;

	@Expose
	@Column(name = "AcceptorHospitalName")
	private String acceptorHospitalName;

	@Expose
	@Column(name = "AcceptorHospitalState")
	private String acceptorHospitalState;

	@Expose
	@Column(name = "AcceptorHospitalDistrict")
	private String acceptorHospitalDistrict;

	@Expose
	@Column(name = "AcceptorHospitalSubDistrict")
	private String acceptorHospitalSubDistrict;

	@Expose
	@Column(name = "AcceptorHospitalVillage")
	private String acceptorHospitalVillage;

	
	@Column(name = "Remarks")
	private String remarks;

	
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
	
	@Expose
	@Transient
	private String agentID;
	
	@Expose
	@Transient
	private String agentName;

	public DirectoryserviceReport(
			Long directoryServiceID, String requestID, Long beneficiaryRegID, Long benCallID, String instituteDirectoryName,
			String instituteSubDirectoryName, Integer institutionID, String acceptorHospitalName, String acceptorHospitalState,
			String acceptorHospitalDistrict, String acceptorHospitalSubDistrict, String acceptorHospitalVillage, String remarks, Timestamp createdDate, Long beneficiaryID, String firstName,
			String lastName, String healthCareWorker, String gender, Timestamp dob, String permVillage, String permSubDistrict, String permDistrict,
			String callTypeName, String callSubTypeName, String phoneNo, Integer callReceivedUserID, Integer callEndUserID, String agentID, String agentFirstName)
	{
		super();
		this.directoryServiceID = directoryServiceID;
		this.requestID = requestID;
		this.beneficiaryRegID = beneficiaryRegID;
		this.benCallID = benCallID;
		this.instituteDirectoryName = instituteDirectoryName;
		this.instituteSubDirectoryName = instituteSubDirectoryName;
		this.institutionID = institutionID;
		this.acceptorHospitalName = acceptorHospitalName;
		this.acceptorHospitalState = acceptorHospitalState;
		this.acceptorHospitalDistrict = acceptorHospitalDistrict;
		this.acceptorHospitalSubDistrict = acceptorHospitalSubDistrict;
		this.acceptorHospitalVillage = acceptorHospitalVillage;
		this.remarks = remarks;
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

}
