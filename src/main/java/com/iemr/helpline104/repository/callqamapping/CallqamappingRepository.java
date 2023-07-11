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
package com.iemr.helpline104.repository.callqamapping;

import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.callqamapping.M_104callqamapping;
import com.iemr.helpline104.data.diseaseScreening.M_Questionnaire;

@Repository
@RestResource(exported = false)
public interface CallqamappingRepository extends CrudRepository<M_104callqamapping, Long>{
	
	
/*	@Query("SELECT  c.beneficiaryRegID, c.questionID, c.answer, c.remarks ,c.m_questionnaire,s.score "
			+ "FROM M_104callqamapping c "
			+ "left join c.m_questionnaire q "
			+ "left join q.m_104QuestionScore s "
			+ "WHERE c.beneficiaryRegID = :beneficiaryRegID and c.questionID=s.questionID")	
	public List<Objects[]> getCDIQuestionScores(@Param("beneficiaryRegID") Long beneficiaryRegID);
	*/
	
	/*
	@Query("SELECT  c.beneficiaryRegID, s.questionID, c.answer, c.remarks,s,q.question "
			+ "FROM M_104QuestionScore s,M_104callqamapping c "
			+ "left join c.m_questionnaire q "
//			+ "left join q.m_104QuestionScore s ON (c.questionID=s.questionID and c.answer=s.answer)"
			+ "WHERE  c.beneficiaryRegID =:beneficiaryRegID and c.questionID=s.questionID and ( q.answerType='text'  or c.answer=s.answer)")	
	public List<Objects[]> getCDIQuestionScores(@Param("beneficiaryRegID") Long beneficiaryRegID); */
	
	
	/*
	@Query("SELECT  c.beneficiaryRegID, s.questionID, c.answer, c.remarks,s,q.question "
			+ "FROM M_104QuestionScore s,M_104callqamapping c "
			+ "left join c.m_questionnaire q "
			+ "WHERE  c.beneficiaryRegID =:beneficiaryRegID and c.benCallID =:benCallID and ( q.answerType='text'  or c.answer=s.answer)")	
	public List<Objects[]> getCDIQuestionScores(@Param("beneficiaryRegID") Long beneficiaryRegID,@Param("benCallID") Long benCallID); */
	
	@Query("SELECT  c.beneficiaryRegID, c.answer, c.score, c.remarks, q.question , c.reasonIfNo , q.questionType , q.answerType "
			+ "FROM M_104callqamapping c "
			+ "left join c.m_questionnaire q "
			+ "WHERE  c.beneficiaryRegID =:beneficiaryRegID and c.benCallID =:benCallID ")	
	public List<Objects[]> getCDIQuestionScores(@Param("beneficiaryRegID") Long beneficiaryRegID,@Param("benCallID") Long benCallID); 
   
   
}
