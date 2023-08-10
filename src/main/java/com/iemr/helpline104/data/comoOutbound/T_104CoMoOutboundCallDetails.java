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
package com.iemr.helpline104.data.comoOutbound;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name="t_104CoMoOutboundCallDetails")
public class T_104CoMoOutboundCallDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iD;
	private Integer uSRMappingID;
	private String callId;
	private String callType;
	private String callSubType;
	private Boolean is_Visited;
	private String remarks;
	private String feedback;
	private Boolean deleted;
	private String createdBy;
	@Column(name = "CreatedDate", insertable=false, updatable=false)
	private Date createdDate;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "LastModDate", insertable=false, updatable=false)
	private Date lastModDate;
	
	public T_104CoMoOutboundCallDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public T_104CoMoOutboundCallDetails(Long iD, Integer uSRMappingID, String callId, String callType,
			String callSubType, Boolean is_Visited, String remarks, String feedback) {
		super();
		this.iD = iD;
		this.uSRMappingID = uSRMappingID;
		this.callId = callId;
		this.callType = callType;
		this.callSubType = callSubType;
		this.is_Visited = is_Visited;
		this.remarks = remarks;
		this.feedback = feedback;
	}

	public Integer getuSRMappingID() {
		return uSRMappingID;
	}

	public void setuSRMappingID(Integer uSRMappingID) {
		this.uSRMappingID = uSRMappingID;
	}

	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getCallSubType() {
		return callSubType;
	}

	public void setCallSubType(String callSubType) {
		this.callSubType = callSubType;
	}

	public Boolean getIs_Visited() {
		return is_Visited;
	}

	public void setIs_Visited(Boolean is_Visited) {
		this.is_Visited = is_Visited;
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

	public Long getiD() {
		return iD;
	}
	
	public String toString() {
		return new Gson().toJson(this);
	}
}
