package com.popeyestore.adminportal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.popeyestore.adminportal.service.AdminCategoryService;
import com.popeyestore.adminportal.service.AdminProductService;
import com.popeyestore.domain.Category;
import com.popeyestore.domain.Product;


@RestController
@RequestMapping("/adminportal")
public class ResourceController {

	@Autowired
	private AdminProductService productService;
	@Autowired
	private AdminCategoryService categoryService;
	
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
}