package com.meryemefe.kartacatask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class KartacaTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(KartacaTaskApplication.class, args);
	}

}

