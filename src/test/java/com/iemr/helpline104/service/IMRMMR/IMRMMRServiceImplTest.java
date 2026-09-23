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
package com.iemr.helpline104.service.IMRMMR;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iemr.helpline104.data.IMRMMR.IMRMMR;
import com.iemr.helpline104.repository.epidemicOutbreak.EpidemicOutbreakRepository;
import com.iemr.helpline104.repository.location.LocationDistrictRepository;
import com.iemr.helpline104.repository.nodalOfficer.NodalOficerRepo;
import com.iemr.helpline104.reposotory.IMRMMR.IMRMMRRepository;
import com.iemr.helpline104.utils.CookieUtil;

/**
 * Saving a death registration stores the request twice - once to mint the
 * registration ID from the generated key - and then notifies every nodal
 * officer of the victim's taluk by SMS and email. The gateways are driven
 * through a stubbed rest call so that no HTTP is attempted here.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class IMRMMRServiceImplTest {

	/** The column types the worklist projection reads, in order. */
	private static final String WORKLIST_ROW = "LIISSSSISISTILSSSSIIISIISSSSIIISSSSSSZSSIIS";

	private static final String AUTHORIZATION = "Bearer token";
	private static final String GATEWAY_ACCEPTED = "{\"statusCode\":200}";

	@Mock
	private IMRMMRRepository imrmmrRepository;

	@Mock
	private NodalOficerRepo nodalOficerRepo;

	@Mock
	private LocationDistrictRepository locationDistrictRepository;

	@Mock
	private EpidemicOutbreakRepository epidemicOutbreakRepository;

	@Mock
	private CookieUtil cookieUtil;

	@InjectMocks
	private IMRMMRServiceImpl imrmmrService;

	private IMRMMRServiceImpl service;

	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(imrmmrService, "IMRMMRSmsTemplate", "IMRMMR");
		ReflectionTestUtils.setField(imrmmrService, "sendSMSUrl", "http://localhost/sms");
		ReflectionTestUtils.setField(imrmmrService, "sendEmailGeneralUrl", "http://localhost/email");
		ReflectionTestUtils.setField(imrmmrService, "IMRMMREmailTemplate", "IMRMMR");
		service = spy(imrmmrService);
		doReturn(GATEWAY_ACCEPTED).when(service).restTemplate(anyString(), anyString(), any());
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
	}

	@AfterEach
	void tearDown() {
		RequestContextHolder.resetRequestAttributes();
	}

	private static Object[] row(String columnTypes) {
		Object[] columns = new Object[columnTypes.length()];
		for (int i = 0; i < columnTypes.length(); i++) {
			switch (columnTypes.charAt(i)) {
				case 'L' -> columns[i] = (long) i;
				case 'I' -> columns[i] = i;
				case 'Z' -> columns[i] = Boolean.TRUE;
				case 'T' -> columns[i] = new Timestamp(1_700_000_000_000L);
				default -> columns[i] = "column-" + i;
			}
		}
		return columns;
	}

	/** A pair of master rows, one of them skipped by the projection. */
	private static ArrayList<Object[]> masterRows() {
		return new ArrayList<>(Arrays.asList(null, new Object[0], new Object[] { 4, "value" }));
	}

	private static IMRMMR saved() {
		IMRMMR saved = new IMRMMR();
		saved.setBenImrMmrID(77L);
		saved.setBeneficiaryRegID(12);
		saved.setVictimDistrict(9);
		saved.setVictimTaluk(3);
		saved.setReferenceDate(new Timestamp(1_700_000_000_000L));
		saved.setCreatedBy("agent");
		saved.setInformerName("informer");
		saved.setRequestID("MDSR-CDR/9/01012024/77");
		return saved;
	}

	private static String saveRequest(String extraFields) {
		return "{\"victimName\":\"Asha\",\"victimDistrict\":9,\"victimTaluk\":3,"
				+ "\"referenceDate\":\"2023-11-14T22:13:20.000\",\"beneficiaryRegID\":12," + extraFields
				+ "\"stagesOfDeath\":{\"duringPregnancy\":\"YES\",\"duringDelivery\":\"NO\","
				+ "\"within42daysOfDelivery\":\"NO\",\"above42daysOfDelivery\":\"NO\",\"noofDelivery\":\"2\"}}";
	}

	@Test
	void saveIMRMMRMintsTheRegistrationIdAndNotifiesTheNodalOfficers() throws Exception {
		when(imrmmrRepository.save(any())).thenReturn(saved());
		when(nodalOficerRepo.findByTalukID(3)).thenReturn(Collections
				.singletonList(new Object[] { "9999999999", "officer@example.org" }));
		when(locationDistrictRepository.findStateByDistrictID(9)).thenReturn(5);
		when(imrmmrRepository.getSMSTypeID(anyString())).thenReturn(2);
		when(imrmmrRepository.getSMSTemplateID(2)).thenReturn(8);

		String response = service.saveIMRMMR(
				saveRequest("\"supportServicesName\":[\"Police\",\"Ambulance\"],\"supportServicesID\":[\"1\",\"2\"],"),
				AUTHORIZATION);

		assertTrue(response.startsWith("MDSR/CDR Data Saved Successfully And Death Registration ID: MDSR-CDR/9/"));
		assertTrue(response.endsWith("/77"));
		verify(service, times(2)).restTemplate(anyString(), anyString(), any());
	}

	@Test
	void saveIMRMMRLeavesTheSupportServicesUnsetWhenNoneAreChosen() throws Exception {
		when(imrmmrRepository.save(any())).thenReturn(saved());

		assertTrue(service.saveIMRMMR(saveRequest(""), AUTHORIZATION).contains("Saved Successfully"));
		verify(nodalOficerRepo).findByTalukID(3);
	}

	@Test
	void saveIMRMMRSkipsTheDeliveryCountWhenItIsNotReported() throws Exception {
		when(imrmmrRepository.save(any())).thenReturn(saved());

		String request = "{\"victimName\":\"Asha\",\"victimDistrict\":9,\"victimTaluk\":3,"
				+ "\"referenceDate\":\"2023-11-14T22:13:20.000\",\"beneficiaryRegID\":12,"
				+ "\"stagesOfDeath\":{\"duringPregnancy\":\"YES\"}}";

		assertTrue(service.saveIMRMMR(request, AUTHORIZATION).contains("Saved Successfully"));
	}

	@Test
	void saveIMRMMRSkipsTheNotificationsWithoutANodalOfficer() throws Exception {
		when(imrmmrRepository.save(any())).thenReturn(saved());
		when(nodalOficerRepo.findByTalukID(3)).thenReturn(null);

		assertTrue(service.saveIMRMMR(saveRequest(""), AUTHORIZATION).contains("Saved Successfully"));
		verify(service, never()).restTemplate(anyString(), anyString(), any());
	}

	@Test
	void saveIMRMMRSkipsANodalOfficerWithoutAContact() throws Exception {
		when(imrmmrRepository.save(any())).thenReturn(saved());
		when(nodalOficerRepo.findByTalukID(3))
				.thenReturn(Collections.singletonList(new Object[] { null, null }));

		assertTrue(service.saveIMRMMR(saveRequest(""), AUTHORIZATION).contains("Saved Successfully"));
		verify(service, never()).restTemplate(anyString(), anyString(), any());
	}

	@Test
	void saveIMRMMRReportsAFailedInsert() {
		IMRMMR unsaved = saved();
		unsaved.setBenImrMmrID(0L);
		when(imrmmrRepository.save(any())).thenReturn(unsaved);

		Exception thrown = assertThrows(Exception.class, () -> service.saveIMRMMR(saveRequest(""), AUTHORIZATION));

		assertTrue(thrown.getMessage().contains("error in saving IMR/MMR data"));
	}

	@Test
	void createSMSRequestCarriesTheTemplateAndTheNodalNumber() {
		when(imrmmrRepository.getSMSTypeID("IMRMMR")).thenReturn(2);
		when(imrmmrRepository.getSMSTemplateID(2)).thenReturn(8);

		String request = service.createSMSRequest("IMRMMR", 12L, 5, "9999999999", "MDSR-CDR/9/01012024/77",
				new Timestamp(1_700_000_000_000L), "agent", "informer");

		assertTrue(request.contains("9999999999"));
		assertTrue(request.contains("\"smsTemplateID\":8"));
	}

	@Test
	void createSMSRequestIsEmptyForAnUnknownTemplate() {
		when(imrmmrRepository.getSMSTypeID("IMRMMR")).thenReturn(0);

		assertEquals("[]", service.createSMSRequest("IMRMMR", 12L, 5, "9999999999", "req",
				new Timestamp(1_700_000_000_000L), "agent", "informer"));
	}

	@Test
	void createEmailGatewaySwallowsAGatewayFailure() throws Exception {
		doReturn(null).when(service).restTemplate(anyString(), anyString(), any());

		service.createEmailGateway("officer@example.org", "req", AUTHORIZATION);

		verify(service).restTemplate(anyString(), anyString(), any());
	}

	@Test
	void createSmsGatewayReportsARejectedSms() {
		doReturn("{\"statusCode\":500}").when(service).restTemplate(anyString(), anyString(), any());
		when(imrmmrRepository.getSMSTypeID(anyString())).thenReturn(2);

		service.createSmsGateway(12L, new Timestamp(1_700_000_000_000L), 5, "9999999999", "req", AUTHORIZATION,
				"agent", "informer");

		verify(service).restTemplate(anyString(), anyString(), any());
	}

	@Test
	void getsupportServicesProjectsTheMasterRows() throws Exception {
		when(imrmmrRepository.findsupportServices()).thenReturn(masterRows());

		assertEquals(1, service.getsupportServices().size());
	}

	@Test
	void getFacilitiesProjectsTheMasterRows() throws Exception {
		when(imrmmrRepository.findfacilities()).thenReturn(masterRows());

		assertEquals(1, service.getFacilities().size());
	}

	@Test
	void getBaseCommunitiesProjectsTheMasterRows() throws Exception {
		when(imrmmrRepository.findBaseCommunities()).thenReturn(masterRows());

		assertEquals(1, service.getBaseCommunities().size());
	}

	@Test
	void getTransitTypeProjectsTheMasterRows() throws Exception {
		when(imrmmrRepository.findTransitType()).thenReturn(masterRows());

		assertEquals(1, service.getTransitType().size());
	}

	@Test
	void getHealthWorkerProjectsTheMasterRows() throws Exception {
		when(imrmmrRepository.findHealthWorker()).thenReturn(masterRows());

		assertEquals(1, service.getHealthWorker().size());
	}

	@Test
	void getWorklistRequestsSearchesByBeneficiary() {
		when(imrmmrRepository.findByBeneficiaryID(12))
				.thenReturn(Arrays.asList(null, new Object[0], row(WORKLIST_ROW)));

		assertTrue(service.getWorklistRequests(12, null, null).contains("column-3"));
	}

	@Test
	void getWorklistRequestsSearchesByPhoneNumber() {
		when(imrmmrRepository.findByPhoneNum("9999999999"))
				.thenReturn(new ArrayList<>(Collections.singletonList(BigInteger.valueOf(12L))));
		when(imrmmrRepository.findByBeneficiaryRegIDs(any()))
				.thenReturn(Collections.singletonList(row(WORKLIST_ROW)));

		assertTrue(service.getWorklistRequests(null, "9999999999", null).contains("column-3"));
	}

	@Test
	void getWorklistRequestsIsEmptyForAnUnknownPhoneNumber() {
		when(imrmmrRepository.findByPhoneNum("9999999999")).thenReturn(new ArrayList<>());

		assertEquals("[]", service.getWorklistRequests(null, "9999999999", null));
	}

	@Test
	void getWorklistRequestsSearchesByTheTrailingRegistrationId() {
		when(imrmmrRepository.findByRequestID(77L)).thenReturn(Collections.singletonList(row(WORKLIST_ROW)));

		assertTrue(service.getWorklistRequests(null, null, "MDSR-CDR/9/01012024/77").contains("column-3"));
	}

	@Test
	void getWorklistRequestsIsEmptyWithoutAnySearchTerm() {
		assertEquals("[]", service.getWorklistRequests(null, null, null));
	}

	@Test
	void updateImrMmrRequestConfirmsADeath() throws Exception {
		IMRMMR stored = saved();
		when(imrmmrRepository.searchByRequestID("req")).thenReturn(stored);
		when(imrmmrRepository.save(stored)).thenReturn(stored);
		IMRMMR update = new IMRMMR();
		update.setRequestID("req");
		update.setDeathConfirmedUI("YES");

		assertEquals("Data updated successfully", service.updateImrMmrRequest(update));
		assertTrue(stored.getDeathConfirmed());
	}

	@Test
	void updateImrMmrRequestRecordsAnUnconfirmedDeath() throws Exception {
		IMRMMR stored = saved();
		when(imrmmrRepository.searchByRequestID("req")).thenReturn(stored);
		when(imrmmrRepository.save(stored)).thenReturn(stored);
		IMRMMR update = new IMRMMR();
		update.setRequestID("req");
		update.setDeathConfirmedUI("NO");

		assertEquals("Data updated successfully", service.updateImrMmrRequest(update));
		assertEquals(Boolean.FALSE, stored.getDeathConfirmed());
	}

	@Test
	void updateImrMmrRequestReturnsNothingWhenTheSaveComesBackAsAnotherRecord() throws Exception {
		IMRMMR stored = saved();
		IMRMMR other = saved();
		other.setBenImrMmrID(78L);
		when(imrmmrRepository.searchByRequestID("req")).thenReturn(stored);
		when(imrmmrRepository.save(stored)).thenReturn(other);
		IMRMMR update = new IMRMMR();
		update.setRequestID("req");
		update.setDeathConfirmedUI("YES");

		assertNull(service.updateImrMmrRequest(update));
	}

	@Test
	void updateImrMmrRequestRejectsAnUnknownRequestId() {
		when(imrmmrRepository.searchByRequestID("req")).thenReturn(null);
		IMRMMR update = new IMRMMR();
		update.setRequestID("req");

		Exception thrown = assertThrows(Exception.class, () -> service.updateImrMmrRequest(update));

		assertTrue(thrown.getMessage().contains("No record found"));
	}
}
