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
package com.iemr.helpline104.service.bloodComponentType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.repository.bloodComponentType.BloodComponentTypeRepository;

/**
 * Component types and blood groups are both served as id/name/description
 * projections, skipping rows the projection cannot read.
 */
@ExtendWith(MockitoExtension.class)
class BloodComponentTypeServiceImplTest {

	@Mock
	private BloodComponentTypeRepository componentTypeRepository;

	@InjectMocks
	private BloodComponentTypeServiceImpl bloodComponentTypeService;

	@Test
	void getBloodComponentTypesProjectsEveryReadableRow() throws Exception {
		when(componentTypeRepository.getComponentTypes())
				.thenReturn(Arrays.asList(null, new Object[0], new Object[] { 1, "Whole blood", "whole" }));

		assertEquals(1, bloodComponentTypeService.getBloodComponentTypes().size());
	}

	@Test
	void getBloodGroupsProjectsEveryReadableRow() throws Exception {
		when(componentTypeRepository.getBloodGroups())
				.thenReturn(Arrays.asList(null, new Object[0], new Object[] { 1, "A+ve", "A positive" }));

		assertEquals(1, bloodComponentTypeService.getBloodGroups().size());
	}
}
