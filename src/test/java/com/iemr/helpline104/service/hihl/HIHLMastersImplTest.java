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
package com.iemr.helpline104.service.hihl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.helpline104.data.hihl.T_hihlcocasesheet;
import com.iemr.helpline104.repository.hihl.M_104appetiteRepo;
import com.iemr.helpline104.repository.hihl.M_104bladderRepo;
import com.iemr.helpline104.repository.hihl.M_104bowelRepo;
import com.iemr.helpline104.repository.hihl.M_104courseRepo;
import com.iemr.helpline104.repository.hihl.M_104familyconditionRepo;
import com.iemr.helpline104.repository.hihl.M_104gettingwithfamilyRepo;
import com.iemr.helpline104.repository.hihl.M_104householdworkRepo;
import com.iemr.helpline104.repository.hihl.M_104hygieneselfcareRepo;
import com.iemr.helpline104.repository.hihl.M_104issuesatworkplaceRepo;
import com.iemr.helpline104.repository.hihl.M_104libidoRepo;
import com.iemr.helpline104.repository.hihl.M_104pastmedicalconditionRepo;
import com.iemr.helpline104.repository.hihl.M_104pastpsychiatricconditionRepo;
import com.iemr.helpline104.repository.hihl.M_104precipitatingfactorRepo;
import com.iemr.helpline104.repository.hihl.M_104progressRepo;
import com.iemr.helpline104.repository.hihl.M_104regularworokRepo;
import com.iemr.helpline104.repository.hihl.M_104relationshipRepo;
import com.iemr.helpline104.repository.hihl.M_104sleepRepo;
import com.iemr.helpline104.repository.hihl.M_104treatmenttypeRepo;
import com.iemr.helpline104.repository.hihl.PsychiatricChiefComplaintsRepo;
import com.iemr.helpline104.repository.hihl.T_hihlcocasesheetRepo;

/**
 * The HIHL masters are served as one document holding every undeleted master
 * list, and a counsellor case sheet is stored as the raw request it arrived as.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class HIHLMastersImplTest {

	@Mock
	private M_104appetiteRepo m_104appetiteRepo;

	@Mock
	private M_104bladderRepo m_104bladderRepo;

	@Mock
	private M_104bowelRepo m_104bowelRepo;

	@Mock
	private M_104courseRepo m_104courseRepo;

	@Mock
	private M_104familyconditionRepo m_104familyconditionRepo;

	@Mock
	private M_104gettingwithfamilyRepo m_104gettingwithfamilyRepo;

	@Mock
	private M_104householdworkRepo m_104householdworkRepo;

	@Mock
	private M_104hygieneselfcareRepo m_104hygieneselfcareRepo;

	@Mock
	private M_104issuesatworkplaceRepo m_104issuesatworkplaceRepo;

	@Mock
	private M_104libidoRepo m_104libidoRepo;

	@Mock
	private M_104pastmedicalconditionRepo m_104pastmedicalconditionRepo;

	@Mock
	private M_104pastpsychiatricconditionRepo m_104pastpsychiatricconditionRepo;

	@Mock
	private M_104precipitatingfactorRepo m_104precipitatingfactorRepo;

	@Mock
	private M_104progressRepo m_104progressRepo;

	@Mock
	private M_104regularworokRepo m_104regularworokRepo;

	@Mock
	private M_104relationshipRepo m_104relationshipRepo;

	@Mock
	private M_104sleepRepo m_104sleepRepo;

	@Mock
	private M_104treatmenttypeRepo m_104treatmenttypeRepo;

	@Mock
	private PsychiatricChiefComplaintsRepo psychiatricChiefComplaintsRepo;

	@Mock
	private T_hihlcocasesheetRepo t_hihlcocasesheetRepo;

	@InjectMocks
	private HIHLMastersImpl hihlMasters;

	@Test
	void getHihlMastersServesEveryUndeletedMasterList() {
		String masters = hihlMasters.getHihlMasters();

		assertTrue(masters.contains("m_104appetite"));
		assertTrue(masters.contains("psychiatricChiefComplaints"));
		assertTrue(masters.contains("m_104treatmenttype"));
	}

	@Test
	void saveHihlCasesheetStoresTheRequestAsTheCaseSheet() throws Exception {
		T_hihlcocasesheet stored = new T_hihlcocasesheet();
		org.springframework.test.util.ReflectionTestUtils.setField(stored, "id", 9L);
		when(t_hihlcocasesheetRepo.save(any())).thenReturn(stored);

		String response = hihlMasters.saveHihlCasesheet("{\"beneficiaryRegID\":12,\"remarks\":\"anxious\"}");

		assertEquals("data saved successfully with ID : 9", response);
	}

	@Test
	void getHihlCasesheetHistoryInfoRendersEveryStoredCaseSheet() {
		T_hihlcocasesheet stored = new T_hihlcocasesheet();
		stored.setCocasesheet("{\"remarks\":\"anxious\"}");
		when(t_hihlcocasesheetRepo.getHihlCasesheets(12L)).thenReturn(Collections.singletonList(stored));

		assertTrue(hihlMasters.getHihlCasesheetHistoryInfo(12L).contains("anxious"));
	}

	@Test
	void getHihlCasesheetHistoryInfoIsEmptyForABeneficiaryWithoutCaseSheets() {
		when(t_hihlcocasesheetRepo.getHihlCasesheets(12L)).thenReturn(Collections.emptyList());

		assertEquals("[]", hihlMasters.getHihlCasesheetHistoryInfo(12L));
	}

	@Test
	void getHihlCasesheetDataRendersTheOneCaseSheet() {
		T_hihlcocasesheet stored = new T_hihlcocasesheet();
		stored.setCocasesheet("{\"remarks\":\"anxious\"}");
		when(t_hihlcocasesheetRepo.findById(9L)).thenReturn(Optional.of(stored));

		assertTrue(hihlMasters.getHihlCasesheetData(9L).contains("anxious"));
	}
}
