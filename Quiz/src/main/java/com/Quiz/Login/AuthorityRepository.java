package com.Quiz.Login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorityRepository extends JpaRepository < Authority , Integer > {

	@Query("select l.authority from Authority l where l.authority_id = ?1")
	public String getAuthorityNameById(Integer authority_id);

	@Query("select l.authority from Authority l where l.authority=?1")
	public Optional<Authority> findByAuthority(String authority);
	
	
}
