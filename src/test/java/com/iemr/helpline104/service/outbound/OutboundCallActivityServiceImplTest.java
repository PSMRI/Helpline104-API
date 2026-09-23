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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.helpline104.data.comoOutbound.OutboundCallActivity;
import com.iemr.helpline104.data.comoOutbound.T_104CoMoOutboundCallDetails;
import com.iemr.helpline104.repository.comoOutbound.CoMoOutboundCallRepository;
import com.iemr.helpline104.repository.comoOutbound.OutboundCallActivityRepository;

/**
 * Outbound call activities are served per provider or in full, and a logged
 * call is accepted only with one of the three recorded call statuses. Reading
 * an agent's calls back fills in the activity name behind each activity id.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OutboundCallActivityServiceImplTest {

	@Mock
	private OutboundCallActivityRepository activityRepository;

	@Mock
	private CoMoOutboundCallRepository outboundCallRepository;

	@InjectMocks
	private OutboundCallActivityServiceImpl outboundCallActivityService;

	private static OutboundCallActivity activity(Long id, String name) {
		OutboundCallActivity activity = new OutboundCallActivity();
		activity.setActivityID(id);
		activity.setActivityName(name);
		return activity;
	}

	private static T_104CoMoOutboundCallDetails callDetails(String callStatus, Long activityID) {
		T_104CoMoOutboundCallDetails details = new T_104CoMoOutboundCallDetails();
		details.setCallStatus(callStatus);
		details.setActivityID(activityID);
		return details;
	}

	@Test
	void getActiveActivitiesByProviderServesTheProvidersActivities() {
		ArrayList<OutboundCallActivity> activities = new ArrayList<>(
				Collections.singletonList(activity(9L, "Follow up")));
		when(activityRepository.findActiveActivitiesByProvider(1)).thenReturn(activities);

		assertEquals(activities, outboundCallActivityService.getActiveActivitiesByProvider(1));
	}

	@Test
	void getActiveActivitiesByProviderServesEveryActivityWithoutAProvider() {
		ArrayList<OutboundCallActivity> activities = new ArrayList<>(
				Collections.singletonList(activity(9L, "Follow up")));
		when(activityRepository.findAllActiveActivitiesAsList()).thenReturn(activities);

		assertEquals(activities, outboundCallActivityService.getActiveActivitiesByProvider(null));
	}

	@Test
	void getAllActivitiesServesTheDeletedOnesToo() {
		ArrayList<OutboundCallActivity> activities = new ArrayList<>(
				Collections.singletonList(activity(9L, "Follow up")));
		when(activityRepository.findAllActivities()).thenReturn(activities);

		assertEquals(activities, outboundCallActivityService.getAllActivities());
	}

	@Test
	void saveActivityMarksANewActivityAsUndeleted() {
		OutboundCallActivity activity = activity(null, "Follow up");
		when(activityRepository.save(activity)).thenReturn(activity);

		outboundCallActivityService.saveActivity(activity);

		assertEquals(Boolean.FALSE, activity.getDeleted());
	}

	@Test
	void saveActivityKeepsAnAlreadyDecidedDeletedFlag() {
		OutboundCallActivity activity = activity(9L, "Follow up");
		activity.setDeleted(true);
		when(activityRepository.save(activity)).thenReturn(activity);

		outboundCallActivityService.saveActivity(activity);

		assertEquals(Boolean.TRUE, activity.getDeleted());
	}

	@Test
	void updateActivityNameReportsTheRowsItTouched() {
		when(activityRepository.updateActivityName(9L, "Follow up", "agent")).thenReturn(1);

		assertEquals(Integer.valueOf(1), outboundCallActivityService.updateActivityName(9L, "Follow up", "agent"));
	}

	@Test
	void toggleActivityStatusReportsTheRowsItTouched() {
		when(activityRepository.toggleActivityStatus(9L, true, "agent")).thenReturn(1);

		assertEquals(Integer.valueOf(1), outboundCallActivityService.toggleActivityStatus(9L, true, "agent"));
	}

	@Test
	void validateCallStatusAcceptsEachRecordedStatus() {
		outboundCallActivityService.validateCallStatus("Answered");
		outboundCallActivityService.validateCallStatus("Not Answered");
		outboundCallActivityService.validateCallStatus("Did Not Want Further Call");
	}

	@Test
	void validateCallStatusRejectsAMissingStatus() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> outboundCallActivityService.validateCallStatus("  "));

		assertTrue(thrown.getMessage().contains("mandatory"));
	}

	@Test
	void validateCallStatusRejectsAnUnknownStatus() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> outboundCallActivityService.validateCallStatus("Busy"));

		assertTrue(thrown.getMessage().contains("Valid values are"));
	}

	@Test
	void saveCallDetailsMarksANewCallAsUndeleted() {
		T_104CoMoOutboundCallDetails details = callDetails("Answered", 9L);
		when(outboundCallRepository.save(details)).thenReturn(details);

		assertSame(details, outboundCallActivityService.saveCallDetails(details));
		assertEquals(Boolean.FALSE, details.getDeleted());
	}

	@Test
	void saveCallDetailsRejectsAnUnknownCallStatus() {
		assertThrows(IllegalArgumentException.class,
				() -> outboundCallActivityService.saveCallDetails(callDetails("Busy", 9L)));
	}

	@Test
	void getActiveCallDetailsByUserFillsInTheActivityNames() {
		T_104CoMoOutboundCallDetails withActivity = callDetails("Answered", 9L);
		T_104CoMoOutboundCallDetails withoutActivity = callDetails("Answered", null);
		when(outboundCallRepository.findActiveCallDetailsByCreatedBy("agent"))
				.thenReturn(new ArrayList<>(Arrays.asList(withActivity, withoutActivity)));
		when(activityRepository.findAllById(any()))
				.thenReturn(Collections.singletonList(activity(9L, "Follow up")));

		outboundCallActivityService.getActiveCallDetailsByUser("agent");

		assertEquals("Follow up", withActivity.getActivityName());
		assertNull(withoutActivity.getActivityName());
	}

	@Test
	void getActiveCallDetailsByUserLeavesCallsWithoutAnActivityAlone() {
		T_104CoMoOutboundCallDetails details = callDetails("Answered", null);
		when(outboundCallRepository.findActiveCallDetailsByCreatedBy("agent"))
				.thenReturn(new ArrayList<>(Collections.singletonList(details)));

		assertEquals(1, outboundCallActivityService.getActiveCallDetailsByUser("agent").size());
		assertNull(details.getActivityName());
	}
}
