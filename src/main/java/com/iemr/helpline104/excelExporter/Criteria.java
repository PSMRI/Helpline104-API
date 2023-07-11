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
package com.iemr.helpline104.excelExporter;

public class Criteria {
	String Start_Date;
	String End_Date;
	String Service;
	String Role_Id;
	String Agent_Id;
	String Call_Type_Name;
	String Call_Type_Id;
	

	public String getService() {
		return Service;
	}

	public void setService(String service) {
		Service = service;
	}

	public String getRole_Id() {
		return Role_Id;
	}

	public void setRole_Id(String role_Id) {
		Role_Id = role_Id;
	}

	public String getAgent_Id() {
		return Agent_Id;
	}

	public void setAgent_Id(String agent_Id) {
		Agent_Id = agent_Id;
	}

	public String getCall_Type_Name() {
		return Call_Type_Name;
	}

	public void setCall_Type_Name(String call_Type_Name) {
		Call_Type_Name = call_Type_Name;
	}

	public String getCall_Type_Id() {
		return Call_Type_Id;
	}

	public void setCall_Type_Id(String call_Type_Id) {
		Call_Type_Id = call_Type_Id;
	}

	public String getStart_Date() {
		return Start_Date;
	}

	public void setStart_Date(String start_Date) {
		Start_Date = start_Date;
	}

	public String getEnd_Date() {
		return End_Date;
	}

	public void setEnd_Date(String end_Date) {
		End_Date = end_Date;
	}
	

}
