package com.example.SpringBootJPA.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.SpringBootJPA.dao.EmployeeRepository;
import com.example.SpringBootJPA.entities.EmployeeEntity;
import com.example.SpringBootJPA.exceptions.RecordNotFoundException;
import com.example.SpringBootJPA.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;

	@Override
	public List<EmployeeEntity> getAllEmployees() {
		return empRepository.findAll();
	}

	@Override
	public EmployeeEntity getEmployee(Long employeeId) {
		Optional<EmployeeEntity> empEntity = empRepository.findById(employeeId);
		if(!empEntity.isPresent()){
			throw new RecordNotFoundException(employeeId+" not exists");
		}
		return  empEntity.get();
	}

	@Override
	public void saveEmployee(EmployeeEntity empEntity) {
		empRepository.save(empEntity);
	}

	@Override
	public void deleteEmployee(Long empId) {
		empRepository.deleteById(empId);
	}

	@Override
	public List<EmployeeEntity> getEmployeesByDepartment(Long deptId) {
		return empRepository.findByLastName(deptId);
	}

}
