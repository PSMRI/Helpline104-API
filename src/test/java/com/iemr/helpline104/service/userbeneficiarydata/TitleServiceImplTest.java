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
package com.iemr.helpline104.service.userbeneficiarydata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.repository.userbeneficiarydata.TitleRepository;

/**
 * The active titles are projected from a three-column row, of which only the id
 * and the title itself are kept.
 */
@ExtendWith(MockitoExtension.class)
class TitleServiceImplTest {

	@Mock
	private TitleRepository titleRepository;

	private TitleServiceImpl titleService;

	@BeforeEach
	void setUp() {
		titleService = new TitleServiceImpl();
		titleService.setTitleServiceImpl(titleRepository);
	}

	@Test
	void getActiveTitlesProjectsEveryReadableRow() {
		when(titleRepository.findAciveTitles()).thenReturn(new LinkedHashSet<>(
				Arrays.asList(new Object[0], new Object[] { 1, "Mrs", "Mistress" })));

		assertEquals(1, titleService.getActiveTitles().size());
	}
}
