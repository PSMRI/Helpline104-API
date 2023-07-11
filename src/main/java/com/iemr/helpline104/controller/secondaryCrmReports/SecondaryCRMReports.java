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
package com.iemr.helpline104.controller.secondaryCrmReports;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iemr.helpline104.data.report.BenmedhistoryReport;
import com.iemr.helpline104.data.report.BloodrequestReport;
import com.iemr.helpline104.data.report.DirectoryserviceReport;
import com.iemr.helpline104.data.report.EpidemicoutbreakReport;
import com.iemr.helpline104.data.report.OrgandonationReport;
import com.iemr.helpline104.data.report.FoodsafetyReport;
import com.iemr.helpline104.data.report.PrescriptionReport;
import com.iemr.helpline104.data.report.SchemeserviceReport;
import com.iemr.helpline104.service.secondaryCrmReports.CRMSecondaryReportService;
import com.iemr.helpline104.utils.mapper.InputMapper;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.annotations.ApiParam;


@CrossOrigin
@RequestMapping({ "/crmReports" })
@RestController
public class SecondaryCRMReports
{
    @Autowired
    private CRMSecondaryReportService cRMSecondaryReportService;
	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	InputMapper inputMapper = new InputMapper();

	@CrossOrigin()
	@RequestMapping(value = "/getROSummaryReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getROSummaryReportByDate(
			@ApiParam(value = "\"{\\\"startDate\\\":\\\"Timestamp\\\",\\\"endDate\\\":\\\"Timestamp\\\","
					+ "\\\"providerServiceMapID\\\":\\\"Integer\\\",\\\"agentID\\\":\\\"Integer\\\","
					+ "\\\"roleName\\\":\\\"String\\\",\\\"reportTypeID\\\":\\\"Integer\\\",\\\"reportType\\\":\\\"String\\\"}\"") @RequestBody String jsonRequest)

