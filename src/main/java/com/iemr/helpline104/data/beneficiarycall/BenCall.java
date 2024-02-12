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
package com.iemr.helpline104.data.beneficiarycall;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.google.gson.annotations.Expose;

/* This class is created for populating data (Agent Id) in case sheet */

@Entity
@Table(name = "t_bencall")
public class BenCall
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BenCallID")	
	private Long benCallID;
	
	@Expose
	@Column(name = "ReceivedAgentID")
	private String receivedAgentID;
	
	@Expose
	@Column(name = "Remarks")
	private String remarks;
	
	@Column(name = "CallTypeID")
	@Expose
	private Integer callTypeID;
	@Expose
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CallTypeID", insertable = false, updatable = false)
	private CallType callTypeObj;

}
