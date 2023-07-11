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
package com.iemr.helpline104.service.users;

import java.util.ArrayList;
import java.util.List;

import com.iemr.helpline104.data.users.M_LoginSecurityQuestions;
import com.iemr.helpline104.data.users.M_User;
import com.iemr.helpline104.data.users.M_UserSecurityQMapping;

public interface IEMRAdminUserService {
	List<M_User> userAuthenticate(String userName, String password);

	M_User userExitsCheck(String userName);

	List<M_UserSecurityQMapping> userSecurityQuestion(Long userId);

	int setForgetPassword(Long userId, String password);

	M_User userWithOldPassExitsCheck(String userName, String Password);

	int saveUserSecurityQuesAns(Iterable<M_UserSecurityQMapping> m_UserSecurityQMapping);

//	Iterable<M_UserSecurityQMapping> saveUserSecurityQuesAns(Iterable<M_UserSecurityQMapping> m_UserSecurityQMapping);
	
	/**
	 * 
	 * @return login security questions
	 */
	ArrayList<M_LoginSecurityQuestions> getAllLoginSecurityQuestions();

}
