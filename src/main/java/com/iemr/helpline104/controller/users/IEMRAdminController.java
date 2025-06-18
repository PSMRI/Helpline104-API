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
package com.iemr.helpline104.controller.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.iemr.helpline104.data.users.M_LoginSecurityQuestions;
import com.iemr.helpline104.data.users.M_Role;
import com.iemr.helpline104.data.users.M_User;
import com.iemr.helpline104.data.users.M_UserSecurityQMapping;
import com.iemr.helpline104.data.users.M_UserServiceRoleMapping;
import com.iemr.helpline104.service.users.IEMRAdminUserService;
import com.iemr.helpline104.service.users.IEMRAdminUserServiceImpl;
import com.iemr.helpline104.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping(value = "/user")
@RestController
public class IEMRAdminController {
	@Autowired
	private IEMRAdminUserService iemrAdminUserService;

	@Autowired
	public void setIemrAdminUserService(IEMRAdminUserServiceImpl iemrAdminUserService) {
		this.iemrAdminUserService = iemrAdminUserService;
	}

	@Operation(summary = "User authenticate")
	@PostMapping(value = { "/userAuthenticate" }, produces = { "application/json" })
	public String userAuthenticate(@RequestBody M_User m_User) {
		List<M_User> mUser = this.iemrAdminUserService.userAuthenticate(m_User.getUserName(), m_User.getPassword());

		Map<Object, Object> resMap = new HashMap();
		Multimap<String, String> serviceRoleMultiMap = ArrayListMultimap.create();
		Map<Object, Object> serviceRoleMap = null;
		List<Map<Object, Object>> serviceRoleList = new ArrayList();
		if (mUser.size() > 0) {
			String uName = ((M_User) mUser.get(0)).getFirstName() + " " + ((M_User) mUser.get(0)).getMiddleName() + " "
					+ ((M_User) mUser.get(0)).getLastName();
			resMap.put("userID", mUser.get(0).getUserID());
			resMap.put("isAuthenticated", Boolean.valueOf(true));
			resMap.put("userName", uName);
			resMap.put("Status", ((M_User) mUser.get(0)).getM_status().getStatus());
			M_User mu;
			for (Iterator localIterator1 = mUser.iterator(); localIterator1.hasNext();) {
				mu = (M_User) localIterator1.next();
				for (M_UserServiceRoleMapping m_UserServiceRoleMapping : mu.getM_UserServiceRoleMapping()) {
					serviceRoleMultiMap.put(m_UserServiceRoleMapping.getM_ServiceMaster().getServiceName(),
							m_UserServiceRoleMapping.getM_Role().getRoleName());
				}
			}
			Set<String> keySet = serviceRoleMultiMap.keySet();
			for (String s : keySet) {
				serviceRoleMap = new HashMap();
				serviceRoleMap.put("Service", s);
				serviceRoleMap.put("Role", serviceRoleMultiMap.get(s));

				serviceRoleList.add(serviceRoleMap);
			}
			resMap.put("Previlege", serviceRoleList);
		} else {
			resMap.put("isAuthenticated", Boolean.valueOf(false));
		}
		System.out.println(new Gson().toJson(resMap));
		return new Gson().toJson(resMap);
	}

	@Operation(summary = "Forget password")
	@PostMapping(value = { "/forgetPassword" }, produces = { "application/json" })
	public String forgetPassword(@RequestBody M_User m_User) {
		M_User mUser = this.iemrAdminUserService.userExitsCheck(m_User.getUserName());

		List<Map<String, String>> quesAnsList = new ArrayList();
		Map<String, String> quesAnsMap;
		Map<Object, Object> resMap = new HashMap();
		if (mUser != null) {
			List<M_UserSecurityQMapping> mUserSecQuesMapping = this.iemrAdminUserService
					.userSecurityQuestion(mUser.getUserID());
			if (mUserSecQuesMapping != null) {
				for (M_UserSecurityQMapping element : mUserSecQuesMapping) {
					quesAnsMap = new HashMap();
					quesAnsMap.put("question", element.getM_LoginSecurityQuestions().getQuestion());
					quesAnsMap.put("answer", element.getAnswers());

					quesAnsList.add(quesAnsMap);
				}
				resMap.put("SecurityQuesAns", quesAnsList);
			}
			return new Gson().toJson(resMap);
		}
		resMap.put("SecurityQuesAns", "user Not Found");
		return new Gson().toJson(resMap);
	}

	@Operation(summary = "Set forget password")
	@PostMapping(value = { "/setForgetPassword" }, produces = { "application/json" })
	public String setPassword(@RequestBody M_User m_user) {
		int noOfRowModified = 0;

		M_User mUser = this.iemrAdminUserService.userExitsCheck(m_user.getUserName());
		String setStatus;
		if (mUser != null) {
			noOfRowModified = this.iemrAdminUserService.setForgetPassword(mUser.getUserID(), m_user.getPassword());
			if (noOfRowModified > 0) {
				setStatus = "Password Changed";
			} else {
				setStatus = "Something Wrong..!!!";
			}
			System.out.println(noOfRowModified);
		} else {
			setStatus = "User Doesn't Exists..!!!";
		}
		return new Gson().toJson(setStatus);
	}

	@Operation(summary = "Change password")
	@PostMapping(value = { "/changePassword" }, produces = { "application/json" })
	public String changePassword(@RequestBody M_User m_User) {
		int noOfRowUpdated = 0;

		M_User mUser = this.iemrAdminUserService.userWithOldPassExitsCheck(m_User.getUserName(),
				m_User.getPassword());
		String changeReqResult;
		if (mUser != null) {
			noOfRowUpdated = this.iemrAdminUserService.setForgetPassword(mUser.getUserID(),
					m_User.getNewPassword());
			if (noOfRowUpdated > 0) {
				changeReqResult = "Password SuccessFully Change";
			} else {
				changeReqResult = "Something WentWrong.....Please Contact Administrator..!!!";
			}
		} else {
			changeReqResult = "Wrong Old Password....Please give correct old password..!!!";
		}
		return new Gson().toJson(changeReqResult);
	}

	@Operation(summary = "Save user security question answers")
	@PostMapping(value = "/saveUserSecurityQuesAns", produces = "application/json")
	public String saveUserSecurityQuesAns(@RequestBody Iterable<M_UserSecurityQMapping> m_UserSecurityQMapping) {
		int responseData = 0;

		Map<String, String> resMap = new HashMap<String, String>();

		responseData = iemrAdminUserService.saveUserSecurityQuesAns(m_UserSecurityQMapping);

		if (responseData > 0)
			resMap.put("status", "Changed");
		else
			resMap.put("status", "notChanged");

		return new Gson().toJson(resMap);
	}

	/**
	 * 
	 * @return security qtns
	 */
	@Operation(summary = "Get security quetions")
	@GetMapping("/getsecurityquetions")
	public String getSecurityts() {
		ArrayList<M_LoginSecurityQuestions> test = iemrAdminUserService.getAllLoginSecurityQuestions();
		return test.toString();
	}

	@Operation(summary = "Get role wrap up time")
	@GetMapping(value = "/role/{roleID}", produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getrolewrapuptime(@PathVariable("roleID") Integer roleID) {

		OutputResponse response = new OutputResponse();
		try {
			M_Role test = iemrAdminUserService.getrolewrapuptime(roleID);
			if (test == null) {
				throw new Exception("RoleID Not Found");
			}
			response.setResponse(test.toString());
		} catch (Exception e) {
			response.setError(e);
		}
		return response.toString();
	}
}
