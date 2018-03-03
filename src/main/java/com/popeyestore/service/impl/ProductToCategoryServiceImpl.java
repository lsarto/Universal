package com.popeyestore.service.impl;

import com.popeyestore.domain.Category;
import com.popeyestore.domain.Product;
import com.popeyestore.domain.ProductToCategory;
import com.popeyestore.repository.ProductToCategoryRepository;
import com.popeyestore.service.ProductToCategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductToCategoryServiceImpl implements ProductToCategoryService{
	@Autowired
	private ProductToCategoryRepository productToCategoryRepository;

	@Override
	public ProductToCategory findOne(Long id) {
		return productToCategoryRepository.findOne(id);
	}

	@Override
	public ProductToCategory save(ProductToCategory productToCategory) {
		return productToCategoryRepository.save(productToCategory);
	}

	@Override
	public void removeOne(Long id) {
		productToCategoryRepository.delete(id);
	}

	@Override
	public List<ProductToCategory> findByProduct(Product product) {
		return productToCategoryRepository.findByProduct(product);
	}

	@Override
	public List<ProductToCategory> findByCategory(Category category) {
		return productToCategoryRepository.findByCategory(category);
	}

}
