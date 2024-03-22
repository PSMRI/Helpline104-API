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
package com.iemr.helpline104.data.foodSafetyCopmlaint;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.iemr.helpline104.data.beneficiarycall.BenCall;
import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.data.location.DistrictBlock;
import com.iemr.helpline104.data.location.DistrictBranchMapping;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.report.BeneficiaryReport;

@Entity
@Table(name = "T_FoodSafetyCopmlaint")
public class T_FoodSafetyCopmlaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("fSComplaintID")
	private Long fSComplaintID;
	private String requestID;
	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "benCallID")
	private BenCall benCall;
	@Expose
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "beneficiaryRegID")
	private BeneficiaryReport beneficiaryReport;

	private String typeOfRequest;
	private Byte isDiarrhea;
	private Byte isVomiting;
	private Byte isAbdominalPain;
	private Byte isChillsOrRigors;
	private Byte isGiddiness;
	private Byte isDehydration;
	private Byte isRashes;
	private Timestamp fromWhen;
	private String historyOfDiet;
	private Byte isFoodConsumed;
	private String typeOfFood;
	private String foodConsumedFrom;
	private String associatedSymptoms;
	@Expose
	private Integer districtID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "districtID")
	private Districts district;
	private Integer districtBlockID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "districtBlockID")
	private DistrictBlock districtBlock;
	private Integer villageID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "villageID")
	private DistrictBranchMapping districtBranchMapping;
	private Short feedbackTypeID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "feedbackTypeID")
	private M_FeedbackType m_feedbackType;

	private String remarks;

	@Expose
	@Column(name = "ProviderServiceMapID")
	private Integer serviceID;

	@Expose
	@Column(name = "IsSelf")
	private Boolean isSelf;

	@Expose
	private String patientName;
	@Expose
	private Integer patientAge;
	@Expose
	private Short patientGenderID;
	@Expose
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;

	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;
	@Expose
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Expose
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Date lastModDate;

	@Expose
	@Transient
	private String phoneNum;

	public String getPhoneNo() {
		return phoneNum;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNum = phoneNo;
	}

	public T_FoodSafetyCopmlaint() {
		super();
	}

	public T_FoodSafetyCopmlaint(Long fSComplaintID, String requestID, Long beneficiaryRegID, String typeOfRequest,
			Byte isDiarrhea, Byte isVomiting, Byte isAbdominalPain, Byte isChillsOrRigors, Byte isGiddiness,
			Byte isDehydration, Byte isRashes, Timestamp fromWhen, String historyOfDiet, Byte isFoodConsumed,
			String typeOfFood, String foodConsumedFrom, String associatedSymptoms, Short feedbackTypeID,
			String feedbackTypeName, String remarks, Date createdDate, Boolean isSelf, Integer districtID,
			String districtName, Integer districtBlockID, String districtBlockName, Integer villageID,
			String villageName) {
		super();
		this.fSComplaintID = fSComplaintID;
		this.requestID = requestID;
		this.beneficiaryRegID = beneficiaryRegID;
		this.typeOfRequest = typeOfRequest;
		this.isDiarrhea = isDiarrhea;
		this.isVomiting = isVomiting;
		this.isAbdominalPain = isAbdominalPain;
		this.isChillsOrRigors = isChillsOrRigors;
		this.isGiddiness = isGiddiness;
		this.isDehydration = isDehydration;
		this.isRashes = isRashes;
		this.fromWhen = fromWhen;
		this.historyOfDiet = historyOfDiet;
		this.isFoodConsumed = isFoodConsumed;
		this.typeOfFood = typeOfFood;
		this.foodConsumedFrom = foodConsumedFrom;
		this.associatedSymptoms = associatedSymptoms;
		this.feedbackTypeID = feedbackTypeID;
		this.remarks = remarks;
		this.m_feedbackType = new M_FeedbackType(feedbackTypeID, feedbackTypeName);
		this.isSelf = isSelf;
		this.district = new Districts(districtID, districtName);
		this.districtBlock = new DistrictBlock(districtBlockID, districtBlockName);
		this.districtBranchMapping = new DistrictBranchMapping(villageID, villageName);
		this.createdDate = createdDate;

	}

	public Long getBenCallID() {
		return benCallID;
	}

	public Long getFSComplaintID() {
		return fSComplaintID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getRequestID() {
		return this.requestID;
	}

	public Long getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	public void setBeneficiaryRegID(Long beneficiaryRegID) {
		this.beneficiaryRegID = beneficiaryRegID;
	}

	public String getTypeOfRequest() {
		return typeOfRequest;
	}

	public void setTypeOfRequest(String typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

	public Byte getIsDiarrhea() {
		return isDiarrhea;
	}

	public void setIsDiarrhea(Byte isDiarrhea) {
		this.isDiarrhea = isDiarrhea;
	}

	public Byte getIsVomiting() {
		return isVomiting;
	}

	public void setIsVomiting(Byte isVomiting) {
		this.isVomiting = isVomiting;
	}

	public Byte getIsAbdominalPain() {
		return isAbdominalPain;
	}

	public void setIsAbdominalPain(Byte isAbdominalPain) {
		this.isAbdominalPain = isAbdominalPain;
	}

	public Byte getIsChillsOrRigors() {
		return isChillsOrRigors;
	}

	public void setIsChillsOrRigors(Byte isChillsOrRigors) {
		this.isChillsOrRigors = isChillsOrRigors;
	}

	public Byte getIsGiddiness() {
		return isGiddiness;
	}

	public void setIsGiddiness(Byte isGiddiness) {
		this.isGiddiness = isGiddiness;
	}

	public Byte getIsDehydration() {
		return isDehydration;
	}

	public void setIsDehydration(Byte isDehydration) {
		this.isDehydration = isDehydration;
	}

	public Byte getIsRashes() {
		return isRashes;
	}

	public void setIsRashes(Byte isRashes) {
		this.isRashes = isRashes;
	}

	public Timestamp getFromWhen() {
		return fromWhen;
	}

	public void setFromWhen(Timestamp fromWhen) {
		this.fromWhen = fromWhen;
	}

	public String getHistoryOfDiet() {
		return historyOfDiet;
	}

	public void setHistoryOfDiet(String historyOfDiet) {
		this.historyOfDiet = historyOfDiet;
	}

	public Byte getIsFoodConsumed() {
		return isFoodConsumed;
	}

	public void setIsFoodConsumed(Byte isFoodConsumed) {
		this.isFoodConsumed = isFoodConsumed;
	}

	public String getTypeOfFood() {
		return typeOfFood;
	}

	public void setTypeOfFood(String typeOfFood) {
		this.typeOfFood = typeOfFood;
	}

	public String getFoodConsumedFrom() {
		return foodConsumedFrom;
	}

	public void setFoodConsumedFrom(String foodConsumedFrom) {
		this.foodConsumedFrom = foodConsumedFrom;
	}

	public String getAssociatedSymptoms() {
		return associatedSymptoms;
	}

	public void setAssociatedSymptoms(String associatedSymptoms) {
		this.associatedSymptoms = associatedSymptoms;
	}

	public Integer getDistrictID() {
		return districtID;
	}

	public Short getFeedbackTypeID() {
		return feedbackTypeID;
	}

	public void setFeedbackTypeID(Short feedbackTypeID) {
		this.feedbackTypeID = feedbackTypeID;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getServiceID() {
		return serviceID;
	}

	public void setServiceID(Integer serviceID) {
		this.serviceID = serviceID;
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

	public void setBenCall(BenCall benCall) {
		this.benCall = benCall;
	}

	public String toString() {
		return new Gson().toJson(this);
	}

}
