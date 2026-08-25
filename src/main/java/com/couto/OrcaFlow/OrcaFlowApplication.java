package com.couto.OrcaFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class OrcaFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrcaFlowApplication.class, args);
	}

}
