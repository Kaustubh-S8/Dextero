package com.Employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employeemanagement.dto.DepartmentDto;
import com.Employeemanagement.entity.Department;
import com.Employeemanagement.service.DepartmentService;


@RestController
@RequestMapping("/dept")
public class DepartmentController {
	
	@Autowired
	private DepartmentService deptService;

	@GetMapping("/getalldept")
	public List<DepartmentDto> getalldept() {
		return deptService.getalldept();
	}
	@PostMapping("/adddept")
	public Department postMethodName(@RequestBody Department dept) {
		return deptService.createdept(dept);
	}
	
	
}
