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
package com.iemr.helpline104.data.grievance;

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

import com.google.gson.annotations.Expose;
import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.data.users.M_User;

@Entity
@Table(name="t_Feedback")
public class T_Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="FeedbackID")
		private int feedbackID;	
	   @Column(name = "BenCallID")
	   private Long benCallID;
	   @Column(name="InstitutionID")
		private int institutionID;
	   @Column(name="DesignationID")
		private int designationID;
	   @Column(name="SeverityID")
		private int severityID;
	   @Column(name="FeedbackTypeID")
		private int feedbackTypeID; 
	   @Column(name="FeedbackStatusID")
		private int feedbackStatusID;
	   @Column(name="Feedback")
		private String feedback; 
	   @Column(name="BeneficiaryRegID")
		private int beneficiaryRegID;
	   @Column(name="ServiceID")
		private int serviceID;
	   @Column(name="UserID")
		private int userID;
	   @Column(name="SMSPhoneNo")
		private String sMSPhoneNo;
	   @Column(name="ServiceAvailDate")
		private Date serviceAvailDate;
	   @Column(name="Deleted", insertable=false, updatable=true)
		private Boolean deleted; 
	   @Column(name="CreatedBy")
		private String createdBy; 
	   @Column(name="CreatedDate", insertable=false, updatable=false)
		private Timestamp createdDate;
	   @Column(name="ModifiedBy")
		private String modifiedBy;
	   @Column(name="LastModDate")
		private Date lastModDate;
	   @Column(name="EmailStatusID")
		private int emailStatusID;		
		
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="FeedbackTypeID", insertable = false, updatable = false)
		private M_FeedbackType mfeedbackType;
		
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="InstitutionID", insertable = false, updatable = false)
		private M_Institution minstitution;
		
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="DesignationID", insertable = false, updatable = false)
		private M_Designation mdesignation;
		
		
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="SeverityID", insertable = false, updatable = false)
		private M_Severity mseverity;
		
		
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="FeedbackStatusID", insertable = false, updatable = false)
		private M_Feedbackstatus mfeedbackstatus;
		
		
		
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="ServiceID", insertable = false, updatable = false)
		private M_Servicemaster mservicemaster;
		
		
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="UserID", insertable = false, updatable = false)
		private M_User mUser;
		
		
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="EmailStatusID", insertable = false, updatable = false)
		private M_Emailstatus memailstatus;
		
		
		/*@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="FeedbackID", insertable = false, updatable = false)
		private T_FeedbackRequset tFeedbackrequest;
		*/
		
		@OneToOne(mappedBy="tfeedback")
		private T_Feedbackresponse tfeedbackres;
		
		
		

		public T_Feedback() {
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

		/*
		public I_Beneficiary getBeneficiary() {
			return beneficiary;
		}

		public void setBeneficiary(I_Beneficiary beneficiary) {
			this.beneficiary = beneficiary;
		}*/


		public M_FeedbackType getMfeedbackType() {
			return mfeedbackType;
		}


		public void setMfeedbackType(M_FeedbackType mfeedbackType) {
			this.mfeedbackType = mfeedbackType;
		}




		public M_Institution getMinstitution() {
			return minstitution;
		}




		public void setMinstitution(M_Institution minstitution) {
			this.minstitution = minstitution;
		}




		public M_Designation getMdesignation() {
			return mdesignation;
		}




		public void setMdesignation(M_Designation mdesignation) {
			this.mdesignation = mdesignation;
		}




		public M_Severity getMseverity() {
			return mseverity;
		}




		public void setMseverity(M_Severity mseverity) {
			this.mseverity = mseverity;
		}




		public M_Feedbackstatus getMfeedbackstatus() {
			return mfeedbackstatus;
		}




		public void setMfeedbackstatus(M_Feedbackstatus mfeedbackstatus) {
			this.mfeedbackstatus = mfeedbackstatus;
		}




		public M_Servicemaster getMservicemaster() {
			return mservicemaster;
		}




		public void setMservicemaster(M_Servicemaster mservicemaster) {
			this.mservicemaster = mservicemaster;
		}




		public M_User getmUser() {
			return mUser;
		}




		public void setmUser(M_User mUser) {
			this.mUser = mUser;
		}




		public M_Emailstatus getMemailstatus() {
			return memailstatus;
		}




		public void setMemailstatus(M_Emailstatus memailstatus) {
			this.memailstatus = memailstatus;
		}




		public T_Feedbackresponse getTfeedbackres() {
			return tfeedbackres;
		}




		public void setTfeedbackres(T_Feedbackresponse tfeedbackres) {
			this.tfeedbackres = tfeedbackres;
		}

		
		
		
		
			
		
		
		
		}
		
		
		
		
		
