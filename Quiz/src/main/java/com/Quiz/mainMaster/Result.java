package com.Quiz.mainMaster;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "results")
public class Result {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private Integer total_correct_answer;
    private Integer total_incorrect_answer;
    private Integer total_question;
    private String testname;
    private Integer college_id;
    private double percentage;
    private Integer attempted_question;
    private Integer not_attempted_question;
    private Integer company_id;
    private Integer job_post_id;
    private String currentdate;
	private String currenttime; 
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getTotal_correct_answer() {
		return total_correct_answer;
	}
	public void setTotal_correct_answer(Integer total_correct_answer) {
		this.total_correct_answer = total_correct_answer;
	}
	public Integer getTotal_incorrect_answer() {
		return total_incorrect_answer;
	}
	public void setTotal_incorrect_answer(Integer total_incorrect_answer) {
		this.total_incorrect_answer = total_incorrect_answer;
	}
	
	public Integer getTotal_question() {
		return total_question;
	}
	public void setTotal_question(Integer total_question) {
		this.total_question = total_question;
	}
	
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public Integer getCollege_id() {
		return college_id;
	}
	public void setCollege_id(Integer college_id) {
		this.college_id = college_id;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public Integer getAttempted_question() {
		return attempted_question;
	}
	public void setAttempted_question(Integer attempted_question) {
		this.attempted_question = attempted_question;
	}
	public Integer getNot_attempted_question() {
		return not_attempted_question;
	}
	public void setNot_attempted_question(Integer not_attempted_question) {
		this.not_attempted_question = not_attempted_question;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public Integer getJob_post_id() {
		return job_post_id;
	}
	public void setJob_post_id(Integer job_post_id) {
		this.job_post_id = job_post_id;
	}
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	public String getCurrenttime() {
		return currenttime;
	}
	public void setCurrenttime(String currenttime) {
		this.currenttime = currenttime;
	}

	
	
}
