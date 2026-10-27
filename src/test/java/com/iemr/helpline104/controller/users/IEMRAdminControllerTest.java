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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.helpline104.data.userbeneficiarydata.M_Status;
import com.iemr.helpline104.data.users.M_LoginSecurityQuestions;
import com.iemr.helpline104.data.users.M_Role;
import com.iemr.helpline104.data.users.M_ServiceMaster;
import com.iemr.helpline104.data.users.M_User;
import com.iemr.helpline104.data.users.M_UserSecurityQMapping;
import com.iemr.helpline104.data.users.M_UserServiceRoleMapping;
import com.iemr.helpline104.service.users.IEMRAdminUserService;

/**
 * Authentication answers with the agent's name, status and the service/role
 * pairs they hold. Password recovery walks the security questions, and both
 * password changes report what the update touched.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class IEMRAdminControllerTest {

	@Mock
	private IEMRAdminUserService iemrAdminUserService;

	@InjectMocks
	private IEMRAdminController controller;

	private static M_User credentials() {
		M_User user = new M_User();
		user.setUserName("agent");
		user.setPassword("secret");
		user.setNewPassword("newsecret");
		return user;
	}

	/** A stored agent holding one service/role pair. */
	private static M_User storedAgent() {
		M_User user = new M_User();
		user.setUserID(12L);
		user.setUserName("agent");
		user.setFirstName("Asha");
		user.setMiddleName("K");
		user.setLastName("Rao");
		user.setM_status(new M_Status(1, "Active"));

		M_ServiceMaster service = mock(M_ServiceMaster.class);
		when(service.getServiceName()).thenReturn("104");
		M_Role role = mock(M_Role.class);
		when(role.getRoleName()).thenReturn("MO");
		M_UserServiceRoleMapping mapping = mock(M_UserServiceRoleMapping.class);
		when(mapping.getM_ServiceMaster()).thenReturn(service);
		when(mapping.getM_Role()).thenReturn(role);

		Set<M_UserServiceRoleMapping> mappings = new HashSet<>();
		mappings.add(mapping);
		user.setM_UserServiceRoleMapping(mappings);
		return user;
	}

	@Test
	void userAuthenticateAnswersWithTheAgentsPrivileges() {
		M_User agent = storedAgent();
		when(iemrAdminUserService.userAuthenticate("agent", "secret"))
				.thenReturn(Collections.singletonList(agent));

		String response = controller.userAuthenticate(credentials());

		assertTrue(response.contains("\"isAuthenticated\":true"));
		assertTrue(response.contains("Asha K Rao"));
		assertTrue(response.contains("104"));
		assertTrue(response.contains("MO"));
	}

	@Test
	void userAuthenticateRejectsUnknownCredentials() {
		when(iemrAdminUserService.userAuthenticate(anyString(), anyString())).thenReturn(Collections.emptyList());

		assertTrue(controller.userAuthenticate(credentials()).contains("\"isAuthenticated\":false"));
	}

	@Test
	void forgetPasswordAnswersWithTheStoredSecurityQuestions() {
		M_User agent = storedAgent();
		M_UserSecurityQMapping answer = new M_UserSecurityQMapping(1L, 12L, "1",
				new M_LoginSecurityQuestions(1, "Your first school?"), "Springfield", "9999999999", Boolean.FALSE,
				"agent", null, null, null);
		when(iemrAdminUserService.userExitsCheck("agent")).thenReturn(agent);
		when(iemrAdminUserService.userSecurityQuestion(12L)).thenReturn(Collections.singletonList(answer));

		String response = controller.forgetPassword(credentials());

		assertTrue(response.contains("Your first school?"));
		assertTrue(response.contains("Springfield"));
	}

	@Test
	void forgetPasswordAnswersWithoutQuestionsWhenNoneAreStored() {
		M_User agent = storedAgent();
		when(iemrAdminUserService.userExitsCheck("agent")).thenReturn(agent);
		when(iemrAdminUserService.userSecurityQuestion(12L)).thenReturn(null);

		assertTrue(controller.forgetPassword(credentials()).contains("{}"));
	}

	@Test
	void forgetPasswordReportsAnUnknownUser() {
		when(iemrAdminUserService.userExitsCheck("agent")).thenReturn(null);

		assertTrue(controller.forgetPassword(credentials()).contains("user Not Found"));
	}

	@Test
	void setPasswordReportsAChangedPassword() {
		M_User agent = storedAgent();
		when(iemrAdminUserService.userExitsCheck("agent")).thenReturn(agent);
		when(iemrAdminUserService.setForgetPassword(12L, "secret")).thenReturn(1);

		assertTrue(controller.setPassword(credentials()).contains("Password Changed"));
	}

	@Test
	void setPasswordReportsAnUpdateThatTouchedNothing() {
		M_User agent = storedAgent();
		when(iemrAdminUserService.userExitsCheck("agent")).thenReturn(agent);
		when(iemrAdminUserService.setForgetPassword(anyLong(), anyString())).thenReturn(0);

		assertTrue(controller.setPassword(credentials()).contains("Something Wrong"));
	}

	@Test
	void setPasswordReportsAnUnknownUser() {
		when(iemrAdminUserService.userExitsCheck("agent")).thenReturn(null);

		assertTrue(controller.setPassword(credentials()).contains("Exists"));
	}

	@Test
	void changePasswordReportsAChangedPassword() {
		M_User agent = storedAgent();
		when(iemrAdminUserService.userWithOldPassExitsCheck("agent", "secret")).thenReturn(agent);
		when(iemrAdminUserService.setForgetPassword(12L, "newsecret")).thenReturn(1);

		assertTrue(controller.changePassword(credentials()).contains("Password SuccessFully Change"));
	}

	@Test
	void changePasswordReportsAnUpdateThatTouchedNothing() {
		M_User agent = storedAgent();
		when(iemrAdminUserService.userWithOldPassExitsCheck("agent", "secret")).thenReturn(agent);
		when(iemrAdminUserService.setForgetPassword(anyLong(), anyString())).thenReturn(0);

		assertTrue(controller.changePassword(credentials()).contains("Something WentWrong"));
	}

	@Test
	void changePasswordRejectsAWrongOldPassword() {
		when(iemrAdminUserService.userWithOldPassExitsCheck(anyString(), anyString())).thenReturn(null);

		assertTrue(controller.changePassword(credentials()).contains("Wrong Old Password"));
	}

	@Test
	void saveUserSecurityQuesAnsReportsTheStoredAnswers() {
		when(iemrAdminUserService.saveUserSecurityQuesAns(any())).thenReturn(1);

		assertTrue(controller.saveUserSecurityQuesAns(Collections.emptyList()).contains("Changed"));
	}

	@Test
	void saveUserSecurityQuesAnsReportsAnswersThatWereNotStored() {
		when(iemrAdminUserService.saveUserSecurityQuesAns(any())).thenReturn(0);

		assertTrue(controller.saveUserSecurityQuesAns(Collections.emptyList()).contains("notChanged"));
	}

	@Test
	void getSecuritytsAnswersWithTheSecurityQuestionMaster() {
		when(iemrAdminUserService.getAllLoginSecurityQuestions()).thenReturn(new java.util.ArrayList<>(
				Collections.singletonList(new M_LoginSecurityQuestions(1, "Your first school?"))));

		assertTrue(controller.getSecurityts().contains("Your first school?"));
	}

	@Test
	void getrolewrapuptimeAnswersWithTheRole() {
		M_Role role = new M_Role(3, "MO", "Medical Officer", Boolean.FALSE, "agent", null, null, null, null);
		when(iemrAdminUserService.getrolewrapuptime(3)).thenReturn(role);

		assertTrue(controller.getrolewrapuptime(3).contains("\"statusCode\":200"));
	}

	@Test
	void getrolewrapuptimeReportsAnUnknownRole() {
		when(iemrAdminUserService.getrolewrapuptime(3)).thenReturn(null);

		assertTrue(controller.getrolewrapuptime(3).contains("RoleID Not Found"));
	}
}
