package com.example.SpringBootJPA.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
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
			@RequestHeader(name="userId") String loginUser){
		
		userService.saveUser(user, new Long(loginUser));
		return ResponseEntity.ok(user);
	
	}
	
	@GetMapping("getAllCountries")
	public List<CountriesEntity> getAllCountries(){
		return userService.getAllCountries();
	}
	
	@GetMapping("getAllUsers")	
	public List<UsersEntity> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("downloadUsers")
	public void downloadUsers(@RequestBody List<Map<String,String>> columnsConfig, 
			HttpServletResponse response) throws Exception{
		userService.downloadUsers(columnsConfig, response);
	}
	
	@GetMapping("getUser")
	public UsersEntity getUser(@RequestParam("userId") Long userId){
		return userService.getUser(userId);
	}
	
	@GetMapping("checkAppStatus")
	public Map<String,String> checkAppStatus(){
		Map<String,String> result = new HashMap<>();
		result.put("status", "Running");
		return result;
	}
}
