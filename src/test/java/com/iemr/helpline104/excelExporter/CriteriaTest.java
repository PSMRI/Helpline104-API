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
package com.iemr.helpline104.excelExporter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.iemr.helpline104.data.EntityAccessors;

/**
 * The exported workbook carries the filter criteria on its own sheet, and dates
 * in the report rows are recognised by the exporter's date check.
 */
class CriteriaTest {

	@Test
	void everyCriteriaAccessorRoundTrips() {
		EntityAccessors.assertEntities(Criteria.class);
	}

	@Test
	void isValidDateAcceptsAnIsoDate() {
		assertTrue(ExcelHelper.isValidDate("2024-01-31"));
	}

	@Test
	void isValidDateRejectsAnythingElse() {
		assertFalse(ExcelHelper.isValidDate("not a date"));
		assertFalse(ExcelHelper.isValidDate(""));
	}
}
