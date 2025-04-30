package com.EmployeeLeaveBal.dto;

import java.util.List;

import com.EmployeeLeaveBal.entity.Employee;

import lombok.Data;

@Data
public class EmployeeRequestDto {

	private Employee employee;
	private List<LeaveRecordDto> leaveeRecords;
}
