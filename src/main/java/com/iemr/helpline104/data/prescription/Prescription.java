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
package com.iemr.helpline104.data.prescription;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;


@Entity
@Table(name = "t_104prescription")
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	public Long prescriptionID;
	@Expose
	private Long beneficiaryRegID;
	@Expose
	private Long benCallID;
	@Expose
	private Integer userID;
	@Expose
	private String diagnosisProvided;
    // private List<I_Beneficiary> i_Beneficiaries;
	@Expose
	@Transient
	private List<PrescribedDrug> prescribedDrugs;	

	
	private String remarks;
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;
	private String createdBy;
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Date lastModDate;

	public Prescription() {
		super();
		
	}
	

	public Prescription(Long prescriptionID, Long beneficiaryRegID, Long benCallID, String diagnosisProvided, String remarks, Date createdDate) {
		super();
		this.prescriptionID = prescriptionID;
		this.beneficiaryRegID = beneficiaryRegID;
		this.benCallID = benCallID;
		this.diagnosisProvided = diagnosisProvided;		
		this.remarks = remarks;
		this.createdDate = createdDate;
		
	}

	public Long getPrescriptionID() {
		return prescriptionID;
	}

	public void setPrescriptionID(Long prescriptionID) {
		this.prescriptionID = prescriptionID;
	}

	public Long getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	public void setBeneficiaryRegID(Long beneficiaryRegID) {
		this.beneficiaryRegID = beneficiaryRegID;
	}

	public Long getBenCallID() {
		return benCallID;
	}

	public void setBenCallID(Long benCallID) {
		this.benCallID = benCallID;
	}

	public String getDiagnosisProvided() {
		return diagnosisProvided;
	}

	public void setDiagnosisProvided(String diagnosisProvided) {
		this.diagnosisProvided = diagnosisProvided;
	}

	

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}	

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastModDate() {
		return lastModDate;
	}

	public void setLastModDate(Date lastModDate) {
		this.lastModDate = lastModDate;
	}

	public String toString() {
		return new Gson().toJson(this);
	}

	private Integer getUserID() {
		return userID;
	}

	private void setUserID(Integer userID) {
		this.userID = userID;
	}

	public List<PrescribedDrug> getPrescribedDrugs() {
		return prescribedDrugs;
	}

	public void setPrescribedDrugs(List<PrescribedDrug> prescribedDrugs) {
		this.prescribedDrugs = prescribedDrugs;
	}
	
	

}
