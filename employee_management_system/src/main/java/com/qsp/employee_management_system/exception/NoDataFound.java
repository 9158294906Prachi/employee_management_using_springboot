package com.qsp.employee_management_system.exception;

public class NoDataFound extends RuntimeException{

	private String message;
	
	public NoDataFound(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}
}
