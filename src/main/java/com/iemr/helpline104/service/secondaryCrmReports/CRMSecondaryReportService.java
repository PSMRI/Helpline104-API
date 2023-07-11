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
package com.iemr.helpline104.service.secondaryCrmReports;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public interface CRMSecondaryReportService {
	ByteArrayInputStream getRegistrationReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getHAOReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getMOReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getMODetailsReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getCOReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getMentalHealthReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getGrievanceReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getPrescriptionReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getCDIResponseReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getSchemeServiceReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getDirectoryserviceReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getBloodRequestReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getBloodOnCallCountReportByDate(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getEpidemicoutbreakReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getOrgandonationReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getFoodSafetyReport(String jsonRequest, String filename) throws Exception;
	ByteArrayInputStream getPDSummaryReport(String jsonRequest, String filename) throws Exception;
}
