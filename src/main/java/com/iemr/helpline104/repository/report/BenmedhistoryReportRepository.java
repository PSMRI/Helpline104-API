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

import com.iemr.helpline104.data.report.BenmedhistoryReport;

@Repository
@RestResource(exported = false)
public interface BenmedhistoryReportRepository extends CrudRepository<BenmedhistoryReport, Long>
{

	@Query("select t.benHistoryID, t.requestID, t.beneficiaryRegID, t.benCallID, t.patientName, t.patientAge, t.patientGenderName, t.algorithm, "
			+ "t.diseaseSummaryID, t.diseaseSummary, t.allergies, t.selecteDiagnosisID, t.selecteDiagnosis, t.addedAdvice, t.prescriptionID, t.actionByHAO, t.remarks, t.isSelf, t.createdDate, b.beneficiaryID, b.firstName, b.lastName, b.healthCareWorker, b.gender, b.dob, b.permVillage, b.permSubDistrict, b.permDistrict, "
			+ "c.callTypeName, c.callSubTypeName, c.phoneNo, c.callReceivedUserID, c.callEndUserID, c.agentID, user.firstName,"
			+ "t.travel_14days,t.travel_type,"
			+ "t.symptoms,t.COVID19_contact_history,t.medical_consultation,"
			+ "t.Suspected_COVID19,t.recommendation " 
			+ "from MedicalHistory t "
			+ "LEFT JOIN t.beneficiaryReport b " + " LEFT JOIN t.bencallReport c "
			+ "left join c.userReportObj user "			
			+ "where t.providerServiceMapID=:providerServiceMapID "			 
			+ "and t.actionByHAO IS NOT NULL " + "and t.createdDate > :startDateTime " + "and t.createdDate < :endDateTime "
			+ "order by t.createdDate desc")
	public List<Object[]> getHAOSummaryReportByDate(
			@Param("providerServiceMapID") Integer providerServiceMapID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime);
	
	@Query("select t.benHistoryID, t.requestID, t.beneficiaryRegID, t.benCallID, t.patientName, t.patientAge, t.patientGenderName, t.algorithm, "
			+ "t.diseaseSummaryID, t.diseaseSummary, t.allergies, t.selecteDiagnosisID, t.selecteDiagnosis, t.addedAdvice, t.prescriptionID, t.actionByHAO, t.remarks, t.isSelf, t.createdDate, b.beneficiaryID, b.firstName, b.lastName, b.healthCareWorker, b.gender, b.dob, b.permVillage, b.permSubDistrict, b.permDistrict, "
			+ "c.callTypeName, c.callSubTypeName, c.phoneNo, c.callReceivedUserID, c.callEndUserID, c.agentID, user.firstName, "
			+ "t.travel_14days,t.travel_type,"
			+ "t.symptoms,t.COVID19_contact_history,t.medical_consultation,"
			+ "t.Suspected_COVID19,t.recommendation " 
			+ "from MedicalHistory t "
			+ "LEFT JOIN t.beneficiaryReport b " + " LEFT JOIN t.bencallReport c "
			+ "left join c.userReportObj user "			
			+ "where t.providerServiceMapID=:providerServiceMapID " + "and c.agentID =:agentID "			 
			+ "and t.actionByHAO IS NOT NULL " + "and t.createdDate > :startDateTime " + "and t.createdDate < :endDateTime "
			+ "order by t.createdDate desc")
	public List<Object[]> getHAOSummaryReportByAgentIDAndDate(
			@Param("providerServiceMapID") Integer providerServiceMapID,  @Param("agentID") String agentID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime);
	

	@Query("select t.benHistoryID, t.requestID, t.beneficiaryRegID, t.benCallID, t.patientName, t.patientAge, t.patientGenderName, t.algorithm, "
			+ "t.diseaseSummaryID, t.diseaseSummary, t.allergies, t.selecteDiagnosisID, t.selecteDiagnosis, t.addedAdvice, t.prescriptionID, t.actionByMO, t.remarks, t.isSelf, t.createdDate, b.beneficiaryID, b.firstName, b.lastName, b.healthCareWorker, b.gender, b.dob, b.permVillage, b.permSubDistrict, b.permDistrict, "
			+ "c.callTypeName, c.callSubTypeName, c.phoneNo, c.callReceivedUserID, c.callEndUserID, c.agentID, user.firstName,"
			+ "prescriptionReport.drugName,prescriptionReport.frequency, prescriptionReport.dosage, prescriptionReport.noOfDays, "
			+ "t.travel_14days,t.travel_type,"
			+ "t.symptoms,t.COVID19_contact_history,t.medical_consultation,"
			+ "t.Suspected_COVID19,t.recommendation " 
			+ "from MedicalHistory t "
			+ "LEFT JOIN t.beneficiaryReport b " + " LEFT JOIN t.bencallReport c "
			+ "left join c.userReportObj user "
			+ "left join t.prescriptionReport prescriptionReport "			
			+ "where t.providerServiceMapID=:providerServiceMapID "			 
			+ "and t.actionByMO IS NOT NULL " + "and t.createdDate > :startDateTime " + "and t.createdDate < :endDateTime "
			+ "order by t.createdDate desc")
	public List<Object[]> getMOSummaryReportByDate(
			@Param("providerServiceMapID") Integer providerServiceMapID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime);
	
