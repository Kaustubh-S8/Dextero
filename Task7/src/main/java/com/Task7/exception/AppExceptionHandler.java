package com.Task7.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(CustomerNotFound.class)
	public ResponseEntity<String> catchCustomerNotFound(CustomerNotFound message){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message.getMessage());
		
	}
	@ExceptionHandler(OrderNotFound.class)
	public ResponseEntity<String> catchOrderNotFound(OrderNotFound message){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message.getMessage());
		
	}
	
}
