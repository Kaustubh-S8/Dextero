package com.Quiz.mainMaster;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quiz_results")
public class QuizResult {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userid;
    private Integer questionid;
    private String seletedOption;
    private boolean isCorrect;
    private Integer resultid;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer user_id) {
		this.userid = user_id;
	}
	public Integer getQuestionid() {
		return questionid;
	}
	public void setQuestionid(Integer questionid) {
		this.questionid = questionid;
	}
	public String getSeletedOption() {
		return seletedOption;
	}
	public void setSeletedOption(String seletedOption) {
		this.seletedOption = seletedOption;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	public Integer getResultid() {
		return resultid;
	}
	public void setResultid(Integer resultid) {
		this.resultid = resultid;
	}
	
    
    
    
    
}
