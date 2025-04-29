package com.FetchDataFromIDs.controller;


import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FetchDataFromIDs.dto.ProductBasicView;
import com.FetchDataFromIDs.dto.ProductDto;
import com.FetchDataFromIDs.dto.ProductFullView;
import com.FetchDataFromIDs.dto.ProductPriceView;
import com.FetchDataFromIDs.entity.Product;
import com.FetchDataFromIDs.exception.ProductNotFound;
import com.FetchDataFromIDs.service.ProductService;

import jakarta.validation.constraints.Size;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService prodService;
	
	@PostMapping("/addproduct")
	public ResponseEntity<String> AddProducts(@RequestBody Product product) {
		return prodService.AddProducts(product);
	}
	
	
	@GetMapping("/getproductsbyids")
	public List<ProductDto> getProductsByIDs(@RequestBody @Size(max=100,message="maximum 100 IDs allowed")List<Integer> ids) {
		return prodService.getProductByids(ids);
	}
	@GetMapping("/getproducts")
	public ResponseEntity<?> getProductbyidsandprojection(@RequestParam @Size(max=100,message="maximum 100 IDs allowed") List<Integer> ids,@RequestParam(required=false)List<String>cols) {
		if(cols==null||cols.isEmpty())cols=List.of("id","name","price");
		List<String>allowcols=List.of("id","name","price","quantity");
		if(!allowcols.containsAll(cols))throw new ProductNotFound("Invalid requested columns");
		
		Class<?>projection=determine(cols);
		 List<?> result=prodService.getProducts(ids,projection);
		 return ResponseEntity.ok(result);
	}
	private Class<?> determine(List<String>cols){
		HashSet<String> colset=new HashSet<>(cols);
		
		if(colset.containsAll(List.of("id","name","price","quantity")))return ProductFullView.class ;
		else if(colset.containsAll(List.of("id","name","price")))return ProductPriceView.class;
		else if(colset.containsAll(List.of("id","name")))return ProductBasicView.class;
		else return ProductDto.class;
		
		
	
	}
	
	
}
