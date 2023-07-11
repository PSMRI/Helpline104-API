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
package com.iemr.helpline104.controller.report;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.data.report.BeneficiaryReport;
import com.iemr.helpline104.data.report.BenmedhistoryReport;
import com.iemr.helpline104.data.report.BloodrequestReport;
import com.iemr.helpline104.data.report.COReport;
import com.iemr.helpline104.data.report.DirectoryserviceReport;
import com.iemr.helpline104.data.report.EpidemicoutbreakReport;
import com.iemr.helpline104.data.report.FeedbackReport;
import com.iemr.helpline104.data.report.FoodsafetyReport;
import com.iemr.helpline104.data.report.MOReport;
import com.iemr.helpline104.data.report.MedicalHistory;
import com.iemr.helpline104.data.report.OrgandonationReport;
import com.iemr.helpline104.data.report.PrescriptionReport;
import com.iemr.helpline104.data.report.SchemeserviceReport;
import com.iemr.helpline104.service.report.CRMReportsService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.annotations.ApiParam;

@CrossOrigin
@RequestMapping({ "/crmReports" })
@RestController
public class CRMReports
{

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	InputMapper inputMapper = new InputMapper();

	@Autowired
	private CRMReportsService crmReportsService;

	/*
	 * @Autowired public void setCallReportsService(CallReportsService callReportsService) { this.callReportsService =
	 * callReportsService; }
	 */
   //add comment
   
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getROSummaryReportByDate/deprecated", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getROSummaryReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			List<BeneficiaryReport> registrationReport = crmReportsService.getRegistrationReport(jsonRequest);
			response.setResponse(registrationReport.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		// String respStr = response.toStringWithSerialization();
		return response.toStringWithSerialization();
	}
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getHAOSummaryReportByDates", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getHAOSummaryReport(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			List<MedicalHistory> haoSummaryReport = crmReportsService.getHAOSummaryReport(jsonRequest);
			response.setResponse(haoSummaryReport.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		// String respStr = response.toStringWithSerialization();
		return response.toStringWithSerialization();
	}
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getMOSummaryReportByDates", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getMOSummaryReport(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			List<MOReport> moSummaryReport = crmReportsService.getMOSummaryReport(jsonRequest);
			response.setResponse(moSummaryReport.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		// String respStr = response.toStringWithSerialization();
		return response.toStringWithSerialization();
	}
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getCOSummaryReportByDates", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getCOSummaryReport(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			List<COReport> coSummaryReport = crmReportsService.getCOSummaryReport(jsonRequest);
			response.setResponse(coSummaryReport.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		// String respStr = response.toStringWithSerialization();
		return response.toStringWithSerialization();
	}
	
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getPDSummaryReportByDateDeprecated", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getPDSummaryReport(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			List<BenmedhistoryReport> pdSummaryReport = crmReportsService.getPDSummaryReport(jsonRequest);
			response.setResponse(pdSummaryReport.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		// String respStr = response.toStringWithSerialization();
		return response.toStringWithSerialization();
	}
	
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getEpidemicReportByDateDeprecated", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getEpidemicReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			List<EpidemicoutbreakReport> epidemicReports = crmReportsService.getEpidemicoutbreakReport(jsonRequest);
			response.setResponse(epidemicReports.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		// String respStr = response.toStringWithSerialization();
		return response.toStringWithSerialization();
	}
	
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getFoodSafetyReportByDateDeprecated", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getFoodSafetyReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			List<FoodsafetyReport> foodsafetyReports = crmReportsService.getFoodsafetyReport(jsonRequest);
			response.setResponse(foodsafetyReports.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		// String respStr = response.toStringWithSerialization();
		return response.toStringWithSerialization();
	}
	
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getBloodOnCallReportByDateDeprecated", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getBloodOnCallReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			List<BloodrequestReport> bloodRequestReports = crmReportsService.getBloodRequestReport(jsonRequest);
			response.setResponse(bloodRequestReports.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		// String respStr = response.toStringWithSerialization();
		return response.toStringWithSerialization();
	}
	
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getOrganDonationReportByDateDeprecated", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getOrganDonationReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			List<OrgandonationReport> epidemicReports = crmReportsService.getOrgandonationReport(jsonRequest);
			response.setResponse(epidemicReports.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		// String respStr = response.toStringWithSerialization();
		return response.toStringWithSerialization();
	}
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getGrievanceReportByDates", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getGrievanceReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			List<FeedbackReport> feedbackReports = crmReportsService.getFeedbackReport(jsonRequest);
			response.setResponse(feedbackReports.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		// String respStr = response.toStringWithSerialization();
		return response.toStringWithSerialization();
	}
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getDirectoryServiceReportByDateDeprecated", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getDirectoryServiceReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			List<DirectoryserviceReport> directoryServiceReports = crmReportsService.getDirectoryserviceReport(jsonRequest);
			response.setResponse(directoryServiceReports.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		// String respStr = response.toStringWithSerialization();
		return response.toStringWithSerialization();
	}
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getSchemesReportByDateDeprecated", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getSchemesReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			List<SchemeserviceReport> schemeReports = crmReportsService.getSchemeServiceReport(jsonRequest);
			response.setResponse(schemeReports.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		// String respStr = response.toStringWithSerialization();
		return response.toStringWithSerialization();
	}
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getPrescriptionReportByDates", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getPrescriptionReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			List<PrescriptionReport> prescriptioReports = crmReportsService.getPrescriptionReport(jsonRequest);
			response.setResponse(prescriptioReports.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		// String respStr = response.toStringWithSerialization();
		return response.toStringWithSerialization();
	}

	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getBloodOnCallCountReportByDateDeprecated", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getBloodOnCallCountReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			String bloodRequestReports = crmReportsService.getBloodOnCallCountReportByDate(jsonRequest);
			response.setResponse(bloodRequestReports.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		return response.toStringWithSerialization();
	}
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getMentalHealthReports", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getMentalHealthReport(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			String mentalHealthRequestReports = crmReportsService.getMentalHealthReport(jsonRequest);
			response.setResponse(mentalHealthRequestReports.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		return response.toStringWithSerialization();
	}
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getMedicalAdviseReports", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getMedicalAdviseReport(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			String requestReports = crmReportsService.getMedicalAdviseReport(jsonRequest);
			response.setResponse(requestReports.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		return response.toStringWithSerialization();
	}
	
	@Deprecated
	@CrossOrigin()
	@RequestMapping(value = "/getCDIResponseReportDeprecated", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getCDIResponseReport(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		OutputResponse response = new OutputResponse();
		try
		{
			String requestReports = crmReportsService.getCDIResponseReport(jsonRequest);
			response.setResponse(requestReports.toString());
		} catch (Exception e)
		{
			response.setError(e);
		}
		logger.info("response is " + response.toStringWithSerialization());
		return response.toStringWithSerialization();
	}
}
