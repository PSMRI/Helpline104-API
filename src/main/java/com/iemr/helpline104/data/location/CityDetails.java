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
package com.iemr.helpline104.data.location;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name="m_city")
public class CityDetails {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer cityID;
	  private String cityName;
	  private Integer districtID;
	  private Integer stateID;
	  private Boolean deleted;
	  private String createdBy;
	  private Date createdDate;
	  private String modifiedBy;
	  private Date lastModDate;
	  
	  public CityDetails() {}
	  
	  public CityDetails(Integer CityID, String CityName)
	  {
	    this.cityID = CityID;
	    this.cityName = CityName;
	  }
	  
	  public Integer getCityID()
	  {
	    return this.cityID;
	  }
	  
	  public void setCityID(int cityID)
	  {
	    this.cityID = Integer.valueOf(cityID);
	  }
	  
	  public String getCityName()
	  {
	    return this.cityName;
	  }
	  
	  public void setCityName(String cityName)
	  {
	    this.cityName = cityName;
	  }
	  
	  public Integer getDistrictID()
	  {
	    return this.districtID;
	  }
	  
	  public void setDistrictID(int districtID)
	  {
	    this.districtID = Integer.valueOf(districtID);
	  }
	  
	  public Integer getStateID()
	  {
	    return this.stateID;
	  }
	  
	  public void setStateID(int stateID)
	  {
	    this.stateID = Integer.valueOf(stateID);
	  }
	  
	  public Boolean isDeleted()
	  {
	    return this.deleted;
	  }
	  
	  public void setDeleted(boolean deleted)
	  {
	    this.deleted = Boolean.valueOf(deleted);
	  }
	  
	  public String getCreatedBy()
	  {
	    return this.createdBy;
	  }
	  
	  public void setCreatedBy(String createdBy)
	  {
	    this.createdBy = createdBy;
	  }
	  
	  public Date getCreatedDate()
	  {
	    return this.createdDate;
	  }
	  
	  public void setCreatedDate(Date createdDate)
	  {
	    this.createdDate = createdDate;
	  }
	  
	  public String getModifiedBy()
	  {
	    return this.modifiedBy;
	  }
	  
	  public void setModifiedBy(String modifiedBy)
	  {
	    this.modifiedBy = modifiedBy;
	  }
	  
	  public Date getLastModDate()
	  {
	    return this.lastModDate;
	  }
	  
	  public void setLastModDate(Date lastModDate)
	  {
	    this.lastModDate = lastModDate;
	  }
	  
	  public String toString()
	  {
	    return new Gson().toJson(this);
	  }
}
