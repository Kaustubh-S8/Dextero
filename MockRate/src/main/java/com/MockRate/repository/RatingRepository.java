package com.MockRate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MockRate.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer>{

	  @Query("select r from Rating r where r.mock.mid = ?1")
	    List<Rating> findByMid(Integer mid);

	  @Query("select r from Rating r where r.users.uid = ?1")
	    List<Rating> findByUid(Integer uid);
}
