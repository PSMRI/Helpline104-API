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
package com.iemr.helpline104.service.search;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.repository.epidemicOutbreak.EpidemicOutbreakRepository;
import com.iemr.helpline104.repository.feedback.FeedbackRepository;
import com.iemr.helpline104.repository.foodSafetyCopmlaint.FoodSafetyCopmlaintRepository;
import com.iemr.helpline104.repository.search.SearchOnFeedbackRepo;
import com.iemr.helpline104.repository.search.SearchOnIdRepo;

/**
 * Quick search is not served from this service any more - it answers nothing.
 */
@ExtendWith(MockitoExtension.class)
class SearchOnIdImplTest {

	@Mock
	private SearchOnIdRepo searchOnIdRepo;

	@Mock
	private SearchOnFeedbackRepo searchOnFeedbackRepo;

	@Mock
	private FoodSafetyCopmlaintRepository searchOnFSRepo;

	@Mock
	private FeedbackRepository feedbackRepository;

	@Mock
	private EpidemicOutbreakRepository epidemicOutbreakRepository;

	@InjectMocks
	private SearchOnIdImpl searchService;

	@Test
	void getQuickSearchDataAnswersNothing() throws Exception {
		assertNull(searchService.getQuickSearchData("{\"phoneNo\":\"9999999999\"}"));
	}
}
