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
package com.iemr.helpline104.service.report;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.data.report.BenCDIResponseReport;
import com.iemr.helpline104.data.report.BencallReport;
import com.iemr.helpline104.data.report.BeneficiaryReport;
import com.iemr.helpline104.data.report.BenmedhistoryReport;
import com.iemr.helpline104.data.report.BloodCompDistrictModel;
import com.iemr.helpline104.data.report.BloodGroupCountReportModel;
import com.iemr.helpline104.data.report.BloodGroupDistrictModel;
import com.iemr.helpline104.data.report.BloodOnRequestCountModel;
import com.iemr.helpline104.data.report.BloodrequestReport;
import com.iemr.helpline104.data.report.COReport;
import com.iemr.helpline104.data.report.DirectoryserviceReport;
import com.iemr.helpline104.data.report.EpidemicoutbreakReport;
import com.iemr.helpline104.data.report.FeedbackReport;
import com.iemr.helpline104.data.report.FoodsafetyReport;
import com.iemr.helpline104.data.report.MOReport;
import com.iemr.helpline104.data.report.MedicalAdviseDiseaseReport;
import com.iemr.helpline104.data.report.MedicalHistory;
import com.iemr.helpline104.data.report.MentalHealthCategory;
import com.iemr.helpline104.data.report.MentalHealthGuidlineReport;
import com.iemr.helpline104.data.report.OrgandonationReport;
import com.iemr.helpline104.data.report.PrescriptionReport;
import com.iemr.helpline104.data.report.SchemeserviceReport;
import com.iemr.helpline104.data.report.UserReport;
import com.iemr.helpline104.data.report.UserServiceRoleReport;
import com.iemr.helpline104.data.report.WorkLocation;
import com.iemr.helpline104.repository.report.BencallReportRepository;
import com.iemr.helpline104.repository.report.BenmedhistoryReportRepository;
import com.iemr.helpline104.repository.report.BloodRequestReportRepository;
import com.iemr.helpline104.repository.report.DirectoryServiceReportRepository;
import com.iemr.helpline104.repository.report.EpidemicoutbreakReportRepository;
import com.iemr.helpline104.repository.report.FeedbackReportRepository;
import com.iemr.helpline104.repository.report.FoodSafetyReportRepository;
import com.iemr.helpline104.repository.report.OrgandonationReportRepository;
import com.iemr.helpline104.repository.report.PrescriptionReportRepository;
import com.iemr.helpline104.repository.report.SchemesReportRepository;
import com.iemr.helpline104.utils.mapper.InputMapper;

@Service
public class CRMReportsServiceImpl implements CRMReportsService
{

	private InputMapper inputMapper = new InputMapper();

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	private EpidemicoutbreakReportRepository epidemicoutbreakReportRepository;

	/*
	 * @Autowired private BeneficiaryReportRepository beneficiaryReportRepository;
	 */

	@Autowired
	private BencallReportRepository bencallReportReportRepository;

	@Autowired
	private BenmedhistoryReportRepository benmedhistoryReportRepository;

	@Autowired
	private BloodRequestReportRepository bloodRequestReportRepository;

	@Autowired
	private DirectoryServiceReportRepository directoryServiceReportRepository;

	@Autowired
	private OrgandonationReportRepository organdonationReportRepository;

	@Autowired
	private SchemesReportRepository schemesReportRepository;

	@Autowired
	private FeedbackReportRepository feedbackReportRepository;

	@Autowired
	private FoodSafetyReportRepository foodSafetyReportRepository;

	@Autowired
	private PrescriptionReportRepository prescriptionReportRepository;

