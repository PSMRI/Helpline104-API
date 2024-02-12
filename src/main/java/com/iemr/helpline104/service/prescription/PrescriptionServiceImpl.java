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
package com.iemr.helpline104.service.prescription;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.controller.prescription.PrescriptionController;
import com.iemr.helpline104.data.foodSafetyCopmlaint.T_FoodSafetyCopmlaint;
import com.iemr.helpline104.data.healthCareWorkerType.M_HealthCareWorker;
import com.iemr.helpline104.data.organDonation.T_OrganDonation;
import com.iemr.helpline104.data.prescription.PrescribedDrug;
import com.iemr.helpline104.data.prescription.Prescription;
import com.iemr.helpline104.repository.prescription.PrescribedDrugRepository;
import com.iemr.helpline104.repository.prescription.PrescriptionRepository;

@Service
public class PrescriptionServiceImpl implements PrescriptionService{

	private Logger logger = LoggerFactory.getLogger(PrescriptionController.class);
	@Autowired(required = false)
	private PrescriptionRepository prescriptionRepository;
	
	@Autowired(required = false)
	private PrescribedDrugRepository prescribedDrugRepository;
	
	@Override
	public Prescription savePrescription(Prescription t_prescription) {
		Prescription prescription = prescriptionRepository.save(t_prescription);
		//prescription.prescriptionID	
		
		Timestamp startDate;		
		Timestamp lastDate; 
		
		List<PrescribedDrug> prescribedDrugs = t_prescription.getPrescribedDrugs();
				
		for(PrescribedDrug prescribedDrug: prescribedDrugs){
			prescribedDrug.setPrescriptionID(prescription.prescriptionID);
			Calendar cal = Calendar.getInstance();
			startDate = new Timestamp(cal.getTimeInMillis());
			cal.add(Calendar.DATE, prescribedDrug.getNoOfDays());
			lastDate = new Timestamp(cal.getTimeInMillis());
			prescribedDrug.setValidTill(lastDate);
			prescribedDrug = prescribedDrugRepository.save(prescribedDrug);
		}
		
		prescription.setPrescribedDrugs(prescribedDrugs);
		
		return prescription;
	}

	@Override
	public List<Prescription> getPrescription(Long beneficiaryRegID, Long prescriptionID) {
		/*
		List<T_Prescription> prescriptionList = new ArrayList<T_Prescription>();
		List<Objects[]> prescription = prescriptionRepository.getPrescription(beneficiaryRegID, prescriptionID);

		for (Object[] objects : prescription) {
			if (objects!=null && objects.length > 0) {
		
					
				prescriptionList.add(new T_Prescription((Long)objects[0], (Long)objects[1], (String)objects[2], (Integer)objects[3],(String)objects[4],
				(String)objects[5],(String)objects[6], (Integer)objects[7], (String)objects[8], (String)objects[9],(String)objects[10]));
			
			}
		}
		return prescriptionList;*/
		return null;
	}

	@Override
	public List<Prescription> getPrescriptionList(Long beneficiaryRegID,Pageable pageable) {
		
		List<Prescription> prescriptionList = new ArrayList<Prescription>();
		List<Objects[]> prescriptions = prescriptionRepository.getPrescriptionList(beneficiaryRegID,pageable);

		for (Object[] objects : prescriptions) {
			if (objects!=null && objects.length > 0) {
		
				Prescription prescription = new Prescription((Long)objects[0], (Long)objects[1], (Long)objects[2], (String)objects[3],(String)objects[4], (Date) objects[5]);
				
				List<Objects[]> drugs = prescribedDrugRepository.getPrescribedDrugs(prescription.getPrescriptionID());
				List<PrescribedDrug> drugsList = new ArrayList<PrescribedDrug>();
				
				for (Object[] drugObjects : drugs) {
					if (drugObjects!=null && drugObjects.length > 0) {						
						
						PrescribedDrug prescribedDrug = new PrescribedDrug((Long)drugObjects[0],(Integer)drugObjects[1], (String)drugObjects[2], (String)drugObjects[3], (String)drugObjects[4],(String)drugObjects[5],(String)drugObjects[6],(Integer)drugObjects[7],(String)drugObjects[8],(String)drugObjects[9],(Timestamp)drugObjects[10]);
						//PrescribedDrug prescribedDrug = new PrescribedDrug((String)objects[0], (String)objects[1], (String)objects[2], (String)objects[3],(String)objects[4],(Integer)objects[5],(String)objects[6],(String)objects[7]);
						drugsList.add(prescribedDrug);
					}
					}
				
				//logger.info(prescription.getPrescriptionID()+": " + drugs);
				prescription.setPrescribedDrugs(drugsList);
				prescriptionList.add(prescription);
			
			}
		}
		return prescriptionList; 
		
	}
	
