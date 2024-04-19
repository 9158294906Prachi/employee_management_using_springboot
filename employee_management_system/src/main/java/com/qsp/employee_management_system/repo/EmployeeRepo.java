package com.qsp.employee_management_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.employee_management_system.dto.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
	Employee findByEmployeeEmail(String email);
	
	Employee findByEmployeePhone(long phone);
	
	List<Employee> findByEmployeeName(String name);
	
	List<Employee> findByEmployeeAddress(String address);	
	
	List<Employee> findByEmployeeDesignation(String designation);

	@Query("Select e from Employee e where e.employeeSalary<=?1")
	List<Employee> findByEmployeeSalaryLessThan(double salary);
	
	List<Employee> findByEmployeeSalaryGreaterThan(double salary);
	
	List<Employee> findByEmployeeGrade(char grade);
	
//	@Query("Select e from Employee e where e.employeeSalary>=?1 and e.employeeSalary<=?2")
	@Query("Select e from Employee e where e.employeeSalary Between ?1 And ?2")
	List<Employee> findByEmployeeSalaryBetween(double salary1, double salary2);
}
