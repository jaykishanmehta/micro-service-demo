package com.jay.fusion.gateway.service;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EntityService {

	public String computeHeader(HttpHeaders headers) {
		String token = null;
		log.info("Compute header");
		
		List<String> entity = headers.get("test-header");
		if(!CollectionUtils.isEmpty(entity)) {
			token = entity.get(0) + "-gateway";
		}
		return token;
	}

}
