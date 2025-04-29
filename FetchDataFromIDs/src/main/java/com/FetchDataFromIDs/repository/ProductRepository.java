package com.FetchDataFromIDs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.FetchDataFromIDs.dto.ProductDto;
import com.FetchDataFromIDs.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("select new com.FetchDataFromIDs.dto.ProductDto(p.id,p.name,p.price)"+"from Product p where p.id In :ids")
	List<ProductDto> findProductDtoByIds(@Param("ids")List<Integer> ids);

	Optional<Product> findByName(String name);
	
	<T> List<T> findByIdIn(List<Integer> limit, Class<T> projection);
	
	
	

}
