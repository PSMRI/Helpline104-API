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
package com.iemr.helpline104.data.drugMapping;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.iemr.helpline104.data.drugGroup.M_DrugGroup;
import com.iemr.helpline104.data.drugMaster.M_DrugMaster;
import com.iemr.helpline104.data.location.M_ProviderServiceMapping;

@Entity
@Table(name="m_104drugmapping")
public class M_104drugmapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "DrugMapID")
	private Integer drugMapID;
	
	
	@Expose
	@Column(name = "DrugId")
	private Integer drugId;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "drugId")
	private M_DrugMaster m_drugMaster;
	
	@Expose
	@Column(name = "DrugName")
	private String drugName;
	
	@Expose
	@Column(name = "DrugGroupID")
	private Integer drugGroupID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "drugGroupID")
	private M_DrugGroup m_drugGroup;
	
	@Expose
	@Column(name = "DrugGroupName")
	private String drugGroupName;
	
	@Expose
	@Column(name = "Remarks")
	private String remarks;

	@Expose
	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "ProviderServiceMapID")
	private M_ProviderServiceMapping m_providerServiceMapping;
	
	@Expose
	@Column(name = "Deleted",insertable = false, updatable = true)
	private Boolean deleted; 
	@Expose
	@Column(name = "Processed",insertable = false, updatable = true)
	private String processed;
	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;
	@Expose
	@Column(name = "CreatedDate" ,insertable = false, updatable = false)
	private Date createdDate;
	@Expose
	@Column(name = "ModifiedBy")
	private String modifiedBy; 
	@Expose
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Date lastModDate;
	
	public M_104drugmapping() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public M_104drugmapping(String drugName) {
		super();		
		this.drugName = drugName;		
	}

	
	public M_104drugmapping(Integer drugMapID, String drugName) {
		super();
		this.drugMapID = drugMapID;		
		this.drugName = drugName;		
	}
	



	public M_104drugmapping(Integer drugMapID, Integer drugId, String drugName,
			Integer drugGroupID, String drugGroupName, String remarks,
			Integer providerServiceMapID) {
		super();
		this.drugMapID = drugMapID;
		this.drugId = drugId;
		this.drugName = drugName;
		this.drugGroupID = drugGroupID;
		this.drugGroupName = drugGroupName;
		this.remarks = remarks;
		this.providerServiceMapID = providerServiceMapID;
	}







	public Integer getDrugId() {
		return drugId;
	}



	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}



	public M_DrugGroup getM_drugGroup() {
		return m_drugGroup;
	}



	public void setM_drugGroup(M_DrugGroup m_drugGroup) {
		this.m_drugGroup = m_drugGroup;
	}



	public String getDrugName() {
		return drugName;
	}



	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}



	public Integer getDrugGroupID() {
		return drugGroupID;
	}



	public void setDrugGroupID(Integer drugGroupID) {
		this.drugGroupID = drugGroupID;
	}



	public M_DrugMaster getM_drugMaster() {
		return m_drugMaster;
	}



	public void setM_drugMaster(M_DrugMaster m_drugMaster) {
		this.m_drugMaster = m_drugMaster;
	}



	public String getDrugGroupName() {
		return drugGroupName;
	}



	public void setDrugGroupName(String drugGroupName) {
		this.drugGroupName = drugGroupName;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public Integer getProviderServiceMapID() {
		return providerServiceMapID;
	}



	public void setProviderServiceMapID(Integer providerServiceMapID) {
		this.providerServiceMapID = providerServiceMapID;
	}



	public M_ProviderServiceMapping getM_providerServiceMapping() {
		return m_providerServiceMapping;
	}



	public void setM_providerServiceMapping(M_ProviderServiceMapping m_providerServiceMapping) {
		this.m_providerServiceMapping = m_providerServiceMapping;
	}



	public Boolean getDeleted() {
		return deleted;
	}



	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}



	public String getProcessed() {
		return processed;
	}



	public void setProcessed(String processed) {
		this.processed = processed;
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



	public Integer getDrugMapID() {
		return drugMapID;
	}



	public String toString() {
		return new Gson().toJson(this);
	}
	
}
