package com.EmployeeLeaveBal.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.EmployeeLeaveBal.dto.EmployeeRequestDto;
import com.EmployeeLeaveBal.entity.Employee;
import com.EmployeeLeaveBal.entity.LeaveRecord;
import com.EmployeeLeaveBal.exception.EmployeeNotFound;
import com.EmployeeLeaveBal.repository.EmployeeRepository;
import com.EmployeeLeaveBal.repository.LeaveRecordRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private LeaveRecordRepository lrRepo;

	public ResponseEntity<String> AddEmployee(EmployeeRequestDto empDto) {
		Optional<Employee> opt=empRepo.findByEmail(empDto.getEmployee().getEmail());
		if(opt.isPresent())throw new EmployeeNotFound("Employee is Already Present with email:- "+empDto.getEmployee().getEmail());
		Employee emp=new Employee();
		emp.setEmail(empDto.getEmployee().getEmail());
		emp.setName(empDto.getEmployee().getName());
		emp.setDesignation(empDto.getEmployee().getDesignation());
		emp.setTotalAollcatedleaves(empDto.getEmployee().getTotalAollcatedleaves());
		Employee saveEmp=empRepo.save(emp);
		
		
		List<LeaveRecord>list=empDto.getLeaveeRecords().stream().map(LeaveRecordDto ->{
			LeaveRecord lr=new LeaveRecord();
			lr.setEmployee(saveEmp);
			lr.setStartdate(LeaveRecordDto.getStartdate());
			lr.setEnddate(LeaveRecordDto.getEnddate());
			lr.setLeavestaken(LeaveRecordDto.getLeavestaken());
			return lr;
		}).collect(Collectors.toList());
		
		lrRepo.saveAll(list);
		
		return new ResponseEntity<String>("Employee is created successfully", HttpStatus.CREATED);
	}
	
	
}
