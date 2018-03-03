package com.popeyestore.service;

import java.util.List;

import com.popeyestore.domain.Category;
import com.popeyestore.domain.Product;
import com.popeyestore.domain.ProductToCategory;

public interface ProductToCategoryService {

	ProductToCategory findOne(Long id);
	
	List<ProductToCategory> findByProduct(Product product);
	
	void removeOne(Long id);
	
	ProductToCategory save(ProductToCategory productToCategory);

	List<ProductToCategory> findByCategory(Category category);

}
