package com.example.SpringBootJPA.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import com.example.SpringBootJPA.entities.EmployeeEntity;
import com.example.SpringBootJPA.service.EmployeeService;

@SuppressWarnings({"unchecked", "rawtypes"})
@RestController
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@GetMapping("getAllEmployees")
	public List<EmployeeEntity> getAllEmployees(@RequestHeader(name="userName") String userName){
		System.out.println("username "+userName);
		return empService.getAllEmployees();
	}

	
	/*@GetMapping("getAllEmployees")
	public List<EmployeeEntity> getAllEmployees(@RequestHeader Map<String, String> headersMap){
		headersMap.forEach((key, value)->{
			System.out.println("key "+key+": value "+value);
		});
		return empService.getAllEmployees();
	}*/

	
	@PutMapping("saveEmployee")
	public ResponseEntity<EmployeeEntity> saveEmployee(@Valid @RequestBody EmployeeEntity empEntity, BindingResult result){
		if(result.hasErrors()){
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error: errors){
				throw new RuntimeException(error.getDefaultMessage());
			}
		}
		empService.saveEmployee(empEntity);
		return new ResponseEntity(empEntity, HttpStatus.OK) ;
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
	public ResponseEntity<EmployeeEntity> getEmployee(@RequestParam("employeeId") Long empId){
		EmployeeEntity empEntity = empService.getEmployee(empId);
		return new ResponseEntity(empEntity,HttpStatus.OK);
	}
	
	@GetMapping("getEmployeesByStream")
	public ResponseBodyEmitter getEmployeesByStream(){
		
		/*provide timeout of responsebody emitter or it will take server
		default timeout. Once it reaches timeout emitter the endpoint call will close
	*/
		ResponseBodyEmitter emitter = new ResponseBodyEmitter(180000l);
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
