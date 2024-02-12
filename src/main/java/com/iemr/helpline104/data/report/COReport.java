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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.helpline104.utils.mapper.OutputMapper;

public class COReport implements Serializable
{

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@Expose
	private Long benHistoryID;

	@Expose
	private String requestID;
	
	private Long beneficiaryRegID;

	@Expose
	private Long benCallID;

	@Expose
	private String patientName;

	@Expose
	private Integer patientAge;

	@Expose
	private String patientGenderName;

	@Expose
	private String symptomID;
	
	private String algorithm;

	@Expose
	private String diseaseSummaryID;

	@Expose
	private String diseaseSummary;

	@Expose
	private String allergies;

	@Expose
	private String selecteDiagnosisID;

	@Expose
	private String selecteDiagnosis;

	@Expose
	private String addedAdvice;

	@Expose
	private Long prescriptionID;

	@Expose
	private String actionByHAO;

	@Expose
	private String actionByCO;

	@Expose
	private String actionByMO;

	@Expose
	private String actionByPD;

	@Expose
	private Byte isSelf;

	private Integer providerServiceMapID;

	@Expose	
	@Transient
	private String date;

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
	private String category;
	
	@Expose
	private String subCategory;
	
	@Expose
	@Transient
	private String agentID;
	
	@Expose
	@Transient
	private String agentName;
	
	@Transient
	private Long locationID;
	
	private String remarks;

	private Timestamp createdDate;

	public COReport(
			Long benHistoryID, String requestID, Long beneficiaryRegID, Long benCallID, String patientName, Integer patientAge,
			String patientGenderName, String algorithm, String diseaseSummaryID, String diseaseSummary, String allergies, String selecteDiagnosisID,
			String selecteDiagnosis, String addedAdvice, Long prescriptionID, String action, String role, String remarks, Byte isSelf,
			Timestamp createdDate, Long beneficiaryID, String firstName, String lastName, String healthCareWorker, String gender, Timestamp dob,
			String permVillage, String permSubDistrict, String permDistrict, String callTypeName, String callSubTypeName, String phoneNo,
			Integer callReceivedUserID, Integer callEndUserID, String category, String subCategory,String agentID, String agentFirstName)

	{
		super();
		this.benHistoryID = benHistoryID;
		this.requestID = requestID;
		this.beneficiaryRegID = beneficiaryRegID;
		this.benCallID = benCallID;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientGenderName = patientGenderName;
		this.algorithm = algorithm;
		this.diseaseSummaryID = diseaseSummaryID;
		this.diseaseSummary = diseaseSummary;
		this.allergies = allergies;
		this.selecteDiagnosisID = selecteDiagnosisID;
		this.selecteDiagnosis = selecteDiagnosis;
		this.addedAdvice = addedAdvice;
		this.prescriptionID = prescriptionID;

		if (role.equals("HAO"))
			this.actionByHAO = action;
		else if (role.equals("MO"))
			this.actionByMO = action;
		else if (role.equals("CO"))
			this.actionByCO = action;
		else if (role.equals("PD"))
			this.actionByPD = action;

		this.remarks = remarks;
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
		this.category=category;
		this.subCategory=subCategory;		
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
	
	@Transient
	private String searchCriteria;
	
	private Long categoryID;
	
	@Transient
	private Long villageID;

	@Transient
	private Long subDistrictID;

	@Transient
	private Long districtID;
	
	@Transient
	private Long roleID;


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
	
	public String getAgentID()
	{
		return agentID;
	}

	/**
	 * @return the searchCriteria
	 */
	public String getSearchCriteria() {
		return searchCriteria;
	}

	/**
	 * @param searchCriteria the searchCriteria to set
	 */
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public Long getVillageID() {
		return villageID;
	}

	public void setVillageID(Long villageID) {
		this.villageID = villageID;
	}

	public Long getSubDistrictID() {
		return subDistrictID;
	}

	public void setSubDistrictID(Long subDistrictID) {
		this.subDistrictID = subDistrictID;
	}

	public Long getDistrictID() {
		return districtID;
	}

	public void setDistrictID(Long districtID) {
		this.districtID = districtID;
	}

	public Long getRoleID() {
		return roleID;
	}

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}

	public Long getLocationID() {
		return locationID;
	}

	public void setLocationID(Long locationID) {
		this.locationID = locationID;
	}
}