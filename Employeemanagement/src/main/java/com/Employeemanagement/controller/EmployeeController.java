package com.Employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.Employeemanagement.dto.EmployeeDto;
import com.Employeemanagement.entity.Employee;
import com.Employeemanagement.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/register/deptid/{id}")
	public ResponseEntity<String> Registeremp(@RequestBody Employee emp,@PathVariable Long id) {
		
		return empService.RegisterEmp(emp,id);
	}
	@PostMapping("/login")
	public ResponseEntity<String> loginemp(@RequestBody EmployeeDto empdto) {
		return empService.loginemp(empdto);
	}
	@GetMapping("/allemp")
	public List<EmployeeDto> getallemployee(@RequestParam (defaultValue="0")int page,@RequestParam (defaultValue="10")int size) {
		return empService.getallemp(page, size);
	}
	@GetMapping("/allempbydeptid/deptid/{id}")
	public List<EmployeeDto> getallempbydeptid(@PathVariable Long id) {
		return empService.getallempbydeptid(id);
	}
	
	
	
}
