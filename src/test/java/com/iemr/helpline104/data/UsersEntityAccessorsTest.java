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
package com.iemr.helpline104.data;

import org.junit.jupiter.api.Test;

import com.iemr.helpline104.data.users.M_LoginSecurityQuestions;
import com.iemr.helpline104.data.users.M_Role;
import com.iemr.helpline104.data.users.M_ServiceMaster;
import com.iemr.helpline104.data.users.M_User;
import com.iemr.helpline104.data.users.M_UserDemographics;
import com.iemr.helpline104.data.users.M_UserQualification;
import com.iemr.helpline104.data.users.M_UserSecurityQMapping;
import com.iemr.helpline104.data.users.M_UserServiceRoleMapping;
import com.iemr.helpline104.data.users.M_UserStateLanguageMapping;

/**
 * Set/get round trips and constructor drives for the Users entities.
 */
class UsersEntityAccessorsTest {

	@Test
	void everyAccessorRoundTripsAndEveryConstructorBuilds() {
		EntityAccessors.assertEntities(
				M_LoginSecurityQuestions.class,
				M_Role.class,
				M_ServiceMaster.class,
				M_User.class,
				M_UserDemographics.class,
				M_UserQualification.class,
				M_UserSecurityQMapping.class,
				M_UserServiceRoleMapping.class,
				M_UserStateLanguageMapping.class);
	}
}
