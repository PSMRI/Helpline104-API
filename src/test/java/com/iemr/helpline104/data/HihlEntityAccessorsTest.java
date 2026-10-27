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

import com.iemr.helpline104.data.hihl.M_104appetite;
import com.iemr.helpline104.data.hihl.M_104bladder;
import com.iemr.helpline104.data.hihl.M_104bowel;
import com.iemr.helpline104.data.hihl.M_104course;
import com.iemr.helpline104.data.hihl.M_104familycondition;
import com.iemr.helpline104.data.hihl.M_104gettingwithfamily;
import com.iemr.helpline104.data.hihl.M_104householdwork;
import com.iemr.helpline104.data.hihl.M_104hygieneselfcare;
import com.iemr.helpline104.data.hihl.M_104issuesatworkplace;
import com.iemr.helpline104.data.hihl.M_104libido;
import com.iemr.helpline104.data.hihl.M_104pastmedicalcondition;
import com.iemr.helpline104.data.hihl.M_104pastpsychiatriccondition;
import com.iemr.helpline104.data.hihl.M_104precipitatingfactor;
import com.iemr.helpline104.data.hihl.M_104progress;
import com.iemr.helpline104.data.hihl.M_104regularworok;
import com.iemr.helpline104.data.hihl.M_104relationship;
import com.iemr.helpline104.data.hihl.M_104sleep;
import com.iemr.helpline104.data.hihl.M_104treatmenttype;
import com.iemr.helpline104.data.hihl.PsychiatricChiefComplaints;
import com.iemr.helpline104.data.hihl.T_hihlcocasesheet;

/**
 * Set/get round trips and constructor drives for the Hihl entities.
 */
class HihlEntityAccessorsTest {

	@Test
	void everyAccessorRoundTripsAndEveryConstructorBuilds() {
		EntityAccessors.assertEntities(
				M_104appetite.class,
				M_104bladder.class,
				M_104bowel.class,
				M_104course.class,
				M_104familycondition.class,
				M_104gettingwithfamily.class,
				M_104householdwork.class,
				M_104hygieneselfcare.class,
				M_104issuesatworkplace.class,
				M_104libido.class,
				M_104pastmedicalcondition.class,
				M_104pastpsychiatriccondition.class,
				M_104precipitatingfactor.class,
				M_104progress.class,
				M_104regularworok.class,
				M_104relationship.class,
				M_104sleep.class,
				M_104treatmenttype.class,
				PsychiatricChiefComplaints.class,
				T_hihlcocasesheet.class);
	}
}
