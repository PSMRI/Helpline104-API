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

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.helpline104.utils.mapper.OutputMapper;


@Entity
@Table(name = "m_providerserviceaddmapping")
public class WorkLocation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PSAddMapID")
	@Expose
	private Integer psAddMapID;

	@Column(name = "ProviderServiceMapID")
	@Expose
	private Integer providerServiceMapID;
	@Column(name = "DistrictID")
	@Expose
	private Integer districtID;
	@Column(name = "Deleted")
	@Expose
	private Boolean deleted;
	@Column(name = "LocationName")
	@Expose
	private String locationName;
	@Column(name = "Address")
	@Expose
	private String address;
	@Column(name = "Processed")
	private String processed;
	@Column(name = "createdBy")
	@Expose
	private String CreatedBy;
	@Column(name = "modifiedBy")
	private String ModifiedBy;
	@Column(name = "createdDate")
	@Expose
	private Timestamp CreatedDate;
	@Column(name = "LastModDate")
	private Timestamp lastModDate;

	@Transient
	private OutputMapper mapper = new OutputMapper();

	@Override
	public String toString() {
		return mapper.gson().toJson(this);
	}
}
