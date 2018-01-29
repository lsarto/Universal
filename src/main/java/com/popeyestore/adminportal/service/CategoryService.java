package com.popeyestore.adminportal.service;

import java.util.List;

import com.popeyestore.adminportal.domain.Category;
import com.popeyestore.adminportal.domain.Type;


public interface CategoryService {
	
	List<Category> findAll();
	
	Category findByName(String name);

	Category createCategory(Category category);
	
	List<Category> findByType(Type type);
	
	Category save(Category category);

	Category findOne(Long categoryId);
}
