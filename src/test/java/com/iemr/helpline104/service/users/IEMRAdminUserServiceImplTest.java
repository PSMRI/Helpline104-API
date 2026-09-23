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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.users.M_LoginSecurityQuestions;
import com.iemr.helpline104.data.users.M_Role;
import com.iemr.helpline104.data.users.M_User;
import com.iemr.helpline104.data.users.M_UserSecurityQMapping;
import com.iemr.helpline104.repository.users.IEMRUserLoginSecurityRepository;
import com.iemr.helpline104.repository.users.IEMRUserRepositoryCustom;
import com.iemr.helpline104.repository.users.IEMRUserSecurityQuesAnsRepository;
import com.iemr.helpline104.repository.users.RoleRepo;

/**
 * The admin user service is a thin reader over the user repositories: it
 * authenticates, answers the security questions, and activates a user once
 * their security answers are stored.
 */
@ExtendWith(MockitoExtension.class)
class IEMRAdminUserServiceImplTest {

	@Mock
	private IEMRUserRepositoryCustom userRepository;

	@Mock
	private IEMRUserSecurityQuesAnsRepository securityQuesAnsRepository;

	@Mock
	private IEMRUserLoginSecurityRepository loginSecurityRepository;

	@Mock
	private RoleRepo roleRepo;

	private IEMRAdminUserServiceImpl adminUserService;

	@BeforeEach
	void setUp() {
		adminUserService = new IEMRAdminUserServiceImpl();
		adminUserService.setIemrUserRepositoryImpl(userRepository);
		adminUserService.setIemrUserRepositoryImpl(securityQuesAnsRepository);
		adminUserService.setIEMRUserLoginSecurityRepository(loginSecurityRepository);
		adminUserService.setRoleRepo(roleRepo);
	}

	@Test
	void userAuthenticateReadsTheMatchingUsers() {
		List<M_User> users = Collections.singletonList(new M_User());
		when(userRepository.findByUserNamePassword("agent", "secret")).thenReturn(users);

		assertEquals(users, adminUserService.userAuthenticate("agent", "secret"));
	}

	@Test
	void userExitsCheckReadsTheUserByName() {
		M_User user = new M_User();
		when(userRepository.findByUserName("agent")).thenReturn(user);

		assertSame(user, adminUserService.userExitsCheck("agent"));
	}

	@Test
	void userSecurityQuestionReadsTheStoredQuestions() {
		List<M_UserSecurityQMapping> questions = Collections.singletonList(new M_UserSecurityQMapping(1L, 12L, "1",
				null, "Springfield", "9999999999", Boolean.FALSE, "agent", null, null, null));
		when(userRepository.getUserSecurityQues(12L)).thenReturn(questions);

		assertEquals(questions, adminUserService.userSecurityQuestion(12L));
	}

	@Test
	void setForgetPasswordReportsTheRowsItTouched() {
		when(userRepository.updateSetForgetPassword(12L, "secret")).thenReturn(1);

		assertEquals(1, adminUserService.setForgetPassword(12L, "secret"));
	}

	@Test
	void userWithOldPassExitsCheckReadsTheUserBehindTheOldPassword() {
		M_User user = new M_User();
		when(userRepository.findUserForChangePass("agent", "secret")).thenReturn(user);

		assertSame(user, adminUserService.userWithOldPassExitsCheck("agent", "secret"));
	}

	@Test
	void saveUserSecurityQuesAnsActivatesEveryUserItStored() {
		M_UserSecurityQMapping answer = new M_UserSecurityQMapping(1L, 12L, "1", null, "Springfield",
				"9999999999", Boolean.FALSE, "agent", null, null, null);
		when(securityQuesAnsRepository.saveAll(Collections.singletonList(answer)))
				.thenReturn(Collections.singletonList(answer));
		when(userRepository.updateSetUserStatusActive(12L)).thenReturn(1);

		assertEquals(1, adminUserService.saveUserSecurityQuesAns(Collections.singletonList(answer)));
	}

	@Test
	void saveUserSecurityQuesAnsReportsNothingWhenNoAnswersWereStored() {
		when(securityQuesAnsRepository.saveAll(Collections.emptyList())).thenReturn(Collections.emptyList());

		assertEquals(0, adminUserService.saveUserSecurityQuesAns(Collections.emptyList()));
	}

	@Test
	void getAllLoginSecurityQuestionsProjectsEveryReadableRow() {
		when(loginSecurityRepository.getAllLoginSecurityQuestions()).thenReturn(new ArrayList<>(
				Arrays.asList(null, new Object[0], new Object[] { 1, "Your first school?" })));

		ArrayList<M_LoginSecurityQuestions> questions = adminUserService.getAllLoginSecurityQuestions();

		assertEquals(1, questions.size());
	}

	@Test
	void getrolewrapuptimeReadsTheRole() {
		M_Role role = new M_Role(3, "MO", "Medical Officer", Boolean.FALSE, "agent", null, null, null, null);
		when(roleRepo.findById(3)).thenReturn(Optional.of(role));

		assertSame(role, adminUserService.getrolewrapuptime(3));
	}

	@Test
	void getrolewrapuptimeIsEmptyForAnUnknownRole() {
		when(roleRepo.findById(3)).thenReturn(Optional.empty());

		assertNull(adminUserService.getrolewrapuptime(3));
	}
}
