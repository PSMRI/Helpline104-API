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
package com.iemr.helpline104.repository.casesheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.casesheet.H104BenMedHistory;

@Repository
@RestResource(exported = false)
public interface H104BenHistoryRepository extends CrudRepository<H104BenMedHistory, Long> {
	
	/*
	@Query("select u from H104BenMedHistory u where u.beneficiaryRegID = :BeneficiaryRegID")
	public ArrayList<Objects[]> getBenHistory(@Param("BeneficiaryRegID") Long BeneficiaryRegID); */
	
	/*
	@Query("select u from H104BenMedHistory LEFT JOIN u.m_gender g where u.benCallID = :benCallID")
	public ArrayList<Objects[]> getHistoryByBenCallID(@Param("benCallID") Long benCallID); */
	
	@Query("select u from H104BenMedHistory u where u.beneficiaryRegID = :BeneficiaryRegID order by u.benHistoryID desc")
	public List<H104BenMedHistory> getBenHistory(@Param("BeneficiaryRegID") Long BeneficiaryRegID); 
	
	
	public List<H104BenMedHistory> findByBenCallID(@Param("benCallID") Long benCallID);
	
	@Query("select u from H104BenMedHistory u where u.beneficiaryRegID = :BeneficiaryRegID order by u.benHistoryID desc")
	List<H104BenMedHistory> getPresentBenHistory(@Param("BeneficiaryRegID") Long BeneficiaryRegID,Pageable pageable);
	            
}
