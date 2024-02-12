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
package com.iemr.helpline104.service.userbeneficiarydata;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.location.States;
import com.iemr.helpline104.data.userbeneficiarydata.M_PhoneType;
import com.iemr.helpline104.repository.userbeneficiarydata.PhoneTypeRepository;

@Service
public class PhoneTypeServiceImpl implements PhoneTypeService{

	@Autowired(required = false)
	private PhoneTypeRepository phoneTypeRepository;
	@Override
	public List<M_PhoneType> getActivePhoneTypes() {
		
		List<M_PhoneType> phoneTypesList = new ArrayList<M_PhoneType>();
		List<Objects[]> phoneTypes = phoneTypeRepository.findAcivePhoneTypes();
		
		for(Object[] phoneType:phoneTypes){
			if(null!=phoneType && phoneType.length>0){
				phoneTypesList.add(new M_PhoneType((Short)phoneType[0], (String)phoneType[1]));
			}
		}
		return phoneTypesList;
	}

	
}
