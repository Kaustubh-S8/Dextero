package com.MockRate.exception;

public class USERSNotFound extends RuntimeException{
	 private String message;

	    public USERSNotFound(String message) {
	        super(message); 
	        this.message = message;
	    }

	    @Override
	    public String getMessage() {
	        return this.message; 
	    }
}
