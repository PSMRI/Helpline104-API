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
package com.iemr.helpline104.service.prescription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.iemr.helpline104.data.prescription.PrescribedDrug;
import com.iemr.helpline104.data.prescription.Prescription;
import com.iemr.helpline104.repository.prescription.PrescribedDrugRepository;
import com.iemr.helpline104.repository.prescription.PrescriptionRepository;

/**
 * A saved prescription stamps each prescribed drug with the generated
 * prescription id and a validity that runs for as many days as the drug was
 * prescribed for. Reading prescriptions back attaches the drugs to each one,
 * and the "latest valid" view keeps only prescriptions with a drug still in
 * date.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PrescriptionServiceImplTest {

	private static final Pageable FIRST_PAGE = PageRequest.of(0, 1);

	@Mock
	private PrescriptionRepository prescriptionRepository;

	@Mock
	private PrescribedDrugRepository prescribedDrugRepository;

	@InjectMocks
	private PrescriptionServiceImpl prescriptionService;

	/** The six columns of the prescription projection. */
	private static List<Object[]> prescriptionRows() {
		return Arrays.asList(null, new Object[0],
				new Object[] { 9L, 12L, 4L, "fever", "rest", new Date(1_700_000_000_000L) });
	}

	/** The eleven columns of the prescribed drug projection. */
	private static Object[] drugRow(Timestamp validTill) {
		return new Object[] { 3L, 5, "Paracetamol", "tablet", "oral", "twice a day", "500mg", 3, "after food",
				"drowsiness", validTill };
	}

	@Test
	void savePrescriptionStampsEachDrugWithTheGeneratedIdAndItsValidity() {
		Prescription saved = new Prescription();
		saved.setPrescriptionID(9L);
		PrescribedDrug drug = new PrescribedDrug(3L, 5, "Paracetamol", "tablet", "oral", "twice a day", "500mg", 3,
				"after food", "drowsiness", null);
		Prescription request = new Prescription();
		request.setPrescribedDrugs(new ArrayList<>(Collections.singletonList(drug)));
		when(prescriptionRepository.save(request)).thenReturn(saved);
		when(prescribedDrugRepository.save(any(PrescribedDrug.class))).thenReturn(drug);

		Prescription response = prescriptionService.savePrescription(request);

		assertEquals(9L, response.getPrescriptionID());
		assertNotNull(drug.getValidTill());
		assertTrue(drug.getValidTill().after(new Timestamp(System.currentTimeMillis())));
	}

	@Test
	void getPrescriptionIsNotServedFromThisService() {
		assertNull(prescriptionService.getPrescription(12L, 9L));
	}

	@Test
	void getPrescriptionListAttachesTheDrugsToEachPrescription() {
		when(prescriptionRepository.getPrescriptionList(12L, FIRST_PAGE)).thenReturn(prescriptionRows());
		when(prescribedDrugRepository.getPrescribedDrugs(9L))
				.thenReturn(Arrays.asList(null, new Object[0], drugRow(new Timestamp(1_700_000_000_000L))));

		List<Prescription> prescriptions = prescriptionService.getPrescriptionList(12L, FIRST_PAGE);

		assertEquals(1, prescriptions.size());
		assertEquals(1, prescriptions.get(0).getPrescribedDrugs().size());
	}

	@Test
	void getPrescriptionListByBenCallIDAttachesTheDrugsToEachPrescription() {
		when(prescriptionRepository.getPrescriptionListByBenCallID(4L)).thenReturn(prescriptionRows());
		when(prescribedDrugRepository.getPrescribedDrugs(9L))
				.thenReturn(Arrays.asList(null, new Object[0], drugRow(new Timestamp(1_700_000_000_000L))));

		List<Prescription> prescriptions = prescriptionService.getPrescriptionListByBenCallID(4L);

		assertEquals(1, prescriptions.size());
		assertNotNull(prescriptions.get(0).getPrescribedDrugs().get(0));
	}

	@Test
	void getLatestValidPescriptionKeepsTheDrugsStillInDate() {
		Timestamp tomorrow = new Timestamp(System.currentTimeMillis() + 86_400_000L);
		when(prescriptionRepository.getLatestValidPescription(12L, FIRST_PAGE)).thenReturn(prescriptionRows());
		when(prescribedDrugRepository.getPrescribedDrugs(9L))
				.thenReturn(Arrays.asList(null, new Object[0], drugRow(tomorrow)));

		List<Prescription> prescriptions = prescriptionService.getLatestValidPescription(12L, FIRST_PAGE);

		assertEquals(1, prescriptions.size());
		assertEquals(1, prescriptions.get(0).getPrescribedDrugs().size());
	}

	@Test
	void getLatestValidPescriptionDropsAPrescriptionWhoseDrugsHaveExpired() {
		when(prescriptionRepository.getLatestValidPescription(12L, FIRST_PAGE)).thenReturn(prescriptionRows());
		when(prescribedDrugRepository.getPrescribedDrugs(9L)).thenReturn(
				Arrays.asList(drugRow(new Timestamp(1_600_000_000_000L)), drugRow(null)));

		assertTrue(prescriptionService.getLatestValidPescription(12L, FIRST_PAGE).isEmpty());
	}
}
