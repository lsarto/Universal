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
import com.popeyestore.adminportal.utility.SearchPrice;
import com.popeyestore.domain.Category;
import com.popeyestore.domain.User;
import com.popeyestore.service.ProductService;
import com.popeyestore.service.TypeService;
import com.popeyestore.service.CategoryService;
import com.popeyestore.service.UserService;

@Controller
public class SearchController {
	private final int FILTER_BRAND=0;
	private final int FILTER_PRICE=1;
	
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

		// String classActiveCategory = "active" + category;
		// classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		// classActiveCategory = classActiveCategory.replaceAll("&", "");
		// model.addAttribute(classActiveCategory, true);
		model.addAttribute("activeCategory", idCategory);

		List<Type> types = typeService.findAll();
		model.addAttribute("types", types);

		List<Product> productList = null;
		Category categoryFound = null;

		if (idCategory == "all") {
			categoryFound = categoryService.findByName(idCategory);
			productList = categoryFound.getProducts();
		} else if (idCategory.startsWith("all")) {
			for (Type type : types) {
				if (idCategory.equals("all" + type.getId())) {
					productList = productService.findByType(type);
					break;
				}
			}
		} else {
			categoryFound = categoryService.findOne(Long.decode(idCategory.replace("-category", "")));
			productList = categoryFound.getProducts();
		}

		if (productList == null || productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "shop-category";
		} else {
			for (int i = 0; i < productList.size(); i++) {
				Product product = productList.get(i);
				if (!product.isActive()) {
					productList.remove(product);
				}
			}
			if (productList.isEmpty()) {
				model.addAttribute("emptyList", true);
				return "shop-category";
			}
		}

		model.addAttribute("productList", productList);
		if (categoryFound != null) {
			Type typeFound = categoryFound.getType();
			model.addAttribute("typeFound", typeFound.getId());
		}

