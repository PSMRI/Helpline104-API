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
package com.iemr.helpline104.data.cdss;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "m_cdssdata")
public class Symptoms {

	@Id
	@Column(name = "SymptomId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SymptomId;
	@Column(name="Symptom")
	private String Symptom;
	@Column(name="Gender")
	private String Gender;
	@Column(name="AgeFrom")
	private int AgeFrom;
	@Column(name="AgeTo")
	private int AgeTo;
	@Column(name="Data")
	private String Data;

	public String getSymptom() {
		return Symptom;
	}

	public void setSymptom(String symptom) {
		Symptom = symptom;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public int getAgeFrom() {
		return AgeFrom;
	}

	public void setAgeFrom(int ageFrom) {
		AgeFrom = ageFrom;
	}

	public int getAgeTo() {
		return AgeTo;
	}

	public void setAgeTo(int ageTo) {
		AgeTo = ageTo;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}

	@Override
	public String toString() {
		return "Symptoms [SymptomId=" + SymptomId + ", Symptom=" + Symptom + ", Gender=" + Gender + ", AgeFrom="
				+ AgeFrom + ", AgeTo=" + AgeTo + ", Data=" + Data + "]";
	}

	public int getSymptomId() {
		return SymptomId;
	}

	public void setSymptomId(int symptomId) {
		SymptomId = symptomId;
	}

}
