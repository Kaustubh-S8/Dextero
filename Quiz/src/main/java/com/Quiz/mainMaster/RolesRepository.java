package com.Quiz.mainMaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>{

	@Query("select r from Roles r where r.id=?1")
	Roles findRolesNameById(Integer id);

	@Query("select r from Roles r where r.company_id=?1")
	List<Roles> findRolesByCompanyId(Integer id);

}
