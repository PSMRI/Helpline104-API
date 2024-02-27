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

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.iemr.helpline104.utils.mapper.OutputMapper;

@Entity
@Table(name = "t_FeedbackRequest")
public class FeedbackRequestDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Long feedbackRequestID;
	@Expose
	private Long feedbackID;
	@Expose
	private String feedbackSupSummary;
	@Expose
	private Integer supUserID;
	@Expose
	private String comments;
	@Expose
	private Boolean deleted;
	@Expose
	private String createdBy;
	private String modifiedBy;
	private Date lastModDate;

	@OneToOne
	@JoinColumn(referencedColumnName = "FeedbackID")
	@JsonIgnore
	private FeedbackDetails feedback;

	/**
	 * @return the feedbackRequestID
	 */
	public Long getFeedbackRequestID() {
		return feedbackRequestID;
	}

	/**
	 * @param feedbackRequestID the feedbackRequestID to set
	 */
	public void setFeedbackRequestID(Long feedbackRequestID) {
		this.feedbackRequestID = feedbackRequestID;
	}

	/**
	 * @return the feedbackID
	 */
	public Long getFeedbackID() {
		return feedbackID;
	}

	/**
	 * @param feedbackID the feedbackID to set
	 */
	public void setFeedbackID(Long feedbackID) {
		this.feedbackID = feedbackID;
	}

	/**
	 * @return the feedbackSupSummary
	 */
	public String getFeedbackSupSummary() {
		return feedbackSupSummary;
	}

	/**
	 * @param feedbackSupSummary the feedbackSupSummary to set
	 */
	public void setFeedbackSupSummary(String feedbackSupSummary) {
		this.feedbackSupSummary = feedbackSupSummary;
	}

	/**
	 * @return the supUserID
	 */
	public Integer getSupUserID() {
		return supUserID;
	}

	/**
	 * @param supUserID the supUserID to set
	 */
	public void setSupUserID(Integer supUserID) {
		this.supUserID = supUserID;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the lastModDate
	 */
	public Date getLastModDate() {
		return lastModDate;
	}

	/**
	 * @param lastModDate the lastModDate to set
	 */
	public void setLastModDate(Date lastModDate) {
		this.lastModDate = lastModDate;
	}

	/**
	 * @return the feedback
	 */
	public FeedbackDetails getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback the feedback to set
	 */
	public void setFeedback(FeedbackDetails feedback) {
		this.feedback = feedback;
	}

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}
}
