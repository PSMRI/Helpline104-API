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
package com.iemr.helpline104.data.balVivah;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.helpline104.data.location.DistrictBranchMapping;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.location.States;
import com.iemr.helpline104.data.userbeneficiarydata.M_Gender;

@Entity
@Table(name = "t_balVivahComplaints")
public class BalVivahComplaint {
	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "balVivahComplaintID")
	private Long balVivaComplaintID;

	@Expose
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;

	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;

	@Expose
	@Column(name = "subjectOfComplaint")
	private String subjectOfComplaint;

	@Expose
	@Column(name = "childName")
	private String childName;

	@Expose
	@Column(name = "childFatherName")
	private String childFatherName;

	@Expose
	@Column(name = "childAge")
	private String childAge;

	@Expose
	@Column(name = "childGender")
	private Short childGender;

	@Expose
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(updatable = false, insertable = false, name = "childGender", referencedColumnName = "genderId")
	private M_Gender gender;

	@Expose
	@Column(name = "childStateID")
	private Integer childState;
	@Expose
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(updatable = false, insertable = false, name = "childStateID", referencedColumnName = "stateID")
	private States state;

	@Expose
	@Column(name = "childFatherStateID")
	private String childFatherState;

	@Expose
	@Column(name = "childDistrictID")
	private Integer childDistrict;
	@Expose
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(updatable = false, insertable = false, name = "childDistrictID", referencedColumnName = "districtID")
	private Districts district;

	@Expose
	@Column(name = "childFatherDistrictID")
	private String childFatherDistrict;

	@Expose
	@Column(name = "childSubDistrictID")
	private String childSubDistrict;

	@Expose
	@Column(name = "childFatherSubDistrictID")
	private String childFatherSubDistrict;

	@Expose
	@Column(name = "childVillageID")
	private Integer childVillage;
	@Expose
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(updatable = false, insertable = false, name = "childVillageID", referencedColumnName = "districtBranchID")
	private DistrictBranchMapping districtBranchMapping;

	@Expose
	@Column(name = "childFatherVillageID")
	private String childFatherVillage;

	@Expose
	@Column(name = "marriageDate")
	private Date marriageDate;

	@Expose
	@Column(name = "ComplaintDate")
	private Timestamp ComplaintDate;

	@Expose
	@Column(name = "RequestID")
	private String requestID;

	@Expose
	@Column(name = "providerServiceMapID")
	private Integer providerServiceMapID;

	@Expose
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;

	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;

	@Expose
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Timestamp createdDate;

	@Expose
	@Transient
	private String phoneNum;

	public M_Gender getGender() {
		return gender;
	}

	public void setGender(M_Gender gender) {
		this.gender = gender;
	}

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	public Districts getDistrict() {
		return district;
	}

	public void setDistrict(Districts district) {
		this.district = district;
	}

	public DistrictBranchMapping getDistrictBranchMapping() {
		return districtBranchMapping;
	}

	public void setDistrictBranchMapping(DistrictBranchMapping districtBranchMapping) {
		this.districtBranchMapping = districtBranchMapping;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public BalVivahComplaint() {
		super();
	}

	public BalVivahComplaint(Long balVivaComplaintID, String requestID, String childName, String childAge,
			Date marriageDate, String childFatherName, String createdBy, String subjectOfComplaint,
			Timestamp ComplaintDate, Integer childState, String stateName, Integer childDistrict, String districtName,
			Integer childVillage, String villageName, Short childGender, String genderName) {
		super();
		this.balVivaComplaintID = balVivaComplaintID;
		this.requestID = requestID;
		this.childName = childName;
		this.childAge = childAge;
		this.marriageDate = marriageDate;
		this.childFatherName = childFatherName;
		this.createdBy = createdBy;
		this.subjectOfComplaint = subjectOfComplaint;
		this.ComplaintDate = ComplaintDate;
		this.state = new States(childState, stateName);
		this.district = new Districts(childDistrict, districtName);
		this.districtBranchMapping = new DistrictBranchMapping(childVillage, villageName);
		this.gender = new M_Gender(childGender, genderName);
		this.childState = childState;
		this.childDistrict = childDistrict;
		this.childVillage = childVillage;
		this.childGender = childGender;
	}

	public Long getBalVivaComplaintID() {
		return balVivaComplaintID;
	}

	public void setBalVivaComplaintID(Long balVivaComplaintID) {
		this.balVivaComplaintID = balVivaComplaintID;
	}

	public Long getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	public void setBeneficiaryRegID(Long beneficiaryRegID) {
		this.beneficiaryRegID = beneficiaryRegID;
	}

	public String getSubjectOfComplaint() {
		return subjectOfComplaint;
	}

	public void setSubjectOfComplaint(String subjectOfComplaint) {
		this.subjectOfComplaint = subjectOfComplaint;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getChildFatherName() {
		return childFatherName;
	}

	public void setChildFatherName(String childFatherName) {
		this.childFatherName = childFatherName;
	}

	public String getChildAge() {
		return childAge;
	}

	public void setChildAge(String childAge) {
		this.childAge = childAge;
	}

	public Short getChildGender() {
		return childGender;
	}

	public void setChildGender(Short childGender) {
		this.childGender = childGender;
	}

	public Integer getChildDistrict() {
		return childDistrict;
	}

	public void setChildDistrict(Integer childDistrict) {
		this.childDistrict = childDistrict;
	}

	public String getChildFatherDistrict() {
		return childFatherDistrict;
	}

	public void setChildFatherDistrict(String childFatherDistrict) {
		this.childFatherDistrict = childFatherDistrict;
	}

	public String getChildSubDistrict() {
		return childSubDistrict;
	}

	public void setChildSubDistrict(String childSubDistrict) {
		this.childSubDistrict = childSubDistrict;
	}

	public String getChildFatherSubDistrict() {
		return childFatherSubDistrict;
	}

	public void setChildFatherSubDistrict(String childFatherSubDistrict) {
		this.childFatherSubDistrict = childFatherSubDistrict;
	}

	public Integer getChildVillage() {
		return childVillage;
	}

	public void setChildVillage(Integer childVillage) {
		this.childVillage = childVillage;
	}

	public String getChildFatherVillage() {
		return childFatherVillage;
	}

	public void setChildFatherVillage(String childFatherVillage) {
		this.childFatherVillage = childFatherVillage;
	}

	public Date getMarriageDate() {
		return marriageDate;
	}

	public void setMarriageDate(Date marriageDate) {
		this.marriageDate = marriageDate;
	}

	public Timestamp getComplaintDate() {
		return ComplaintDate;
	}

	public void setComplaintDate(Timestamp complaintDate) {
		ComplaintDate = complaintDate;
	}

	public Integer getProviderServiceMapID() {
		return providerServiceMapID;
	}

	public void setProviderServiceMapID(Integer providerServiceMapID) {
		this.providerServiceMapID = providerServiceMapID;
	}

	public Long getBenCallID() {
		return benCallID;
	}

	public void setBenCallID(Long benCallID) {
		this.benCallID = benCallID;
	}

	public Integer getChildState() {
		return childState;
	}

	public void setChildState(Integer childState) {
		this.childState = childState;
	}

	public String getChildFatherState() {
		return childFatherState;
	}

	public void setChildFatherState(String childFatherState) {
		this.childFatherState = childFatherState;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
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

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}
