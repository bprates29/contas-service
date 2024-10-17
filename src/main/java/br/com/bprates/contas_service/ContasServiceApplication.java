package br.com.bprates.contas_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ContasServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContasServiceApplication.class, args);
	}

}
