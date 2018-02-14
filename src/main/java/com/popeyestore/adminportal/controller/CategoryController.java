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
public class CategoryController {	
	@Autowired
	private AdminCategoryService categoryService;

	@RequestMapping(value="/categoriesByType", method = RequestMethod.POST)
	public String categoriesByType(@RequestParam("categoryName") String categoryName) {
		ObjectMapper mapper = new ObjectMapper();
		String categoriesString = null;
		
		Category category = categoryService.findByName(categoryName);
		List<Category> categoryList = category.getCategories();
		categoryList.add(category);
		
		try {
			categoriesString = mapper.writeValueAsString(categoryList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return categoriesString;
	}

}
