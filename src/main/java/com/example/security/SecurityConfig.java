package com.example.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.userdetails.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
  
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	logger.info("Config ...");
    	
     http.authorizeRequests()
         .antMatchers("/")
         .permitAll()
         .antMatchers("/api/users/me")
         .hasAnyRole("USER")
         .anyRequest()
         .authenticated()
         .and()
         .formLogin()
         .permitAll()
         .and()
         .logout()
         .permitAll();

     http.csrf().disable();
    	
    }
   
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	logger.info("AuthenticationManagerBuilder ...");
    	auth.userDetailsService(new MyUserDetailsService()).passwordEncoder(passwordEncoder());
    }
      
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ 
    	logger.info("passwordEncoder ...");
        return new BCryptPasswordEncoder(); 
    }
    
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
    	logger.info("authenticationManager ...");
    	return super.authenticationManager();
    }
}
