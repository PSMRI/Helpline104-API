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
package com.iemr.helpline104.data.casesheet;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.helpline104.data.beneficiarycall.BenCall;
import com.iemr.helpline104.data.beneficiarycall.OutboundCallRequest;
import com.iemr.helpline104.data.category.CategoryDetails;
import com.iemr.helpline104.data.category.SubCategoryDetails;
import com.iemr.helpline104.data.prescription.Prescription;
import com.iemr.helpline104.data.userbeneficiarydata.M_Gender;
import com.iemr.helpline104.utils.mapper.OutputMapper;

@Entity
@Table(name = "t_104benmedhistory")
public class H104BenMedHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "BenHistoryID")
	private Long benHistoryID;
	@Expose
	@Column(name = "RequestID")
	private String requestID;
	@Expose
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;
	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;
	@Expose
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(updatable = false, insertable = false, name = "benCallID")
	private BenCall benCall;

	/*
	 * @OneToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(updatable = false, insertable = false, name = "benCallID")
	 */
	@Expose
	@Transient
	private OutboundCallRequest outboundCallRequest;
	@Expose
	@Transient
	private String callID;
	@Expose
	@Column(name = "PatientName")
	private String patientName;
	@Expose
	@Column(name = "PatientAge")
	private Integer patientAge;
	@Expose
	@Column(name = "PatientGenderID")
	private Short patientGenderID;
	@Expose
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(updatable = false, insertable = false, name = "patientGenderID")
	private M_Gender m_gender;
	@Expose
	@Column(name = "Algorithm")
	private String algorithm;
	@Expose
	@Column(name = "SymptomID")
	private String symptomID;
	@Expose
	@Column(name = "DiseaseSummary")
	private String diseaseSummary;
	@Expose
	@Column(name = "DiseaseSummaryID")
	private String diseaseSummaryID;
	@Expose
	@Column(name = "Allergies")
	private String allergies;
	@Expose
	@Column(name = "SelecteDiagnosis")
	private String selecteDiagnosis;
	@Expose
	@Column(name = "SelecteDiagnosisID")
	private String selecteDiagnosisID;
	@Expose
	@Column(name = "AddedAdvice")
	private String addedAdvice;
	@Expose
	@Column(name = "PrescriptionID")
	private Long prescriptionID;
	@Expose
	@Column(name = "ActionByHAO")
	private String actionByHAO;
	@Expose
	@Column(name = "ActionByCO")
	private String actionByCO;
	@Expose
	@Column(name = "ActionByPD")
	private String actionByPD;
	@Expose
	@Column(name = "ActionByMO")
	private String actionByMO;
	@Expose
	@Transient
	private List<Prescription> prescription;
	@Expose
	@Column(name = "Remarks")
	private String remarks;
	@Expose
	@Column(name = "IsSelf")
	private Boolean isSelf;
	@Expose
	@Transient
	private Integer districtID;
	@Expose
	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;

	@Expose
	@Column(name = "CategoryID")
	private Integer categoryID;
	@Expose
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(updatable = false, insertable = false, name = "categoryID")
	private CategoryDetails categoryDetails;
	@Expose
	@Column(name = "SubCategoryID")
	private Integer subCategoryID;
	@Expose
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(updatable = false, insertable = false, name = "subCategoryID")
	private SubCategoryDetails subCategoryDetails;
	@Expose
	@Column(name = "Deleted")
	private Boolean deleted;
	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;
	@Expose
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Timestamp createdDate;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Date lastModDate;
	@Expose
	@Column(name = "IsChiefComplaint")
	private Boolean isChiefComplaint;
	@Expose
	@Column(name = "ageUnits")
	private String ageUnits;
	@Expose
	@Column(name = "dOB")
	private Timestamp dOB;

	@Expose
	@Column(name = "RiskLevel")
	private String riskLevel;

	@Expose
	@Column(name = "TreatmentRecommendation")
	private String treatmentRecommendation;

	public H104BenMedHistory(Long beneficiaryRegID, String patientName, Integer patientAge, Short patientGenderID,
			String genderName, String algorithm, String symptomID, String diseaseSummary, String diseaseSummaryID,
			String allergies, String selecteDiagnosis, String selecteDiagnosisID, String addedAdvice, Long prescription,
			String actionByHAO, String actionByCO, String actionByMO, String remarks, Boolean deleted, String createdBy,
			Timestamp createdDate, String modifiedBy, Date lastModDate, String ageUnits, Timestamp dOB) {
		super();
		this.beneficiaryRegID = beneficiaryRegID;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.m_gender = new M_Gender(patientGenderID, genderName);
		this.algorithm = algorithm;
		this.symptomID = symptomID;
		this.diseaseSummary = diseaseSummary;
		this.diseaseSummaryID = diseaseSummaryID;
		this.allergies = allergies;
		this.selecteDiagnosis = selecteDiagnosis;
		this.selecteDiagnosisID = selecteDiagnosisID;
		this.addedAdvice = addedAdvice;
		this.prescriptionID = prescription;
		this.actionByHAO = actionByHAO;
		this.actionByCO = actionByCO;
		this.actionByMO = actionByMO;
		this.remarks = remarks;
		this.deleted = deleted;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.lastModDate = lastModDate;
		this.ageUnits = ageUnits;
		this.dOB = dOB;
	}

	public H104BenMedHistory() {
		super();

	}

	public String getActionByCO() {
		return actionByCO;
	}

	public String getActionByPD() {
		return actionByPD;
	}

	public String getActionByMO() {
		return actionByMO;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public Long getBenHistoryID() {
		return benHistoryID;
	}

	public Integer getDistrictID() {
		return districtID;
	}

	public String getCallID() {
		return callID;
	}

	public Long getBenCallID() {
		return benCallID;
	}

	public Long getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	public void setOutboundCallRequest(OutboundCallRequest outboundCallRequest) {
		this.outboundCallRequest = outboundCallRequest;
	}

	public void setPrescription(List<Prescription> prescription) {
		this.prescription = prescription;
	}

	public void setBenCall(BenCall benCall) {
		this.benCall = benCall;
	}

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}

}
