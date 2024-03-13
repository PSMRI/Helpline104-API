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

import com.iemr.helpline104.data.diseaseScreening.M_questionairValues;
import com.iemr.helpline104.repository.diseaseScreening.QuestionScoreRepository;

@Service
public class QuestionScoreServiceImpl implements QuestionScoreService{

	@Autowired
	private QuestionScoreRepository questionScoreRepository;
	@Override
	public List<M_questionairValues> fetchAnswers(Integer questionID) throws Exception {
		List<M_questionairValues> answers = new ArrayList<M_questionairValues>();
		List<Object[]> answersList = questionScoreRepository.fetchAnswers(questionID);
		
		for (Object[] objects : answersList) {
			if (objects!=null && objects.length > 0) {
							
				answers.add(new M_questionairValues((Integer)objects[0], (Integer)objects[1], (String)objects[2], (Integer)objects[3]));
			}
		}
		
		return answers;
	}

}
