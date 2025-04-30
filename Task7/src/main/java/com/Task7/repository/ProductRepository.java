package com.Task7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Task7.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
