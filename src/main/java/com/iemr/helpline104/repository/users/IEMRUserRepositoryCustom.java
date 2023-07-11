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
package com.iemr.helpline104.repository.users;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iemr.helpline104.data.users.M_User;
import com.iemr.helpline104.data.users.M_UserSecurityQMapping;

@Repository
@RestResource(exported = false)
public interface IEMRUserRepositoryCustom extends CrudRepository<M_User, Long> {
	@Query("SELECT u FROM M_User u WHERE LOWER(u.UserName) = LOWER(:userName) and  u.Password = :password ")
	List<M_User> findByUserNamePassword(@Param("userName") String username, @Param("password") String password);

	@Query("SELECT u FROM M_User u WHERE LOWER(u.UserName) = LOWER(:userName)")
	M_User findByUserName(@Param("userName") String username);

	@Query("SELECT usqm FROM M_UserSecurityQMapping usqm WHERE usqm.UserID = :userId")
	List<M_UserSecurityQMapping> getUserSecurityQues(@Param("userId") Long userid);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE M_User u set u.Password = :password where u.UserID = :userId")
	int updateSetForgetPassword(@Param("userId") Long userid, @Param("password") String password);

	@Query("SELECT u FROM M_User u WHERE LOWER(u.UserName) = LOWER(:userName) and  u.Password = :password ")
	M_User findUserForChangePass(@Param("userName") String username, @Param("password") String password);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE M_User u set u.StatusID = 2 where u.UserID = :userId")
	int updateSetUserStatusActive(@Param("userId") Long userId);

}
