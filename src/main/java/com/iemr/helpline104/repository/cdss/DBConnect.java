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
package com.iemr.helpline104.repository.cdss;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.cdss.Symptoms;

@Repository
@RestResource(exported = false)
public interface DBConnect extends CrudRepository<Symptoms, Integer> {
	
	@Query("SELECT Symptom FROM Symptoms s group by Symptom")
	List<String> getSymptoms();

	@Query(value="SELECT * FROM m_cdssdata s where LOWER(s.Symptom) = LOWER(:symptom) and :age>=s.AgeFrom and :age<=s.AgeTo and s.Gender like %:gender% order by(s.AgeTo-s.AgeFrom) Limit 1",nativeQuery=true)
	Symptoms getQuestions(@Param("symptom") String symptom,@Param("age")int age,@Param("gender") String gender);

	@Query("SELECT Data FROM Symptoms s where s.SymptomId = :id ")
	String getResultsById(@Param("id") int id);

	@Query("SELECT Symptom FROM Symptoms s where :age>=s.AgeFrom and :age<=s.AgeTo and s.Gender like %:gender%  group by Symptom")
	List<String> getSymptoms(@Param("gender")String gender, @Param("age")int age);


	
}
