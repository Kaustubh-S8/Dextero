package com.Employeemanagement.repositroy;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Employeemanagement.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByEmail(String email);
	List<Employee> findByDepartmentId(Long deptid);
	
	Page<Employee> findAll(Pageable pageable);
}
