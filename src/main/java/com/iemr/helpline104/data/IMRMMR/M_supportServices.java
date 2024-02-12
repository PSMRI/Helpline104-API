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
package com.iemr.helpline104.data.IMRMMR;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "m_imrmmrsupportservices")
public class M_supportServices {
	
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supportServiceID ")
	private Integer supportServiceID ;

	@Expose
	@Column(name = "supportServiceName")
	private String supportServiceName ;

	public M_supportServices() {
	}
	
	public M_supportServices(Integer supportServiceID, String supportServiceName) {
	    this.supportServiceID = supportServiceID;
	    this.supportServiceName = supportServiceName;
	}

	public Integer getSupportServiceID() {
		return supportServiceID;
	}

	public void setSupportServiceID(Integer supportServiceID) {
		this.supportServiceID = supportServiceID;
	}

	public String getSupportServiceName() {
		return supportServiceName;
	}

	public void setSupportServiceName(String supportServiceName) {
		this.supportServiceName = supportServiceName;
	}
}
