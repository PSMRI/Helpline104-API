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
@Table(name = "db_reporting.fact_104prescription", schema = "db_reporting")

public class PrescriptionReport
{

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Fact_104prescriptionID")
	private Long fact_104prescriptionID;

	@Expose
	@Column(name = "PrescriptionID")
	private Long prescriptionID;

	
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;

	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;

	@Expose
	@Column(name = "UserID")
	private Integer userID;

	@Expose
	@Column(name = "DiagnosisProvided")
	private String diagnosisProvided;

	@Expose
	@Column(name = "Remarks")
	private String remarks;

	@Expose
	@Column(name = "DrugName")
	private String drugName;

	@Expose
	@Column(name = "DrugGroupName")
	private String drugGroupName;

	@Expose
	@Column(name = "DrugForm")
	private String drugForm;

	@Expose
	@Column(name = "DrugRoute")
	private String drugRoute;

	@Expose
	@Column(name = "Frequency")
	private String frequency;

	@Expose
	@Column(name = "Dosage")
	private String dosage;

	@Expose
	@Column(name = "NoOfDays")
	private Integer noOfDays;

	@Expose
	@Column(name = "TimeToConsume")
	private String timeToConsume;

	@Expose
	@Column(name = "SideEffects")
	private String sideEffects;
	
	@Column(name = "ValidTill")
	private Timestamp validTill;
	
	@Expose
	@Transient
	private String validTillDate;

	
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

	public PrescriptionReport(
			Long prescriptionID, Long beneficiaryRegID, Long benCallID, Integer userID, String diagnosisProvided, String remarks, String drugName,
			String drugGroupName, String drugForm, String drugRoute, String frequency, String dosage, Integer noOfDays, String timeToConsume,
			String sideEffects, Timestamp validTill, Timestamp createdDate, Long beneficiaryID, String firstName, String lastName, String healthCareWorker, String gender, Timestamp dob,
			String permVillage, String permSubDistrict, String permDistrict, String callTypeName, String callSubTypeName, String phoneNo,
			Integer callReceivedUserID, Integer callEndUserID,String agentID, String agentFirstName)
	{
		super();

		this.prescriptionID = prescriptionID;
		this.beneficiaryRegID = beneficiaryRegID;
		this.benCallID = benCallID;
		this.userID = userID;
		this.diagnosisProvided = diagnosisProvided;
		this.remarks = remarks;
		this.drugName = drugName;
		this.drugGroupName = drugGroupName;
		this.drugForm = drugForm;
		this.drugRoute = drugRoute;
		this.frequency = frequency;
		this.dosage = dosage;
		this.noOfDays = noOfDays;
		this.timeToConsume = timeToConsume;
		this.sideEffects = sideEffects;
		this.validTill = validTill;
		this.validTillDate = dateFormat.format(validTill);
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
