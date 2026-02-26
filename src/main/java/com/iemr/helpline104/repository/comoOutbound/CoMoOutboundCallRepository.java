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
package com.iemr.helpline104.repository.comoOutbound;

import java.sql.Date;
import java.util.ArrayList;

import org.checkerframework.checker.units.qual.t;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.comoOutbound.OutboundCallActivity;
import com.iemr.helpline104.data.comoOutbound.T_104CoMoOutboundCallDetails;

import jakarta.transaction.Transactional;

@Repository
@RestResource(exported = false)
public interface CoMoOutboundCallRepository extends CrudRepository<T_104CoMoOutboundCallDetails, Long> {

    @Transactional
	@Modifying
	@Query("update T_104CoMoOutboundCallDetails set callId = :callId WHERE iD = :iD")
	public Integer update(@Param("iD") Long iD, @Param("callId") String callId);

    
    // Get active activities by provider
    @Query("SELECT o FROM OutboundCallActivity o WHERE o.deleted = false " +
           "AND o.providerServiceMapID = :providerServiceMapID ORDER BY o.activityName")
    ArrayList<OutboundCallActivity> findActiveActivitiesByProvider(
        @Param("providerServiceMapID") Integer providerServiceMapID);
    
    // Get ALL active activities as entity list
    @Query("SELECT o FROM OutboundCallActivity o WHERE o.deleted = false ORDER BY o.activityName")
    ArrayList<OutboundCallActivity> findAllActiveActivitiesAsList();
    
    // Get ALL active activities as Object array (activityID, activityName)
    @Query("SELECT o.activityID, o.activityName FROM OutboundCallActivity o " +
           "WHERE o.deleted = false ORDER BY o.activityName")
    ArrayList<Object[]> findAllActiveActivities();
    
    // Update activity name
    @Transactional
    @Modifying
    @Query("UPDATE OutboundCallActivity SET activityName = :activityName, " +
           "modifiedBy = :modifiedBy " +
           "WHERE activityID = :activityID")
    Integer updateActivityName(@Param("activityID") Long activityID, 
                              @Param("activityName") String activityName,
                              @Param("modifiedBy") String modifiedBy);
    
    // Toggle activity status
    @Transactional
    @Modifying
    @Query("UPDATE OutboundCallActivity SET deleted = :deleted, " +
           "modifiedBy = :modifiedBy " +
           "WHERE activityID = :activityID")
    Integer toggleActivityStatus(@Param("activityID") Long activityID, 
                                 @Param("deleted") Boolean deleted,
                                 @Param("modifiedBy") String modifiedBy);

    // Get active call details by createdBy (logged-in user) with activity eagerly loaded
    @Query("SELECT t FROM T_104CoMoOutboundCallDetails t " +
           "LEFT JOIN FETCH t.activity " +
           "WHERE t.deleted = false AND t.createdBy = :createdBy " +
           "ORDER BY t.createdDate DESC")
    ArrayList<T_104CoMoOutboundCallDetails> findActiveCallDetailsByCreatedBy(
        @Param("createdBy") String createdBy);

}
