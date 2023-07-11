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
package com.iemr.helpline104.service.report;

import java.util.List;

import com.iemr.helpline104.data.report.BeneficiaryReport;
import com.iemr.helpline104.data.report.BenmedhistoryReport;
import com.iemr.helpline104.data.report.BloodrequestReport;
import com.iemr.helpline104.data.report.COReport;
import com.iemr.helpline104.data.report.DirectoryserviceReport;
import com.iemr.helpline104.data.report.EpidemicoutbreakReport;
import com.iemr.helpline104.data.report.FeedbackReport;
import com.iemr.helpline104.data.report.FoodsafetyReport;
import com.iemr.helpline104.data.report.MOReport;
import com.iemr.helpline104.data.report.MedicalHistory;
import com.iemr.helpline104.data.report.OrgandonationReport;
import com.iemr.helpline104.data.report.PrescriptionReport;
import com.iemr.helpline104.data.report.SchemeserviceReport;

public interface CRMReportsService {	

	List<EpidemicoutbreakReport> getEpidemicoutbreakReport(String jsonRequest) throws Exception;
	List<BeneficiaryReport> getRegistrationReport(String jsonRequest) throws Exception;
	List<MedicalHistory> getHAOSummaryReport(String jsonRequest) throws Exception;
	List<MOReport> getMOSummaryReport(String jsonRequest) throws Exception;
	List<COReport> getCOSummaryReport(String jsonRequest) throws Exception;
	List<BenmedhistoryReport> getPDSummaryReport(String jsonRequest) throws Exception;
	List<BloodrequestReport> getBloodRequestReport(String jsonRequest) throws Exception;
	List<DirectoryserviceReport> getDirectoryserviceReport(String jsonRequest) throws Exception;
	List<OrgandonationReport> getOrgandonationReport(String jsonRequest) throws Exception;
	List<SchemeserviceReport> getSchemeServiceReport(String jsonRequest) throws Exception;
	List<FeedbackReport> getFeedbackReport(String jsonRequest) throws Exception;
	List<FoodsafetyReport> getFoodsafetyReport(String jsonRequest) throws Exception;
	List<PrescriptionReport> getPrescriptionReport(String jsonRequest) throws Exception;
	String getBloodOnCallCountReportByDate(String request) throws Exception;
	String getMentalHealthReport(String request) throws Exception;
	String getMedicalAdviseReport(String request) throws Exception;
	String getCDIResponseReport(String request) throws Exception;
}
