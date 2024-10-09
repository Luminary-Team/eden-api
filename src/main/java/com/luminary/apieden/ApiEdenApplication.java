package com.luminary.apieden;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Api Eden",
		description = "Api with basic operations from the database of Eden",
		version = "1"))
public class ApiEdenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiEdenApplication.class, args);
	}

}
