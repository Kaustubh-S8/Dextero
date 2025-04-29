package com.FetchDataFromIDs.exception;

public class ProductNotFound extends RuntimeException {
	
	private String message;
	
	public ProductNotFound(String message) {
		super(message);
		this.message=message;
	}
	
	@Override
    public String getMessage() {
        return this.message;
    }


}
