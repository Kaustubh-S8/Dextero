package com.MockRate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MockRate.entity.USERS;

@Repository
public interface USERSRepository extends JpaRepository<USERS, Integer>{

	Optional<USERS> findByEmail(String email);

}
