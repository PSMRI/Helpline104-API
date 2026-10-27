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
package com.iemr.helpline104.service.beneficiarycall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.helpline104.data.beneficiarycall.BeneficiaryCall;
import com.iemr.helpline104.repository.beneficiarycall.BeneficiaryCallRepository;

/**
 * A new call is always recorded as a 104 call, and picks up the beneficiary
 * already attached to the same dialler call ID when the caller did not send
 * one.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BeneficiaryCallServiceImplTest {

	@Mock
	private BeneficiaryCallRepository beneficiaryCallRepository;

	private BeneficiaryCallServiceImpl beneficiaryCallService;

	@BeforeEach
	void setUp() {
		beneficiaryCallService = new BeneficiaryCallServiceImpl();
		ReflectionTestUtils.setField(beneficiaryCallService, "beneficiaryCallRepository", beneficiaryCallRepository);
	}

	@Test
	void createCallReusesTheBeneficiaryBehindTheDiallerCall() {
		BeneficiaryCall call = new BeneficiaryCall();
		call.setCallID("call-1");
		when(beneficiaryCallRepository.findBeneficiaryRegIDByCallID("call-1"))
				.thenReturn(Collections.singletonList(12L));
		when(beneficiaryCallRepository.save(call)).thenReturn(call);

		BeneficiaryCall response = beneficiaryCallService.createCall(call);

		assertEquals(Long.valueOf(12L), response.getBeneficiaryRegID());
		assertEquals(Boolean.FALSE, response.getIs1097());
	}

	@Test
	void createCallLeavesTheBeneficiaryUnsetWhenTheDiallerCallIsNew() {
		BeneficiaryCall call = new BeneficiaryCall();
		call.setCallID("call-1");
		when(beneficiaryCallRepository.findBeneficiaryRegIDByCallID("call-1")).thenReturn(Collections.emptyList());
		when(beneficiaryCallRepository.save(call)).thenReturn(call);

		assertNull(beneficiaryCallService.createCall(call).getBeneficiaryRegID());
	}

	@Test
	void createCallKeepsTheBeneficiaryTheCallerSent() {
		BeneficiaryCall call = new BeneficiaryCall();
		call.setCallID("call-1");
		call.setBeneficiaryRegID(13L);
		when(beneficiaryCallRepository.save(call)).thenReturn(call);

		assertEquals(Long.valueOf(13L), beneficiaryCallService.createCall(call).getBeneficiaryRegID());
	}

	@Test
	void updateBeneficiaryIDInCallReportsTheRowsItTouched() {
		when(beneficiaryCallRepository.updateBeneficiaryIDInCall(4L, 12L)).thenReturn(1);

		assertEquals(Integer.valueOf(1), beneficiaryCallService.updateBeneficiaryIDInCall(4L, 12L));
	}
}
