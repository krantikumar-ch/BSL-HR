package com.example.SpringBootJPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootJPA.entities.EmployeeEntity;
import com.example.SpringBootJPA.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@GetMapping("getAllEmployees")
	public List<EmployeeEntity> getAllEmployees(){
		return empService.getAllEmployees();
	}
	
	@PutMapping("saveEmployee")
	public EmployeeEntity saveEmployee(@RequestBody EmployeeEntity empEntity){
		empService.saveEmployee(empEntity);
		return empEntity;
	}
	
	@GetMapping("getEmployeesByDepartment")
	public List<EmployeeEntity> getEmployeesByDepartment(@RequestParam("deptId")Long deptId){
		return empService.getEmployeesByDepartment(deptId);
	}
	
	@DeleteMapping("deleteEmployee/{empId}")
	public void deleteEmployee(@PathVariable(name="empId")Long empId){
		empService.deleteEmployee(empId);
	}
	
	@GetMapping("getEmployee")
	public EmployeeEntity getEmployee(@RequestParam("employeeId") Long empId){
		return empService.getEmployee(empId);
	}
}
