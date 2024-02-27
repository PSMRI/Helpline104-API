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
package com.iemr.helpline104.repository.location;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.location.CountryCity;
import com.iemr.helpline104.data.location.MCountry;
@Service
@Repository
@RestResource(exported = false)
public interface CountryCityRepository extends CrudRepository<CountryCity, Integer> {
	@Autowired(required=true)

	@Query("select country from MCountry country where country.deleted = false order by country.countryName asc")
	public ArrayList<MCountry> getCountry();
	
	@Query("select city from CountryCity city where city.countryID = :countryID and city.deleted = false order by city.cityName asc")
	public ArrayList<CountryCity> getCities(@Param("countryID") Integer countryID);

}
