package com.popeyestore.adminportal.service;

import java.util.List;

import com.popeyestore.adminportal.domain.Category;
import com.popeyestore.adminportal.domain.Product;


public interface ProductService {
	
	Product save(Product product);

	List<Product> findAll();
	
	Product findOne(Long id);
	
	void removeOne(Long id);

	List<Product> findByCategory(Category categorySelected);
}
