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

import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

public class BloodGroupDistrictModel {

	private String district;
	private Long totalCount;
	private Long APositive;
	private Long ANegative;
	private Long BPositive;
	private Long BNegative;
	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return the totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the aPositive
	 */
	public Long getAPositive() {
		return APositive;
	}
	/**
	 * @param aPositive the aPositive to set
	 */
	public void setAPositive(Long aPositive) {
		APositive = aPositive;
	}
	/**
	 * @return the aNegative
	 */
	public Long getANegative() {
		return ANegative;
	}
	/**
	 * @param aNegative the aNegative to set
	 */
	public void setANegative(Long aNegative) {
		ANegative = aNegative;
	}
	/**
	 * @return the bPositive
	 */
	public Long getBPositive() {
		return BPositive;
	}
	/**
	 * @param bPositive the bPositive to set
	 */
	public void setBPositive(Long bPositive) {
		BPositive = bPositive;
	}
	/**
	 * @return the bNegative
	 */
	public Long getBNegative() {
		return BNegative;
	}
	/**
	 * @param bNegative the bNegative to set
	 */
	public void setBNegative(Long bNegative) {
		BNegative = bNegative;
	}
	
	@Override
	public String toString() {

		return new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).serializeNulls()
				.setDateFormat("dd-MM-yyyy HH:mm:ss").create().toJson(this);

	}
}
