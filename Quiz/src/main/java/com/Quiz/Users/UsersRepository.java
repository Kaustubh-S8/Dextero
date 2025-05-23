package com.Quiz.Users;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByEmail(String email);
	
	@Query(nativeQuery = true, value ="select id FROM Users where email=?1")
	public Integer Update(String emailid);
}
