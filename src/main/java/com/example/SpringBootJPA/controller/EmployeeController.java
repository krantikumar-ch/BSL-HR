package com.example.SpringBootJPA.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

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
	
	@GetMapping("getEmployeesByStream")
	public ResponseBodyEmitter getEmployeesByStream(){
		ResponseBodyEmitter emitter = new ResponseBodyEmitter();
		List<EmployeeEntity> empEntities = empService.getAllEmployees();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		List<EmployeeEntity> subList = new ArrayList<>();
		executor.execute(() ->{
			try{
				
				for(EmployeeEntity employeeEntity : empEntities){
					if(subList.size() ==5){
						emitter.send(subList);
						subList.clear();
						Thread.sleep(2000);
					}
					subList.add(employeeEntity);
					
				}
				if(subList.size() !=0){
					emitter.send(subList);
				}
				emitter.complete();
			}
			catch(Exception e){
				e.printStackTrace();
				emitter.completeWithError(e);
			}
		});
		executor.shutdown();
		return emitter;
	}
}
