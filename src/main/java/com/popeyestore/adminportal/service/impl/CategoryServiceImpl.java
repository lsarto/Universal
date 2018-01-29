package com.popeyestore.adminportal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popeyestore.adminportal.domain.Category;
import com.popeyestore.adminportal.domain.Type;
import com.popeyestore.adminportal.repository.CategoryRepository;
import com.popeyestore.adminportal.service.CategoryService;
import com.popeyestore.adminportal.service.UserService;



@Service
public class CategoryServiceImpl implements CategoryService{
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

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
