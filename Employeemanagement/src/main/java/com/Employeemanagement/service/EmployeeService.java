package com.Employeemanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Employeemanagement.dto.EmployeeDto;
import com.Employeemanagement.entity.Department;
import com.Employeemanagement.entity.Employee;
import com.Employeemanagement.repositroy.DepartmentRepository;
import com.Employeemanagement.repositroy.EmployeeRepository;
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private DepartmentRepository deptRepo;
	
	public ResponseEntity<String> RegisterEmp(Employee emp,Long id) {
		Optional<Employee> opt = empRepo.findByEmail(emp.getEmail());
		if(opt.isPresent())return new ResponseEntity<String>("employee already register", HttpStatus.BAD_REQUEST);
		Optional<Department> dept=deptRepo.findById(id);
		if(dept.isPresent()) {
		emp.setDepartment(dept.get());
		empRepo.save(emp);
		return new ResponseEntity<String>("employee registered successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("department not found", HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<String> loginemp(EmployeeDto emp) {
		Optional<Employee> opt = empRepo.findByEmail(emp.getEmail());
		if(opt.isPresent()&& opt.get().getPassword().equals(emp.getPassword()))return new ResponseEntity<String>("login successfull", HttpStatus.OK);
		return new ResponseEntity<String>("invalid creadentials", HttpStatus.BAD_REQUEST);
	}
	public List<EmployeeDto> getallemp(int page,int size) {
		Pageable pageable=PageRequest.of(page-1, size);//i do page-1 because page cannot be zero, page number start from 1
		
		Page<Employee>list= empRepo.findAll(pageable);
		List<EmployeeDto> EmployeeDto= new ArrayList<>();
		for(Employee emp : list) {
			EmployeeDto Response= new EmployeeDto();
			BeanUtils.copyProperties(emp, Response);
			EmployeeDto.add(Response);
			
		}
		return EmployeeDto;
	}
	public List<EmployeeDto> getallempbydeptid(Long deptid) {
		List<Employee> Listofemp=empRepo.findByDepartmentId(deptid);
		List<EmployeeDto> EmployeeDto= new ArrayList<>();
		for(Employee emp : Listofemp) {
			EmployeeDto Response= new EmployeeDto();
			BeanUtils.copyProperties(emp, Response);
			EmployeeDto.add(Response);
		}
		return EmployeeDto;
	}

}
