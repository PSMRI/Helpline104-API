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
package com.iemr.helpline104.service.directory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.helpline104.data.beneficiarycall.BenCall;
import com.iemr.helpline104.data.directory.Directoryservice;
import com.iemr.helpline104.repository.beneficiarycall.BenCallRepository;
import com.iemr.helpline104.repository.directory.DirectoryRepository;
import com.iemr.helpline104.repository.directory.DirectoryserviceRepository;

/**
 * Directory searches are recorded per call and read back either by beneficiary
 * or by call; the active directories themselves are served as an id/name list.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DirectoryServiceImplTest {

	@Mock
	private DirectoryRepository directoryRepository;

	@Mock
	private DirectoryserviceRepository directoryserviceRepository;

	@Mock
	private BenCallRepository benCallRepository;

	private DirectoryServiceImpl directoryService;

	@BeforeEach
	void setUp() {
		directoryService = new DirectoryServiceImpl();
		directoryService.setDirectoryRepository(directoryRepository);
		ReflectionTestUtils.setField(directoryService, "directoryserviceRepository", directoryserviceRepository);
		ReflectionTestUtils.setField(directoryService, "benCallRepository", benCallRepository);
	}

	/** The five columns of the directory search projection. */
	private static List<Object[]> searchRows() {
		return Arrays.asList(null, new Object[0],
				new Object[] { 9L, "Blood banks", "District Hospital", "Pune", "9999999999" });
	}

	@Test
	void getDirectorySearchHistorySearchesByBeneficiary() {
		when(directoryserviceRepository.getBenSearchHistory(12L)).thenReturn(searchRows());

		assertEquals(1, directoryService.getDirectorySearchHistory(12L, null).size());
	}

	@Test
	void getDirectorySearchHistorySearchesByCallAndAttachesTheCall() {
		when(directoryserviceRepository.getBenSearchHistoryByBenCallID(4L)).thenReturn(searchRows());
		when(benCallRepository.findByBenCallID(4L)).thenReturn(new BenCall());

		assertEquals(1, directoryService.getDirectorySearchHistory(null, 4L).size());
		verify(benCallRepository).findByBenCallID(4L);
	}

	@Test
	void saveDirectorySearchHistoryStoresEveryRow() {
		Directoryservice search = new Directoryservice();
		when(directoryserviceRepository.saveAll(any())).thenReturn(Collections.singletonList(search));

		assertTrue(directoryService.saveDirectorySearchHistory(new Directoryservice[] { search }).contains("["));
	}

	@Test
	void getDirectoriesProjectsEveryReadableRow() {
		when(directoryRepository.findAciveDirectories())
				.thenReturn(new LinkedHashSet<>(Arrays.asList(new Object[0], new Object[] { 1, "Blood banks" })));

		assertEquals(1, directoryService.getDirectories().size());
	}
}
