package com.knutime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KnutimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnutimeApplication.class, args);
	}

	@Bean
	public ServerProperties getServerProperties() {
		return new ServerProperties();
	}
}
