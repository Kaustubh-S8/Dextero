package com.Quiz.mainMaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer>{

	@Query("select r from Result r where r.user_id=?1")
	List<Result> getTestResultsForCandidate(Integer user_id);

	@Query("select r from Result r where r.user_id=?1 AND r.job_post_id=?2")
	Result findByUseridAndJobpostid(Integer userid, Integer jobpostid);
	

}
