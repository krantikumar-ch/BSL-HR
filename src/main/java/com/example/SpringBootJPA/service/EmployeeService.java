package com.example.SpringBootJPA.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.example.SpringBootJPA.dto.EmployeePageResponse;
import com.example.SpringBootJPA.entities.EmployeeEntity;

public interface EmployeeService {

	List<EmployeeEntity> getAllEmployees();
	
	EmployeeEntity getEmployee(Long employeeId);
	
	void saveEmployee(EmployeeEntity empEntity);
	
	void deleteEmployee(Long empId);
	
	List<EmployeeEntity> getEmployeesByDepartment(Long deptId);

	EmployeePageResponse getEmployeeByPage(int pageNumber, String searchValue);
	
	public void downloadEmployee( List<Map<String,String>> columns, HttpServletResponse response) throws IOException ;
	
}
