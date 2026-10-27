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
package com.iemr.helpline104.service.location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.repository.location.LocationCityRepository;
import com.iemr.helpline104.repository.location.LocationDistrictBlockRepository;
import com.iemr.helpline104.repository.location.LocationDistrictRepository;
import com.iemr.helpline104.repository.location.LocationDistrilctBranchRepository;
import com.iemr.helpline104.repository.location.LocationStateRepository;

/**
 * Every level of the location tree is served as an id/name list, skipping rows
 * the projection cannot read. The state-and-district view needs four columns
 * because it carries the state alongside the district.
 */
@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

	@Mock
	private LocationStateRepository locationStateRepository;

	@Mock
	private LocationDistrictRepository locationDistrictRepository;

	@Mock
	private LocationDistrictBlockRepository locationDistrictBlockRepository;

	@Mock
	private LocationCityRepository locationCityRepository;

	@Mock
	private LocationDistrilctBranchRepository locationDistrilctBranchRepository;

	private LocationServiceImpl locationService;

	@BeforeEach
	void setUp() {
		locationService = new LocationServiceImpl();
		locationService.setLocationStateRepository(locationStateRepository);
		locationService.setLocationDistrictRepository(locationDistrictRepository);
		locationService.setLocationDistrictBlockRepository(locationDistrictBlockRepository);
		locationService.setLocationCityRepository(locationCityRepository);
		locationService.setLocationDistrilctBranchRepository(locationDistrilctBranchRepository);
	}

	private static ArrayList<Object[]> idAndName() {
		return new ArrayList<>(Arrays.asList(null, new Object[0], new Object[] { 2, "Pune" }));
	}

	@Test
	void getStatesProjectsEveryReadableRow() {
		when(locationStateRepository.findBy(1)).thenReturn(idAndName());

		assertEquals(1, locationService.getStates(1).size());
	}

	@Test
	void getDistrictsProjectsEveryReadableRow() {
		when(locationDistrictRepository.findBy(1)).thenReturn(idAndName());

		assertEquals(1, locationService.getDistricts(1).size());
	}

	@Test
	void getDistrictBlocksProjectsEveryReadableRow() {
		when(locationDistrictBlockRepository.findBy(2)).thenReturn(idAndName());

		assertEquals(1, locationService.getDistrictBlocks(2).size());
	}

	@Test
	void findStateDistrictByNeedsTheStateColumnsToo() {
		when(locationDistrictRepository.findStateDistrictBy(2)).thenReturn(new ArrayList<>(
				Arrays.asList(new Object[] { 2, "Pune" }, new Object[] { 2, "Pune", "Maharashtra", 1 })));

		assertEquals(1, locationService.findStateDistrictBy(2).size());
	}

	@Test
	void getCitiesProjectsEveryReadableRow() {
		when(locationDistrictBlockRepository.findBy(2)).thenReturn(idAndName());

		assertEquals(1, locationService.getCities(2).size());
	}

	@Test
	void getDistrilctBranchsProjectsEveryReadableRow() {
		when(locationDistrictBlockRepository.findBy(2)).thenReturn(idAndName());

		assertEquals(1, locationService.getDistrilctBranchs(2).size());
	}
}
