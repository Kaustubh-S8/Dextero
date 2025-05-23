package com.Quiz.Login.Session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginSessionRepository extends JpaRepository < LoginSession , Integer > {

	@Query("select count(l.id) from LoginSession l where l.username = ?1")
	Integer GetCountID(String username);
	
	@Query("select l from LoginSession l where l.username = ?1")
	LoginSession FindByUserName(String username);
	
	@Query("select l from LoginSession l where l.accesstoken = ?1")
	LoginSession FindByAccessToken(String accesstoken);
	
	@Query("select count(l.id) from LoginSession l where l.accesstoken = ?1")
	Integer GetCountAccessToken(String accesstoken);
}
