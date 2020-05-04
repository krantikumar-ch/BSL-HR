package com.example.SpringBootJPA.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringBootJPA.entities.UsersEntity;
import java.lang.String;
import java.util.List;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

	List<UsersEntity> findByUserName(String username);
}
