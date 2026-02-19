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

package com.iemr.helpline104.service.outbound;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iemr.helpline104.repository.comoOutbound.OutboundCallActivityRepository;
import com.iemr.helpline104.data.comoOutbound.OutboundCallActivity;
import com.iemr.helpline104.data.comoOutbound.T_104CoMoOutboundCallDetails;
import com.iemr.helpline104.repository.comoOutbound.CoMoOutboundCallRepository;

@Service
public class OutboundCallActivityServiceImpl implements OutboundCallActivityService {

    @Autowired
    private OutboundCallActivityRepository activityRepository;

    @Autowired
    private CoMoOutboundCallRepository outboundCallRepository;

    private static final String[] VALID_CALL_STATUSES = {
            "Answered",
            "Not Answered",
            "Did Not Want Further Call"
    };

    @Override
    public ArrayList<OutboundCallActivity> getActiveActivitiesByProvider(Integer providerServiceMapID) {
        if (providerServiceMapID == null) {
            return activityRepository.findAllActiveActivitiesAsList();
        }
        return activityRepository.findActiveActivitiesByProvider(providerServiceMapID);
    }

    @Override
    public ArrayList<OutboundCallActivity> getAllActivities() {
        return activityRepository.findAllActivities();
    }

    @Override
    public OutboundCallActivity saveActivity(OutboundCallActivity activity) {
        if (activity.getDeleted() == null) {
            activity.setDeleted(false);
        }
        return activityRepository.save(activity);
    }

    @Override
    public Integer updateActivityName(Long activityID, String activityName, String modifiedBy) {
        return activityRepository.updateActivityName(activityID, activityName, modifiedBy);
    }

    @Override
    public Integer toggleActivityStatus(Long activityID, Boolean deleted, String modifiedBy) {
        return activityRepository.toggleActivityStatus(activityID, deleted, modifiedBy);
    }

    @Override
    public void validateCallStatus(String callStatus) {
        if (callStatus == null || callStatus.trim().isEmpty()) {
            throw new IllegalArgumentException("Call status is mandatory");
        }

        if (!Arrays.asList(VALID_CALL_STATUSES).contains(callStatus)) {
            throw new IllegalArgumentException(
                    "Invalid call status. Valid values are: " + String.join(", ", VALID_CALL_STATUSES));
        }
    }

    @Override
    public ArrayList<T_104CoMoOutboundCallDetails> getCallActivityHistory() {
        return outboundCallRepository.getCallActivityHistory();
    }

    @Override
    public T_104CoMoOutboundCallDetails saveCallDetails(T_104CoMoOutboundCallDetails callDetails) {
        validateCallStatus(callDetails.getCallStatus());
        if (callDetails.getDeleted() == null) {
            callDetails.setDeleted(false);
        }
        return outboundCallRepository.save(callDetails);
    }
}