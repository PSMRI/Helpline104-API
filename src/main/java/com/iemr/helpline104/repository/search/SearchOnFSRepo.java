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
package com.iemr.helpline104.repository.search;

import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.foodSafetyCopmlaint.T_FoodSafetyCopmlaint;


@Repository
@RestResource(exported = false)
public interface SearchOnFSRepo extends CrudRepository<T_FoodSafetyCopmlaint, Long> {

//	@Query("select f.fSComplaintID, f.requestID, f.beneficiaryRegID, f.typeOfRequest, f.isDiarrhea, f.isVomiting, f.isAbdominalPain, f.isChillsOrRigors, f.isGiddiness, f.isDehydration, "
//			+ "f.isRashes, f.fromWhen, f.historyOfDiet, f.isFoodConsumed, f.typeOfFood, f.foodConsumedFrom, f.associatedSymptoms, t.feedbackTypeID, t.feedbackTypeName, "
//			+ " f.remarks, f.createdDate, f.isSelf, f.districtID, d.districtName, f.districtBlockID, db.blockName, f.villageID, dbm.villageName "
//			+ "from T_FoodSafetyCopmlaint f "
//			+ " LEFT JOIN f.m_feedbackType t"
//			+ " LEFT JOIN f.district d"
//			+ " LEFT JOIN f.districtBlock db"
//			+ " LEFT JOIN f.districtBranchMapping dbm"
//			+ " LEFT JOIN BeneficiaryMapping ben ON f.beneficiaryRegID=ben.benRegID "
//			+ "LEFT JOIN BenContactMapping bc ON bc.benContactsID=ben.benContactsId "
//			+ " where f.deleted=false and bc.preferredPhoneNum = :mobileNumber order by f.fSComplaintID desc")
//	public List<Objects[]> getFoodSafetyComplaintsListByMobile(@Param("mobileNumber") String mobileNumber
//			)
//			throws Exception;
//
//	@Query("select f.fSComplaintID, f.requestID, f.beneficiaryRegID, f.typeOfRequest, f.isDiarrhea, f.isVomiting, f.isAbdominalPain, f.isChillsOrRigors, f.isGiddiness, f.isDehydration, "
//			+ "f.isRashes, f.fromWhen, f.historyOfDiet, f.isFoodConsumed, f.typeOfFood, f.foodConsumedFrom, f.associatedSymptoms, t.feedbackTypeID, t.feedbackTypeName, "
//			+ " f.remarks, f.createdDate, f.isSelf, f.districtID, d.districtName, f.districtBlockID, db.blockName, f.villageID, dbm.villageName "
//			+ "from T_FoodSafetyCopmlaint f "
//			+ " LEFT JOIN f.m_feedbackType t" 
//			+ " LEFT JOIN f.district d"
//			+ " LEFT JOIN f.districtBlock db"
//			+ " LEFT JOIN f.districtBranchMapping dbm"
//			+ " where  f.deleted=false and  f.fSComplaintID= :ID order by f.fSComplaintID desc")
//	public List<Objects[]> getFoodSafetyComplaintsListByGrievanceID(@Param("ID") String ID
//			)
//			throws Exception;


}
