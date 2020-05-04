package com.example.SpringBootJPA.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootJPA.dao.UsersRepository;
import com.example.SpringBootJPA.entities.UsersEntity;
import com.example.SpringBootJPA.service.UsersService;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository userRepository;
	
	@Override
	public UsersEntity getUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
