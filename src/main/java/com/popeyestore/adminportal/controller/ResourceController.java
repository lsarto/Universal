package com.popeyestore.adminportal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.popeyestore.adminportal.service.AdminProductService;
import com.popeyestore.domain.Category;
import com.popeyestore.domain.Product;
import com.popeyestore.service.CategoryService;


@RestController
@RequestMapping("/adminportal")
public class ResourceController {

	@Autowired
	private AdminProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/product/removeList", method=RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean removeList(
			@RequestBody ArrayList<String> productIdList, Model model
			){
		
		for (String id : productIdList) {
			String productId =id.substring(8);
			try {
				Product product = productService.findOne(Long.parseLong(productId));
				productService.removeOne(Long.parseLong(productId));
				Category category = product.getCategory();
				category.setQty(category.getQty()-1);
				categoryService.save(category);
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
	@RequestMapping(value="/category/removeList", method=RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean removeCategoryList(
			@RequestBody ArrayList<String> categoryIdList, Model model
			){
		
		for (String id : categoryIdList) {
			String categoryId =id.substring(8);
			try {
				Category category = categoryService.findOne(Long.parseLong(categoryId));
				List<Category> subcategories = category.getCategories();
				
				if(subcategories==null || subcategories.isEmpty()){
					categoryService.removeOne(category.getId());
				} else {
					for(Category subcategory: subcategories){
						List<Product> productsOfSubct = subcategory.getProducts();
						if(productsOfSubct!=null && !productsOfSubct.isEmpty()){
							return false;
						}
					}
					for(Category subcategory: subcategories){
						categoryService.removeOne(subcategory.getId());
					}
					categoryService.removeOne(category.getId());
				}
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
}