	@Override
	public List<BeneficiaryReport> getRegistrationReport(String request) throws Exception
	{

		logger.debug("getRegistrationReport request: " + request);
		BencallReport bencallReportRequest = inputMapper.gson().fromJson(request, BencallReport.class);
		List<BeneficiaryReport> registrationReports = new ArrayList<BeneficiaryReport>();
		List<Objects[]> reports;

		if (bencallReportRequest.getAgentID() == null || bencallReportRequest.getAgentID() == "")
		{
			reports =
					bencallReportReportRepository.getROSummaryReportByDate(
							bencallReportRequest.getProviderServiceMapID(), bencallReportRequest.getStartDateTime(),
							bencallReportRequest.getEndDateTime());
		} else
		{

			reports =
					bencallReportReportRepository.getROSummaryReportByAgentIDAndDate(
							bencallReportRequest.getProviderServiceMapID(), bencallReportRequest.getAgentID(),
							bencallReportRequest.getStartDateTime(), bencallReportRequest.getEndDateTime());
		}

		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				registrationReports.add(
						new BeneficiaryReport(
								(Long) objects[0], (String) objects[1], (String) objects[2], (String) objects[3], (String) objects[4],
								(String) objects[5], (Timestamp) objects[6], (String) objects[7], (Byte) objects[8], (String) objects[9],
								(String) objects[10], (String) objects[11], (String) objects[12], (String) objects[13], (String) objects[14],
								(String) objects[15], (String) objects[16], (Timestamp) objects[17], (String) objects[18], (String) objects[19],
								(String) objects[20], (Integer) objects[21], (Integer) objects[22], (String) objects[23], (String) objects[24]));
			}
		}

		return registrationReports;

	}

	@Override
	public List<MedicalHistory> getHAOSummaryReport(String request) throws Exception
	{

		logger.debug("getHAOSummaryReport request: " + request);
		BenmedhistoryReport benmedhistoryReportRequest = inputMapper.gson().fromJson(request, BenmedhistoryReport.class);
		List<MedicalHistory> haoSummaryReport = new ArrayList<MedicalHistory>();
		List<Objects[]> reports;
		if (benmedhistoryReportRequest.getAgentID() == null || benmedhistoryReportRequest.getAgentID() == "")
		{
			reports =
					benmedhistoryReportRepository.getHAOSummaryReportByDate(
							benmedhistoryReportRequest.getProviderServiceMapID(), benmedhistoryReportRequest.getStartDateTime(),
							benmedhistoryReportRequest.getEndDateTime());
		} else
		{
			reports =
					benmedhistoryReportRepository.getHAOSummaryReportByAgentIDAndDate(
							benmedhistoryReportRequest.getProviderServiceMapID(), benmedhistoryReportRequest.getAgentID(),
							benmedhistoryReportRequest.getStartDateTime(), benmedhistoryReportRequest.getEndDateTime());
		}

		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				haoSummaryReport.add(
						new MedicalHistory(
								(Long) objects[0], (String) objects[1], (Long) objects[2], (Long) objects[3], (String) objects[4],
								(Integer) objects[5], (String) objects[6], (String) objects[7], (String) objects[8], (String) objects[9],
								(String) objects[10], (String) objects[11], (String) objects[12], (String) objects[13], (Long) objects[14],
								(String) objects[15], "HAO", (String) objects[16], (Byte) objects[17], (Timestamp) objects[18], (Long) objects[19],
								(String) objects[20], (String) objects[21], (String) objects[22], (String) objects[23], (Timestamp) objects[24],
								(String) objects[25], (String) objects[26], (String) objects[27], (String) objects[28], (String) objects[29],
								(String) objects[30], (Integer) objects[31], (Integer) objects[32], (String) objects[33], (String) objects[34],
								(Boolean) objects[35], (String) objects[36], (String) objects[37], (String) objects[38], (Boolean) objects[39],
								(Boolean) objects[40], (String) objects[41]));
			}
		}

		return haoSummaryReport;

	}

	@Override
	public List<MOReport> getMOSummaryReport(String request) throws Exception
	{

		logger.debug("getMOSummaryReport request: " + request);
		BenmedhistoryReport benmedhistoryReportRequest = inputMapper.gson().fromJson(request, BenmedhistoryReport.class);
		List<MOReport> moSummaryReport = new ArrayList<MOReport>();
		List<Objects[]> reports;
		if (benmedhistoryReportRequest.getAgentID() == null || benmedhistoryReportRequest.getAgentID() == "")
		{
			reports =
					benmedhistoryReportRepository.getMOSummaryReportByDate(
							benmedhistoryReportRequest.getProviderServiceMapID(), benmedhistoryReportRequest.getStartDateTime(),
							benmedhistoryReportRequest.getEndDateTime());
		} else
		{
			reports =
					benmedhistoryReportRepository.getMOSummaryReportByAgentIDAndDate(
							benmedhistoryReportRequest.getProviderServiceMapID(), benmedhistoryReportRequest.getAgentID(),
							benmedhistoryReportRequest.getStartDateTime(), benmedhistoryReportRequest.getEndDateTime());
		}

		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				moSummaryReport.add(
						new MOReport(
								(Long) objects[0], (String) objects[1], (Long) objects[2], (Long) objects[3], (String) objects[4],
								(Integer) objects[5], (String) objects[6], (String) objects[7], (String) objects[8], (String) objects[9],
								(String) objects[10], (String) objects[11], (String) objects[12], (String) objects[13], (Long) objects[14],
								(String) objects[15], "MO", (String) objects[16], (Byte) objects[17], (Timestamp) objects[18], (Long) objects[19],
								(String) objects[20], (String) objects[21], (String) objects[22], (String) objects[23], (Timestamp) objects[24],
								(String) objects[25], (String) objects[26], (String) objects[27], (String) objects[28], (String) objects[29],
								(String) objects[30], (Integer) objects[31], (Integer) objects[32], (String) objects[33], (String) objects[34],
								(String) objects[35], (String) objects[36],(String) objects[37], (Integer) objects[38],
								(Boolean) objects[39], (String) objects[40], (String) objects[41], (String) objects[42], (Boolean) objects[43],
								(Boolean) objects[44], (String) objects[45]));

			}
		}

		return moSummaryReport;

	}

	@Override
	public List<COReport> getCOSummaryReport(String request) throws Exception
	{

		logger.debug("getCOSummaryReport request: " + request);
		BenmedhistoryReport benmedhistoryReportRequest = inputMapper.gson().fromJson(request, BenmedhistoryReport.class);
		List<COReport> coSummaryReport = new ArrayList<COReport>();
		List<Objects[]> reports;
		if (benmedhistoryReportRequest.getAgentID() == null || benmedhistoryReportRequest.getAgentID() == "")
		{
			reports =
					benmedhistoryReportRepository.getCOSummaryReportByDate(
							benmedhistoryReportRequest.getProviderServiceMapID(), benmedhistoryReportRequest.getStartDateTime(),
							benmedhistoryReportRequest.getEndDateTime());
		} else
		{
			reports =
					benmedhistoryReportRepository.getCOSummaryReportByAgentIDAndDate(
							benmedhistoryReportRequest.getProviderServiceMapID(), benmedhistoryReportRequest.getAgentID(),
							benmedhistoryReportRequest.getStartDateTime(), benmedhistoryReportRequest.getEndDateTime());
		}

		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				coSummaryReport.add(
						new COReport(
								(Long) objects[0], (String) objects[1], (Long) objects[2], (Long) objects[3], (String) objects[4],
								(Integer) objects[5], (String) objects[6], (String) objects[7], (String) objects[8], (String) objects[9],
								(String) objects[10], (String) objects[11], (String) objects[12], (String) objects[13], (Long) objects[14],
								(String) objects[15], "CO", (String) objects[16], (Byte) objects[17], (Timestamp) objects[18], (Long) objects[19],
								(String) objects[20], (String) objects[21], (String) objects[22], (String) objects[23], (Timestamp) objects[24],
								(String) objects[25], (String) objects[26], (String) objects[27], (String) objects[28], (String) objects[29],
								(String) objects[30], (Integer) objects[31], (Integer) objects[32], (String) objects[33], (String) objects[34], (String) objects[35], (String) objects[36]));
			}
		}

		return coSummaryReport;

	}

	@Override
	public List<BenmedhistoryReport> getPDSummaryReport(String request) throws Exception
	{

		logger.debug("getPDSummaryReport request: " + request);
		BenmedhistoryReport benmedhistoryReportRequest = inputMapper.gson().fromJson(request, BenmedhistoryReport.class);
		List<BenmedhistoryReport> pdSummaryReport = new ArrayList<BenmedhistoryReport>();
		List<Objects[]> reports;
		if (benmedhistoryReportRequest.getAgentID() == null || benmedhistoryReportRequest.getAgentID() == "")
		{
			reports =
					benmedhistoryReportRepository.getPDSummaryReportByDate(
							benmedhistoryReportRequest.getProviderServiceMapID(), benmedhistoryReportRequest.getStartDateTime(),
							benmedhistoryReportRequest.getEndDateTime());
		} else
		{
			reports =
					benmedhistoryReportRepository.getPDSummaryReportByAgentIDAndDate(
							benmedhistoryReportRequest.getProviderServiceMapID(), benmedhistoryReportRequest.getAgentID(),
							benmedhistoryReportRequest.getStartDateTime(), benmedhistoryReportRequest.getEndDateTime());
		}

		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				pdSummaryReport.add(
						new BenmedhistoryReport(
								(Long) objects[0], (String) objects[1], (Long) objects[2], (Long) objects[3], (String) objects[4],
								(Integer) objects[5], (String) objects[6], (String) objects[7], (String) objects[8], (String) objects[9],
								(String) objects[10], (String) objects[11], (String) objects[12], (String) objects[13], (Long) objects[14],
								(String) objects[15], "PD", (String) objects[16], (Byte) objects[17], (Timestamp) objects[18], (Long) objects[19],
								(String) objects[20], (String) objects[21], (String) objects[22], (String) objects[23], (Timestamp) objects[24],
								(String) objects[25], (String) objects[26], (String) objects[27], (String) objects[28], (String) objects[29],
								(String) objects[30], (Integer) objects[31], (Integer) objects[32], (String) objects[33], (String) objects[34]));
			}
		}

		return pdSummaryReport;

	}

	@Override
	public List<EpidemicoutbreakReport> getEpidemicoutbreakReport(String request) throws Exception
	{

		logger.debug("getEpidemicoutbreakReport request: " + request);
		EpidemicoutbreakReport epidemicoutbreakReportRequest = inputMapper.gson().fromJson(request, EpidemicoutbreakReport.class);
		List<EpidemicoutbreakReport> epidemicoutbreakReports = new ArrayList<EpidemicoutbreakReport>();
		List<Objects[]> reports =
				epidemicoutbreakReportRepository.getEpidemicoutbreakReportByDate(
						epidemicoutbreakReportRequest.getProviderServiceMapID(), epidemicoutbreakReportRequest.getStartDateTime(),
						epidemicoutbreakReportRequest.getEndDateTime());

		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				epidemicoutbreakReports.add(
						new EpidemicoutbreakReport(
								(Long) objects[0], (String) objects[1], (Long) objects[2], (Long) objects[3], (String) objects[4],
								(Integer) objects[5], (String) objects[6], (String) objects[7], (String) objects[8], (String) objects[9],
								(Timestamp) objects[10], (Long) objects[11], (String) objects[12], (String) objects[13], (String) objects[14],
								(String) objects[15], (Timestamp) objects[16], (String) objects[17], (String) objects[18], (String) objects[19],
								(String) objects[20], (String) objects[21], (String) objects[22], (Integer) objects[23], (Integer) objects[24], (String) objects[25], (String) objects[26]));
			}
		}

		return epidemicoutbreakReports;

	}

	@Override
	public List<BloodrequestReport> getBloodRequestReport(String request) throws Exception
	{

		logger.info("getBloodOnCallCountReportByDate request: " + request);
		BloodrequestReport bloodrequestReportRequest = inputMapper.gson().fromJson(request, BloodrequestReport.class);
		List<BloodrequestReport> bloodrequestReport = new ArrayList<BloodrequestReport>();
		List<Objects[]> reports =
				bloodRequestReportRepository.getBloodRequestReportByDate(
						bloodrequestReportRequest.getProviderServiceMapID(), bloodrequestReportRequest.getStartDateTime(),
						bloodrequestReportRequest.getEndDateTime());

		logger.info("reports count: " + reports.size());

		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				bloodrequestReport.add(
						new BloodrequestReport(
								(Long) objects[0], (String) objects[1], (Long) objects[2], (Long) objects[3], (Long) objects[4], (String) objects[5],
								(Integer) objects[6], (String) objects[7], (String) objects[8], (String) objects[9], (String) objects[10],
								(String) objects[11], (String) objects[12], (String) objects[13], (Boolean) objects[14], (Timestamp) objects[15],
								(String) objects[16], (String) objects[17], (String) objects[18], (String) objects[19], (String) objects[20],
								(String) objects[21], (Byte) objects[22], (Timestamp) objects[23], (Long) objects[24], (String) objects[25],
								(String) objects[26], (String) objects[27], (String) objects[28], (Timestamp) objects[29], (String) objects[30],
								(String) objects[31], (String) objects[32], (String) objects[33], (String) objects[34], (String) objects[35],
								(Integer) objects[36], (Integer) objects[37],(String) objects[38],(String) objects[39]));
			}
		}

		return bloodrequestReport;

	}

	@Override
	public List<DirectoryserviceReport> getDirectoryserviceReport(String request) throws Exception
	{

		logger.debug("getDirectoryserviceReport request: " + request);
		DirectoryserviceReport directoryServiceReportRequest = inputMapper.gson().fromJson(request, DirectoryserviceReport.class);
		List<DirectoryserviceReport> directoryServiceReport = new ArrayList<DirectoryserviceReport>();
		List<Objects[]> reports =
				directoryServiceReportRepository.getDirectoryServiceReportByDate(
						directoryServiceReportRequest.getProviderServiceMapID(), directoryServiceReportRequest.getStartDateTime(),
						directoryServiceReportRequest.getEndDateTime());

		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				directoryServiceReport.add(
						new DirectoryserviceReport(
								(Long) objects[0], (String) objects[1], (Long) objects[2], (Long) objects[3], (String) objects[4],
								(String) objects[5], (Integer) objects[6], (String) objects[7], (String) objects[8], (String) objects[9],
								(String) objects[10], (String) objects[11], (String) objects[12], (Timestamp) objects[13], (Long) objects[14],
								(String) objects[15], (String) objects[16], (String) objects[17], (String) objects[18], (Timestamp) objects[19],
								(String) objects[20], (String) objects[21], (String) objects[22], (String) objects[23], (String) objects[24],
								(String) objects[25], (Integer) objects[26], (Integer) objects[27],(String) objects[28],(String) objects[29]));
			}
		}

		return directoryServiceReport;

	}

	@Override
	public List<OrgandonationReport> getOrgandonationReport(String request) throws Exception
	{

		logger.debug("getOrgandonationReport request: " + request);
		OrgandonationReport organdonationReportRequest = inputMapper.gson().fromJson(request, OrgandonationReport.class);
		List<OrgandonationReport> organdonationReport = new ArrayList<OrgandonationReport>();
		List<Objects[]> reports =
				organdonationReportRepository.getOrgandonationReportByDate(
						organdonationReportRequest.getProviderServiceMapID(), organdonationReportRequest.getStartDateTime(),
						organdonationReportRequest.getEndDateTime());

		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				organdonationReport.add(
						new OrgandonationReport(
								(Long) objects[0], (String) objects[1], (Long) objects[2], (Long) objects[3], (String) objects[4],
								(Integer) objects[5], (String) objects[6], (String) objects[7], (String) objects[8], (String) objects[9],
								(String) objects[10], (String) objects[11], (String) objects[12], (String) objects[13], (String) objects[14],
								(Byte) objects[15], (Timestamp) objects[16], (Long) objects[17], (String) objects[18], (String) objects[19],
								(String) objects[20], (String) objects[21], (Timestamp) objects[22], (String) objects[23], (String) objects[24],
								(String) objects[25], (String) objects[26], (String) objects[27], (String) objects[28], (Integer) objects[29],
								(Integer) objects[30],(String) objects[31],(String) objects[32]));
			}
		}

		return organdonationReport;

	}

	@Override
	public List<SchemeserviceReport> getSchemeServiceReport(String request) throws Exception
	{

		logger.debug("geSchemeserviceReport request: " + request);
		SchemeserviceReport schemeReportRequest = inputMapper.gson().fromJson(request, SchemeserviceReport.class);
		List<SchemeserviceReport> schemeReport = new ArrayList<SchemeserviceReport>();
		List<Objects[]> reports =
				schemesReportRepository.getSchemeServiceReportByDate(
						schemeReportRequest.getProviderServiceMapID(), schemeReportRequest.getStartDateTime(), schemeReportRequest.getEndDateTime());

		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				schemeReport.add(
						new SchemeserviceReport(
								(Long) objects[0], (String) objects[1], (Long) objects[2], (Long) objects[3], (Integer) objects[4],
								(String) objects[5], (String) objects[6], (String) objects[7], (Timestamp) objects[8], (Long) objects[9],
								(String) objects[10], (String) objects[11], (String) objects[12], (String) objects[13], (Timestamp) objects[14],
								(String) objects[15], (String) objects[16], (String) objects[17], (String) objects[18], (String) objects[19],
								(String) objects[20], (Integer) objects[21], (Integer) objects[22],(String) objects[23],(String) objects[24]));
			}
		}

		return schemeReport;

	}

	@Override
	public List<FeedbackReport> getFeedbackReport(String request) throws Exception
	{

		logger.debug("geFeedbackReport request: " + request);
		FeedbackReport feedbackReportRequest = inputMapper.gson().fromJson(request, FeedbackReport.class);
		List<FeedbackReport> feedbackReport = new ArrayList<FeedbackReport>();
		List<Objects[]> reports;

		if (feedbackReportRequest.getFeedbackTypeID() == null
				&& (feedbackReportRequest.getAgentID() == null || feedbackReportRequest.getAgentID() == ""))
		{
			reports =
					feedbackReportRepository.getFeedbackReportByDate(
							feedbackReportRequest.getProviderServiceMapID(), feedbackReportRequest.getStartDateTime(),
							feedbackReportRequest.getEndDateTime());
		} else if (feedbackReportRequest.getFeedbackTypeID() != null
				&& (feedbackReportRequest.getAgentID() == null || feedbackReportRequest.getAgentID() == ""))
		{
			reports =
					feedbackReportRepository.getFeedbackReportByTypeAndDate(
							feedbackReportRequest.getProviderServiceMapID(), feedbackReportRequest.getStartDateTime(),
							feedbackReportRequest.getEndDateTime(), feedbackReportRequest.getFeedbackTypeID());
		} else if (feedbackReportRequest.getFeedbackTypeID() == null
				&& (feedbackReportRequest.getAgentID() != null && feedbackReportRequest.getAgentID() != ""))
		{
			reports =
					feedbackReportRepository.getFeedbackReportByAgentIDAndDate(
							feedbackReportRequest.getProviderServiceMapID(), feedbackReportRequest.getAgentID(),
							feedbackReportRequest.getStartDateTime(), feedbackReportRequest.getEndDateTime());
		} else
		{
			reports =
					feedbackReportRepository.getFeedbackReportByAgendIDTypeAndDate(
							feedbackReportRequest.getProviderServiceMapID(), feedbackReportRequest.getAgentID(),
							feedbackReportRequest.getStartDateTime(), feedbackReportRequest.getEndDateTime(),
							feedbackReportRequest.getFeedbackTypeID());
		}

		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				feedbackReport.add(
						new FeedbackReport(
								(Long) objects[0], (String) objects[1], (Long) objects[2], (Long) objects[3], (String) objects[4],
								(String) objects[5], (String) objects[6], (String) objects[7], (String) objects[8], (String) objects[9],
								(String) objects[10], (String) objects[11], (String) objects[12], (String) objects[13], (String) objects[14],
								(String) objects[15], (String) objects[16], (Integer) objects[17], (String) objects[18], (Timestamp) objects[19],
								(Timestamp) objects[20], (Long) objects[21], (String) objects[22], (String) objects[23], (String) objects[24],
								(String) objects[25], (Timestamp) objects[26], (String) objects[27], (String) objects[28], (String) objects[29],
								(String) objects[30], (String) objects[31], (String) objects[32], (Integer) objects[33], (Integer) objects[34],(String) objects[35],(String) objects[36]));
			}
		}

		return feedbackReport;

	}

	@Override
	public List<FoodsafetyReport> getFoodsafetyReport(String request) throws Exception
	{

		logger.debug("getFoodsafetyReport request: " + request);
		FoodsafetyReport foodsafetyReportRequest = inputMapper.gson().fromJson(request, FoodsafetyReport.class);
		List<FoodsafetyReport> foodsafetyReport = new ArrayList<FoodsafetyReport>();
		List<Objects[]> reports =
				foodSafetyReportRepository.getFoodsafetyReportByDate(
						foodsafetyReportRequest.getProviderServiceMapID(), foodsafetyReportRequest.getStartDateTime(),
						foodsafetyReportRequest.getEndDateTime());

		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				foodsafetyReport.add(
						new FoodsafetyReport(
								(Long) objects[0], (String) objects[1], (Long) objects[2], (Long) objects[3], (String) objects[4], (Byte) objects[5],
								(Byte) objects[6], (Byte) objects[7], (Byte) objects[8], (Byte) objects[9], (Byte) objects[10], (Byte) objects[11],
								(Timestamp) objects[12], (String) objects[13], (Byte) objects[14], (String) objects[15], (String) objects[16],
								(String) objects[17], (String) objects[18], (String) objects[19], (Boolean) objects[20], (String) objects[21], (Integer) objects[22], (String) objects[23], (Timestamp) objects[24], (Long) objects[25],
								(String) objects[26], (String) objects[27], (String) objects[28], (String) objects[29], (Timestamp) objects[30],
								(String) objects[31], (String) objects[32], (String) objects[33], (String) objects[34], (String) objects[35],
								(String) objects[36], (Integer) objects[37], (Integer) objects[38], (String) objects[39], (String) objects[40]));
			}
		}

		return foodsafetyReport;

	}

	@Override
	public List<PrescriptionReport> getPrescriptionReport(String request) throws Exception
	{

		logger.debug("getPrescriptionReport request: " + request);
		PrescriptionReport prescriptionReportRequest = inputMapper.gson().fromJson(request, PrescriptionReport.class);
		List<PrescriptionReport> prescriptionReport = new ArrayList<PrescriptionReport>();
		List<Objects[]> reports =
				prescriptionReportRepository.getPrescriptionReportByDate(
						prescriptionReportRequest.getProviderServiceMapID(), prescriptionReportRequest.getStartDateTime(),
						prescriptionReportRequest.getEndDateTime());

		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				prescriptionReport.add(
						new PrescriptionReport(
								(Long) objects[0], (Long) objects[1], (Long) objects[2], (Integer) objects[3], (String) objects[4],
								(String) objects[5], (String) objects[6], (String) objects[7], (String) objects[8], (String) objects[9],
								(String) objects[10], (String) objects[11], (Integer) objects[12], (String) objects[13], (String) objects[14],
								(Timestamp) objects[15], (Timestamp) objects[16], (Long) objects[17], (String) objects[18], (String) objects[19],
								(String) objects[20], (String) objects[21], (Timestamp) objects[22], (String) objects[23], (String) objects[24],
								(String) objects[25], (String) objects[26], (String) objects[27], (String) objects[28], (Integer) objects[29],
								(Integer) objects[30],(String) objects[31],(String) objects[32]));
			}
		}

		return prescriptionReport;

	}
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public String getBloodOnCallCountReportByDate(String request) throws Exception
	{

		logger.info("getBloodOnCallCountReportByDate request: " + request);
		BloodrequestReport bloodrequestReportRequest = inputMapper.gson().fromJson(request, BloodrequestReport.class);
		
		if(bloodrequestReportRequest.getSearchCriteria().equalsIgnoreCase("Component"))
		{
			List<BloodOnRequestCountModel> bloodrequestReport = new ArrayList<BloodOnRequestCountModel>();
			BloodOnRequestCountModel wbccountModel= getWBCCount(bloodrequestReportRequest);
			BloodOnRequestCountModel rbccountModel = getRBCCount(bloodrequestReportRequest);
			BloodOnRequestCountModel othercountModel=getOthersCount(bloodrequestReportRequest);
			bloodrequestReport.add(wbccountModel);
			bloodrequestReport.add(rbccountModel);
			bloodrequestReport.add(othercountModel);
			
			return bloodrequestReport.toString();
		}
		
		else if(bloodrequestReportRequest.getSearchCriteria().equalsIgnoreCase("Group"))
		{
			List<BloodGroupCountReportModel> groupCountList = new ArrayList<BloodGroupCountReportModel>();
					BloodGroupCountReportModel countAPos= getBloodGroupCount(bloodrequestReportRequest, 1);
					BloodGroupCountReportModel countANeg= getBloodGroupCount(bloodrequestReportRequest, 2);
					BloodGroupCountReportModel countBPos= getBloodGroupCount(bloodrequestReportRequest, 3);
					BloodGroupCountReportModel countBNeg= getBloodGroupCount(bloodrequestReportRequest, 4);
					groupCountList.add(countAPos);
					groupCountList.add(countANeg);
					groupCountList.add(countBPos);
					groupCountList.add(countBNeg);
					
			return groupCountList.toString();
		}
		
		else if(bloodrequestReportRequest.getSearchCriteria().equalsIgnoreCase("District Wise Component"))
		{
			Integer stateID = bloodRequestReportRepository.getStateByProvider(bloodrequestReportRequest.getProviderServiceMapID());
			List<Districts> list= bloodRequestReportRepository.getDistrictByStateID(stateID);
			
			List<BloodCompDistrictModel> reportList = new ArrayList<BloodCompDistrictModel>();
			BloodCompDistrictModel model =null;
			
			for(Districts district : list)
			{
				model =new BloodCompDistrictModel();
				Long totalCount = getTotalCountByDistrict(bloodrequestReportRequest, district.getDistrictID());
				Long wbcCount = getWBCCountByDistrict(bloodrequestReportRequest, district.getDistrictID());
				Long rbcCount = getRBCCountByDistrict(bloodrequestReportRequest, district.getDistrictID());
				Long otherCount = getOtherCountByDistrict(bloodrequestReportRequest, district.getDistrictID());
				
				model.setDistrict(district.getDistrictName());
				model.setTotalCount(totalCount);
				model.setWBC(wbcCount);
				model.setRedBloodCells(rbcCount);
				model.setOthers(otherCount);
				
				reportList.add(model);
				
			}
			return reportList.toString();
		}
		
		else if(bloodrequestReportRequest.getSearchCriteria().equalsIgnoreCase("District Wise Group"))
		{
			Integer stateID = bloodRequestReportRepository.getStateByProvider(bloodrequestReportRequest.getProviderServiceMapID());
			List<Districts> list= bloodRequestReportRepository.getDistrictByStateID(stateID);
			
			List<BloodGroupDistrictModel> reportList = new ArrayList<BloodGroupDistrictModel>();
			BloodGroupDistrictModel model =null;
			
			for(Districts district : list)
			{
				model =new BloodGroupDistrictModel();
				Long totalCount = getTotalBloodGroupCountDistrictWise(bloodrequestReportRequest, district.getDistrictID());
				Long Apos = getBloodGroupCountDistrictWise(bloodrequestReportRequest, district.getDistrictID(),1);
				Long Aneg = getBloodGroupCountDistrictWise(bloodrequestReportRequest, district.getDistrictID(),2);
				Long Bpos = getBloodGroupCountDistrictWise(bloodrequestReportRequest, district.getDistrictID(),3);
				Long Bneg = getBloodGroupCountDistrictWise(bloodrequestReportRequest, district.getDistrictID(),4);
				
				model.setDistrict(district.getDistrictName());
				model.setTotalCount(totalCount);
				model.setAPositive(Apos);
				model.setANegative(Aneg);
				model.setBPositive(Bpos);
				model.setBNegative(Bneg);
				
				reportList.add(model);
				
			}
			return reportList.toString();
		}
		
		return request;
	}
	
	private BloodOnRequestCountModel getWBCCount(BloodrequestReport bloodrequestReportRequest)
	{
		BloodOnRequestCountModel countModel= null;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BloodrequestReport> criteriaQuery = criteriaBuilder.createQuery(BloodrequestReport.class);
		Root<BloodrequestReport> root = criteriaQuery.from(BloodrequestReport.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		

		predicates.add(criteriaBuilder.between(root.get("createdDate"), bloodrequestReportRequest.getStartDateTime(), bloodrequestReportRequest.getEndDateTime()));
		predicates.add(criteriaBuilder.equal(root.get("providerServiceMapID"),bloodrequestReportRequest.getProviderServiceMapID()));

		Join<BloodrequestReport, BeneficiaryReport> benReport = root.join("beneficiaryReport", JoinType.INNER);
		
		
		if (bloodrequestReportRequest.getDistrictID() != null
				|| bloodrequestReportRequest.getSubDistrictID() != null	|| bloodrequestReportRequest.getVillageID() != null)
		{
			
			predicates.add(criteriaBuilder.isNotNull(root.get("beneficiaryRegID")));
			
			if (bloodrequestReportRequest.getDistrictID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("PermDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("PermDistrictId"),
						bloodrequestReportRequest.getDistrictID()));
			}
			if (bloodrequestReportRequest.getSubDistrictID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("permSubDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("permSubDistrictId"),
						bloodrequestReportRequest.getSubDistrictID()));
			}
			if (bloodrequestReportRequest.getVillageID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("permVillageId")));
				predicates.add(criteriaBuilder.equal(benReport.get("permVillageId"),
						bloodrequestReportRequest.getVillageID()));
			}

		}
		
		predicates.add(criteriaBuilder.equal(root.get("componentTypeID"),3));
		
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}))
				.orderBy(criteriaBuilder.desc(root.get("fact_BloodRequestID")));
		TypedQuery<BloodrequestReport> typedQueryWBC = entityManager.createQuery(criteriaQuery);
		int WBC = typedQueryWBC.getResultList().size();
		
		countModel =new BloodOnRequestCountModel();
		countModel.setComponentType("WBC");
		countModel.setCount(Long.valueOf(WBC));
		
		return countModel;
	}
	
	private BloodOnRequestCountModel getRBCCount(BloodrequestReport bloodrequestReportRequest)
	{
		BloodOnRequestCountModel countModel= null;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BloodrequestReport> criteriaQuery = criteriaBuilder.createQuery(BloodrequestReport.class);
		Root<BloodrequestReport> root = criteriaQuery.from(BloodrequestReport.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		

		predicates.add(criteriaBuilder.between(root.get("createdDate"), bloodrequestReportRequest.getStartDateTime(), bloodrequestReportRequest.getEndDateTime()));
		predicates.add(criteriaBuilder.equal(root.get("providerServiceMapID"),bloodrequestReportRequest.getProviderServiceMapID()));

		Join<BloodrequestReport, BeneficiaryReport> benReport = root.join("beneficiaryReport", JoinType.INNER);
		
		
		if (bloodrequestReportRequest.getDistrictID() != null
				|| bloodrequestReportRequest.getSubDistrictID() != null	|| bloodrequestReportRequest.getVillageID() != null)
		{
			
			predicates.add(criteriaBuilder.isNotNull(root.get("beneficiaryRegID")));
			
			if (bloodrequestReportRequest.getDistrictID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("PermDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("PermDistrictId"),
						bloodrequestReportRequest.getDistrictID()));
			}
			if (bloodrequestReportRequest.getSubDistrictID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("permSubDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("permSubDistrictId"),
						bloodrequestReportRequest.getSubDistrictID()));
			}
			if (bloodrequestReportRequest.getVillageID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("permVillageId")));
				predicates.add(criteriaBuilder.equal(benReport.get("permVillageId"),
						bloodrequestReportRequest.getVillageID()));
			}

		}
		
		predicates.add(criteriaBuilder.equal(root.get("componentTypeID"),2));
		
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}))
				.orderBy(criteriaBuilder.desc(root.get("fact_BloodRequestID")));
		TypedQuery<BloodrequestReport> typedQueryRBC = entityManager.createQuery(criteriaQuery);
		int RBC = typedQueryRBC.getResultList().size();
		
		countModel =new BloodOnRequestCountModel();
		countModel.setComponentType("Red Blood cells");
		countModel.setCount(Long.valueOf(RBC));
		
		return countModel;
	}
	
	private BloodOnRequestCountModel getOthersCount(BloodrequestReport bloodrequestReportRequest)
	{
		BloodOnRequestCountModel countModel= null;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BloodrequestReport> criteriaQuery = criteriaBuilder.createQuery(BloodrequestReport.class);
		Root<BloodrequestReport> root = criteriaQuery.from(BloodrequestReport.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate predi=null;

		predicates.add(criteriaBuilder.between(root.get("createdDate"), bloodrequestReportRequest.getStartDateTime(), bloodrequestReportRequest.getEndDateTime()));
		predicates.add(criteriaBuilder.equal(root.get("providerServiceMapID"),bloodrequestReportRequest.getProviderServiceMapID()));

		Join<BloodrequestReport, BeneficiaryReport> benReport = root.join("beneficiaryReport", JoinType.INNER);
		
		if (bloodrequestReportRequest.getDistrictID() != null
				|| bloodrequestReportRequest.getSubDistrictID() != null	|| bloodrequestReportRequest.getVillageID() != null)
		{
			
			predicates.add(criteriaBuilder.isNotNull(root.get("beneficiaryRegID")));
			
			if (bloodrequestReportRequest.getDistrictID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("PermDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("PermDistrictId"),
						bloodrequestReportRequest.getDistrictID()));
			}
			if (bloodrequestReportRequest.getSubDistrictID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("permSubDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("permSubDistrictId"),
						bloodrequestReportRequest.getSubDistrictID()));
			}
			if (bloodrequestReportRequest.getVillageID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("permVillageId")));
				predicates.add(criteriaBuilder.equal(benReport.get("permVillageId"),
						bloodrequestReportRequest.getVillageID()));
			}

		}
		predi = criteriaBuilder.and(criteriaBuilder.notEqual(root.get("componentTypeID"),2),criteriaBuilder.notEqual(root.get("componentTypeID"),3),
				criteriaBuilder.isNotNull(root.get("componentTypeID"))); 
		
		predicates.add(predi);
		
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}))
				.orderBy(criteriaBuilder.desc(root.get("fact_BloodRequestID")));
		TypedQuery<BloodrequestReport> typedQueryOther = entityManager.createQuery(criteriaQuery);
		int Other = typedQueryOther.getResultList().size();
		
		countModel =new BloodOnRequestCountModel();
		countModel.setComponentType("Others");
		countModel.setCount(Long.valueOf(Other));
		
		return countModel;
	}
	
	private BloodGroupCountReportModel getBloodGroupCount(BloodrequestReport bloodrequestReportRequest, Integer bloodGroupID)
	{
		BloodGroupCountReportModel countModel= null;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BloodrequestReport> criteriaQuery = criteriaBuilder.createQuery(BloodrequestReport.class);
		Root<BloodrequestReport> root = criteriaQuery.from(BloodrequestReport.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(criteriaBuilder.between(root.get("createdDate"), bloodrequestReportRequest.getStartDateTime(), bloodrequestReportRequest.getEndDateTime()));
		predicates.add(criteriaBuilder.equal(root.get("providerServiceMapID"),bloodrequestReportRequest.getProviderServiceMapID()));

		Join<BloodrequestReport, BeneficiaryReport> benReport = root.join("beneficiaryReport", JoinType.INNER);
		
		if (bloodrequestReportRequest.getDistrictID() != null
				|| bloodrequestReportRequest.getSubDistrictID() != null	|| bloodrequestReportRequest.getVillageID() != null)
		{
			
			predicates.add(criteriaBuilder.isNotNull(root.get("beneficiaryRegID")));
			
			if (bloodrequestReportRequest.getDistrictID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("PermDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("PermDistrictId"),
						bloodrequestReportRequest.getDistrictID()));
			}
			if (bloodrequestReportRequest.getSubDistrictID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("permSubDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("permSubDistrictId"),
						bloodrequestReportRequest.getSubDistrictID()));
			}
			if (bloodrequestReportRequest.getVillageID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("permVillageId")));
				predicates.add(criteriaBuilder.equal(benReport.get("permVillageId"),
						bloodrequestReportRequest.getVillageID()));
			}
		}
		
		predicates.add(criteriaBuilder.equal(root.get("bloodGroupID"),bloodGroupID));
		
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}))
				.orderBy(criteriaBuilder.desc(root.get("fact_BloodRequestID")));
		TypedQuery<BloodrequestReport> typedQuery = entityManager.createQuery(criteriaQuery);
		int groupcount = typedQuery.getResultList().size();
		
		countModel =new BloodGroupCountReportModel();
		if(bloodGroupID==1)
		{
			countModel.setGroup("A+ve");
		}
		else if(bloodGroupID==2)
		{
			countModel.setGroup("A-ve");
		}
		else if(bloodGroupID==3)
		{
			countModel.setGroup("B+ve");
		}
		else if(bloodGroupID==4)
		{
			countModel.setGroup("B-ve");
		}
		countModel.setCount(Long.valueOf(groupcount));
		
		return countModel;
	}
	
	private Long getWBCCountByDistrict(BloodrequestReport bloodrequestReportRequest, Integer districtID)
	{
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BloodrequestReport> criteriaQuery = criteriaBuilder.createQuery(BloodrequestReport.class);
		Root<BloodrequestReport> root = criteriaQuery.from(BloodrequestReport.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(criteriaBuilder.between(root.get("createdDate"), bloodrequestReportRequest.getStartDateTime(), bloodrequestReportRequest.getEndDateTime()));
		predicates.add(criteriaBuilder.equal(root.get("providerServiceMapID"),bloodrequestReportRequest.getProviderServiceMapID()));

		Join<BloodrequestReport, BeneficiaryReport> benReport = root.join("beneficiaryReport", JoinType.INNER);
			
			predicates.add(criteriaBuilder.isNotNull(root.get("beneficiaryRegID")));
			
				predicates.add(criteriaBuilder.isNotNull(benReport.get("PermDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("PermDistrictId"),districtID));
			
			predicates.add(criteriaBuilder.equal(root.get("componentTypeID"),3));
		
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}))
				.orderBy(criteriaBuilder.desc(root.get("fact_BloodRequestID")));
		TypedQuery<BloodrequestReport> typedQuery = entityManager.createQuery(criteriaQuery);
		int WBC = typedQuery.getResultList().size();
		
		
		return Long.valueOf(WBC);
	}
	
	private Long getRBCCountByDistrict(BloodrequestReport bloodrequestReportRequest, Integer districtID)
	{
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BloodrequestReport> criteriaQuery = criteriaBuilder.createQuery(BloodrequestReport.class);
		Root<BloodrequestReport> root = criteriaQuery.from(BloodrequestReport.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(criteriaBuilder.between(root.get("createdDate"), bloodrequestReportRequest.getStartDateTime(), bloodrequestReportRequest.getEndDateTime()));
		predicates.add(criteriaBuilder.equal(root.get("providerServiceMapID"),bloodrequestReportRequest.getProviderServiceMapID()));

		Join<BloodrequestReport, BeneficiaryReport> benReport = root.join("beneficiaryReport", JoinType.INNER);
			
			predicates.add(criteriaBuilder.isNotNull(root.get("beneficiaryRegID")));
			
				predicates.add(criteriaBuilder.isNotNull(benReport.get("PermDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("PermDistrictId"),districtID));
			
			predicates.add(criteriaBuilder.equal(root.get("componentTypeID"),3));
		
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}))
				.orderBy(criteriaBuilder.desc(root.get("fact_BloodRequestID")));
		TypedQuery<BloodrequestReport> typedQuery = entityManager.createQuery(criteriaQuery);
		int RBC = typedQuery.getResultList().size();
		
		
		return Long.valueOf(RBC);
	}
	
	private Long getTotalCountByDistrict(BloodrequestReport bloodrequestReportRequest, Integer districtID)
	{
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BloodrequestReport> criteriaQuery = criteriaBuilder.createQuery(BloodrequestReport.class);
		Root<BloodrequestReport> root = criteriaQuery.from(BloodrequestReport.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(criteriaBuilder.between(root.get("createdDate"), bloodrequestReportRequest.getStartDateTime(), bloodrequestReportRequest.getEndDateTime()));
		predicates.add(criteriaBuilder.equal(root.get("providerServiceMapID"),bloodrequestReportRequest.getProviderServiceMapID()));

		Join<BloodrequestReport, BeneficiaryReport> benReport = root.join("beneficiaryReport", JoinType.INNER);
			
			predicates.add(criteriaBuilder.isNotNull(root.get("beneficiaryRegID")));
			
				predicates.add(criteriaBuilder.isNotNull(benReport.get("PermDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("PermDistrictId"),districtID));
			
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}))
				.orderBy(criteriaBuilder.desc(root.get("fact_BloodRequestID")));
		TypedQuery<BloodrequestReport> typedQuery = entityManager.createQuery(criteriaQuery);
		int RBC = typedQuery.getResultList().size();
		
		
		return Long.valueOf(RBC);
	}
	
	private Long getOtherCountByDistrict(BloodrequestReport bloodrequestReportRequest, Integer districtID)
	{
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BloodrequestReport> criteriaQuery = criteriaBuilder.createQuery(BloodrequestReport.class);
		Root<BloodrequestReport> root = criteriaQuery.from(BloodrequestReport.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(criteriaBuilder.between(root.get("createdDate"), bloodrequestReportRequest.getStartDateTime(), bloodrequestReportRequest.getEndDateTime()));
		predicates.add(criteriaBuilder.equal(root.get("providerServiceMapID"),bloodrequestReportRequest.getProviderServiceMapID()));

		Join<BloodrequestReport, BeneficiaryReport> benReport = root.join("beneficiaryReport", JoinType.INNER);
			
			predicates.add(criteriaBuilder.isNotNull(root.get("beneficiaryRegID")));
			
				predicates.add(criteriaBuilder.isNotNull(benReport.get("PermDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("PermDistrictId"),districtID));
				
				Predicate predi=null;
				predi = criteriaBuilder.and(criteriaBuilder.notEqual(root.get("componentTypeID"),2),criteriaBuilder.notEqual(root.get("componentTypeID"),3),
						criteriaBuilder.isNotNull(root.get("componentTypeID"))); 
				
				predicates.add(predi);
		
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}))
				.orderBy(criteriaBuilder.desc(root.get("fact_BloodRequestID")));
		TypedQuery<BloodrequestReport> typedQuery = entityManager.createQuery(criteriaQuery);
		int RBC = typedQuery.getResultList().size();
		
		
		return Long.valueOf(RBC);
	}
	
	private Long getBloodGroupCountDistrictWise(BloodrequestReport bloodrequestReportRequest, Integer districtID, Integer bloodGroupID)
	{
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BloodrequestReport> criteriaQuery = criteriaBuilder.createQuery(BloodrequestReport.class);
		Root<BloodrequestReport> root = criteriaQuery.from(BloodrequestReport.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(criteriaBuilder.between(root.get("createdDate"), bloodrequestReportRequest.getStartDateTime(), bloodrequestReportRequest.getEndDateTime()));
		predicates.add(criteriaBuilder.equal(root.get("providerServiceMapID"),bloodrequestReportRequest.getProviderServiceMapID()));

		Join<BloodrequestReport, BeneficiaryReport> benReport = root.join("beneficiaryReport", JoinType.INNER);
			
			predicates.add(criteriaBuilder.isNotNull(root.get("beneficiaryRegID")));
				predicates.add(criteriaBuilder.isNotNull(benReport.get("PermDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("PermDistrictId"),districtID));

		predicates.add(criteriaBuilder.equal(root.get("bloodGroupID"),bloodGroupID));
		
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}))
				.orderBy(criteriaBuilder.desc(root.get("fact_BloodRequestID")));
		TypedQuery<BloodrequestReport> typedQuery = entityManager.createQuery(criteriaQuery);
		int groupcount = typedQuery.getResultList().size();
		
		return Long.valueOf(groupcount);
	}
	
	private Long getTotalBloodGroupCountDistrictWise(BloodrequestReport bloodrequestReportRequest, Integer districtID)
	{
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BloodrequestReport> criteriaQuery = criteriaBuilder.createQuery(BloodrequestReport.class);
		Root<BloodrequestReport> root = criteriaQuery.from(BloodrequestReport.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(criteriaBuilder.between(root.get("createdDate"), bloodrequestReportRequest.getStartDateTime(), bloodrequestReportRequest.getEndDateTime()));
		predicates.add(criteriaBuilder.equal(root.get("providerServiceMapID"),bloodrequestReportRequest.getProviderServiceMapID()));

		Join<BloodrequestReport, BeneficiaryReport> benReport = root.join("beneficiaryReport", JoinType.INNER);
		
			predicates.add(criteriaBuilder.isNotNull(root.get("beneficiaryRegID")));
				predicates.add(criteriaBuilder.isNotNull(benReport.get("PermDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("PermDistrictId"),districtID));

		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}))
				.orderBy(criteriaBuilder.desc(root.get("fact_BloodRequestID")));
		TypedQuery<BloodrequestReport> typedQuery = entityManager.createQuery(criteriaQuery);
		int groupcount = typedQuery.getResultList().size();
		
		return Long.valueOf(groupcount);
	}

	@Override
	public String getMentalHealthReport(String request) throws Exception
	{

		logger.debug("getMentalHealthReport request: " + request);
		BenmedhistoryReport benmedhistoryReportRequest = inputMapper.gson().fromJson(request, BenmedhistoryReport.class);
		if(benmedhistoryReportRequest.getSearchCriteria().equalsIgnoreCase("Guidelines"))
		{
			List<MentalHealthGuidlineReport> list = new ArrayList<MentalHealthGuidlineReport>();
			List<Objects[]> reports =
					benmedhistoryReportRepository.getMentalHealthByGuidelines(
							benmedhistoryReportRequest.getProviderServiceMapID(), benmedhistoryReportRequest.getStartDateTime(),
							benmedhistoryReportRequest.getEndDateTime());
			MentalHealthGuidlineReport report= null;
			for (Object[] objects : reports)
			{
				if (objects != null && objects.length > 0)
				{
					report = new MentalHealthGuidlineReport();
					Long count = (Long) objects[0];
					String diseaseSummary= (String) objects[1];
					report.setCount(count);
					report.setGuidelines(diseaseSummary);
					list.add(report);
				}
			}

			return list.toString();

		}
		else
		{
			List<MentalHealthCategory> list = new ArrayList<MentalHealthCategory>();
			List<Objects[]> reports =
					benmedhistoryReportRepository.getMentalHealthByCategory(
							benmedhistoryReportRequest.getProviderServiceMapID(), benmedhistoryReportRequest.getStartDateTime(),
							benmedhistoryReportRequest.getEndDateTime());
			MentalHealthCategory report= null;
			for (Object[] objects : reports)
			{
				if (objects != null && objects.length > 0)
				{
					report = new MentalHealthCategory();
					Long count = (Long) objects[0];
					String category= (String) objects[1];
					report.setCount(count);
					report.setCategory(category);
					list.add(report);
				}
			}

			return list.toString();
		}
	}
	
	@Override
	public String getMedicalAdviseReport(String request) throws Exception
	{

		logger.debug("getMedicalAdviseReport request: " + request);
		BenmedhistoryReport benmedhistoryReportRequest = inputMapper.gson().fromJson(request, BenmedhistoryReport.class);
			String report = getMedicalAdviseByDisease(benmedhistoryReportRequest);
			return report;
	}
	
	private String getMedicalAdviseByDisease(BenmedhistoryReport request)
	{
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		Root<BenmedhistoryReport> root = criteriaQuery.from(BenmedhistoryReport.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(criteriaBuilder.between(root.get("createdDate"), request.getStartDateTime(), request.getEndDateTime()));
		predicates.add(criteriaBuilder.equal(root.get("providerServiceMapID"),request.getProviderServiceMapID()));

		Join<BenmedhistoryReport, BencallReport> benCallReport = root.join("bencallReport", JoinType.INNER);
		Join<BencallReport, UserReport> userReport = benCallReport.join("userReportObj", JoinType.INNER);
		Join<UserReport, UserServiceRoleReport> userServiceRoleReport = userReport.join("userServiceRoleReport", JoinType.INNER);
		Join<UserServiceRoleReport, WorkLocation> workLocation = userServiceRoleReport.join("workLocation", JoinType.INNER);
		Join<BenmedhistoryReport, BeneficiaryReport> benReport = root.join("beneficiaryReport", JoinType.INNER);
		
		if (request.getDistrictID() != null
				|| request.getSubDistrictID() != null	|| request.getVillageID() != null || request.getRoleID()!=null || request.getLocationID() !=null )
		{
			
			predicates.add(criteriaBuilder.isNotNull(root.get("beneficiaryRegID")));
			
			if (request.getDistrictID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("PermDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("PermDistrictId"),
						request.getDistrictID()));
			}
			if (request.getSubDistrictID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("permSubDistrictId")));
				predicates.add(criteriaBuilder.equal(benReport.get("permSubDistrictId"),
						request.getSubDistrictID()));
			}
			if (request.getVillageID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(benReport.get("permVillageId")));
				predicates.add(criteriaBuilder.equal(benReport.get("permVillageId"),
						request.getVillageID()));
			}
			if (request.getRoleID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(userServiceRoleReport.get("roleID")));
				predicates.add(criteriaBuilder.equal(userServiceRoleReport.get("roleID"),
						request.getRoleID()));
			}
			if (request.getLocationID() != null)
			{
				predicates.add(criteriaBuilder.isNotNull(workLocation.get("psAddMapID")));
				predicates.add(criteriaBuilder.equal(workLocation.get("psAddMapID"),
						request.getLocationID()));
			}
		}
		
		criteriaQuery.multiselect(root.get("diseaseSummary"),criteriaBuilder.count(root)).groupBy(root.get("diseaseSummary")).where(predicates.toArray(new Predicate[] {}))
				.orderBy(criteriaBuilder.desc(root.get("fact_104BenMedHistoryID")));
		
		List<Object[]> results = entityManager.createQuery(criteriaQuery).getResultList();
		
		List<MedicalAdviseDiseaseReport> list = new ArrayList<MedicalAdviseDiseaseReport>();
		MedicalAdviseDiseaseReport report=null;
		for(Object[] object : results){
			
				String disease=	(String) object[0];
				Long count= 	(Long) object[1];
				report = new MedicalAdviseDiseaseReport();
				report.setDiseaseSummary(disease);
				report.setCount(count);
				list.add(report);
		}
		
		return list.toString();
	}
	
	@Override
	public String getCDIResponseReport(String request) throws Exception
	{

		logger.debug("getCDIResponseReport request: " + request);
		BenCDIResponseReport benCDIResponseReport = inputMapper.gson().fromJson(request, BenCDIResponseReport.class);
		
		List<Objects[]> reports=null;
		
		if(benCDIResponseReport.getAgentID()!=null)
		{
			reports =
					bloodRequestReportRepository.getCDIResponseReportByAgentID(
							benCDIResponseReport.getProviderServiceMapID(), benCDIResponseReport.getStartDateTime(), benCDIResponseReport.getEndDateTime(),benCDIResponseReport.getAgentID());
		}
		else {
			reports=
				bloodRequestReportRepository.getCDIResponseReport(
						benCDIResponseReport.getProviderServiceMapID(), benCDIResponseReport.getStartDateTime(), benCDIResponseReport.getEndDateTime());
		}
		List<BenCDIResponseReport> list = new ArrayList<BenCDIResponseReport>();
		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{

				list.add(
						new BenCDIResponseReport(
								(String) objects[0], (String) objects[1], (Integer) objects[2], (Long) objects[3], (String) objects[4], (String) objects[5],
								(String) objects[6], (String) objects[7], (Timestamp) objects[8], (String) objects[9], (String) objects[10],
								(String) objects[11], (String) objects[12], (String) objects[13], (String) objects[14], (String) objects[15], (String) objects[16]));
			}
		}

		return list.toString();
	}

}
