package com.popeyestore.adminportal.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.popeyestore.adminportal.domain.Category;
import com.popeyestore.adminportal.domain.Type;


public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	Category findByName(String name);
	
	List<Category> findByType(Type type);
}
