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
package com.iemr.helpline104.data.disease;

import java.sql.Date;

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
@Table(name = "m_104diseasesummary")
public class Disease {

	@Id
	@Expose
	@Column(name = "DiseasesummaryID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer diseasesummaryID;

	@Expose
	@Column(name = "DiseaseName")
	private String diseaseName;

	@Expose
	@Column(name = "Summary")
	private String summary;

	@Expose
	@Column(name = "is_dangerous")
	private String couldbedangerous;

	@Expose
	@Column(name = "Causes")
	private String causes;

	@Expose
	@Column(name = "Dos_Donts")
	private String dos_donts;

	@Expose
	@Column(name = "Symptoms_Signs")
	private String symptoms_Signs;

	@Expose
	@Column(name = "medicaladvice")
	private String medicaladvice;

	@Expose
	@Column(name = "Riskfactors")
	private String riskfactors;

	@Expose
	@Column(name = "Treatment")
	private String treatment;

	@Expose
	@Column(name = "Self_care")
	private String self_care;

	@Expose
	@Column(name = "Investigations")
	private String investigations;

	@Expose
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;

	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;

	@Expose
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;

	@Expose
	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Expose
	@Column(name = "LastModDate", insertable = false, updatable = true)
	private Date lastModDate;

	@Expose
	@Transient
	private Integer pageSize;

	@Expose
	@Transient
	private Integer pageNo;

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}

	public Disease() {
	}

	public Disease(Integer diseasesummaryID, String diseaseName) {
		this.diseasesummaryID = diseasesummaryID;
		this.diseaseName = diseaseName;
	}

	public Integer getDiseasesummaryID() {
		return diseasesummaryID;
	}

	public void setDiseasesummaryID(Integer diseasesummaryID) {
		this.diseasesummaryID = diseasesummaryID;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCouldbedangerous() {
		return couldbedangerous;
	}

	public void setCouldbedangerous(String couldbedangerous) {
		this.couldbedangerous = couldbedangerous;
	}

	public String getCauses() {
		return causes;
	}

	public void setCauses(String causes) {
		this.causes = causes;
	}

	public String getSymptoms_Signs() {
		return symptoms_Signs;
	}

	public void setSymptoms_Signs(String symptoms_Signs) {
		this.symptoms_Signs = symptoms_Signs;
	}

	public String getMedicaladvice() {
		return medicaladvice;
	}

	public void setMedicaladvice(String medicaladvice) {
		this.medicaladvice = medicaladvice;
	}

	public String getRiskfactors() {
		return riskfactors;
	}

	public void setRiskfactors(String riskfactors) {
		this.riskfactors = riskfactors;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getSelf_care() {
		return self_care;
	}

	public void setSelf_care(String self_care) {
		this.self_care = self_care;
	}

	public String getInvestigations() {
		return investigations;
	}

	public void setInvestigations(String investigations) {
		this.investigations = investigations;
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

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getDos_donts() {
		return dos_donts;
	}

	public void setDos_donts(String dos_donts) {
		this.dos_donts = dos_donts;
	}

}
