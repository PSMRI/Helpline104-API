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
package com.iemr.helpline104.data.organDonation;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.helpline104.data.beneficiarycall.BenCall;
import com.iemr.helpline104.data.userbeneficiarydata.M_Gender;
import com.iemr.helpline104.utils.mapper.OutputMapper;

@Entity
@Table(name="T_OrganDonation")

public class T_OrganDonation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Long organDonationID;
	@Expose
	private String requestID;
	@Transient	
	@Expose	
	private List<RequestedInstitution> requestedInstitution;
	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "benCallID")
	private BenCall benCall;
	@Expose
	private Long beneficiaryRegID;
	/*@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "beneficiaryRegID")
	@Expose
	private I_Beneficiary i_beneficiary;*/	
	@Expose
	private String donarName;
	@Expose
	private Integer donarAge;
	@Expose
	private Short donarGenderID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "donarGenderID")
	private M_Gender m_gender;
	@Expose
	private Integer donationTypeID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "donationTypeID")
	private M_DonationType m_donationType;
	@Expose
	private Integer donatableOrganID;
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "donatableOrganID")
	private M_DonatableOrgan m_donatableOrgan;
	@Expose
	private Integer acceptorHospitalID;
	@Expose
	@Column(name = "Remarks")
	private String remarks;
	@Expose
	@Transient
	private Integer districtID;
	
	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;
	
	@Expose
	@Column(name = "IsSelf")
	private Boolean isSelf;	
	
	@Expose
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;
	@Expose
	private String createdBy;
	@Column(name = "CreatedDate", insertable=false, updatable=false)
	@Expose
	private Date createdDate;
	private String modifiedBy;
	
	public T_OrganDonation(Long organDonationID, String requestID, Long beneficiaryRegID, String donarName, Integer donarAge,Short donarGenderID, String genderName, Integer donationTypeID, String donationType, String donationTypeDesc,
			Integer donatableOrganID, String donatableOrgan, String donatableOrganDesc, Integer acceptorHospitalID, String remarks) {
		super();
		this.organDonationID = organDonationID;
		this.requestID = requestID;
		this.beneficiaryRegID = beneficiaryRegID;
		this.donarName = donarName;
		this.donarAge = donarAge;
		this.m_gender = new M_Gender(donarGenderID, genderName);
		this.m_donationType = new M_DonationType(donationTypeID, donationType, donationTypeDesc);
		this.m_donatableOrgan = new M_DonatableOrgan(donatableOrganID, donatableOrgan, donatableOrganDesc);
		this.acceptorHospitalID = acceptorHospitalID;
		this.remarks = remarks;
		
	}
	
	public T_OrganDonation(Long organDonationID, Long beneficiaryRegID, String donarName, Integer donarAge,Short donarGenderID, Integer donationTypeID, String donationType, String donationTypeDesc,
			Integer donatableOrganID, String donatableOrgan, String donatableOrganDesc, Integer acceptorHospitalID, String remarks) {
		super();
		this.organDonationID = organDonationID;
		this.beneficiaryRegID = beneficiaryRegID;
		this.donarName = donarName;
		this.donarAge = donarAge;
		this.donarGenderID = donarGenderID;
		this.m_donationType = new M_DonationType(donationTypeID, donationType, donationTypeDesc);
		this.m_donatableOrgan = new M_DonatableOrgan(donatableOrganID, donatableOrgan, donatableOrganDesc);
		this.acceptorHospitalID = acceptorHospitalID;
		this.remarks = remarks;
	}


	public T_OrganDonation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getBenCallID()
	{
		return benCallID;
	}


	public Long getOrganDonationID() {
		return organDonationID;
	}


	public Long getBeneficiaryRegID() {
		return beneficiaryRegID;
	}


	public void setBeneficiaryRegID(Long beneficiaryRegID) {
		this.beneficiaryRegID = beneficiaryRegID;
	}

	public Integer getDonationTypeID() {
		return donationTypeID;
	}


	public void setDonationTypeID(Integer donationTypeID) {
		this.donationTypeID = donationTypeID;
	}


	public Integer getDonatableOrganID() {
		return donatableOrganID;
	}


	public void setDonatableOrganID(Integer donatableOrganID) {
		this.donatableOrganID = donatableOrganID;
	}

	public Integer getAcceptorHospitalID() {
		return acceptorHospitalID;
	}

	public void setAcceptorHospitalID(Integer acceptorHospitalID) {
		this.acceptorHospitalID = acceptorHospitalID;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	
	public String getRequestID() {
		return requestID;
	}
	
	public Integer getDistrictID() {
		return districtID;
	}
	
	public void setBenCall(BenCall benCall)
	{
		this.benCall = benCall;
	}
	
	public List<RequestedInstitution> getRequestedInstitution()
	{
		return requestedInstitution;
	}
	public void setRequestedInstitution(List<RequestedInstitution> requestedInstitution)
	{
		this.requestedInstitution = requestedInstitution;
	}
	
    /*
	public String toString() {
		return new Gson().toJson(this);
	} */
	
	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString()
	{
		return outputMapper.gson().toJson(this);
	}

}
