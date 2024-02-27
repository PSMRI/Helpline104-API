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

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import jakarta.persistence.Transient;

@Entity
@Table(name = "m_usersecurityqamapping")
public class M_UserSecurityQMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UserSecurityQAID;
	private Long UserID;
	
	@Column(name = "QuestionID")
	private String QuestionID;

	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "QuestionID")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "QuestionID", insertable = false, updatable = false)
	private M_LoginSecurityQuestions m_LoginSecurityQuestions;

	private String Answers;
	private String MobileNumber;
	private Boolean Deleted;
	private String CreatedBy;
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date CreatedDate;
	private String ModifiedBy;
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Date LastModDate;

	protected M_UserSecurityQMapping() {
	}

	public M_UserSecurityQMapping(Long userSecurityQAID, Long userID, String questionID,
			M_LoginSecurityQuestions m_LoginSecurityQuestions, String answers, String mobileNumber, Boolean deleted,
			String createdBy, Date createdDate, String modifiedBy, Date lastModDate) {
		super();
		UserSecurityQAID = userSecurityQAID;
		UserID = userID;
		QuestionID = questionID;
		this.m_LoginSecurityQuestions = m_LoginSecurityQuestions;
		this.Answers = answers;
		this.MobileNumber = mobileNumber;
		this.Deleted = deleted;
		this.CreatedBy = createdBy;
		this.CreatedDate = createdDate;
		this.ModifiedBy = modifiedBy;
		this.LastModDate = lastModDate;
	}

	public Long getUserSecurityQAID() {
		return this.UserSecurityQAID;
	}

	public Long getUserID() {
		return this.UserID;
	}

	public M_LoginSecurityQuestions getM_LoginSecurityQuestions() {
		return this.m_LoginSecurityQuestions;
	}

	public String getAnswers() {
		return this.Answers;
	}

	public String getMobileNumber() {
		return this.MobileNumber;
	}

	public Boolean getDeleted() {
		return this.Deleted;
	}

	public String getCreatedBy() {
		return this.CreatedBy;
	}

	public Date getCreatedDate() {
		return CreatedDate;
	}

	public String getModifiedBy() {
		return this.ModifiedBy;
	}

	public Date getLastModDate() {
		return LastModDate;
	}

	public void setUserSecurityQAID(Long userSecurityQAID) {
		UserSecurityQAID = userSecurityQAID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public void setM_LoginSecurityQuestions(M_LoginSecurityQuestions m_LoginSecurityQuestions) {
		this.m_LoginSecurityQuestions = m_LoginSecurityQuestions;
	}

	public void setAnswers(String answers) {
		Answers = answers;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public void setDeleted(Boolean deleted) {
		Deleted = deleted;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}

	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	public void setLastModDate(Date lastModDate) {
		LastModDate = lastModDate;
	}

	public String getQuestionID() {
		return QuestionID;
	}

	public void setQuestionID(String questionID) {
		QuestionID = questionID;
	}
}
