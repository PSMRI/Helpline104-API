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
package com.iemr.helpline104.service.organDonation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.helpline104.data.beneficiarycall.BenCall;
import com.iemr.helpline104.data.organDonation.OrganDonations;
import com.iemr.helpline104.data.organDonation.RequestedInstitution;
import com.iemr.helpline104.data.organDonation.T_OrganDonation;
import com.iemr.helpline104.repository.beneficiarycall.BenCallRepository;
import com.iemr.helpline104.repository.organDonation.OrganDonationRepository;
import com.iemr.helpline104.repository.organDonation.RequestedInstitutionRepository;

/**
 * Organ donation requests are searched by beneficiary, call or request ID, each
 * one carrying the institutions it was raised against. Saving mints the OD
 * request ID from the generated key.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrganDonationServiceImplTest {

	@Mock
	private OrganDonationRepository organDonationRepository;

	@Mock
	private RequestedInstitutionRepository requestedInstitutionRepository;

	@Mock
	private BenCallRepository benCallRepository;

	@InjectMocks
	private OrganDonationServiceImpl organDonationService;

	/** The fifteen columns of the organ donation projection. */
	private static List<Object[]> donationRows() {
		return Arrays.asList(null, new Object[0], new Object[] { 9L, "OD/2/01012024/9", 12L, "Asha", 40, (short) 2,
				"Female", 1, "pledge", "kidney", 2, "Pune", "remarks", 3, "agent" });
	}

	@Test
	void getOrganDonationRequestsSearchesByBeneficiary() throws Exception {
		when(organDonationRepository.getOrganDonationRequests(12L)).thenReturn(donationRows());
		when(requestedInstitutionRepository.findByOrganDonationIDAndDeleted(9L, false))
				.thenReturn(Collections.singletonList(new RequestedInstitution()));

		List<T_OrganDonation> requests = organDonationService.getOrganDonationRequests(12L, null, null);

		assertEquals(1, requests.size());
		assertEquals(1, requests.get(0).getRequestedInstitution().size());
	}

	@Test
	void getOrganDonationRequestsSearchesByCallAndAttachesTheCall() throws Exception {
		when(organDonationRepository.getOrganDonationRequestsByBenCallID(4L)).thenReturn(donationRows());
		when(benCallRepository.findByBenCallID(4L)).thenReturn(new BenCall());

		assertEquals(1, organDonationService.getOrganDonationRequests(null, 4L, null).size());
		verify(benCallRepository).findByBenCallID(4L);
	}

	@Test
	void getOrganDonationRequestsSearchesByRequestId() throws Exception {
		when(organDonationRepository.getOrganDonationRequestsByRequestID("OD/2/01012024/9"))
				.thenReturn(donationRows());

		assertEquals(1, organDonationService.getOrganDonationRequests(null, null, "OD/2/01012024/9").size());
	}

	@Test
	void saveMintsTheRequestIdForEveryDonation() throws Exception {
		T_OrganDonation donation = new T_OrganDonation(9L, null, 12L, "Asha", 40, (short) 2, "Female", 1, "pledge",
				"living donor", 2, "kidney", "renal", 3, "remarks");
		OrganDonations request = new OrganDonations();
		request.setT_organDonations(Collections.singletonList(donation));
		when(organDonationRepository.save(any())).thenReturn(donation);

		String response = organDonationService.save(request);

		assertTrue(response.contains("OD/"));
		verify(organDonationRepository, org.mockito.Mockito.times(2)).save(donation);
	}

	@Test
	void updateWritesTheDonationTypeOrganAndRemarks() throws Exception {
		T_OrganDonation donation = new T_OrganDonation(9L, null, 12L, "Asha", 40, (short) 2, "Female", 1, "pledge",
				"living donor", 2, "kidney", "renal", 3, "remarks");

		assertTrue(organDonationService.update(donation).contains("remarks"));
		verify(organDonationRepository).updateOrganDonationDetails(9L, null, null, "remarks");
	}

	@Test
	void saveInstituteDetailsPointsEveryInstitutionAtTheDonation() throws Exception {
		RequestedInstitution institution = new RequestedInstitution();
		T_OrganDonation donation = new T_OrganDonation(9L, null, 12L, "Asha", 40, (short) 2, "Female", 1, "pledge",
				"living donor", 2, "kidney", "renal", 3, "remarks");
		donation.setCreatedBy("agent");
		donation.setRequestedInstitution(Collections.singletonList(institution));

		organDonationService.saveInstituteDetails(donation);

		verify(requestedInstitutionRepository).saveAll(Collections.singletonList(institution));
	}

	@Test
	void saveInstituteDetailsAcceptsADonationWithoutInstitutions() throws Exception {
		T_OrganDonation donation = new T_OrganDonation();

		organDonationService.saveInstituteDetails(donation);

		verify(requestedInstitutionRepository, org.mockito.Mockito.never()).saveAll(any());
	}

	@Test
	void getDonationTypesProjectsEveryReadableRow() throws Exception {
		when(organDonationRepository.getDonationTypes())
				.thenReturn(Arrays.asList(null, new Object[0], new Object[] { 1, "Pledge", "living donor" }));

		assertEquals(1, organDonationService.getDonationTypes().size());
	}

	@Test
	void getDonatableOrgansProjectsEveryReadableRow() throws Exception {
		when(organDonationRepository.getDonatableOrgans())
				.thenReturn(Arrays.asList(null, new Object[0], new Object[] { 1, "Kidney", "renal" }));

		assertEquals(1, organDonationService.getDonatableOrgans().size());
	}
}
