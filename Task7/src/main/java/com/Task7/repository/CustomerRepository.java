package com.Task7.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Task7.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Optional<Customer> findByEmail(String email);

	@Query("select Distinct c from Customer c left join fetch c.products p where "+ "lower(c.name) like lower(concat('%',:name,'%')) And "+
			"lower(c.email) like lower(concat('%',:email,'%'))")
	Page<Customer> findByEmailAndName(@Param ("name") String name, @Param("email")String email, Pageable pageable);
	
	@Query("select Distinct c from Customer c left join fetch c.products p where "+ "lower(c.name) like lower(concat('%',:name,'%')) ")
	Page<Customer> findByName(@Param ("name")String name, Pageable pageable);

	@Query("select Distinct c from Customer c left join fetch c.products p where "+"lower(c.email) like lower(concat('%',:email,'%'))")
	Page<Customer> findByEmail(@Param ("email")String email, Pageable pageable);

	@Query("select Distinct c from Customer c left join fetch c.products p where "+ "lower(c.name) like lower(concat('%',:search,'%')) OR "+
			"lower(c.email) like lower(concat('%',:search2,'%'))")
	Page<Customer> findByEmailOrName(@Param ("search")String search,@Param ("search2")String search2, Pageable pageable);

	
	

}
