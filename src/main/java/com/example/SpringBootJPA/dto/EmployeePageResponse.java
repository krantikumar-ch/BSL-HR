package com.example.SpringBootJPA.dto;

import com.example.SpringBootJPA.entities.EmployeeEntity;

import java.util.List;

public class EmployeePageResponse{
	
	private List<EmployeeEntity> employees;
	
	private Long count;
	
	private Integer totalPages;
	
	private Integer pageSize;
	
	private Integer currentPage;

	public List<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
}
