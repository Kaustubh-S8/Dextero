package com.Quiz.Login;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginUserAuthorityRepository  extends JpaRepository < LoginUserAuthority , Integer >{

	@Query("select a.authority_id from LoginUserAuthority a where a.user_id = ?1")
	  Integer GetAuthorityID(Integer user_id);
	
	@Query("select count(a.user_id) from LoginUserAuthority a where a.authority_id = ?1")
	  Integer GetCountID(Integer authority_id);
	
	@Query("select a from LoginUserAuthority a where a.user_id = ?1")
	 public LoginUserAuthority findByUserID(Integer user_id);
}
