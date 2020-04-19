package com.example.SpringBootJPA.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootJPA.dao.DepartmentRepository;
import com.example.SpringBootJPA.entities.DepartmentEntity;
import com.example.SpringBootJPA.service.DepartmentService;

@Transactional
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository deptRepository;
	
	@Override
	public List<DepartmentEntity> getAllDepartments() {
		return deptRepository.findAll();
	}

	@Override
	public DepartmentEntity getDepartment(Long departmentId) {
		Optional<DepartmentEntity> deptEntity = deptRepository.findById(departmentId);
		return deptEntity.isPresent() ? deptEntity.get() : null;
	}

	@Override
	public void saveDepartment(DepartmentEntity deptEntity) {
		deptRepository.saveAndFlush(deptEntity);
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		deptRepository.deleteById(departmentId);
	}

}
