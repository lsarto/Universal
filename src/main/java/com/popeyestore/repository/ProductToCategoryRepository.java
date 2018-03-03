package com.popeyestore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.popeyestore.domain.Category;
import com.popeyestore.domain.Product;
import com.popeyestore.domain.ProductToCategory;

public interface ProductToCategoryRepository extends CrudRepository<ProductToCategory, Long>{
	
	ProductToCategory findOne(Long id);
	
	List<ProductToCategory> findByProduct(Product product);

	List<ProductToCategory> findByCategory(Category category);

}
