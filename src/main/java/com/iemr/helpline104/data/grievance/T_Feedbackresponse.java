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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_feedbackresponse")
public class T_Feedbackresponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FeedbackResponseID")
	private Integer feedbackResponseID;
	@Column(name = "FeedbackRequestID")
	private Integer feedbackRequestID;
	@Column(name = "ResponseSummary")
	private String responseSummary;
	@Column(name = "AuthUserID")
	private int authUserID;
	@Column(name = "Comments")
	private String comments;
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;
	@Column(name = "CreatedBy")
	private String createdBy;
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "LastModDate")
	private Date lastModDate;
	@Column(name = "FeedbackID")
	private int feedbackID;
	@Column(name = "AuthName")
	private String authName;
	@Column(name = "AuthDesignation")
	private String authDesignation;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FeedbackRequestID", insertable = false, updatable = false)
	private T_FeedbackRequset tfeedbackr1;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FeedbackID", insertable = false, updatable = false)
	private T_Feedback tfeedback;

	public T_Feedbackresponse() {
	}

	public Integer getFeedbackResponseID() {
		return feedbackResponseID;
	}

	public void setFeedbackResponseID(Integer feedbackResponseID) {
		this.feedbackResponseID = feedbackResponseID;
	}

	public Integer getFeedbackRequestID() {
		return feedbackRequestID;
	}

	public void setFeedbackRequestID(Integer feedbackRequestID) {
		this.feedbackRequestID = feedbackRequestID;
	}

	public String getResponseSummary() {
		return responseSummary;
	}

	public void setResponseSummary(String responseSummary) {
		this.responseSummary = responseSummary;
	}

	public int getAuthUserID() {
		return authUserID;
	}

	public void setAuthUserID(int authUserID) {
		this.authUserID = authUserID;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public int getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthDesignation() {
		return authDesignation;
	}

	public void setAuthDesignation(String authDesignation) {
		this.authDesignation = authDesignation;
	}

	public T_FeedbackRequset getTfeedbackr1() {
		return tfeedbackr1;
	}

	public void setTfeedbackr1(T_FeedbackRequset tfeedbackr1) {
		this.tfeedbackr1 = tfeedbackr1;
	}

	public T_Feedback getTfeedback() {
		return tfeedback;
	}

	public void setTfeedback(T_Feedback tfeedback) {
		this.tfeedback = tfeedback;
	}

}