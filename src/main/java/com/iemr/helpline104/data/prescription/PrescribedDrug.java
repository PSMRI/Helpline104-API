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
import java.sql.Timestamp;

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
import com.iemr.helpline104.data.drugMapping.M_104drugmapping;

@Entity
@Table(name = "t_104prescribeddrug")
public class PrescribedDrug {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "PrescribedDrugID")
	private Long prescribedDrugID;
	@Expose
	@Column(name = "PrescriptionID")
	private Long prescriptionID;

	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "prescriptionID")
	private Prescription t_prescription;

	@Expose
	@Column(name = "DrugMapID")
	private Integer drugMapID;
	
	@Expose
	@Transient
	private String drugName;

	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "drugMapID")
	private M_104drugmapping m_104drugmapping;

	@Expose
	@Column(name = "DrugForm")
	private String drugForm;
	// for usage
	@Expose
	@Column(name = "DrugRoute")
	private String drugRoute;
	@Expose
	@Column(name = "Frequency")
	private String frequency;
	@Expose
	@Column(name = "dosage")
	private String dosage;
	@Expose
	@Column(name = "noOfDays")
	private Integer noOfDays;
	@Expose
	@Column(name = "timeToConsume")
	private String timeToConsume;
	@Expose
	@Column(name = "sideEffects")
	private String sideEffects;
	@Expose
	@Column(name = "ValidTill")
	private Timestamp validTill;
	// private String remarks;
	@Expose
	@Column(name = "Deleted")
	private Boolean deleted;
	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Date lastModDate;

	public PrescribedDrug() {
		super();

	}

	/*
	 * p.drugMapID,d.drugName,p.drugForm,p.drugRoute,p.frequency,p.dosage,p.
	 * noOfDays, " + "p.timeToConsume,p.sideEffects
	 */

	public PrescribedDrug(Long drugID,Integer drugMapID, String drugName, String drugForm, String drugRoute, String frequency,
			String dosage, Integer noOfDays, String timeToConsume, String sideEffects, Timestamp validTill) {
		super();
		this.prescribedDrugID = drugID;
		this.drugMapID = drugMapID;		
		this.drugForm = drugForm;
		this.drugRoute = drugRoute; // this.m_drugMaster = new
		//this.m_104drugmapping = new M_104drugmapping(drugMapID, drugName);
		this.drugName = drugName;
		this.dosage = dosage;
		this.frequency = frequency;
		this.noOfDays = noOfDays;
		this.timeToConsume = timeToConsume;
		this.sideEffects = sideEffects;
		this.noOfDays = noOfDays;
		this.sideEffects = sideEffects;
		this.validTill = validTill;
		
	}	

	private Long getPrescriptionID() {
		return prescriptionID;
	}

	public void setPrescriptionID(Long prescriptionID) {
		this.prescriptionID = prescriptionID;
	}
	
	public Integer getNoOfDays() {
		return noOfDays;
	}
	public void setValidTill(Timestamp validTill) {
		this.validTill = validTill;
	}
	
	public Timestamp getValidTill() {
		return validTill;
	}
	

}
