package com.popeyestore.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.popeyestore.domain.Product;
import com.popeyestore.domain.Type;
import com.popeyestore.adminportal.utility.Brand;
import com.popeyestore.domain.Category;
import com.popeyestore.domain.User;
import com.popeyestore.service.ProductService;
import com.popeyestore.service.TypeService;
import com.popeyestore.service.CategoryService;
import com.popeyestore.service.UserService;

@Controller
public class SearchController {
	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TypeService typeService;

	@RequestMapping("/searchByCategory")
	public String searchByCategory(@RequestParam("category") String idCategory, Model model, Principal principal) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}

//		String classActiveCategory = "active" + category;
//		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
//		classActiveCategory = classActiveCategory.replaceAll("&", "");
//		model.addAttribute(classActiveCategory, true);
		model.addAttribute("activeCategory", idCategory);
		
		List<Type> types = typeService.findAll();
		model.addAttribute("types", types);

		List<Product> productList=null;
		Category categoryFound;

		if(idCategory=="all"){
			categoryFound = categoryService.findByName(idCategory);
			productList = categoryFound.getProducts();
		}
		else if(idCategory.startsWith("all")){
			for(Type type: types){
				if(idCategory.equals("all"+type.getId())){
					productList = productService.findByType(type);
					break;
				}
			}
		} else {
			categoryFound = categoryService.findOne(Long.decode(idCategory.replace("-category", "")));
			productList = categoryFound.getProducts();
		}
		

		if (productList==null || productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "shop-category";
		} else {
			for(int i=0; i<productList.size(); i++){
				Product product = productList.get(i);
				if(!product.isActive()){
					productList.remove(product);
				}
			}
			if(productList.isEmpty()){
				model.addAttribute("emptyList", true);
				return "shop-category";
			}
		}
		
		model.addAttribute("productList", productList);

		return "shop-category";
	}

	@RequestMapping("/searchByCategoryAjax")
	public String searchByCategoryAjax(@RequestParam("category") String idCategory, Model model, Principal principal) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}


//		String classActiveCategory = "active" + category;
//		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
//		classActiveCategory = classActiveCategory.replaceAll("&", "");
//		model.addAttribute(classActiveCategory, true);
		model.addAttribute("activeCategory", idCategory);
		
		List<Type> types = typeService.findAll();
		model.addAttribute("types", types);

		List<Product> productList = null;
		Category categoryFound;
		
		if(idCategory=="all"){
			categoryFound = categoryService.findByName(idCategory);
			productList = categoryFound.getProducts();
		}
		else if(idCategory.startsWith("all")){
			for(Type type: types){
				if(idCategory.equals("all"+type.getId())){
					productList = productService.findByType(type);
					break;
				}
			}
		} else {
			categoryFound = categoryService.findOne(Long.decode(idCategory.replace("-category", "")));
			productList = categoryFound.getProducts();
		}
		
		if (productList==null || productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "common/categorieFragment :: listFragment";
		} else {
			for(int i=0; i<productList.size(); i++){
				Product product = productList.get(i);
				if(!product.isActive()){
					productList.remove(product);
				}
			}
			if(productList.isEmpty()){
				model.addAttribute("emptyList", true);
				return "common/categorieFragment :: listFragment";
			}
		}

		model.addAttribute("productList", productList);

		return "common/categorieFragment :: listFragment";
	}

	@RequestMapping("/searchProduct")
	public String searchProduct(@ModelAttribute("keyword") String keyword, Principal principal, Model model) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		List<Product> productList = productService.blurrySearch(keyword);

		if (productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "shop-category";
		}
		
		model.addAttribute("productList", productList);

		return "shop-category";
	}
	
	@RequestMapping("/searchByBrand")
	public String searchByBrand(@ModelAttribute("brand") Brand brand, Principal principal, Model model) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		Boolean wismec = brand.getWismec();
		Boolean drops = brand.getDrops();
		Boolean ijoy = brand.getIjoy();
		Boolean geekVape = brand.getGeekVape();
		List<Product> productList = new ArrayList<Product>();

		if(wismec!=null && wismec.booleanValue()==true){
			List<Product> wismecProducts = productService.blurrySearch("Wismec");
			model.addAttribute("wismec", true);
			productList.addAll(wismecProducts);
		}
		if(drops!=null && drops.booleanValue()==true){
			List<Product> dropsProducts = productService.blurrySearch("Drops");
			model.addAttribute("drops", true);
			productList.addAll(dropsProducts);
		}
		if(ijoy!=null && ijoy.booleanValue()==true){
			List<Product> ijoyProducts = productService.blurrySearch("Ijoy");
			model.addAttribute("ijoy", true);
			productList.addAll(ijoyProducts);
		}
		if(geekVape!=null && geekVape.booleanValue()==true){
			List<Product> geekVapeProducts = productService.blurrySearch("Geek Vape");
			model.addAttribute("geekVape", true);
			productList.addAll(geekVapeProducts);
		}
		
		if (productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "shop-category";
		}
		
		model.addAttribute("productList", productList);

		return "shop-category";
	}
}
