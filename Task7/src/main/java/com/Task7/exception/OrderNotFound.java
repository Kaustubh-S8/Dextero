package com.Task7.exception;

public class OrderNotFound extends RuntimeException {
	private String message;
	 public OrderNotFound(String message) {
		super(message);
		this.message=message;
	}
	 
	 @Override
	 public String getMessage() {
		 return this.message;
	 }

}
