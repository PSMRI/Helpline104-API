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
package com.iemr.helpline104.service.beneficiarycall;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.iemr.helpline104.data.beneficiarycall.BeneficiaryCall;
import com.iemr.helpline104.data.location.DistrictBlock;
import com.iemr.helpline104.data.location.DistrictBranchMapping;
import com.iemr.helpline104.data.location.Districts;
import com.iemr.helpline104.repository.beneficiarycall.BeneficiaryCallRepository;

@RunWith(MockitoJUnitRunner.class)
public class BeneficiaryCallServiceImplTest {
	
	@InjectMocks
	BeneficiaryCallServiceImpl beneficiaryCallServiceImpl;
	
	@Mock
	BeneficiaryCallRepository beneficiaryCallRepository;
	
	@Test
	public void createCallTest()
	{
		BeneficiaryCall beneficiaryCall=new BeneficiaryCall();
		BeneficiaryCall beneficiaryCall1=new BeneficiaryCall();
		beneficiaryCall.setCallID("101");
		beneficiaryCall1.setCallID("101");
		doReturn(beneficiaryCall1).when(beneficiaryCallRepository).save(beneficiaryCall);
		BeneficiaryCall response=beneficiaryCallServiceImpl.createCall(beneficiaryCall);
		assertTrue(response.getCallID().equals("101"));
	}
	@Test
	public void createCallTest1()
	{
		BeneficiaryCall beneficiaryCall=new BeneficiaryCall();
		BeneficiaryCall response=beneficiaryCallServiceImpl.createCall(beneficiaryCall);
		assertTrue(null==response);
	}
	
	
	
	@Test
	public void updateBeneficiaryIDInCallTest()
	{
		doReturn(1).when(beneficiaryCallRepository).updateBeneficiaryIDInCall(Mockito.anyLong(), Mockito.anyLong());
		Integer response=beneficiaryCallServiceImpl.updateBeneficiaryIDInCall(Mockito.anyLong(), Mockito.anyLong());
		assertTrue(1==response);
	}
	
	
	@Test
	public void updateBeneficiaryIDInCallTest1()
	{
		Integer response=beneficiaryCallServiceImpl.updateBeneficiaryIDInCall(Mockito.anyLong(), Mockito.anyLong());
		assertFalse(1==response);
	}
}
