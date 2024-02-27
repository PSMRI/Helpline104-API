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
package com.iemr.helpline104.repository.diseaseScreening;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.diseaseScreening.M_Questionnaire;
@Service
@Repository
@RestResource(exported = false)
public interface QuestionnaireRepository extends CrudRepository<M_Questionnaire, Integer> {
	@Autowired(required=true)

	@Query("select q from M_Questionnaire q " + 
	"LEFT JOIN q.m_104QuestionScore s where q.questionTypeID = :questionTypeID group by q.questionID order by q.questionRank, s.iD")
	public List<Objects[]> fetchQuestionsOnQuestionID(@Param("questionTypeID") Integer questionTypeID);

	@Query("SELECT DISTINCT q from M_Questionnaire q LEFT JOIN q.m_questionairValues s "
			+ "WHERE q.providerServiceMapID = :providerServiceMapID AND q.deleted is false "
			+ "ORDER BY q.questionTypeID ASC, q.questionRank ASC")
	public List<Objects[]> fetchQuestions(@Param("providerServiceMapID") Integer providerServiceMapID);

}
