package com.qsp.employee_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.employee_management_system.dto.Employee;
import com.qsp.employee_management_system.repo.EmployeeRepo;

@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepo repo;

	public Employee saveEmployee(Employee employee) {
		return repo.save(employee);
	}

	public String loginEmployee(String email, String password) {
		Employee employee = repo.findByEmployeeEmail(email);
		if (employee == null) {
			return null;
		} else {
			if (employee.getEmployeePassword().equals(password)) {
				return "yes";
			} else {
				return "no";
			}
		}
	}

	public Employee findEmployee(int id) {
		Optional<Employee> employee=repo.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		} else {
			return null;
		}
	}
	
	public List<Employee> findAllEmployee() {
		return repo.findAll();
	}
	
	public List<Employee> saveAllEmployee(List<Employee> list) {
		return repo.saveAll(list);
	}
	
	public Employee findByPhone(long phone) {
		return repo.findByEmployeePhone(phone);
	}
	
	public Employee findByEmail(String email) {
		return repo.findByEmployeeEmail(email);
	}
	
	public List<Employee> findByName(String name) {
		return repo.findByEmployeeName(name);
	}
	
	public List<Employee> findByAddress(String address) {
		return repo.findByEmployeeAddress(address);
	}
	
	public List<Employee> findByDesignation(String designation) {
		return repo.findByEmployeeDesignation(designation);
	}
	
	public Employee deleteEmployee(int id) {
		Optional<Employee> optional=repo.findById(id);
		if (optional.isPresent()) {
			repo.deleteById(id);
			return optional.get();
		} else {
			return null;
		}
	}
	
	public Employee deleteByPhone(long phone) {
		Employee employee= findByPhone(phone);
		if (employee!=null) {
			repo.delete(employee);
			return employee;
		} else {
			return null;
		}
	}
	
	public Employee deleteByEmail(String email) {
		Employee employee= findByEmail(email);
		if (employee!=null) {
			repo.delete(employee);
			return employee;
		} else {
			return null;
		}
	}
	
	public List<Employee> findBySalaryLessThan(double salary) {
		return repo.findByEmployeeSalaryLessThan(salary);
	}
	
	public List<Employee> findBySalaryGreaterThan(double salary) {
		return repo.findByEmployeeSalaryGreaterThan(salary);
	}
	
	public List<Employee> findByGrade(char grade) {
		return repo.findByEmployeeGrade(grade);
	}
	
	public List<Employee> findBySalaryBetween(double salary1, double salary2) {
		return repo.findByEmployeeSalaryBetween(salary1, salary2);
	}
}
