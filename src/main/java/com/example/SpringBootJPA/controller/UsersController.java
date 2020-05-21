package com.example.SpringBootJPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootJPA.entities.CountriesEntity;
import com.example.SpringBootJPA.entities.UsersEntity;
import com.example.SpringBootJPA.service.UsersService;

@RestController
public class UsersController {
	
	@Autowired
	private UsersService userService;
	
	@PutMapping("saveUser")
	public ResponseEntity<UsersEntity> saveUser(@RequestBody UsersEntity user,
			@RequestHeader(name="userId", defaultValue="1") String loginUser){
		
		userService.saveUser(user, new Long(loginUser));
		return ResponseEntity.ok(user);
	
	}
	
	@GetMapping("getAllCountries")
	public List<CountriesEntity> getAllCountries(){
		return userService.getAllCountries();
	}

}
