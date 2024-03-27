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
package com.iemr.helpline104.service.secondaryCrmReports;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.secondary.data.BencallReport;
import com.iemr.helpline104.secondary.repo.report.SecondaryReportRepo;
import com.iemr.helpline104.utils.exception.IEMRException;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iemr.helpline104.data.report.BenmedhistoryReport;
import com.iemr.helpline104.data.report.BloodrequestReport;
import com.iemr.helpline104.data.report.FeedbackReport;
import com.iemr.helpline104.excelExporter.Criteria;
import com.iemr.helpline104.excelExporter.ExcelHelper;;
@Service
public class CRMSecondaryReportServiceImpl implements CRMSecondaryReportService {

	@Autowired
	private SecondaryReportRepo secondaryReportRepo;

	@Override
	public ByteArrayInputStream getRegistrationReport(String jsonRequest, String filename) throws Exception {

		String[] headers = { "SNo", "Beneficiary ID", "Title", "First Name", "Middle Name", "Last Name", "Dob",
				"Gender", "Community", "Education", "Father Name", "Marital Status", "Health Care Worker",
				"Phone Number", "Phone Num1", "Phone Num2", "Phone Num3", "Phone Num4", "Emergency Registeration",
				"Preferred Phone Num", "Perm District", "Perm Sub District", "Perm Village", "Call Type",
				"Call Sub Type", "Call Received User ID", "Call End User ID", "Date", "Agent ID", "Agent Name" };
		ByteArrayInputStream response = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			BencallReport bencallReportRequest = objectMapper.readValue(jsonRequest, BencallReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bencallReportRequest.getStartDateTime().toString());
			c.setEnd_Date(bencallReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getRegisterationReport(bencallReportRequest.getStartDateTime(),
					bencallReportRequest.getEndDateTime(), bencallReportRequest.getAgentID(),
					bencallReportRequest.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result,  c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}

	@Override
	public ByteArrayInputStream getHAOReport(String jsonRequest, String filename) throws Exception {

		String[] headers = { "SNo", "Action By HAO", "Action By P D", "Allergies", "Selected Diagnosis",
				"Selected Diagnosis ID", "Added Advice", "Disease Summary ID","Algorithm", "Prescription ID", "Request ID",
				"Ben History ID", "First Name", "Last Name", "Patient Name", "Is Self", "Date Of birth", "Patient Age",
				"Gender", "Health Care Worker", "Phone Number", "District", "Sub District", "Village", "Ben Call ID",
				"Call Type", "Call Sub Type", "Call Received User ID", "Call End User ID", "Symptom ID",
				"Action By C O", "Action By M O", "Date", "Beneficiary ID", "Agent ID", "Agent Name",
				"Have You Travelled In Last14 Days", "Travel Type", "Symptom", "Covid19 Contact History",
				"Did You Seek Medical Consultation", "Suspected Covid19", "Recommendation" };
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			BencallReport bencallReportRequest = objectMapper.readValue(jsonRequest, BencallReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bencallReportRequest.getStartDateTime().toString());
			c.setEnd_Date(bencallReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getHAOReport(bencallReportRequest.getStartDateTime(),
					bencallReportRequest.getEndDateTime(), bencallReportRequest.getAgentID(),
					bencallReportRequest.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}

	@Override
	public ByteArrayInputStream getMOReport(String jsonRequest, String filename) throws Exception {
		String[] headers = { "SNo", "Action By HAO", "Action By P D", "Allergies","Disease Summary", "Selected Diagnosis",
				"Selected Diagnosis ID", "Added Advice", "Disease Summary ID", "Prescription ID", "Request ID",
				"Ben History ID", "First Name", "Last Name", "Patient Name", "Is Self", "Date Of birth", "Patient Age",
				"Gender", "Health Care Worker", "Phone Number", "District", "Sub District", "Village", "Ben Call ID",
				"Call Type", "Call Sub Type", "Call Received User ID", "Call End User ID", "Symptom ID",
				"Action By C O", "Action By M O", "Date", "Beneficiary ID", "Drug Name", "Frequency", "Dosage",
				"No Of Days", "Agent ID", "Agent Name", "Have You Travelled In Last14 Days", "Travel Type", "Symptom",
				"Covid19 Contact History", "Did You Seek Medical Consultation", "Suspected Covid19", "Recommendation" };
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			BencallReport bencallReportRequest = objectMapper.readValue(jsonRequest, BencallReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bencallReportRequest.getStartDateTime().toString());
			c.setEnd_Date(bencallReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getMOReport(bencallReportRequest.getStartDateTime(),
					bencallReportRequest.getEndDateTime(), bencallReportRequest.getAgentID(),
					bencallReportRequest.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result,  c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}

	@Override
	public ByteArrayInputStream getMODetailsReport(String jsonRequest, String filename) throws Exception {
		String[] headers = { "SNo", "Disease Summary", "Count" };
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			BenmedhistoryReport bencallReportRequest = objectMapper.readValue(jsonRequest,
					BenmedhistoryReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bencallReportRequest.getStartDateTime().toString());
			c.setEnd_Date(bencallReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getMODetailsReport(bencallReportRequest.getStartDateTime(),
					bencallReportRequest.getEndDateTime(),
					bencallReportRequest.getProviderServiceMapID(), bencallReportRequest.getDistrictID(),
					bencallReportRequest.getSubDistrictID(), bencallReportRequest.getVillageID(),
					bencallReportRequest.getRoleID(), bencallReportRequest.getLocationID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}

	@Override
	public ByteArrayInputStream getCOReport(String jsonRequest, String filename) throws Exception {

		String[] headers = { "SNo", "Beneficiary ID", "Action By P D", "Allergies","Disease Summary", "Selected Diagnosis",
				"Selected Diagnosis ID", "Added Advice", "Disease Summary ID", "Prescription ID", "Request ID",
				"Ben History ID", "First Name", "Last Name", "Patient Name", "Is Self", "Date Of birth", "Patient Age",
				"Gender", "Health Care Worker", "Phone Number", "District", "Sub District", "Village", "Ben Call ID",
				"Call Type", "Call Sub Type", "Call Received User ID", "Symptom ID", "Action By H A O", "Action By C O",
				"Action By M O", "Date", "Call End User ID", "Category", "Sub Category", "Agent ID",
				"Agent Name" };
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			BencallReport bencallReportRequest = objectMapper.readValue(jsonRequest, BencallReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bencallReportRequest.getStartDateTime().toString());
			c.setEnd_Date(bencallReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getCOReport(bencallReportRequest.getStartDateTime(),
					bencallReportRequest.getEndDateTime(), bencallReportRequest.getAgentID(),
					bencallReportRequest.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result,  c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}

	@Override
	public ByteArrayInputStream getMentalHealthReport(String jsonRequest, String filename) throws Exception {

		String[] headers = new String[3];
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			List<Object[]> result = null;
			BenmedhistoryReport bloodrequestReport = objectMapper.readValue(jsonRequest,
					BenmedhistoryReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bloodrequestReport.getStartDateTime().toString());
			c.setEnd_Date(bloodrequestReport.getEndDateTime().toString());
			c.setService(filename);
			if (bloodrequestReport.getSearchCriteria().equalsIgnoreCase("Guidelines")) {
				headers[0] = "SNo";
				headers[1] = "Guidelines";
				headers[2] = "Count";
				result = secondaryReportRepo.getGuidelinesReport(bloodrequestReport.getStartDateTime(),
						bloodrequestReport.getEndDateTime(),
						bloodrequestReport.getProviderServiceMapID());
			} else {
				headers[0] = "SNo";
				headers[1] = "Category";
				headers[2] = "Count";
				result = secondaryReportRepo.getCategoryReport(bloodrequestReport.getStartDateTime(),
						bloodrequestReport.getEndDateTime(),
						bloodrequestReport.getProviderServiceMapID());
			}

			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c);
			else
				throw new IEMRException("No data found");

		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}

	@Override
	public ByteArrayInputStream getGrievanceReport(String jsonRequest, String filename) throws Exception {

		String[] headers = { "SNo", "Beneficiary ID", "Feedback Against", "Feedback ID", "Feedback Nature Name",
				"Feedback Status Name", "Email Status ID", "Feedback Type Name", "Feedback", "Designation",
				"Request ID", "Service Availed Date", "Severity Name", "Sms Phone No", "User ID", "Institute Type Name",
				"Institution Name",
				"District Branch Name", "District Name", "Block Name", "Date", "First Name", "Last Name",
				"Date Of birth", "Gender", "Health Care Worker", "Phone Number", "State Name", "District",
				"Sub District", "Village", "Ben Call ID", "Call Type", "Call Sub Type", "Call Received User ID",
				"Call End User ID", "Agent ID", "Agent Name" };
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			FeedbackReport feedbackReportRequest = objectMapper.readValue(jsonRequest, FeedbackReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(feedbackReportRequest.getStartDateTime().toString());
			c.setEnd_Date(feedbackReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getGrievanceReport(feedbackReportRequest.getStartDateTime(),
					feedbackReportRequest.getEndDateTime(), feedbackReportRequest.getAgentID(),
					feedbackReportRequest.getProviderServiceMapID(), feedbackReportRequest.getFeedbackTypeID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result,  c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}

	@Override
	public ByteArrayInputStream getPrescriptionReport(String jsonRequest, String filename) throws Exception {
		// Date First Name Last Name Date Of Birth Gender Phone Number Health Care
		// Worker District Sub District Village Call Type Call Sub Type Ben Call ID Call
		// End User ID Call Received User ID Agent ID Agent Name

		String[] headers = { "SNo", "Beneficiary ID", "Diagnosis Provided", "Drug Form", "Drug Group Name", "Drug Name",
				"Drug Route", "Frequency", "No Of Days", "Dosage", "Time To Consume", "Prescription ID", "Remarks",
				"Valid Till Date", "User ID", "Side Effects", "Date", "First Name", "Last Name", "Date Of birth",
				"Gender", "Phone Number", "Health Care Worker", "District", "Sub District", "Village", "Call Type",
				"Call Sub Type", "Ben Call ID", "Call End User ID", "Call Received User ID", "Agent ID", "Agent Name" };
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			BencallReport bencallReportRequest = objectMapper.readValue(jsonRequest, BencallReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bencallReportRequest.getStartDateTime().toString());
			c.setEnd_Date(bencallReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getPrescriptionReport(bencallReportRequest.getStartDateTime(),
					bencallReportRequest.getEndDateTime(), bencallReportRequest.getAgentID(),
					bencallReportRequest.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}
	
	@Override
	public ByteArrayInputStream getSchemeServiceReport(String jsonRequest, String filename) throws Exception {

		String[] headers = { "SNo", "Beneficiary ID", "Scheme Desc", "Scheme ID", "Scheme Name", "Scheme Service ID",
				"Remarks", "Request ID", "Date", "First Name", "Last Name", "Date Of Birth", "Gender", 
				"Health Care Worker", "Phone Number", "District", "Sub District", "Village", "Call Type",
				"Call Sub Type", "Ben Call ID", "Call Received User ID", "Call End User ID", "Agent ID", "Agent Name"};
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			BencallReport bencallReportRequest = objectMapper.readValue(jsonRequest, BencallReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bencallReportRequest.getStartDateTime().toString());
			c.setEnd_Date(bencallReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getSchemeServiceReport(bencallReportRequest.getStartDateTime(),
					bencallReportRequest.getEndDateTime(), bencallReportRequest.getAgentID(),
					bencallReportRequest.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}
	
	@Override
	public ByteArrayInputStream getFoodSafetyReport(String jsonRequest, String filename) throws Exception {

		String[] headers = { "SNo","Beneficiary ID", "Feedback Type", "Associated Symptoms", "Food Consumed From", "Fromadd Services Date", 
				"Fs Complaint ID", "History Of Diet", "Is Abdominal Pain", "Is Chills Or Rigors", "Is Dehydration", "Is Diarrhea", "Is Food Consumed",
				"Is Giddiness", "Is Rashes", "Is Vomiting", "Type Of Food", "Type Of Request", "Remarks", "Request ID", "Date", "First Name", "Last Name", "Patient Name",
				"Patient Age", "Date Of Birth", "Gender", "Health Care Worker", "Is Self", "Patient Gender ID", "Phone Number", "District", "Sub District", "Village",  "Ben Call ID",
				"Call Type", "Call Sub Type", "Call Received User ID", "Call End User ID", "From When Date", "Patient Gender", "Agent ID", "Agent Name"};
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			BencallReport bencallReportRequest = objectMapper.readValue(jsonRequest, BencallReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bencallReportRequest.getStartDateTime().toString());
			c.setEnd_Date(bencallReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getFoodSafetyReport(bencallReportRequest.getStartDateTime(),
					bencallReportRequest.getEndDateTime(), bencallReportRequest.getAgentID(),
					bencallReportRequest.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}
	
	@Override
	public ByteArrayInputStream getDirectoryserviceReport(String jsonRequest, String filename) throws Exception {

		String[] headers = { "SNo", "Beneficiary ID", "Acceptor Hospital Name", "Acceptor Hospital State",
				"Acceptor Hospital District", "Acceptor Hospital Sub District", "Acceptor Hospital Village",
				"Institute Directory Name", "Institute Sub Directory Name", "Institution ID", "Date",
				"First Name", "Last Name", "Date Of Birth", "Gender", "Health Care Worker", "Phone Number",
				"District", "Sub District","Village", "Ben Call ID", "Call Type", "Call Sub Type",
				"Call Received User ID", "Call End User ID", "Agent ID", "Agent Name"};
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			BencallReport bencallReportRequest = objectMapper.readValue(jsonRequest, BencallReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bencallReportRequest.getStartDateTime().toString());
			c.setEnd_Date(bencallReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getDirectoryserviceReport(bencallReportRequest.getStartDateTime(),
					bencallReportRequest.getEndDateTime(), bencallReportRequest.getAgentID(),
					bencallReportRequest.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}
	
	@Override
	public ByteArrayInputStream getCDIResponseReport(String jsonRequest, String filename) throws Exception {

		String[] headers = {"SNo", "Question", "Answer", "Score", "Beneficiary ID",  "First Name", "Last Name", "Health Care Worker",	
				"Gender", "Date Of Birth",	"Village", "Sub District", "District",	"Call Type", "Call Sub Type", "Phone Number", "Agent ID", "Agent Name"
};
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			FeedbackReport cDIResponseRequest = objectMapper.readValue(jsonRequest, FeedbackReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(cDIResponseRequest.getStartDateTime().toString());
			c.setEnd_Date(cDIResponseRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getCDIResponseReport(cDIResponseRequest.getStartDateTime(),
					cDIResponseRequest.getEndDateTime(), cDIResponseRequest.getAgentID(),
					cDIResponseRequest.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}
	
	@Override
	public ByteArrayInputStream getBloodRequestReport(String jsonRequest, String filename) throws Exception {

		String[] headers = { "SNo", "Beneficiary ID", "Blood Group Name", "Component Type Name", "Unit Required",
				"Type Of Request", "Blood Req ID", "Blood Bank Person Name", "Blood Bank Person Designation",
				"Blood Bank Address", "Blood Bank Mobile No", "Outbound Call Date", "Outbound Needed",
				"Hospital Admitted", "Hospital District Name", "Date", "Remarks", "Feedback", "Request ID",
				"First Name", "Last Name", "Date Of Birth", "Gender", "Health Care Worker", "Is Self",
				"Phone Number", "Recipient Age", "Recipient Name", "Recipient Beneficiary ID",
				"Recipient Gender Name", "District", "Sub District", "Village", "Ben Call ID",
				"Call Type", "Call Sub Type", "Call Received User ID", "Call End User ID",
				"Outbound Date", "Agent ID", "Agent Name"};
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			BencallReport bencallReportRequest = objectMapper.readValue(jsonRequest, BencallReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bencallReportRequest.getStartDateTime().toString());
			c.setEnd_Date(bencallReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getBloodRequestReport(bencallReportRequest.getStartDateTime(),
					bencallReportRequest.getEndDateTime(), bencallReportRequest.getAgentID(),
					bencallReportRequest.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}
	
	@Override
	public ByteArrayInputStream getBloodOnCallCountReportByDate(String jsonRequest, String filename) throws Exception {

		String[] componentHeaders = {"SNo", "Component", "Count"};
		String[] groupHeaders= { "SNo", "Group", "Count"};
		String[] distComponentHeaders = { "SNo", "District", "Total Count", "W B C", "Red Blood Cells", "Others"};
		String[] grpComponentHeaders = { "SNo", "District", "Total Count", "A Positive", "A Negative",
				"B Positive", "B Negative"};

		ByteArrayInputStream response = null;List<Object[]> result=null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			BloodrequestReport bloodrequestReport = objectMapper.readValue(jsonRequest,
					BloodrequestReport.class);
			Criteria c=new Criteria();
			c.setStart_Date(bloodrequestReport.getStartDateTime().toString());
			c.setEnd_Date(bloodrequestReport.getEndDateTime().toString());
			c.setService(filename);
			
			if(bloodrequestReport.getSearchCriteria().equalsIgnoreCase("Component")) {
				 result = secondaryReportRepo.getComponentReport(bloodrequestReport.getStartDateTime(),
						 bloodrequestReport.getEndDateTime(),bloodrequestReport.getDistrictID(), 
						 bloodrequestReport.getSubDistrictID(),bloodrequestReport.getVillageID(),
						 bloodrequestReport.getProviderServiceMapID());
			} else if (bloodrequestReport.getSearchCriteria().equalsIgnoreCase("Group")) {
				 result = secondaryReportRepo.getGroupReport(bloodrequestReport.getStartDateTime(),
						 bloodrequestReport.getEndDateTime(), bloodrequestReport.getDistrictID(), 
						 bloodrequestReport.getSubDistrictID(),bloodrequestReport.getVillageID(),
						 bloodrequestReport.getProviderServiceMapID());
			} else if (bloodrequestReport.getSearchCriteria().equalsIgnoreCase("District Wise Component")) {
				 result = secondaryReportRepo.getDistrictComponentReport(bloodrequestReport.getStartDateTime(),
						 bloodrequestReport.getEndDateTime(),bloodrequestReport.getDistrictID(), 
						 bloodrequestReport.getSubDistrictID(),bloodrequestReport.getVillageID(),
						 bloodrequestReport.getProviderServiceMapID());
			} else if(bloodrequestReport.getSearchCriteria().equalsIgnoreCase("District Wise Group")) {
				result = secondaryReportRepo.getDistrictGroupReport(bloodrequestReport.getStartDateTime(),
						bloodrequestReport.getEndDateTime(),bloodrequestReport.getDistrictID(), 
						 bloodrequestReport.getSubDistrictID(),bloodrequestReport.getVillageID(),
						 bloodrequestReport.getProviderServiceMapID());
			}
			
			if (result != null && result.size() > 0)
			{
				if(bloodrequestReport.getSearchCriteria().equalsIgnoreCase("Component"))
					response = ExcelHelper.tutorialsToExcel(componentHeaders, result,c);
				else if (bloodrequestReport.getSearchCriteria().equalsIgnoreCase("Group"))
					response = ExcelHelper.tutorialsToExcel(groupHeaders, result,c);
			    else if (bloodrequestReport.getSearchCriteria().equalsIgnoreCase("District Wise Component"))
			    	response = ExcelHelper.tutorialsToExcel(distComponentHeaders, result, c);
			    else if(bloodrequestReport.getSearchCriteria().equalsIgnoreCase("District Wise Group"))
			    	response = ExcelHelper.tutorialsToExcel(grpComponentHeaders, result, c);
			}
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}
	
	@Override
	public ByteArrayInputStream getEpidemicoutbreakReport(String jsonRequest, String filename) throws Exception {

		String[] headers = { "SNo", "Beneficiary ID", "Nature Of Complaint", "Outbreak Complaint ID", 
				"Affected District Block Name", "Affected District Branch Name", "Affected District Name",
				"Total People Affected", "Remarks", "Request ID", "Date", "First Name", "Last Name", "Date Of Birth",
				"Gender", "Health Care Worker", "Phone Number", "District", "Sub District", "Village", "Call Type",
				"Call Sub Type", "Ben Call ID", "Call Received User ID", "Call End User ID", "Agent ID", 
				"Agent Name" };
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			BencallReport bencallReportRequest = objectMapper.readValue(jsonRequest, BencallReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bencallReportRequest.getStartDateTime().toString());
			c.setEnd_Date(bencallReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getEpidemicoutbreakReport(bencallReportRequest.getStartDateTime(),
					bencallReportRequest.getEndDateTime(), bencallReportRequest.getAgentID(),
					bencallReportRequest.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result,  c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}
	
	
	@Override
	public ByteArrayInputStream getOrgandonationReport(String jsonRequest, String filename) throws Exception {

		String[] headers = { "SNo", "Beneficiary ID", "Donation Type Name",	"Organ Donation ID", "Donatable Organ Name",
				"Acceptor Hospital Name", "Acceptor Hospital State", "Acceptor Hospital District", "Acceptor Hospital Sub District",
				"Acceptor Hospital Village", "Created Date", "Remarks",	"Request ID", "First Name", "Last Name", "Dob",	
				"Donar Age", "Donar Gender", "Gender", "Health Care Worker", "Is Self",	"Phone Number",	"Donar Name",
				"District",	"Sub District",	"Village",	"Ben Call ID",	"Call Type", "Call Sub Type", 
				"Call Received User ID", "Call End User ID", "Date", "Agent ID", "Agent Name"
};
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			BencallReport bencallReportRequest = objectMapper.readValue(jsonRequest, BencallReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bencallReportRequest.getStartDateTime().toString());
			c.setEnd_Date(bencallReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getOrgandonationReport(bencallReportRequest.getStartDateTime(),
					bencallReportRequest.getEndDateTime(), bencallReportRequest.getAgentID(),
					bencallReportRequest.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result,  c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}
	
	@Override
	public ByteArrayInputStream getPDSummaryReport(String jsonRequest, String filename) throws Exception {

		String[] headers = { "SNo", "Action By M O", "Action By P D", "Allergies",
				"Disease Summary", "Selected Diagnosis", "Selected Diagnosis ID", "Added Advice", "Disease Summary ID", 
				"Prescription ID", "Request ID", "Ben History ID", "First Name", "Last Name", "Patient Name",  
				"Is Self", "Date Of Birth", "Patient Age", "Gender", "Patient Gender Name","Health Care Worker", 
				"Phone Number", "District","Sub District", "Village", "Ben Call ID", "Call Type", "Call Sub Type", 
				"Call Received User ID", "Call End User ID", "Symptom ID", "Action By H A O", "Action By C O", 
				"Date", "Beneficiary ID", "Agent ID", "Agent Name"
};
		ByteArrayInputStream response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {

			BencallReport bencallReportRequest = objectMapper.readValue(jsonRequest, BencallReport.class);
			Criteria c = new Criteria();
			c.setStart_Date(bencallReportRequest.getStartDateTime().toString());
			c.setEnd_Date(bencallReportRequest.getEndDateTime().toString());
			c.setService(filename);
			List<Object[]> result = secondaryReportRepo.getPDSummaryReport(bencallReportRequest.getStartDateTime(),
					bencallReportRequest.getEndDateTime(), bencallReportRequest.getAgentID(),
					bencallReportRequest.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result,  c);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}
}
