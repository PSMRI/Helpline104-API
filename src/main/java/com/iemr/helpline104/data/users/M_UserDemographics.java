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
package com.iemr.helpline104.data.users;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.google.gson.Gson;
import com.iemr.helpline104.data.location.CityDetails;
import com.iemr.helpline104.data.location.Country;
import com.iemr.helpline104.data.location.States;
import com.iemr.helpline104.data.userbeneficiarydata.M_Community;
import com.iemr.helpline104.data.userbeneficiarydata.M_Religion;

@Entity
@Table(name = "M_UserDemographics")
public class M_UserDemographics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer DemographicID;

	private Integer UserID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(unique = true, insertable = false, referencedColumnName = "UserID")
	private M_User m_user;

	private String FathersName;
	private String MothersName;

	private Integer CommunityID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(unique = true, insertable = false, referencedColumnName = "CommunityID")
	private M_Community m_community;

	private Integer ReligionID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(unique = true, insertable = false, referencedColumnName = "ReligionID")
	private M_Religion m_religion;

	private String AddressLine1;
	private String AddressLine2;
	private String AddressLine3;
	private String AddressLine4;
	private String AddressLine5;

	private Integer CityID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(unique = true, insertable = false, referencedColumnName = "CityID")
	private CityDetails m_city;

	private Integer StateID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(unique = true, insertable = false, referencedColumnName = "StateID")
	private States m_state;

	private Integer CountryID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(unique = true, insertable = false, referencedColumnName = "CountryID")
	private Country m_country;

	private String PinCode;
	private Boolean IsPresent;
	private Boolean IsPermanent;
	private Boolean Deleted;
	private String CreatedBy;
	private Date CreatedDate;
	private String ModifiedBy;
	private Date LastModDate;

	protected M_UserDemographics() {
	}

	protected M_UserDemographics(int DemographicID, int UserID, String FathersName, String MothersName, int CommunityID,
			int ReligionID, String AddressLine1, String AddressLine2, String AddressLine3, String AddressLine4,
			String AddressLine5, int CityID, int StateID, int CountryID, String PinCode, Boolean IsPresent,
			Boolean IsPermanent, Boolean Deleted) {
		this.DemographicID = Integer.valueOf(DemographicID);
		this.UserID = Integer.valueOf(UserID);
		this.FathersName = FathersName;
		this.MothersName = MothersName;
		this.CommunityID = Integer.valueOf(CommunityID);
		this.m_community = new M_Community(this.CommunityID.intValue(), "");
		this.ReligionID = Integer.valueOf(ReligionID);
		this.m_religion = new M_Religion(this.ReligionID.intValue(), "", "");
		this.AddressLine1 = AddressLine1;
		this.AddressLine2 = AddressLine2;
		this.AddressLine3 = AddressLine3;
		this.AddressLine4 = AddressLine4;
		this.AddressLine5 = AddressLine5;
		this.CityID = Integer.valueOf(CityID);
		this.m_city = new CityDetails(Integer.valueOf(CityID), "");
		this.StateID = Integer.valueOf(StateID);
		this.m_state = new States(StateID, "");
		this.CountryID = Integer.valueOf(CountryID);
		this.m_country = new Country(CountryID, "");
		this.PinCode = PinCode;
		this.IsPresent = IsPresent;
		this.IsPermanent = IsPermanent;
		this.Deleted = Deleted;
	}

	public String toString() {
		return new Gson().toJson(this);
	}
}
