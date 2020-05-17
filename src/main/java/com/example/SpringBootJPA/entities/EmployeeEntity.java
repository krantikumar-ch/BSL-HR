package com.example.SpringBootJPA.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.example.SpringBootJPA.annotations.UniqueChild;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Table(name="employees")

@Getter @Setter @ToString

@UniqueChild(uniqueKeys={"firstName","departmentId"}, primaryKey="employeeId", message="First Name already Exists. Try Some other")

@JsonIgnoreProperties(ignoreUnknown=true)
public class EmployeeEntity {
	
	@Id @Column(name="EMPLOYEE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="empSeq")
	@SequenceGenerator(name="empSeq", sequenceName="EMPLOYEES_SEQ", allocationSize=1)
	private Long employeeId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="EMAIL")
	@NotBlank(message="Email is Mandatory")
	private String email;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="HIRE_DATE")
	private Date hireDate;
	
	@Column(name="JOB_ID")
	private String jobId;
	
	@Column(name="SALARY")
	private Float salary;
	
	@Column(name="COMMISSION_PCT")
	private Float commissionPct;
	
	@Column(name="MANAGER_ID")
	private Long managerId;
	
	@Column(name="DEPARTMENT_ID")
	private Long departmentId;
	
	@Column(name="EMP_TYPE")
	private String empType;
	
}
