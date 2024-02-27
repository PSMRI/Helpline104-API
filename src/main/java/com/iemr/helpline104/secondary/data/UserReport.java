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
package com.iemr.helpline104.secondary.data;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "db_reporting.dim_user", schema = "db_reporting")
public class UserReport implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "Dim_USERID", insertable = false, updatable = false)
	private Long dim_USERID;
	
	@Expose
	@Column(name = "UserID")
	private Long userID;
	
	@Expose
	@Column(name = "EmployeeID")
	private String empID;
	
	@Expose
	@Column(name="FirstName")
	private String firstName;
	
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "UserID", referencedColumnName = "UserID", updatable = false, insertable = false)
//	@Expose
//	private UserServiceRoleReport userServiceRoleReport;

	/**
	 * @return the dim_USERID
	 */
	public Long getDim_USERID() {
		return dim_USERID;
	}

	/**
	 * @param dim_USERID the dim_USERID to set
	 */
	public void setDim_USERID(Long dim_USERID) {
		this.dim_USERID = dim_USERID;
	}

	/**
	 * @return the userID
	 */
	public Long getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(Long userID) {
		this.userID = userID;
	}

	/**
	 * @return the empID
	 */
	public String getEmpID() {
		return empID;
	}

	/**
	 * @param empID the empID to set
	 */
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	
}

