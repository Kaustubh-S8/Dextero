package com.EmployeeLeaveBal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(EmployeeNotFound.class)
	public ResponseEntity<String> catchEmployeeNotFound (EmployeeNotFound message){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message.getMessage());
	}
}
