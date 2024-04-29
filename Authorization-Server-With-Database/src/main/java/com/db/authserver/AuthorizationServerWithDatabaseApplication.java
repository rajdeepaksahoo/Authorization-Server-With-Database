package com.db.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthorizationServerWithDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerWithDatabaseApplication.class, args);
	}

}
