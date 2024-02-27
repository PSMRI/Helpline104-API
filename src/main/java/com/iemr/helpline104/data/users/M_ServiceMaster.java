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

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "m_servicemaster")
public class M_ServiceMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ServiceID;
	private String ServiceName;
	private String ServiceDesc;
	private Boolean Deleted;
	private String CreatedBy;
	private Timestamp CreatedDate;
	private String ModifiedBy;
	private Timestamp LastModDate;
	@OneToMany(mappedBy = "m_ServiceMaster", cascade = { jakarta.persistence.CascadeType.ALL })
	private Set<M_UserServiceRoleMapping> m_UserServiceRoleMapping;

	protected M_ServiceMaster() {
	}

	public M_ServiceMaster(Integer serviceID, String serviceName, String serviceDesc, Boolean deleted, String createdBy,
			Timestamp createdDate, String modifiedBy, Timestamp lastModDate,
			Set<M_UserServiceRoleMapping> m_UserServiceRoleMapping) {
		this.ServiceID = serviceID;
		this.ServiceName = serviceName;
		this.ServiceDesc = serviceDesc;
		this.Deleted = deleted;
		this.CreatedBy = createdBy;
		this.CreatedDate = createdDate;
		this.ModifiedBy = modifiedBy;
		this.LastModDate = lastModDate;
		this.m_UserServiceRoleMapping = m_UserServiceRoleMapping;
	}

	public Integer getServiceID() {
		return this.ServiceID;
	}

	public void setServiceID(int serviceID) {
		this.ServiceID = Integer.valueOf(serviceID);
	}

	public String getServiceName() {
		return this.ServiceName;
	}

	public void setServiceName(String serviceName) {
		this.ServiceName = serviceName;
	}

	public String getServiceDesc() {
		return this.ServiceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.ServiceDesc = serviceDesc;
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
}
