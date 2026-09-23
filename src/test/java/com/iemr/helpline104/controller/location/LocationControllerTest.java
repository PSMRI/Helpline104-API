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
package com.iemr.helpline104.controller.location;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.location.DistrictBlock;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.location.States;
import com.iemr.helpline104.service.location.LocationService;

/**
 * Every level of the location tree is answered as the rendered list of what the
 * service found; city and village both read the taluk level.
 */
@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

	@Mock
	private LocationService locationService;

	private LocationController controller;

	@BeforeEach
	void setUp() {
		controller = new LocationController();
		controller.setLocationService(locationService);
	}

	@Test
	void getStatesAnswersWithTheStatesOfTheCountry() {
		when(locationService.getStates(1)).thenReturn(Collections.singletonList(new States(1, "Maharashtra")));

		assertTrue(controller.getStates(1).contains("Maharashtra"));
	}

	@Test
	void getDistrictsAnswersWithTheDistrictsOfTheState() {
		when(locationService.getDistricts(1)).thenReturn(Collections.singletonList(new Districts(2, "Pune")));

		assertTrue(controller.getDistricts(1).contains("Pune"));
	}

	@Test
	void geStatetDistrictsAnswersWithTheDistrictsAndTheirState() {
		when(locationService.findStateDistrictBy(1))
				.thenReturn(Collections.singletonList(new Districts(2, "Pune", 1, "Maharashtra")));

		assertTrue(controller.geStatetDistricts(1).contains("Pune"));
	}

	@Test
	void getDistrictBlocksAnswersWithTheTaluksOfTheDistrict() {
		when(locationService.getDistrictBlocks(2))
				.thenReturn(Collections.singletonList(new DistrictBlock(3, "Haveli")));

		assertTrue(controller.getDistrictBlocks(2).contains("Haveli"));
	}

	@Test
	void getCityReadsTheTalukLevel() {
		when(locationService.getDistrictBlocks(2))
				.thenReturn(Collections.singletonList(new DistrictBlock(3, "Haveli")));

		assertTrue(controller.getCity(2).contains("Haveli"));
	}

	@Test
	void getVillagesReadsTheTalukLevel() {
		when(locationService.getDistrictBlocks(2))
				.thenReturn(Collections.singletonList(new DistrictBlock(3, "Haveli")));

		assertTrue(controller.getVillages(2).contains("Haveli"));
	}
}
