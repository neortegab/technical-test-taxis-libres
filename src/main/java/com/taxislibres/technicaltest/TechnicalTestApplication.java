package com.taxislibres.technicaltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TechnicalTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalTestApplication.class, args);
	}
	@GetMapping
	public String welcome(){
		return """
                Welcome.
                 Endpoints are located under the "/api/v1" route.
                Please review README.md at the repository for further details of usage.""";
	}
}
