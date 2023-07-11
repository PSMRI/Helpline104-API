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

public class BloodCompDistrictModel {
	
	private String district;
	private Long totalCount;
	private Long WBC;
	private Long redBloodCells;
	private Long others;
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
	 * @return the wBC
	 */
	public Long getWBC() {
		return WBC;
	}
	/**
	 * @param wBC the wBC to set
	 */
	public void setWBC(Long wBC) {
		WBC = wBC;
	}
	/**
	 * @return the redBloodCells
	 */
	public Long getRedBloodCells() {
		return redBloodCells;
	}
	/**
	 * @param redBloodCells the redBloodCells to set
	 */
	public void setRedBloodCells(Long redBloodCells) {
		this.redBloodCells = redBloodCells;
	}
	/**
	 * @return the others
	 */
	public Long getOthers() {
		return others;
	}
	/**
	 * @param others the others to set
	 */
	public void setOthers(Long others) {
		this.others = others;
	}
	
	@Override
	public String toString() {

		return new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).serializeNulls()
				.setDateFormat("dd-MM-yyyy HH:mm:ss").create().toJson(this);

	}
}
