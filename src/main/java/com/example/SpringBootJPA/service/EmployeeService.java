package com.example.SpringBootJPA.service;

import java.util.List;

import com.example.SpringBootJPA.entities.EmployeeEntity;

public interface EmployeeService {

	List<EmployeeEntity> getAllEmployees();
	
	EmployeeEntity getEmployee(Long employeeId);
	
	void saveEmployee(EmployeeEntity empEntity);
	
	void deleteEmployee(Long empId);
	
	List<EmployeeEntity> getEmployeesByDepartment(Long deptId);
	
}
