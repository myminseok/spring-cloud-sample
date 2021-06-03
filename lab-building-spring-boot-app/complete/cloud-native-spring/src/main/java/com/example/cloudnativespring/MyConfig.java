package com.example.cloudnativespring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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