	@Query("select t.benHistoryID, t.requestID, t.beneficiaryRegID, t.benCallID, t.patientName, t.patientAge, t.patientGenderName, t.algorithm, "
			+ "t.diseaseSummaryID, t.diseaseSummary, t.allergies, t.selecteDiagnosisID, t.selecteDiagnosis, t.addedAdvice, t.prescriptionID, t.actionByMO, t.remarks, t.isSelf, t.createdDate, b.beneficiaryID, b.firstName, b.lastName, b.healthCareWorker, b.gender, b.dob, b.permVillage, b.permSubDistrict, b.permDistrict, "
			+ "c.callTypeName, c.callSubTypeName, c.phoneNo, c.callReceivedUserID, c.callEndUserID, c.agentID, user.firstName, "
			+ "prescriptionReport.drugName,prescriptionReport.frequency, prescriptionReport.dosage, prescriptionReport.noOfDays, "
			+ "t.travel_14days,t.travel_type,"
			+ "t.symptoms,t.COVID19_contact_history,t.medical_consultation,"
			+ "t.Suspected_COVID19,t.recommendation " 
			+ "from MedicalHistory t "
			+ "LEFT JOIN t.beneficiaryReport b " + " LEFT JOIN t.bencallReport c "
			+ "left join c.userReportObj user "		
			+ "left join t.prescriptionReport prescriptionReport "			
			+ "where t.providerServiceMapID=:providerServiceMapID "	+ "and c.agentID =:agentID "		 
			+ "and t.actionByMO IS NOT NULL " + "and t.createdDate > :startDateTime " + "and t.createdDate < :endDateTime "
			+ "order by t.createdDate desc")
	public List<Object[]> getMOSummaryReportByAgentIDAndDate(
			@Param("providerServiceMapID") Integer providerServiceMapID,  @Param("agentID") String agentID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime);
	
	
	
	@Query("select t.benHistoryID, t.requestID, t.beneficiaryRegID, t.benCallID, t.patientName, t.patientAge, t.patientGenderName, t.algorithm, "
			+ "t.diseaseSummaryID, t.diseaseSummary, t.allergies, t.selecteDiagnosisID, t.selecteDiagnosis, t.addedAdvice, t.prescriptionID, t.actionByCO, t.remarks, t.isSelf, t.createdDate, b.beneficiaryID, b.firstName, b.lastName, b.healthCareWorker, b.gender, b.dob, b.permVillage, b.permSubDistrict, b.permDistrict, "
			+ "c.callTypeName, c.callSubTypeName, c.phoneNo, c.callReceivedUserID, c.callEndUserID, t.category, t.subCategory, c.agentID, user.firstName "
			+ "from BenmedhistoryReport t "
			+ "LEFT JOIN t.beneficiaryReport b " + " LEFT JOIN t.bencallReport c "
			+ "left join c.userReportObj user "			
			+ "where t.providerServiceMapID=:providerServiceMapID "		 
			+ "and t.actionByCO IS NOT NULL " + "and t.createdDate > :startDateTime " + "and t.createdDate < :endDateTime "
			+ "order by t.createdDate desc")
	public List<Object[]> getCOSummaryReportByDate(
			@Param("providerServiceMapID") Integer providerServiceMapID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime);
	
	@Query("select t.benHistoryID, t.requestID, t.beneficiaryRegID, t.benCallID, t.patientName, t.patientAge, t.patientGenderName, t.algorithm, "
			+ "t.diseaseSummaryID, t.diseaseSummary, t.allergies, t.selecteDiagnosisID, t.selecteDiagnosis, t.addedAdvice, t.prescriptionID, t.actionByCO, t.remarks, t.isSelf, t.createdDate, b.beneficiaryID, b.firstName, b.lastName, b.healthCareWorker, b.gender, b.dob, b.permVillage, b.permSubDistrict, b.permDistrict, "
			+ "c.callTypeName, c.callSubTypeName, c.phoneNo, c.callReceivedUserID, c.callEndUserID, t.category, t.subCategory, c.agentID, user.firstName "
			+ "from BenmedhistoryReport t "
			+ "LEFT JOIN t.beneficiaryReport b " + " LEFT JOIN t.bencallReport c "
			+ "left join c.userReportObj user "			
			+ "where t.providerServiceMapID=:providerServiceMapID "	+ "and c.agentID =:agentID "	 
			+ "and t.actionByCO IS NOT NULL " + "and t.createdDate > :startDateTime " + "and t.createdDate < :endDateTime "
			+ "order by t.createdDate desc")
	public List<Object[]> getCOSummaryReportByAgentIDAndDate(
			@Param("providerServiceMapID") Integer providerServiceMapID,  @Param("agentID") String agentID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime);
	
	
	
