package com.Employeemanagement.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employeemanagement.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
