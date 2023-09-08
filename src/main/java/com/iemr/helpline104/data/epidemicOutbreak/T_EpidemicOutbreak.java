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
package com.iemr.helpline104.data.epidemicOutbreak;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.helpline104.data.beneficiarycall.BenCall;
import com.iemr.helpline104.data.location.DistrictBlock;
import com.iemr.helpline104.data.location.DistrictBranchMapping;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.utils.mapper.OutputMapper;

@Entity
@Table(name = "T_EpidemicOutbreak")
public class T_EpidemicOutbreak {
	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long outbreakComplaintID;
	@Expose
	private String requestID;
	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "benCallID")
	private BenCall benCall;
	@Expose
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;
	@Expose
	private String natureOfComplaint;
	@Expose
	private Integer totalPeopleAffected;
	@Expose
	private Integer affectedDistrictID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "affectedDistrictID")
	private Districts m_district;
	@Expose
	private Integer affectedDistrictBlockID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "affectedDistrictBlockID")
	private DistrictBlock m_districtblock;
	@Expose
	private Integer affectedVillageID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "affectedVillageID")
	private DistrictBranchMapping districtBranchMapping;
	@Expose
	@Column(name = "ProviderServiceMapID")
	private Integer serviceID;
	@Expose
	private String remarks;
	@Expose
	private Boolean deleted;
	@Expose
	private String createdBy;
	@Expose
	private String modifiedBy;
	@Expose
	@Column(name = "createdDate", insertable = false, updatable = false)
	private Timestamp createdDate;

	@Expose
	@Transient
	private String phoneNum;

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public T_EpidemicOutbreak() {
		super();
	}

	public T_EpidemicOutbreak(Long outbreakComplaintID, String requestID, Long beneficiaryRegID,
			String natureOfComplaint, Integer totalPeopleAffected, Integer affectedDistrictID, String districtName,
			Integer affectedDistrictBlockID, String blockName, Integer affectedVillageID, String villageName,
			String remarks, Timestamp createdDate) {
		super();
		this.outbreakComplaintID = outbreakComplaintID;
		this.requestID = requestID;
		this.beneficiaryRegID = beneficiaryRegID;
		this.natureOfComplaint = natureOfComplaint;
		this.totalPeopleAffected = totalPeopleAffected;
		this.m_district = new Districts(affectedDistrictID, districtName);
		this.m_districtblock = new DistrictBlock(affectedDistrictBlockID, blockName);
		this.districtBranchMapping = new DistrictBranchMapping(affectedVillageID, villageName);
		this.affectedDistrictID = affectedDistrictID;
		this.affectedDistrictBlockID = affectedDistrictBlockID;
		this.affectedVillageID = affectedVillageID;
		this.createdDate = createdDate;
		this.remarks = remarks;

	}

	public Long getBenCallID() {
		return benCallID;
	}

	public Long getOutbreakComplaintID() {
		return outbreakComplaintID;
	}

	public void setOutbreakComplaintID(Long outbreakComplaintID) {
		this.outbreakComplaintID = outbreakComplaintID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public Long getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	public void setBeneficiaryRegID(Long beneficiaryRegID) {
		this.beneficiaryRegID = beneficiaryRegID;
	}

	public String getNatureOfComplaint() {
		return natureOfComplaint;
	}

	public void setNatureOfComplaint(String natureOfComplaint) {
		this.natureOfComplaint = natureOfComplaint;
	}

	public Integer getTotalPeopleAffected() {
		return totalPeopleAffected;
	}

	public void setTotalPeopleAffected(Integer totalPeopleAffected) {
		this.totalPeopleAffected = totalPeopleAffected;
	}

	public Integer getAffectedDistrictID() {
		return affectedDistrictID;
	}

	public void setAffectedDistrictID(Integer affectedDistrictID) {
		this.affectedDistrictID = affectedDistrictID;
	}

	public Integer getAffectedDistrictBlockID() {
		return affectedDistrictBlockID;
	}

	public void setAffectedDistrictBlockID(Integer affectedDistrictBlockID) {
		this.affectedDistrictBlockID = affectedDistrictBlockID;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getServiceID() {
		return serviceID;
	}

	public void setServiceID(Integer serviceID) {
		this.serviceID = serviceID;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setBenCall(BenCall benCall) {
		this.benCall = benCall;
	}

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}

	public Districts getM_district() {
		return m_district;
	}

	public void setM_district(Districts m_district) {
		this.m_district = m_district;
	}

	public DistrictBlock getM_districtblock() {
		return m_districtblock;
	}

	public void setM_districtblock(DistrictBlock m_districtblock) {
		this.m_districtblock = m_districtblock;
	}

	public Integer getAffectedVillageID() {
		return affectedVillageID;
	}

	public void setAffectedVillageID(Integer affectedVillageID) {
		this.affectedVillageID = affectedVillageID;
	}

	public DistrictBranchMapping getDistrictBranchMapping() {
		return districtBranchMapping;
	}

	public void setDistrictBranchMapping(DistrictBranchMapping districtBranchMapping) {
		this.districtBranchMapping = districtBranchMapping;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public OutputMapper getOutputMapper() {
		return outputMapper;
	}

	public void setOutputMapper(OutputMapper outputMapper) {
		this.outputMapper = outputMapper;
	}

	public BenCall getBenCall() {
		return benCall;
	}

	public void setBenCallID(Long benCallID) {
		this.benCallID = benCallID;
	}

}
