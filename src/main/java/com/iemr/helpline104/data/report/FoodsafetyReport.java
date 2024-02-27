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
@Table(name = "db_reporting.fact_foodsafetycopmlaint", schema = "db_reporting")

public class FoodsafetyReport
{

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Fact_FSComplaintID")
	private Long fact_FSComplaintID;

	@Expose
	@Column(name = "FSComplaintID")
	private Long fsComplaintID;

	@Expose
	@Column(name = "RequestID")
	private String requestID;

	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;

	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;

	@Expose
	@Column(name = "TypeOfRequest")
	private String typeOfRequest;

	@Expose
	@Column(name = "isDiarrhea")
	private Byte isDiarrhea;

	@Expose
	@Column(name = "isVomiting")
	private Byte isVomiting;

	@Expose
	@Column(name = "isAbdominalPain")
	private Byte isAbdominalPain;

	@Expose
	@Column(name = "isChillsOrRigors")
	private Byte isChillsOrRigors;

	@Expose
	@Column(name = "isGiddiness")
	private Byte isGiddiness;

	@Expose
	@Column(name = "isDehydration")
	private Byte isDehydration;

	@Expose
	@Column(name = "isRashes")
	private Byte isRashes;
	
	@Column(name = "FromWhen")
	private Timestamp fromWhen;
	
	@Expose	
	@Transient
	private String fromWhenDate;

	@Expose
	@Column(name = "HistoryOfDiet")
	private String historyOfDiet;

	@Expose
	@Column(name = "isFoodConsumed")
	private Byte isFoodConsumed;

	@Expose
	@Column(name = "TypeOfFood")
	private String typeOfFood;

	@Expose
	@Column(name = "FoodConsumedFrom")
	private String foodConsumedFrom;

	@Expose
	@Column(name = "AssociatedSymptoms")
	private String associatedSymptoms;

	@Expose
	@Column(name = "FeedbackType")
	private String feedbackType;

	@Expose
	@Column(name = "Remarks")
	private String remarks;

	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;

	@Expose
	@Column(name = "IsSelf")
	private Boolean isSelf;

	@Expose
	@Column(name = "PatientName")
	private String patientName;
	@Expose
	@Column(name = "PatientAge")
	private Integer patientAge;
	@Expose
	@Column(name = "PatientGenderName")
	private String patientGender;

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

	public FoodsafetyReport(
			Long fsComplaintID, String requestID, Long beneficiaryRegID, Long benCallID, String typeOfRequest, Byte isDiarrhea, Byte isVomiting,
			Byte isAbdominalPain, Byte isChillsOrRigors, Byte isGiddiness, Byte isDehydration, Byte isRashes, Timestamp fromWhen,
			String historyOfDiet, Byte isFoodConsumed, String typeOfFood, String foodConsumedFrom, String associatedSymptoms, String feedbackType,
			String remarks, Boolean isSelf, String patientName, Integer patientAge, String patientGenderName, Timestamp createdDate, Long beneficiaryID, String firstName, String lastName, String healthCareWorker, String gender,
			Timestamp dob, String permVillage, String permSubDistrict, String permDistrict, String callTypeName, String callSubTypeName,
			String phoneNo, Integer callReceivedUserID, Integer callEndUserID, String agentID, String agentFirstName)
	{
		super();

		this.fsComplaintID = fsComplaintID;
		this.requestID = requestID;
		this.beneficiaryRegID = beneficiaryRegID;
		this.benCallID = benCallID;
		this.typeOfRequest = typeOfRequest;
		this.isDiarrhea = isDiarrhea;
		this.isVomiting = isVomiting;
		this.isAbdominalPain = isAbdominalPain;
		this.isChillsOrRigors = isChillsOrRigors;
		this.isGiddiness = isGiddiness;
		this.isDehydration = isDehydration;
		this.isRashes = isRashes;
		this.fromWhen = fromWhen;
		this.fromWhenDate = dateFormat.format(fromWhen);
		this.historyOfDiet = historyOfDiet;
		this.isFoodConsumed = isFoodConsumed;
		this.typeOfFood = typeOfFood;
		this.foodConsumedFrom = foodConsumedFrom;
		this.associatedSymptoms = associatedSymptoms;
		this.feedbackType = feedbackType;
		this.remarks = remarks;
		this.isSelf = isSelf;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientGender = patientGenderName;
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
