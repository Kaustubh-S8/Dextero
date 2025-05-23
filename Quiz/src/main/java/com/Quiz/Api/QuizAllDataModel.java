package com.Quiz.Api;

import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class QuizAllDataModel {

	private Integer user_job_id;
	private Integer exam_mode_id;
	private Integer course_id;
	private Integer company_id;
	private Integer timelimit;
	private List<QuizModel> quiz_model;
	
	public Integer getUser_job_id() {
		return user_job_id;
	}
	public void setUser_job_id(Integer user_job_id) {
		this.user_job_id = user_job_id;
	}
	public Integer getExam_mode_id() {
		return exam_mode_id;
	}
	public void setExam_mode_id(Integer exam_mode_id) {
		this.exam_mode_id = exam_mode_id;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public Integer getTimelimit() {
		return timelimit;
	}
	public void setTimelimit(Integer timelimit) {
		this.timelimit = timelimit;
	}
	public List<QuizModel> getQuiz_model() {
		return quiz_model;
	}
	public void setQuiz_model(List<QuizModel> quiz_model) {
		this.quiz_model = quiz_model;
	}
	
}
