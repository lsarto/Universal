package com.popeyestore.adminportal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.popeyestore.adminportal.service.AdminCategoryService;
import com.popeyestore.adminportal.service.AdminUserService;
import com.popeyestore.domain.Category;
import com.popeyestore.domain.Type;
import com.popeyestore.repository.CategoryRepository;



@Service
public class AdminCategoryServiceImpl implements AdminCategoryService{
	private static final Logger LOG = LoggerFactory.getLogger(AdminUserService.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category findByName(String name) {
		return (Category) categoryRepository.findByName(name);
	}

	@Override
	public List<Category> findAll() {
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public Category createCategory(Category category) {
		Category localCategory = (Category) categoryRepository.findByName(category.getName());
		
		if(localCategory != null){
			LOG.info("category {} already exists. Nothing will be done.", category.getName());
		} else {
			localCategory = categoryRepository.save(category);
		}
		
		return localCategory;
	}

	@Override
	public List<Category> findByType(Type type) {
		return categoryRepository.findByType(type);
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category findOne(Long categoryId) {
		return categoryRepository.findOne(categoryId);
	}
	

}
