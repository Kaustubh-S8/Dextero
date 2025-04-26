package com.Employeemanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employeemanagement.dto.DepartmentDto;
import com.Employeemanagement.entity.Department;
import com.Employeemanagement.repositroy.DepartmentRepository;
import com.Employeemanagement.repositroy.EmployeeRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository deptRepo;
	
	@Autowired
	private EmployeeRepository empRepo;

	public List<DepartmentDto> getalldept() {
		List<Department>ListofDept=deptRepo.findAll();
		List<DepartmentDto> DepartmentDto= new ArrayList<>();
		for(Department Dept : ListofDept) {
			DepartmentDto Response= new DepartmentDto();
			BeanUtils.copyProperties(Dept, Response);
			DepartmentDto.add(Response);
			
		}
		return DepartmentDto;
	}

	public Department createdept(Department dept) {
		 Department dep= deptRepo.save(dept);
		 return dep;
	}
}
