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
package com.iemr.helpline104.repository.report;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.report.BeneficiaryReport;

@Repository
@RestResource(exported = false)
public interface BeneficiaryReportRepository extends CrudRepository<BeneficiaryReport, Long>
{
	/*@Query("select b.beneficiaryID, b.title,  b.firstName, b.lastName, b.gender, b.maritalStatus, b.dob, b.spouseName, b.emergencyRegistration, b.healthCareWorker, b.education, "
			+ "b.occupation, b.community, b.emailId, b.permVillage, b.permSubDistrict, b.permDistrict, "
			+ "c.callTypeName, c.callSubTypeName, c.phoneNo, c.callReceivedUserID, c.callEndUserID " + "from bencallReport c "
			+ " LEFT JOIN c.BeneficiaryReport b " + "where c.providerServiceMapID=:providerServiceMapID " + "and c.isCalledEarlier = 0 "
			+ "and c.lodadedDate > :startDateTime " + "and c.lodadedDate < :endDateTime " + "order by c.lodadedDate desc")
	public List<Objects[]> getROSummaryReportByDate(
			@Param("providerServiceMapID") Integer providerServiceMapID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime);*/

}
