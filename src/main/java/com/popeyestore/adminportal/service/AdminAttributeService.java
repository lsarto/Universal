package com.popeyestore.adminportal.service;

import java.util.List;

import com.popeyestore.domain.Product;
import com.popeyestore.domain.ProductAttribute;


public interface AdminAttributeService {

	ProductAttribute save(ProductAttribute productAttribute);

	ProductAttribute findOne(Long id);
	
	void deleteById(Long id);
	
	List<ProductAttribute> findByProduct(Product product);
}
