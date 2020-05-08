package com.example.SpringBootJPA.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootJPA.dao.UsersRepository;
import com.example.SpringBootJPA.entities.UsersEntity;
import com.example.SpringBootJPA.exceptions.RecordNotFoundException;
import com.example.SpringBootJPA.service.UsersService;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository userRepository;
	
	@Override
	public UsersEntity getUser(String userName) {
		List<UsersEntity> usersList = userRepository.findByUserName(userName);
		if(usersList.isEmpty()){
			throw new RecordNotFoundException(userName+" Not Exists");
		}
		return usersList.get(0);
	}

}
