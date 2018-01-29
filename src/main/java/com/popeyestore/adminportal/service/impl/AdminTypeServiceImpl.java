package com.popeyestore.adminportal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.popeyestore.adminportal.service.AdminCategoryService;
import com.popeyestore.adminportal.service.AdminTypeService;
import com.popeyestore.adminportal.service.AdminUserService;
import com.popeyestore.domain.Category;
import com.popeyestore.domain.Type;
import com.popeyestore.repository.TypeRepository;



@Service
public class AdminTypeServiceImpl implements AdminTypeService{
	private static final Logger LOG = LoggerFactory.getLogger(AdminUserService.class);

	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private AdminCategoryService categoryService;

	@Override
	public List<Type> findAll() {
		return (List<Type>) typeRepository.findAll();
	}

	@Override
	public Type findByName(String name) {
		return typeRepository.findByName(name);
	}

	@Override
	@Transactional
	public Type createCategory(Type type, List<Category> categories) {
		Type localType = typeRepository.findByName(type.getName());
		
		if(localType != null){
			LOG.info("type {} already exists. Nothing will be done.", type.getName());
		} else {
			for(Category category: categories){
				categoryService.createCategory(category);
			}
			
			type.setCategories(categories);
			localType = typeRepository.save(type);
		}
		
		return localType;
	}

	@Override
	public Type save(Type type) {
		return typeRepository.save(type);
	}

	@Override
	public Type findOne(Long typeId) {
		return typeRepository.findOne(typeId);
	}

}
