package com.popeyestore.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.popeyestore.domain.Product;
import com.popeyestore.domain.ProductAttribute;




public interface ProductAttributeRepository extends CrudRepository<ProductAttribute, Long>{
	void deleteById(Long id);
	
	List<ProductAttribute> findByProduct(Product product);
}
