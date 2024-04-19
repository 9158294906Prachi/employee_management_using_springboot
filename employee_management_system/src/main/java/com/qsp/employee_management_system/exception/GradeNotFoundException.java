package com.qsp.employee_management_system.exception;

public class GradeNotFoundException extends RuntimeException{

	private String message;

	public GradeNotFoundException(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

}
