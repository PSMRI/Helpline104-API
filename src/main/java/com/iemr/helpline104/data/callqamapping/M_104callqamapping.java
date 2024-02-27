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
package com.iemr.helpline104.data.callqamapping;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.iemr.helpline104.data.diseaseScreening.M_questionairValues;
import com.iemr.helpline104.data.diseaseScreening.M_Questionnaire;

@Entity
@Table(name = "t_104bencdiresponse")
public class M_104callqamapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iD;
	private Long beneficiaryRegID;
	private Long benCallID;
	private String callerID;

	@Column(name = "QuestionID")
	@Expose
	private Integer questionID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "questionID")
	@JsonIgnore
	@Expose
	private M_Questionnaire m_questionnaire;

	private String answer;
	private Integer score;
	private Integer totalScore;
	private String remarks;
	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;
	private Boolean deleted;
	private Character processed;
	private String createdBy;
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Date lastModDate;
	@Column(name = "reasonIfNo")
	private String reasonIfNo;

	public M_104callqamapping() {
		super();
	}

	public M_104callqamapping(Long beneficiaryRegID, Integer questionID, String answer, String remarks,
			M_questionairValues questionScore, String question) {
		super();
		this.beneficiaryRegID = beneficiaryRegID;
		this.questionID = questionID;
		this.answer = answer;
		this.remarks = remarks;
		this.m_questionnaire = new M_Questionnaire();
		Set questionScores = new HashSet<>();
		questionScores.add(questionScore);
		this.m_questionnaire.setM_104QuestionScore(questionScores);
		this.m_questionnaire.setQuestion(question);
	}

	public M_104callqamapping(Long beneficiaryRegID, String answer, Integer score, String remarks, String question,
			String reasonIfNo, String questionType, String answerType) {
		super();
		this.beneficiaryRegID = beneficiaryRegID;
		this.answer = answer;
		this.score = score;
		this.remarks = remarks;
		this.m_questionnaire = new M_Questionnaire();
		this.m_questionnaire.setQuestion(question);
		this.reasonIfNo = reasonIfNo;
		this.m_questionnaire.setQuestionType(questionType);
		this.m_questionnaire.setAnswerType(answerType);
	}

	public Long getiD() {
		return iD;
	}

	public Long getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	public void setBeneficiaryRegID(Long beneficiaryRegID) {
		this.beneficiaryRegID = beneficiaryRegID;
	}

	public Integer getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Integer questionID) {
		this.questionID = questionID;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastModDate() {
		return lastModDate;
	}

	public void setLastModDate(Date lastModDate) {
		this.lastModDate = lastModDate;
	}

	public String getCallerID() {
		return callerID;
	}

	public void setCallerID(String callerID) {
		this.callerID = callerID;
	}

	public Character getProcessed() {
		return processed;
	}

	public void setProcessed(Character processed) {
		this.processed = processed;
	}

	public M_Questionnaire getM_questionnaire() {
		return m_questionnaire;
	}

	public void setM_questionnaire(M_Questionnaire m_questionnaire) {
		this.m_questionnaire = m_questionnaire;
	}

	public String toString() {
		return new Gson().toJson(this);
	}

	public Long getBenCallID() {
		return benCallID;
	}

	private void setBenCallID(Long benCallID) {
		this.benCallID = benCallID;
	}

	private Integer getScore() {
		return score;
	}

	private void setScore(Integer score) {
		this.score = score;
	}

	public String getReasonIfNo() {
		return reasonIfNo;
	}

	public void setReasonIfNo(String reasonIfNo) {
		this.reasonIfNo = reasonIfNo;
	}
};
