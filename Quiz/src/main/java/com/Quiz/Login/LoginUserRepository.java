package com.Quiz.Login;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface LoginUserRepository extends JpaRepository<LoginUser, Integer>{

	public Optional<LoginUser> findByUsername(String username);
	
	@Query("select l.login_user_id from LoginUser l where l.username = ?1")
	Integer GetAuthorityID(String username);
	
	@Modifying
	@Query("update LoginUser l set l.password=?2, l.apassword=?3 where l.username =?1")
	public void UpdatePasswordByUsername(String username, String password, String apassword);
	
	@Query("select l from LoginUser l where l.username = ?1")
	public List<LoginUser> getUserName(String username);
	
	@Query("select count(l) from LoginUser l where l.username = ?1")
	public int findByUser(String username);
	
	@Query("select l from LoginUser l where l.username = ?1")
	public LoginUser findByUserName(String username);
	
	@Query(value ="select status from LoginUser where username = ?1")
	public String getStatusByUsernamePassword(String username);

}