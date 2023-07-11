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
package com.iemr.helpline104.data.userbeneficiarydata;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.iemr.helpline104.data.users.M_UserDemographics;

@Entity
@Table(name = "M_Religion")
public class M_Religion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ReligionID;
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "m_religion")
	private Set<I_BenDemographics> i_bendemographics;*/
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "m_religion")
	private Set<M_UserDemographics> m_userdemographics;
	private String ReligionType;
	private String ReligionDesc;
	private Boolean Deleted;
	private String CreatedBy;
	private Date CreatedDate;
	private String ModifiedBy;
	private Date LastModDate;

	protected M_Religion() {
	}

	public M_Religion(int ReligionID, String ReligionType, String ReligionDesc) {
		this.ReligionID = Integer.valueOf(ReligionID);
		this.ReligionType = ReligionType;
		this.ReligionDesc = ReligionDesc;
	}

	public String toString() {
		return new Gson().toJson(this);
	}
}
