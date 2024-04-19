package com.qsp.employee_management_system.util;

import lombok.Data;

@Data
public class ResponceStructure<T> {
	
	private String message;
	private int status;
	private T data;

}
