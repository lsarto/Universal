package com.popeyestore.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.popeyestore.adminportal.service.AdminProductService;
import com.popeyestore.domain.Category;
import com.popeyestore.domain.Product;
import com.popeyestore.repository.ProductRepository;

@Service
public class AdminProductServiceImpl implements AdminProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Product findOne(Long id) {
		return productRepository.findOne(id);
	}
	
	@Override
	public void removeOne(Long id) {
		productRepository.delete(id);
	}

	@Override
	public List<Product> findByCategory(Category categorySelected) {
		return productRepository.findByCategory(categorySelected);
	}
}
