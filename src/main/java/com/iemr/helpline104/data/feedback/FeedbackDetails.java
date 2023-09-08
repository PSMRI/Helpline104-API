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
package com.iemr.helpline104.data.feedback;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.helpline104.data.feedbackNature.M_FeedbackNature;
import com.iemr.helpline104.data.institute.InstituteType;
import com.iemr.helpline104.utils.mapper.OutputMapper;

@Entity
@Table(name = "t_feedback")
public class FeedbackDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FeedbackID")
	@Expose
	private Long feedbackID;

	@Column(name = "requestID")
	@Expose
	private String requestID;

	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;

	@Column(name = "InstitutionID")
	@Expose
	private Long institutionID;

	@Column(name = "institutionname")
	@Expose
	private String instiName;

	@Column(name = "DesignationID")
	@Expose
	private Integer designationID;
	@Column(name = "SeverityID")
	@Expose
	private Short severityID;
	@Column(name = "FeedbackTypeID")
	@Expose
	private Short feedbackTypeID;
	@Column(name = "StateID")
	@Expose
	private Integer stateID;
	@Column(name = "DistrictID")
	@Expose
	private Integer districtID;
	@Column(name = "DistrictBranchID")
	@Expose
	private Integer districtBranchID;
	@Column(name = "BlockID")
	@Expose
	private Integer blockID;
	@Column(name = "FeedbackStatusID")
	@Expose
	private Short feedbackStatusID;
	@Column(name = "Feedback")
	@Expose
	private String feedback;
	@Column(name = "BeneficiaryRegID")
	@Expose
	private Long beneficiaryRegID;

	@Column(name = "ProviderServiceMapID")
	@Expose
	private Integer ProviderServiceMapID;

	@Transient
	@Expose
	private Integer serviceID;

	@Column(name = "UserID")
	@Expose
	private Integer userID;
	@Column(name = "SMSPhoneNo")
	@Expose
	private String sMSPhoneNo;
	@Column(name = "ServiceAvailDate")
	@Expose
	private Date serviceAvailDate;
	@Column(name = "Deleted", insertable = false, updatable = true)
	@Expose
	private Boolean deleted;
	@Column(name = "CreatedBy")
	@Expose
	private String createdBy;
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date CreatedDate;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Date lastModDate;
	@Transient
	@Expose
	private Integer serviceID1097;

	@Column(name = "InstitutionTypeID")
	@Expose
	private Integer instituteTypeID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "InstitutionTypeID", insertable = false, updatable = false)
	@Expose
	private InstituteType instituteType;

	/**
	 * join to get feedback nature
	 */
	@Expose
	@Column(name = "FeedbackNatureID")
	private Integer feedbackNatureID;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FeedbackNatureID", insertable = false, updatable = false)
	@Expose
	private M_FeedbackNature feedbackNatureDetail;

	@Expose
	@Column(name = "CategoryID")
	private Integer categoryID;

	@Expose
	@Column(name = "SubCategoryID")
	private Integer subCategoryID;

	@Column(name = "FeedbackAgainst")
	@Expose
	private String feedbackAgainst;

	public FeedbackDetails() {

	}

	public FeedbackDetails(Long feedbackID, Long institutionID, String instiName, Integer designationID,
			Short severityID, Short feedbackTypeID, Short feedbackStatusID, String feedback, Long beneficiaryRegID,
			Integer serviceID, Integer userID, String sMSPhoneNo, Date serviceAvailDate, Boolean deleted,
			String createdBy, Date createdDate, String modifiedBy, Date lastModDate, String feedbackAgainst) {
		super();
		this.feedbackID = feedbackID;
		this.institutionID = institutionID;
		this.instiName = instiName;
		this.designationID = designationID;
		this.severityID = severityID;
		this.feedbackTypeID = feedbackTypeID;
		this.feedbackStatusID = feedbackStatusID;
		this.feedback = feedback;
		this.beneficiaryRegID = beneficiaryRegID;
		this.serviceID = serviceID;
		this.userID = userID;
		this.sMSPhoneNo = sMSPhoneNo;
		this.serviceAvailDate = serviceAvailDate;
		this.deleted = deleted;
		this.createdBy = createdBy;
		CreatedDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.lastModDate = lastModDate;
		this.feedbackAgainst = feedbackAgainst;
	}

	public FeedbackDetails(Long feedbackID, Short severityID, Short feedbackTypeID, Short feedbackStatusID,
			String feedback, String createdBy, String feedbackAgainst) {
		this.feedbackID = feedbackID;
		this.severityID = severityID;
		this.feedbackTypeID = feedbackTypeID;
		this.feedbackStatusID = feedbackStatusID;
		this.feedback = feedback;
		this.createdBy = createdBy;
		this.feedbackAgainst = feedbackAgainst;
	}

	public Integer getStateID() {
		return stateID;
	}

	public void setStateID(Integer stateID) {
		this.stateID = stateID;
	}

	public Integer getDistrictBranchID() {
		return districtBranchID;
	}

	public void setDistrictBranchID(Integer districtBranchID) {
		this.districtBranchID = districtBranchID;
	}

	public Integer getBlockID() {
		return blockID;
	}

	public void setBlockID(Integer blockID) {
		this.blockID = blockID;
	}

	public Integer getServiceID1097() {
		return serviceID1097;
	}

	public void setServiceID1097(Integer serviceID1097) {
		this.serviceID1097 = serviceID1097;
	}

	public Integer getInstituteTypeID() {
		return instituteTypeID;
	}

	public void setInstituteTypeID(Integer instituteTypeID) {
		this.instituteTypeID = instituteTypeID;
	}

	public InstituteType getInstituteType() {
		return instituteType;
	}

	public void setInstituteType(InstituteType instituteType) {
		this.instituteType = instituteType;
	}

	public Integer getFeedbackNatureID() {
		return feedbackNatureID;
	}

	public void setFeedbackNatureID(Integer feedbackNatureID) {
		this.feedbackNatureID = feedbackNatureID;
	}

	public M_FeedbackNature getFeedbackNatureDetail() {
		return feedbackNatureDetail;
	}

	public void setFeedbackNatureDetail(M_FeedbackNature feedbackNatureDetail) {
		this.feedbackNatureDetail = feedbackNatureDetail;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public Integer getSubCategoryID() {
		return subCategoryID;
	}

	public void setSubCategoryID(Integer subCategoryID) {
		this.subCategoryID = subCategoryID;
	}

	public String getFeedbackAgainst() {
		return feedbackAgainst;
	}

	public void setFeedbackAgainst(String feedbackAgainst) {
		this.feedbackAgainst = feedbackAgainst;
	}

	public OutputMapper getOutputMapper() {
		return outputMapper;
	}

	public void setOutputMapper(OutputMapper outputMapper) {
		this.outputMapper = outputMapper;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setDistrictID(Integer districtID) {
		this.districtID = districtID;
	}

	public Long getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(Long feedbackID) {
		this.feedbackID = feedbackID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public Long getInstitutionID() {
		return institutionID;
	}

	public void setInstitutionID(Long institutionID) {
		this.institutionID = institutionID;
	}

	public Integer getDesignationID() {
		return designationID;
	}

	public void setDesignationID(Integer designationID) {
		this.designationID = designationID;
	}

	public Short getSeverityID() {
		return severityID;
	}

	public void setSeverityID(Short severityID) {
		this.severityID = severityID;
	}

	public Short getFeedbackTypeID() {
		return feedbackTypeID;
	}

	public void setFeedbackTypeID(Short feedbackTypeID) {
		this.feedbackTypeID = feedbackTypeID;
	}

	public Short getFeedbackStatusID() {
		return feedbackStatusID;
	}

	public void setFeedbackStatusID(Short feedbackStatusID) {
		this.feedbackStatusID = feedbackStatusID;
	}

	public Integer getDistrictID() {
		return districtID;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Long getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	public void setBeneficiaryRegID(Long beneficiaryRegID) {
		this.beneficiaryRegID = beneficiaryRegID;
	}

	public Integer getProviderServiceMapID() {
		return ProviderServiceMapID;
	}

	public void setProviderServiceMapID(Integer providerServiceMapID) {
		this.ProviderServiceMapID = providerServiceMapID;
	}

	public Integer getServiceID() {
		return serviceID;
	}

	public void setServiceID(Integer serviceID) {
		this.serviceID = serviceID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getsMSPhoneNo() {
		return sMSPhoneNo;
	}

	public void setsMSPhoneNo(String sMSPhoneNo) {
		this.sMSPhoneNo = sMSPhoneNo;
	}

	public Date getServiceAvailDate() {
		return serviceAvailDate;
	}

	public void setServiceAvailDate(Date serviceAvailDate) {
		this.serviceAvailDate = serviceAvailDate;
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
		return CreatedDate;
	}

	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
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

	public Long getBenCallID() {
		return benCallID;
	}

	public Integer get1097ServiceID() {
		return serviceID1097;
	}

	public void setBenCallID(Long benCallID) {
		this.benCallID = benCallID;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "feedback")
	private List<FeedbackRequestDetails> feedbackRequestDetails;

	/**
	 * @return the feedbackRquestDetails
	 */
	public List<FeedbackRequestDetails> getFeedbackRequestDetails() {
		return feedbackRequestDetails;
	}

	/**
	 * @param feedbackRquestDetails the feedbackRquestDetails to set
	 */
	public void setFeedbackRequestDetails(List<FeedbackRequestDetails> feedbackRequestDetails) {
		this.feedbackRequestDetails = feedbackRequestDetails;
	}

	public String getInstiName() {
		return instiName;
	}

	public void setInstiName(String instiName) {
		this.instiName = instiName;
	}

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}
}
