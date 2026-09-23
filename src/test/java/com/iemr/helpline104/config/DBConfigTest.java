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
package com.iemr.helpline104.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.helpline104.utils.config.ConfigProperties;

import jakarta.persistence.EntityManagerFactory;

/**
 * Both databases are pooled with the same conservative pool settings; the
 * primary one carries the operational schema and the secondary one the
 * reporting schema.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DBConfigTest {

	private final PrimaryDBConfig primaryDBConfig = new PrimaryDBConfig();
	private final SecondaryDBConfig secondaryDBConfig = new SecondaryDBConfig();

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private EntityManagerFactoryBuilder builder;

	/**
	 * The primary pool reads its credentials from the application properties, so
	 * they have to be loaded before the bean is built.
	 */
	@BeforeEach
	void loadTheApplicationProperties() {
		new ConfigProperties();
		Properties properties = (Properties) ReflectionTestUtils.getField(ConfigProperties.class, "properties");
		properties.setProperty("spring.datasource.username", "iemr");
		properties.setProperty("spring.datasource.password", "secret");
	}

	@Test
	void thePrimaryDataSourceIsPooledAndValidated() {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = (org.apache.tomcat.jdbc.pool.DataSource) primaryDBConfig
				.dataSource();

		PoolConfiguration pool = dataSource.getPoolProperties();
		assertEquals(30, pool.getMaxActive());
		assertEquals("SELECT 1", pool.getValidationQuery());
		assertEquals("iemr", dataSource.getUsername());
	}

	@Test
	void theSecondaryDataSourceIsPooledAndValidated() {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = (org.apache.tomcat.jdbc.pool.DataSource) secondaryDBConfig
				.dataSource();

		PoolConfiguration pool = dataSource.getPoolProperties();
		assertEquals(30, pool.getMaxActive());
		assertEquals("SELECT 1", pool.getValidationQuery());
	}

	@Test
	void thePrimaryEntityManagerCoversTheOperationalEntities() {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = mock(org.apache.tomcat.jdbc.pool.DataSource.class);

		assertNotNull(primaryDBConfig.entityManagerFactory(builder, dataSource));
	}

	@Test
	void theSecondaryEntityManagerCoversTheReportingEntities() {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = mock(org.apache.tomcat.jdbc.pool.DataSource.class);

		assertNotNull(secondaryDBConfig.barEntityManagerFactory(builder, dataSource));
	}

	@Test
	void thePrimaryTransactionManagerIsBoundToItsEntityManager() {
		EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);

		JpaTransactionManager transactionManager = (JpaTransactionManager) primaryDBConfig
				.transactionManager(entityManagerFactory);

		assertEquals(entityManagerFactory, transactionManager.getEntityManagerFactory());
	}

	@Test
	void theSecondaryTransactionManagerIsDeclared() {
		assertNotNull(secondaryDBConfig.barTransactionManager(mock(EntityManagerFactory.class)));
	}
}
