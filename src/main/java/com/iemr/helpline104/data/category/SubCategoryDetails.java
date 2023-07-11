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
package com.iemr.helpline104.data.category;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.google.gson.annotations.Expose;
import com.iemr.helpline104.utils.mapper.OutputMapper;

@Entity
@Table(name = "m_SubCategory")
public class SubCategoryDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "SubCategoryID")
	private Integer subCategoryID;
    
	/*
	@Expose
	@Column(name = "CategoryID")
	private Integer categoryID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CategoryID", insertable = false, updatable = false)
	@JsonIgnore
	@Expose
	private CategoryDetails categoryDetails; */

	@Expose
	@Column(name = "SubCategoryName")
	private String subCategoryName;
	
	/*
	@Expose
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;

	@Column(name = "CreatedBy")
	@Expose
	private String createdBy;

	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Timestamp createdDate;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Timestamp lastModDate; */

	
	/**
	 * default constructor
	 */
	public SubCategoryDetails() {
	}

	/**
	 * Cosntructor with parameters
	 * 
	 * @param SubCategoryID
	 * @param SubCategoryName
	 */
	public SubCategoryDetails(Integer SubCategoryID, String SubCategoryName) {

		this.subCategoryID = SubCategoryID;
		this.subCategoryName = SubCategoryName;
		
		
	}

	/*
	public SubCategoryDetails(Integer SubCategoryID, String SubCategoryName,
			CategoryDetails category) throws IEMRException {
		this.subCategoryID = SubCategoryID;
		this.subCategoryName = SubCategoryName;		
		this.categoryDetails = InputMapper.gson().fromJson(category.toString(), CategoryDetails.class);
		this.categoryID = categoryDetails.getCategoryID();
	}

	public SubCategoryDetails(String SubCategoryName) {

		
		this.subCategoryName = SubCategoryName;
		
	} */

	/**
	 * @return the subCategoryID
	 */
	public Integer getSubCategoryID() {
		return subCategoryID;
	}

	/**
	 * @param subCategoryID
	 *            the subCategoryID to set
	 */
	public void setSubCategoryID(Integer subCategoryID) {
		this.subCategoryID = subCategoryID;
	}

	
	/**
	 * @return the subCategoryName
	 */
	public String getSubCategoryName() {
		return subCategoryName;
	}

	/**
	 * @param subCategoryName
	 *            the subCategoryName to set
	 */
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	
	

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}
}
