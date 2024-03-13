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
package com.iemr.helpline104.repository.epidemicOutbreak;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.epidemicOutbreak.T_EpidemicOutbreak;

@Repository
@RestResource(exported = false)
public interface EpidemicOutbreakRepository extends CrudRepository<T_EpidemicOutbreak, Long> {

	@Query("select e.outbreakComplaintID, e.requestID, e.beneficiaryRegID, e.natureOfComplaint, e.totalPeopleAffected, "
			+ "e.affectedDistrictID, d.districtName, e.affectedDistrictBlockID, b.blockName, "
			+ "e.affectedVillageID, dbm.villageName, " + "e.remarks, e.createdDate from T_EpidemicOutbreak e "
			+ "LEFT JOIN e.m_district d " + "LEFT JOIN e.m_districtblock b " + "LEFT JOIN e.districtBranchMapping dbm "
			+ "where e.deleted=false and e.beneficiaryRegID=:beneficiaryRegID order by e.outbreakComplaintID desc")
	public List<Object[]> getEpidemicOutbreakComplaints(@Param("beneficiaryRegID") Long beneficiaryRegID)
			throws Exception;

	@Query("select e.outbreakComplaintID, e.requestID, e.beneficiaryRegID, e.natureOfComplaint, e.totalPeopleAffected, "
			+ "e.affectedDistrictID, d.districtName, e.affectedDistrictBlockID, b.blockName, "
			+ "e.affectedVillageID, dbm.villageName, " + "e.remarks, e.createdDate from T_EpidemicOutbreak e "
			+ "LEFT JOIN e.m_district d " + "LEFT JOIN e.m_districtblock b " + "LEFT JOIN e.districtBranchMapping dbm "
			+ "where e.deleted=false and e.benCallID=:benCallID order by e.outbreakComplaintID desc")
	public List<Object[]> getEpidemicOutbreakComplaintsByBenCallID(@Param("benCallID") Long benCallID)
			throws Exception;

	@Query("select e.outbreakComplaintID, e.requestID, e.beneficiaryRegID, e.natureOfComplaint, e.totalPeopleAffected, "
			+ "e.affectedDistrictID, d.districtName, e.affectedDistrictBlockID, b.blockName, "
			+ "e.affectedVillageID, dbm.villageName, " + "e.remarks, e.createdDate from T_EpidemicOutbreak e "
			+ "LEFT JOIN e.m_district d " + "LEFT JOIN e.m_districtblock b " + "LEFT JOIN e.districtBranchMapping dbm "
			+ "where e.deleted=false and e.requestID=:requestID order by e.outbreakComplaintID desc")
	public List<Object[]> getEpidemicOutbreakComplaintsByRequestID(@Param("requestID") String requestID)
			throws Exception;

	@Query("select e.outbreakComplaintID, e.requestID, e.beneficiaryRegID, e.natureOfComplaint, e.totalPeopleAffected, "
			+ "e.affectedDistrictID, d.districtName, e.affectedDistrictBlockID, b.blockName, "
			+ "e.affectedVillageID, dbm.villageName, " + "e.remarks, e.createdDate from T_EpidemicOutbreak e "
			+ "LEFT JOIN e.m_district d " + "LEFT JOIN e.m_districtblock b " + "LEFT JOIN e.districtBranchMapping dbm "
			+ "where e.deleted=false and e.outbreakComplaintID=:outbreakComplaintID ")
	public List<Object[]> getEpidemicOutbreakComplaintsByRequestIDNew(
			@Param("outbreakComplaintID") Long outbreakComplaintID) throws Exception;

	@Query(value = "SELECT im.BenRegId FROM "
			+ " db_identity.i_beneficiarycontacts ic INNER JOIN db_identity.i_beneficiarymapping im "
			+ " ON ic.VanSerialNo = im.BenContactsId AND ic.VanID = im.VanID "
			+ " WHERE ic.PreferredPhoneNum = :phoneNum", nativeQuery = true)
	public ArrayList<BigInteger> findByPhoneNum(@Param("phoneNum") String phoneNum);

	@Query("select e.outbreakComplaintID, e.requestID, e.beneficiaryRegID, e.natureOfComplaint, e.totalPeopleAffected, "
			+ "e.affectedDistrictID, d.districtName, e.affectedDistrictBlockID, b.blockName, "
			+ "e.affectedVillageID, dbm.villageName, " + "e.remarks, e.createdDate from T_EpidemicOutbreak e "
			+ "LEFT JOIN e.m_district d " + "LEFT JOIN e.m_districtblock b " + "LEFT JOIN e.districtBranchMapping dbm "
			+ "where e.deleted=false and e.beneficiaryRegID IN :ids ")
	public List<Object[]> findByBenRegIDs(@Param("ids") List<Long> ids);

	// shubham 29-09-2020
	// 29-09-2020 shubham
//		@Query("select e.outbreakComplaintID, e.requestID, e.beneficiaryRegID, e.natureOfComplaint, e.totalPeopleAffected, "
//				+ "e.affectedDistrictID, d.districtName, e.affectedDistrictBlockID, b.blockName, "
//				+ "e.affectedVillageID, dbm.villageName, "
//				+ "e.remarks, e.createdDate from T_EpidemicOutbreak e " + "LEFT JOIN e.m_district d "
//				+ "LEFT JOIN e.m_districtblock b "
//				+ "LEFT JOIN e.districtBranchMapping dbm "
//				+ "where e.deleted=false and e.requestID=:outbreakComplaintID order by e.outbreakComplaintID desc")
//		public List<Object[]> getEpidemicOutbreakComplaintsByID(@Param("outbreakComplaintID") Long outbreakComplaintID)
//				throws Exception;
//		@Query("select e.outbreakComplaintID, e.requestID, e.beneficiaryRegID, e.natureOfComplaint, e.totalPeopleAffected, "
//				+ "e.affectedDistrictID, d.districtName, e.affectedDistrictBlockID, b.blockName, "
//				+ "e.affectedVillageID, dbm.villageName, "
//				+ "e.remarks, e.createdDate from T_EpidemicOutbreak e " 
//				+ "LEFT JOIN e.m_district d "
//				+ "LEFT JOIN e.m_districtblock b "
//				+ "LEFT JOIN e.districtBranchMapping dbm "
//				+ " LEFT JOIN BeneficiaryMapping ben ON e.beneficiaryRegID=ben.benRegID "
//				+ "LEFT JOIN BenContactMapping bc ON bc.benContactsID=ben.benContactsId "
//				+ "where e.deleted=false and bc.preferredPhoneNum = :mobile order by e.outbreakComplaintID desc")
//		public List<Object[]> getEpidemicOutbreakComplaintsByMobile(@Param("mobile") String mobile)
//				throws Exception;

	@Query(" SELECT t FROM T_EpidemicOutbreak t WHERE t.requestID = :requestID ")
	public T_EpidemicOutbreak searchByRequestID(@Param("requestID") String requestID);
}
