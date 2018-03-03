package com.popeyestore.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popeyestore.adminportal.service.AdminTypeService;
import com.popeyestore.domain.Category;
import com.popeyestore.domain.Type;
import com.popeyestore.repository.CategoryRepository;
import com.popeyestore.service.CategoryService;
import com.popeyestore.service.UserService;

@Service
public class CategoryServiceImpl implements CategoryService{
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private AdminTypeService typeService;

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
		
		if(subcategory.getName()!=null && !Objects.equals(subcategory.getName(), "")){
			if(localCategoryOwner == null){
				LOG.info("category owner {} not exists. Nothing will be done.", ownerCategory.getName());
			} else {
				localSubcategory = (Category) categoryRepository.findByName(subcategory.getName());
				if(localSubcategory!=null){
					LOG.info("subcategory {} already exists. Nothing will be done.", subcategory.getName());
				} else {
					subcategory.setOwnerCategory(ownerCategory);
					subcategory.setQty(0);
					subcategory.setType(type);
					localSubcategory = categoryRepository.save(subcategory);
				}
			}
		}
		
		return localSubcategory;
	}
	
	@Override
	public Category updateSubcategory(Category ownerCategory, Category subcategory, Type type) {
		Category localCategoryOwner = (Category) categoryRepository.findByName(ownerCategory.getName());
		
		if(subcategory.getName()!=null && !Objects.equals(subcategory.getName(), "")){
			if(localCategoryOwner == null){
				LOG.info("category owner {} not exists. Nothing will be done.", ownerCategory.getName());
			} else {
				if(subcategory.getId()!=null){
					LOG.info("subcategory {} updated.", subcategory.getName());
					subcategory.setType(ownerCategory.getType());
					subcategory.setOwnerCategory(ownerCategory);
					categoryRepository.save(subcategory);
				} else {
					subcategory.setOwnerCategory(ownerCategory);
					subcategory.setQty(0);
					subcategory.setType(type);
					subcategory = categoryRepository.save(subcategory);
				}
			}
		}
		
		return subcategory;
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category findOne(Long idCategory) {
		return categoryRepository.findOne(idCategory);
	}

	@Override
	public void removeAll(List<Category> listCategory) {
		categoryRepository.delete(listCategory);
	}

	@Override
	public void removeOne(Long id) {
		categoryRepository.delete(id);
	}
	

}
