package com.popeyestore.adminportal.service;

import java.util.List;

import com.popeyestore.domain.Category;
import com.popeyestore.domain.Type;




public interface AdminTypeService {
	
	List<Type> findAll();
	
	Type findByName(String name);

	Type createCategory(Type category, List<Category> subCategories);
	
	Type save(Type type);

	Type findOne(Long typeId);
}
