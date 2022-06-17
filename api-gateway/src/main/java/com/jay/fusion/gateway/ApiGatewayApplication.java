package com.jay.fusion.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.jay.fusion.gateway.security.PreGatewayFilterFactory;

@EnableEurekaClient
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public PreGatewayFilterFactory.Config  getFilterConfig() {
		return new PreGatewayFilterFactory.Config();
	}
	
}
