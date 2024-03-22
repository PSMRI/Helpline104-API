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
package com.iemr.helpline104.service.diseaseScreening;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iemr.helpline104.data.diseaseScreening.M_Questionnaire;
import com.iemr.helpline104.repository.diseaseScreening.QuestionnaireRepository;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{

	@Autowired
	private QuestionnaireRepository questionnaireRepository;
	
	@Override
	 @Transactional
	public List<M_Questionnaire> fetchQuestions(Integer questionTypeID ,Integer providerServiceMapID) throws Exception {
		List<M_Questionnaire> questions = new ArrayList<M_Questionnaire>();
		List<M_Questionnaire> questionsList = null;
		if(questionTypeID != null && (questionTypeID == 1 || questionTypeID == 2 || questionTypeID == 3)) {
			questionsList = questionnaireRepository.fetchQuestionsOnQuestionID(questionTypeID);
		}else {
			questionsList = questionnaireRepository.fetchQuestions(providerServiceMapID);
		}
		
		//List<Object[]> questionsList = questionnaireRepository.fetchQuestions(questionTypeID, providerServiceMapID);
		
/*		for (Object[] objects : questionsList) {
			if (objects!=null && objects.length > 0) {
							
				questions.add(new M_Questionnaire((Integer)objects[0], (String)objects[1], (String)objects[2], (Integer)objects[3],
						(String)objects[4], (Boolean)objects[5], (String)objects[6], (Integer)objects[7], (Integer)objects[8]));
			}
		}*/
		return questionsList;
	}

}
