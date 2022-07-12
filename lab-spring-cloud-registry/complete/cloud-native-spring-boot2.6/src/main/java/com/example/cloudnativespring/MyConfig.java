package com.example.cloudnativespring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
public class MyConfig {

	private String special;

	public MyConfig(@Value("${cook.special:none}") String special) {
		this.special = special;
	}

	public String getSpecial() {
		return special;
	}

}