package com.EmployeeLeaveBal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeLeaveBal.dto.LeaveBalanceDto;
import com.EmployeeLeaveBal.service.LeaveRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class LeaveRecordController {

	@Autowired
	private LeaveRecordService lrService;
	
	
	@GetMapping("/getleaves/empid/{id}")
	public ResponseEntity<LeaveBalanceDto> getMethodName(@PathVariable Integer id) {
		LeaveBalanceDto leavebal=lrService.getLeaveBal(id);
		
		return ResponseEntity.ok(leavebal);
	}
	
	
	
}
