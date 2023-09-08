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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_feedbackrequest")
public class T_FeedbackRequset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FeedbackRequestID")
	private int feedbackRequestID;
	@Column(name = "FeedbackID")
	private Integer feedbackID;
	@Column(name = "FeedbackSupSummary")
	private String feedbackSupSummary;
	@Column(name = "SupUserID")
	private int supUserID;
	@Column(name = "Comments")
	private String comments;
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;
	@Column(name = "CreatedBy")
	private String createdBy;
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Timestamp createdDate;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "LastModDate")
	private Date lastModDate;
	@Column(name = "EmailStatusID")
	private int emailStatusID;

	@OneToOne(mappedBy = "tfeedbackr1", cascade = CascadeType.ALL)
	private T_Feedbackresponse tfeedbackRes;

	public int getFeedbackRequestID() {
		return feedbackRequestID;
	}

	public void setFeedbackRequestID(int feedbackRequestID) {
		this.feedbackRequestID = feedbackRequestID;
	}

	public Integer getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(Integer feedbackID) {
		this.feedbackID = feedbackID;
	}

	public String getFeedbackSupSummary() {
		return feedbackSupSummary;
	}

	public void setFeedbackSupSummary(String feedbackSupSummary) {
		this.feedbackSupSummary = feedbackSupSummary;
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

	public T_Feedbackresponse getTfeedbackRes() {
		return tfeedbackRes;
	}

	public void setTfeedbackRes(T_Feedbackresponse tfeedbackRes) {
		this.tfeedbackRes = tfeedbackRes;
	}

}