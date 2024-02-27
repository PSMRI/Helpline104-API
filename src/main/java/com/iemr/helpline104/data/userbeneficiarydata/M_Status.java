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
package com.iemr.helpline104.data.userbeneficiarydata;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.iemr.helpline104.data.users.M_User;

@Entity
@Table(name = "m_status")
public class M_Status {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer StatusID;
	
	@OneToMany(mappedBy = "m_status", fetch = FetchType.LAZY)
	private Set<M_User> m_user;
	/*
	@OneToMany(mappedBy = "m_status", fetch = FetchType.LAZY)
	private Set<I_Beneficiary> i_beneficiary; */
	
	private String Status;
	private String StatusDesc;
	private Boolean Deleted;
	private String CreatedBy;
	private Timestamp CreatedDate;
	private String ModifiedBy;
	private Timestamp LastModDate;

	protected M_Status() {
	}

	public M_Status(int statusID, String status) {
		this.StatusID = Integer.valueOf(statusID);
		this.Status = status;
	}

	public M_Status(int statusID, String status, String statusDesc, boolean deleted, String createdBy,
			Timestamp createdDate, String modifiedBy, Timestamp lastModDate, Set<M_User> m_User) {
		this.StatusID = Integer.valueOf(statusID);
		this.Status = status;
		this.StatusDesc = statusDesc;
		this.Deleted = Boolean.valueOf(deleted);
		this.CreatedBy = createdBy;
		this.CreatedDate = createdDate;
		this.ModifiedBy = modifiedBy;
		this.LastModDate = lastModDate;
		this.m_user = m_User;
	}

	public int getStatusID() {
		return this.StatusID.intValue();
	}

	public void setStatusID(int statusID) {
		this.StatusID = Integer.valueOf(statusID);
	}

	public String getStatus() {
		return this.Status;
	}

	public void setStatus(String status) {
		this.Status = status;
	}

	public String getStatusDesc() {
		return this.StatusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.StatusDesc = statusDesc;
	}

	public boolean isDeleted() {
		return this.Deleted.booleanValue();
	}

	public void setDeleted(boolean deleted) {
		this.Deleted = Boolean.valueOf(deleted);
	}

	public String getCreatedBy() {
		return this.CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		this.CreatedBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.CreatedDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.CreatedDate = createdDate;
	}

	public String getModifiedBy() {
		return this.ModifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.ModifiedBy = modifiedBy;
	}

	public Timestamp getLastModDate() {
		return this.LastModDate;
	}

	public void setLastModDate(Timestamp lastModDate) {
		this.LastModDate = lastModDate;
	}

	public Set<M_User> getM_User() {
		return this.m_user;
	}

	public void setM_User(Set<M_User> m_User) {
		this.m_user = m_User;
	}
}
