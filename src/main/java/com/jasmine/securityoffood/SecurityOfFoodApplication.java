package com.jasmine.securityoffood;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.jasmine.securityoffood.mapper")
public class SecurityOfFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityOfFoodApplication.class, args);
	}

}
