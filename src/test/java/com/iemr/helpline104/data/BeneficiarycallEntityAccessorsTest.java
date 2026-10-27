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

import com.iemr.helpline104.data.beneficiarycall.BenCall;
import com.iemr.helpline104.data.beneficiarycall.BenCallDisconnectedData;
import com.iemr.helpline104.data.beneficiarycall.BenCallServicesMappingHistory;
import com.iemr.helpline104.data.beneficiarycall.BeneficiaryCall;
import com.iemr.helpline104.data.beneficiarycall.CallType;
import com.iemr.helpline104.data.beneficiarycall.M_subservice;
import com.iemr.helpline104.data.beneficiarycall.OutboundCallRequest;
import com.iemr.helpline104.data.beneficiarycall.ServiceProvided;
import com.iemr.helpline104.data.beneficiarycall.ServicesMaster104;

/**
 * Set/get round trips and constructor drives for the Beneficiarycall entities.
 */
class BeneficiarycallEntityAccessorsTest {

	@Test
	void everyAccessorRoundTripsAndEveryConstructorBuilds() {
		EntityAccessors.assertEntities(
				BenCall.class,
				BenCallDisconnectedData.class,
				BenCallServicesMappingHistory.class,
				BeneficiaryCall.class,
				CallType.class,
				M_subservice.class,
				OutboundCallRequest.class,
				ServiceProvided.class,
				ServicesMaster104.class);
	}
}
