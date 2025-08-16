package com.Ayush.SpringSecurityExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringSecurityExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityExampleApplication.class, args);
	}

}
