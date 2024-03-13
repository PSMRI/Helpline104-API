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
package com.iemr.helpline104.service.disease;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.helpline104.data.disease.Disease;
import com.iemr.helpline104.repository.disease.DiseaseRepository;
import com.iemr.helpline104.utils.exception.IEMRException;
import com.iemr.helpline104.utils.mapper.InputMapper;

@Service
public class DiseaseServiceImpl implements DiseaseService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	DiseaseRepository diseaseRepository;
	
	@Override
	public String saveDisease(String request) {
		logger.info("saveDisease - Start");
		Disease[] disease = null;
		try {
			disease = InputMapper.gson().fromJson(request, Disease[].class);
		} catch (IEMRException e) {
			e.printStackTrace();
		}
		
		List<Disease> list = (List<Disease>) diseaseRepository.saveAll(Arrays.asList(disease));
		
		logger.info("saveDisease - End");
		return list.toString();
	}
	
	@Override
	public String deleteDisease(String request) {
		logger.info("deactivated - Start");
		Disease disease = null;
		try {
			disease = InputMapper.gson().fromJson(request, Disease.class);
		} catch (IEMRException e) {
			e.printStackTrace();
		}
		int count=0;
		if(disease!=null && disease.getDiseasesummaryID()!=null && disease.getDeleted()!=null)
		 count =diseaseRepository.deleteDisease(disease.getDiseasesummaryID(), disease.getDeleted());
		
		logger.info("deactivated - End");

		if(count==1 && disease.getDeleted()==true)
		{
		return "deactivated successfully";
		}

		return "activated successfully";
	}
	
	@Override
	public String getDisease(String request) throws IEMRException {
		logger.info("getDisease - Start");
		Disease disease = InputMapper.gson().fromJson(request, Disease.class);
		Integer totalCount = diseaseRepository.getDiseaseCount();
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Disease> criteriaQuery = criteriaBuilder.createQuery(Disease.class);
		Root<Disease> root = criteriaQuery.from(Disease.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Disease> typedQuery = entityManager.createQuery(criteriaQuery);
		
		if (disease.getPageNo() != null && disease.getPageSize() != null)
		{
			typedQuery.setMaxResults(disease.getPageSize())
			.setFirstResult((disease.getPageNo() - 1) * disease.getPageSize());
		}
		
		List<Disease> list=typedQuery.getResultList();
		
		Integer totalPages= getPageCount(totalCount, disease.getPageSize());
		
		Map<String, Object> responseMap = new HashMap<>();
		
		responseMap.put("DiseaseList", list);
		responseMap.put("totalPages", totalPages);
		
		logger.info("getDisease - End");
		return responseMap.toString();
	}
	
	private int getPageCount(Integer totalCount, Integer pageSize) {
		if (pageSize > 0) {
			if (totalCount % pageSize == 0)
				return (int) (totalCount / pageSize);
			else
				return ((int) (totalCount / pageSize) + 1);
		} else
			return 0;
	}
	
	@Override
	public String updateDisease(String request) throws IEMRException {
		
		logger.info("updateDisease - Start");
		Disease disease = InputMapper.gson().fromJson(request, Disease.class);
		int count = diseaseRepository.updateDisease(disease.getDiseaseName(),disease.getSummary(),disease.getCouldbedangerous(),disease.getCauses(),disease.getDos_donts(),
				disease.getSymptoms_Signs(),disease.getMedicaladvice(),disease.getRiskfactors(),disease.getTreatment(),disease.getSelf_care()
				,disease.getInvestigations(),disease.getDiseasesummaryID());
		logger.info("updateDisease - End");
		if(count==1)
		{
			return "Updated successfully";
		}
		return "Update failed";
	}
	
	@Override
	public String getAvailableDiseases() throws IEMRException {
		
		logger.info("getAvailableDiseases - Start");
		List<Object[]> reports= diseaseRepository.getAvailableDiseases();
		List<Disease> list = new ArrayList<Disease>();
		
		for (Object[] objects : reports)
		{
			if (objects != null && objects.length > 0)
			{
				list.add(new Disease((Integer) objects[0], (String) objects[1]));
			}
		}
		
		logger.info("getAvailableDiseases - End");
		return list.toString();
	}
	
	@Override
	public String getDiseasesByID(String request) throws IEMRException {
		logger.info("getDiseasesByID - Start");
		Disease req = InputMapper.gson().fromJson(request, Disease.class);
		Disease disease= diseaseRepository.getDiseasesByID(req.getDiseasesummaryID());
		
		logger.info("getDiseasesByID - End");
		return disease.toString();
	}
}
