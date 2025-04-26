package com.MockRate.dto;

import com.MockRate.enums.Role;

public class USERSDto {
	private Integer uid;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private Role role;
	
	private Double userTotalScore;
	
	public USERSDto() {
		// TODO Auto-generated constructor stub
	}

	public USERSDto(Integer uid, String name, String email, String password, Role role, Double userTotalScore) {
		super();
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.userTotalScore = userTotalScore;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Double getUserTotalScore() {
		return userTotalScore;
	}

	public void setUserTotalScore(Double userTotalScore) {
		this.userTotalScore = userTotalScore;
	}
	
}
