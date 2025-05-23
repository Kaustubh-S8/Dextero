package com.Quiz.mainMaster;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizResultService {

	@Autowired
	private QuizResultRepository repo;
	
	public void save(QuizResult std) {
		repo.save(std);
	}
	public List<QuizResult> listAllByUseridAndResultid(Integer Userid, Integer Resultid){
		return repo.findAllByUseridAndResultid(Userid,Resultid);
	}
	public void saveAll(List<QuizResult> std) {
		repo.saveAll(std);
	}
	
}
