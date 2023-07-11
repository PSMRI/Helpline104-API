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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.iemr.helpline104.utils.exception.IEMRException;

public class ExcelHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] CriteriaHEADERs = { "Filter Name", "Value" };
	static String SHEET = "Users";

	public static ByteArrayInputStream tutorialsToExcel(String[] headers, List<Objects[]> result, Criteria criteria)
			throws IEMRException {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet criteriaSheet = workbook.createSheet("Criteria");

			// Removing milliseconds from timestamp
			criteria.setStart_Date(criteria.getStart_Date().replaceAll("\\.\\d+", ""));
			criteria.setEnd_Date(criteria.getEnd_Date().replaceAll("\\.\\d+", ""));

			// Header
			Row headerRowCriteria = criteriaSheet.createRow(0);
			for (int col = 0; col < CriteriaHEADERs.length; col++) {
				Cell cell = headerRowCriteria.createCell(col);
				cell.setCellValue(CriteriaHEADERs[col]);
			}
			int criteriaRowIndex = 1;
			for (int j = 0; j < 3; j++) {
				Row row = criteriaSheet.createRow(criteriaRowIndex++);
				if (j == 0) {
					row.createCell(0).setCellValue("Start_Date");
					row.createCell(1).setCellValue(criteria.getStart_Date());
				} else if (j == 1) {
					row.createCell(0).setCellValue("End_Date");
					row.createCell(1).setCellValue(criteria.getEnd_Date());
				} else {
					row.createCell(0).setCellValue("Service");
					row.createCell(1).setCellValue(criteria.getService());
				}
			}

			Sheet sheet = workbook.createSheet("Report");

			// Header
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < headers.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(headers[col]);
			}
			int rowIdx = 1;
			int size = 0;
			int sno = 1, a = 1;
			for (Object[] obj : result) {
				Row row = sheet.createRow(rowIdx++);
				size = obj.length;
				row.createCell(0).setCellValue(sno++);
				a = 1;
				for (int i = 0; i < size; i++) {
					Boolean isDate = isValidDate(obj[i] != null ? obj[i].toString() : "");
					if (isDate) {
						row.createCell(a)
								.setCellValue(obj[i] != null ? obj[i].toString().replaceAll("\\.\\d+", "") : "");
					} else
						row.createCell(a).setCellValue(obj[i] != null ? obj[i].toString() : "");
					a++;
				}
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			throw new IEMRException("fail to import data to Excel file: " + e.getMessage());
		}
	}

	public static boolean isValidDate(String inDate) {

		SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {

			Date date = inputDateFormat.parse(inDate);
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}
}
