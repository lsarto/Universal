package com.popeyestore.service;

import java.util.List;

import com.popeyestore.domain.Category;
import com.popeyestore.domain.Type;

public interface CategoryService {
	
	List<Category> findAll();
	
	Category findByName(String name);

	Category createCategory(Category category, Type type);
	
	Category createSubcategory(Category ownerCategory, Category subcategory, Type type);
	
	Category updateSubcategory(Category ownerCategory, Category subcategory, Type type);

	Category save(Category category);

	Category findOne(Long idCategory);
	
	void removeAll(List<Category> categoryList);
	
	void removeOne(Long id);
}
