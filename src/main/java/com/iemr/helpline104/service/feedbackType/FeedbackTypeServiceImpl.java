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
package com.iemr.helpline104.service.feedbackType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.feedbackNature.M_FeedbackNature;
import com.iemr.helpline104.data.feedbackType.M_FeedbackType;
import com.iemr.helpline104.repository.feedbackNature.FeedbackNatureRepository;
import com.iemr.helpline104.repository.feedbackType.FeedbackTypeRepository;

@Service
public class FeedbackTypeServiceImpl implements FeedbackTypeService{

	
	@Autowired
	private FeedbackTypeRepository feedbackTypeRepository;
	
	@Override
	public List<M_FeedbackType> getNatureOfComplaintTypes(Integer serviceLineID, Short feedbackTypeID)
			throws Exception {
		List<M_FeedbackType> natureOfComplaintsList = new ArrayList<M_FeedbackType>();
		List<Object[]> natureOfComplaints = feedbackTypeRepository.getNatureOfComplaintTypes(serviceLineID, feedbackTypeID);
		
		for (Object[] objects : natureOfComplaints) {
			if (objects!=null && objects.length > 0) {
				
				natureOfComplaintsList.add(new M_FeedbackType((Short)objects[0], (String)objects[1], (String)objects[2], (Integer)objects[3], (Integer)objects[4],
																(String)objects[5], (String)objects[6]));
			}
		}
		return natureOfComplaintsList;
	}
}
