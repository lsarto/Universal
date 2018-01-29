package com.popeyestore.adminportal.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.popeyestore.adminportal.domain.Type;


public interface TypeRepository extends CrudRepository<Type, Long>{
		
	Type findByName(String name);
	List<Type> findAll();
}
