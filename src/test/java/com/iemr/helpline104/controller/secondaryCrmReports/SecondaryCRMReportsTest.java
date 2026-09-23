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
package com.iemr.helpline104.controller.secondaryCrmReports;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.iemr.helpline104.service.secondaryCrmReports.CRMSecondaryReportService;
import com.iemr.helpline104.utils.exception.IEMRException;

/**
 * Each endpoint streams one exported workbook back as an attachment, answers an
 * empty reporting window with 500 and any other failure with the legacy 5000
 * status. The attachment is named after the request when it carries a fileName,
 * and after the report otherwise.
 */
@ExtendWith(MockitoExtension.class)
class SecondaryCRMReportsTest {

	private static final String REQUEST = "{\"providerServiceMapID\":1}";
	private static final String NO_DATA = "No data found";

	@Mock
	private CRMSecondaryReportService secondaryReportService;

	@InjectMocks
	private SecondaryCRMReports controller;

	private static ByteArrayInputStream workbook() {
		return new ByteArrayInputStream("workbook".getBytes(StandardCharsets.UTF_8));
	}

	private static void assertIsAttachmentNamed(ResponseEntity<Object> response, String filename) {
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		assertEquals("attachment; filename=" + filename + ".xlsx",
				response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION));
	}

	@Test
	void getROSummaryReportByDateStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getRegistrationReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getROSummaryReportByDate(REQUEST), "Registration_Service");
	}

	@Test
	void getROSummaryReportByDateReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getRegistrationReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getROSummaryReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getROSummaryReportByDateReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getRegistrationReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getROSummaryReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getHAOSummaryReportByDateStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getHAOReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getHAOSummaryReportByDate(REQUEST), "Health_Advisory_Service");
	}

	@Test
	void getHAOSummaryReportByDateReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getHAOReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getHAOSummaryReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getHAOSummaryReportByDateReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getHAOReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getHAOSummaryReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getMOSummaryReportByDateStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getMOReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getMOSummaryReportByDate(REQUEST), "Medical_Services");
	}

	@Test
	void getMOSummaryReportByDateReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getMOReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getMOSummaryReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getMOSummaryReportByDateReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getMOReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getMOSummaryReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getMedicalAdviseReportStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getMODetailsReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getMedicalAdviseReport(REQUEST), "Medical_Services_Detail");
	}

	@Test
	void getMedicalAdviseReportReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getMODetailsReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getMedicalAdviseReport(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getMedicalAdviseReportReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getMODetailsReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getMedicalAdviseReport(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getCOSummaryReportByDateStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getCOReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getCOSummaryReportByDate(REQUEST), "Counselling_Service");
	}

	@Test
	void getCOSummaryReportByDateReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getCOReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getCOSummaryReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getCOSummaryReportByDateReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getCOReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getCOSummaryReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getMentalHealthReportStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getMentalHealthReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getMentalHealthReport(REQUEST), "Counselling_Service_Detail");
	}

	@Test
	void getMentalHealthReportReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getMentalHealthReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getMentalHealthReport(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getMentalHealthReportReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getMentalHealthReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getMentalHealthReport(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getGrievanceReportByDateStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getGrievanceReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getGrievanceReportByDate(REQUEST), "Grievance");
	}

	@Test
	void getGrievanceReportByDateReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getGrievanceReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getGrievanceReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getGrievanceReportByDateReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getGrievanceReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getGrievanceReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getPrescriptionReportByDateStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getPrescriptionReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getPrescriptionReportByDate(REQUEST), "Prescription");
	}

	@Test
	void getPrescriptionReportByDateReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getPrescriptionReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getPrescriptionReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getPrescriptionReportByDateReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getPrescriptionReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getPrescriptionReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getCDIResponseReportStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getCDIResponseReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getCDIResponseReport(REQUEST), "Surveyor");
	}

	@Test
	void getCDIResponseReportReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getCDIResponseReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getCDIResponseReport(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getCDIResponseReportReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getCDIResponseReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getCDIResponseReport(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getSchemesReportByDateStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getSchemeServiceReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getSchemesReportByDate(REQUEST), "Health_Schemes");
	}

	@Test
	void getSchemesReportByDateReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getSchemeServiceReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getSchemesReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getSchemesReportByDateReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getSchemeServiceReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getSchemesReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getDirectoryServiceReportByDateStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getDirectoryserviceReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getDirectoryServiceReportByDate(REQUEST), "Directory_Services");
	}

	@Test
	void getDirectoryServiceReportByDateReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getDirectoryserviceReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getDirectoryServiceReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getDirectoryServiceReportByDateReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getDirectoryserviceReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getDirectoryServiceReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getFoodSafetyReportByDateStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getFoodSafetyReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getFoodSafetyReportByDate(REQUEST), "Food_Safety");
	}

	@Test
	void getFoodSafetyReportByDateReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getFoodSafetyReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getFoodSafetyReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getFoodSafetyReportByDateReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getFoodSafetyReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getFoodSafetyReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getBloodOnCallReportByDateStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getBloodRequestReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getBloodOnCallReportByDate(REQUEST), "Blood_Request");
	}

	@Test
	void getBloodOnCallReportByDateReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getBloodRequestReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getBloodOnCallReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getBloodOnCallReportByDateReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getBloodRequestReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getBloodOnCallReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getBloodOnCallCountReportByDateStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getBloodOnCallCountReportByDate(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getBloodOnCallCountReportByDate(REQUEST), "Blood_Request_Detail");
	}

	@Test
	void getBloodOnCallCountReportByDateReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getBloodOnCallCountReportByDate(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getBloodOnCallCountReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getBloodOnCallCountReportByDateReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getBloodOnCallCountReportByDate(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getBloodOnCallCountReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getEpidemicReportByDateStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getEpidemicoutbreakReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getEpidemicReportByDate(REQUEST), "Epidemic_Outbreak_Service");
	}

	@Test
	void getEpidemicReportByDateReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getEpidemicoutbreakReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getEpidemicReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getEpidemicReportByDateReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getEpidemicoutbreakReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getEpidemicReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getOrganDonationReportByDateStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getOrgandonationReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getOrganDonationReportByDate(REQUEST), "Organ_Donation");
	}

	@Test
	void getOrganDonationReportByDateReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getOrgandonationReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getOrganDonationReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getOrganDonationReportByDateReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getOrgandonationReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getOrganDonationReportByDate(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void getPDSummaryReportStreamsTheExportedWorkbook() throws Exception {
		when(secondaryReportService.getPDSummaryReport(any(), any())).thenReturn(workbook());

		assertIsAttachmentNamed(controller.getPDSummaryReport(REQUEST), "Psychiatrist");
	}

	@Test
	void getPDSummaryReportReportsAnEmptyWindowAsAServerError() throws Exception {
		when(secondaryReportService.getPDSummaryReport(any(), any())).thenThrow(new IEMRException(NO_DATA));

		ResponseEntity<Object> response = controller.getPDSummaryReport(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals(NO_DATA, response.getBody());
	}

	@Test
	void getPDSummaryReportReportsAnUnexpectedFailureAsAServerError() throws Exception {
		when(secondaryReportService.getPDSummaryReport(any(), any())).thenThrow(new RuntimeException("db down"));

		ResponseEntity<Object> response = controller.getPDSummaryReport(REQUEST);

		assertEquals(500, response.getStatusCode().value());
		assertEquals("db down", response.getBody());
	}

	@Test
	void theAttachmentIsNamedAfterTheRequestedFileName() throws Exception {
		when(secondaryReportService.getRegistrationReport(any(), any())).thenReturn(workbook());

		ResponseEntity<Object> response = controller
				.getROSummaryReportByDate("{\"providerServiceMapID\":1,\"fileName\":\"my-export\"}");

		assertIsAttachmentNamed(response, "my-export");
	}

	@Test
	void getFileNameFallsBackToTheReportNameWithoutARequestedFileName() {
		assertEquals("Registration_Service", controller.getFileName(REQUEST, "Registration_Service"));
	}

	@Test
	void getFileNameReadsTheRequestedFileName() {
		assertEquals("my-export",
				controller.getFileName("{\"fileName\":\"my-export\"}", "Registration_Service"));
	}

	@Test
	void theExportedWorkbookIsStreamedBackToTheCaller() throws Exception {
		when(secondaryReportService.getRegistrationReport(any(), any())).thenReturn(workbook());

		ResponseEntity<Object> response = controller.getROSummaryReportByDate(REQUEST);

		assertTrue(response.getHeaders().getContentType().toString().contains("ms-excel"));
	}
}
