package com.qsp.employee_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.employee_management_system.dao.EmployeeDao;
import com.qsp.employee_management_system.dto.Employee;
import com.qsp.employee_management_system.exception.AddressNotFoundException;
import com.qsp.employee_management_system.exception.DesignationNotFoundException;
import com.qsp.employee_management_system.exception.EmailNotFoundException;
import com.qsp.employee_management_system.exception.EmployeeNameNotFoundException;
import com.qsp.employee_management_system.exception.GradeNotFoundException;
import com.qsp.employee_management_system.exception.IdNotFoundException;
import com.qsp.employee_management_system.exception.NoDataFound;
import com.qsp.employee_management_system.exception.PhoneNumberNotFoundException;
import com.qsp.employee_management_system.exception.SalaryNotFoundException;
import com.qsp.employee_management_system.util.ResponceStructure;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;

	public ResponseEntity<ResponceStructure<Employee>> saveEmployee(Employee employee) {
		double salary = employee.getEmployeeSalary();
		if (salary <= 10000) {
			employee.setEmployeeGrade('D');
		} else if (salary > 10000 && salary <= 20000) {
			employee.setEmployeeGrade('C');
		} else if (salary > 20000 && salary <= 40000) {
			employee.setEmployeeGrade('B');
		} else {
			employee.setEmployeeGrade('A');
		}

		ResponceStructure<Employee> structure = new ResponceStructure<>();
		structure.setMessage("signup success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveEmployee(employee));
		return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponceStructure<Employee>> loginEmployee(String email, String password) {
		String message =dao.loginEmployee(email, password);
		if (message.equals("yes")) {
			ResponceStructure<Employee> structure = new ResponceStructure<>();
			structure.setMessage("login successful!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.findByEmail(email));
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.OK);			
		} else if (message.equals("no")) {
			ResponceStructure<Employee> structure = new ResponceStructure<>();
			structure.setMessage("Invalid password!");
			structure.setStatus(HttpStatus.BAD_REQUEST.value());
			structure.setData(null);
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.BAD_REQUEST);			
		} else {
			throw new EmailNotFoundException("Employee with email " + email + " not found");
		}
	}

	public ResponseEntity<ResponceStructure<Employee>> findEmployee(int id) {
		Employee employee = dao.findEmployee(id);
		ResponceStructure<Employee> structure = new ResponceStructure<>();
		if (employee != null) {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Employee with id " + id + " not found");
		}
	}

	public ResponseEntity<ResponceStructure<List<Employee>>> findAllEmployee() {
		List<Employee> employee = dao.findAllEmployee();
		ResponceStructure<List<Employee>> structure = new ResponceStructure<>();
		if (employee.isEmpty()) {
			throw new NoDataFound("Data is not present");
		} else {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponceStructure<List<Employee>>> saveAllEmployee(List<Employee> list) {
		for (Employee employee : list) {
			double salary = employee.getEmployeeSalary();
			if (salary <= 10000) {
				employee.setEmployeeGrade('D');
			} else if (salary > 10000 && salary <= 20000) {
				employee.setEmployeeGrade('C');
			} else if (salary > 20000 && salary <= 40000) {
				employee.setEmployeeGrade('B');
			} else {
				employee.setEmployeeGrade('A');
			}
		}
		ResponceStructure<List<Employee>> structure = new ResponceStructure<>();
		structure.setMessage("all signup success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveAllEmployee(list));
		return new ResponseEntity<ResponceStructure<List<Employee>>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponceStructure<Employee>> findByPhone(long phone) {
		Employee employee = dao.findByPhone(phone);
		ResponceStructure<Employee> structure = new ResponceStructure<>();
		if (employee != null) {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {
			throw new PhoneNumberNotFoundException("Employee with phone number " + phone + " not found");
		}
	}

	public ResponseEntity<ResponceStructure<Employee>> findByEmail(String email) {
		Employee employee = dao.findByEmail(email);
		ResponceStructure<Employee> structure = new ResponceStructure<>();
		if (employee != null) {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {
			throw new EmailNotFoundException("Employee with email " + email + " not found");
		}
	}

	public ResponseEntity<ResponceStructure<List<Employee>>> findByName(String name) {
		List<Employee> employee = dao.findByName(name);
		ResponceStructure<List<Employee>> structure = new ResponceStructure<>();
		if (employee.isEmpty()) {
			throw new EmployeeNameNotFoundException("Employee with name " + name + " not found");
		} else {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponceStructure<List<Employee>>> findByAddress(String address) {
		List<Employee> employee = dao.findByAddress(address);
		ResponceStructure<List<Employee>> structure = new ResponceStructure<>();
		if (employee.isEmpty()) {
			throw new AddressNotFoundException("Employee with address " + address + " not found");
		} else {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponceStructure<List<Employee>>> findByDesignation(String designation) {
		List<Employee> employee = dao.findByDesignation(designation);
		ResponceStructure<List<Employee>> structure = new ResponceStructure<>();
		if (employee.isEmpty()) {
			throw new DesignationNotFoundException("Employee with designation " + designation + " not found");
		} else {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponceStructure<Employee>> updateEmployee(int id, Employee employee) {
		Employee dbEmployee = dao.findEmployee(id);
		ResponceStructure<Employee> structure = new ResponceStructure<>();
		if (dbEmployee != null) {
			employee.setEmployeeId(id);
			double salary = employee.getEmployeeSalary();
			if (salary <= 10000) {
				employee.setEmployeeGrade('D');
			} else if (salary > 10000 && salary <= 20000) {
				employee.setEmployeeGrade('C');
			} else if (salary > 20000 && salary <= 40000) {
				employee.setEmployeeGrade('B');
			} else {
				employee.setEmployeeGrade('A');
			}
			structure.setMessage("successfully updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(employee));
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.OK);

		} else {
			throw new IdNotFoundException("Employee with id " + id + " not found");
		}

	}

	public ResponseEntity<ResponceStructure<Employee>> updateSalary(int id, double newsalary) {
		Employee employee = dao.findEmployee(id);
		ResponceStructure<Employee> structure = new ResponceStructure<>();
		if (employee != null) {
			employee.setEmployeeId(id);
			employee.setEmployeeSalary(newsalary);
			double salary = employee.getEmployeeSalary();
			if (salary <= 10000) {
				employee.setEmployeeGrade('D');
			} else if (salary > 10000 && salary <= 20000) {
				employee.setEmployeeGrade('C');
			} else if (salary > 20000 && salary <= 40000) {
				employee.setEmployeeGrade('B');
			} else {
				employee.setEmployeeGrade('A');
			}
			structure.setMessage("successfully updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(employee));
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.OK);

		} else {
			throw new IdNotFoundException("Employee with id " + id + " not found");
		}
	}

	public ResponseEntity<ResponceStructure<Employee>> updateByDesignation(int id, String designation) {
		Employee employee = dao.findEmployee(id);
		ResponceStructure<Employee> structure = new ResponceStructure<>();
		if (employee != null) {
			employee.setEmployeeId(id);
			employee.setEmployeeDesignation(designation);
			structure.setMessage("designation updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(employee));
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.OK);

		} else {
			throw new IdNotFoundException("Employee with id " + id + " not found");
		}
	}

	public ResponseEntity<ResponceStructure<Employee>> updateByPhone(int id, long phone) {
		Employee employee = dao.findEmployee(id);
		ResponceStructure<Employee> structure = new ResponceStructure<>();
		if (employee != null) {
			employee.setEmployeeId(id);
			employee.setEmployeePhone(phone);
			structure.setMessage("phone number updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(employee));
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.OK);

		} else {
			throw new IdNotFoundException("Employee with id " + id + " not found");
		}
	}

	public ResponseEntity<ResponceStructure<Employee>> updateByEmail(int id, String email) {
		Employee employee = dao.findEmployee(id);
		ResponceStructure<Employee> structure = new ResponceStructure<>();
		if (employee != null) {
			employee.setEmployeeId(id);
			employee.setEmployeeEmail(email);
			structure.setMessage("phone number updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(employee));
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.OK);

		} else {
			throw new IdNotFoundException("Employee with id " + id + " not found");
		}
	}

	public ResponseEntity<ResponceStructure<Employee>> updateByAddress(int id, String address) {
		Employee employee = dao.findEmployee(id);
		if (employee != null) {
			employee.setEmployeeId(id);
			employee.setEmployeeAddress(address);
			ResponceStructure<Employee> structure = new ResponceStructure<>();
			structure.setMessage("phone number updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(employee));
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.OK);

		} else {
			throw new IdNotFoundException("Employee with id " + id + " not found");
		}
	}

	public ResponseEntity<ResponceStructure<Employee>> deleteEmployee(int id) {
		Employee employee = dao.deleteEmployee(id);
		if (employee != null) {
			ResponceStructure<Employee> structure = new ResponceStructure<>();
			structure.setMessage("successfully deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.OK);

		} else {
			throw new IdNotFoundException("Employee with id " + id + " not found");
		}
	}

	public ResponseEntity<ResponceStructure<Employee>> deleteByPhone(long phone) {
		Employee employee = dao.deleteByPhone(phone);
		if (employee != null) {
			ResponceStructure<Employee> structure = new ResponceStructure<>();
			structure.setMessage("successfully deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.OK);

		} else {
			throw new PhoneNumberNotFoundException("Employee with phone number " + phone + " not found");
		}
	}

	public ResponseEntity<ResponceStructure<Employee>> deleteByEmail(String email) {
		Employee employee = dao.deleteByEmail(email);
		if (employee != null) {
			ResponceStructure<Employee> structure = new ResponceStructure<>();
			structure.setMessage("successfully deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<Employee>>(structure, HttpStatus.OK);

		} else {
			throw new EmailNotFoundException("Employee with email " + email + " not found");
		}
	}

	public ResponseEntity<ResponceStructure<List<Employee>>> findBySalaryLessThan(double salary) {
		List<Employee> employee = dao.findBySalaryLessThan(salary);
		if (employee.isEmpty()) {
			throw new SalaryNotFoundException("Employee with salary " + salary + " not found");
		} else {
			ResponceStructure<List<Employee>> structure = new ResponceStructure<>();
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<List<Employee>>>(structure, HttpStatus.OK);

		}
	}

	public ResponseEntity<ResponceStructure<List<Employee>>> findBySalaryGreaterThan(double salary) {
		List<Employee> employee = dao.findBySalaryGreaterThan(salary);
		if (employee.isEmpty()) {
			throw new SalaryNotFoundException("Employee with salary " + salary + " not found");
		} else {
			ResponceStructure<List<Employee>> structure = new ResponceStructure<>();
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<List<Employee>>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponceStructure<List<Employee>>> findByGrade(char grade) {
		List<Employee> employee = dao.findByGrade(grade);
		if (employee.isEmpty()) {
			throw new GradeNotFoundException("Employee with grade " + grade + " not found");
		} else {
			ResponceStructure<List<Employee>> structure = new ResponceStructure<>();
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<List<Employee>>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponceStructure<List<Employee>>> findBySalaryBetween(double salary1, double salary2) {
		List<Employee> employee = dao.findBySalaryBetween(salary1, salary2);
		if (employee.isEmpty()) {
			throw new SalaryNotFoundException(
					"Employee with salary between " + salary1 + " and " + salary2 + " not found");
		} else {
			ResponceStructure<List<Employee>> structure = new ResponceStructure<>();
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponceStructure<List<Employee>>>(structure, HttpStatus.OK);
		}
	}

}
