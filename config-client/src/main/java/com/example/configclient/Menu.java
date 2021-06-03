package com.example.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;


@RefreshScope
@Component
public class Menu {

	private String special;

	private String secretMenu;

	public Menu(@Value("${cook.special:none}") String special, @Value("${secretMenu:none}") String secretMenu) {
		this.special = special;
		this.secretMenu = secretMenu;
	}

	public String getSpecial() {
		return special;
	}

	public String getSecretMenu() {
		return secretMenu;
	}

}