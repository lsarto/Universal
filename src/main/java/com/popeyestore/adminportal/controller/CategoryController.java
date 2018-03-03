package com.popeyestore.adminportal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.popeyestore.adminportal.service.AdminCategoryService;
import com.popeyestore.adminportal.service.AdminTypeService;
import com.popeyestore.domain.Category;
import com.popeyestore.domain.Product;
import com.popeyestore.domain.Type;
import com.popeyestore.service.CategoryService;

@RequestMapping("/adminportal/category")
@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AdminTypeService typeService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCategory(@RequestParam("typeSelected") String typeName, Model model) {
		Category category = new Category();
		List<Category> subcategories = new ArrayList<>();
		category.setCategories(subcategories);
		Type type = typeService.findByName(typeName);
		category.setType(type);

		model.addAttribute("category", category);
		return "addCategory";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCategoryPost(@ModelAttribute("category") Category category, Model model) {
		Type type = typeService.findByName(category.getType().getName());
		List<Category> subcategories = category.getCategories();
		category.setCategories(null);
		Category owner = categoryService.createCategory(category, type);


		if (subcategories != null && !subcategories.isEmpty()) {
			for (Category subcategory : subcategories) {
				if (subcategory.getName() != null && !Objects.equals(subcategory.getName(), "")) {
					categoryService.createSubcategory(owner, subcategory, type);
				}
			}
		}

		return "home";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/updateCategory", method = RequestMethod.GET)
	public String updateCategory(@RequestParam("id") Long id, Model model) {
		Category category = categoryService.findOne(id);
		
		model.addAttribute("category", category);
		return "updateCategory";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public String updateCategoryPost(@ModelAttribute("category") Category categoryUpdated, Model model) {
		List<Category> subcategories = categoryUpdated.getCategories();
		
		try {
			List<Category> subcategoriesInRepository = categoryService.findAll();
			for(Category category: subcategoriesInRepository){
				if(category.getOwnerCategory()!=null){
					if(category.getOwnerCategory().getId()==categoryUpdated.getId()){
						categoryService.removeOne(category.getId());
					}
				}
			}
		} catch(Exception e) {
			model.addAttribute("removeCategoryFailed", true);
			e.printStackTrace();
			return "redirect://updateCategory?id="+categoryUpdated.getId();
		}
		
		Category categoryInRepository = categoryService.findOne(categoryUpdated.getId());

		categoryUpdated.setQty(categoryInRepository.getQty());
		categoryUpdated.setType(categoryInRepository.getType());
		categoryUpdated.setCategories(null);
		categoryService.save(categoryUpdated);
		if(subcategories!=null && !subcategories.isEmpty()){
			for(Category subcategory: subcategories){
				categoryService.updateSubcategory(categoryUpdated, subcategory, categoryUpdated.getType());
			}
		}
		
		
		List<Category> allCategory = categoryService.findAll();
		List<Category> categoryList = new ArrayList<>();
		
		for(Category category: allCategory){
			Category owner = category.getOwnerCategory();
			
			if(owner==null){
				categoryList.add(category);
			}
		}
		
		model.addAttribute("removeCategoryFailed", false);
		model.addAttribute("categoryList", categoryList);
		return "categoryList";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public @ResponseBody Boolean remove(
			@RequestBody String id, Model model) {
		try{
			Category category = categoryService.findOne(Long.parseLong(id.substring(15)));
			List<Category> subcategories = category.getCategories();
			
			if(subcategories==null || subcategories.isEmpty()){
				categoryService.removeOne(category.getId());
			} else {
				for(Category subcategory: subcategories){
					List<Product> productsOfSubct = subcategory.getProducts();
					if(productsOfSubct!=null && !productsOfSubct.isEmpty()){
						return new Boolean(false);
					}
				}
				for(Category subcategory: subcategories){
					categoryService.removeOne(subcategory.getId());
				}
				categoryService.removeOne(category.getId());
			}
			
		} catch (Exception e){
        	e.printStackTrace();
			return new Boolean(false);
		}
		
		List<Category> allCategory = categoryService.findAll();
		List<Category> categoryList = new ArrayList<>();
		
		for(Category category: allCategory){
			Category owner = category.getOwnerCategory();
			
			if(owner==null){
				categoryList.add(category);
			}
		}
		
		model.addAttribute("removeCategoryFailed", false);
		model.addAttribute("categoryList", categoryList);
		return new Boolean(true);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/categoryList")
	public String categoryListPage(Model model) {
		List<Category> allCategory = categoryService.findAll();
		List<Category> categoryList = new ArrayList<>();
		
		for(Category category: allCategory){
			Category owner = category.getOwnerCategory();
			
			if(owner==null){
				categoryList.add(category);
			}
		}
		
		model.addAttribute("categoryList", categoryList);
		
		return "categoryList";
	}

}
