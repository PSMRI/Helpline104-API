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
package com.iemr.helpline104;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * The application declares the beans the rest of the service reads its
 * configuration and its Redis session store through, and greets a caller by
 * name on the smoke-test endpoint.
 */
class AppTest {

	private final App app = new App();

	@Test
	void theConfigurationPropertiesBeanIsDeclared() {
		assertNotNull(app.configProperties());
	}

	@Test
	void theSharedApplicationBeansAreDeclared() {
		assertNotNull(app.instantiateBeans());
	}

	@Test
	void theServletContainerIsGivenTheApplicationItself() {
		SpringApplicationBuilder builder = new SpringApplicationBuilder();

		assertNotNull(app.configure(builder));
	}

	@Test
	void helloGreetsTheCallerByName() {
		assertEquals("Hi Asha !", new HelloController().hello("Asha"));
	}

	@Test
	void theRedisTemplateKeysUsersByTheirIdAsAString() {
		RedisConnectionFactory factory = mock(RedisConnectionFactory.class);

		RedisTemplate<String, Object> template = new HelloController().redisTemplate(factory);

		assertEquals(StringRedisSerializer.class, template.getKeySerializer().getClass());
		assertNotNull(template.getValueSerializer());
	}
}
