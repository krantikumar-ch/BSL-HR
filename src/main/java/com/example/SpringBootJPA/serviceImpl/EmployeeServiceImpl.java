package com.example.SpringBootJPA.serviceImpl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.SpringBootJPA.common.AppUtils;
import com.example.SpringBootJPA.dto.EmployeePageResponse;
import com.example.SpringBootJPA.entities.EmployeeEntity;
import com.example.SpringBootJPA.exceptions.RecordNotFoundException;
import com.example.SpringBootJPA.repositories.EmployeeRepository;
import com.example.SpringBootJPA.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;

	@Override
	public List<EmployeeEntity> getAllEmployees() {
		return empRepository.findAll(Sort.by("firstName"));
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
	
	@Override
	public EmployeePageResponse getEmployeeByPage(int pageNumber, String searchValue){
		
		int pageSize= 20;
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize, 
							Sort.by("firstName").ascending()); 
		Page<EmployeeEntity> empPage = null;
		if(!StringUtils.isEmpty(searchValue)){
			String search ="%"+searchValue+"%";
			empPage= empRepository.findByFirstNameIgnoreCaseLike(search, pageable);
		}
		else{
			empPage= empRepository.findAll(pageable);
		}
		EmployeePageResponse response= new EmployeePageResponse();
		response.setEmployees(empPage.toList());
		response.setCount(empPage.getTotalElements());
		response.setPageSize(pageSize);
		response.setTotalPages(empPage.getTotalPages());
		response.setCurrentPage(pageNumber);
		return response;
		 
	}

	@Override
	public void downloadEmployee(List<Map<String, String>> columns, HttpServletResponse response) throws IOException {
		
		List<EmployeeEntity> empList = getAllEmployees();
		AppUtils.addExcelToResponse(columns, empList, "Employees", "Employee", response);
	
	}

}
