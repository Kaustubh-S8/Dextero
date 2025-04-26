package com.MockRate.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MockInterview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mid;
	
	private String topic;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private USERS users;
	
	private Double mockAvgScore;
	
	public MockInterview() {
	}

	public MockInterview(Integer mid, String topic, USERS users, Double mockAvgScore) {
		super();
		this.mid = mid;
		this.topic = topic;
		this.users = users;
		this.mockAvgScore = mockAvgScore;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public USERS getUsers() {
		return users;
	}

	public void setUsers(USERS users) {
		this.users = users;
	}

	public Double getMockAvgScore() {
		return mockAvgScore;
	}

	public void setMockAvgScore(Double mockAvgScore) {
		this.mockAvgScore = mockAvgScore;
	}

	
}
