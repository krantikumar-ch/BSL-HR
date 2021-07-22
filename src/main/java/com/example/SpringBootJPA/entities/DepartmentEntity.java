package com.example.SpringBootJPA.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="departments")
public class DepartmentEntity {

	@Id @Column(name="DEPARTMENT_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="deptSeq")
	@SequenceGenerator(name="deptSeq", sequenceName="DEPARTMENTS_SEQ", allocationSize=10)
	private Long departmentId;
	
	@Column(name="DEPARTMENT_NAME")
	private String departmentName;
	
	@Column(name="MANAGER_ID")
	private Long managerId;
	
	@Column(name="LOCATION_ID")
	private Long locationId;
	
	@OneToMany(orphanRemoval=true, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="department_id")
	@OrderBy("firstName")
	private List<EmployeeEntity> employees;

	@Override
	public String toString() {
		return "DepartmentEntity{" +
				"departmentId=" + departmentId +
				", departmentName='" + departmentName + '\'' +
				", managerId=" + managerId +
				", locationId=" + locationId +
				", employees=" + employees +
				'}';
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public List<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees = employees;
	}
}
