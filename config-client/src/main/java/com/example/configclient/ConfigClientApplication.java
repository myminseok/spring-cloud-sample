package com.example.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//https://cloud.spring.io/spring-cloud-config/reference/html/#_spring_cloud_config_client
///https://github.com/spring-cloud-services-samples/cook/blob/master/src/main/java/cook/CookController.java
@SpringBootApplication
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

}


@RestController
class MenuRestController{

	private final Menu menu;

	public MenuRestController(Menu menu) {
		this.menu = menu;
	}

	@RequestMapping("/")
	public String restaurant() {
		return String.format("Today's special is: %s", menu.getSpecial());
	}

}