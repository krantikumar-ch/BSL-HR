package com.example.SpringBootJPA.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="departments")
@Getter @Setter
@ToString
public class DepartmentEntity {

	@Id @Column(name="DEPARTMENT_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="deptSeq")
	@SequenceGenerator(name="deptSeq", sequenceName="DEPARTMENTS_SEQ", allocationSize=1)
	private Long departmentId;
	
	@Column(name="DEPARTMENT_NAME")
	private String departmentName;
	
	@Column(name="MANAGER_ID")
	private Long managerId;
	
	@Column(name="LOCATION_ID")
	private Long locationId;
	
	@OneToMany(orphanRemoval=true, cascade=CascadeType.ALL)
	@JoinColumn(name="department_id")
	@OrderBy("firstName")
	private List<EmployeeEntity> employees;
}
