package com.qsp.employee_management_system.exception;

public class EmailNotFoundException extends RuntimeException{

	private String message;
	
	public EmailNotFoundException(String message) {
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
}
