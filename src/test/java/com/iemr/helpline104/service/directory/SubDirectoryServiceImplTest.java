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
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.repository.directory.SubDirectoryRepository;

/**
 * The sub-directories of a directory are served as an id/name list, skipping
 * rows the projection cannot read.
 */
@ExtendWith(MockitoExtension.class)
class SubDirectoryServiceImplTest {

	@Mock
	private SubDirectoryRepository subDirectoryRepository;

	private SubDirectoryServiceImpl subDirectoryService;

	@BeforeEach
	void setUp() {
		subDirectoryService = new SubDirectoryServiceImpl();
		subDirectoryService.setSubDirectoryRepository(subDirectoryRepository);
	}

	@Test
	void getSubDirectoriesProjectsEveryReadableRow() {
		when(subDirectoryRepository.findAciveSubDirectories(1))
				.thenReturn(new LinkedHashSet<>(Arrays.asList(new Object[0], new Object[] { 2, "Government" })));

		assertEquals(1, subDirectoryService.getSubDirectories(1).size());
	}
}