	{
		
		 String filename = getFileName(jsonRequest,"Registration_Service");
		    InputStreamResource file=null;
		    try
		    {
		      file = new InputStreamResource(cRMSecondaryReportService.getRegistrationReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		    }
		    catch(Exception e)
		    {
		    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
		    	if(e.getMessage().equalsIgnoreCase("No data found"))
		    	return ResponseEntity.status(500).body(e.getMessage());
		    	else
		    	return ResponseEntity.status(5000).body(e.getMessage());
		    }
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/getHAOSummaryReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getHAOSummaryReportByDate(
			@ApiParam(value = "\"{\\\"startDate\\\":\\\"Timestamp\\\",\\\"endDate\\\":\\\"Timestamp\\\","
					+ "\\\"providerServiceMapID\\\":\\\"Integer\\\",\\\"agentID\\\":\\\"Integer\\\","
					+ "\\\"roleName\\\":\\\"String\\\",\\\"reportTypeID\\\":\\\"Integer\\\",\\\"reportType\\\":\\\"String\\\"}\"") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Health_Advisory_Service");
		    InputStreamResource file=null;
		    try
		    {
		      file = new InputStreamResource(cRMSecondaryReportService.getHAOReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		    }
		    catch(Exception e)
		    {
		    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
		    	if(e.getMessage().equalsIgnoreCase("No data found"))
		    	return ResponseEntity.status(500).body(e.getMessage());
		    	else
		    	return ResponseEntity.status(5000).body(e.getMessage());
		    }
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/getMOSummaryReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getMOSummaryReportByDate(
			@ApiParam(value = "\"{\\\"startDate\\\":\\\"Timestamp\\\",\\\"endDate\\\":\\\"Timestamp\\\","
					+ "\\\"providerServiceMapID\\\":\\\"Integer\\\",\\\"agentID\\\":\\\"Integer\\\","
					+ "\\\"roleName\\\":\\\"String\\\",\\\"reportTypeID\\\":\\\"Integer\\\",\\\"reportType\\\":\\\"String\\\"}\"") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Medical_Services");
		    InputStreamResource file=null;
		    try
		    {
		      file = new InputStreamResource(cRMSecondaryReportService.getMOReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		    }
		    catch(Exception e)
		    {
		    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
		    	if(e.getMessage().equalsIgnoreCase("No data found"))
		    	return ResponseEntity.status(500).body(e.getMessage());
		    	else
		    	return ResponseEntity.status(5000).body(e.getMessage());
		    }
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/getMedicalAdviseReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getMedicalAdviseReport(
			@ApiParam(value = "\"{\\\"startDate\\\":\\\"Timestamp\\\",\\\"endDate\\\":\\\"Timestamp\\\","
					+ "\\\"providerServiceMapID\\\":\\\"Integer\\\",\\\"agentID\\\":\\\"Integer\\\","
					+ "\\\"roleName\\\":\\\"String\\\",\\\"reportTypeID\\\":\\\"Integer\\\",\\\"reportType\\\":\\\"String\\\"}\"") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Medical_Services_Detail");
		    InputStreamResource file=null;
		    try
		    {
		      file = new InputStreamResource(cRMSecondaryReportService.getMODetailsReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		    }
		    catch(Exception e)
		    {
		    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
		    	if(e.getMessage().equalsIgnoreCase("No data found"))
		    	return ResponseEntity.status(500).body(e.getMessage());
		    	else
		    	return ResponseEntity.status(5000).body(e.getMessage());
		    }
	}
	@CrossOrigin()
	@RequestMapping(value = "/getCOSummaryReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getCOSummaryReportByDate(
			@ApiParam(value = "\"{\\\"startDate\\\":\\\"Timestamp\\\",\\\"endDate\\\":\\\"Timestamp\\\","
					+ "\\\"providerServiceMapID\\\":\\\"Integer\\\",\\\"agentID\\\":\\\"Integer\\\","
					+ "\\\"roleName\\\":\\\"String\\\",\\\"reportTypeID\\\":\\\"Integer\\\",\\\"reportType\\\":\\\"String\\\"}\"") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Counselling_Service");
		    InputStreamResource file=null;
		    try
		    {
		      file = new InputStreamResource(cRMSecondaryReportService.getCOReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		    }
		    catch(Exception e)
		    {
		    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
		    	if(e.getMessage().equalsIgnoreCase("No data found"))
		    	return ResponseEntity.status(500).body(e.getMessage());
		    	else
		    	return ResponseEntity.status(5000).body(e.getMessage());
		    }
	}
	@CrossOrigin()
	@RequestMapping(value = "/getMentalHealthReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getMentalHealthReport(
			@ApiParam(value = "\"{\\\"startDate\\\":\\\"Timestamp\\\",\\\"endDate\\\":\\\"Timestamp\\\","
					+ "\\\"providerServiceMapID\\\":\\\"Integer\\\",\\\"agentID\\\":\\\"Integer\\\","
					+ "\\\"roleName\\\":\\\"String\\\",\\\"reportTypeID\\\":\\\"Integer\\\",\\\"reportType\\\":\\\"String\\\"}\"") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Counselling_Service_Detail");
		    InputStreamResource file=null;
		    try
		    {
		      file = new InputStreamResource(cRMSecondaryReportService.getMentalHealthReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		    }
		    catch(Exception e)
		    {
		    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
		    	if(e.getMessage().equalsIgnoreCase("No data found"))
		    	return ResponseEntity.status(500).body(e.getMessage());
		    	else
		    	return ResponseEntity.status(5000).body(e.getMessage());
		    }
	}
	@CrossOrigin()
	@RequestMapping(value = "/getGrievanceReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getGrievanceReportByDate(
			@ApiParam(value = "\"{\\\"startDate\\\":\\\"Timestamp\\\",\\\"endDate\\\":\\\"Timestamp\\\","
					+ "\\\"providerServiceMapID\\\":\\\"Integer\\\",\\\"agentID\\\":\\\"Integer\\\","
					+ "\\\"roleName\\\":\\\"String\\\",\\\"reportTypeID\\\":\\\"Integer\\\",\\\"reportType\\\":\\\"String\\\"}\"") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Grievance");
		    InputStreamResource file=null;
		    try
		    {
		      file = new InputStreamResource(cRMSecondaryReportService.getGrievanceReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		    }
		    catch(Exception e)
		    {
		    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
		    	if(e.getMessage().equalsIgnoreCase("No data found"))
		    	return ResponseEntity.status(500).body(e.getMessage());
		    	else
		    	return ResponseEntity.status(5000).body(e.getMessage());
		    }
	}
	@CrossOrigin()
	@RequestMapping(value = "/getPrescriptionReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getPrescriptionReportByDate(
			@ApiParam(value = "\"{\\\"startDate\\\":\\\"Timestamp\\\",\\\"endDate\\\":\\\"Timestamp\\\","
					+ "\\\"providerServiceMapID\\\":\\\"Integer\\\",\\\"agentID\\\":\\\"Integer\\\","
					+ "\\\"roleName\\\":\\\"String\\\",\\\"reportTypeID\\\":\\\"Integer\\\",\\\"reportType\\\":\\\"String\\\"}\"") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Prescription");
		    InputStreamResource file=null;
		    try
		    {
		      file = new InputStreamResource(cRMSecondaryReportService.getPrescriptionReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		    }
		    catch(Exception e)
		    {
		    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
		    	if(e.getMessage().equalsIgnoreCase("No data found"))
		    	return ResponseEntity.status(500).body(e.getMessage());
		    	else
		    	return ResponseEntity.status(5000).body(e.getMessage());
		    }
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/getCDIResponseReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getCDIResponseReport(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Surveyor");
	    InputStreamResource file=null;
	    try
	    {
	      file = new InputStreamResource(cRMSecondaryReportService.getCDIResponseReport(jsonRequest,filename));
	      return ResponseEntity.ok()
			        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
			        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
			        .body(file);
	    }
	    catch(Exception e)
	    {
	    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
	    	if(e.getMessage().equalsIgnoreCase("No data found"))
	    	return ResponseEntity.status(500).body(e.getMessage());
	    	else
	    	return ResponseEntity.status(5000).body(e.getMessage());
	    }
}
	@CrossOrigin()
	@RequestMapping(value = "/getSchemesReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getSchemesReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Health_Schemes");
	    InputStreamResource file=null;
		try
		{
			file = new InputStreamResource(cRMSecondaryReportService.getSchemeServiceReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);

		    }
		    catch(Exception e)
		    {
		    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
		    	if(e.getMessage().equalsIgnoreCase("No data found"))
		    	return ResponseEntity.status(500).body(e.getMessage());
		    	else
		    	return ResponseEntity.status(5000).body(e.getMessage());
		    }
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/getDirectoryServiceReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getDirectoryServiceReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Directory_Services");
	    InputStreamResource file=null;
		try
		{
			file = new InputStreamResource(cRMSecondaryReportService.getDirectoryserviceReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		} catch (Exception e)
	    {
	    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
	    	if(e.getMessage().equalsIgnoreCase("No data found"))
	    	return ResponseEntity.status(500).body(e.getMessage());
	    	else
	    	return ResponseEntity.status(5000).body(e.getMessage());
	    }
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/getFoodSafetyReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getFoodSafetyReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Food_Safety");
	    InputStreamResource file=null;
		try
		{
			file = new InputStreamResource(cRMSecondaryReportService.getFoodSafetyReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		} catch (Exception e)
	    {
	    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
	    	if(e.getMessage().equalsIgnoreCase("No data found"))
	    	return ResponseEntity.status(500).body(e.getMessage());
	    	else
	    	return ResponseEntity.status(5000).body(e.getMessage());
	    }
		
	}
//		OutputResponse response = new OutputResponse();
//		try
//		{
//			List<FoodsafetyReport> foodsafetyReports = crmReportsService.getFoodsafetyReport(jsonRequest);
//			response.setResponse(foodsafetyReports.toString());
//		} catch (Exception e)
//		{
//			response.setError(e);
//		}
//		logger.info("response is " + response.toStringWithSerialization());
//		// String respStr = response.toStringWithSerialization();
//		return response.toStringWithSerialization();
//	}
//	
	@CrossOrigin()
	@RequestMapping(value = "/getBloodOnCallReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getBloodOnCallReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Blood_Request");
	    InputStreamResource file=null;
		try
		{
			file = new InputStreamResource(cRMSecondaryReportService.getBloodRequestReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		} catch (Exception e)
	    {
	    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
	    	if(e.getMessage().equalsIgnoreCase("No data found"))
	    	return ResponseEntity.status(500).body(e.getMessage());
	    	else
	    	return ResponseEntity.status(5000).body(e.getMessage());
	    }
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/getBloodOnCallCountReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getBloodOnCallCountReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\", "
					+ "\"districtID\":\"Integer\", "
					+"\"searchCriteria\": \"String\", "
					+ "\"subDistrictID\": \"Integer\", "
					+ "\"villageID\": \"Integer\", "
					+ "\"filename\" : \"String\" }") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Blood_Request_Detail");
	    InputStreamResource file=null;
		try
		{
			file = new InputStreamResource(cRMSecondaryReportService.getBloodOnCallCountReportByDate(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		} catch (Exception e)
	    {
	    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
	    	if(e.getMessage().equalsIgnoreCase("No data found"))
	    	return ResponseEntity.status(500).body(e.getMessage());
	    	else
	    	return ResponseEntity.status(5000).body(e.getMessage());
	    }
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/getEpidemicReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getEpidemicReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)
	{
		String filename = getFileName(jsonRequest,"Epidemic_Outbreak_Service");
	    InputStreamResource file=null;
		try
		{
			file = new InputStreamResource(cRMSecondaryReportService.getEpidemicoutbreakReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		} catch (Exception e)
	    {
	    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
	    	if(e.getMessage().equalsIgnoreCase("No data found"))
	    	return ResponseEntity.status(500).body(e.getMessage());
	    	else
	    	return ResponseEntity.status(5000).body(e.getMessage());
	    }
	}
			
	@CrossOrigin()		
	@RequestMapping(value = "/getOrganDonationReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getOrganDonationReportByDate(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Organ_Donation");
	    InputStreamResource file=null;
		try
		{
			file = new InputStreamResource(cRMSecondaryReportService.getOrgandonationReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		} catch (Exception e)
	    {
	    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
	    	if(e.getMessage().equalsIgnoreCase("No data found"))
	    	return ResponseEntity.status(500).body(e.getMessage());
	    	else
	    	return ResponseEntity.status(5000).body(e.getMessage());
	    }
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/getPDSummaryReportByDate", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getPDSummaryReport(
			@ApiParam("{\"startDateTime\":\"Date time stamp of start date\", "
					+ "\"providerServiceMapID\":\"Integer - provider service map ID\", "
					+ "\"endDateTime\":\"Date time stamp of end date\"}") @RequestBody String jsonRequest)

	{
		String filename = getFileName(jsonRequest,"Psychiatrist");
	    InputStreamResource file=null;
		try
		{
			file = new InputStreamResource(cRMSecondaryReportService.getPDSummaryReport(jsonRequest,filename));
		      return ResponseEntity.ok()
				        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename+".xlsx"))//add generic function
				        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				        .body(file);
		} catch (Exception e)
	    {
	    	logger.error("Report Name:"+filename+" Timestamp:"+System.currentTimeMillis()+" Error: "+e.getMessage());
	    	if(e.getMessage().equalsIgnoreCase("No data found"))
	    	return ResponseEntity.status(500).body(e.getMessage());
	    	else
	    	return ResponseEntity.status(5000).body(e.getMessage());
	    }
	}
	public String getFileName(String jsonRequest,String name)
	{
		String fileName=null;
		JsonObject jsnOBJ = new JsonObject();
		JsonParser jsnParser = new JsonParser();
		JsonElement jsnElmnt = jsnParser.parse(jsonRequest);
		jsnOBJ = jsnElmnt.getAsJsonObject();
		if (jsnOBJ != null && jsnOBJ.has("fileName"))
		{
		 fileName=jsnOBJ.get("fileName").getAsString();
		}
		if(fileName !=null)
		return fileName;
		else
			return name;
	}
}
