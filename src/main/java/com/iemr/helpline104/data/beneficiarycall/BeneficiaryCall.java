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
package com.iemr.helpline104.data.beneficiarycall;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.iemr.helpline104.data.location.DistrictBlock;
import com.iemr.helpline104.data.location.DistrictBranchMapping;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.users.M_ServiceMaster;
import com.iemr.helpline104.utils.mapper.OutputMapper;

@Entity
@Table(name = "t_bencall")
public class BeneficiaryCall {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BenCallID")
	@Expose
	private Long benCallID;
	@OneToMany(mappedBy = "beneficiaryCall", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BenCallServicesMappingHistory> benCallServicesMappingHistories;

	@Expose
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;

	@Expose
	@Column(name = "CallID")
	private String callID;

	@Expose
	@Column(name = "CalledServiceID")
	private Integer calledServiceID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "calledServiceID")
	private M_ServiceMaster m_serviceMaster;

	@Expose
	@Column(name = "is1097")
	private Boolean is1097;

	@Expose
	@Column(name = "CallTime")
	private Date callTime;
	@Expose
	@Column(name = "Remarks")
	private String remarks;

	@Column(name = "ServicesProvided")
	private String servicesProvided;

	@Expose
	@Column(name = "CallClosureType")
	private String callClosureType;

	@Expose
	@Column(name = "DispositionStatusID")
	private Integer dispositionStatusID;

	@Expose
	@Column(name = "CallReceivedUserID")
	private Integer callReceivedUserID;

	@Expose
	@Column(name = "CallEndUserID")
	private Integer callEndUserID;
	@Column(name = "Category")
	private String category;
	@Column(name = "SubCategory")
	private String subCategory;

	@Expose
	@Column(name = "CDICallStatus")
	private String cDICallStatus;

	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;
	@Column(name = "CreatedBy")
	private String createdBy;
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Date lastModDate;

	@Expose
	@Transient
	private Long informationServices;

	@Expose
	@Transient
	private Long feedbackServices;

	@Expose
	@Transient
	private Long referralServices;

	public Long getBenCallID() {
		return benCallID;
	}

	public List<BenCallServicesMappingHistory> getBenCallServicesMappingHistories() {
		return benCallServicesMappingHistories;
	}

	public Long getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	public Integer getCalledServiceID() {
		return calledServiceID;
	}

	public String getCallID() {
		return callID;
	}

	public void setCallID(String callID) {
		this.callID = callID;
	}

	public Boolean getIs1097() {
		return is1097;
	}

	public void setIs1097(Boolean is1097) {
		this.is1097 = is1097;
	}

	public Date getCallTime() {
		return callTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getServicesProvided() {
		return servicesProvided;
	}

	public String getCallClosureType() {
		return callClosureType;
	}

	public Integer getDispositionStatusID() {
		return dispositionStatusID;
	}

	public Integer getCallReceivedUserID() {
		return callReceivedUserID;
	}

	public Long getInformationServices() {
		return informationServices;
	}

	public Long getFeedbackServices() {
		return feedbackServices;
	}

	public Long getReferralServices() {
		return referralServices;
	}

	public Integer getCallEndUserID() {
		return callEndUserID;
	}

	public String getCategory() {
		return category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public Date getLastModDate() {
		return lastModDate;
	}

	public BeneficiaryCall() {

	}

	public BeneficiaryCall(Long benCallID, String benCallServicesMappingHistories, Integer calledServiceID,
			Boolean is1097, Date callTime, String remarks, String callClosureType, Integer dispositionStatusID) {
		this.benCallID = benCallID;
		Type listType = new TypeToken<List<String>>() {
		}.getType();
		this.benCallServicesMappingHistories = new Gson().fromJson(benCallServicesMappingHistories, listType);
		this.calledServiceID = calledServiceID;
		this.is1097 = is1097;
		this.callTime = callTime;
		this.remarks = remarks;
		this.callClosureType = callClosureType;
		this.dispositionStatusID = dispositionStatusID;
	}

	public BeneficiaryCall(Long benCallID, Date callTime, String remarks, Long informationServices,
			Long feedbackServices, Long referralServices, Date createdDate) {
		this.benCallID = benCallID;
		this.callTime = callTime;
		this.remarks = remarks;
		this.informationServices = informationServices;
		this.feedbackServices = feedbackServices;
		this.referralServices = referralServices;
		this.createdDate = createdDate;
	}

	public BeneficiaryCall(Long benCallID, Integer callReceivedUserID, Long beneficiaryRegID, String firstName,
			String middleName, String lastName, Short genderID, String genderName, Date dOB, Integer cityID,
			String cityName, Short beneficiaryTypeID, String beneficiaryType, Integer healthCareWorkerId) {
		this.benCallID = benCallID;
		this.callReceivedUserID = callReceivedUserID;

	}

	public BeneficiaryCall(Long beneficiaryRegID, Boolean is1097, String createdBy) {
		this.beneficiaryRegID = beneficiaryRegID;
		this.is1097 = is1097;
		this.createdBy = createdBy;
	}

	public BeneficiaryCall(Long benCallID, Integer callReceivedUserID, Long beneficiaryRegID, String firstName,
			String middleName, String lastName, Short genderID, String genderName, Date dOB, Integer cityID,
			String cityName, Short beneficiaryTypeID, String beneficiaryType, Integer healthCareWorkerId,
			Districts districts, DistrictBlock districtBlocks, DistrictBranchMapping districtBranchMapping) {
		this.benCallID = benCallID;
		this.callReceivedUserID = callReceivedUserID;

	}

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}
}
