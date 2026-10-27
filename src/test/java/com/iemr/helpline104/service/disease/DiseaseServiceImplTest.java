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
package com.iemr.helpline104.service.disease;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.helpline104.data.disease.Disease;
import com.iemr.helpline104.repository.disease.DiseaseRepository;
import com.iemr.helpline104.utils.exception.IEMRException;
import com.iemr.helpline104.utils.mapper.OutputMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;

/**
 * The disease master is listed page by page through a criteria query, and
 * deactivating, activating and updating a disease each report on the row count
 * the update touched.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DiseaseServiceImplTest {

	@Mock
	private DiseaseRepository diseaseRepository;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private EntityManager entityManager;

	@Mock
	private TypedQuery<Disease> typedQuery;

	@InjectMocks
	private DiseaseServiceImpl diseaseService;

	@BeforeEach
	void setUp() {
		new OutputMapper();
		ReflectionTestUtils.setField(diseaseService, "entityManager", entityManager);
		doReturn(typedQuery).when(entityManager).createQuery(any(CriteriaQuery.class));
		when(typedQuery.getResultList()).thenReturn(Collections.singletonList(new Disease(4, "Malaria")));
	}

	@Test
	void saveDiseaseStoresEveryDiseaseInTheRequest() {
		when(diseaseRepository.saveAll(any()))
				.thenReturn(Collections.singletonList(new Disease(4, "Malaria")));

		assertTrue(diseaseService.saveDisease("[{\"diseaseName\":\"Malaria\"}]").contains("Malaria"));
	}

	@Test
	void deleteDiseaseReportsADeactivatedDisease() {
		when(diseaseRepository.deleteDisease(4, true)).thenReturn(1);

		assertEquals("deactivated successfully",
				diseaseService.deleteDisease("{\"diseasesummaryID\":4,\"deleted\":true}"));
	}

	@Test
	void deleteDiseaseReportsAReactivatedDisease() {
		when(diseaseRepository.deleteDisease(4, false)).thenReturn(1);

		assertEquals("activated successfully",
				diseaseService.deleteDisease("{\"diseasesummaryID\":4,\"deleted\":false}"));
	}

	@Test
	void deleteDiseaseLeavesAnIncompleteRequestAlone() {
		assertEquals("activated successfully", diseaseService.deleteDisease("{\"diseasesummaryID\":4}"));
		verify(diseaseRepository, org.mockito.Mockito.never()).deleteDisease(anyInt(), anyBoolean());
	}

	@Test
	void getDiseaseListsThePageTheCallerAskedFor() throws Exception {
		when(diseaseRepository.getDiseaseCountExcludingDeleted()).thenReturn(3);

		String response = diseaseService.getDisease("{\"pageNo\":1,\"pageSize\":2}");

		assertTrue(response.contains("Malaria"));
		assertTrue(response.contains("totalPages=2"));
		verify(typedQuery).setFirstResult(0);
		verify(typedQuery).setMaxResults(2);
	}

	@Test
	void getDiseaseListsEveryDiseaseWithoutPagination() throws Exception {
		when(diseaseRepository.getDiseaseCountExcludingDeleted()).thenReturn(4);

		String response = diseaseService.getDisease("{}");

		assertTrue(response.contains("Malaria"));
		assertTrue(response.contains("totalPages=0"));
	}

	@Test
	void getDiseaseCountsTheLastPartialPage() throws Exception {
		when(diseaseRepository.getDiseaseCountExcludingDeleted()).thenReturn(4);

		assertTrue(diseaseService.getDisease("{\"pageNo\":1,\"pageSize\":2}").contains("totalPages=2"));
	}

	@Test
	void getDiseaseRejectsANonPositivePage() {
		when(diseaseRepository.getDiseaseCountExcludingDeleted()).thenReturn(3);

		IEMRException thrown = assertThrows(IEMRException.class,
				() -> diseaseService.getDisease("{\"pageNo\":0,\"pageSize\":2}"));

		assertTrue(thrown.getMessage().contains("Invalid pagination parameter"));
	}

	@Test
	void getDiseaseRejectsARequestItCannotParse() {
		assertThrows(IEMRException.class, () -> diseaseService.getDisease("null"));
	}

	@Test
	void updateDiseaseReportsTheUpdatedDisease() throws Exception {
		when(diseaseRepository.updateDisease(anyString(), any(), any(), any(), any(), any(), any(), any(), any(),
				any(), any(), anyInt())).thenReturn(1);

		assertEquals("Updated successfully",
				diseaseService.updateDisease("{\"diseasesummaryID\":4,\"diseaseName\":\"Malaria\"}"));
	}

	@Test
	void updateDiseaseReportsAnUpdateThatTouchedNothing() throws Exception {
		when(diseaseRepository.updateDisease(anyString(), any(), any(), any(), any(), any(), any(), any(), any(),
				any(), any(), anyInt())).thenReturn(0);

		assertEquals("Update failed",
				diseaseService.updateDisease("{\"diseasesummaryID\":4,\"diseaseName\":\"Malaria\"}"));
	}

	@Test
	void getAvailableDiseasesProjectsEveryReadableRow() throws Exception {
		when(diseaseRepository.getAvailableDiseases())
				.thenReturn(Arrays.asList(null, new Object[0], new Object[] { 4, "Malaria" }));

		assertTrue(diseaseService.getAvailableDiseases().contains("Malaria"));
	}

	@Test
	void getDiseasesByIDReadsTheOneDisease() throws Exception {
		when(diseaseRepository.getDiseasesByID(4)).thenReturn(new Disease(4, "Malaria"));

		assertTrue(diseaseService.getDiseasesByID("{\"diseasesummaryID\":4}").contains("Malaria"));
	}
}
