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
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.users.M_LoginSecurityQuestions;
import com.iemr.helpline104.data.users.M_Role;
import com.iemr.helpline104.data.users.M_User;
import com.iemr.helpline104.data.users.M_UserSecurityQMapping;
import com.iemr.helpline104.repository.users.IEMRUserLoginSecurityRepository;
import com.iemr.helpline104.repository.users.IEMRUserRepositoryCustom;
import com.iemr.helpline104.repository.users.IEMRUserSecurityQuesAnsRepository;
import com.iemr.helpline104.repository.users.RoleRepo;

@Service
public class IEMRAdminUserServiceImpl implements IEMRAdminUserService {

	private IEMRUserRepositoryCustom iEMRUserRepositoryCustom;
	private IEMRUserSecurityQuesAnsRepository iEMRUserSecurityQuesAnsRepository;

	@Autowired
	public void setIemrUserRepositoryImpl(IEMRUserRepositoryCustom iEMRUserRepositoryCustom) {
		this.iEMRUserRepositoryCustom = iEMRUserRepositoryCustom;
	}

	@Autowired
	public void setIemrUserRepositoryImpl(IEMRUserSecurityQuesAnsRepository iemrLoginSecurityQuesAnsRepository) {
		this.iEMRUserSecurityQuesAnsRepository = iemrLoginSecurityQuesAnsRepository;
	}

	@Override
	public List<M_User> userAuthenticate(String userName, String password) {
		return iEMRUserRepositoryCustom.findByUserNamePassword(userName, password);
	}

	@Override
	public M_User userExitsCheck(String userName) {
		return iEMRUserRepositoryCustom.findByUserName(userName);
	}

	@Override
	public List<M_UserSecurityQMapping> userSecurityQuestion(Long userId) {
		return iEMRUserRepositoryCustom.getUserSecurityQues(userId);
	}

	@Override
	public int setForgetPassword(Long userId, String password) {
		return iEMRUserRepositoryCustom.updateSetForgetPassword(userId, password);

	}

	@Override
	public M_User userWithOldPassExitsCheck(String userName, String password) {
		return iEMRUserRepositoryCustom.findUserForChangePass(userName, password);
	}

	@Override
	public int saveUserSecurityQuesAns(Iterable<M_UserSecurityQMapping> m_UserSecurityQMapping) {
		int x = 0;
		Iterable<M_UserSecurityQMapping> obj = iEMRUserSecurityQuesAnsRepository.saveAll(m_UserSecurityQMapping);
		for (M_UserSecurityQMapping usq : obj) {
			x = iEMRUserRepositoryCustom.updateSetUserStatusActive(usq.getUserID());
		}
		return x;

	}

	@Override
	public ArrayList<M_LoginSecurityQuestions> getAllLoginSecurityQuestions() {

		ArrayList<M_LoginSecurityQuestions> result = new ArrayList<M_LoginSecurityQuestions>();
		ArrayList<Objects[]> lists = iEMRUserLoginSecurityRepository.getAllLoginSecurityQuestions();
		for (Object[] objects : lists) {
			if (objects != null && objects.length > 0) {
				result.add(new M_LoginSecurityQuestions((Integer) objects[0], (String) objects[1]));
			}
		}
		return result;
	}

	/*
	 * IemrUserLoginSecurityRepository repository
	 */
	private IEMRUserLoginSecurityRepository iEMRUserLoginSecurityRepository;

	private RoleRepo roleRepo;

	@Autowired
	public void setIEMRUserLoginSecurityRepository(IEMRUserLoginSecurityRepository iEMRUserLoginSecurityRepository) {

		this.iEMRUserLoginSecurityRepository = iEMRUserLoginSecurityRepository;
	}

	public M_Role getrolewrapuptime(int roleID) {
		// TODO Auto-generated method stub
		return roleRepo.findById(roleID).get();
		
	}

}