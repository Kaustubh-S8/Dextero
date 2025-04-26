package com.MockRate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rid;

	private Integer communication;
	
	private Integer confidence;
	
	private Integer content;

	private Integer interaction;
	
	private Integer liveliness;
	
	private Double totalScore;
	
	@ManyToOne
	private USERS users;
	
	@ManyToOne
	private MockInterview mock;
	
	public Rating() {
		// TODO Auto-generated constructor stub
	}

	public Rating(Integer rid, Integer communication, Integer confidence, Integer content, Integer interaction,
			Integer liveliness, Double totalScore, USERS users, MockInterview mock) {
		super();
		this.rid = rid;
		this.communication = communication;
		this.confidence = confidence;
		this.content = content;
		this.interaction = interaction;
		this.liveliness = liveliness;
		this.totalScore = totalScore;
		this.users = users;
		this.mock = mock;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getCommunication() {
		return communication;
	}

	public void setCommunication(Integer communication) {
		this.communication = communication;
	}

	public Integer getConfidence() {
		return confidence;
	}

	public void setConfidence(Integer confidence) {
		this.confidence = confidence;
	}

	public Integer getContent() {
		return content;
	}

	public void setContent(Integer content) {
		this.content = content;
	}

	public Integer getInteraction() {
		return interaction;
	}

	public void setInteraction(Integer interaction) {
		this.interaction = interaction;
	}

	public Integer getLiveliness() {
		return liveliness;
	}

	public void setLiveliness(Integer liveliness) {
		this.liveliness = liveliness;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public USERS getUsers() {
		return users;
	}

	public void setUsers(USERS users) {
		this.users = users;
	}

	public MockInterview getMock() {
		return mock;
	}

	public void setMock(MockInterview mock) {
		this.mock = mock;
	}

	
	
}
