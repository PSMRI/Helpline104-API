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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.helpline104.utils.IEMRApplBeans;
import com.iemr.helpline104.utils.config.ConfigProperties;

import jakarta.ws.rs.core.MediaType;

@SpringBootApplication
/*
 * @ComponentScan
 * 
 * @EnableTransactionManagement(proxyTargetClass = true)
 * 
 * @EnableCaching(proxyTargetClass = true)
 */
public class App extends SpringBootServletInitializer {

	@Bean
	public ConfigProperties configProperties() {
        return new ConfigProperties();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}
	
	@Bean
	public IEMRApplBeans instantiateBeans()
	{
		return new IEMRApplBeans();
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<App> applicationClass = App.class;
}

@RestController
class HelloController {

	@PostMapping(value ="/hello/{name}", produces = MediaType.APPLICATION_JSON)
	public String hello(@PathVariable String name) {

		return "Hi " + name + " !";

	}
}


