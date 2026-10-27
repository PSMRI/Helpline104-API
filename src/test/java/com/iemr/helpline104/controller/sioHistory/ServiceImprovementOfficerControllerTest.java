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
package com.iemr.helpline104.controller.sioHistory;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.helpline104.data.bloodRequest.T_BloodRequest;
import com.iemr.helpline104.service.bloodComponentType.BloodComponentTypeServiceImpl;
import com.iemr.helpline104.service.bloodRequest.BloodRequestServiceImpl;
import com.iemr.helpline104.service.directory.DirectoryServiceImpl;
import com.iemr.helpline104.service.epidemicOutbreak.EpidemicOutbreakServiceImpl;
import com.iemr.helpline104.service.foodSafetyCopmlaint.FoodSafetyCopmlaintServiceImpl;
import com.iemr.helpline104.service.organDonation.OrganDonationServiceImpl;

/**
 * The improvement officer's history endpoint gathers every service a
 * beneficiary used into one document, and reports a request without a
 * beneficiary as the error the response carries.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ServiceImprovementOfficerControllerTest {

	@Mock
	private BloodRequestServiceImpl bloodRequestServiceImpl;

	@Mock
	private BloodComponentTypeServiceImpl bloodComponentTypeServiceImpl;

	@Mock
	private EpidemicOutbreakServiceImpl epidemicOutbreakServiceImpl;

	@Mock
	private OrganDonationServiceImpl organDonationServiceImpl;

	@Mock
	private DirectoryServiceImpl directoryServiceImpl;

	@Mock
	private FoodSafetyCopmlaintServiceImpl foodSafetyCopmlaintServiceImpl;

	@InjectMocks
	private ServiceImprovementOfficerController controller;

	@Test
	void getSioHistoryGathersEveryServiceTheBeneficiaryUsed() throws Exception {
		when(bloodRequestServiceImpl.getBloodRequest(any(), any(), any()))
				.thenReturn(Collections.singletonList(new T_BloodRequest()));
		when(bloodComponentTypeServiceImpl.getBloodComponentTypes()).thenReturn(Collections.emptyList());
		when(bloodComponentTypeServiceImpl.getBloodGroups()).thenReturn(Collections.emptyList());
		when(epidemicOutbreakServiceImpl.getEpidemicOutbreakComplaints(any(), any(), any(), any()))
				.thenReturn(Collections.emptyList());
		when(organDonationServiceImpl.getOrganDonationRequests(any(), any(), any()))
				.thenReturn(Collections.emptyList());
		when(organDonationServiceImpl.getDonationTypes()).thenReturn(Collections.emptyList());
		when(organDonationServiceImpl.getDonatableOrgans()).thenReturn(Collections.emptyList());
		when(directoryServiceImpl.getDirectories()).thenReturn(Collections.emptyList());
		when(foodSafetyCopmlaintServiceImpl.getFoodSafetyComplaints(any(), any(), any(), any()))
				.thenReturn(Collections.emptyList());

		assertTrue(controller.getSioHistory("{\"benificiaryRegID\":12}").contains("\"statusCode\":200"));
	}

	@Test
	void getSioHistoryReportsARequestWithoutABeneficiary() {
		assertTrue(controller.getSioHistory("{}").contains("statusCode"));
	}

	@Test
	void getSioHistoryReportsAFailure() throws Exception {
		when(bloodRequestServiceImpl.getBloodRequest(any(), any(), any()))
				.thenThrow(new RuntimeException("db down"));

		assertTrue(controller.getSioHistory("{\"benificiaryRegID\":12}").contains("db down"));
	}
}
