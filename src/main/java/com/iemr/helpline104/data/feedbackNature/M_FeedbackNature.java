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
package com.iemr.helpline104.data.feedbackNature;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "M_FeedbackNature")
public class M_FeedbackNature {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer feedbackNatureID;
	@Expose
	private String feedbackNature;
	@Expose
	private String feedbackNatureDesc;
	@Expose
	private Short feedbackTypeID;
	private Boolean deleted;
	private String modifiedBy;
	private String createdBy;

	public M_FeedbackNature(Integer feedbackNatureID, String feedbackNature, String feedbackNatureDesc) {
		super();
		this.feedbackNatureID = feedbackNatureID;
		this.feedbackNature = feedbackNature;
		this.feedbackNatureDesc = feedbackNatureDesc;
	}

	public M_FeedbackNature() {
		super();
	}

	public String getFeedbackNature() {
		return feedbackNature;
	}

	public void setFeedbackNature(String feedbackNature) {
		this.feedbackNature = feedbackNature;
	}

	public String getFeedbackNatureDesc() {
		return feedbackNatureDesc;
	}

	public void setFeedbackNatureDesc(String feedbackNatureDesc) {
		this.feedbackNatureDesc = feedbackNatureDesc;
	}

	public Short getFeedbackTypeID() {
		return feedbackTypeID;
	}

	public void setFeedbackTypeID(Short feedbackTypeID) {
		this.feedbackTypeID = feedbackTypeID;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getFeedbackNatureID() {
		return feedbackNatureID;
	}

	public String toString() {
		return new Gson().toJson(this);
	}
}
