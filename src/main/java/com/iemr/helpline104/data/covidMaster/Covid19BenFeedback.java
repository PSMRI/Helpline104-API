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
package com.iemr.helpline104.data.covidMaster;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "t_104covid19")
public class Covid19BenFeedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "COVID19_104ID", insertable = false, updatable = false)
	private Long COVID19_104ID;
	
	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;
	@Expose
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;
	@Expose
	@Column(name = "beneficiaryID")
	private Long beneficiaryID;
	@Expose
	@Column(name = "GendenderID")
	private Integer genderID;
	@Expose
	@Column(name = "callTypeID")
	private Integer callTypeID;
	@Expose
	@Column(name = "Age")
	private Integer age;
	@Expose
	@Column(name = "CategoryID")
	private Integer categoryID;
	@Expose
	@Column(name = "SubCategoryID")
	private Integer subCategoryID;
	
	@Expose
	@Column(name = "categoryname")
	private String categoryName;
	@Expose
	@Column(name = "subcategoryname")
	private String subCategoryName;
	
	@Expose
	@Column(name = "TestForID")
	private String ID;
	@Expose
	@Column(name = "TestForName")
	private String Value;
	@Expose
	@Column(name = "Travel_14days")
	private Boolean travelledLast14DaysUI;
	@Expose
	@Column(name = "Travel_type")
	private String travelTypes_db;
	@Expose
	@Column(name = "ModeOfTravel")
	private String modeOfTravel;
	
	@Expose
	@Column(name = "modeOfTravelInternational")
	private String modeOfTravelInternational;
	
	@Expose
	@Column(name = "FromstateID")
	private Integer fromstateID;
	@Expose
	@Column(name = "FromcityID")
	private Integer fromcityID;
	@Expose
	@Column(name = "FromSubDistrictID")
	private Integer fromSubDistrict;
	@Expose
	@Column(name = "TostateID")
	private Integer tostateID;
	@Expose
	@Column(name = "TocityID")
	private Integer tocityID;
	@Expose
	@Column(name = "TosubDistrict")
	private Integer tosubDistrict;
	@Expose
	@Column(name = "InterCountryID_from")
	private Integer interFromCountry;
	@Expose
	@Column(name = "InterCityID_from")
	private Integer interFromcity;
	
	@Expose
	@Column(name = "InterCountryID_to")
	private Integer interToCountry;
	@Expose
	@Column(name = "InterCityID_to")
	private Integer interTocity;
	
	@Expose
	@Column(name = "Symptoms")
	private String symptoms_db;
	
	@Expose
	@Column(name = "HealthCondition")
	private String healthCondition_db;
	
	@Expose
	@Column(name = "LaboratoryConfirmed")
	private Boolean laboratoryConfirmedUI;
	
	@Expose
	@Column(name = "LargeGathering")
	private Boolean largeGatheringUI;
	
	@Expose
	@Column(name = "PublicExposedPlaces")
	private Boolean publicExposedPlacesUI;
	
	@Expose
	@Column(name = "FamliyPublicExposedPlaces")
	private Boolean famliyPublicExposedPlacesUI;
	
	
	@Expose
	@Column(name = "Symptoms11Select")
	private String symptoms11Select_db;
	@Expose
	@Column(name = "HowLongHaveFever")
	private Integer howLongHaveFever;
	@Expose
	@Column(name = "HighestRecordedFever")
	private Integer highestRecordedFever;
	@Expose
	@Column(name = "FeverPattern")
	private Integer feverPattern;
	
	@Expose
	@Column(name = "howLongHaveFeverValue")
	private String howLongHaveFeverValue;
	@Expose
	@Column(name = "highestRecordedFeverValue")
	private String highestRecordedFeverValue;
	@Expose
	@Column(name = "feverPatternValue")
	private String feverPatternValue;
	
	@Expose
	@Column(name = "RiskOfCovid19")
	private String riskOfCovid19;
	
	@Expose
	@Column(name = "isPregnant")
	private String isPregnant;
	
	@Expose
	@Column(name = "Deleted", insertable = false, updatable = false)
	private Boolean deleted;
	@Expose
	@Column(name = "Processed", insertable = false, updatable = true)
	private String processed;
	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;
	@Expose
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Timestamp createdDate;
	@Expose
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Expose
	@Column(name = "LastModDate")
	private Timestamp lastModDate;
	@Expose
	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;
	
	
	
	@Expose
	@Transient
	private Map<String, String> forWhomThisTest;
	
	
	@Expose
	@Transient
	private String[] symptoms;
	
	@Expose
	@Transient
	private String[] healthConditions;
	
	@Expose
	@Transient
	private String[] symptoms11Selected;
	
	@Expose
	@Transient
	private String[] travelType;
	
	@Expose
	@Transient
	private String travelledLast14Days;
	
	@Expose
	@Transient
	private String laboratoryConfirmed;
	
	@Expose
	@Transient
	private String largeGathering;
	
	@Expose
	@Transient
	private String publicExposedPlaces;
	
	@Expose
	@Transient
	private String famliyPublicExposedPlaces;
	
	
	
	

	
	
	

	public Long getCOVID19_104ID() {
		return COVID19_104ID;
	}

	public void setCOVID19_104ID(Long cOVID19_104ID) {
		COVID19_104ID = cOVID19_104ID;
	}
	
	

	public Long getBenCallID() {
		return benCallID;
	}

	public void setBenCallID(Long benCallID) {
		this.benCallID = benCallID;
	}

	public Long getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	public void setBeneficiaryRegID(Long beneficiaryRegID) {
		this.beneficiaryRegID = beneficiaryRegID;
	}

	public Long getBeneficiaryID() {
		return beneficiaryID;
	}

	public void setBeneficiaryID(Long beneficiaryID) {
		this.beneficiaryID = beneficiaryID;
	}

	public Integer getGenderID() {
		return genderID;
	}

	public void setGenderID(Integer genderID) {
		this.genderID = genderID;
	}

	public Integer getCallTypeID() {
		return callTypeID;
	}

	public void setCallTypeID(Integer callTypeID) {
		this.callTypeID = callTypeID;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public Integer getSubCategoryID() {
		return subCategoryID;
	}

	public void setSubCategoryID(Integer subCategoryID) {
		this.subCategoryID = subCategoryID;
	}
	
	

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
    
	

	

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	public String getModeOfTravel() {
		return modeOfTravel;
	}

	public void setModeOfTravel(String modeOfTravel) {
		this.modeOfTravel = modeOfTravel;
	}
	
	

	public String getModeOfTravelInternational() {
		return modeOfTravelInternational;
	}

	public void setModeOfTravelInternational(String modeOfTravelInternational) {
		this.modeOfTravelInternational = modeOfTravelInternational;
	}

	public Integer getFromstateID() {
		return fromstateID;
	}

	public void setFromstateID(Integer fromstateID) {
		this.fromstateID = fromstateID;
	}

	public Integer getFromcityID() {
		return fromcityID;
	}

	public void setFromcityID(Integer fromcityID) {
		this.fromcityID = fromcityID;
	}

	public Integer getFromSubDistrict() {
		return fromSubDistrict;
	}

	public void setFromSubDistrict(Integer fromSubDistrict) {
		this.fromSubDistrict = fromSubDistrict;
	}

	public Integer getTostateID() {
		return tostateID;
	}

	public void setTostateID(Integer tostateID) {
		this.tostateID = tostateID;
	}

	public Integer getTocityID() {
		return tocityID;
	}

	public void setTocityID(Integer tocityID) {
		this.tocityID = tocityID;
	}

	public Integer getTosubDistrict() {
		return tosubDistrict;
	}

	public void setTosubDistrict(Integer tosubDistrict) {
		this.tosubDistrict = tosubDistrict;
	}

	public Integer getInterFromCountry() {
		return interFromCountry;
	}

	public void setInterFromCountry(Integer interFromCountry) {
		this.interFromCountry = interFromCountry;
	}

	public Integer getInterFromcity() {
		return interFromcity;
	}

	public void setInterFromcity(Integer interFromcity) {
		this.interFromcity = interFromcity;
	}

	public Integer getInterToCountry() {
		return interToCountry;
	}

	public void setInterToCountry(Integer interToCountry) {
		this.interToCountry = interToCountry;
	}

	public Integer getInterTocity() {
		return interTocity;
	}

	public void setInterTocity(Integer interTocity) {
		this.interTocity = interTocity;
	}

	public String getHealthCondition_db() {
		return healthCondition_db;
	}

	public void setHealthCondition_db(String healthCondition_db) {
		this.healthCondition_db = healthCondition_db;
	}

	

	

	public String getSymptoms11Select_db() {
		return symptoms11Select_db;
	}

	public void setSymptoms11Select_db(String symptoms11Select_db) {
		this.symptoms11Select_db = symptoms11Select_db;
	}

	public Integer getHowLongHaveFever() {
		return howLongHaveFever;
	}

	public void setHowLongHaveFever(Integer howLongHaveFever) {
		this.howLongHaveFever = howLongHaveFever;
	}

	public Integer getHighestRecordedFever() {
		return highestRecordedFever;
	}

	public void setHighestRecordedFever(Integer highestRecordedFever) {
		this.highestRecordedFever = highestRecordedFever;
	}

	public Integer getFeverPattern() {
		return feverPattern;
	}

	public void setFeverPattern(Integer feverPattern) {
		this.feverPattern = feverPattern;
	}

	public String getRiskOfCovid19() {
		return riskOfCovid19;
	}

	public void setRiskOfCovid19(String riskOfCovid19) {
		this.riskOfCovid19 = riskOfCovid19;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getLastModDate() {
		return lastModDate;
	}

	public void setLastModDate(Timestamp lastModDate) {
		this.lastModDate = lastModDate;
	}

	public String getSymptoms_db() {
		return symptoms_db;
	}

	public void setSymptoms_db(String symptoms_db) {
		this.symptoms_db = symptoms_db;
	}

	public String[] getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String[] symptoms) {
		this.symptoms = symptoms;
	}

	public String[] getHealthConditions() {
		return healthConditions;
	}

	public void setHealthConditions(String[] healthConditions) {
		this.healthConditions = healthConditions;
	}

	public String[] getSymptoms11Selected() {
		return symptoms11Selected;
	}

	public void setSymptoms11Selected(String[] symptoms11Selected) {
		this.symptoms11Selected = symptoms11Selected;
	}

	public Integer getProviderServiceMapID() {
		return providerServiceMapID;
	}

	public void setProviderServiceMapID(Integer providerServiceMapID) {
		this.providerServiceMapID = providerServiceMapID;
	}

	public Boolean getTravelledLast14DaysUI() {
		return travelledLast14DaysUI;
	}

	public void setTravelledLast14DaysUI(Boolean travelledLast14DaysUI) {
		this.travelledLast14DaysUI = travelledLast14DaysUI;
	}

	public Boolean getLaboratoryConfirmedUI() {
		return laboratoryConfirmedUI;
	}

	public void setLaboratoryConfirmedUI(Boolean laboratoryConfirmedUI) {
		this.laboratoryConfirmedUI = laboratoryConfirmedUI;
	}

	public Boolean getLargeGatheringUI() {
		return largeGatheringUI;
	}

	public void setLargeGatheringUI(Boolean largeGatheringUI) {
		this.largeGatheringUI = largeGatheringUI;
	}

	public Boolean getPublicExposedPlacesUI() {
		return publicExposedPlacesUI;
	}

	public void setPublicExposedPlacesUI(Boolean publicExposedPlacesUI) {
		this.publicExposedPlacesUI = publicExposedPlacesUI;
	}

	public Boolean getFamliyPublicExposedPlacesUI() {
		return famliyPublicExposedPlacesUI;
	}

	public void setFamliyPublicExposedPlacesUI(Boolean famliyPublicExposedPlacesUI) {
		this.famliyPublicExposedPlacesUI = famliyPublicExposedPlacesUI;
	}

	public String getTravelledLast14Days() {
		return travelledLast14Days;
	}

	public void setTravelledLast14Days(String travelledLast14Days) {
		this.travelledLast14Days = travelledLast14Days;
	}

	public String getLaboratoryConfirmed() {
		return laboratoryConfirmed;
	}

	public void setLaboratoryConfirmed(String laboratoryConfirmed) {
		this.laboratoryConfirmed = laboratoryConfirmed;
	}

	public String getLargeGathering() {
		return largeGathering;
	}

	public void setLargeGathering(String largeGathering) {
		this.largeGathering = largeGathering;
	}

	public String getPublicExposedPlaces() {
		return publicExposedPlaces;
	}

	public void setPublicExposedPlaces(String publicExposedPlaces) {
		this.publicExposedPlaces = publicExposedPlaces;
	}

	public String getFamliyPublicExposedPlaces() {
		return famliyPublicExposedPlaces;
	}

	public void setFamliyPublicExposedPlaces(String famliyPublicExposedPlaces) {
		this.famliyPublicExposedPlaces = famliyPublicExposedPlaces;
	}

	public String getTravelTypes_db() {
		return travelTypes_db;
	}

	public void setTravelTypes_db(String travelTypes_db) {
		this.travelTypes_db = travelTypes_db;
	}

	public String[] getTravelType() {
		return travelType;
	}

	public void setTravelType(String[] travelType) {
		this.travelType = travelType;
	}

	public Map<String, String> getForWhomThisTest() {
		return forWhomThisTest;
	}

	public void setForWhomThisTest(Map<String, String> forWhomThisTest) {
		this.forWhomThisTest = forWhomThisTest;
	}

	
	
	
	

}
