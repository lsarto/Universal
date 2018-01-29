package com.popeyestore.adminportal.service;

import java.util.List;

import com.popeyestore.adminportal.domain.Product;
import com.popeyestore.adminportal.domain.ProductAttribute;

public interface AttributeService {

	ProductAttribute save(ProductAttribute productAttribute);

	ProductAttribute findOne(Long id);
	
	void deleteById(Long id);
	
	List<ProductAttribute> findByProduct(Product product);
}
