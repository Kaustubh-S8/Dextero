package com.Task7.dto;

import java.util.List;

import com.Task7.entity.Customer;

import lombok.Data;

@Data
public class MultiCreateRequestDto {

	private Customer customer;
	private List<ProductDto> products;
}
