package com.example.SpringBootJPA.dto;

import java.util.List;

import com.example.SpringBootJPA.entities.EmployeeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeePageResponse{
	
	private List<EmployeeEntity> employees;
	
	private Long count;
	
	private Integer totalPages;
	
	private Integer pageSize;
	
	private Integer currentPage;
}
