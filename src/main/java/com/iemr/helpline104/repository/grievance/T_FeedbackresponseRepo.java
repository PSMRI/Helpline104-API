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
package com.iemr.helpline104.repository.grievance;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.grievance.T_Feedbackresponse;

@Repository
@RestResource(exported = false)
public interface T_FeedbackresponseRepo extends CrudRepository<T_Feedbackresponse, Integer>{
	@Query("SELECT tf1.responseSummary,tf1.feedbackRequestID,tf1.comments,tf1.authName,tf1.authDesignation," 
	+ " tfr.feedbackID as FeedbackID,"
	+ " tfr.feedbackSupSummary as FeedbackSupSummary,"
	+ " tfr.comments as Comments,"
	+ " tf.feedback as Feedback"		
	+ " FROM T_Feedbackresponse tf1 "  
	+ " JOIN tf1.tfeedbackr1 tfr " 
	+ " JOIN tf1.tfeedback tf"
	+ " WHERE tf1.feedbackID =:i ")
	ArrayList<Object[]> getdatabyId(@Param("i")int i);

}
