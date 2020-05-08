package com.example.SpringBootJPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootJPA.entities.DepartmentEntity;
import com.example.SpringBootJPA.service.DepartmentService;

@RestController
public class DepartmentController //extends AutowiredAnnotationBeanPostProcessor
{

	@Autowired
	private DepartmentService deptService;
	
	@GetMapping("getAllDepartments")
	public List<DepartmentEntity> getAllDepartments(){
		return deptService.getAllDepartments();
	}
	
	@GetMapping("getDepartment")
	public DepartmentEntity getDepartment(@RequestParam("deptId") Long deptId){
		return deptService.getDepartment(deptId);
	}
}
