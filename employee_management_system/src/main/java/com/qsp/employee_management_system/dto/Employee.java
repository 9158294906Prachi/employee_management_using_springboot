package com.qsp.employee_management_system.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employee_info")
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	private String employeeName;
	@Column(unique = true)
	private long employeePhone;
	private String employeeAddress;
	@Column(unique = true)
	private String employeeEmail;
	private String employeePassword;
	private String employeeDesignation;
	private double employeeSalary;
	private char employeeGrade;
}
