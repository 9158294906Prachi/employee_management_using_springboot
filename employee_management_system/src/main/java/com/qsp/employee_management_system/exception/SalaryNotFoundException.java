package com.qsp.employee_management_system.exception;

public class SalaryNotFoundException extends RuntimeException {

	private String message;

	public SalaryNotFoundException(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

}
