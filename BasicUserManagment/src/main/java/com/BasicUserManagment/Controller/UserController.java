package com.BasicUserManagment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BasicUserManagment.dto.TaskDto;
import com.BasicUserManagment.dto.UserDto;
import com.BasicUserManagment.security.UserDetailsImpl;
import com.BasicUserManagment.service.TaskService;
import com.BasicUserManagment.service.UserService;



@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
public class UserController {

	 @Autowired
	 private  UserService userService;
	 
	 @Autowired
	 private TaskService taskservice;
	 
	 @GetMapping("/getdetails")
	 public ResponseEntity<UserDto> getprofile() {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 	return  ResponseEntity.ok(userService.findUserById(userDetails.getId()));
	 }
	 
	 @GetMapping("/getTask")
	 public ResponseEntity<List<TaskDto>> getMethodName() {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 	
		return ResponseEntity.ok(taskservice.getAllTaskById(userDetails.getId()));
	 }
	 
	 
}