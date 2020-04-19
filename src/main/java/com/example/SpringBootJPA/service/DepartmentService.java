package com.example.SpringBootJPA.service;

import java.util.List;

import com.example.SpringBootJPA.entities.DepartmentEntity;

public interface DepartmentService {
	
	List<DepartmentEntity> getAllDepartments();
	
	DepartmentEntity getDepartment(Long departmentId);
	
	void saveDepartment(DepartmentEntity deptEntity);
	
	void deleteDepartment(Long departmentId);
	
}
