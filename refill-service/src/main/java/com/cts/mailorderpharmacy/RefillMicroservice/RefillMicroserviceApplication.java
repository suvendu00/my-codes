package com.cts.mailorderpharmacy.RefillMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

@EnableFeignClients
public class RefillMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefillMicroserviceApplication.class, args);
	}

}
