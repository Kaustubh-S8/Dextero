package com.EmployeeLeaveBal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.EmployeeLeaveBal.entity.LeaveRecord;

@Repository
public interface LeaveRecordRepository extends JpaRepository<LeaveRecord, Integer>{

	
	@Query("select COALESCE(SUM(lr.leavestaken),0) from LeaveRecord lr "+
			" where lr.employee.id=:id ")
	int sumofLeavesFromEmployeeId(@Param("id") Integer id);
}
