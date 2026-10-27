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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.location.CountryCity;
import com.iemr.helpline104.data.location.MCountry;
import com.iemr.helpline104.repository.location.CountryCityRepository;

/**
 * Countries and their cities are handed back as the rendered list the caller
 * receives.
 */
@ExtendWith(MockitoExtension.class)
class CountryCityServiceImplTest {

	@Mock
	private CountryCityRepository countryCityRepository;

	@InjectMocks
	private CountryCityServiceImpl countryCityService;

	@Test
	void getCountryRendersTheStoredCountries() {
		when(countryCityRepository.getCountry())
				.thenReturn(new ArrayList<>(Collections.singletonList(new MCountry())));

		assertNotNull(countryCityService.getCountry());
	}

	@Test
	void getCitiesRendersTheCitiesOfTheCountry() {
		when(countryCityRepository.getCities(1))
				.thenReturn(new ArrayList<>(Collections.singletonList(new CountryCity())));

		assertNotNull(countryCityService.getCities(1));
	}
}
