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
@Table(name = "db_reporting.fact_104benmedhistory", schema = "db_reporting")

public class BenmedhistoryReport implements Serializable
{

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Fact_104BenMedHistoryID")
	private Long fact_104BenMedHistoryID;

	@Expose
	@Column(name = "BenHistoryID")
	private Long benHistoryID;

	@Expose
	@Column(name = "RequestID")
	private String requestID;

	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;

	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;

	@Expose
	@Column(name = "PatientName")
	private String patientName;

	@Expose
	@Column(name = "PatientAge")
	private Integer patientAge;

	@Column(name = "PatientGenderID")
	private Integer patientGenderID;

	@Expose
	@Column(name = "PatientGenderName")
	private String patientGenderName;

	@Expose
	@Column(name = "SymptomID")
	private String symptomID;

	@Column(name = "Algorithm")
	private String algorithm;

	@Expose
	@Column(name = "DiseaseSummaryID")
	private String diseaseSummaryID;

	@Expose
	@Column(name = "DiseaseSummary")
	private String diseaseSummary;

	@Expose
	@Column(name = "Allergies")
	private String allergies;

	@Expose
	@Column(name = "SelecteDiagnosisID")
	private String selecteDiagnosisID;

	@Expose
	@Column(name = "SelecteDiagnosis")
	private String selecteDiagnosis;

	@Expose
	@Column(name = "AddedAdvice")
	private String addedAdvice;

	@Expose
	@Column(name = "PrescriptionID")
	private Long prescriptionID;

	@Column(name = "Rx_UserID")
	private Integer rx_UserID;

	@Column(name = "Rx_DiagnosisProvided")
	private String rx_DiagnosisProvided;

	@Column(name = "Rx_Remarks")
	private Integer rx_Remarks;

	@Expose
	@Column(name = "ActionByHAO")
	private String actionByHAO;

	@Expose
	@Column(name = "ActionByCO")
	private String actionByCO;

	@Expose
	@Column(name = "ActionByMO")
	private String actionByMO;

	@Expose
	@Column(name = "ActionByPD")
	private String actionByPD;

	
	@Column(name = "Remarks")
	private String remarks;

	@Expose
	@Column(name = "IsSelf")
	private Byte isSelf;

	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;

	
	@Column(name = "CreatedDate")
	private Timestamp createdDate;
	
	@Expose	
	@Transient
	private String date;

	// beneficiary details from dim_beneficiary
	@OneToOne
	@JoinColumn(updatable = false, insertable = false, name = "beneficiaryRegID", referencedColumnName = "beneficiaryRegID")
	private BeneficiaryReport beneficiaryReport;
	
	@OneToOne
	@JoinColumn(updatable = false, insertable = false, name = "prescriptionID", referencedColumnName = "prescriptionID")
	private PrescriptionReport prescriptionReport;

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
	
	@Transient
	private Long locationID;

	public BenmedhistoryReport(
			Long benHistoryID, String requestID, Long beneficiaryRegID, Long benCallID, String patientName, Integer patientAge,
			String patientGenderName, String algorithm, String diseaseSummaryID, String diseaseSummary, String allergies, String selecteDiagnosisID,
			String selecteDiagnosis, String addedAdvice, Long prescriptionID, String action, String role, String remarks, Byte isSelf,
			Timestamp createdDate, Long beneficiaryID, String firstName, String lastName, String healthCareWorker, String gender, Timestamp dob,
			String permVillage, String permSubDistrict, String permDistrict, String callTypeName, String callSubTypeName, String phoneNo,
			Integer callReceivedUserID, Integer callEndUserID, String agentID, String agentFirstName)

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
	
	@Column(name = "CategoryID")
	private Long categoryID;

	@Column(name = "CategoryName")
	private String category;
	
	@Transient
	private Long villageID;

	@Transient
	private Long subDistrictID;

	@Transient
	private Long districtID;
	
	@Transient
	private Long roleID;
	
	@Column(name = "SubCategoryName")
	private String subCategory;


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
