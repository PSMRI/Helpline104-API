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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.helpline104.data.beneficiarycall.BenCallDisconnectedData;
import com.iemr.helpline104.data.beneficiarycall.BenCallServicesMappingHistory;
import com.iemr.helpline104.data.beneficiarycall.M_subservice;
import com.iemr.helpline104.repository.beneficiarycall.BenCallDisconnectedDataRepo;
import com.iemr.helpline104.repository.beneficiarycall.ServiceMasterRepository;
import com.iemr.helpline104.repository.beneficiarycall.ServicesHistoryRepository;

/**
 * Services availed on a call are recorded as history rows, the sub-service
 * master is served per provider, and a disconnected call is logged only when
 * the dialler gave it a log id.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ServicesHistoryServiceImplTest {

	@Mock
	private ServicesHistoryRepository serviceHistoryRepository;

	@Mock
	private ServiceMasterRepository serviceMasterRepository;

	@Mock
	private BenCallDisconnectedDataRepo benCallDisconnectedDataRepo;

	private ServicesHistoryServiceImpl servicesHistoryService;

	@BeforeEach
	void setUp() {
		servicesHistoryService = new ServicesHistoryServiceImpl();
		servicesHistoryService.setServiceHistoryRepository(serviceHistoryRepository);
		ReflectionTestUtils.setField(servicesHistoryService, "serviceMasterRepository", serviceMasterRepository);
		ReflectionTestUtils.setField(servicesHistoryService, "benCallDisconnectedDataRepo",
				benCallDisconnectedDataRepo);
	}

	@Test
	void createServiceHistoryStoresTheServiceAvailed() {
		BenCallServicesMappingHistory history = new BenCallServicesMappingHistory();
		when(serviceHistoryRepository.save(history)).thenReturn(history);

		assertSame(history, servicesHistoryService.createServiceHistory(history));
	}

	@Test
	void getServicesServesTheProvidersSubServices() {
		List<M_subservice> services = Collections.singletonList(new M_subservice());
		when(serviceMasterRepository.getServices(1)).thenReturn(services);

		assertEquals(services, servicesHistoryService.getServices(1));
	}

	@Test
	void saveCallDisconnectedDataLogsTheDisconnectedCall() throws Exception {
		BenCallDisconnectedData stored = new BenCallDisconnectedData();
		ReflectionTestUtils.setField(stored, "cZentricLogID", 9L);
		when(benCallDisconnectedDataRepo.save(any())).thenReturn(stored);

		assertEquals("Data saved successfully",
				servicesHistoryService.saveCallDisconnectedData("{\"callID\":\"call-1\"}"));
	}

	@Test
	void saveCallDisconnectedDataReportsACallThatWasNotLogged() throws Exception {
		when(benCallDisconnectedDataRepo.save(any())).thenReturn(new BenCallDisconnectedData());

		assertEquals("Error occurred while saving data",
				servicesHistoryService.saveCallDisconnectedData("{\"callID\":\"call-1\"}"));
	}

	@Test
	void saveCallDisconnectedDataRejectsARequestItCannotRead() throws Exception {
		assertEquals("Invalid input", servicesHistoryService.saveCallDisconnectedData("not-json"));
		verify(benCallDisconnectedDataRepo, never()).save(any());
	}
}
