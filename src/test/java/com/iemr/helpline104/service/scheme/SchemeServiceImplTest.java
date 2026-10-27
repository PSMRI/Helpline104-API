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
package com.iemr.helpline104.service.scheme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.scheme.T_Schemeservice;
import com.iemr.helpline104.repository.scheme.SchemeRepository;

/**
 * Scheme searches are recorded per call and read back either by beneficiary or
 * by call, whichever the caller knows.
 */
@ExtendWith(MockitoExtension.class)
class SchemeServiceImplTest {

	@Mock
	private SchemeRepository schemeRepository;

	@InjectMocks
	private SchemeServiceImpl schemeService;

	@Test
	void getSchemeSearchHistorySearchesByBeneficiary() {
		List<T_Schemeservice> history = Collections.singletonList(new T_Schemeservice());
		when(schemeRepository.findByBeneficiaryRegID(12L)).thenReturn(history);

		assertEquals(history, schemeService.getSchemeSearchHistory(12L, 4L));
		verify(schemeRepository).findByBeneficiaryRegID(12L);
	}

	@Test
	void getSchemeSearchHistoryFallsBackToTheCall() {
		List<T_Schemeservice> history = Collections.singletonList(new T_Schemeservice());
		when(schemeRepository.findByBenCallID(4L)).thenReturn(history);

		assertEquals(history, schemeService.getSchemeSearchHistory(null, 4L));
	}

	@Test
	void getSchemeSearchHistoryIsEmptyWithoutASearchTerm() {
		assertTrue(schemeService.getSchemeSearchHistory(null, null).isEmpty());
		verifyNoInteractions(schemeRepository);
	}

	@Test
	void saveSchemeSearchHistoryStoresEveryRow() {
		T_Schemeservice search = new T_Schemeservice();
		when(schemeRepository.save(Collections.singletonList(search)))
				.thenReturn(Collections.singletonList(search));

		assertEquals(Collections.singletonList(search).toString(),
				schemeService.saveSchemeSearchHistory(new T_Schemeservice[] { search }));
	}
}
