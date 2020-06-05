package com.example.SpringBootJPA.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.example.SpringBootJPA.entities.CountriesEntity;
import com.example.SpringBootJPA.entities.UsersEntity;

public interface UsersService {

	public UsersEntity getUser(String userName);
	
	public void saveUser(UsersEntity user, Long loginUser);
	
	public List<CountriesEntity> getAllCountries();
	
	public List<UsersEntity> getAllUsers();
	
	public void downloadUsers(List<Map<String,String>> columns, HttpServletResponse response) throws Exception;
	
	public UsersEntity getUser(Long userId);
}