	@Query("select t.benHistoryID, t.requestID, t.beneficiaryRegID, t.benCallID, t.patientName, t.patientAge, t.patientGenderName, t.algorithm, "
			+ "t.diseaseSummaryID, t.diseaseSummary, t.allergies, t.selecteDiagnosisID, t.selecteDiagnosis, t.addedAdvice, t.prescriptionID, t.actionByPD, t.remarks, t.isSelf, t.createdDate, b.beneficiaryID, b.firstName, b.lastName, b.healthCareWorker, b.gender, b.dob, b.permVillage, b.permSubDistrict, b.permDistrict, "
			+ "c.callTypeName, c.callSubTypeName, c.phoneNo, c.callReceivedUserID, c.callEndUserID, c.agentID, user.firstName "
			+ "from BenmedhistoryReport t "
			+ "LEFT JOIN t.beneficiaryReport b " + " LEFT JOIN t.bencallReport c "
			+ "left join c.userReportObj user "			
			+ "where t.providerServiceMapID=:providerServiceMapID "			 
			+ "and t.actionByPD IS NOT NULL " + "and t.createdDate > :startDateTime " + "and t.createdDate < :endDateTime "
			+ "order by t.createdDate desc")
	public List<Object[]> getPDSummaryReportByDate(
			@Param("providerServiceMapID") Integer providerServiceMapID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime);
	
	@Query("select t.benHistoryID, t.requestID, t.beneficiaryRegID, t.benCallID, t.patientName, t.patientAge, t.patientGenderName, t.algorithm, "
			+ "t.diseaseSummaryID, t.diseaseSummary, t.allergies, t.selecteDiagnosisID, t.selecteDiagnosis, t.addedAdvice, t.prescriptionID, t.actionByPD, t.remarks, t.isSelf, t.createdDate, b.beneficiaryID, b.firstName, b.lastName, b.healthCareWorker, b.gender, b.dob, b.permVillage, b.permSubDistrict, b.permDistrict, "
			+ "c.callTypeName, c.callSubTypeName, c.phoneNo, c.callReceivedUserID, c.callEndUserID, c.agentID, user.firstName "
			+ "from BenmedhistoryReport t "
			+ "LEFT JOIN t.beneficiaryReport b " + " LEFT JOIN t.bencallReport c "
			+ "left join c.userReportObj user "			
			+ "where t.providerServiceMapID=:providerServiceMapID "	+ "and c.agentID =:agentID "		 
			+ "and t.actionByPD IS NOT NULL " + "and t.createdDate > :startDateTime " + "and t.createdDate < :endDateTime "
			+ "order by t.createdDate desc")
	public List<Object[]> getPDSummaryReportByAgentIDAndDate(
			@Param("providerServiceMapID") Integer providerServiceMapID,  @Param("agentID") String agentID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime);
	
	@Query("select count(benmedhistoryReport.fact_104BenMedHistoryID), benmedhistoryReport.diseaseSummary from BenmedhistoryReport benmedhistoryReport "
			+ "where (benmedhistoryReport.actionByCO is not null or benmedhistoryReport.actionByPD is not null) and benmedhistoryReport.diseaseSummary is not null "
			+ "and benmedhistoryReport.createdDate >= :startDateTime and benmedhistoryReport.createdDate <= :endDateTime "
			+ "and benmedhistoryReport.providerServiceMapID = :providerServiceMapID "
			+ "group by benmedhistoryReport.diseaseSummary "
			+ "order by benmedhistoryReport.diseaseSummary asc")
	public List<Object[]> getMentalHealthByGuidelines(
			@Param("providerServiceMapID") Integer providerServiceMapID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime);
	
	@Query("select count(benmedhistoryReport.fact_104BenMedHistoryID), benmedhistoryReport.category from BenmedhistoryReport benmedhistoryReport "
			+ "where benmedhistoryReport.category is not null "
			+ "and benmedhistoryReport.createdDate >= :startDateTime and benmedhistoryReport.createdDate <= :endDateTime "
			+ "and benmedhistoryReport.providerServiceMapID = :providerServiceMapID "
			+ "group by benmedhistoryReport.category "
			+ "order by benmedhistoryReport.category asc")
	public List<Object[]> getMentalHealthByCategory(
			@Param("providerServiceMapID") Integer providerServiceMapID, @Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime);

}
