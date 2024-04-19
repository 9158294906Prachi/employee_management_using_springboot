package com.qsp.employee_management_system.exception;

public class PhoneNumberNotFoundException extends RuntimeException{
	
private String message;
	
	public PhoneNumberNotFoundException(String message) {
		this.message=message;
	}
	public String getMessage() {
		return message;
	}

}
