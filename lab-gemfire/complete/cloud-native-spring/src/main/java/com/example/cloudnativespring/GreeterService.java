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

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;



@Service
public class GreeterService {


	@Autowired
	private final RestTemplate rest;

	private static final String BACKEND_SERVICE="greeter-messages";

	private static final String URI_TEMPLATE = UriComponentsBuilder.fromUriString("http://"+BACKEND_SERVICE+"/greeting")
			.queryParam("salutation", "{salutation}")
			.queryParam("name", "{name}")
			.build()
			.toUriString();


	public GreeterService(RestTemplate restTemplate) {
		this.rest = restTemplate;
	}

	public Greeting greetRestTemplate(String salutation, String name) {
		Assert.notNull(salutation, "salutation is required");
		Assert.notNull(name, "name is required");

		return rest.getForObject(URI_TEMPLATE, Greeting.class, salutation, name);
	}


}

class Greeting {

	private final String message;

	@JsonCreator
	public Greeting(@JsonProperty("message") String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}
