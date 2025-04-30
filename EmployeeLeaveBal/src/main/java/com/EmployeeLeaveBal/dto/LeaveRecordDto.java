package com.EmployeeLeaveBal.dto;

import java.time.LocalDate;

import com.EmployeeLeaveBal.entity.Employee;

import lombok.Data;

@Data
public class LeaveRecordDto {

	
	
	private Employee employee;
	
	private LocalDate startdate;
	private LocalDate enddate;
	private int leavestaken;
}
