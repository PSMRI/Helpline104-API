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
package com.iemr.helpline104.data;

import org.junit.jupiter.api.Test;

import com.iemr.helpline104.data.report.BenCDIResponseReport;
import com.iemr.helpline104.data.report.BencallReport;
import com.iemr.helpline104.data.report.BeneficiaryReport;
import com.iemr.helpline104.data.report.BenmedhistoryReport;
import com.iemr.helpline104.data.report.BloodCompDistrictModel;
import com.iemr.helpline104.data.report.BloodGroupCountReportModel;
import com.iemr.helpline104.data.report.BloodGroupDistrictModel;
import com.iemr.helpline104.data.report.BloodOnRequestCountModel;
import com.iemr.helpline104.data.report.BloodrequestReport;
import com.iemr.helpline104.data.report.COReport;
import com.iemr.helpline104.data.report.DirectoryserviceReport;
import com.iemr.helpline104.data.report.EpidemicoutbreakReport;
import com.iemr.helpline104.data.report.FeedbackReport;
import com.iemr.helpline104.data.report.FoodsafetyReport;
import com.iemr.helpline104.data.report.MOReport;
import com.iemr.helpline104.data.report.MedicalAdviseDiseaseReport;
import com.iemr.helpline104.data.report.MedicalHistory;
import com.iemr.helpline104.data.report.MentalHealthCategory;
import com.iemr.helpline104.data.report.MentalHealthGuidlineReport;
import com.iemr.helpline104.data.report.OrgandonationReport;
import com.iemr.helpline104.data.report.PrescriptionReport;
import com.iemr.helpline104.data.report.SchemeserviceReport;
import com.iemr.helpline104.data.report.UserReport;
import com.iemr.helpline104.data.report.UserServiceRoleReport;
import com.iemr.helpline104.data.report.WorkLocation;

/**
 * Set/get round trips and constructor drives for the Report entities.
 */
class ReportEntityAccessorsTest {

	@Test
	void everyAccessorRoundTripsAndEveryConstructorBuilds() {
		EntityAccessors.assertEntities(
				BenCDIResponseReport.class,
				BencallReport.class,
				BeneficiaryReport.class,
				BenmedhistoryReport.class,
				BloodCompDistrictModel.class,
				BloodGroupCountReportModel.class,
				BloodGroupDistrictModel.class,
				BloodOnRequestCountModel.class,
				BloodrequestReport.class,
				COReport.class,
				DirectoryserviceReport.class,
				EpidemicoutbreakReport.class,
				FeedbackReport.class,
				FoodsafetyReport.class,
				MOReport.class,
				MedicalAdviseDiseaseReport.class,
				MedicalHistory.class,
				MentalHealthCategory.class,
				MentalHealthGuidlineReport.class,
				OrgandonationReport.class,
				PrescriptionReport.class,
				SchemeserviceReport.class,
				UserReport.class,
				UserServiceRoleReport.class,
				WorkLocation.class);
	}
}
