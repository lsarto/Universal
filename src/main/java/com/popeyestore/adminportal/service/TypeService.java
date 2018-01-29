package com.popeyestore.adminportal.service;

import java.util.List;

import com.popeyestore.adminportal.domain.Category;
import com.popeyestore.adminportal.domain.Type;


public interface TypeService {
	
	List<Type> findAll();
	
	Type findByName(String name);

	Type createCategory(Type category, List<Category> subCategories);
	
	Type save(Type type);

	Type findOne(Long typeId);
}
