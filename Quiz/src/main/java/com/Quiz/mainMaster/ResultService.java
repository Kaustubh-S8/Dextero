package com.Quiz.mainMaster;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;



@Service
public class ResultService {
	
	@Autowired
	private ResultRepository repo;
	
	public List<Result> listAll() {
		return repo.findAll();
	}
	public void save(Result result) {
		repo.save(result);	
	}
	public List<Result> getTestResultsForCandidate(@Param("user_id") Integer user_id){
		return repo.getTestResultsForCandidate(user_id);
	}
	public Result findByUseridAndJobpostid(Integer userid,Integer jobpostid) {
		return repo.findByUseridAndJobpostid(userid,jobpostid);
	}
}
