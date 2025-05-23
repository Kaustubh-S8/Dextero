package com.Quiz.Api;

import org.springframework.stereotype.Component;

@Component
public class APIMessageModel {

	private Integer status_code;
	private String status_message; 
	private String message;
	private String accesstoken;
	
	public Integer getStatus_code() {
		return status_code;
	}
	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}
	public String getStatus_message() {
		return status_message;
	}
	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	
}
