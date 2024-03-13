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
package com.iemr.helpline104.repository.feedback;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.feedback.FeedbackDetails;

@Repository
@RestResource(exported = false)
public interface FeedbackRepository extends CrudRepository<FeedbackDetails, Long> {

	@Query("select feedbackID, severityID, feedbackTypeID, feedbackStatusID, feedback, createdBy, feedbackAgainst "
			+ "from FeedbackDetails where beneficiaryRegID = :id")
	public ArrayList<Object[]> findByBeneficiaryID(@Param("id") long id);

	@Query("select feedbackID, severityID, feedbackTypeID, feedbackStatusID, feedback, createdBy, feedbackAgainst "
			+ "from FeedbackDetails where feedbackID = :id")
	public ArrayList<Object[]> findByFeedbackID(@Param("id") long id);

//	@Query("SELECT feedbackID, severityID, feedbackTypeID, feedbackStatusID, feedback, createdBy, feedbackAgainst "
//			+ "from FeedbackDetails where feedbackID = :id AND  ")
//	public ArrayList<Object[]> findByGrievanceID(@Param("id") String id);

//	@Query("SELECT f FROM FeedbackDetails f WHERE feedbackID = :id AND deleted=false ")
//	public ArrayList<FeedbackDetails> findByGrievanceID(@Param("id") String id);

	@Query("SELECT f FROM FeedbackDetails f WHERE feedbackID = :id AND deleted=false ")
	public ArrayList<FeedbackDetails> findByFeedbackIDNew(@Param("id") long id);

	// shubham 29-09-2020
//	@Query("select feedbackID, severityID, feedbackTypeID, feedbackStatusID, feedback, createdBy, feedbackAgainst "
//			+ "from FeedbackDetails f " + " LEFT JOIN BeneficiaryMapping ben ON f.beneficiaryRegID=ben.benRegID "
//			+ "LEFT JOIN BenContactMapping bc ON bc.benContactsID=ben.benContactsId "
//			+ " where f.deleted=false and bc.preferredPhoneNum = :mobile order by f.feedbackID desc")
//	public ArrayList<Object[]> findByMobile(@Param("mobile") String mobile);

	@Query("SELECT t FROM FeedbackDetails t WHERE t.requestID = :requestID AND deleted=false ")
	public FeedbackDetails searchByRequestID(@Param("requestID") String requestID);

}
