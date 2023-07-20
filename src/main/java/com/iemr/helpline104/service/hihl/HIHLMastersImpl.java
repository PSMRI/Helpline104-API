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
package com.iemr.helpline104.service.hihl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.iemr.helpline104.data.hihl.T_hihlcocasesheet;
import com.iemr.helpline104.repository.hihl.M_104appetiteRepo;
import com.iemr.helpline104.repository.hihl.M_104bladderRepo;
import com.iemr.helpline104.repository.hihl.M_104bowelRepo;
import com.iemr.helpline104.repository.hihl.M_104courseRepo;
import com.iemr.helpline104.repository.hihl.M_104familyconditionRepo;
import com.iemr.helpline104.repository.hihl.M_104gettingwithfamilyRepo;
import com.iemr.helpline104.repository.hihl.M_104householdworkRepo;
import com.iemr.helpline104.repository.hihl.M_104hygieneselfcareRepo;
import com.iemr.helpline104.repository.hihl.M_104issuesatworkplaceRepo;
import com.iemr.helpline104.repository.hihl.M_104libidoRepo;
import com.iemr.helpline104.repository.hihl.M_104pastmedicalconditionRepo;
import com.iemr.helpline104.repository.hihl.M_104pastpsychiatricconditionRepo;
import com.iemr.helpline104.repository.hihl.M_104precipitatingfactorRepo;
import com.iemr.helpline104.repository.hihl.M_104progressRepo;
import com.iemr.helpline104.repository.hihl.M_104regularworokRepo;
import com.iemr.helpline104.repository.hihl.M_104relationshipRepo;
import com.iemr.helpline104.repository.hihl.M_104sleepRepo;
import com.iemr.helpline104.repository.hihl.M_104treatmenttypeRepo;
import com.iemr.helpline104.repository.hihl.PsychiatricChiefComplaintsRepo;
import com.iemr.helpline104.repository.hihl.T_hihlcocasesheetRepo;
import com.iemr.helpline104.utils.exception.IEMRException;
import com.iemr.helpline104.utils.mapper.InputMapper;

@Service
public class HIHLMastersImpl implements HIHLMasters {

	@Autowired
	private M_104appetiteRepo m_104appetiteRepo;
	@Autowired
	private M_104bladderRepo m_104bladderRepo;
	@Autowired
	private M_104bowelRepo m_104bowelRepo;
	@Autowired
	private M_104courseRepo m_104courseRepo;
	@Autowired
	private M_104familyconditionRepo m_104familyconditionRepo;
	@Autowired
	private M_104gettingwithfamilyRepo m_104gettingwithfamilyRepo;
	@Autowired
	private M_104householdworkRepo m_104householdworkRepo;
	@Autowired
	private M_104hygieneselfcareRepo m_104hygieneselfcareRepo;
	@Autowired
	private M_104issuesatworkplaceRepo m_104issuesatworkplaceRepo;
	@Autowired
	private M_104libidoRepo m_104libidoRepo;
	@Autowired
	private M_104pastmedicalconditionRepo m_104pastmedicalconditionRepo;
	@Autowired
	private M_104pastpsychiatricconditionRepo m_104pastpsychiatricconditionRepo;
	@Autowired
	private M_104precipitatingfactorRepo m_104precipitatingfactorRepo;
	@Autowired
	private M_104progressRepo m_104progressRepo;
	@Autowired
	private M_104regularworokRepo m_104regularworokRepo;
	@Autowired
	private M_104relationshipRepo m_104relationshipRepo;
	@Autowired
	private M_104sleepRepo m_104sleepRepo;
	@Autowired
	private M_104treatmenttypeRepo m_104treatmenttypeRepo;
	@Autowired
	private PsychiatricChiefComplaintsRepo psychiatricChiefComplaintsRepo;

	@Autowired
	private T_hihlcocasesheetRepo t_hihlcocasesheetRepo;

	public String getHihlMasters() {
		Map<String, Object> responseMasters = new HashMap<String, Object>();
		responseMasters.put("m_104appetite", m_104appetiteRepo.findByDeleted(false));
		responseMasters.put("m_104bladder", m_104bladderRepo.findByDeleted(false));

		responseMasters.put("m_104bowel", m_104bowelRepo.findByDeleted(false));
		responseMasters.put("m_104course", m_104courseRepo.findByDeleted(false));

		responseMasters.put("m_104familycondition", m_104familyconditionRepo.findByDeleted(false));
		responseMasters.put("m_104gettingwithfamily", m_104gettingwithfamilyRepo.findByDeleted(false));

		responseMasters.put("m_104householdwork", m_104householdworkRepo.findByDeleted(false));
		responseMasters.put("m_104hygieneselfcare", m_104hygieneselfcareRepo.findByDeleted(false));

		responseMasters.put("m_104issuesatworkplace", m_104issuesatworkplaceRepo.findByDeleted(false));
		responseMasters.put("m_104libido", m_104libidoRepo.findByDeleted(false));

		responseMasters.put("m_104pastmedicalcondition", m_104pastmedicalconditionRepo.findByDeleted(false));
		responseMasters.put("m_104pastpsychiatriccondition", m_104pastpsychiatricconditionRepo.findByDeleted(false));

		responseMasters.put("m_104precipitatingfactor", m_104precipitatingfactorRepo.findByDeleted(false));
		responseMasters.put("m_104progress", m_104progressRepo.findByDeleted(false));

		responseMasters.put("m_104regularworok", m_104regularworokRepo.findByDeleted(false));
		responseMasters.put("m_104relationship", m_104relationshipRepo.findByDeleted(false));

		responseMasters.put("m_104sleep", m_104sleepRepo.findByDeleted(false));
		responseMasters.put("m_104treatmenttype", m_104treatmenttypeRepo.findByDeleted(false));

		responseMasters.put("psychiatricChiefComplaints", psychiatricChiefComplaintsRepo.findByDeleted(false));

		return new Gson().toJson(responseMasters);
	}

	public String saveHihlCasesheet(String request) throws IEMRException {
		T_hihlcocasesheet t_hihlcocasesheet = InputMapper.gson().fromJson(request, T_hihlcocasesheet.class);

		JSONObject jsonobj = new JSONObject(request);
		t_hihlcocasesheet.setCocasesheet(jsonobj.toString());

		T_hihlcocasesheet temp = t_hihlcocasesheetRepo.save(t_hihlcocasesheet);
		return "data saved successfully with ID : " + temp.getId();
	}

	public String getHihlCasesheetHistoryInfo(Long benRegId) {
		List<JSONObject> responseList = new ArrayList<JSONObject>();
		List<T_hihlcocasesheet> resultSet = t_hihlcocasesheetRepo.getHihlCasesheets(benRegId);
		if (resultSet != null && resultSet.size() > 0) {
			for (T_hihlcocasesheet t_hihlcocasesheet : resultSet) {
				JSONObject obj = new JSONObject(t_hihlcocasesheet.getCocasesheet());
				responseList.add(obj);
			}

		}
		return responseList.toString();
	}

	public String getHihlCasesheetData(Long casesheetId) {
		T_hihlcocasesheet resultSet = t_hihlcocasesheetRepo.findById(casesheetId);
		return new Gson().toJson(resultSet);
	}

}
