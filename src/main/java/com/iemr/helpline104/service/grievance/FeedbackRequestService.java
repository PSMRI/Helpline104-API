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
package com.iemr.helpline104.service.grievance;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.grievance.T_FeedbackRequset;
import com.iemr.helpline104.repository.grievance.FeedbackRequest;

@Service
public class FeedbackRequestService {
	
	//@Autowired 
	private FeedbackRequest feedbackrequest;

	public int update(T_FeedbackRequset feedbackrequest1) {
		feedbackrequest.save(feedbackrequest1);  
		
		return feedbackrequest1.getFeedbackRequestID();
	}

	public ArrayList<Object[]> getAllFeedback(Integer feedbackID) {
		// TODO Auto-generated method stub
		return feedbackrequest.getAllFeedbackByID(feedbackID);
	}

	
	public T_FeedbackRequset getdataById(int feedbackID) {
		// TODO Auto-generated method stub
		return feedbackrequest.getdataById(feedbackID);
	}

	public T_FeedbackRequset getdataById1(int res) {
		// TODO Auto-generated method stub
		return feedbackrequest.getdataById1(res);
	}

	

}
