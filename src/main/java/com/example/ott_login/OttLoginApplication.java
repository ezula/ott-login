package com.example.ott_login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class OttLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(OttLoginApplication.class, args);
	}

}
