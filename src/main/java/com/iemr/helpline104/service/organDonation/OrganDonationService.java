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
package com.iemr.helpline104.service.organDonation;

import java.util.List;


import com.iemr.helpline104.data.organDonation.M_DonatableOrgan;
import com.iemr.helpline104.data.organDonation.M_DonationType;
import com.iemr.helpline104.data.organDonation.OrganDonations;
import com.iemr.helpline104.data.organDonation.T_OrganDonation;


public interface OrganDonationService {
	
	List<T_OrganDonation> getOrganDonationRequests(Long benificiaryRegID, Long benCallID, String requestID) throws Exception;
	
	public String save(OrganDonations organDonations) throws Exception;
	
	public String update(T_OrganDonation organDonation) throws Exception;
	
	public String saveInstituteDetails(T_OrganDonation organDonation) throws Exception;

	List<M_DonationType> getDonationTypes() throws Exception;
	
	List<M_DonatableOrgan> getDonatableOrgans() throws Exception;
}
