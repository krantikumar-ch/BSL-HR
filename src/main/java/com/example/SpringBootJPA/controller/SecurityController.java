package com.example.SpringBootJPA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootJPA.config.security.JWTUtils;
import com.example.SpringBootJPA.dto.AuthenticationRequest;
import com.example.SpringBootJPA.entities.UsersEntity;
import com.example.SpringBootJPA.service.UsersService;

@RestController
public class SecurityController {

	@Autowired
	private UsersService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) 
			throws Exception{
		
		UsersEntity user = userService.getUser(request.getUserName());
		try{
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(),
							request.getPassword()));
		}
		catch(BadCredentialsException bce){
			bce.printStackTrace();
			throw new Exception("Invalid User Name and password");
		}
		
		return ResponseEntity.ok(JWTUtils.createJWT(user));	
	}
}