package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.UserProfile;

@RestController
public class RestResource 
{
	
	Logger logger = LoggerFactory.getLogger(RestResource.class);

    @GetMapping("/api/users/me")
    public ResponseEntity<UserProfile> profile() 
    {
        
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      
        String email = user.getUsername() + "@ashokit.com";
        
 
        UserProfile profile = new UserProfile();
        
        profile.setName(user.getUsername());
        profile.setEmail(email);
        
        if(user.isEnabled()) {
        	profile.setStatus("ACTIVE");
        }
        else {
        	profile.setStatus("INACTIVE");
        }
 
        return ResponseEntity.ok(profile);
       // return  new  ResponseEntity<UserProfile>(profile,HttpStatus.OK);
    }
}