package com.jay.fusion.department.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class InterServiceInterceptor implements HandlerInterceptor {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if(StringUtils.isNotEmpty(request.getHeader("gateway-header"))) {
			log.info("Request contains required headers");
		} else {
			log.info("Request missing required headers");
//			response.getWriter().write("Request missing required headers in " + appName);
//	        response.setStatus(HttpStatus.BAD_REQUEST.value());
//	        return false;
		}
		return true;
	}
	
}