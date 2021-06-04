/*
 * Copyright 2017-Present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.cloudnativespring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


//https://docs.spring.io/spring-boot/docs/2.0.0.BUILD-SNAPSHOT/reference/html/boot-features-security-webflux.html
//https://docs.spring.io/spring-security/site/docs/current/reference/html5/

@Profile({"dev","cloud","prod"})
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

		@Bean
		SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
			return http
					// Demonstrate that method security works
					// Best practice to use both for defense in depth
					.authorizeExchange(exchanges -> exchanges
							.anyExchange().permitAll()
					)
					.httpBasic().disable()
					.build();
		}
}
