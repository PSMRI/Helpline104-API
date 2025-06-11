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

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iemr.helpline104.data.userbeneficiarydata.M_Gender;
import com.iemr.helpline104.data.userbeneficiarydata.M_MaritalStatus;
import com.iemr.helpline104.data.userbeneficiarydata.M_Status;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class M_User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UserID;
	private Integer TitleID;
	private String firstName;
	private String middleName;
	private String lastName;
	private Integer GenderID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GenderID", insertable = false, updatable = false)
	private M_Gender m_gender;

	private Integer MaritalStatusID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaritalStatusID", insertable = false, updatable = false)
	private M_MaritalStatus m_maritalstatus;

	private Integer StatusID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StatusID", insertable = false, updatable = false)
	private M_Status m_status;

	private String AadhaarNo;
	private String PAN;
	private Timestamp DOB;
	private Timestamp DOJ;
	private Integer QualificationID;
	private String UserName;
	private String Password;
	private String EmailID;

	// private Integer ReligionID;
	// @OneToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "ReligionID", insertable = false, updatable = false)
	// private M_Religion m_relegion;
   
	@OneToMany(mappedBy = "m_user", fetch = FetchType.LAZY)
	@OrderBy("USRMappingID ASC") 
	private Set<M_UserServiceRoleMapping> m_UserServiceRoleMapping;

	private String EmergencyContactPerson;
	private String EmergencyContactNo;
	private Boolean IsSupervisor;
	private Boolean Deleted;
	private String CreatedBy;
	private Timestamp CreatedDate;
	private String ModifiedBy;
	private Timestamp LastModDate;
	@Transient
	private String newPassword = null;

	public M_User() {
	}

	public M_User(Long userID, Integer titleID, String firstName, String middleName, String lastName, Integer genderID,
			Integer maritalStatusID, String aadhaarNo, String pAN, Timestamp dOB, Timestamp dOJ,
			Integer qualificationID, String userName, String password, String emailID, M_Status m_Status,
			Set<M_UserServiceRoleMapping> m_UserServiceRoleMapping, String emergencyContactPerson,
			String emergencyContactNo, Boolean isSupervisor, Boolean deleted, String createdBy, Timestamp createdDate,
			String modifiedBy, Timestamp lastModDate, String newPassword) {
		this.UserID = userID;
		this.TitleID = titleID;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.GenderID = genderID;
		this.MaritalStatusID = maritalStatusID;
		this.AadhaarNo = aadhaarNo;
		this.PAN = pAN;
		this.DOB = dOB;
		this.DOJ = dOJ;
		this.QualificationID = qualificationID;
		this.UserName = userName;
		this.Password = password;
		this.EmailID = emailID;
		this.m_UserServiceRoleMapping = m_UserServiceRoleMapping;
		this.EmergencyContactPerson = emergencyContactPerson;
		this.EmergencyContactNo = emergencyContactNo;
		this.IsSupervisor = isSupervisor;
		this.Deleted = deleted;
		this.CreatedBy = createdBy;
		this.CreatedDate = createdDate;
		this.ModifiedBy = modifiedBy;
		this.LastModDate = lastModDate;
		this.newPassword = newPassword;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public Integer getTitleID() {
		return TitleID;
	}

	public void setTitleID(Integer titleID) {
		TitleID = titleID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getGenderID() {
		return GenderID;
	}

	public void setGenderID(Integer genderID) {
		GenderID = genderID;
	}

	public M_Gender getM_gender() {
		return m_gender;
	}

	public void setM_gender(M_Gender m_gender) {
		this.m_gender = m_gender;
	}

	public Integer getMaritalStatusID() {
		return MaritalStatusID;
	}

	public void setMaritalStatusID(Integer maritalStatusID) {
		MaritalStatusID = maritalStatusID;
	}

	public M_MaritalStatus getM_maritalstatus() {
		return m_maritalstatus;
	}

	public void setM_maritalstatus(M_MaritalStatus m_maritalstatus) {
		this.m_maritalstatus = m_maritalstatus;
	}

	public Integer getStatusID() {
		return StatusID;
	}

	public void setStatusID(Integer statusID) {
		StatusID = statusID;
	}

	public M_Status getM_status() {
		return m_status;
	}

	public void setM_status(M_Status m_status) {
		this.m_status = m_status;
	}

	public String getAadhaarNo() {
		return AadhaarNo;
	}

	public void setAadhaarNo(String aadhaarNo) {
		AadhaarNo = aadhaarNo;
	}

	public String getPAN() {
		return PAN;
	}

	public void setPAN(String pAN) {
		PAN = pAN;
	}

	public Timestamp getDOB() {
		return DOB;
	}

	public void setDOB(Timestamp dOB) {
		DOB = dOB;
	}

	public Timestamp getDOJ() {
		return DOJ;
	}

	public void setDOJ(Timestamp dOJ) {
		DOJ = dOJ;
	}

	public Integer getQualificationID() {
		return QualificationID;
	}

	public void setQualificationID(Integer qualificationID) {
		QualificationID = qualificationID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmailID() {
		return EmailID;
	}

	public void setEmailID(String emailID) {
		EmailID = emailID;
	}

	// public Integer getReligionID() {
	// return ReligionID;
	// }
	//
	// public void setReligionID(Integer religionID) {
	// ReligionID = religionID;
	// }
	//
	// public M_Religion getM_relegion() {
	// return m_relegion;
	// }
	//
	// public void setM_relegion(M_Religion m_relegion) {
	// this.m_relegion = m_relegion;
	// }

	public Set<M_UserServiceRoleMapping> getM_UserServiceRoleMapping() {
		return m_UserServiceRoleMapping;
	}

	public void setM_UserServiceRoleMapping(Set<M_UserServiceRoleMapping> m_UserServiceRoleMapping) {
		this.m_UserServiceRoleMapping = m_UserServiceRoleMapping;
	}

	public String getEmergencyContactPerson() {
		return EmergencyContactPerson;
	}

	public void setEmergencyContactPerson(String emergencyContactPerson) {
		EmergencyContactPerson = emergencyContactPerson;
	}

	public String getEmergencyContactNo() {
		return EmergencyContactNo;
	}

	public void setEmergencyContactNo(String emergencyContactNo) {
		EmergencyContactNo = emergencyContactNo;
	}

	public Boolean getIsSupervisor() {
		return IsSupervisor;
	}

	public void setIsSupervisor(Boolean isSupervisor) {
		IsSupervisor = isSupervisor;
	}

	public Boolean getDeleted() {
		return Deleted;
	}

	public void setDeleted(Boolean deleted) {
		Deleted = deleted;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		CreatedDate = createdDate;
	}

	public String getModifiedBy() {
		return ModifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	public Timestamp getLastModDate() {
		return LastModDate;
	}

	public void setLastModDate(Timestamp lastModDate) {
		LastModDate = lastModDate;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