	@Override
	public List<Prescription> getPrescriptionListByBenCallID(Long benCallID) {
		logger.info("get PrescriptionList By BenCallID " + benCallID);
		List<Prescription> prescriptionList = new ArrayList<Prescription>();
		List<Objects[]> prescriptions = prescriptionRepository.getPrescriptionListByBenCallID(benCallID);

		for (Object[] objects : prescriptions) {
			if (objects!=null && objects.length > 0) {
		
				Prescription prescription = new Prescription((Long)objects[0], (Long)objects[1], (Long)objects[2], (String)objects[3],(String)objects[4], (Date) objects[5]);
				
				List<Objects[]> drugs = prescribedDrugRepository.getPrescribedDrugs(prescription.getPrescriptionID());
				List<PrescribedDrug> drugsList = new ArrayList<PrescribedDrug>();
				
				for (Object[] drugObjects : drugs) {
					if (drugObjects!=null && drugObjects.length > 0) {						
						
						PrescribedDrug prescribedDrug = new PrescribedDrug((Long)drugObjects[0],(Integer)drugObjects[1], (String)drugObjects[2], (String)drugObjects[3], (String)drugObjects[4],(String)drugObjects[5],(String)drugObjects[6],(Integer)drugObjects[7],(String)drugObjects[8],(String)drugObjects[9],(Timestamp)drugObjects[10]);
						//PrescribedDrug prescribedDrug = new PrescribedDrug((String)objects[0], (String)objects[1], (String)objects[2], (String)objects[3],(String)objects[4],(Integer)objects[5],(String)objects[6],(String)objects[7]);
						drugsList.add(prescribedDrug);
					}
					}
				
				//logger.info(prescription.getPrescriptionID()+": " + drugs);
				prescription.setPrescribedDrugs(drugsList);
				prescriptionList.add(prescription);
			
			}
		}
		return prescriptionList; 
		
	}
	
	@Override
	public List<Prescription> getLatestValidPescription(Long beneficiaryRegID,Pageable pageable) {
		
		List<Prescription> prescriptionList = new ArrayList<Prescription>();
		List<Objects[]> prescriptions = prescriptionRepository.getLatestValidPescription(beneficiaryRegID,pageable);

		for (Object[] objects : prescriptions) {
			if (objects!=null && objects.length > 0) {
		
				Prescription prescription = new Prescription((Long)objects[0], (Long)objects[1], (Long)objects[2], (String)objects[3],(String)objects[4], (Date)objects[5]);
				
				List<Objects[]> drugs = prescribedDrugRepository.getPrescribedDrugs(prescription.getPrescriptionID());
				List<PrescribedDrug> drugsList = new ArrayList<PrescribedDrug>();
				PrescribedDrug prescribedDrug;
				
				Timestamp today = new Timestamp(Calendar.getInstance().getTimeInMillis());
				Timestamp validTill;
				for (Object[] drugObjects : drugs) {
					if (drugObjects!=null && drugObjects.length > 0) {						
						
						prescribedDrug = new PrescribedDrug((Long)drugObjects[0],(Integer)drugObjects[1], (String)drugObjects[2], (String)drugObjects[3], (String)drugObjects[4],(String)drugObjects[5],(String)drugObjects[6],(Integer)drugObjects[7],(String)drugObjects[8],(String)drugObjects[9],(Timestamp)drugObjects[10]);
						//PrescribedDrug prescribedDrug = new PrescribedDrug((String)objects[0], (String)objects[1], (String)objects[2], (String)objects[3],(String)objects[4],(Integer)objects[5],(String)objects[6],(String)objects[7]);
						validTill = prescribedDrug.getValidTill();
						if(validTill != null && validTill.after(today))
						drugsList.add(prescribedDrug);
					}
					}
				
				//logger.info(prescription.getPrescriptionID()+": " + drugs);
				if(drugsList.size()>0)
				{
				prescription.setPrescribedDrugs(drugsList);
				prescriptionList.add(prescription);
				}
			
			}
		}
		return prescriptionList; 
		
	}

	/*
	@Override
	public List<T_Prescription> getLatestValidPescription(Long beneficiaryRegID) {
		
		List<T_Prescription> prescriptionList = new ArrayList<T_Prescription>();
		List<Objects[]> prescriptions = prescriptionRepository.getLatestValidPescription(beneficiaryRegID,new PageRequest(0, 1));

		for (Object[] objects : prescriptions) {
			if (objects!=null && objects.length > 0) {
		
				
				prescriptionList.add(new T_Prescription((Long)objects[0], (Long)objects[1], (String)objects[2], (Integer)objects[3],(String)objects[4],
						(String)objects[5],(String)objects[6], (Integer)objects[7], (String)objects[8], (String)objects[9],(String)objects[10]));
			
			}
		}
		return prescriptionList; 
		
	}  */
	
	

}
