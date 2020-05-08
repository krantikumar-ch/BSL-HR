package com.example.SpringBootJPA.config.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SpringBootJPA.entities.UsersEntity;
import com.example.SpringBootJPA.service.UsersService;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersService userservice;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("Load by username");
		UsersEntity user = userservice.getUser(userName);
		return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}
}
