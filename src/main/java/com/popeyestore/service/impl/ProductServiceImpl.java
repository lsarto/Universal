package com.popeyestore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popeyestore.domain.Category;
import com.popeyestore.domain.Product;
import com.popeyestore.domain.Type;
import com.popeyestore.repository.ProductRepository;
import com.popeyestore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		List<Product> productList = (List<Product>) productRepository.findAll();
		List<Product> activeProductList = new ArrayList<>();

		for (Product product : productList) {
			if (product.isActive()) {
				activeProductList.add(product);
			}
		}

		return activeProductList;
	}

	public Product findOne(Long id) {
		return productRepository.findOne(id);
	}

//	public List<Product> findByCategory(String category) {
//		List<Product> productList = productRepository.findByCategory(category);
//
//		List<Product> activeProductList = new ArrayList<>();
//
//		for (Product product : productList) {
//			if (product.isActive()) {
//				activeProductList.add(product);
//			}
//		}
//
//		return activeProductList;
//	}

	public List<Product> blurrySearch(String name) {
		List<Product> productList = productRepository.findByNameContaining(name);
		List<Product> activeProductList = new ArrayList<>();

		for (Product product : productList) {
			if (product.isActive()) {
				activeProductList.add(product);
			}
		}

		return activeProductList;
	}

	@Override
	public List<Product> findByType(Type type) {
		return productRepository.findByType(type);
	}

	@Override
	public List<Product> findLatest() {
		return productRepository.findByLatestTrue();
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public List<Product> findByPriceBetween(double minPrice, double maxPrice) {
		return productRepository.findByListPriceBetween(minPrice, maxPrice);
	}
}
