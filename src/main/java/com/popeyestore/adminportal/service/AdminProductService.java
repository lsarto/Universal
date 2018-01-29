package com.popeyestore.adminportal.service;

import java.util.List;

import com.popeyestore.domain.Category;
import com.popeyestore.domain.Product;




public interface AdminProductService {
	
	Product save(Product product);

	List<Product> findAll();
	
	Product findOne(Long id);
	
	void removeOne(Long id);

	List<Product> findByCategory(Category categorySelected);
}
