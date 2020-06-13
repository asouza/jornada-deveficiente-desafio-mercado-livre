package com.deveficiente.desafiomercadolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DesafioMercadolivreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioMercadolivreApplication.class, args);
	}
	
}
