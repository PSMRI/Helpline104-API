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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.repository.report.BencallReportRepository;
import com.iemr.helpline104.repository.report.BenmedhistoryReportRepository;
import com.iemr.helpline104.repository.report.BloodRequestReportRepository;
import com.iemr.helpline104.repository.report.DirectoryServiceReportRepository;
import com.iemr.helpline104.repository.report.EpidemicoutbreakReportRepository;
import com.iemr.helpline104.repository.report.FeedbackReportRepository;
import com.iemr.helpline104.repository.report.FoodSafetyReportRepository;
import com.iemr.helpline104.repository.report.OrgandonationReportRepository;
import com.iemr.helpline104.repository.report.PrescriptionReportRepository;
import com.iemr.helpline104.repository.report.SchemesReportRepository;
import com.iemr.helpline104.utils.mapper.OutputMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;

/**
 * The report service turns native query rows into report entities. Each report
 * is driven with a row of unset columns - every column is nullable in the
 * reports - so that the whole projection is exercised, and with the request
 * shapes that pick a different query.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CRMReportsServiceImplTest {

	/**
	 * The column types each report projection reads, in order: L a Long, S a
	 * String, I an Integer, T a Timestamp, B a Byte, Z a Boolean. A report row
	 * has to carry the right types because the report entities cast every column
	 * and format the timestamps as they are built.
	 */
	private static final String REGISTRATION_ROW = "LSSSSSTSBSSSSSSSSTSSSIISS";
	private static final String HAO_ROW = "LSLLSISSSSSSSSLSSBTLSSSSTSSSSSSIISSZSSSZZS";
	private static final String MO_ROW = "LSLLSISSSSSSSSLSSBTLSSSSTSSSSSSIISSSSSIZSSSZZS";
	private static final String CO_ROW = "LSLLSISSSSSSSSLSSBTLSSSSTSSSSSSIISSSS";
	private static final String PD_ROW = "LSLLSISSSSSSSSLSSBTLSSSSTSSSSSSIISS";
	private static final String EPIDEMIC_ROW = "LSLLSISSSSTLSSSSTSSSSSSIISS";
	private static final String BLOOD_REQUEST_ROW = "LSLLLSISSSSSSSZTSSSSSSBTLSSSSTSSSSSSIISS";
	private static final String DIRECTORY_ROW = "LSLLSSISSSSSSTLSSSSTSSSSSSIISS";
	private static final String ORGAN_ROW = "LSLLSISSSSSSSSSBTLSSSSTSSSSSSIISS";
	private static final String SCHEME_ROW = "LSLLISSSTLSSSSTSSSSSSIISS";
	private static final String FEEDBACK_ROW = "LSLLSSSSSSSSSSSSSISTTLSSSSTSSSSSSIISS";
	private static final String FOODSAFETY_ROW = "LSLLSBBBBBBBTSBSSSSSZSISTLSSSSTSSSSSSIISS";
	private static final String PRESCRIPTION_ROW = "LLLISSSSSSSSISSTTLSSSSTSSSSSSIISS";
	private static final String CDI_ROW = "SSILSSSSTSSSSSSSS";

	private static final String BY_DATE = "{\"providerServiceMapID\":1}";
	private static final String BY_AGENT = "{\"providerServiceMapID\":1,\"agentID\":\"agent-7\"}";

	@Mock
	private EpidemicoutbreakReportRepository epidemicoutbreakReportRepository;

	@Mock
	private BencallReportRepository bencallReportReportRepository;

	@Mock
	private BenmedhistoryReportRepository benmedhistoryReportRepository;

	@Mock
	private BloodRequestReportRepository bloodRequestReportRepository;

	@Mock
	private DirectoryServiceReportRepository directoryServiceReportRepository;

	@Mock
	private OrgandonationReportRepository organdonationReportRepository;

	@Mock
	private SchemesReportRepository schemesReportRepository;

	@Mock
	private FeedbackReportRepository feedbackReportRepository;

	@Mock
	private FoodSafetyReportRepository foodSafetyReportRepository;

	@Mock
	private PrescriptionReportRepository prescriptionReportRepository;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private EntityManager entityManager;

	@Mock
	private TypedQuery<Object> typedQuery;

	@InjectMocks
	private CRMReportsServiceImpl reportsService;

	@BeforeEach
	void setUp() {
		new OutputMapper();
		ReflectionTestUtils.setField(reportsService, "entityManager", entityManager);
		doReturn(typedQuery).when(entityManager).createQuery(any(CriteriaQuery.class));
		when(typedQuery.getResultList()).thenReturn(Collections.emptyList());
	}

	/** Builds one query row of the given column types. */
	private static Object[] row(String columnTypes) {
		Object[] columns = new Object[columnTypes.length()];
		for (int i = 0; i < columnTypes.length(); i++) {
			switch (columnTypes.charAt(i)) {
				case 'L' -> columns[i] = (long) i;
				case 'I' -> columns[i] = i;
				case 'B' -> columns[i] = (byte) i;
				case 'Z' -> columns[i] = Boolean.TRUE;
				case 'T' -> columns[i] = new Timestamp(1_700_000_000_000L);
				default -> columns[i] = "column-" + i;
			}
		}
		return columns;
	}

	private static List<Object[]> oneRow(String columnTypes) {
		return Collections.singletonList(row(columnTypes));
	}

	/** A null row and an empty row - both skipped - next to one the report reads. */
	private static List<Object[]> rowsIncludingSkipped(String columnTypes) {
		return Arrays.asList(null, new Object[0], row(columnTypes));
	}

	@Test
	void getRegistrationReportReadsTheDateQueryWithoutAnAgent() throws Exception {
		when(bencallReportReportRepository.getROSummaryReportByDate(any(), any(), any())).thenReturn(oneRow(REGISTRATION_ROW));

		assertEquals(1, reportsService.getRegistrationReport(BY_DATE).size());
		verify(bencallReportReportRepository).getROSummaryReportByDate(any(), any(), any());
	}

	@Test
	void getRegistrationReportReadsTheAgentQueryWhenAnAgentIsGiven() throws Exception {
		when(bencallReportReportRepository.getROSummaryReportByAgentIDAndDate(any(), any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(REGISTRATION_ROW));

		assertEquals(1, reportsService.getRegistrationReport(BY_AGENT).size());
	}

	@Test
	void getHAOSummaryReportReadsTheDateQueryWithoutAnAgent() throws Exception {
		when(benmedhistoryReportRepository.getHAOSummaryReportByDate(any(), any(), any())).thenReturn(oneRow(HAO_ROW));

		assertEquals(1, reportsService.getHAOSummaryReport(BY_DATE).size());
	}

	@Test
	void getHAOSummaryReportReadsTheAgentQueryWhenAnAgentIsGiven() throws Exception {
		when(benmedhistoryReportRepository.getHAOSummaryReportByAgentIDAndDate(any(), any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(HAO_ROW));

		assertEquals(1, reportsService.getHAOSummaryReport(BY_AGENT).size());
	}

	@Test
	void getMOSummaryReportReadsTheDateQueryWithoutAnAgent() throws Exception {
		when(benmedhistoryReportRepository.getMOSummaryReportByDate(any(), any(), any())).thenReturn(oneRow(MO_ROW));

		assertEquals(1, reportsService.getMOSummaryReport(BY_DATE).size());
	}

	@Test
	void getMOSummaryReportReadsTheAgentQueryWhenAnAgentIsGiven() throws Exception {
		when(benmedhistoryReportRepository.getMOSummaryReportByAgentIDAndDate(any(), any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(MO_ROW));

		assertEquals(1, reportsService.getMOSummaryReport(BY_AGENT).size());
	}

	@Test
	void getCOSummaryReportReadsTheDateQueryWithoutAnAgent() throws Exception {
		when(benmedhistoryReportRepository.getCOSummaryReportByDate(any(), any(), any())).thenReturn(oneRow(CO_ROW));

		assertEquals(1, reportsService.getCOSummaryReport(BY_DATE).size());
	}

	@Test
	void getCOSummaryReportReadsTheAgentQueryWhenAnAgentIsGiven() throws Exception {
		when(benmedhistoryReportRepository.getCOSummaryReportByAgentIDAndDate(any(), any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(CO_ROW));

		assertEquals(1, reportsService.getCOSummaryReport(BY_AGENT).size());
	}

	@Test
	void getPDSummaryReportReadsTheDateQueryWithoutAnAgent() throws Exception {
		when(benmedhistoryReportRepository.getPDSummaryReportByDate(any(), any(), any())).thenReturn(oneRow(PD_ROW));

		assertEquals(1, reportsService.getPDSummaryReport(BY_DATE).size());
	}

	@Test
	void getPDSummaryReportReadsTheAgentQueryWhenAnAgentIsGiven() throws Exception {
		when(benmedhistoryReportRepository.getPDSummaryReportByAgentIDAndDate(any(), any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(PD_ROW));

		assertEquals(1, reportsService.getPDSummaryReport(BY_AGENT).size());
	}

	@Test
	void getEpidemicoutbreakReportProjectsEveryRow() throws Exception {
		when(epidemicoutbreakReportRepository.getEpidemicoutbreakReportByDate(any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(EPIDEMIC_ROW));

		assertEquals(1, reportsService.getEpidemicoutbreakReport(BY_DATE).size());
	}

	@Test
	void getBloodRequestReportProjectsEveryRow() throws Exception {
		when(bloodRequestReportRepository.getBloodRequestReportByDate(any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(BLOOD_REQUEST_ROW));

		assertEquals(1, reportsService.getBloodRequestReport(BY_DATE).size());
	}

	@Test
	void getDirectoryserviceReportProjectsEveryRow() throws Exception {
		when(directoryServiceReportRepository.getDirectoryServiceReportByDate(any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(DIRECTORY_ROW));

		assertEquals(1, reportsService.getDirectoryserviceReport(BY_DATE).size());
	}

	@Test
	void getOrgandonationReportProjectsEveryRow() throws Exception {
		when(organdonationReportRepository.getOrgandonationReportByDate(any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(ORGAN_ROW));

		assertEquals(1, reportsService.getOrgandonationReport(BY_DATE).size());
	}

	@Test
	void getSchemeServiceReportProjectsEveryRow() throws Exception {
		when(schemesReportRepository.getSchemeServiceReportByDate(any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(SCHEME_ROW));

		assertEquals(1, reportsService.getSchemeServiceReport(BY_DATE).size());
	}

	@Test
	void getFoodsafetyReportProjectsEveryRow() throws Exception {
		when(foodSafetyReportRepository.getFoodsafetyReportByDate(any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(FOODSAFETY_ROW));

		assertEquals(1, reportsService.getFoodsafetyReport(BY_DATE).size());
	}

	@Test
	void getPrescriptionReportProjectsEveryRow() throws Exception {
		when(prescriptionReportRepository.getPrescriptionReportByDate(any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(PRESCRIPTION_ROW));

		assertEquals(1, reportsService.getPrescriptionReport(BY_DATE).size());
	}

	@Test
	void getFeedbackReportReadsTheDateQueryWithoutATypeOrAnAgent() throws Exception {
		when(feedbackReportRepository.getFeedbackReportByDate(any(), any(), any())).thenReturn(oneRow(FEEDBACK_ROW));

		assertEquals(1, reportsService.getFeedbackReport(BY_DATE).size());
	}

	@Test
	void getFeedbackReportReadsTheTypeQueryForATypeWithoutAnAgent() throws Exception {
		when(feedbackReportRepository.getFeedbackReportByTypeAndDate(any(), any(), any(), any())).thenReturn(oneRow(FEEDBACK_ROW));

		assertEquals(1, reportsService.getFeedbackReport("{\"providerServiceMapID\":1,\"feedbackTypeID\":2}").size());
	}

	@Test
	void getFeedbackReportReadsTheAgentQueryForAnAgentWithoutAType() throws Exception {
		when(feedbackReportRepository.getFeedbackReportByAgentIDAndDate(any(), any(), any(), any()))
				.thenReturn(oneRow(FEEDBACK_ROW));

		assertEquals(1, reportsService.getFeedbackReport(BY_AGENT).size());
	}

	@Test
	void getFeedbackReportReadsTheAgentAndTypeQueryWhenBothAreGiven() throws Exception {
		when(feedbackReportRepository.getFeedbackReportByAgendIDTypeAndDate(any(), any(), any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(FEEDBACK_ROW));

		assertEquals(1, reportsService
				.getFeedbackReport("{\"providerServiceMapID\":1,\"agentID\":\"agent-7\",\"feedbackTypeID\":2}").size());
	}

	@Test
	void getBloodOnCallCountReportCountsTheThreeComponentTypes() throws Exception {
		String report = reportsService
				.getBloodOnCallCountReportByDate("{\"providerServiceMapID\":1,\"searchCriteria\":\"Component\"}");

		assertTrue(report.contains("WBC"));
	}

	@Test
	void getBloodOnCallCountReportCountsTheFourBloodGroups() throws Exception {
		String report = reportsService
				.getBloodOnCallCountReportByDate("{\"providerServiceMapID\":1,\"searchCriteria\":\"Group\"}");

		assertTrue(report.contains("A+ve"));
		assertTrue(report.contains("B-ve"));
	}

	@Test
	void getBloodOnCallCountReportBreaksComponentsDownByDistrict() throws Exception {
		when(bloodRequestReportRepository.getStateByProvider(any())).thenReturn(5);
		when(bloodRequestReportRepository.getDistrictByStateID(any()))
				.thenReturn(Collections.singletonList(new Districts(9, "Pune")));

		String report = reportsService.getBloodOnCallCountReportByDate(
				"{\"providerServiceMapID\":1,\"searchCriteria\":\"District Wise Component\"}");

		assertTrue(report.contains("Pune"));
	}

	@Test
	void getBloodOnCallCountReportBreaksBloodGroupsDownByDistrict() throws Exception {
		when(bloodRequestReportRepository.getStateByProvider(any())).thenReturn(5);
		when(bloodRequestReportRepository.getDistrictByStateID(any()))
				.thenReturn(Collections.singletonList(new Districts(9, "Pune")));

		String report = reportsService.getBloodOnCallCountReportByDate(
				"{\"providerServiceMapID\":1,\"searchCriteria\":\"District Wise Group\"}");

		assertTrue(report.contains("Pune"));
	}

	@Test
	void getBloodOnCallCountReportFiltersOnTheRequestedLocation() throws Exception {
		String report = reportsService.getBloodOnCallCountReportByDate(
				"{\"providerServiceMapID\":1,\"searchCriteria\":\"Component\",\"districtID\":3,"
						+ "\"subDistrictID\":4,\"villageID\":5}");

		assertTrue(report.contains("WBC"));
	}

	@Test
	void getBloodOnCallCountReportEchoesAnUnknownSearchCriteria() throws Exception {
		String request = "{\"providerServiceMapID\":1,\"searchCriteria\":\"unsupported\"}";

		assertEquals(request, reportsService.getBloodOnCallCountReportByDate(request));
	}

	@Test
	void getMentalHealthReportCountsGuidelines() throws Exception {
		when(benmedhistoryReportRepository.getMentalHealthByGuidelines(any(), any(), any()))
				.thenReturn(Arrays.asList(null, new Object[0], new Object[] { 4L, "psychosis" }));

		String report = reportsService
				.getMentalHealthReport("{\"providerServiceMapID\":1,\"searchCriteria\":\"Guidelines\"}");

		assertTrue(report.contains("psychosis"));
	}

	@Test
	void getMentalHealthReportCountsCategoriesForAnyOtherCriteria() throws Exception {
		when(benmedhistoryReportRepository.getMentalHealthByCategory(any(), any(), any()))
				.thenReturn(Arrays.asList(null, new Object[0], new Object[] { 4L, "anxiety" }));

		String report = reportsService
				.getMentalHealthReport("{\"providerServiceMapID\":1,\"searchCriteria\":\"Category\"}");

		assertTrue(report.contains("anxiety"));
	}

	@Test
	void getMedicalAdviseReportGroupsTheDiseaseSummary() throws Exception {
		when(typedQuery.getResultList())
				.thenReturn(Collections.singletonList(new Object[] { "fever", 12L }));

		String report = reportsService.getMedicalAdviseReport("{\"providerServiceMapID\":1}");

		assertTrue(report.contains("fever"));
	}

	@Test
	void getMedicalAdviseReportFiltersOnEveryRequestedDimension() throws Exception {
		when(typedQuery.getResultList())
				.thenReturn(Collections.singletonList(new Object[] { "fever", 12L }));

		String report = reportsService.getMedicalAdviseReport("{\"providerServiceMapID\":1,\"districtID\":3,"
				+ "\"subDistrictID\":4,\"villageID\":5,\"roleID\":6,\"locationID\":7}");

		assertTrue(report.contains("fever"));
	}

	@Test
	void getCDIResponseReportReadsTheAgentQueryWhenAnAgentIsGiven() throws Exception {
		when(bloodRequestReportRepository.getCDIResponseReportByAgentID(any(), any(), any(), any()))
				.thenReturn(oneRow(CDI_ROW));

		assertNotNull(reportsService.getCDIResponseReport(BY_AGENT));
		verify(bloodRequestReportRepository).getCDIResponseReportByAgentID(any(), any(), any(), any());
	}

	@Test
	void getCDIResponseReportReadsTheDateQueryWithoutAnAgent() throws Exception {
		when(bloodRequestReportRepository.getCDIResponseReport(any(), any(), any()))
				.thenReturn(rowsIncludingSkipped(CDI_ROW));

		assertNotNull(reportsService.getCDIResponseReport(BY_DATE));
		verify(bloodRequestReportRepository).getCDIResponseReport(any(), any(), any());
	}
}
