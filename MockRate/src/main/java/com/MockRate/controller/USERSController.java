package com.MockRate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.MockRate.dto.USERSDto;
import com.MockRate.entity.USERS;
import com.MockRate.repository.USERSRepository;
import com.MockRate.service.USERSService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/user")
public class USERSController {

	@Autowired
	private USERSService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> Register(@RequestBody USERS user) {
	
		return userService.RegisterUser(user);
	}
	@PostMapping("/login")
	public ResponseEntity<String> postMethodName(@RequestParam String email,@RequestParam String password) {
		return userService.loginUser(email,password);
	}
	@GetMapping("/details/userid/{uid}")
	public USERSDto getUserDetails(@PathVariable Integer uid) {
		return userService.getUserDetails(uid);
	}
	
	@GetMapping("/admin/{aid}/alluserdetails")
	public List<USERSDto> getAllUserDetails(@PathVariable Integer aid) {
		return userService.getAllUserDetails(aid);
	}
	@PutMapping("/admin/{aid}/updateuser/{uid}")
	public ResponseEntity<String> UpdateUser(@PathVariable Integer aid,@PathVariable Integer uid, @RequestBody USERS user) {
		return userService.UpdateUser(aid,uid,user);
	}
	@DeleteMapping("/admin/{aid}/deleteuser/userid/{uid}")
	public ResponseEntity<String> DeleteUser(@PathVariable Integer uid,@PathVariable Integer aid){
		return userService.DeleteUser(uid,aid);
	}
	
	
	
	
	
}
