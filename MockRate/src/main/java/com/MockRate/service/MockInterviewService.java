package com.MockRate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MockRate.dto.MockResponseDto;
import com.MockRate.entity.MockInterview;
import com.MockRate.entity.USERS;
import com.MockRate.enums.Role;
import com.MockRate.exception.USERSNotFound;
import com.MockRate.repository.MockInterviewRepository;
import com.MockRate.repository.USERSRepository;

@Service
public class MockInterviewService {
	
	@Autowired
	private MockInterviewRepository mockRepo;
	@Autowired
	private USERSRepository userRepo;

	public ResponseEntity<String> AssignMock(Integer uid,MockInterview mock) {
		USERS user= userRepo.findById(uid).orElseThrow(() -> new USERSNotFound("User Not Found"));
		if (user.getRole() == Role.ADMIN) {
			return ResponseEntity.badRequest().body("Cannot assign presentation to Admin");
		}
		
		mock.setUsers(user);
		
		mockRepo.save(mock);
		return ResponseEntity.ok().body("mock added successfully");
	}

	public List<MockResponseDto> getallmockDetails(Integer uid) {
		List<MockInterview> mocks = mockRepo.findByUid(uid);
		List<MockResponseDto> mockResponseDtos = new ArrayList<>();
		for (MockInterview mock : mocks) {
            MockResponseDto response = new MockResponseDto();
            BeanUtils.copyProperties(mock, response);
            response.setUid(uid);
            mockResponseDtos.add(response);
        }
        
        return mockResponseDtos;
	}

}
