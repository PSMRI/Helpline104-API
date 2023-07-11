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
package com.iemr.helpline104.data.sioHistory;

import java.util.List;

import com.google.gson.Gson;
import com.iemr.helpline104.data.bloodComponentType.M_BloodGroup;
import com.iemr.helpline104.data.bloodComponentType.M_ComponentType;
import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;
import com.iemr.helpline104.data.directory.Directory;
import com.iemr.helpline104.data.epidemicOutbreak.T_EpidemicOutbreak;
import com.iemr.helpline104.data.foodSafetyCopmlaint.T_FoodSafetyCopmlaint;
import com.iemr.helpline104.data.organDonation.M_DonatableOrgan;
import com.iemr.helpline104.data.organDonation.M_DonationType;
import com.iemr.helpline104.data.organDonation.T_OrganDonation;

public class SioHistory {
	private List<T_BloodRequest> t_bloodRequest;
	private List<M_ComponentType> m_componentType;
	private List<T_EpidemicOutbreak>  t_epidemicOutbreak;
	private List<T_OrganDonation> t_organDonation;
	private List<Directory> directory;
	private List<T_FoodSafetyCopmlaint> t_foodSafetyCopmlaint;
	private List<M_DonationType> m_donationType;
	private List<M_DonatableOrgan> m_donatableOrgan;
	private List<M_BloodGroup> m_bloodGroup;
	
	public String toString() {
		return new Gson().toJson(this);
	}
	
	public List<T_BloodRequest> getT_bloodRequest() {
		return t_bloodRequest;
	}
	public void setT_bloodRequest(List<T_BloodRequest> t_bloodRequest) {
		this.t_bloodRequest = t_bloodRequest;
	}
	public List<M_ComponentType> getM_componentType() {
		return m_componentType;
	}
	public void setM_componentType(List<M_ComponentType> m_componentType) {
		this.m_componentType = m_componentType;
	}
	public List<T_EpidemicOutbreak> getT_epidemicOutbreak() {
		return t_epidemicOutbreak;
	}
	public void setT_epidemicOutbreak(List<T_EpidemicOutbreak> t_epidemicOutbreak) {
		this.t_epidemicOutbreak = t_epidemicOutbreak;
	}
	public List<T_OrganDonation> getT_organDonation() {
		return t_organDonation;
	}
	public void setT_organDonation(List<T_OrganDonation> t_organDonation) {
		this.t_organDonation = t_organDonation;
	}
	public List<Directory> getDirectory() {
		return directory;
	}
	public void setDirectory(List<Directory> directory) {
		this.directory = directory;
	}
	public List<T_FoodSafetyCopmlaint> getT_foodSafetyCopmlaint() {
		return t_foodSafetyCopmlaint;
	}
	public void setT_foodSafetyCopmlaint(List<T_FoodSafetyCopmlaint> t_foodSafetyCopmlaint) {
		this.t_foodSafetyCopmlaint = t_foodSafetyCopmlaint;
	}
	
	public List<M_DonationType> getM_donationType() {
		return m_donationType;
	}

	public void setM_donationType(List<M_DonationType> m_donationType) {
		this.m_donationType = m_donationType;
	}

	public List<M_DonatableOrgan> getM_donatableOrgan() {
		return m_donatableOrgan;
	}

	public void setM_donatableOrgan(List<M_DonatableOrgan> m_donatableOrgan) {
		this.m_donatableOrgan = m_donatableOrgan;
	}
	
	public List<M_BloodGroup> getM_bloodGroup() {
		return m_bloodGroup;
	}

	public void setM_bloodGroup(List<M_BloodGroup> m_bloodGroup) {
		this.m_bloodGroup = m_bloodGroup;
	}
}
