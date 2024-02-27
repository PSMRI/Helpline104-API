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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "t_COVID19")
public class COVIDHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "COVID19ID")
	private Long covid19ID;	
	
	@Expose
	@Column(name = "BenMedHistoryID")
	private Long benHistoryID;	

	@Expose
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;

	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;
	
	@Expose
	@Column(name = "travel_14days")
	private Boolean travel_14days;
	
	@Expose
	@Column(name = "travel_type")
	private String travel_type;
	
	@Expose
	@Column(name = "domestic_mode")
	private String domestic_mode;
	
	@Expose
	@Column(name = "domStateID_from")
	private Integer domStateID_from;
	
	@Expose
	@Column(name = "domDistrictID_from")
	private Integer domDistrictID_from;
	
	@Expose
	@Column(name = "domTalukID_from")
	private Integer domTalukID_from;
	
	@Expose
	@Column(name = "domStateID_to")
	private Integer domStateID_to;
	
	@Expose
	@Column(name = "domDistrictID_to")
	private Integer domDistrictID_to;
	
	@Expose
	@Column(name = "domTalukID_to")
	private Integer domTalukID_to;
	
	@Expose
	@Column(name = "international_mode")
	private String international_mode;
	
	@Expose
	@Column(name = "interCountryID_from")
	private Integer interCountryID_from;
	
	@Expose
	@Column(name = "interCityID_from")
	private Long interCityID_from;
	
	@Expose
	@Column(name = "interCountryID_to")
	private Integer interCountryID_to;
	
	@Expose
	@Column(name = "interCityID_to")
	private Long interCityID_to;
	
	@Expose
	@Column(name = "symptoms")
	private String symptoms;
	
	@Expose
	@Column(name = "COVID19_contact_history")
	private String COVID19_contact_history;
	
	@Expose
	@Column(name = "medical_consultation")
	private Boolean medical_consultation;	
	
	@Expose
	@Column(name = "Suspected_COVID19")
	private Boolean Suspected_COVID19;
	
	@Expose
	@Column(name = "recommendation")
	private String recommendation;
	
	@Expose
	@Column(name = "Deleted")
	private Boolean deleted;
	
	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@Expose
	@Column(name = "CreatedDate", insertable=false, updatable=false)
	private Timestamp createdDate;
	
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@Column(name = "LastModDate", insertable=false, updatable=false)
	private Date lastModDate;
	
	@Expose
	@Transient
	private Boolean isCOVIDAvailable;

	public Long getCovid19ID() {
		return covid19ID;
	}

	public void setCovid19ID(Long covid19id) {
		covid19ID = covid19id;
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

	public Boolean getTravel_14days() {
		return travel_14days;
	}

	public void setTravel_14days(Boolean travel_14days) {
		this.travel_14days = travel_14days;
	}

	public String getTravel_type() {
		return travel_type;
	}

	public void setTravel_type(String travel_type) {
		this.travel_type = travel_type;
	}

	public String getDomestic_mode() {
		return domestic_mode;
	}

	public void setDomestic_mode(String domestic_mode) {
		this.domestic_mode = domestic_mode;
	}

	public Integer getDomStateID_from() {
		return domStateID_from;
	}

	public void setDomStateID_from(Integer domStateID_from) {
		this.domStateID_from = domStateID_from;
	}

	public Integer getDomDistrictID_from() {
		return domDistrictID_from;
	}

	public void setDomDistrictID_from(Integer domDistrictID_from) {
		this.domDistrictID_from = domDistrictID_from;
	}

	public Integer getDomTalukID_from() {
		return domTalukID_from;
	}

	public void setDomTalukID_from(Integer domTalukID_from) {
		this.domTalukID_from = domTalukID_from;
	}

	public Integer getDomStateID_to() {
		return domStateID_to;
	}

	public void setDomStateID_to(Integer domStateID_to) {
		this.domStateID_to = domStateID_to;
	}

	public Integer getDomDistrictID_to() {
		return domDistrictID_to;
	}

	public void setDomDistrictID_to(Integer domDistrictID_to) {
		this.domDistrictID_to = domDistrictID_to;
	}

	public Integer getDomTalukID_to() {
		return domTalukID_to;
	}

	public void setDomTalukID_to(Integer domTalukID_to) {
		this.domTalukID_to = domTalukID_to;
	}

	public String getInternational_mode() {
		return international_mode;
	}

	public void setInternational_mode(String international_mode) {
		this.international_mode = international_mode;
	}

	public Integer getInterCountryID_from() {
		return interCountryID_from;
	}

	public void setInterCountryID_from(Integer interCountryID_from) {
		this.interCountryID_from = interCountryID_from;
	}

	public Long getInterCityID_from() {
		return interCityID_from;
	}

	public void setInterCityID_from(Long interCityID_from) {
		this.interCityID_from = interCityID_from;
	}

	public Integer getInterCountryID_to() {
		return interCountryID_to;
	}

	public void setInterCountryID_to(Integer interCountryID_to) {
		this.interCountryID_to = interCountryID_to;
	}

	public Long getInterCityID_to() {
		return interCityID_to;
	}

	public void setInterCityID_to(Long interCityID_to) {
		this.interCityID_to = interCityID_to;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getCOVID19_contact_history() {
		return COVID19_contact_history;
	}

	public void setCOVID19_contact_history(String cOVID19_contact_history) {
		COVID19_contact_history = cOVID19_contact_history;
	}

	public Boolean getMedical_consultation() {
		return medical_consultation;
	}

	public void setMedical_consultation(Boolean medical_consultation) {
		this.medical_consultation = medical_consultation;
	}

	public Boolean getSuspected_COVID19() {
		return Suspected_COVID19;
	}

	public void setSuspected_COVID19(Boolean suspected_COVID19) {
		Suspected_COVID19 = suspected_COVID19;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
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

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
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

	public Boolean getIsCOVIDAvailable() {
		return isCOVIDAvailable;
	}

	public void setIsCOVIDAvailable(Boolean isCOVIDAvailable) {
		this.isCOVIDAvailable = isCOVIDAvailable;
	}

	public Long getBenHistoryID() {
		return benHistoryID;
	}

	public void setBenHistoryID(Long benHistoryID) {
		this.benHistoryID = benHistoryID;
	}
	
}
