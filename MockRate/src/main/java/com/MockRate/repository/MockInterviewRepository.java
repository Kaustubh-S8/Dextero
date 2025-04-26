package com.MockRate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MockRate.entity.MockInterview;

@Repository
public interface MockInterviewRepository extends JpaRepository<MockInterview, Integer>{

	@Query("select m from MockInterview m where m.users.uid = ?1")
	List<MockInterview> findByUid(Integer uid);

}
