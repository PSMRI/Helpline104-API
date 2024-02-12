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
package com.iemr.helpline104.data.users;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name = "m_userservicerolemapping")
public class M_UserServiceRoleMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer USRMappingID;

	private Integer UserID;
	private Integer RoleID;
	private Integer ServiceID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserID", updatable = false, insertable = false)
	private M_User m_user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RoleID", updatable = false, insertable = false)
	private M_Role m_Role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ServiceID", updatable = false, insertable = false)
	private M_ServiceMaster m_ServiceMaster;
	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "ServiceID")
	// private M_ServiceMaster m_ServiceMaster;

	private Integer StateID;
	private Boolean Deleted;
	private String CreatedBy;
	private Timestamp CreatedDate;
	private String ModifiedBy;
	private Timestamp LastModDate;

	protected M_UserServiceRoleMapping() {
	}

	public M_UserServiceRoleMapping(Integer uSRMappingID) {
		this.USRMappingID = uSRMappingID;
	}

	public M_UserServiceRoleMapping(Integer uSRMappingID, M_User m_user, M_Role m_Role, M_ServiceMaster m_ServiceMaster,
			Integer stateID, Boolean deleted, String createdBy, Timestamp createdDate, String modifiedBy,
			Timestamp lastModDate) {
		this.USRMappingID = uSRMappingID;
		this.m_user = m_user;
		this.m_Role = m_Role;
		this.m_ServiceMaster = m_ServiceMaster;
		this.StateID = stateID;
		this.Deleted = deleted;
		this.CreatedBy = createdBy;
		this.CreatedDate = createdDate;
		this.ModifiedBy = modifiedBy;
		this.LastModDate = lastModDate;
	}

	public Integer getUSRMappingID() {
		return this.USRMappingID;
	}

	public void setUSRMappingID(int uSRMappingID) {
		this.USRMappingID = Integer.valueOf(uSRMappingID);
	}

	public M_User getM_user() {
		return this.m_user;
	}

	public void setM_user(M_User m_user) {
		this.m_user = m_user;
	}

	public M_Role getM_Role() {
		return this.m_Role;
	}

	public void setM_Role(M_Role m_Role) {
		this.m_Role = m_Role;
	}

	public M_ServiceMaster getM_ServiceMaster() {
		return this.m_ServiceMaster;
	}

	public void setM_ServiceMaster(M_ServiceMaster m_ServiceMaster) {
		this.m_ServiceMaster = m_ServiceMaster;
	}

	public Integer getStateID() {
		return StateID;
	}

	public void setStateID(Integer stateID) {
		StateID = stateID;
	}

	public Boolean isDeleted() {
		return Deleted;
	}

	public void setDeleted(Boolean deleted) {
		Deleted = deleted;
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

	public Integer getUserID() {
		return UserID;
	}

	public void setUserID(Integer userID) {
		UserID = userID;
	}

	public Integer getRoleID() {
		return RoleID;
	}

	public void setRoleID(Integer roleID) {
		RoleID = roleID;
	}

	public Integer getServiceID() {
		return ServiceID;
	}

	public void setServiceID(Integer serviceID) {
		ServiceID = serviceID;
	}

	public Boolean getDeleted() {
		return Deleted;
	}

	public void setUSRMappingID(Integer uSRMappingID) {
		USRMappingID = uSRMappingID;
	}

	public String toString() {
		return new Gson().toJson(this);
	}

}
