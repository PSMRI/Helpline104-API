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
package com.iemr.helpline104.data.bloodRequest;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

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

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.iemr.helpline104.data.beneficiarycall.BenCall;
import com.iemr.helpline104.data.bloodComponent.M_Component;
import com.iemr.helpline104.data.bloodComponentType.M_BloodGroup;
import com.iemr.helpline104.data.bloodComponentType.M_ComponentType;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.userbeneficiarydata.M_Gender;

@Entity
@Table(name="T_BloodRequest")
public class T_BloodRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Long bloodReqID;
	@Expose
	private String requestID;
	@Transient	
	@Expose	
	private List<RequestedBloodBank> requestedBloodBank;
	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "benCallID")
	private BenCall benCall;	
	@Expose
	private Long beneficiaryRegID;
	@Expose
	private Long recipientBeneficiaryID;
	@Expose
	private String recipientName;
	@Expose
	private Integer recipientAge;
	@Expose
	private Short recipientGenderID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "recipientGenderID")	
	private M_Gender m_gender;
	@Expose
	private String typeOfRequest;
	@Expose
	private Integer bloodGroupID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "bloodGroupID")
	private M_BloodGroup m_bloodGroup;
	@Expose
	private Integer componentTypeID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "componentTypeID")
	private M_ComponentType m_componentType;
	@Expose
	private Integer componentID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "componentID")
	private M_Component m_component;
	@Expose
	private String unitRequired;
	@Expose
	private String hospitalAdmitted;
	@Expose
	private Integer districtID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "districtID")
	private Districts m_district;
	@Expose
	private Boolean outboundNeeded;
	@Expose
	private Timestamp outboundDate;
	@Expose
	private String bloodBankAddress;
	@Expose
	private String bbPersonName;
	@Expose
	private String bbPersonDesignation;
	@Expose
	private String bbMobileNo;
	@Expose
	private String remarks;
	@Expose
	private String feedback;
	
	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;
	
	@Expose
	@Column(name = "IsSelf")
	private Boolean isSelf;
	
	@Expose
	@Transient
	private String receivedAgentID;
	
	@Expose
	@Column(name = "Deleted",insertable = false, updatable = true)
	private Boolean deleted;
	@Expose
	private String createdBy;
	@Expose
	@Column(name = "CreatedDate", insertable=false, updatable=false)
	private Date createdDate;
	@Expose
	private String modifiedBy;
	
	

	public T_BloodRequest(Long bloodReqID, String requestID, Long beneficiaryRegID, 
			Long recipientBeneficiaryID, String recipientName, Integer recipientAge, Short recipientGenderID, String genderName,
			String typeOfRequest, Integer bloodGroupID, String bloodGroup, String bloodGroupDesc, Integer componentTypeID, String componentType, String componentTypeDesc,
			Integer componentID, String component, String componentDesc, String unitRequired, String hospitalAdmitted,
			Integer districtID, String districtName, Boolean outboundNeeded, Timestamp outboundDate, String bloodBank, String mobileNo,String bbPersonName, String bbPersonDesignation,
			String remarks, String feedback/*, String receivedAgentID */) {
		super();
		this.bloodReqID = bloodReqID;
		this.requestID = requestID;
		this.beneficiaryRegID = beneficiaryRegID;
		this.recipientBeneficiaryID = recipientBeneficiaryID;
		this.recipientName = recipientName;
		this.recipientAge = recipientAge;
		this.m_gender = new M_Gender(recipientGenderID, genderName);
		this.typeOfRequest = typeOfRequest;
		this.bloodGroupID = bloodGroupID;
		this.m_bloodGroup = new M_BloodGroup(bloodGroupID, bloodGroup, bloodGroupDesc);
		this.m_componentType = new M_ComponentType(componentTypeID, componentType, componentTypeDesc);
		this.m_component = new M_Component(componentID, component, componentDesc);
		this.unitRequired = unitRequired;
		this.hospitalAdmitted = hospitalAdmitted;
		this.m_district = new Districts(districtID, districtName);
		this.outboundNeeded = outboundNeeded;
		this.outboundDate = outboundDate;
		this.bloodBankAddress = bloodBank;
		this.bbMobileNo = mobileNo;
		this.bbPersonName = bbPersonName;
		this.bbPersonDesignation = bbPersonDesignation;
		this.remarks = remarks;
		this.feedback = feedback;
	}
	
	public T_BloodRequest() {
		super();
	}
	
	public Long getBenCallID()
	{
		return benCallID;
	}

	public Long getBloodReqID() {
		return bloodReqID;
	}

	public void setBloodReqID(Long bloodReqID) {
		this.bloodReqID = bloodReqID;
	}
	
	public String getBbPersonName() {
		return bbPersonName;
	}

	public void setBbPersonName(String bbPersonName) {
		this.bbPersonName = bbPersonName;
	}
	
	public String getBbPersonDesignation() {
		return bbPersonDesignation;
	}

	public void setBbPersonDesignation(String bbPersonDesignation) {
		this.bbPersonDesignation = bbPersonDesignation;
	}

	public Long getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	public void setBeneficiaryRegID(Long beneficiaryRegID) {
		this.beneficiaryRegID = beneficiaryRegID;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public Integer getRecipientAge() {
		return recipientAge;
	}

	public void setRecipientAge(Integer recipientAge) {
		this.recipientAge = recipientAge;
	}

	public Short getRecipientGender() {
		return recipientGenderID;
	}

	public void setRecipientGender(Short recipientGender) {
		this.recipientGenderID = recipientGender;
	}

	public String getTypeOfRequest() {
		return typeOfRequest;
	}

	public void setTypeOfRequest(String typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

	public Integer getComponentTypeID() {
		return componentTypeID;
	}

	public void setComponentTypeID(Integer componentTypeID) {
		this.componentTypeID = componentTypeID;
	}

	public Integer getComponentID() {
		return componentID;
	}

	public void setComponentID(Integer componentID) {
		this.componentID = componentID;
	}

	public String getHospitalAdmitted() {
		return hospitalAdmitted;
	}

	public void setHospitalAdmitted(String hospitalAdmitted) {
		this.hospitalAdmitted = hospitalAdmitted;
	}

	public Integer getDistrictID() {
		return districtID;
	}

	public void setDistrictID(Integer districtID) {
		this.districtID = districtID;
	}

	public Boolean getOutboundNeeded() {
		return outboundNeeded;
	}

	public void setOutboundNeeded(Boolean outboundNeeded) {
		this.outboundNeeded = outboundNeeded;
	}

	public Timestamp getOutboundDate() {
		return outboundDate;
	}

	public void setOutboundDate(Timestamp outboundDate) {
		this.outboundDate = outboundDate;
	}

	public String getBloodBank() {
		return bloodBankAddress;
	}

	public void setBloodBank(String bloodBank) {
		this.bloodBankAddress = bloodBank;
	}

	public String getMobileNo() {
		return bbMobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.bbMobileNo = mobileNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public Long getRecipientBeneficiaryID() {
		return recipientBeneficiaryID;
	}

	public void setRecipientBeneficiaryID(Long recipientBeneficiaryID) {
		this.recipientBeneficiaryID = recipientBeneficiaryID;
	}
	
	public Integer getBloodGroup() {
		return bloodGroupID;
	}

	public void setBloodGroup(Integer bloodGroup) {
		this.bloodGroupID = bloodGroup;
	}

	public String getUnitRequired() {
		return unitRequired;
	}

	public void setUnitRequired(String unitRequired) {
		this.unitRequired = unitRequired;
	}
	
	public String getRequestID() {
		return this.requestID;
	}
	
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public String toString() {
		return new Gson().toJson(this);
	}
	

	public void setBenCall(BenCall benCall)
	{
		this.benCall = benCall;
	}

	public List<RequestedBloodBank> getRequestedBloodBank()
	{
		return requestedBloodBank;
	}

	public void setRequestedBloodBank(List<RequestedBloodBank> requestedBloodBank)
	{
		this.requestedBloodBank = requestedBloodBank;
	}

}
