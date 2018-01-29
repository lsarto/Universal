package com.popeyestore.adminportal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.popeyestore.adminportal.domain.Category;
import com.popeyestore.adminportal.domain.Product;
import com.popeyestore.adminportal.service.CategoryService;
import com.popeyestore.adminportal.service.ProductService;


@RestController
@RequestMapping("/adminportal")
public class ResourceController {

	@Autowired
	private ProductService productService;
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
}