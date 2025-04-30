package com.Task7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Task7.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository custRepo;
	
	

	
}
