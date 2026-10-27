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
package com.iemr.helpline104.data;

import org.junit.jupiter.api.Test;

import com.iemr.helpline104.data.location.CityDetails;
import com.iemr.helpline104.data.location.Country;
import com.iemr.helpline104.data.location.CountryCity;
import com.iemr.helpline104.data.location.DistrictBlock;
import com.iemr.helpline104.data.location.DistrictBranchMapping;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.location.MCountry;
import com.iemr.helpline104.data.location.M_ProviderServiceMapping;
import com.iemr.helpline104.data.location.M_ServiceProvider;
import com.iemr.helpline104.data.location.States;
import com.iemr.helpline104.data.location.Taluks;

/**
 * Set/get round trips and constructor drives for the Location entities.
 */
class LocationEntityAccessorsTest {

	@Test
	void everyAccessorRoundTripsAndEveryConstructorBuilds() {
		EntityAccessors.assertEntities(
				CityDetails.class,
				Country.class,
				CountryCity.class,
				DistrictBlock.class,
				DistrictBranchMapping.class,
				Districts.class,
				MCountry.class,
				M_ProviderServiceMapping.class,
				M_ServiceProvider.class,
				States.class,
				Taluks.class);
	}
}
