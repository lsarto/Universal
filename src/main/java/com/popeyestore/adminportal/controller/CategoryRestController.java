package com.popeyestore.adminportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.popeyestore.adminportal.service.AdminCategoryService;
import com.popeyestore.domain.Category;


@RestController
@RequestMapping("/adminportal/product")
public class CategoryRestController {	
	@Autowired
	private AdminCategoryService categoryService;

	@RequestMapping(value="/subcategoriesByCategoryName", method = RequestMethod.POST)
	public String subcategoriesByCategoryName(@RequestParam("categoryName") String categoryName) {
		ObjectMapper mapper = new ObjectMapper();
		String categoriesString = null;
		
		Category category = categoryService.findByName(categoryName);
		List<Category> subcategoryList = category.getCategories();
		
		try {
			categoriesString = mapper.writeValueAsString(subcategoryList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return categoriesString;
	}
	
	@RequestMapping(value="/subcategoriesByCategoryId", method = RequestMethod.POST)
	public String subcategoriesByCategoryId(@RequestParam("categoryId") Long categoryId) {
		ObjectMapper mapper = new ObjectMapper();
		String categoriesString = null;
		
		Category category = categoryService.findOne(categoryId);
		List<Category> subcategoryList = category.getCategories();
		
		try {
			categoriesString = mapper.writeValueAsString(subcategoryList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return categoriesString;
	}

}
