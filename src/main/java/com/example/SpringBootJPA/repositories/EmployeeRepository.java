package com.example.SpringBootJPA.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.SpringBootJPA.entities.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

	@Query(value="select e.*, d.DEPARTMENT_NAME from employees e join departments d on (d.DEPARTMENT_ID = e.DEPARTMENT_ID) where e.department_id = :departmentId", nativeQuery=true)
	List<EmployeeEntity> findByLastName(@Param("departmentId") Long departmentId);
	
	List<EmployeeEntity> findByFirstNameIgnoreCaseLike(String searchItem, Pageable page);
	
	long countByFirstNameIgnoreCaseLike(String searchItem);
}
