package com.MockRate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MockRate.dto.MockResponseDto;
import com.MockRate.entity.MockInterview;
import com.MockRate.entity.USERS;
import com.MockRate.enums.Role;
import com.MockRate.exception.USERSNotFound;
import com.MockRate.repository.USERSRepository;
import com.MockRate.service.MockInterviewService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/mock")
public class MockInterviewController {

	@Autowired
	private MockInterviewService mockService;
	@Autowired
	private USERSRepository userRepo;
	
	@PostMapping("/admin/{aid}/assignmock/userid/{uid}")
	public ResponseEntity<String> postMethodName(@RequestBody MockInterview mock,@PathVariable Integer aid,@PathVariable Integer uid) {
		USERS user= userRepo.findById(aid).orElseThrow(() -> new USERSNotFound("User Not Found"));
		if(user.getRole()!=Role.ADMIN)throw new USERSNotFound("only admin can access");
		return mockService.AssignMock(uid,mock);
	}
	
	@GetMapping("/allmock/userid/{uid}")
	public List<MockResponseDto> getAllmockDetails(@PathVariable Integer uid) {
		USERS user= userRepo.findById(uid).orElseThrow(() -> new USERSNotFound("User Not Found"));
		if(user.getRole()==Role.ADMIN)throw new USERSNotFound("admin access denied");
		return mockService.getallmockDetails(uid) ;
	}
	
	
}
