package com.FetchDataFromIDs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(ProductNotFound.class)
	public ResponseEntity<String> CatchProductNotFound(ProductNotFound message){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message.getMessage());
	}
}
