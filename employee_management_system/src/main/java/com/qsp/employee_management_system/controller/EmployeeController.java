package com.qsp.employee_management_system.controller;

import java.util.List;

import javax.naming.NameNotFoundException;
import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.employee_management_system.dto.Employee;
import com.qsp.employee_management_system.service.EmployeeService;
import com.qsp.employee_management_system.util.ResponceStructure;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping("/signup")
	public ResponseEntity<ResponceStructure<Employee>> saveEmployee(@RequestBody Employee employee) {
		return service.saveEmployee(employee);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponceStructure<Employee>> loginEmployee(@RequestParam String email,String password) {
		return service.loginEmployee(email, password);
	}
	
	@GetMapping("/find")
	public ResponseEntity<ResponceStructure<Employee>> findEmployee(int id) {
		return service.findEmployee(id);
	}
	
	@GetMapping("/find/all")
	public ResponseEntity<ResponceStructure<List<Employee>>> findAllEmployee() {
		return service.findAllEmployee();
	}
	
	@PostMapping("/save/all")
	public ResponseEntity<ResponceStructure<List<Employee>>> saveAllEmployee(@RequestBody List<Employee> list) {
		return service.saveAllEmployee(list);
	}
	
	@GetMapping("/find/phone")
	public ResponseEntity<ResponceStructure<Employee>> findByPhone(long phone) {
		return service.findByPhone(phone);
	}
	
	@GetMapping("/find/email")
	public ResponseEntity<ResponceStructure<Employee>> findByEmail(String email) {
		return service.findByEmail(email);
	}
	
	@GetMapping("/find/name")
	public ResponseEntity<ResponceStructure<List<Employee>>> findByName(String name) {
		return service.findByName(name);
	}
	
	@GetMapping("/find/address")
	public ResponseEntity<ResponceStructure<List<Employee>>> findByAddress(String address) {
		return service.findByAddress(address);
	}
	
	@GetMapping("/find/designation")
	public ResponseEntity<ResponceStructure<List<Employee>>> findByDesignation(String designation) {
		return service.findByDesignation(designation);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponceStructure<Employee>> updateEmployee(int id,@RequestBody Employee employee) {
		return service.updateEmployee(id, employee);
	}
	
	@PatchMapping("/update/salary")
	public ResponseEntity<ResponceStructure<Employee>> updateBySalary(int id,double salary) {
		return service.updateSalary(id, salary);
	}
	
	@PatchMapping("/update/designation")
	public ResponseEntity<ResponceStructure<Employee>> updateByDesignation(int id,String designation) {
		return service.updateByDesignation(id, designation);
	}
	
	@PatchMapping("/update/phone")
	public ResponseEntity<ResponceStructure<Employee>> updateByPhone(int id,long phone) {
		return service.updateByPhone(id, phone);
	}
	
	@PatchMapping("/update/email")
	public ResponseEntity<ResponceStructure<Employee>> updateByEmail(int id,String email) {
		return service.updateByEmail(id, email);
	}
	
	@PatchMapping("/update/address")
	public ResponseEntity<ResponceStructure<Employee>> updateByAddress(int id,String address) {
		return service.updateByAddress(id, address);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponceStructure<Employee>> deleteEmployee(int id) {
		return service.deleteEmployee(id);
	}
	
	@DeleteMapping("/delete/phone")
	public ResponseEntity<ResponceStructure<Employee>> deleteByPhone(long phone) {
		return service.deleteByPhone(phone);
	}
	
	@DeleteMapping("/delete/email")
	public ResponseEntity<ResponceStructure<Employee>> deleteByEmail(String email) {
		return service.deleteByEmail(email);
	}
	
	@GetMapping("/find/lessthan")
	public ResponseEntity<ResponceStructure<List<Employee>>> findBySalaryLessThan(double salary) {
		return service.findBySalaryLessThan(salary);
	}
	
	@GetMapping("/find/greaterthan")
	public ResponseEntity<ResponceStructure<List<Employee>>> findBySalaryGreaterThan(double salary) {
		return service.findBySalaryGreaterThan(salary);
	}
	
	@GetMapping("/find/grade")
	public ResponseEntity<ResponceStructure<List<Employee>>> findByGrade(char grade) {
		return service.findByGrade(grade);
	}
	
	@GetMapping("/find/between")
	public ResponseEntity<ResponceStructure<List<Employee>>> findBySalaryBetween(double salary1, double salary2) {
		return service.findBySalaryBetween(salary1, salary2);
	}
}
