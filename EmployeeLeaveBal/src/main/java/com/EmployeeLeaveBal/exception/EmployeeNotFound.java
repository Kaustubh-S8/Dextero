package com.EmployeeLeaveBal.exception;


public class EmployeeNotFound extends RuntimeException{

	private String message;
	
	public EmployeeNotFound(String message) {
		super(message);
		this.message=message;
	}
	@Override
	public String getMessage() {
		return this.message;
	}
}
