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
package com.iemr.helpline104.repository.directory;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.directory.Directoryservice;

@Service
@Repository
@RestResource(exported = false)
public interface DirectoryserviceRepository extends CrudRepository<Directoryservice, Long> {
	@Autowired(required = true)

	@Query("select d.directoryServiceID, i.institutionName, i.address, s.instituteDirectoryName, b.instituteSubDirectoryName from Directoryservice d left join d.institute i join d.instituteDirectory s join d.instituteSubDirectory b where d.deleted=false and d.beneficiaryRegID = :BeneficiaryRegID")
	public List<Object[]> getBenSearchHistory(@Param("BeneficiaryRegID") Long BeneficiaryRegID);

	@Query("select d.directoryServiceID, i.institutionName, i.address, s.instituteDirectoryName, b.instituteSubDirectoryName from Directoryservice d left join d.institute i join d.instituteDirectory s join d.instituteSubDirectory b where d.deleted=false and d.benCallID = :benCallID")
	public List<Object[]> getBenSearchHistoryByBenCallID(@Param("benCallID") Long benCallID);

}
