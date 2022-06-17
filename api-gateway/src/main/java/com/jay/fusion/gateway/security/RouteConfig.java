package com.jay.fusion.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class RouteConfig {

	@Autowired
	private PreGatewayFilterFactory.Config config;
	
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder rlb, PreGatewayFilterFactory authorizationHeaderFilter) {
		log.info(">>> Route locator executed");
		
		return rlb.routes()
				.route(p -> p.path("/users/**")
						.filters(f -> f.filter(authorizationHeaderFilter.apply(config)))
						.uri("lb://USER-SERVICE"))
				.route(p -> p.path("/departments/**")
						.filters(f -> f.filter(authorizationHeaderFilter.apply(config)))
						.uri("lb://DEPARTMENT-SERVICE"))
				.build();
	}
	
}