		return "shop-category";
	}

	@RequestMapping("/searchByCategoryAjax")
	public String searchByCategoryAjax(@RequestParam("category") String idCategory, Model model, Principal principal) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}

		// String classActiveCategory = "active" + category;
		// classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		// classActiveCategory = classActiveCategory.replaceAll("&", "");
		// model.addAttribute(classActiveCategory, true);
		model.addAttribute("activeCategory", idCategory);

		List<Type> types = typeService.findAll();
		model.addAttribute("types", types);

		List<Product> productList = null;
		Category categoryFound;

		if (idCategory == "all") {
			categoryFound = categoryService.findByName(idCategory);
			productList = categoryFound.getProducts();
		} else if (idCategory.startsWith("all")) {
			for (Type type : types) {
				if (idCategory.equals("all" + type.getId())) {
					productList = productService.findByType(type);
					break;
				}
			}
		} else {
			categoryFound = categoryService.findOne(Long.decode(idCategory.replace("-category", "")));
			productList = categoryFound.getProducts();
		}

		if (productList == null || productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "common/categorieFragment :: listFragment";
		} else {
			for (int i = 0; i < productList.size(); i++) {
				Product product = productList.get(i);
				if (!product.isActive()) {
					productList.remove(product);
				}
			}
			if (productList.isEmpty()) {
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
	public String searchByBrand(@ModelAttribute("brand") Brand brand, @ModelAttribute("price") SearchPrice price,
			Principal principal, Model model) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}

		List<Product> productList = applyFilter(new Long(-1), brand, price, model, FILTER_BRAND);

		if (productList==null || productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "shop-category";
		}

		model.addAttribute("productList", productList);

		return "shop-category";
	}

	private List<Product> productsByBrand(Brand brand, List<Product> productList, Model model) {
		Boolean wismec = brand.getWismec();
		Boolean drops = brand.getDrops();
		Boolean ijoy = brand.getIjoy();
		Boolean geekVape = brand.getGeekVape();
		boolean isOneCheck = false;

		if(productList==null){ // if no filter is applied before
			productList = new ArrayList<Product>();
			if (wismec != null && wismec.booleanValue() == true) {
				List<Product> wismecProducts = productService.blurrySearch("Wismec");
				model.addAttribute("wismec", true);
				productList.addAll(wismecProducts);
				isOneCheck=true;
			}
			if (drops != null && drops.booleanValue() == true) {
				List<Product> dropsProducts = productService.blurrySearch("Drops");
				model.addAttribute("drops", true);
				productList.addAll(dropsProducts);
				isOneCheck=true;
			}
			if (ijoy != null && ijoy.booleanValue() == true) {
				List<Product> ijoyProducts = productService.blurrySearch("Ijoy");
				model.addAttribute("ijoy", true);
				productList.addAll(ijoyProducts);
				isOneCheck=true;
			}
			if (geekVape != null && geekVape.booleanValue() == true) {
				List<Product> geekVapeProducts = productService.blurrySearch("Geekvape");
				model.addAttribute("geekVape", true);
				productList.addAll(geekVapeProducts);
				isOneCheck=true;
			}
		} else { //if some filter is applied before use that filtered list of products
			List<Product> productsToAdd = new ArrayList<Product>();

			for(Product product: productList){
				String productName = product.getName().toLowerCase();
				if (wismec != null && wismec.booleanValue() == true) {
					if (productName.contains("wismec")){
						productsToAdd.add(product);
					}
					model.addAttribute("wismec", true);
					isOneCheck=true;
				}
				if (drops != null && drops.booleanValue() == true) {
					if (productName.contains("drops")){
						productsToAdd.add(product);
					}
					model.addAttribute("drops", true);
					isOneCheck=true;
				}
				if (ijoy != null && ijoy.booleanValue() == true) {
					if (productName.contains("ijoy")){
						productsToAdd.add(product);
					}
					model.addAttribute("ijoy", true);
					isOneCheck=true;
				}
				if (geekVape != null && geekVape.booleanValue() == true) {
					if (productName.contains("geekvape")){
						productsToAdd.add(product);
					}
					model.addAttribute("geekVape", true);
					isOneCheck=true;
				}
			}
			productList = productsToAdd;
		}
		
		if(!isOneCheck){ //if no one is checked then not apply this filter
			return null;
		}
		return productList;
	}

	@RequestMapping("/searchByPrice")
	public String findByPrice(@ModelAttribute("price") SearchPrice price, @ModelAttribute("brand") Brand brand,
			@ModelAttribute("activeCategory") Long idCategoria, Principal principal, Model model) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}

		List<Product> productsToReturn = applyFilter(idCategoria, brand, price, model, FILTER_PRICE);
		
		if (productsToReturn == null || productsToReturn.isEmpty()) {
			model.addAttribute("emptyList", true);
		}
		model.addAttribute("productList", productsToReturn);

		return "shop-category";
	}

	private List<Product> productsByPrice(Long idCategoria, SearchPrice price,
			List<Product> products, Model model) {
		Double minPrice = null;
		Double maxPrice = null;
		List<Product> productsToReturn = new ArrayList<Product>();

		
		if (price.getPrice0to25() != null && price.getPrice0to25()) {
			minPrice = 0.0;
			maxPrice = 25.0;
			model.addAttribute("price0to25", true);
		}
		if (price.getPrice25to50() != null && price.getPrice25to50()) {
			if (minPrice == null) {
				minPrice = 25.0;
			}
			maxPrice = 50.0;
			model.addAttribute("price25to50", true);
		}
		if (price.getPrice50to75() != null && price.getPrice50to75()) {
			if (minPrice == null) {
				minPrice = 50.0;
			}
			maxPrice = 75.0;
			model.addAttribute("price50to75", true);
		}
		if (price.getPrice75to100() != null && price.getPrice75to100()) {
			if (minPrice == null) {
				minPrice = 75.0;
			}
			maxPrice = 100.0;
			model.addAttribute("price75to100", true);
		}
		if (price.getPrice100up() != null && price.getPrice100up()) {
			if (minPrice == null) {
				minPrice = 100.0;
			}
			maxPrice = 1000000.0;
			model.addAttribute("price100up", true);
		}
		//if some price checkbox is checked
		if (minPrice != null && maxPrice != null) {
			if(products!=null){ //if some filter before is applied
				for (Product product : products) {
					if (product.getOurPrice() == 0.0) {
						if (product.getListPrice() >= minPrice && product.getListPrice() <= maxPrice) {
							productsToReturn.add(product);
						}
					} else {
						if (product.getOurPrice() >= minPrice && product.getOurPrice() <= maxPrice) {
							productsToReturn.add(product);
						}
					}
				}
			} else { //if no filter before is applied
				productsToReturn = productService.findByPriceBetween(minPrice.doubleValue(), maxPrice.doubleValue());
			}
		} else { //if no price checkbox is checked return null
			return null;
		}
		
		return productsToReturn;
	}
	
	private List<Product> applyFilter(Long idCategoria, Brand brand, SearchPrice price, Model model, int FILTER){
		List<Product> products = null;
		
		if(FILTER==FILTER_BRAND){
			products = productsByPrice(idCategoria, price, null, model);
			
			return productsByBrand(brand, products, model);
			
		} else if(FILTER==FILTER_PRICE){
			products = productsByBrand(brand, null, model);
			
			if(products!=null){
				//if some brands is checked use
				//brand filter products
				;
			} else if (idCategoria != -1) {
				//else if any brand is checked and some category filter is active
				//use products by category filter
				Category category = categoryService.findOne(idCategoria);
				Type typeFound = category.getType();
				model.addAttribute("typeFound", typeFound.getId());
				products = productService.findByCategory(category);
			} 
			//if no brands nor categories are selected
			//set products to null, so they will search in db
			
			return productsByPrice(idCategoria, price, products, model);
		}
		return products;
	}
}
