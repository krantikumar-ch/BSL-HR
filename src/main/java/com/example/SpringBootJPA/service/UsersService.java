package com.example.SpringBootJPA.service;

import java.util.List;

import com.example.SpringBootJPA.entities.CountriesEntity;
import com.example.SpringBootJPA.entities.UsersEntity;

public interface UsersService {

	public UsersEntity getUser(String userName);
	
	public void saveUser(UsersEntity user, Long loginUser);
	
	public List<CountriesEntity> getAllCountries();
}
