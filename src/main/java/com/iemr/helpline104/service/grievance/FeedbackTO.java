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
package com.iemr.helpline104.service.grievance;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import com.iemr.helpline104.data.feedbackType.M_FeedbackType;

public class FeedbackTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int feedbackID; 
		private int institutionID;
		private int designationID; 
		private int severityID;
		private int feedbackTypeID; 
		private int feedbackStatusID; 
		private String feedback; 
		private int beneficiaryRegID; 
		private int serviceID; 
		private int userID;
		private String sMSPhoneNo; 
		private Date serviceAvailDate; 
		private Boolean deleted; 
		private String createdBy; 
		private Timestamp createdDate; 
		private String modifiedBy;
		private Date lastModDate;
		private int emailStatusID;
		private Timestamp startDate;
		private Timestamp endDate;
		private int selectStatus;
		private String emailID ;
		private String updateResponse;
		private int supUserID;
		private String comments;
		/*
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="BeneficiaryRegID", insertable = false, updatable = false)
		private I_Beneficiary beneficiary; */
		
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="FeedbackTypeID", insertable = false, updatable = false)
		private M_FeedbackType mfeedbackType;
		
	public FeedbackTO() {
		// TODO Auto-generated constructor stub
	}

	public int getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}

	public int getInstitutionID() {
		return institutionID;
	}

	public void setInstitutionID(int institutionID) {
		this.institutionID = institutionID;
	}

	public int getDesignationID() {
		return designationID;
	}

	public void setDesignationID(int designationID) {
		this.designationID = designationID;
	}

	public int getSeverityID() {
		return severityID;
	}

	public void setSeverityID(int severityID) {
		this.severityID = severityID;
	}

	public int getFeedbackTypeID() {
		return feedbackTypeID;
	}

	public void setFeedbackTypeID(int feedbackTypeID) {
		this.feedbackTypeID = feedbackTypeID;
	}

	public int getFeedbackStatusID() {
		return feedbackStatusID;
	}

	public void setFeedbackStatusID(int feedbackStatusID) {
		this.feedbackStatusID = feedbackStatusID;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public int getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	public void setBeneficiaryRegID(int beneficiaryRegID) {
		this.beneficiaryRegID = beneficiaryRegID;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
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

	public int getEmailStatusID() {
		return emailStatusID;
	}

	public void setEmailStatusID(int emailStatusID) {
		this.emailStatusID = emailStatusID;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public int getSelectStatus() {
		return selectStatus;
	}

	public void setSelectStatus(int selectStatus) {
		this.selectStatus = selectStatus;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getUpdateResponse() {
		return updateResponse;
	}

	public void setUpdateResponse(String updateResponse) {
		this.updateResponse = updateResponse;
	}

	public int getSupUserID() {
		return supUserID;
	}

	public void setSupUserID(int supUserID) {
		this.supUserID = supUserID;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	public M_FeedbackType getMfeedbackType() {
		return mfeedbackType;
	}

	public void setMfeedbackType(M_FeedbackType mfeedbackType) {
		this.mfeedbackType = mfeedbackType;
	}

		
			
		
		
		
		}
		
		
		
		
		



