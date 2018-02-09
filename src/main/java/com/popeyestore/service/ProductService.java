package com.popeyestore.service;

import java.util.List;

import com.popeyestore.domain.Category;
import com.popeyestore.domain.Product;
import com.popeyestore.domain.Type;

public interface ProductService {
	List<Product> findAll();

	Product findOne(Long id);

	List<Product> blurrySearch(String name);
	
	List<Product> findByType(Type type);
	
	List<Product> findLatest();
	
	List<Product> findByCategory(Category category);
	
	List<Product> findByPriceBetween(double minPrice, double maxPrice);
}
