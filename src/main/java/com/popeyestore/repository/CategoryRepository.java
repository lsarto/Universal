package com.popeyestore.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.popeyestore.domain.Category;
import com.popeyestore.domain.Type;

public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	Category findByName(String name);
	
	List<Category> findByType(Type type);
}
