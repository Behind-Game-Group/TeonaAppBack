package com.group.teona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.group.teona.repositories")
public class TeonaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeonaApplication.class, args);
	}

}
