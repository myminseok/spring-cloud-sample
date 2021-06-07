package com.example.cloudnativespring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.geode.config.annotation.EnableClusterAware;

@SpringBootApplication
@EnableClusterAware
public class CloudNativeSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudNativeSpringApplication.class, args);
	}

}

