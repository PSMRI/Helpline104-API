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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="m_userqualification")
public class M_UserQualification
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer QualificationID;
  private String Name;
  private String UserQualificationDesc;
  private Boolean Deleted;
  private String CreatedBy;
  private Date CreatedDate;
  private String ModifiedBy;
  private Date LastModDate;
  
  public M_UserQualification() {}
  
  public M_UserQualification(int QualificationID, String Name)
  {
    this.QualificationID = Integer.valueOf(QualificationID);
    this.Name = Name;
  }
}
