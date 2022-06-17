package com.jay.fusion.user.aop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration
public class FeignRequestInterceptor {

	@Autowired
	private HttpServletRequest request;
	
	@Bean
	public RequestInterceptor requestInterceptor() {
	  return requestTemplate -> {
		  //SecurityContextHolder.getContext().getAuthentication() if data to be fetched from authentication
	      requestTemplate.header("test-header", request.getHeader("test-header"));
	      requestTemplate.header("gateway-header", request.getHeader("gateway-header"));
	      
	  };
	}
	
}
