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
package com.iemr.helpline104.data.report;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
import com.iemr.helpline104.utils.mapper.OutputMapper;

@Entity
@Table(name = "db_reporting.fact_bencall", schema = "db_reporting")

public class BencallReport implements Serializable 
{
	private static final long serialVersionUID = 1L;
	@Transient
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Fact_BenCallID")
	private Long fact_BenCallID;

	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;

	
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;

	@Expose
	@Column(name = "CallID")
	private String callID;

	@Expose
	@Column(name = "SessionID")
	private String sessionID;

	@Expose
	@Column(name = "PhoneNo")
	private String phoneNo;

	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;

	@Column(name = "CallTypeID")
	private Integer callTypeID;

	@Expose
	@Column(name = "CallTypeName")
	private String callTypeName;

	@Expose
	@Column(name = "CallSubTypeName")
	private String callSubTypeName;

	@Expose
	@Column(name = "CallTime")
	private Timestamp callTime;

	@Expose
	@Column(name = "Remarks")
	private String remarks;

	@Expose
	@Column(name = "CallClosureType")
	private String callClosureType;

	@Expose
	@Column(name = "CallReceivedUserID")
	private Integer callReceivedUserID;

	@Expose
	@Column(name = "ReceivedRoleName")
	private String receivedRoleName;

	@Expose
	@Column(name = "ReceivedAgentID")
	private String agentID;	

	@Expose
	@Column(name = "CallEndUserID")
	private Integer callEndUserID;

	@Expose
	@Column(name = "Category")
	private String category;

	@Expose
	@Column(name = "SubCategory")
	private String subCategory;

	@Expose
	@Column(name = "CDICallStatus")
	private String cdiCallStatus;

	@Expose
	@Column(name = "IsOutbound")
	private Boolean isOutbound;

	@Expose
	@Column(name = "IsCalledEarlier")
	private Boolean isCalledEarlier;

	@Column(name = "CreatedBy")
	private String createdBy;

	
	@Column(name = "CreatedDate")
	private Timestamp createdDate;
	
	/*
	@Expose	
	@Transient
	private Timestamp callDate; */

	// beneficiary details from dim_beneficiary
	@OneToOne
	@JoinColumn(updatable = false, insertable = false, name = "beneficiaryRegID", referencedColumnName = "beneficiaryRegID")
	private BeneficiaryReport beneficiaryReport;

	// search params
	@Transient
	private Timestamp startDateTime;

	@Transient
	private Timestamp endDateTime;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CallReceivedUserID", referencedColumnName = "UserID", insertable = false, updatable = false)
	private UserReport userReportObj;

	public BencallReport()
	{
		super();
	}

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString()
	{
		return outputMapper.gson().toJson(this);
	}

	public Timestamp getStartDateTime()
	{
		return startDateTime;
	}

	public Timestamp getEndDateTime()
	{
		return endDateTime;
	}

	public Integer getProviderServiceMapID()
	{
		return providerServiceMapID;
	}
	
	public String getAgentID()
	{
		return agentID;
	}

}
