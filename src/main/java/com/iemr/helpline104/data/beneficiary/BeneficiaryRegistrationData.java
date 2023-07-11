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
package com.iemr.helpline104.data.beneficiary;

import java.util.List;

import com.google.gson.Gson;
import com.iemr.helpline104.data.directory.Directory;
import com.iemr.helpline104.data.location.States;
import com.iemr.helpline104.data.userbeneficiarydata.M_Community;
import com.iemr.helpline104.data.userbeneficiarydata.M_Gender;
import com.iemr.helpline104.data.userbeneficiarydata.M_Language;
import com.iemr.helpline104.data.userbeneficiarydata.M_MaritalStatus;
import com.iemr.helpline104.data.userbeneficiarydata.M_PhoneType;
import com.iemr.helpline104.data.userbeneficiarydata.M_Status;
import com.iemr.helpline104.data.userbeneficiarydata.M_Title;

public class BeneficiaryRegistrationData {
	private List<M_Title> m_Title;
	private List<M_Status> m_Status;
	//private List<I_BeneficiaryEducation> i_BeneficiaryEducation;
	private List<States> states;
	private List<M_Gender> m_genders;
	private List<M_MaritalStatus> m_maritalStatuses;
	private List<M_Community> m_communities;
	private List<M_Language> m_language;
	private List<Directory> directory;
	//private List<I_BenRelationshipType> i_beneficiaryType;
	private List<M_PhoneType> phoneTypes;

	public String toString() {
		return new Gson().toJson(this);
	}
    /*
	public List<I_BeneficiaryEducation> getI_BeneficiaryEducation() {
		return i_BeneficiaryEducation;
	}

	public void setI_BeneficiaryEducation(List<I_BeneficiaryEducation> i_BeneficiaryEducation) {
		this.i_BeneficiaryEducation = i_BeneficiaryEducation;
	} */

	public List<M_Gender> getM_genders() {
		return m_genders;
	}

	public void setM_genders(List<M_Gender> m_genders) {
		this.m_genders = m_genders;
	}

	public List<M_MaritalStatus> getM_maritalStatuses() {
		return m_maritalStatuses;
	}

	public void setM_maritalStatuses(List<M_MaritalStatus> m_maritalStatuses) {
		this.m_maritalStatuses = m_maritalStatuses;
	}

	public List<M_Community> getM_Communities() {
		return m_communities;
	}

	public void setM_Communities(List<M_Community> m_Communities) {
		this.m_communities = m_Communities;
	}

	public List<M_Title> getM_Title() {
		return this.m_Title;
	}

	public void setM_Title(List<M_Title> m_Title) {
		this.m_Title = m_Title;
	}

	public List<M_Status> getM_Status() {
		return this.m_Status;
	}

	public void setM_Status(List<M_Status> m_Status) {
		this.m_Status = m_Status;
	}
    /*
	public List<I_BeneficiaryEducation> getM_UserQualification() {
		return this.i_BeneficiaryEducation;
	}

	public void setM_UserQualification(List<I_BeneficiaryEducation> m_UserQualification) {
		this.i_BeneficiaryEducation = m_UserQualification;
	}*/

	public List<States> getStates() {
		return this.states;
	}

	public void setStates(List<States> states) {
		this.states = states;
	}
	
	public List<M_Gender> getM_Genders() {
		return this.m_genders;
	}

	public void setM_Genders(List<M_Gender> m_genders) {
		this.m_genders = m_genders;
	}
	
	public List<M_MaritalStatus> getMaritalStatus() {
		return this.m_maritalStatuses;
	}

	public void setMaritalStatus(List<M_MaritalStatus> m_maritalStatuses) {
		this.m_maritalStatuses = m_maritalStatuses;
	}

	public List<M_Language> getM_language() {
		return m_language;
	}

	public void setM_language(List<M_Language> m_language) {
		this.m_language = m_language;
	}

	public List<Directory> getDirectory() {
		return directory;
	}

	public void setDirectory(List<Directory> directory) {
		this.directory = directory;
	}
    /*
	public List<I_BenRelationshipType> getI_beneficiaryType() {
		return i_beneficiaryType;
	}

	public void setI_beneficiaryType(List<I_BenRelationshipType> i_beneficiaryType) {
		this.i_beneficiaryType = i_beneficiaryType;
	} */

	public List<M_PhoneType> getPhoneTypes() {
		return phoneTypes;
	}

	public void setPhoneTypes(List<M_PhoneType> phoneTypes) {
		this.phoneTypes = phoneTypes;
	}
}
