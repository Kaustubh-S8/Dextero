package com.MockRate.entity;

import java.util.List;

import com.MockRate.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class USERS {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private Double userTotalScore;
	
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<MockInterview> mock;

	
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Rating> ratings;
	
	public USERS() {
	}


	public USERS(Integer uid, String name, String email, String password, Role role, Double userTotalScore,
			List<MockInterview> mock, List<Rating> ratings) {
		super();
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.userTotalScore = userTotalScore;
		this.mock = mock;
		this.ratings = ratings;
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


	public List<MockInterview> getMock() {
		return mock;
	}


	public void setMock(List<MockInterview> mock) {
		this.mock = mock;
	}


	public List<Rating> getRatings() {
		return ratings;
	}


	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	

}
