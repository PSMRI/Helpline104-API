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
package com.iemr.helpline104.data.feedbackType;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.iemr.helpline104.data.feedbackNature.M_FeedbackNature;
import com.iemr.helpline104.data.location.CityDetails;

@Entity
@Table(name="M_FeedbackType")
public class M_FeedbackType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short feedbackTypeID;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "feedbackTypeID")
	private Set<M_FeedbackNature> m_feedbackNature;
	
	private String feedbackTypeName;
	private String feedbackDesc;
	private Integer providerServiceMapID;
	private String feedbackTypeCode;
	private Boolean deleted;
	private String createdBy;
	private String modifiedBy;
	
	
	public M_FeedbackType(Short feedbackTypeID, String feedbackTypeName, String feedbackDesc,
			Integer providerServiceMapID,Integer feedbackNatureID, String feedbackNature, String feedbackNatureDesc) {
		super();
		this.feedbackTypeID = feedbackTypeID;
		this.feedbackTypeName = feedbackTypeName;
		this.feedbackDesc = feedbackDesc;
		this.providerServiceMapID = providerServiceMapID;
		Set feedbackNatures=new HashSet<M_FeedbackNature>();
		feedbackNatures.add(new M_FeedbackNature(feedbackNatureID, feedbackNature, feedbackNatureDesc));
		
		this.m_feedbackNature = feedbackNatures;
	}
	
	public M_FeedbackType(Short feedbackTypeID, String feedbackTypeName) {
		super();
		this.feedbackTypeID = feedbackTypeID;
		this.feedbackTypeName = feedbackTypeName;
	}

	public M_FeedbackType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFeedbackTypeName() {
		return feedbackTypeName;
	}


	public void setFeedbackTypeName(String feedbackTypeName) {
		this.feedbackTypeName = feedbackTypeName;
	}


	public String getFeedbackDesc() {
		return feedbackDesc;
	}


	public void setFeedbackDesc(String feedbackDesc) {
		this.feedbackDesc = feedbackDesc;
	}


	public Integer getProviderServiceMapID() {
		return providerServiceMapID;
	}


	public void setProviderServiceMapID(Integer providerServiceMapID) {
		this.providerServiceMapID = providerServiceMapID;
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


	public Short getFeedbackTypeID() {
		return feedbackTypeID;
	}


	public String toString() {
		return new Gson().toJson(this);
	}
	
}
