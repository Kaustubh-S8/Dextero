package com.Task7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Task7.dto.MultiCreateRequestDto;
import com.Task7.entity.Customer;
import com.Task7.service.MultiCreateService;




@RestController
public class CustomerController {

	@Autowired
	private MultiCreateService mService;
	
	@PostMapping("/add")
	public ResponseEntity<String> getMethodName(@RequestBody MultiCreateRequestDto request) {
		return mService.Add(request);
	}
	@GetMapping("/getallcustomer")
	public ResponseEntity<Page<Customer>> getAllcustomer(@RequestParam (defaultValue="0")int page,@RequestParam (defaultValue="10")int size
								,@RequestParam (required = false)String name,@RequestParam (required = false)String email,@RequestParam(required = false)String search) {
		Page<Customer>customer= mService.getAllCustomer(page,size,name,email,search);
		return ResponseEntity.ok(customer);
	}
	
	
	
	
}
