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
package com.iemr.helpline104.sms;

import java.sql.Timestamp;

public class SmsRequestOBJ {
	private Long beneficiaryRegID;
	private Integer smsTemplateID;
 private Integer smsTemplateTypeID;
 private String smsType;
 private String nodalNumber;
 private Integer stateID;
 private String imrDate;
 private String createdBy;
 private String imrID;
 private String informerName;
 public String getInformerName() {
	return informerName;
}
public void setInformerName(String informerName) {
	this.informerName = informerName;
}
public String getSmsType() {
	return smsType;
}
public String getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
public void setSmsType(String smsType) {
	this.smsType = smsType;
}
public Long getBeneficiaryRegID() {
	return beneficiaryRegID;
}
public void setBeneficiaryRegID(Long beneficiaryRegID) {
	this.beneficiaryRegID = beneficiaryRegID;
}
public Integer getSmsTemplateID() {
	return smsTemplateID;
}
public void setSmsTemplateID(Integer smsTemplateID) {
	this.smsTemplateID = smsTemplateID;
}
public Integer getSmsTemplateTypeID() {
	return smsTemplateTypeID;
}
public void setSmsTemplateTypeID(Integer smsTemplateTypeID) {
	this.smsTemplateTypeID = smsTemplateTypeID;
}
public String getNodalNumber() {
	return nodalNumber;
}
public void setNodalNumber(String nodalNumber) {
	this.nodalNumber = nodalNumber;
}
public Integer getStateID() {
	return stateID;
}
public void setStateID(Integer stateID) {
	this.stateID = stateID;
}
public String getImrDate() {
	return imrDate;
}
public void setImrDate(String imrDate) {
	this.imrDate = imrDate;
}
public String getImrID() {
	return imrID;
}
public void setImrID(String imrID) {
	this.imrID = imrID;
}

}
