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
package com.iemr.helpline104.service.IMRMMR;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iemr.helpline104.data.IMRMMR.IMRMMR;
import com.iemr.helpline104.data.IMRMMR.M_facilities;
import com.iemr.helpline104.data.IMRMMR.M_supportServices;
import com.iemr.helpline104.data.IMRMMR.m_iMRMMRBaseCommunity;
import com.iemr.helpline104.data.IMRMMR.m_iMRMMRHealthworker;
import com.iemr.helpline104.data.IMRMMR.m_iMRMMRTransitType;
import com.iemr.helpline104.repository.epidemicOutbreak.EpidemicOutbreakRepository;
import com.iemr.helpline104.repository.location.LocationDistrictRepository;
import com.iemr.helpline104.repository.nodalOfficer.NodalOficerRepo;
import com.iemr.helpline104.reposotory.IMRMMR.IMRMMRRepository;
import com.iemr.helpline104.sms.SmsRequestOBJ;
import com.iemr.helpline104.utils.mapper.InputMapper;

@Service
public class IMRMMRServiceImpl implements IMRMMRService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Value("${IMRMMRSmsTemplate}")
	private String IMRMMRSmsTemplate;
	@Value("${sendSMSUrl}")
	private String sendSMSUrl;
	@Value("${sendEmailGeneralUrl}")
	private String sendEmailGeneralUrl;
	@Value("${IMRMMREmailTemplate}")
	private String IMRMMREmailTemplate;
	@Autowired
	IMRMMRRepository imrmmrRepository;
	@Autowired
	NodalOficerRepo nodalOficerRepo;
	@Autowired
	LocationDistrictRepository locationDistrictRepository;

	@Autowired
	private EpidemicOutbreakRepository epidemicOutbreakRepository;

	@Override
	public String saveIMRMMR(String request, String Authorization) throws Exception {
		logger.info("saveIMRMMR - Start");
		String ImrMmrResponse = null;

		IMRMMR imrmmr = InputMapper.gson().fromJson(request, IMRMMR.class);

		//Validating Mandatory fields
//		if(imrmmr.getVictimName() == null || imrmmr.getVictimAge() == null || imrmmr.getVictimDistrict() == null || imrmmr.getVictimTaluk() == null || imrmmr.getRelationshipType() == null) {
//			throw new Exception("error in saving IMR/MMR data as mandatory field(s) is null");
//		}

		// Convert Array to String 'SupportsServicesName'
		if (imrmmr != null && imrmmr.getSupportServicesName() != null && imrmmr.getSupportServicesName().length > 0) {
			StringBuffer sb = new StringBuffer("");
			int pointer = 1;
			for (String s : imrmmr.getSupportServicesName()) {
				if (pointer == imrmmr.getSupportServicesName().length)
					sb.append(s);
				else
					sb.append(s + "||");

				pointer++;
			}
			imrmmr.setSupportServicesName_db(sb.toString());
		} else
			imrmmr.setSupportServicesName_db(null);

		// Convert Array to String 'SupportsServicesID'
		if (imrmmr != null && imrmmr.getSupportServicesID() != null && imrmmr.getSupportServicesID().length > 0) {
			StringBuffer sb = new StringBuffer("");
			int pointer = 1;
			for (String s : imrmmr.getSupportServicesID()) {
				if (pointer == imrmmr.getSupportServicesID().length)
					sb.append(s);
				else
					sb.append(s + "||");

				pointer++;
			}
			imrmmr.setSupportServicesID_db(sb.toString());
		} else
			imrmmr.setSupportServicesName_db(null);

		// Inserting data into child fields of StagesOfDeath
		imrmmr.setDuringPregnancy(imrmmr.getStagesOfDeath().get("duringPregnancy"));
		imrmmr.setDuringDelivery(imrmmr.getStagesOfDeath().get("duringDelivery"));
		imrmmr.setWithin42daysOfDelivery(imrmmr.getStagesOfDeath().get("within42daysOfDelivery"));

		if (imrmmr.getStagesOfDeath().get("noofDelivery") != null)
			imrmmr.setNoofDelivery(Integer.parseInt(imrmmr.getStagesOfDeath().get("noofDelivery")));
		else
			imrmmr.setNoofDelivery(null);
		
		imrmmr.setAbove42daysOfDelivery(imrmmr.getStagesOfDeath().get("above42daysOfDelivery"));
		

		imrmmr = imrmmrRepository.save(imrmmr);

		String requestID = "MDSR-CDR/" + imrmmr.getVictimDistrict() + "/"
				+ new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTimeInMillis()) + "/"
				+ imrmmr.getBenImrMmrID();
		imrmmr.setRequestID(requestID);
		imrmmr.setDeleted(false);
		imrmmr = imrmmrRepository.save(imrmmr);

		if (imrmmr.getBenImrMmrID() > 0) {
			Integer stateID = 0;
			List<Object[]> contact = null;
			ImrMmrResponse = "MDSR/CDR Data Saved Successfully And Death Registration ID: " + imrmmr.getRequestID();
			if (imrmmr.getVictimTaluk() != null) {
				contact = nodalOficerRepo.findByTalukID(imrmmr.getVictimTaluk());
			}
			if (imrmmr.getVictimDistrict() != null)
				stateID = locationDistrictRepository.findStateByDistrictID(imrmmr.getVictimDistrict());
			if (contact != null) {
				for (int i = 0; i < contact.size(); i++) {
					if (contact.get(i)[0] != null) {
						createSmsGateway(Long.valueOf(imrmmr.getBeneficiaryRegID()), imrmmr.getReferenceDate(), stateID,
								contact.get(i)[0].toString(), imrmmr.getRequestID(), Authorization,
								imrmmr.getCreatedBy(),imrmmr.getInformerName());
					}
				}
				for (int i = 0; i < contact.size(); i++) {
					if (contact.get(i)[1] != null) {
						createEmailGateway(contact.get(i)[1].toString(), imrmmr.getRequestID(), Authorization);
					}
				}
			}
		} else
			throw new Exception("error in saving IMR/MMR data");

		logger.info("saveIMRMMR - End");
		return ImrMmrResponse;
	}

	public void createEmailGateway(String emailID, String requestID, String Authorization) throws Exception {
		boolean success = false;
		Map<String, String> emailReqObj = new HashMap<String, String>();

		emailReqObj.put("requestID", requestID);
		emailReqObj.put("emailType", IMRMMREmailTemplate);
		emailReqObj.put("emailID", emailID);
		try {
			String emailStatus = restTemplate(new Gson().toJson(emailReqObj), sendEmailGeneralUrl, Authorization);
			if (emailStatus != null) {
				JsonObject jsnOBJ = new JsonObject();
				JsonParser jsnParser = new JsonParser();
				JsonElement jsnElmnt = jsnParser.parse(emailStatus);
				jsnOBJ = jsnElmnt.getAsJsonObject();
				if (jsnOBJ != null && jsnOBJ.get("statusCode").getAsInt() == 200)
					success = true;
			}

			if (success)
				logger.info("IMR email sent Successfully");
		} catch (Exception e) {
			logger.info("Error in sending email" + e);
		}

	}

	public void createSmsGateway(Long benRegID, Timestamp refDate, Integer stateID, String contact, String imrmmrID,
			String Authorization, String createdBy,String informerName) {
		int returnOBJ = 0;

		String requestOBJ = createSMSRequest(IMRMMRSmsTemplate, benRegID, stateID, contact, imrmmrID, refDate,
				createdBy,informerName);

		if (requestOBJ != null) {
			String smsStatus = restTemplate(requestOBJ, sendSMSUrl, Authorization);
			if (smsStatus != null) {
				JsonObject jsnOBJ = new JsonObject();
				JsonParser jsnParser = new JsonParser();
				JsonElement jsnElmnt = jsnParser.parse(smsStatus);
				jsnOBJ = jsnElmnt.getAsJsonObject();
				if (jsnOBJ != null && jsnOBJ.get("statusCode").getAsInt() == 200)
					returnOBJ = 1;
			}
			// System.out.println("hello");
		}

		// return returnOBJ;
		if (returnOBJ == 1)
			logger.info("IMR sms sent");

	}

	public String createSMSRequest(String smsType, Long benRegID, Integer stateID, String contact, String imrmmrID,
			Timestamp refDate, String createdBy,String informerName) {

		SmsRequestOBJ obj;
		ArrayList<SmsRequestOBJ> objList = new ArrayList<>();
		Date d = new Date((long) refDate.getTime());
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = f.format(d);
		int smsTypeID;
		smsTypeID = imrmmrRepository.getSMSTypeID(smsType);

		if (smsTypeID != 0) {
			obj = new SmsRequestOBJ();
			obj.setSmsTemplateID(imrmmrRepository.getSMSTemplateID(smsTypeID));
			obj.setBeneficiaryRegID(benRegID);
			obj.setStateID(stateID);
			obj.setSmsType(smsType);
			obj.setImrDate(dateStr);
			obj.setImrID(imrmmrID);
			obj.setNodalNumber(contact);
			obj.setSmsTemplateTypeID(smsTypeID);
			obj.setCreatedBy(createdBy);
			obj.setInformerName(informerName);
			objList.add(obj);
		}

		return new Gson().toJson(objList);
	}

	public String restTemplate(String request, String url, String Authorization) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("AUTHORIZATION", Authorization);

		HttpEntity<Object> requestOBJ = new HttpEntity<Object>(request, headers);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(url, HttpMethod.POST, requestOBJ, String.class).getBody();
	}

	@Override
	public List<M_supportServices> getsupportServices() throws Exception {
		List<M_supportServices> supportServicesList = new ArrayList<M_supportServices>();
		ArrayList<Objects[]> lists = imrmmrRepository.findsupportServices();

		for (Object[] objects : lists) {
			if (objects != null && objects.length > 0) {
				supportServicesList.add(new M_supportServices((Integer) objects[0], (String) objects[1]));
			}
		}
		return supportServicesList;
	}

	public List<M_facilities> getFacilities() throws Exception {
		List<M_facilities> facilitiesList = new ArrayList<M_facilities>();
		ArrayList<Objects[]> lists = imrmmrRepository.findfacilities();

		for (Object[] objects : lists) {
			if (objects != null && objects.length > 0) {
				facilitiesList.add(new M_facilities((Integer) objects[0], (String) objects[1]));
			}
		}
		return facilitiesList;
	}

	/*
	 * DU20091017 - added baseCommunity master values
	 */
	public List<m_iMRMMRBaseCommunity> getBaseCommunities() throws Exception {
		List<m_iMRMMRBaseCommunity> baseCommunitiesList = new ArrayList<m_iMRMMRBaseCommunity>();
		ArrayList<Objects[]> lists = imrmmrRepository.findBaseCommunities();

		for (Object[] objects : lists) {
			if (objects != null && objects.length > 0) {
				baseCommunitiesList.add(new m_iMRMMRBaseCommunity((Integer) objects[0], (String) objects[1]));
			}
		}
		return baseCommunitiesList;
	}

	/*
	 * DU20091017 - added transitType master values
	 */
	public List<m_iMRMMRTransitType> getTransitType() throws Exception {
		List<m_iMRMMRTransitType> transitTypeList = new ArrayList<m_iMRMMRTransitType>();
		ArrayList<Objects[]> lists = imrmmrRepository.findTransitType();

		for (Object[] objects : lists) {
			if (objects != null && objects.length > 0) {
				transitTypeList.add(new m_iMRMMRTransitType((Integer) objects[0], (String) objects[1]));
			}
		}
		return transitTypeList;
	}

	/*
	 * DU20091017 - added healthWorker master values
	 */
	public List<m_iMRMMRHealthworker> getHealthWorker() throws Exception {
		List<m_iMRMMRHealthworker> healthWorkerist = new ArrayList<m_iMRMMRHealthworker>();
		ArrayList<Objects[]> lists = imrmmrRepository.findHealthWorker();

		for (Object[] objects : lists) {
			if (objects != null && objects.length > 0) {
				healthWorkerist.add(new m_iMRMMRHealthworker((Integer) objects[0], (String) objects[1]));
			}
		}
		return healthWorkerist;
	}

	public String getWorklistRequests(Integer beneficiaryRegID, String phoneNo, String requestID) {
		List<IMRMMR> feedbackList = new ArrayList<IMRMMR>();
		List<Object[]> feedback = null;
		IMRMMR IMRMMRC;

		if (beneficiaryRegID != null)
			feedback = imrmmrRepository.findByBeneficiaryID(beneficiaryRegID);
		else if (phoneNo != null) {
			ArrayList<BigInteger> benRegIds = imrmmrRepository.findByPhoneNum(phoneNo);
			List<Integer> list1 = new ArrayList<>();
			for (BigInteger bi : benRegIds) {
				list1.add(bi.intValue());
			}
			if (list1 != null && list1.size() > 0)
				feedback = imrmmrRepository.findByBeneficiaryRegIDs(list1);
		} else if (requestID != null) {
			String ar[] = requestID.split("/");
			if (ar.length > 0)
				feedback = imrmmrRepository.findByRequestID(Long.parseLong(ar[ar.length - 1]));
		}

		if (feedback != null)
			for (Object[] objects : feedback) {
				if (objects != null && objects.length > 0) {
					IMRMMRC = new IMRMMR((Long) objects[0], (Integer) objects[1], (Integer) objects[2], (String) objects[3], (String) objects[4],
                            (String) objects[5], (String) objects[6], (Integer) objects[7], (String) objects[8], (Integer) objects[9], (String) objects[10],
                            (Timestamp) objects[11], (Integer) objects[12], (Long) objects[13], (String) objects[14], (String) objects[15],
                            (String) objects[16], (String) objects[17], (Integer) objects[18], (Integer) objects[19], (Integer) objects[20],
                            (String) objects[21], (Integer) objects[22], (Integer) objects[23], (String) objects[24], (String) objects[25],
                            (String) objects[26], (String) objects[27], (Integer) objects[28], (Integer) objects[29], (Integer) objects[30],
                            (String) objects[31], (String) objects[32], (String) objects[33], (String) objects[34], (String) objects[35],
                            (String) objects[36], (Boolean) objects[37], (String) objects[38],(String) objects[39], (Integer) objects[40],
                            (Integer) objects[41],(String) objects[42]);
					feedbackList.add(IMRMMRC);
				}
			}
		return new Gson().toJson(feedbackList);
	}

	@Override
	public String updateImrMmrRequest(IMRMMR complaint) throws Exception {
		IMRMMR resultSet = imrmmrRepository.searchByRequestID(complaint.getRequestID());
		if (resultSet != null) {
			if (complaint.getDeathConfirmedUI().equalsIgnoreCase("YES"))
				resultSet.setDeathConfirmed(true);
			else if (complaint.getDeathConfirmedUI().equalsIgnoreCase("NO"))
				resultSet.setDeathConfirmed(false);
			complaint = imrmmrRepository.save(resultSet);
			if (resultSet.getBenImrMmrID() == complaint.getBenImrMmrID()) {
				complaint = imrmmrRepository.save(resultSet);
				return "Data updated successfully";
			} else
				return null;
		} else
			throw new Exception("No record found for given request ID");
	}
}
