package com.popeyestore.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.popeyestore.adminportal.domain.Category;
import com.popeyestore.adminportal.domain.Product;


public interface ProductRepository extends CrudRepository<Product, Long> {
	
	List<Product> findByNameContaining(String name);
	
	List<Product> findByCategory(Category category);
	
}
