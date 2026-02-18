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

import com.iemr.helpline104.data.comoOutbound.OutboundCallActivity;
import com.iemr.helpline104.data.comoOutbound.T_104CoMoOutboundCallDetails;

public interface OutboundCallActivityService {

        ArrayList<OutboundCallActivity> getActiveActivitiesByProvider(Integer providerServiceMapID);

        ArrayList<Object[]> getAllActiveActivities();

        OutboundCallActivity saveActivity(OutboundCallActivity activity);

        Integer updateActivityName(Long activityID, String activityName, String modifiedBy);

        Integer toggleActivityStatus(Long activityID, Boolean deleted, String modifiedBy);

        void validateCallStatus(String callStatus);

        ArrayList<T_104CoMoOutboundCallDetails> getCallActivityHistory();

        T_104CoMoOutboundCallDetails saveCallDetails(T_104CoMoOutboundCallDetails callDetails);

}
