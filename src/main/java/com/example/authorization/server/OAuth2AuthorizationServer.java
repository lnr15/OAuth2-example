package com.example.authorization.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter 
{
	
	Logger logger = LoggerFactory.getLogger(OAuth2AuthorizationServer.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Value("${hostIp}")
    private String hostIp;
    
    @Autowired
    AuthenticationManager amBean;
    
    @Autowired
    UserDetailsService service;
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	logger.info("AuthorizationServerEndpointsConfigurer  ..");
    	endpoints.authenticationManager(amBean).userDetailsService(service);
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	logger.info(" configure ..");
        clients
            .inMemory()
            .withClient("clientapp").secret(passwordEncoder.encode("654321"))
            .authorizedGrantTypes("authorization_code", "refresh_token","password")
            
            .scopes("read")
           
            .redirectUris("http://"+hostIp+":5454/auth_code")
            .accessTokenValiditySeconds(100)
            .refreshTokenValiditySeconds(600);
    }
}