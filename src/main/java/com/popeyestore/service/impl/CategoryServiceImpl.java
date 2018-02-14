package com.popeyestore.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popeyestore.domain.Category;
import com.popeyestore.domain.Type;
import com.popeyestore.repository.CategoryRepository;
import com.popeyestore.service.CategoryService;
import com.popeyestore.service.UserService;

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
	public Category createCategory(Category category, Type type) {
		Category localCategory = (Category) categoryRepository.findByName(category.getName());
		
		if(localCategory != null){
			LOG.info("category {} already exists. Nothing will be done.", category.getName());
		} else {
			category.setQty(0);
			category.setType(type);
			localCategory = categoryRepository.save(category);
		}
		
		return localCategory;
	}
	
	@Override
	public Category createSubcategory(Category ownerCategory, Category subcategory, Type type) {
		Category localCategoryOwner = (Category) categoryRepository.findByName(ownerCategory.getName());
		Category localSubcategory = null;
		
		if(localCategoryOwner == null){
			LOG.info("category owner {} not exists. Nothing will be done.", ownerCategory.getName());
		} else {
			localSubcategory = (Category) categoryRepository.findByName(subcategory.getName());
			if(localSubcategory!=null){
				LOG.info("subcategory {} already exists. Nothing will be done.", subcategory.getName());
			} else {
				subcategory.setQty(0);
				subcategory.setType(type);
				localSubcategory = categoryRepository.save(subcategory);
			}
		}
		
		return localSubcategory;
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category findOne(Long idCategory) {
		return categoryRepository.findOne(idCategory);
	}
	

}
