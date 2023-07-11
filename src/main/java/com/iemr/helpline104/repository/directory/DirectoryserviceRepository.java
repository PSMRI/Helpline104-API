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

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.directory.Directoryservice;

@Repository
@RestResource(exported = false)
public interface DirectoryserviceRepository extends CrudRepository<Directoryservice, Long>
{

	/*
	 * @Query("select d.directoryServiceID, i.institutionName, s.instituteDirectoryName, b.instituteSubDirectoryName, st.stateID, st.stateName, dt.districtID, dt.districtName, db.districtBranchID, db.villageName from Directoryservice d left join d.institute i join d.instituteDirectory s join d.instituteSubDirectory b join i.state st join i.district dt join i.districtbranchmapping db where d.deleted=false and d.beneficiaryRegID = :BeneficiaryRegID"
	 * ) public List<Objects[]> getBenSearchHistory(@Param("BeneficiaryRegID") Long BeneficiaryRegID);
	 */

	@Query("select d.directoryServiceID, i.institutionName, i.address, s.instituteDirectoryName, b.instituteSubDirectoryName from Directoryservice d left join d.institute i join d.instituteDirectory s join d.instituteSubDirectory b where d.deleted=false and d.beneficiaryRegID = :BeneficiaryRegID")
	public List<Objects[]> getBenSearchHistory(@Param("BeneficiaryRegID") Long BeneficiaryRegID);

	@Query("select d.directoryServiceID, i.institutionName, i.address, s.instituteDirectoryName, b.instituteSubDirectoryName from Directoryservice d left join d.institute i join d.instituteDirectory s join d.instituteSubDirectory b where d.deleted=false and d.benCallID = :benCallID")
	public List<Objects[]> getBenSearchHistoryByBenCallID(@Param("benCallID") Long benCallID);

}
