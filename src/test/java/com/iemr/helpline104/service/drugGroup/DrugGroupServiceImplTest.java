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
package com.iemr.helpline104.service.drugGroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.drugMaster.DrugFrequency;
import com.iemr.helpline104.data.drugMaster.DrugStrength;
import com.iemr.helpline104.repository.drugGroup.DrugFrequencyRepository;
import com.iemr.helpline104.repository.drugGroup.DrugGroupRepository;
import com.iemr.helpline104.repository.drugGroup.DrugStrengthRepository;

/**
 * The drug master is served per provider: groups, the drugs in a group, the
 * whole drug list, and the frequency and strength masters behind a
 * prescription.
 */
@ExtendWith(MockitoExtension.class)
class DrugGroupServiceImplTest {

	@Mock
	private DrugGroupRepository drugGroupRepository;

	@Mock
	private DrugFrequencyRepository drugFrequencyRepository;

	@Mock
	private DrugStrengthRepository drugStrengthRepository;

	@InjectMocks
	private DrugGroupServiceImpl drugGroupService;

	/** The seven columns of the drug mapping projection. */
	private static Object[] drugRow() {
		return new Object[] { 5, 7, "Paracetamol", 2, "tablet", "oral", 3 };
	}

	@Test
	void getDrugGroupsProjectsEveryReadableRow() throws Exception {
		when(drugGroupRepository.getDrugGroups(1))
				.thenReturn(Arrays.asList(null, new Object[0], new Object[] { 2, "Analgesics", "pain relief" }));

		assertEquals(1, drugGroupService.getDrugGroups(1).size());
	}

	@Test
	void getDrugListProjectsEveryReadableRow() throws Exception {
		when(drugGroupRepository.getDrugList(1, 2)).thenReturn(Arrays.asList(null, new Object[0], drugRow()));

		assertEquals(1, drugGroupService.getDrugList(1, 2).size());
	}

	@Test
	void getDrugDetailListProjectsEveryReadableRow() throws Exception {
		when(drugGroupRepository.getDrugDetailList(1)).thenReturn(Arrays.asList(null, new Object[0], drugRow()));

		assertEquals(1, drugGroupService.getDrugDetailList(1).size());
	}

	@Test
	void getDrugFrequencyServesTheFrequencyMaster() {
		ArrayList<DrugFrequency> frequencies = new ArrayList<>(Collections.singletonList(new DrugFrequency()));
		when(drugFrequencyRepository.getDrugFrequency()).thenReturn(frequencies);

		assertEquals(frequencies, drugGroupService.getDrugFrequency());
	}

	@Test
	void getDrugStrengthServesTheStrengthMasterOfTheProvider() {
		ArrayList<DrugStrength> strengths = new ArrayList<>(Collections.singletonList(new DrugStrength()));
		when(drugStrengthRepository.getDrugStrength(1)).thenReturn(strengths);

		assertEquals(strengths, drugGroupService.getDrugStrength(1));
	}
}
