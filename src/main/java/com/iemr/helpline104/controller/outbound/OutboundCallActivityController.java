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

package com.iemr.helpline104.controller.outbound;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iemr.helpline104.data.comoOutbound.OutboundCallActivity;
import com.iemr.helpline104.data.comoOutbound.T_104CoMoOutboundCallDetails;
import com.iemr.helpline104.service.outbound.OutboundCallActivityService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

@RestController
@RequestMapping(value = "/outbound")
public class OutboundCallActivityController {

    @Autowired
    private OutboundCallActivityService activityService;

    @Autowired
    private InputMapper inputMapper;

    // Get activities by providerServiceMapID
    @PostMapping(value = "/activities", headers = "Authorization")
    public String getActiveActivities(@RequestBody String request) {
        OutputResponse output = new OutputResponse();
        try {
            JsonObject obj = new JsonParser().parse(request).getAsJsonObject();

            ArrayList<OutboundCallActivity> activities;

            if (obj.has("providerServiceMapID") && obj.get("providerServiceMapID") != null
                    && !obj.get("providerServiceMapID").isJsonNull()) {
                Integer providerServiceMapID = obj.get("providerServiceMapID").getAsInt();
                activities = activityService.getActiveActivitiesByProvider(providerServiceMapID);
            } else {
                // Return all active activities if providerServiceMapID not provided
                activities = activityService.getActiveActivitiesByProvider(null);
            }

            output.setResponse(new Gson().toJson(activities));
        } catch (Exception e) {
            output.setError(e);
        }
        return output.toString();
    }

    // Get ALL active activities (for admin/supervisor)
    @GetMapping(value = "/activities/all", headers = "Authorization")
    public String getAllActiveActivities() {
        OutputResponse output = new OutputResponse();
        try {
            ArrayList<OutboundCallActivity> activities = activityService.getAllActivities();
            output.setResponse(new Gson().toJson(activities));
        } catch (Exception e) {
            output.setError(e);
        }
        return output.toString();
    }

    // Save/Create activity
    @PostMapping(value = "/activity", headers = "Authorization")
    public String saveActivity(@RequestBody String request) {
        OutputResponse output = new OutputResponse();
        try {
            OutboundCallActivity activity = inputMapper.gson().fromJson(request, OutboundCallActivity.class);
            OutboundCallActivity savedObj = activityService.saveActivity(activity);
            output.setResponse("Activity saved successfully with ID: " + savedObj.getActivityID());
        } catch (Exception e) {
            output.setError(e);
        }
        return output.toString();
    }
    
    // Update activity name
    @PutMapping(value = "/activity/name", headers = "Authorization")
    public String updateActivityName(@RequestBody String request) {
        OutputResponse output = new OutputResponse();
        try {
            JsonObject obj = new JsonParser().parse(request).getAsJsonObject();
            Long activityID = obj.get("activityID").getAsLong();
            String activityName = obj.get("activityName").getAsString();
            String modifiedBy = obj.get("modifiedBy").getAsString();

            Integer updated = activityService.updateActivityName(activityID, activityName, modifiedBy);
            output.setResponse("Activity name updated successfully. Rows affected: " + updated);
        } catch (Exception e) {
            output.setError(e);
        }
        return output.toString();
    }

    // Toggle activity status (enable/disable)
    @PutMapping(value = "/activity/status", headers = "Authorization")
    public String toggleActivityStatus(@RequestBody String request) {
        OutputResponse output = new OutputResponse();
        try {
            JsonObject obj = new JsonParser().parse(request).getAsJsonObject();
            Long activityID = obj.get("activityID").getAsLong();
            Boolean deleted = obj.get("deleted").getAsBoolean();
            String modifiedBy = obj.get("modifiedBy").getAsString();

            Integer updated = activityService.toggleActivityStatus(activityID, deleted, modifiedBy);
            output.setResponse("Activity status updated successfully. Rows affected: " + updated);
        } catch (Exception e) {
            output.setError(e);
        }
        return output.toString();
    }

    // Save call details on close
    @PostMapping(value = "/callDetails/save", headers = "Authorization")
    public String saveCallDetails(@RequestBody String request) {
        OutputResponse output = new OutputResponse();
        try {
            T_104CoMoOutboundCallDetails callDetails = inputMapper.gson().fromJson(request,
                    T_104CoMoOutboundCallDetails.class);
            T_104CoMoOutboundCallDetails savedObj = activityService.saveCallDetails(callDetails);
            output.setResponse(new Gson().toJson(savedObj));
        } catch (Exception e) {
            output.setError(e);
        }
        return output.toString();
    }

    // Get call activity history
    @GetMapping(value = "/callActivity/history", headers = "Authorization")
    public String getCallActivityHistory() {
        OutputResponse output = new OutputResponse();
        try {
            ArrayList<?> history = activityService.getCallActivityHistory();
            output.setResponse(new Gson().toJson(history));
        } catch (Exception e) {
            output.setError(e);
        }
        return output.toString();
    }
}