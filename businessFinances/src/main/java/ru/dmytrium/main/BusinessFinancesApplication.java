package ru.dmytrium.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@SpringBootApplication
public class BusinessFinancesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessFinancesApplication.class, args);
	}

}
