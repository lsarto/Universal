package com.popeyestore.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.multipart.MultipartFile;

import com.popeyestore.adminportal.domain.DataTransfer;
import com.popeyestore.adminportal.service.AdminAttributeService;
import com.popeyestore.adminportal.service.AdminCategoryService;
import com.popeyestore.adminportal.service.AdminProductService;
import com.popeyestore.adminportal.service.AdminTypeService;
import com.popeyestore.adminportal.utility.ImageUtility;
import com.popeyestore.domain.Category;
import com.popeyestore.domain.Product;
import com.popeyestore.domain.ProductAttribute;
import com.popeyestore.domain.Type;

@Controller
@RequestMapping("/adminportal/product")
public class ProductController {
	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

	private final int IMAGE_WIDTH = 1000;
	private final int IMAGE_HEIGHT = 1000;
	@Autowired
	private AdminCategoryService categoryService;
	@Autowired
	private AdminProductService productService;
	@Autowired
	private AdminTypeService typeService;
	@Autowired
	private AdminAttributeService attributeService;

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProduct(@RequestParam("typeSelected") String typeName, Model model) {
		Product product = new Product();
		Category category = new Category();
		List<ProductAttribute> attributeList = new ArrayList<>();
		Type type = new Type();
		
		type.setName(typeName);
		product.setType(type);
		product.setProductAttributes(attributeList);
		product.setCategory(category);
		
		List<Category> categories = categoryService.findByType(typeService.findByName(typeName));
		DataTransfer dt = new DataTransfer(product, categories, attributeList);
		model.addAttribute("dataTransfer", dt);
		model.addAttribute("categories", categories);
		
		return "addProduct";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProductPost(@ModelAttribute("dataTransfer") DataTransfer dataTransfer, HttpServletRequest request) {
		Product product = dataTransfer.getProduct();
		List<ProductAttribute> attributeList = product.getProductAttributes();
		
		//save category
		String categoryName = product.getCategory().getName();
		Category category = categoryService.findByName(categoryName);
		category.setQty(category.getQty()+1);
		categoryService.save(category);
		product.setCategory(category);
		
		
		//save type
		String typeName = product.getType().getName();
		Type type = typeService.findByName(typeName);
		typeService.save(type);
		product.setType(type);
		
		//save product
		product.setProductAttributes(null);
		if(product.getOurPrice()==0.0){
			product.setOurPrice(product.getListPrice());
		}
		productService.save(product);
		
		//save attributes
		if(attributeList!=null && !attributeList.isEmpty()){
			for(int i=0; i<attributeList.size(); i++){
				ProductAttribute attribute = attributeList.get(i);
				if(attribute.getName()!=null && !Objects.equals(attribute.getName(), "")){
					attribute.setProduct(product);
					attributeService.save(attribute);
				}
			}
		}
		
		//system debug
		LOG.debug("productName: "+ dataTransfer.getProduct().getName());
		LOG.debug("productType: "+ dataTransfer.getProduct().getType().getName());
		LOG.debug("productCategory: " + category.getName());
		if(attributeList!=null && !attributeList.isEmpty()){
			for(ProductAttribute pa: attributeList){
				LOG.debug("productAttribute Name: "+pa.getName()+", Value: "+pa.getValue());
			}
		}
		LOG.debug("Peso spedizione: " + dataTransfer.getProduct().getShippingWeight());
		LOG.debug("list price: " + dataTransfer.getProduct().getListPrice());
		LOG.debug("Our Price: " + dataTransfer.getProduct().getOurPrice());
		LOG.debug("SKU: " + dataTransfer.getProduct().getInStockNumber());
		LOG.debug("Sale: " + dataTransfer.getProduct().isSale());
		LOG.debug("New: " + dataTransfer.getProduct().isNewProduct());
		LOG.debug("Status of product: " + dataTransfer.getProduct().isActive());
		LOG.debug("description: " + dataTransfer.getProduct().getDescription());
		
		LOG.info("Add new Product: Name: "+product.getName()+", Type: "+product.getType().getName()
				+", Category: "+product.getCategory().getName()+", AttributeList: "+product.getProductAttributes()
				+", ShippingWeight: "+product.getShippingWeight()+", listPrice: "+product.getListPrice()
				+", OurPrice: "+product.getOurPrice()+", SKU: "+product.getInStockNumber()
				+", Sale"+product.isSale()+", New: "+product.isNewProduct()
				+", Status: "+product.isActive()+", Description: "+product.getDescription());

		MultipartFile productCategory = product.getProductCategory();
		MultipartFile productDetail1 = product.getProductDetail1();
		MultipartFile productDetail2 = product.getProductDetail2();
		MultipartFile productDetail3 = product.getProductDetail3();
		MultipartFile latestImage = product.getLatestImage();

		try {
			byte[] bytes;
			BufferedOutputStream stream;
			String name;
			String path;

			
			bytes = productCategory.getBytes();
			name = product.getId() + "-1.png";
			path = "src/main/resources/static/image/product/" + name;
			stream = new BufferedOutputStream(
					new FileOutputStream(new File(path)));
			stream.write(bytes);
			ImageUtility.resize(path, path, IMAGE_WIDTH, IMAGE_HEIGHT);
			
		    bytes = productDetail1.getBytes();
			name = product.getId() + "-2.png";
			path = "src/main/resources/static/image/product/" + name;
			stream = new BufferedOutputStream(
					new FileOutputStream(new File(path)));
			stream.write(bytes);
			ImageUtility.resize(path, path, IMAGE_WIDTH, IMAGE_HEIGHT);
			
			bytes = productDetail2.getBytes();
			name = product.getId() + "-3.png";
			path = "src/main/resources/static/image/product/" + name;
			stream = new BufferedOutputStream(
					new FileOutputStream(new File(path)));
			stream.write(bytes);
			ImageUtility.resize(path, path, IMAGE_WIDTH, IMAGE_HEIGHT);
			
			bytes = productDetail3.getBytes();
			name = product.getId() + "-4.png";
			path = "src/main/resources/static/image/product/" + name;
			stream = new BufferedOutputStream(
					new FileOutputStream(new File(path)));
			stream.write(bytes);
			ImageUtility.resize(path, path, IMAGE_WIDTH, IMAGE_HEIGHT);
			
			bytes = latestImage.getBytes();
			name = product.getId() + "-5.png";
			path = "src/main/resources/static/image/product/" + name;
			stream = new BufferedOutputStream(
					new FileOutputStream(new File(path)));
			stream.write(bytes);
			ImageUtility.resize(path, path, IMAGE_WIDTH, IMAGE_HEIGHT);
			
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:productList";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/productList")
	public String productListPage(Model model) {
		List<Product> productList = productService.findAll();
		model.addAttribute("productList", productList);	

		return "productList";
		
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/productInfo")
	public String productInfo(@RequestParam("id") Long id, Model model) {
		Product product = productService.findOne(id);
		model.addAttribute("product", product);
		
		return "productInfo";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/updateProduct")
	public String updateProduct(@RequestParam("id") Long id, Model model) {
		Product product = productService.findOne(id);
		LOG.debug("product.getType(): "+product.getType().getName());
		LOG.debug("product.getCategory(): "+product.getCategory());
		List<Category> categories = categoryService.findByType(product.getType());
		LOG.debug("categories: " + categories);
		DataTransfer dt = new DataTransfer(product, categories, product.getProductAttributes());
		model.addAttribute("dataTransfer", dt);
		
		return "updateProduct";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/updateProduct", method=RequestMethod.POST)
	public String updateProductPost(@ModelAttribute("dataTransfer") DataTransfer dataTransfer, HttpServletRequest request) {
		Product product = dataTransfer.getProduct();
		List<ProductAttribute> attributeList = product.getProductAttributes();
		
		//save type
		Long typeId = product.getType().getId();
		Type type = typeService.findOne(typeId);
		product.setType(type);
		
		//save category
		Long categoryId = product.getCategory().getId();
		Category categorySelected = categoryService.findOne(categoryId);
		List<Product> productsOfSelCat = productService.findByCategory(categorySelected);
		boolean categoryChanged=true;
		for(Product p: productsOfSelCat){ //verifying if admin has changed category
			if(product.getId()==p.getId()){
				categoryChanged = false;
				break;
			}
		}
		if(categoryChanged){ // if category has changed
			//update qty in old category
			Product productInRepository = productService.findOne(product.getId());
			Long oldCatgoryId = productInRepository.getCategory().getId();
			Category oldCategory = categoryService.findOne(oldCatgoryId);
			oldCategory.setQty(oldCategory.getQty()-1);
			
			//update qty new category
			Category category = categoryService.findOne(categoryId);
			category.setQty(category.getQty()+1);
			categoryService.save(category);
			
			//association of new category to product
			product.setCategory(category);
		}
		
		
		//save product
		product.setProductAttributes(null);
		productService.save(product);
		
		//save attributes
		if(attributeList!=null && !attributeList.isEmpty()){
			List<ProductAttribute> oldAttributes = attributeService.findByProduct(product);
			if(oldAttributes!=null && !oldAttributes.isEmpty()){
				for(ProductAttribute oldAttribute: oldAttributes){ //verifying if attribute has been removed
					boolean found=false;
					for(ProductAttribute attribute: attributeList){
						if(oldAttribute.getId()==attribute.getId()){
							found=true;
						}
					}
					if(!found){
						attributeService.deleteById(oldAttribute.getId());
					}
				}
			}
			for(ProductAttribute attribute: attributeList){
				if(attribute.getName()!=null && !Objects.equals(attribute.getName(), "")){
					attribute.setProduct(product);
					attributeService.save(attribute);
				}
			}
		}
		
		LOG.info("Update Product: Name: "+product.getName()+", Type: "+product.getType().getName()
				+", CategoryId: "+product.getCategory().getId()+", AttributeList: "+product.getProductAttributes()
				+", ShippingWeight: "+product.getShippingWeight()+", listPrice: "+product.getListPrice()
				+", OurPrice: "+product.getOurPrice()+", SKU: "+product.getInStockNumber()
				+", Sale"+product.isSale()+", New: "+product.isNewProduct()
				+", Status: "+product.isActive()+", Description: "+product.getDescription());
		
		MultipartFile productCategory = product.getProductCategory();
		MultipartFile productDetail1 = product.getProductDetail1();
		MultipartFile productDetail2 = product.getProductDetail2();
		MultipartFile productDetail3 = product.getProductDetail3();
		MultipartFile latestImage = product.getLatestImage();
		
		if(!productCategory.isEmpty()) {
			try {
				byte[] bytes = productCategory.getBytes();
				String name = product.getId() + "-1.png";
				String path = "src/main/resources/static/image/product/"+name;
				
				Files.delete(Paths.get(path));
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(path)));
				stream.write(bytes);
				ImageUtility.resize(path, path, IMAGE_WIDTH, IMAGE_HEIGHT);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(!productDetail1.isEmpty()) {
			try {
				byte[] bytes = productDetail1.getBytes();
				String name = product.getId() + "-2.png";
				String path = "src/main/resources/static/image/product/"+name;
				
				Files.delete(Paths.get(path));
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(path)));
				stream.write(bytes);
				ImageUtility.resize(path, path, IMAGE_WIDTH, IMAGE_HEIGHT);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(!productDetail2.isEmpty()) {
			try {
				byte[] bytes = productDetail2.getBytes();
				String name = product.getId() + "-3.png";
				String path = "src/main/resources/static/image/product/"+name;
				
				Files.delete(Paths.get(path));
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(path)));
				stream.write(bytes);
				ImageUtility.resize(path, path, IMAGE_WIDTH, IMAGE_HEIGHT);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(!productDetail3.isEmpty()) {
			try {
				byte[] bytes = productDetail3.getBytes();
				String name = product.getId() + "-4.png";
				String path = "src/main/resources/static/image/product/"+name;
				
				Files.delete(Paths.get(path));
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(path)));
				stream.write(bytes);
				ImageUtility.resize(path, path, IMAGE_WIDTH, IMAGE_HEIGHT);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(!latestImage.isEmpty()) {
			try {
				byte[] bytes = latestImage.getBytes();
				String name = product.getId() + "-5.png";
				String path = "src/main/resources/static/image/product/"+name;
				
				Files.delete(Paths.get(path));
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(path)));
				stream.write(bytes);
				ImageUtility.resize(path, path, IMAGE_WIDTH, IMAGE_HEIGHT);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/product/productInfo?id="+product.getId();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public @ResponseBody Boolean remove(
			@RequestBody String id, Model model) {
		try {
			Product product = productService.findOne(Long.parseLong(id.substring(14)));
			productService.removeOne(Long.parseLong(id.substring(14)));
			Category category = product.getCategory();
			category.setQty(category.getQty()-1);
			categoryService.save(category);
        } catch (Exception e){
        	e.printStackTrace();
			return new Boolean(false);
		}
	
		List<Product> productList = productService.findAll();
		model.addAttribute("productList", productList);
		
		return new Boolean(true);
	}
}
