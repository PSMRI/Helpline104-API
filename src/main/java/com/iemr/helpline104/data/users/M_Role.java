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
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.helpline104.utils.mapper.OutputMapper;

@Entity
@Table(name = "m_Role")
public class M_Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer RoleID;
	@Expose
	private String RoleName;
	@Expose
	private String RoleDesc;
	@Expose
	private Boolean Deleted;
	private String CreatedBy;
	private Timestamp CreatedDate;
	private String ModifiedBy;
	private Timestamp LastModDate;
	@Expose
	@Column(name = "isWrapUpTime")
	private Boolean isWrapUpTime;

	@Expose
	@Column(name = "WrapUpTime")
	private Integer WrapUpTime;
	@OneToMany(mappedBy = "m_Role", cascade = { javax.persistence.CascadeType.ALL })
	private Set<M_UserServiceRoleMapping> m_UserServiceRoleMapping;

	protected M_Role() {
	}

	public M_Role(Integer roleID, String roleName, String roleDesc, Boolean deleted, String createdBy,
			Timestamp createdDate, String modifiedBy, Timestamp lastModDate,
			Set<M_UserServiceRoleMapping> m_UserServiceRoleMapping) {
		this.RoleID = roleID;
		this.RoleName = roleName;
		this.RoleDesc = roleDesc;
		this.Deleted = deleted;
		this.CreatedBy = createdBy;
		this.CreatedDate = createdDate;
		this.ModifiedBy = modifiedBy;
		this.LastModDate = lastModDate;
		this.m_UserServiceRoleMapping = m_UserServiceRoleMapping;
	}

	public Integer getRoleID() {
		return this.RoleID;
	}

	public void setRoleID(int roleID) {
		this.RoleID = Integer.valueOf(roleID);
	}

	public String getRoleName() {
		return this.RoleName;
	}

	public void setRoleName(String roleName) {
		this.RoleName = roleName;
	}

	public String getRoleDesc() {
		return this.RoleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.RoleDesc = roleDesc;
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

	public Set<M_UserServiceRoleMapping> getM_UserServiceRoleMapping() {
		return this.m_UserServiceRoleMapping;
	}

	public void setM_UserServiceRoleMapping(Set<M_UserServiceRoleMapping> m_UserServiceRoleMapping) {
		this.m_UserServiceRoleMapping = m_UserServiceRoleMapping;
	}

	public Boolean getIsWrapUpTime() {
		return isWrapUpTime;
	}

	public void setIsWrapUpTime(Boolean isWrapUpTime) {
		this.isWrapUpTime = isWrapUpTime;
	}

	public Integer getWrapUpTime() {
		return WrapUpTime;
	}

	public void setWrapUpTime(Integer wrapUpTime) {
		WrapUpTime = wrapUpTime;
	}

	public Boolean getDeleted() {
		return Deleted;
	}

	public void setDeleted(Boolean deleted) {
		Deleted = deleted;
	}

	public void setRoleID(Integer roleID) {
		RoleID = roleID;
	}
	
	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}

}
