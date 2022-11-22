package com.example.resource.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {
    //Here we specify to allow the request to the url /api/users/me with valid access token and scope read 
	Logger logger = LoggerFactory.getLogger(ResourceServer.class);

	@Override
	public void configure(HttpSecurity http) throws Exception {
		logger.info(" configure .. ");
		http.requestMatchers().antMatchers("/api/users/me**").and()
		.authorizeRequests().anyRequest()
		.access("#oauth2.hasScope('read')");
	}
}