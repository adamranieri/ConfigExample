package dev.ranieri.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableEurekaClient
@EntityScan("dev.ranieri.entities") // will allow spring to find your entities
@ComponentScan("dev.ranieri")// please scan all packages and any class labled with component incorporate into the spring application
@EnableJpaRepositories("dev.ranieri.repositories") //
public class MicroRpasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroRpasApplication.class, args);
	}

}
