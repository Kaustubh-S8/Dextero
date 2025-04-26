package com.MockRate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MockRate.dto.USERSDto;
import com.MockRate.entity.USERS;
import com.MockRate.enums.Role;
import com.MockRate.exception.USERSNotFound;
import com.MockRate.repository.USERSRepository;

@Service
public class USERSService {

	@Autowired
	private USERSRepository userRepo;
	
	public ResponseEntity<String> RegisterUser(USERS user) {
		Optional<USERS> opt= userRepo.findByEmail(user.getEmail());
		if(opt.isPresent())return new ResponseEntity<String>("User is already register",HttpStatus.BAD_REQUEST);
		userRepo.save(user);
		return  new ResponseEntity<String>("User registered successfully",HttpStatus.CREATED);
	}

	public ResponseEntity<String> loginUser(String email, String password) {
		USERS user=userRepo.findByEmail(email).orElseThrow(()-> new USERSNotFound("USER not REgister "));
		if(user.getPassword().equals(password))return new ResponseEntity<String>("User login successfull",HttpStatus.OK);
		return  new ResponseEntity<String>("Invalid creadentials",HttpStatus.BAD_REQUEST);
	}

	public USERSDto getUserDetails(Integer uid) {
		USERS user=userRepo.findById(uid).orElseThrow(()-> new USERSNotFound("USER not REgister "));
		USERSDto userDto=new USERSDto();
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

	public List<USERSDto> getAllUserDetails(Integer aid) {
		USERS admin=userRepo.findById(aid).orElseThrow(()-> new USERSNotFound("Admin id not found "));
		if(admin.getRole()!=Role.ADMIN)throw new USERSNotFound("Only admin can access") ;
		List<USERS> users =  userRepo.findAll();
		List<USERSDto> userResponseDto= new ArrayList<>();
		for(USERS user : users) {
			USERSDto Response= new USERSDto();
			BeanUtils.copyProperties(user, Response);
			userResponseDto.add(Response);
			
		}
		
		return userResponseDto;
	}

	public ResponseEntity<String> UpdateUser(Integer aid, Integer uid, USERS user) {
		USERS admin=userRepo.findById(aid).orElseThrow(()-> new USERSNotFound("Admin id not found "));
		if(admin.getRole()!=Role.ADMIN)throw new USERSNotFound("Cannot change Admin details") ;
		USERS users=userRepo.findById(uid).orElseThrow(()-> new USERSNotFound("USer not found "));
		users.setEmail(user.getEmail());
		users.setName(user.getName());
		users.setPassword(user.getPassword());
		users.setRole(user.getRole());
		userRepo.save(users);
		return new ResponseEntity<String>("User updated Successfully", HttpStatus.OK);
	}

	public ResponseEntity<String> DeleteUser(Integer uid, Integer aid) {
		USERS admin=userRepo.findById(aid).orElseThrow(()-> new USERSNotFound("Admin id not found "));
		USERS user=userRepo.findById(uid).orElseThrow(()-> new USERSNotFound("USer not found "));
		userRepo.delete(user);
		return new ResponseEntity<String>("User is Deleted Successfully",HttpStatus.OK);
	}

}
