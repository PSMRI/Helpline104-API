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
package com.iemr.helpline104.data.diseaseScreening;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name = "m_questionnaire")
public class M_Questionnaire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer questionID;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questionID")
	private Set<M_questionairValues> m_questionairValues;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questionID")
	private Set<M_104QuestionScore> m_104QuestionScore;

	

	private String question;
	private String questionDesc;
	private Integer questionTypeID;
	private String answerType;
	private Boolean triggerFeedback;
	private String triggerFeedbackFor;
	private Integer providerServiceMapID;
	private Boolean deleted;
	private String createdBy;
	private Integer questionWeightage;
	private String questionType;
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Date lastModDate;

	@Column(name = "QuestionRank")
	private int questionRank;

	public M_Questionnaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public M_Questionnaire(Integer questionID, String question, String questionDesc, Integer questionTypeID,
			String answerType, Boolean triggerFeedback, String triggerFeedbackFor, Integer providerServiceMapID) {
		super();
		this.questionID = questionID;
		this.question = question;
		this.questionDesc = questionDesc;
		this.questionTypeID = questionTypeID;
		this.answerType = answerType;
		this.triggerFeedback = triggerFeedback;
		this.triggerFeedbackFor = triggerFeedbackFor;
		this.providerServiceMapID = providerServiceMapID;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public Integer getQuestionTypeID() {
		return questionTypeID;
	}

	public void setQuestionTypeID(Integer questionTypeID) {
		this.questionTypeID = questionTypeID;
	}

	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	public Boolean getTriggerFeedback() {
		return triggerFeedback;
	}

	public void setTriggerFeedback(Boolean triggerFeedback) {
		this.triggerFeedback = triggerFeedback;
	}

	public String getTriggerFeedbackFor() {
		return triggerFeedbackFor;
	}

	public void setTriggerFeedbackFor(String triggerFeedbackFor) {
		this.triggerFeedbackFor = triggerFeedbackFor;
	}

	public Integer getProviderServiceMapID() {
		return providerServiceMapID;
	}

	public void setProviderServiceMapID(Integer providerServiceMapID) {
		this.providerServiceMapID = providerServiceMapID;
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

	public Integer getQuestionID() {
		return questionID;
	}

	public Integer getQuestionWeightage() {
		return questionWeightage;
	}

	public void setQuestionWeightage(Integer questionWeightage) {
		this.questionWeightage = questionWeightage;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public int getQuestionRank() {
		return questionRank;
	}

	public void setQuestionRank(int questionRank) {
		this.questionRank = questionRank;
	}

	public void setQuestionID(Integer questionID) {
		this.questionID = questionID;
	}

	public String toString() {
		return new Gson().toJson(this);
	}

	public Set<M_questionairValues> getM_questionairValues() {
		return m_questionairValues;
	}

	public void setM_questionairValues(Set<M_questionairValues> m_questionairValues) {
		this.m_questionairValues = m_questionairValues;
	}

	public Set<M_104QuestionScore> getM_104QuestionScore() {
		return m_104QuestionScore;
	}

	public void setM_104QuestionScore(Set<M_104QuestionScore> m_104QuestionScore) {
		this.m_104QuestionScore = m_104QuestionScore;
	}
	
}
