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
package com.iemr.helpline104.service.snomedct;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.snomedct.SCTDescription;
import com.iemr.helpline104.repository.snomedct.SnomedRepository;

@Service
public class SnomedServiceImpl implements SnomedService {

	@Autowired
	private SnomedRepository snomedRepository;

	@Override
	public List<SCTDescription> findSnomedCTRecordFromTerm(String term) {

		List<SCTDescription> sctDesList = new ArrayList<SCTDescription>();

		List<Objects[]> records = snomedRepository.findSnomedCTRecordFromTerm(term);

		for (Object[] sctdescription : records) {
			if (sctdescription != null && sctdescription.length > 1) {
				
				SCTDescription sctDes = new SCTDescription( (String)sctdescription[0], (String)sctdescription[1]);
				sctDesList.add(sctDes);
			}
		}
		
		return sctDesList;

	}

}
