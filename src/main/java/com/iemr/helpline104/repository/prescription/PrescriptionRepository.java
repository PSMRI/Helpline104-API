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
package com.iemr.helpline104.repository.prescription;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.helpline104.data.prescription.Prescription;

@Repository
@RestResource(exported = false)
public interface PrescriptionRepository extends CrudRepository<Prescription, Long>{
	
	/*
	@Query("select p.beneficiaryRegID,p.benCallID,p.diagnosisProvided,p.drugID,d.drugName,p.dosage,p.drugUsage,p.noOfDays, "
			+ "p.timeToConsume,p.sideEffects,p.remarks "
			+ " from Prescription p "
			+ " RIGHT JOIN p.m_drugMaster d "
			+ "where d.deleted = false and p.beneficiaryRegID=:beneficiaryRegID and p.prescriptionID=:prescriptionID")
	public List<Objects[]> getPrescription(@Param("beneficiaryRegID") Long beneficiaryRegID, @Param("prescriptionID") Long prescriptionID); 
	
	@Query("select p.beneficiaryRegID,p.benCallID,p.diagnosisProvided,p.drugID,d.drugName,p.dosage,p.drugUsage,p.noOfDays, "
			+ "p.timeToConsume,p.sideEffects,p.remarks "
			+ " from Prescription p "
			+ " RIGHT JOIN p.m_drugMaster d "
			+ "where d.deleted = false and p.beneficiaryRegID=:beneficiaryRegID") */
	
	@Query("select p.prescriptionID,p.beneficiaryRegID,p.benCallID,p.diagnosisProvided,p.remarks,p.createdDate "			
			+ " from Prescription p "			
			+ "where p.deleted = false and p.beneficiaryRegID=:beneficiaryRegID")
	public List<Objects[]> getPrescriptionList(@Param("beneficiaryRegID") Long beneficiaryRegID,Pageable pageable);
	
	@Query("select p.prescriptionID,p.beneficiaryRegID,p.benCallID,p.diagnosisProvided,p.remarks,p.createdDate "			
			+ " from Prescription p "			
			+ "where p.deleted = false and p.benCallID=:benCallID")
	public List<Objects[]> getPrescriptionListByBenCallID(@Param("benCallID") Long benCallID);
	
	/*
	@Query("select p.beneficiaryRegID,p.benCallID,p.diagnosisProvided,p.drugID,d.drugName,p.dosage,p.drugUsage,p.noOfDays, "
			+ "p.timeToConsume,p.sideEffects,p.remarks "
			+ " from Prescription p "
			+ " RIGHT JOIN p.m_drugMaster d "
			+ "where d.deleted = false and p.beneficiaryRegID=:beneficiaryRegID and DATE(p.createdDate) between adddate(now(),-5) and now() order by p.prescriptionID DESC")
	public List<Objects[]> getLatestValidPescription(@Param("beneficiaryRegID") Long beneficiaryRegID,Pageable pageable); */
	
	@Query("select p.prescriptionID,p.beneficiaryRegID,p.benCallID,p.diagnosisProvided,p.remarks,p.createdDate "			
			+ " from Prescription p "			
			+ "where p.deleted = false and p.beneficiaryRegID=:beneficiaryRegID and DATE(p.createdDate) between adddate(now(),-30) and now() order by p.prescriptionID DESC")
	public List<Objects[]> getLatestValidPescription(@Param("beneficiaryRegID") Long beneficiaryRegID,Pageable pageable);
	
	
	
}
