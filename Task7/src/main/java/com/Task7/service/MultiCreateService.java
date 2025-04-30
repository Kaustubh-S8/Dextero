package com.Task7.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Task7.dto.MultiCreateRequestDto;
import com.Task7.entity.Customer;
import com.Task7.entity.Product;
import com.Task7.exception.CustomerNotFound;
import com.Task7.repository.CustomerRepository;
import com.Task7.repository.ProductRepository;

@Service
public class MultiCreateService {

	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private ProductRepository prodRepo;

	public ResponseEntity<String> Add(MultiCreateRequestDto request) {

		Optional <Customer>opt=custRepo.findByEmail(request.getCustomer().getEmail());
		if(opt.isPresent())throw new CustomerNotFound ("customer is already present with email:- "+opt.get().getEmail());
		Customer cus=new Customer();
		cus.setEmail(request.getCustomer().getEmail());
		cus.setName(request.getCustomer().getName());
		
		
		List<Product> list=request.getProducts().stream().map(ProductDto ->{
			Product p=new Product();
			p.setName(ProductDto.getName());
			p.setQuantity(ProductDto.getQuantity());
		
			return p;
		}).collect(Collectors.toList());
		List<Product> pr=prodRepo.saveAll(list);
		cus.setProducts(pr);
		custRepo.save(cus);
		
		
		return  new ResponseEntity<String>("Customer and product added successfully", HttpStatus.CREATED);
	}

	public Page<Customer> getAllCustomer(int page, int size, String name, String email, String search) {
	
		Pageable pageable=PageRequest.of(page, size);
		if(search !=null&&!search.isEmpty()) {
			return custRepo.findByEmailOrName(search,search,pageable);
		}
		else if(name!=null&&email!=null)return custRepo.findByEmailAndName(email,name,pageable);
		else if(name!=null)return custRepo.findByName(name,pageable);
		else if(email!=null)return custRepo.findByEmail(email,pageable);
		else return custRepo.findAll(pageable);
		
	}
	
	
	
}
