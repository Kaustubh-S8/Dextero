package com.FetchDataFromIDs.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.FetchDataFromIDs.dto.ProductDto;
import com.FetchDataFromIDs.entity.Product;
import com.FetchDataFromIDs.exception.ProductNotFound;
import com.FetchDataFromIDs.repository.ProductRepository;

@Service
public class ProductService {
 
	@Autowired
	private ProductRepository prodRepo;

	public List<ProductDto> getProductByids(List<Integer> ids) {
	if(ids==null||ids.isEmpty()) throw new ProductNotFound("Id List cannot be empty");
	
	List<Integer>limit =ids.stream().distinct().limit(100).collect(Collectors.toList());
	return prodRepo.findProductDtoByIds(limit);
	}

	public ResponseEntity<String> AddProducts(Product product) {
		Optional<Product> opt=prodRepo.findByName(product.getName());
		if(opt.isPresent())throw new ProductNotFound("Product is already Present");
		prodRepo.save(product);
		return new ResponseEntity<String>("Product is added successfully",HttpStatus.CREATED);
	}
	public <T>List<?> getProducts(List<Integer>ids,Class<T> projection){
		if(ids==null||ids.isEmpty()) return Collections.emptyList();
		List<Integer>limit =ids.stream().distinct().limit(100).collect(Collectors.toList());
		return prodRepo.findByIdIn(limit, projection);
	
		
	}
}
