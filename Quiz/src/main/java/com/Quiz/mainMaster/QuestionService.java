package com.Quiz.mainMaster;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository repo;
	
	public void save(Question question) {
	      repo.save(question);
	 }
	public List<Question> listAll() {
	      return repo.findAll();
	 }
	public Question get(int id) {
		  return repo.findById(id).get();
	 }
	public Integer getTotalQuestionCount(Integer course_id, Integer company_id) {
		return repo.getTotalQuestionCount(course_id,company_id);
	}
	 public boolean questionExists(String title, Integer course_id) {
	        return repo.existsQuestion(title, course_id);
	    }
	public List<Question> findAllQuestionsByCourseid(Integer course_id){
		return repo.findAllQuestionsByCourseid(course_id);
	}
	public List<Question> findAllQuestionsByIdAndCourseid(List<Integer> selectedQuestionIds,Integer course_id) {
		return repo.findAllByIdInAndCourseid(selectedQuestionIds,course_id);
	}
	 

}
