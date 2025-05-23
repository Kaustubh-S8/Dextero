package com.EmployeeLeaveBal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeLeaveBal.dto.EmployeeRequestDto;
import com.EmployeeLeaveBal.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	
	@PostMapping("/add")
	public ResponseEntity<String> AddEmployee(@RequestBody EmployeeRequestDto empDto) {
		return empService.AddEmployee(empDto);
	}
	
	
	
}
