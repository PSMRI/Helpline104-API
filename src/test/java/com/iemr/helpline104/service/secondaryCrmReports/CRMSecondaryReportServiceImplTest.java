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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.secondary.repo.report.SecondaryReportRepo;
import com.iemr.helpline104.utils.exception.IEMRException;

/**
 * Every secondary report reads its rows from one native query and hands them to
 * the Excel exporter, and reports "No data found" when the query comes back
 * empty. Both halves of that contract are driven for each report.
 */
@ExtendWith(MockitoExtension.class)
class CRMSecondaryReportServiceImplTest {

	private static final String WINDOW = "{\"startDateTime\":1700000000000,\"endDateTime\":1700086400000,"
			+ "\"agentID\":\"agent-7\",\"providerServiceMapID\":1";

	@Mock
	private SecondaryReportRepo secondaryReportRepo;

	@InjectMocks
	private CRMSecondaryReportServiceImpl secondaryReportService;

	/**
	 * A row carrying a date, an unset column and a number: the exporter formats
	 * each of the three differently.
	 */
	private static List<Object[]> rows() {
		return Collections.singletonList(new Object[] { "2024-01-31", null, 7 });
	}

	private static List<Object[]> noRows() {
		return Collections.emptyList();
	}

	private static String request(String... extraFields) {
		return WINDOW + String.join("", Arrays.stream(extraFields).map(f -> "," + f).toList()) + "}";
	}

	private static void assertIsWorkbook(ByteArrayInputStream response) {
		assertNotNull(response);
		assertTrue(response.available() > 0, "the exported workbook must carry bytes");
	}

	@Test
	void getRegistrationReportExportsTheRegistrationRows() throws Exception {
		when(secondaryReportRepo.getRegisterationReport(any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getRegistrationReport(request(), "registration"));
	}

	@Test
	void getRegistrationReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getRegisterationReport(any(), any(), any(), any())).thenReturn(noRows());

		IEMRException thrown = assertThrows(IEMRException.class,
				() -> secondaryReportService.getRegistrationReport(request(), "registration"));

