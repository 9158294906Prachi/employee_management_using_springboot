package com.qsp.employee_management_system.exception;

public class DesignationNotFoundException extends RuntimeException{
	
	private String message;

	public DesignationNotFoundException(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

}
