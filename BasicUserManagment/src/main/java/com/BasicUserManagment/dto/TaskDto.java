package com.BasicUserManagment.dto;

import java.util.Date;

public class TaskDto {
	
	private Long id;
	private String title;
	private String description;
    private Date dueDate;
    private boolean completed;
    private Long userId;
    
    
    public TaskDto() {
    }


	public TaskDto(Long id, String title, String description, Date dueDate, boolean completed, Long userId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.completed = completed;
		this.userId=userId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public boolean isCompleted() {
		return completed;
	}


	public void setCompleted(boolean completed) {
		this.completed = completed;
	}


	public Long getUserid() {
		return userId;
	}


	public void setUserid(Long userId) {
		this.userId = userId;
	}

	
    
}