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
package com.iemr.helpline104.data.organDonation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.iemr.helpline104.utils.mapper.OutputMapper;

@Entity
@Table(name="M_DonatableOrgan")
public class M_DonatableOrgan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer donatableOrganID;
	@Expose
	private String donatableOrgan;
	@Expose
	private String donatableOrganDesc;
	@Expose
	private Boolean deleted;
	@Expose
	private String createdBy;
	@Expose
	private String modifiedBy;
	
	public M_DonatableOrgan(Integer donatableOrganID, String donatableOrgan, String donatableOrganDesc) {
		super();
		this.donatableOrganID = donatableOrganID;
		this.donatableOrgan = donatableOrgan;
		this.donatableOrganDesc = donatableOrganDesc;
	}

	public M_DonatableOrgan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDonatableOrgan() {
		return donatableOrgan;
	}

	public void setDonatableOrgan(String donatableOrgan) {
		this.donatableOrgan = donatableOrgan;
	}

	public String getDonatableOrganDesc() {
		return donatableOrganDesc;
	}

	public void setDonatableOrganDesc(String donatableOrganDesc) {
		this.donatableOrganDesc = donatableOrganDesc;
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

	public Integer getDonatableOrganID() {
		return donatableOrganID;
	}
	
	/*
	public String toString() {
		return new Gson().toJson(this);
	} */
	
	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString()
	{
		return outputMapper.gson().toJson(this);
	}
}
