package de.SassChris.HardJerky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class HardJerkyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HardJerkyApplication.class, args);
	}

}
