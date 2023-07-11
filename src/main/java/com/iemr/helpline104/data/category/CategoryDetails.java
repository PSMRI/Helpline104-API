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
@Table(name = "m_Category")
public class CategoryDetails
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "CategoryID")
	private Integer categoryID;

	@Expose
	@Column(name = "CategoryName")
	private String categoryName;

	/*
	 * @Expose
	 * 
	 * @Column(name = "Deleted", insertable = false, updatable = true) private Boolean deleted;
	 * 
	 * @Column(name = "CreatedBy")
	 * 
	 * @Expose private String createdBy;
	 * 
	 * @Column(name = "CreatedDate", insertable = false, updatable = false) private Timestamp createdDate;
	 * 
	 * @Column(name = "ModifiedBy") private String modifiedBy;
	 * 
	 * @Column(name = "LastModDate", insertable = false, updatable = false) private Timestamp lastModDate;
	 */

	@Column(name = "104_CS_Type", insertable = false, updatable = false)
	@Expose
	private Boolean isWellBeing;

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryDetails")
	 * 
	 * @Transient
	 * 
	 * @JsonIgnore private Set<SubCategoryDetails> subCategories;
	 */

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	public Integer getCategoryID()
	{
		return categoryID;
	}

	public void setCategoryID(Integer categoryID)
	{
		this.categoryID = categoryID;
	}

	public String getCategoryName()
	{
		return this.categoryName;
	}

	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}
	
	public CategoryDetails()
	{
		
	}

	public CategoryDetails(Integer CategoryID, String CategoryName)
	{
		this.categoryID = CategoryID;
		this.categoryName = CategoryName;
	}

	/*
	 * public CategoryDetails(Integer CategoryID, String CategoryName, Boolean isWellBeing) { this.categoryID =
	 * CategoryID; this.categoryName = CategoryName; this.isWellBeing = isWellBeing; }
	 */

	@Override
	public String toString()
	{
		return outputMapper.gson().toJson(this);
	}

	public Boolean getIsWellBeing()
	{

		return isWellBeing;
	}
}
