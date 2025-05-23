package com.Quiz.mainMaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Integer>{

	@Query("select qs from QuizResult qs where qs.userid=?1 And qs.resultid=?2")
	List<QuizResult> findAllByUseridAndResultid(Integer userid, Integer resultid);

}
