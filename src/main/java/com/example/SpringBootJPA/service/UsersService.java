package com.example.SpringBootJPA.service;

import com.example.SpringBootJPA.entities.UsersEntity;

public interface UsersService {

	public UsersEntity getUser(String userName);
	
	public void saveUser(UsersEntity user, Long loginUser);
}