		assertTrue(thrown.getMessage().contains("No data found"));
	}

	@Test
	void getHAOReportExportsTheHaoRows() throws Exception {
		when(secondaryReportRepo.getHAOReport(any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getHAOReport(request(), "hao"));
	}

	@Test
	void getHAOReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getHAOReport(any(), any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class, () -> secondaryReportService.getHAOReport(request(), "hao"));
	}

	@Test
	void getMOReportExportsTheMoRows() throws Exception {
		when(secondaryReportRepo.getMOReport(any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getMOReport(request(), "mo"));
	}

	@Test
	void getMOReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getMOReport(any(), any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class, () -> secondaryReportService.getMOReport(request(), "mo"));
	}

	@Test
	void getMODetailsReportExportsTheDiseaseCounts() throws Exception {
		when(secondaryReportRepo.getMODetailsReport(any(), any(), any(), any(), any(), any(), any(), any()))
				.thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getMODetailsReport(request(), "mo-details"));
	}

	@Test
	void getMODetailsReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getMODetailsReport(any(), any(), any(), any(), any(), any(), any(), any()))
				.thenReturn(noRows());

		assertThrows(IEMRException.class, () -> secondaryReportService.getMODetailsReport(request(), "mo-details"));
	}

	@Test
	void getCOReportExportsTheCoRows() throws Exception {
		when(secondaryReportRepo.getCOReport(any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getCOReport(request(), "co"));
	}

	@Test
	void getCOReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getCOReport(any(), any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class, () -> secondaryReportService.getCOReport(request(), "co"));
	}

	@Test
	void getMentalHealthReportExportsTheGuidelineCounts() throws Exception {
		when(secondaryReportRepo.getGuidelinesReport(any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService
				.getMentalHealthReport(request("\"searchCriteria\":\"Guidelines\""), "mental-health"));
	}

	@Test
	void getMentalHealthReportExportsTheCategoryCountsForAnyOtherCriteria() throws Exception {
		when(secondaryReportRepo.getCategoryReport(any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService
				.getMentalHealthReport(request("\"searchCriteria\":\"Category\""), "mental-health"));
	}

	@Test
	void getMentalHealthReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getCategoryReport(any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class, () -> secondaryReportService
				.getMentalHealthReport(request("\"searchCriteria\":\"Category\""), "mental-health"));
	}

	@Test
	void getGrievanceReportExportsTheGrievanceRows() throws Exception {
		when(secondaryReportRepo.getGrievanceReport(any(), any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getGrievanceReport(request("\"feedbackTypeID\":2"), "grievance"));
	}

	@Test
	void getGrievanceReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getGrievanceReport(any(), any(), any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class, () -> secondaryReportService.getGrievanceReport(request(), "grievance"));
	}

	@Test
	void getPrescriptionReportExportsThePrescriptionRows() throws Exception {
		when(secondaryReportRepo.getPrescriptionReport(any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getPrescriptionReport(request(), "prescription"));
	}

	@Test
	void getPrescriptionReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getPrescriptionReport(any(), any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class, () -> secondaryReportService.getPrescriptionReport(request(), "prescription"));
	}

	@Test
	void getSchemeServiceReportExportsTheSchemeRows() throws Exception {
		when(secondaryReportRepo.getSchemeServiceReport(any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getSchemeServiceReport(request(), "scheme"));
	}

	@Test
	void getSchemeServiceReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getSchemeServiceReport(any(), any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class, () -> secondaryReportService.getSchemeServiceReport(request(), "scheme"));
	}

	@Test
	void getFoodSafetyReportExportsTheComplaintRows() throws Exception {
		when(secondaryReportRepo.getFoodSafetyReport(any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getFoodSafetyReport(request(), "food-safety"));
	}

	@Test
	void getFoodSafetyReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getFoodSafetyReport(any(), any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class, () -> secondaryReportService.getFoodSafetyReport(request(), "food-safety"));
	}

	@Test
	void getDirectoryserviceReportExportsTheDirectoryRows() throws Exception {
		when(secondaryReportRepo.getDirectoryserviceReport(any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getDirectoryserviceReport(request(), "directory"));
	}

	@Test
	void getDirectoryserviceReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getDirectoryserviceReport(any(), any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class, () -> secondaryReportService.getDirectoryserviceReport(request(), "directory"));
	}

	@Test
	void getCDIResponseReportExportsTheResponseRows() throws Exception {
		when(secondaryReportRepo.getCDIResponseReport(any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getCDIResponseReport(request(), "cdi"));
	}

	@Test
	void getCDIResponseReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getCDIResponseReport(any(), any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class, () -> secondaryReportService.getCDIResponseReport(request(), "cdi"));
	}

	@Test
	void getBloodRequestReportExportsTheRequestRows() throws Exception {
		when(secondaryReportRepo.getBloodRequestReport(any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getBloodRequestReport(request(), "blood-request"));
	}

	@Test
	void getBloodRequestReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getBloodRequestReport(any(), any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class,
				() -> secondaryReportService.getBloodRequestReport(request(), "blood-request"));
	}

	@Test
	void getBloodOnCallCountReportExportsTheComponentCounts() throws Exception {
		when(secondaryReportRepo.getComponentReport(any(), any(), any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService
				.getBloodOnCallCountReportByDate(request("\"searchCriteria\":\"Component\""), "blood-on-call"));
	}

	@Test
	void getBloodOnCallCountReportExportsTheBloodGroupCounts() throws Exception {
		when(secondaryReportRepo.getGroupReport(any(), any(), any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService
				.getBloodOnCallCountReportByDate(request("\"searchCriteria\":\"Group\""), "blood-on-call"));
	}

	@Test
	void getBloodOnCallCountReportExportsTheDistrictComponentCounts() throws Exception {
		when(secondaryReportRepo.getDistrictComponentReport(any(), any(), any(), any(), any(), any()))
				.thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getBloodOnCallCountReportByDate(
				request("\"searchCriteria\":\"District Wise Component\""), "blood-on-call"));
	}

	@Test
	void getBloodOnCallCountReportExportsTheDistrictBloodGroupCounts() throws Exception {
		when(secondaryReportRepo.getDistrictGroupReport(any(), any(), any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getBloodOnCallCountReportByDate(
				request("\"searchCriteria\":\"District Wise Group\""), "blood-on-call"));
	}

	@Test
	void getBloodOnCallCountReportReportsAnUnknownSearchCriteriaAsNoData() {
		assertThrows(IEMRException.class, () -> secondaryReportService
				.getBloodOnCallCountReportByDate(request("\"searchCriteria\":\"unsupported\""), "blood-on-call"));
	}

	@Test
	void getEpidemicoutbreakReportExportsTheOutbreakRows() throws Exception {
		when(secondaryReportRepo.getEpidemicoutbreakReport(any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getEpidemicoutbreakReport(request(), "epidemic"));
	}

	@Test
	void getEpidemicoutbreakReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getEpidemicoutbreakReport(any(), any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class, () -> secondaryReportService.getEpidemicoutbreakReport(request(), "epidemic"));
	}

	@Test
	void getOrgandonationReportExportsTheDonationRows() throws Exception {
		when(secondaryReportRepo.getOrgandonationReport(any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getOrgandonationReport(request(), "organ-donation"));
	}

	@Test
	void getOrgandonationReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getOrgandonationReport(any(), any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class,
				() -> secondaryReportService.getOrgandonationReport(request(), "organ-donation"));
	}

	@Test
	void getPDSummaryReportExportsThePdRows() throws Exception {
		when(secondaryReportRepo.getPDSummaryReport(any(), any(), any(), any())).thenReturn(rows());

		assertIsWorkbook(secondaryReportService.getPDSummaryReport(request(), "pd"));
	}

	@Test
	void getPDSummaryReportReportsAnEmptyWindow() {
		when(secondaryReportRepo.getPDSummaryReport(any(), any(), any(), any())).thenReturn(noRows());

		assertThrows(IEMRException.class, () -> secondaryReportService.getPDSummaryReport(request(), "pd"));
	}
}
