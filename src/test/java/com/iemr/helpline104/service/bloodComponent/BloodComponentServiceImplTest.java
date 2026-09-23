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
package com.iemr.helpline104.service.bloodComponent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.bloodComponent.M_Component;
import com.iemr.helpline104.repository.bloodComponent.BloodComponentRepository;

/**
 * Blood components are stored as given and read back as an id/name/description
 * projection.
 */
@ExtendWith(MockitoExtension.class)
class BloodComponentServiceImplTest {

	@Mock
	private BloodComponentRepository bloodComponentRepository;

	@InjectMocks
	private BloodComponentServiceImpl bloodComponentService;

	@Test
	void saveStoresTheComponent() {
		M_Component component = new M_Component();
		when(bloodComponentRepository.save(component)).thenReturn(component);

		assertSame(component, bloodComponentService.save(component));
	}

	@Test
	void getBloodComponentsProjectsEveryReadableRow() throws Exception {
		when(bloodComponentRepository.getBloodComponents(3)).thenReturn(
				Arrays.asList(null, new Object[0], new Object[] { 3, "Plasma", "fresh frozen" }));

		assertEquals(1, bloodComponentService.getBloodComponents(3).size());
	}
}
