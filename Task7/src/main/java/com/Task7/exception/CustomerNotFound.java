package com.Task7.exception;

public class CustomerNotFound extends RuntimeException {
	private String message;
	
	public CustomerNotFound(String message) {
		super(message);
		this.message=message;
	}
	@Override
	public String getMessage() {
		return this.message;
	}

}
