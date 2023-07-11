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
package com.iemr.helpline104.data.bloodComponentType;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;

@Entity
@Table(name="M_ComponentType")
public class M_ComponentType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer componentTypeID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "componentTypeID")
	private T_BloodRequest T_BloodRequest;
	
	private String componentType;
	private String componentTypeDesc;
	private Boolean deleted;
	private String createdBy;
	private String modifiedBy;
	
	public M_ComponentType(Integer componentTypeID, String componentType, String componentTypeDesc) {
		super();
		this.componentTypeID = componentTypeID;
		this.componentType = componentType;
		this.componentTypeDesc = componentTypeDesc;
	}

	public M_ComponentType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getComponentTypeID() {
		return componentTypeID;
	}

	public void setComponentTypeID(Integer componentTypeID) {
		this.componentTypeID = componentTypeID;
	}

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getComponentTypeDesc() {
		return componentTypeDesc;
	}

	public void setComponentTypeDesc(String componentTypeDesc) {
		this.componentTypeDesc = componentTypeDesc;
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
	
	public String toString() {
		return new Gson().toJson(this);
	}

	
}
