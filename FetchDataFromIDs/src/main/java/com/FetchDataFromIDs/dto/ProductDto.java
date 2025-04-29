package com.FetchDataFromIDs.dto;

import lombok.Data;

@Data
public class ProductDto {

	private Integer id;
	
	private String name;
	
	private Double price;

	public ProductDto(Integer id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
}
