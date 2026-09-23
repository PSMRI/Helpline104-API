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
package com.iemr.helpline104.service.snomedct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.snomedct.SCTDescription;
import com.iemr.helpline104.repository.snomedct.SnomedRepository;

/**
 * A SNOMED CT term search projects the concept id and term of each match, and
 * skips any row that does not carry both.
 */
@ExtendWith(MockitoExtension.class)
class SnomedServiceImplTest {

	@Mock
	private SnomedRepository snomedRepository;

	@InjectMocks
	private SnomedServiceImpl snomedService;

	@Test
	void findSnomedCTRecordFromTermProjectsEveryCompleteMatch() {
		when(snomedRepository.findSnomedCTRecordFromTerm("fever"))
				.thenReturn(Arrays.asList(null, new Object[] { "386661006" },
						new Object[] { "386661006", "Fever" }));

		List<SCTDescription> matches = snomedService.findSnomedCTRecordFromTerm("fever");

		assertEquals(1, matches.size());
		assertEquals("Fever", matches.get(0).getTerm());
	}
}
