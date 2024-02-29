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



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.helpline104.data.userbeneficiarydata.M_Status;
import com.iemr.helpline104.data.users.M_LoginSecurityQuestions;
import com.iemr.helpline104.data.users.M_Role;
import com.iemr.helpline104.data.users.M_ServiceMaster;
import com.iemr.helpline104.data.users.M_User;
import com.iemr.helpline104.data.users.M_UserSecurityQMapping;
import com.iemr.helpline104.data.users.M_UserServiceRoleMapping;
import com.iemr.helpline104.service.users.IEMRAdminUserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class IEMRAdminControllerTest {

	@InjectMocks
	IEMRAdminController iEMRAdminController;
	
	@Mock
	IEMRAdminUserServiceImpl iemrAdminUserServiceImpl;
	
	@Test
	public void userAuthenticate()
	{
		List<M_User> mUserList=Lists.newArrayList();
		M_User m_User=Mockito.mock(M_User.class, Mockito.CALLS_REAL_METHODS);
		m_User.setFirstName("first");
		m_User.setMiddleName("middle");
		m_User.setLastName("last");
		M_Status m_status=Mockito.mock(M_Status.class, Mockito.CALLS_REAL_METHODS);
		m_status.setStatusID(109);
		m_User.setM_status(m_status);
		
		M_ServiceMaster m_ServiceMaster=Mockito.mock(M_ServiceMaster.class, Mockito.CALLS_REAL_METHODS);
		M_Role m_Role=Mockito.mock(M_Role.class, Mockito.CALLS_REAL_METHODS);
		M_UserServiceRoleMapping m_UserServiceRoleMapping =Mockito.mock(M_UserServiceRoleMapping.class, Mockito.CALLS_REAL_METHODS);
		m_UserServiceRoleMapping.setM_ServiceMaster(m_ServiceMaster);
		m_UserServiceRoleMapping.setM_Role(m_Role);
		m_UserServiceRoleMapping.setServiceID(106);
		Set<M_UserServiceRoleMapping> set=new HashSet<M_UserServiceRoleMapping>();
		set.add(m_UserServiceRoleMapping);
		m_User.setM_UserServiceRoleMapping(set);
		mUserList.add(m_User);
		 doReturn(mUserList).when(iemrAdminUserServiceImpl).userAuthenticate(Mockito.anyString(),Mockito.anyString());
		 String response=iEMRAdminController.userAuthenticate(m_User);
		 assertTrue(response.contains("\"userName\":\"first middle last\""));
		
		
	}
	
	@Test
	public void userAuthenticate1()
	{
		List<M_User> mUserList=Lists.newArrayList();
		M_User m_User=Mockito.mock(M_User.class, Mockito.CALLS_REAL_METHODS);
		m_User.setFirstName("first");
		m_User.setMiddleName("middle");
		m_User.setLastName("last");
		mUserList.add(m_User);
		 String response=iEMRAdminController.userAuthenticate(m_User);
		 assertFalse(response.contains("\"userName\":\"first middle last\""));
		 assertTrue(response.contains("\"isAuthenticated\":false"));
		
		
	}
	
	@Test
	public void forgetPasswordTest()
	{
		M_User m_User=Mockito.mock(M_User.class, Mockito.CALLS_REAL_METHODS);
		m_User.setFirstName("first");
		m_User.setMiddleName("middle");
		m_User.setLastName("last");
		 String response=iEMRAdminController.forgetPassword(m_User);
		 assertFalse(response.contains("\"userName\":\"first middle last\""));
		 assertTrue(response.contains("\"SecurityQuesAns\":\"user Not Found\""));
	}
	
	@Test
	public void forgetPasswordTest1()
	{
		M_User m_User=Mockito.mock(M_User.class, Mockito.CALLS_REAL_METHODS);
		m_User.setFirstName("first");
		m_User.setMiddleName("middle");
		m_User.setLastName("last");
		doReturn(m_User).when(iemrAdminUserServiceImpl).userExitsCheck(Mockito.anyString());
		 String response=iEMRAdminController.forgetPassword(m_User);
		 assertFalse(response.contains("\"userName\":\"first middle last\""));
	}
	
	@Test
	public void forgetPasswordTest2()
	{
		M_User m_User=Mockito.mock(M_User.class, Mockito.CALLS_REAL_METHODS);
		m_User.setFirstName("first");
		m_User.setMiddleName("middle");
		m_User.setLastName("last");
		doReturn(m_User).when(iemrAdminUserServiceImpl).userExitsCheck(Mockito.anyString());
		
		List<M_UserSecurityQMapping> list=Lists.newArrayList();
		M_UserSecurityQMapping m_UserSecurityQMapping=Mockito.mock(M_UserSecurityQMapping.class, Mockito.CALLS_REAL_METHODS);
		M_LoginSecurityQuestions m_LoginSecurityQuestions=Mockito.mock(M_LoginSecurityQuestions.class, Mockito.CALLS_REAL_METHODS);
		m_LoginSecurityQuestions.setQuestion("ques");
		m_UserSecurityQMapping.setAnswers("ans");
		m_UserSecurityQMapping.setM_LoginSecurityQuestions(m_LoginSecurityQuestions);
		list.add(m_UserSecurityQMapping);
		doReturn(list).when(iemrAdminUserServiceImpl).userSecurityQuestion(Mockito.anyLong());
		 String response=iEMRAdminController.forgetPassword(m_User);
		 assertFalse(response.contains("\"userName\":\"first middle last\""));
		 assertTrue(response.contains("\"question\":\"ques\""));
	}
	@Test
	public void setPasswordTest()
	{
		M_User m_User=Mockito.mock(M_User.class, Mockito.CALLS_REAL_METHODS);
		m_User.setFirstName("first");
		m_User.setMiddleName("middle");
		m_User.setLastName("last");
		doReturn(m_User).when(iemrAdminUserServiceImpl).userExitsCheck(Mockito.anyString());
		
		 String response=iEMRAdminController.setPassword(m_User);
		 assertTrue(response.contains("Something Wrong..!!!"));
	}
	@Test
	public void setPasswordTest1()
	{
		M_User m_User=Mockito.mock(M_User.class, Mockito.CALLS_REAL_METHODS);
		
		 String response=iEMRAdminController.setPassword(m_User);
		 assertTrue(response.contains("User Doesn\\u0027t Exists..!!!"));
	}
	@Test
	public void setPasswordTest2()
	{
		M_User m_User=Mockito.mock(M_User.class, Mockito.CALLS_REAL_METHODS);
		m_User.setFirstName("first");
		m_User.setMiddleName("middle");
		m_User.setLastName("last");
		doReturn(m_User).when(iemrAdminUserServiceImpl).userExitsCheck(Mockito.anyString());
		doReturn(1).when(iemrAdminUserServiceImpl).setForgetPassword(Mockito.anyLong(), Mockito.anyString());
		 String response=iEMRAdminController.setPassword(m_User);
		 assertTrue(response.contains("Password Changed"));
	}
	@Test
	public void changePasswordTest()
	{
		M_User m_User=Mockito.mock(M_User.class, Mockito.CALLS_REAL_METHODS);
		m_User.setFirstName("first");
		m_User.setMiddleName("middle");
		m_User.setLastName("last");
		doReturn(m_User).when(iemrAdminUserServiceImpl).userWithOldPassExitsCheck(Mockito.anyString(),Mockito.anyString());
		 String response=iEMRAdminController.changePassword(m_User);
		 assertTrue(response.contains("Something WentWrong"));
	}
	
	@Test
	public void changePasswordTest1()
	{
		M_User m_User=Mockito.mock(M_User.class, Mockito.CALLS_REAL_METHODS);
		m_User.setFirstName("first");
		 String response=iEMRAdminController.changePassword(m_User);
		 assertTrue(response.contains("Wrong Old Password"));
	}
	@Test
	public void changePasswordTest2()
	{
		M_User m_User=Mockito.mock(M_User.class, Mockito.CALLS_REAL_METHODS);
		m_User.setFirstName("first");
		m_User.setMiddleName("middle");
		m_User.setLastName("last");
		doReturn(m_User).when(iemrAdminUserServiceImpl).userWithOldPassExitsCheck(Mockito.anyString(),Mockito.anyString());
		doReturn(1).when(iemrAdminUserServiceImpl).setForgetPassword(Mockito.anyLong(), Mockito.anyString());
		 String response=iEMRAdminController.changePassword(m_User);
		 assertTrue(response.contains("Password SuccessFully Change"));
	}
	
	@Test
	public void saveUserSecurityQuesAnsTest()
	{
		Iterable<M_UserSecurityQMapping> m_UserSecurityQMapping = null;
		doReturn(1).when(iemrAdminUserServiceImpl).saveUserSecurityQuesAns(Mockito.any());
		String response=iEMRAdminController.saveUserSecurityQuesAns(m_UserSecurityQMapping);
		 assertTrue(response.contains("Changed"));
	}
	@Test
	public void saveUserSecurityQuesAnsTest1()
	{
		Iterable<M_UserSecurityQMapping> m_UserSecurityQMapping = null;
		String response=iEMRAdminController.saveUserSecurityQuesAns(m_UserSecurityQMapping);
		 assertTrue(response.contains("notChanged"));
	}
	
	@Test
	public void getSecuritytsTest()
	{
		ArrayList<M_LoginSecurityQuestions> list=Lists.newArrayList();
		M_LoginSecurityQuestions m_LoginSecurityQuestions=Mockito.mock(M_LoginSecurityQuestions.class, Mockito.CALLS_REAL_METHODS);
		m_LoginSecurityQuestions.setQuestionID(101);
		list.add(m_LoginSecurityQuestions);
		//doReturn(list).when(iemrAdminUserServiceImpl).getAllLoginSecurityQuestions();
		String response=iEMRAdminController.getSecurityts();
		assertTrue(response.contains(""));
	}
}
