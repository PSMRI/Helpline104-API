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
package com.iemr.helpline104.controller.organDonation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.organDonation.M_DonatableOrgan;
import com.iemr.helpline104.data.organDonation.M_DonationType;
import com.iemr.helpline104.data.organDonation.T_OrganDonation;
import com.iemr.helpline104.service.organDonation.OrganDonationService;

/**
 * The organ donation endpoints wrap the service answer in an OutputResponse,
 * and report a failure as the error the response carries.
 */
@ExtendWith(MockitoExtension.class)
class OrganDonationControllerTest {

	private static final String REQUEST = "{\"beneficiaryRegID\":12,\"benCallID\":4,"
			+ "\"requestID\":\"OD/2/01012024/9\"}";

	@Mock
	private OrganDonationService organDonationService;

	@InjectMocks
	private OrganDonationController controller;

	@Test
	void saveOrganDonationDetailsReturnsSuccess() throws Exception {
		when(organDonationService.save(any())).thenReturn("[]");

		assertTrue(controller.saveOrganDonationDetails(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void saveOrganDonationDetailsReportsAFailure() throws Exception {
		when(organDonationService.save(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveOrganDonationDetails(REQUEST).contains("db down"));
	}

	@Test
	void updateOrganDonationDetailsReturnsSuccess() throws Exception {
		when(organDonationService.update(any())).thenReturn("{}");

		assertTrue(controller.updateOrganDonationDetails(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void updateOrganDonationDetailsReportsAFailure() throws Exception {
		when(organDonationService.update(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.updateOrganDonationDetails(REQUEST).contains("db down"));
	}

	@Test
	void saveOrganDonationInstituteDetailsReturnsSuccess() throws Exception {
		when(organDonationService.saveInstituteDetails(any())).thenReturn("{}");

		assertTrue(controller.saveOrganDonationInstituteDetails(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void saveOrganDonationInstituteDetailsReportsAFailure() throws Exception {
		when(organDonationService.saveInstituteDetails(any())).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.saveOrganDonationInstituteDetails(REQUEST).contains("db down"));
	}

	@Test
	void getOrganDonationDetailsReturnsSuccess() throws Exception {
		when(organDonationService.getOrganDonationRequests(any(), any(), any()))
				.thenReturn(Collections.singletonList(new T_OrganDonation()));

		assertTrue(controller.getOrganDonationDetails(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getOrganDonationDetailsReportsNoRequestFound() throws Exception {
		when(organDonationService.getOrganDonationRequests(any(), any(), any()))
				.thenReturn(Collections.emptyList());

		assertTrue(controller.getOrganDonationDetails(REQUEST).contains("\"statusCode\":200"));
	}

	@Test
	void getOrganDonationDetailsReportsAFailure() throws Exception {
		when(organDonationService.getOrganDonationRequests(any(), any(), any()))
				.thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getOrganDonationDetails(REQUEST).contains("db down"));
	}

	@Test
	void getOrganDonationTypesReturnsSuccess() throws Exception {
		when(organDonationService.getDonationTypes()).thenReturn(Collections.singletonList(new M_DonationType()));

		assertTrue(controller.getOrganDonationTypes().contains("\"statusCode\":200"));
	}

	@Test
	void getOrganDonationTypesReportsAFailure() throws Exception {
		when(organDonationService.getDonationTypes()).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getOrganDonationTypes().contains("db down"));
	}

	@Test
	void getDonatableOrgansReturnsSuccess() throws Exception {
		when(organDonationService.getDonatableOrgans())
				.thenReturn(Collections.singletonList(new M_DonatableOrgan()));

		assertTrue(controller.getDonatableOrgans().contains("\"statusCode\":200"));
	}

	@Test
	void getDonatableOrgansReportsAFailure() throws Exception {
		when(organDonationService.getDonatableOrgans()).thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getDonatableOrgans().contains("db down"));
	}
}
