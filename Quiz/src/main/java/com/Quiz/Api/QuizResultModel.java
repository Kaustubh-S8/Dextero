package com.Quiz.Api;

import org.springframework.stereotype.Component;

@Component
public class QuizResultModel {
	
	private String QuestionTitle;
	private String selectedOption;
	private String answer;
	private boolean isCorrect;
	
	
	public String getQuestionTitle() {
		return QuestionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		QuestionTitle = questionTitle;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	
}
