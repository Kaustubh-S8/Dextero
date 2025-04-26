package com.BasicUserManagment.exception;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;


@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<String> catchUserNotFound(UserNotFound message){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message.getMessage());
	}

	 @ExceptionHandler(ExpiredJwtException.class)
	    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException ex) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token has expired.");
	    }

	    @ExceptionHandler(SignatureException.class)
	    public ResponseEntity<?> handleInvalidJwtException(SignatureException ex) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT token.");
	    }

	    @ExceptionHandler(AccessDeniedException.class)
	    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: You don't have required permission.");
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getFieldErrors().forEach(error -> {
	            errors.put(error.getField(), error.getDefaultMessage());
	        });
	        return ResponseEntity.badRequest().body(errors);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<?> handleGenericException(Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	    }
}
