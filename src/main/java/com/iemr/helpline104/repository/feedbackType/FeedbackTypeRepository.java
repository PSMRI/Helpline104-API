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
package com.iemr.helpline104.repository.feedbackType;

import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.feedbackType.M_FeedbackType;

@Repository
@RestResource(exported = false)
public interface FeedbackTypeRepository extends CrudRepository<M_FeedbackType, Long> {

	@Query("select t.feedbackTypeID,t.feedbackTypeName,t.feedbackDesc,t.providerServiceMapID,n.feedbackNatureID, n.feedbackNature, n.feedbackNatureDesc "
			+ "from M_FeedbackType t" + " RIGHT JOIN t.m_feedbackNature n "
			+ "where n.deleted=false and t.providerServiceMapID=:serviceLineID and t.feedbackTypeID=:feedbackTypeID")
	public List<Objects[]> getNatureOfComplaintTypes(@Param("serviceLineID") Integer serviceLineID,
			@Param("feedbackTypeID") Short feedbackTypeID) throws Exception;

	@Query("select new M_FeedbackType(feedbackTypeID, feedbackTypeName) from M_FeedbackType type where "
			+ "providerServiceMapID = :providerServiceMapID and deleted = false order by feedbackTypeName asc")
	List<M_FeedbackType> findActiveFeedbackTypes(@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query("select new M_FeedbackType(feedbackTypeID, feedbackTypeName) from M_FeedbackType type where "
			+ "providerServiceMapID = :providerServiceMapID and feedbackTypeName = 'Epidemic Complaints' and deleted = false")
	M_FeedbackType findEpidemicFeedbackTypeID(@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query("select new M_FeedbackType(feedbackTypeID, feedbackTypeName) from M_FeedbackType type where "
			+ "providerServiceMapID = :providerServiceMapID and feedbackTypeName = 'Foodsafety Complaints' and deleted = false")
	M_FeedbackType findFoodSafetyFeedbackTypeID(@Param("providerServiceMapID") Integer providerServiceMapID);
	
	@Query("select feedbackTypeCode from M_FeedbackType type where "
			+ "feedbackTypeID = :feedbackTypeID and deleted = false")
	String findFeedbackTypeCode(@Param("feedbackTypeID") Short feedbackTypeID);
    
	@Query("select new M_FeedbackType(feedbackTypeID, feedbackTypeName) from M_FeedbackType type where "
			+ "providerServiceMapID = :providerServiceMapID and feedbackTypeName = 'Bal Vivah Complaints' and deleted = false")
	M_FeedbackType findBalVivahFeedbackTypeID(@Param("providerServiceMapID") Integer providerServiceMapID);
}
