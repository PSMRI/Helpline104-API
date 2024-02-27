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
package com.iemr.helpline104.data.callqamapping;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import com.google.gson.Gson;

@Entity
public class CallqaMappings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mappingId;
	
	@Transient
	private List<M_104callqamapping> m_104callqamapping;

	public List<M_104callqamapping> getM_104callqamapping() {
		return m_104callqamapping;
	}

	public void setM_104callqamapping(List<M_104callqamapping> m_104callqamapping) {
		this.m_104callqamapping = m_104callqamapping;
	}

	public Long getMappingId() {
		return mappingId;
	}
	
	public String toString() {
		return new Gson().toJson(this);
	}
}
