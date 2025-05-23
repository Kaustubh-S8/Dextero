package com.Quiz.Login.Session;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="system_login_user_session")
public class LoginSession {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	public String username;
	public String current_datetime;
	public String accesstoken;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCurrent_datetime() {
		return current_datetime;
	}
	public void setCurrent_datetime(String current_datetime) {
		this.current_datetime = current_datetime;
	}
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	
}
