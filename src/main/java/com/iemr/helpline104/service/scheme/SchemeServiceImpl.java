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
package com.iemr.helpline104.service.scheme;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iemr.helpline104.data.scheme.T_Schemeservice;
import com.iemr.helpline104.repository.scheme.SchemeRepository;
@Service
public class SchemeServiceImpl implements SchemeService{

	@Autowired 
	private SchemeRepository schemeRepository;	
	
	
	@Override
	public List<T_Schemeservice> getSchemeSearchHistory(Long benificiaryRegID, Long benCallID)
	{

		List<T_Schemeservice> schemeServiceList = new ArrayList<T_Schemeservice>();
		

		if (benificiaryRegID != null)
			schemeServiceList = schemeRepository.findByBeneficiaryRegID(benificiaryRegID);
		else if (benCallID != null)
			schemeServiceList = schemeRepository.findByBenCallID(benCallID);
		
		return schemeServiceList;
	}


	@Override
	public String saveSchemeSearchHistory(T_Schemeservice[] schemeHistory) 
	{
		Iterable<T_Schemeservice> savedSchemeResponse = schemeRepository.save(Arrays.asList(schemeHistory));

		return savedSchemeResponse.toString();
	}

}
