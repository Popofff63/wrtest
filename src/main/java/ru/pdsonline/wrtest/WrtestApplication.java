package ru.pdsonline.wrtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WrtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WrtestApplication.class, args);
	}

}
