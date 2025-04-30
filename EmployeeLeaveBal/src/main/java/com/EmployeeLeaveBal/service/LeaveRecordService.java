package com.EmployeeLeaveBal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeLeaveBal.dto.LeaveBalanceDto;
import com.EmployeeLeaveBal.entity.Employee;
import com.EmployeeLeaveBal.exception.EmployeeNotFound;
import com.EmployeeLeaveBal.repository.EmployeeRepository;
import com.EmployeeLeaveBal.repository.LeaveRecordRepository;

@Service
public class LeaveRecordService {

	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private LeaveRecordRepository leaveRepo;
	
	public LeaveBalanceDto getLeaveBal(Integer id) {
		Employee emp=empRepo.findById(id).orElseThrow(()-> new EmployeeNotFound("Employee not found with id:- "+id));
		
		int leaveTaken=leaveRepo.sumofLeavesFromEmployeeId(emp.getId());
		
		return new LeaveBalanceDto(emp.getEmail(), emp.getName(), emp.getDesignation(), emp.getTotalAollcatedleaves(), leaveTaken);
	}

	
}
