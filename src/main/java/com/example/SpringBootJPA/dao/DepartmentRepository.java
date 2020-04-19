package com.example.SpringBootJPA.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringBootJPA.entities.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

}
