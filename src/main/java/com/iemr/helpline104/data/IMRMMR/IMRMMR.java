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
package com.iemr.helpline104.data.IMRMMR;

import java.sql.Timestamp;
import java.util.Map;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import com.google.gson.annotations.Expose;
import com.iemr.helpline104.data.location.Districts;

@Entity
@Table(name = "t_104benimrmmr")
public class IMRMMR {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "Benimrmmrid")
	private Long benImrMmrID;

	@Expose
	@Column(name = "BenCallID")
	private Integer benCallID;

	@Expose
	@Column(name = "BeneficiaryRegID")
	private Integer beneficiaryRegID;

	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;

	@Expose
	@Column(name = "within42daysOfDelivery")
	private String within42daysOfDelivery;

	@Expose
	@Column(name = "duringDelivery")
	private String duringDelivery;

	@Expose
	@Column(name = "duringPregnancy")
	private String duringPregnancy;

	@Expose
	@Column(name = "duringTransit")
	private String transitType;

	@Expose
	@Column(name = "facilityID")
	private Integer facilityID;

	@Expose
	@Column(name = "facilityName")
	private String facilityName;

	@Expose
	@Column(name = "noofDelivery")
	private Integer noofDelivery;

	@Expose
	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;

	@Expose
	@Column(name = "reasonOfDeath")
	private String reasonOfDeath;

	@Column(name = "referenceDate")
	private Timestamp referenceDate;

	@Expose
	@Column(name = "relationshipType")
	private Integer relationshipType;

	@Expose
	@Column(name = "relativeMobileNumber")
	private Long relativeMobileNumber;

	@Expose
	@Transient
	private String[] supportServicesName;

	@Expose
	@Column(name = "supportServicesName")
	private String supportServicesName_db;

	@Expose
	@Transient
	private String[] supportServicesID;

	@Expose
	@Column(name = "supportServicesID")
	private String supportServicesID_db;

	@Expose
	@Column(name = "TypeOfDelivery")
	private String TypeOfDelivery;

	@Expose
	@Column(name = "typeOfInfromation")
	private String typeOfInfromation;

	@Expose
	@Column(name = "userID")
	private Integer userID;

	@Expose
	@Column(name = "victimAge")
	private Integer victimAge;

	@Expose
	@Column(name = "victimDistrictid")
	private Integer victimDistrict;

	@Expose
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(updatable = false, insertable = false, name = "victimDistrictid", referencedColumnName = "districtID")
	private Districts victimDistrictName;
	
	@Expose
	@Column(name = "victimName")
	private String victimName;

	@Expose
	@Column(name = "victimTalukid")
	private Integer victimTaluk;

	@Expose
	@Column(name = "victimVillageid")
	private Integer victimVillage;

	@Expose
	@Transient
	private Map<String, String> stagesOfDeath;

	@Expose
	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Expose
	@Column(name = "requestID")
	private String requestID;

	@Expose
	@Column(name = "informerCategory")
	private String informerCategory;

	@Expose
	@Column(name = "selectedCategory")
	private String selectedCategory;

	@Expose
	@Column(name = "informerName")
	private String informerName;

	@Expose
	@Column(name = "informerMobileNumber")
	private String informerMobileNumber;

	@Expose
	@Column(name = "informerDistrictid")
	private Integer informerDistrictid;

	@Expose
	@Column(name = "informerTalukid")
	private Integer informerTalukid;

	@Expose
	@Column(name = "informerVillageid")
	private Integer informerVillageid;

	@Expose
	@Column(name = "informerAddress")
	private String informerAddress;

	@Expose
	@Column(name = "identityType")
	private String identityType;

	@Expose
	@Column(name = "informerIdNo")
	private String informerIdNo;

	@Expose
	@Column(name = "victimAddress")
	private String victimAddress;

	@Expose
	@Column(name = "transitTypeId")
	private Integer transitTypeID;

	@Expose
	@Column(name = "baseCommunityID")
	private Integer baseCommunityID;
	
	@Expose
	@Column(name = "baseCommunityname")
	private String baseCommunity;
    
	@Expose
	@Column(name = "victimguardian")
	private String victimGuardian;
	
	@Expose
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;

	@Expose
	@Column(name = "deathConfirmed")
	private Boolean deathConfirmed;
	
	@Expose
	@Column(name = "above42daysOfDelivery")
	private String above42daysOfDelivery;
	
	@Expose
	@Transient
	private String phoneNum;

	@Expose
    @Transient
    private String deathConfirmedUI;
	
	public IMRMMR() {
		super();
	}

	public IMRMMR(Long benImrMmrID, Integer benCallID, Integer beneficiaryRegID, String within42daysOfDelivery, String duringDelivery,
            String duringPregnancy, String transitType, Integer facilityID, String facilityName, Integer noofDelivery, String reasonOfDeath,
            Timestamp referenceDate, Integer relationshipType, Long relativeMobileNumber, String supportServicesID_db, String supportServicesName_db,
            String TypeOfDelivery, String typeOfInfromation, Integer userID, Integer victimAge, Integer victimDistrict, String victimName,
            Integer victimTaluk, Integer victimVillage, String requestID, String selectedCategory, String informerName, String informerMobileNumber,
            Integer informerDistrictid, Integer informerTalukid, Integer informerVillageid, String informerAddress, String identityType,
            String informerIdNo,  String victimAddress, String informerCategory, String baseCommunity, Boolean deathConfirmed,String above42daysOfDelivery,String victimGuardian,
            Integer transitTypeID,Integer baseCommunityID, String victimDistrictName_temp)
 {
        super();
        this.benImrMmrID = benImrMmrID;
        this.benCallID = benCallID;
        this.beneficiaryRegID = beneficiaryRegID;
        this.within42daysOfDelivery = within42daysOfDelivery;
        this.duringDelivery = duringDelivery;
        this.duringPregnancy = duringPregnancy;
        this.transitType = transitType;
        this.facilityID = facilityID;
        this.facilityName = facilityName;
        this.noofDelivery = noofDelivery;
        this.reasonOfDeath = reasonOfDeath;
        this.referenceDate = referenceDate;
        this.relationshipType = relationshipType;
        this.relativeMobileNumber = relativeMobileNumber;
        this.supportServicesID_db = supportServicesID_db;
        this.supportServicesName_db = supportServicesName_db;
        this.TypeOfDelivery = TypeOfDelivery;
        this.typeOfInfromation = typeOfInfromation;
        this.userID = userID;
        this.victimAge = victimAge;
        this.victimDistrict = victimDistrict;
        this.victimDistrictName = new Districts(victimDistrict, victimDistrictName_temp);
        this.victimName = victimName;
        this.victimTaluk = victimTaluk;
        this.victimVillage = victimVillage;
        this.requestID = requestID;
        this.selectedCategory = selectedCategory;
        this.informerName = informerName;
        this.informerMobileNumber =informerMobileNumber;
        this.informerDistrictid = informerDistrictid;
        this.informerTalukid = informerTalukid;
        this.informerVillageid = informerVillageid;
        this.informerAddress = informerAddress;
        this.identityType = identityType;
        this.informerIdNo = informerIdNo;
        this.victimAddress = victimAddress;
        this.informerCategory = informerCategory;
        this.baseCommunity = baseCommunity;
        this.deathConfirmed = deathConfirmed;
        this.above42daysOfDelivery = above42daysOfDelivery;
        this.victimGuardian = victimGuardian;
        this.transitTypeID = transitTypeID;
        this.baseCommunityID = baseCommunityID;
    }

	public String getDeathConfirmedUI() {
		return deathConfirmedUI;
	}

	public void setDeathConfirmedUI(String deathConfirmedUI) {
		this.deathConfirmedUI = deathConfirmedUI;
	}

	public Boolean getDeathConfirmed() {
		return deathConfirmed;
	}

	public void setDeathConfirmed(Boolean deathConfirmed) {
		this.deathConfirmed = deathConfirmed;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Integer getFacilityID() {
		return facilityID;
	}

	public void setFacilityID(Integer facilityID) {
		this.facilityID = facilityID;
	}

	public String[] getSupportServicesID() {
		return supportServicesID;
	}

	public void setSupportServicesID(String[] supportServicesID) {
		this.supportServicesID = supportServicesID;
	}

	public String getSupportServicesID_db() {
		return supportServicesID_db;
	}

	public void setSupportServicesID_db(String supportServicesID_db) {
		this.supportServicesID_db = supportServicesID_db;
	}

	public String getWithin42daysOfDelivery() {
		return within42daysOfDelivery;
	}

	public void setWithin42daysOfDelivery(String within42daysOfDelivery) {
		this.within42daysOfDelivery = within42daysOfDelivery;
	}

	public String[] getSupportServicesName() {
		return supportServicesName;
	}

	public void setSupportServicesName(String[] supportServicesName) {
		this.supportServicesName = supportServicesName;
	}

	public String getSupportServicesName_db() {
		return supportServicesName_db;
	}

	public void setSupportServicesName_db(String supportServicesName_db) {
		this.supportServicesName_db = supportServicesName_db;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	public void setBeneficiaryRegID(Integer beneficiaryRegID) {
		this.beneficiaryRegID = beneficiaryRegID;
	}

	public Map<String, String> getStagesOfDeath() {
		return stagesOfDeath;
	}

	public void setStagesOfDeath(Map<String, String> stagesOfDeath) {
		this.stagesOfDeath = stagesOfDeath;
	}

	public Long getBenImrMmrID() {
		return benImrMmrID;
	}

	public void setBenImrMmrID(Long benImrMmrID) {
		this.benImrMmrID = benImrMmrID;
	}

	public Integer getBenCallID() {
		return benCallID;
	}

	public void setBenCallID(Integer benCallID) {
		this.benCallID = benCallID;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDuringDelivery() {
		return duringDelivery;
	}

	public void setDuringDelivery(String duringDelivery) {
		this.duringDelivery = duringDelivery;
	}

	public String getDuringPregnancy() {
		return duringPregnancy;
	}

	public void setDuringPregnancy(String duringPregnancy) {
		this.duringPregnancy = duringPregnancy;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public Integer getNoofDelivery() {
		return noofDelivery;
	}

	public void setNoofDelivery(Integer noofDelivery) {
		this.noofDelivery = noofDelivery;
	}

	public Integer getProviderServiceMapID() {
		return providerServiceMapID;
	}

	public void setProviderServiceMapID(Integer providerServiceMapID) {
		this.providerServiceMapID = providerServiceMapID;
	}

	public String getReasonOfDeath() {
		return reasonOfDeath;
	}

	public void setReasonOfDeath(String reasonOfDeath) {
		this.reasonOfDeath = reasonOfDeath;
	}

	public Timestamp getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(Timestamp referenceDate) {
		this.referenceDate = referenceDate;
	}

	public Integer getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(Integer relationshipType) {
		this.relationshipType = relationshipType;
	}

	public Long getRelativeMobileNumber() {
		return relativeMobileNumber;
	}

	public void setRelativeMobileNumber(Long relativeMobileNumber) {
		this.relativeMobileNumber = relativeMobileNumber;
	}

	public String getTypeOfDelivery() {
		return TypeOfDelivery;
	}

	public void setTypeOfDelivery(String typeOfDelivery) {
		TypeOfDelivery = typeOfDelivery;
	}

	public String getTypeOfInfromation() {
		return typeOfInfromation;
	}

	public void setTypeOfInfromation(String typeOfInfromation) {
		this.typeOfInfromation = typeOfInfromation;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getVictimAge() {
		return victimAge;
	}

	public void setVictimAge(Integer victimAge) {
		this.victimAge = victimAge;
	}

	public Integer getVictimDistrict() {
		return victimDistrict;
	}

	public void setVictimDistrict(Integer victimDistrict) {
		this.victimDistrict = victimDistrict;
	}

	public String getVictimName() {
		return victimName;
	}

	public void setVictimName(String victimName) {
		this.victimName = victimName;
	}

	public Integer getVictimTaluk() {
		return victimTaluk;
	}

	public void setVictimTaluk(Integer victimTaluk) {
		this.victimTaluk = victimTaluk;
	}

	public Integer getVictimVillage() {
		return victimVillage;
	}

	public void setVictimVillage(Integer victimVillage) {
		this.victimVillage = victimVillage;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getInformerCategory() {
		return informerCategory;
	}

	public void setInformerCategory(String informerCategory) {
		this.informerCategory = informerCategory;
	}

	public String getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public String getInformerName() {
		return informerName;
	}

	public void setInformerName(String informerName) {
		this.informerName = informerName;
	}

	public String getInformerMobileNumber() {
		return informerMobileNumber;
	}

	public void setInformerMobileNumber(String informerMobileNumber) {
		this.informerMobileNumber = informerMobileNumber;
	}

	public Integer getInformerDistrictid() {
		return informerDistrictid;
	}

	public void setInformerDistrictid(Integer informerDistrictid) {
		this.informerDistrictid = informerDistrictid;
	}

	public Integer getInformerTalukid() {
		return informerTalukid;
	}

	public void setInformerTalukid(Integer informerTalukid) {
		this.informerTalukid = informerTalukid;
	}

	public Integer getInformerVillageid() {
		return informerVillageid;
	}

	public void setInformerVillageid(Integer informerVillageid) {
		this.informerVillageid = informerVillageid;
	}

	public String getInformerAddress() {
		return informerAddress;
	}

	public void setInformerAddress(String informerAddress) {
		this.informerAddress = informerAddress;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getInformerIdNo() {
		return informerIdNo;
	}

	public void setInformerIdNo(String informerIdNo) {
		this.informerIdNo = informerIdNo;
	}

	public String getVictimAddress() {
		return victimAddress;
	}

	public void setVictimAddress(String victimAddress) {
		this.victimAddress = victimAddress;
	}
	
	public Integer getBaseCommunityID() {
		return baseCommunityID;
	}

	public void setBaseCommunityID(Integer baseCommunityID) {
		this.baseCommunityID = baseCommunityID;
	}

	public Districts getVictimDistrictName() {
		return victimDistrictName;
	}

	public void setVictimDistrictName(Districts victimDistrictName) {
		this.victimDistrictName = victimDistrictName;
	}

	public String getAbove42daysOfDelivery() {
		return above42daysOfDelivery;
	}

	public void setAbove42daysOfDelivery(String above42daysOfDelivery) {
		this.above42daysOfDelivery = above42daysOfDelivery;
	}
	
	public Integer getTransitTypeID() {
		return transitTypeID;
	}

	public void setTransitTypeID(Integer transitTypeID) {
		this.transitTypeID = transitTypeID;
	}

	public String getBaseCommunity() {
		return baseCommunity;
	}

	public void setBaseCommunity(String baseCommunity) {
		this.baseCommunity = baseCommunity;
	}

	public String getTransitType() {
		return transitType;
	}

	public void setTransitType(String transitType) {
		this.transitType = transitType;
	}

	public String getVictimGuardian() {
		return victimGuardian;
	}

	public void setVictimGuardian(String victimGuardian) {
		this.victimGuardian = victimGuardian;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
