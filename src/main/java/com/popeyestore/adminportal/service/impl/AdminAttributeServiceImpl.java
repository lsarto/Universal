package com.popeyestore.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popeyestore.adminportal.repository.ProductAttributeRepository;
import com.popeyestore.adminportal.service.AdminAttributeService;
import com.popeyestore.domain.Product;
import com.popeyestore.domain.ProductAttribute;

@Service
public class AdminAttributeServiceImpl implements AdminAttributeService{
	@Autowired
	private ProductAttributeRepository attributeRepository;
	
	@Override
	public ProductAttribute save(ProductAttribute productAttribute) {
		return attributeRepository.save(productAttribute);
	}

	@Override
	public ProductAttribute findOne(Long id) {
		return attributeRepository.findOne(id);
	}

	@Override
	public void deleteById(Long id) {
		attributeRepository.delete(id);
	}

	@Override
	public List<ProductAttribute> findByProduct(Product product) {
		return attributeRepository.findByProduct(product);
	}

}
