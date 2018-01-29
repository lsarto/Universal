package com.popeyestore.adminportal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.popeyestore.adminportal.domain.Category;
import com.popeyestore.adminportal.domain.Type;
import com.popeyestore.adminportal.repository.TypeRepository;
import com.popeyestore.adminportal.service.CategoryService;
import com.popeyestore.adminportal.service.TypeService;
import com.popeyestore.adminportal.service.UserService;



@Service
public class TypeServiceImpl implements TypeService{
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private CategoryService categoryService;

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
