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
package com.iemr.helpline104.data.location;

import java.io.Serializable;
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

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "m_district")
public class Districts implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer districtID;
	@Expose
	private Integer stateID;
	@Expose
	private String districtName;
	private Integer GovtDistrictID;
	private Integer GovtStateID;
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;
	@Column(name = "CreatedBy")
	private String createdBy;
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Date lastModDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(unique = true, insertable = false, name = "stateID", updatable = false)
	private States states;

	public Districts()
	{
	}

	public Districts(Integer DistrictID, String DistrictName)
	{
		this.districtID = DistrictID;
		this.districtName = DistrictName;
	}

	public Districts(Integer DistrictID, String DistrictName, Integer stateId, String stateName)
	{
		this.states = new States(stateId.intValue(), stateName);
		this.districtID = DistrictID;
		this.districtName = DistrictName;
	}

	public int getDistrictID()
	{
		return this.districtID.intValue();
	}

	public void setDistrictID(int districtID)
	{
		this.districtID = Integer.valueOf(districtID);
	}

	public String getDistrictName()
	{
		return this.districtName;
	}

	public void setDistrictName(String districtName)
	{
		this.districtName = districtName;
	}

	public boolean isDeleted()
	{
		return this.deleted.booleanValue();
	}

	public void setDeleted(boolean deleted)
	{
		this.deleted = Boolean.valueOf(deleted);
	}

	public String getCreatedBy()
	{
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public Date getCreatedDate()
	{
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public String getModifiedBy()
	{
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	public Date getLastModDate()
	{
		return this.lastModDate;
	}

	public void setLastModDate(Date lastModDate)
	{
		this.lastModDate = lastModDate;
	}

	public States getStates()
	{
		return this.states;
	}

	public void setStates(States states)
	{
		this.states = states;
	}

	public Integer getGovtDistrictID()
	{
		return GovtDistrictID;
	}

	public void setGovtDistrictID(Integer govtDistrictID)
	{
		GovtDistrictID = govtDistrictID;
	}

	public Integer getGovtStateID()
	{
		return GovtStateID;
	}

	public void setGovtStateID(Integer govtStateID)
	{
		GovtStateID = govtStateID;
	}

	public String toString()
	{
		return new Gson().toJson(this);
	}
}
