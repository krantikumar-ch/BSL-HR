package com.example.SpringBootJPA.entities;

import com.example.SpringBootJPA.annotations.UniqueChild;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity @Table(name="employees")

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

	@Override
	public String toString() {
		return "EmployeeEntity{" +
				"employeeId=" + employeeId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", hireDate=" + hireDate +
				", jobId='" + jobId + '\'' +
				", salary=" + salary +
				", commissionPct=" + commissionPct +
				", managerId=" + managerId +
				", departmentId=" + departmentId +
				", empType='" + empType + '\'' +
				'}';
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Float getCommissionPct() {
		return commissionPct;
	}

	public void setCommissionPct(Float commissionPct) {
		this.commissionPct = commissionPct;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}
}
