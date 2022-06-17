package com.jay.fusion.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.jay.fusion.gateway.service.EntityService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class PreGatewayFilterFactory extends AbstractGatewayFilterFactory<PreGatewayFilterFactory.Config> {

    public PreGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
    	log.info(">>>>>>>>>>>> Gateway headers");
    	
        // grab configuration from Config object
        return (exchange, chain) -> {
            //If you want to build a "pre" filter you need to manipulate the
            //request before calling chain.filter
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
            builder.headers(h -> h.add("gateway-header", config.getToken(h)));
            //use builder to manipulate the request
            return chain.filter(exchange.mutate().request(builder.build()).build());
        };
    }
    

    public static class Config {

    	@Autowired
    	private EntityService entityService;
    	
		public String getToken(HttpHeaders headers) {
//			return "gateway-header";
			return entityService.computeHeader(headers);
		}
        //Put the configuration properties for your filter here
    }

}