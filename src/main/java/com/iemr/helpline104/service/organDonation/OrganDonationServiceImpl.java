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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.bloodRequest.RequestedBloodBank;
import com.iemr.helpline104.data.organDonation.M_DonatableOrgan;
import com.iemr.helpline104.data.organDonation.M_DonationType;
import com.iemr.helpline104.data.organDonation.OrganDonations;
import com.iemr.helpline104.data.organDonation.RequestedInstitution;
import com.iemr.helpline104.data.organDonation.T_OrganDonation;
import com.iemr.helpline104.repository.beneficiarycall.BenCallRepository;
import com.iemr.helpline104.repository.organDonation.OrganDonationRepository;
import com.iemr.helpline104.repository.organDonation.RequestedInstitutionRepository;

@Service
public class OrganDonationServiceImpl implements OrganDonationService
{

	@Autowired
	private OrganDonationRepository organDonationRepository;
	@Autowired
	private RequestedInstitutionRepository requestedInstitutionRepository;
	@Autowired
	private BenCallRepository benCallRepository;

	@Override
	public List<T_OrganDonation> getOrganDonationRequests(Long benificiaryRegID, Long benCallID, String requestID) throws Exception
	{
		List<T_OrganDonation> organDonationRequestList = new ArrayList<T_OrganDonation>();

		List<Objects[]> organDonationRequest = null;

		if (benificiaryRegID != null)
			organDonationRequest = organDonationRepository.getOrganDonationRequests(benificiaryRegID);
		else if (benCallID != null)
			organDonationRequest = organDonationRepository.getOrganDonationRequestsByBenCallID(benCallID);
		else if (requestID != null)
			organDonationRequest = organDonationRepository.getOrganDonationRequestsByRequestID(requestID);

		T_OrganDonation tOrganDonation;

		for (Object[] objects : organDonationRequest)
		{
			if (objects != null && objects.length > 0)
			{

				tOrganDonation =
						new T_OrganDonation(
								(Long) objects[0], (String) objects[1], (Long) objects[2], (String) objects[3], (Integer) objects[4],
								(Short) objects[5], (String) objects[6], (Integer) objects[7], (String) objects[8], (String) objects[9],
								(Integer) objects[10], (String) objects[11], (String) objects[12], (Integer) objects[13], (String) objects[14]);

				if (benCallID != null)
					tOrganDonation.setBenCall(benCallRepository.findByBenCallID(benCallID));

				tOrganDonation.setRequestedInstitution(requestedInstitutionRepository.findByOrganDonationIDAndDeleted((Long) objects[0], false));

				organDonationRequestList.add(tOrganDonation);
			}
		}
		return organDonationRequestList;
	}

	@Override
	public String save(OrganDonations organDonations) throws Exception
	{

		List<T_OrganDonation> organDonationList = new ArrayList<T_OrganDonation>();

		for (T_OrganDonation t_organDonation : organDonations.getT_organDonations())
		{

			t_organDonation = organDonationRepository.save(t_organDonation);

			String requestID = "OD/"
					+ t_organDonation.getDistrictID() + "/" + new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTimeInMillis()) + "/"
					+ t_organDonation.getOrganDonationID();
			t_organDonation.setRequestID(requestID);
			organDonationRepository.save(t_organDonation);

			organDonationList.add(t_organDonation);
		}
		return organDonationList.toString();
	}

	@Override
	public String update(T_OrganDonation organDonation) throws Exception
	{

		organDonationRepository.updateOrganDonationDetails(organDonation.getOrganDonationID(),organDonation.getDonationTypeID(),organDonation.getDonatableOrganID(),organDonation.getRemarks());

		return organDonation.toString();
	}

	@Override
	public String saveInstituteDetails(T_OrganDonation organDonation) throws Exception
	{

		List<RequestedInstitution> requestedInstitutions = organDonation.getRequestedInstitution();

		if (requestedInstitutions != null)
		{

			for (RequestedInstitution institution : requestedInstitutions)
			{
				institution.setOrganDonationID(organDonation.getOrganDonationID());
				institution.setCreatedBy(organDonation.getCreatedBy());
			}

			requestedInstitutionRepository.saveAll(requestedInstitutions);

		}

		return organDonation.toString();
	}

	@Override
	public List<M_DonationType> getDonationTypes() throws Exception
	{
		List<M_DonationType> organDonationTypesList = new ArrayList<M_DonationType>();
		List<Objects[]> organDonationTypes = organDonationRepository.getDonationTypes();

		for (Object[] objects : organDonationTypes)
		{
			if (objects != null && objects.length > 0)
			{
				organDonationTypesList.add(new M_DonationType((Integer) objects[0], (String) objects[1], (String) objects[2]));
			}
		}
		return organDonationTypesList;
	}

	@Override
	public List<M_DonatableOrgan> getDonatableOrgans() throws Exception
	{
		List<M_DonatableOrgan> donatableOrgansList = new ArrayList<M_DonatableOrgan>();
		List<Objects[]> donatableOrgans = organDonationRepository.getDonatableOrgans();

		for (Object[] objects : donatableOrgans)
		{
			if (objects != null && objects.length > 0)
			{

				donatableOrgansList.add(new M_DonatableOrgan((Integer) objects[0], (String) objects[1], (String) objects[2]));
			}
		}
		return donatableOrgansList;
	}

}
