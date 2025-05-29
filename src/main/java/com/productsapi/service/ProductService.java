package com.productsapi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.productsapi.model.Category;
import com.productsapi.model.Product;
import com.productsapi.repository.interfaces.ProductGateway;

@Service
public class ProductService {

	private final ProductGateway repository;

	public ProductService(ProductGateway repository) {
		this.repository = repository;
	}
	
	public Iterable<Product> getAllProducts() {
		return repository.findAll();
	}
	
	public Product getProduct(int id) {
		return repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Product with Id:" + id + " not Found"));
	}
	
	public Product addProduct(Product product) {
		return repository.add(product);
	}
	
	public void updateProduct(Product product) {
		repository.update(product);
	}
	
	public void deleteProduct(int id) {
		repository.removeById(id);
	}
	
	public List<Product> getProductsByCategory(Category category){
		return repository.findByCategory(category);		
	}
	
	public List<Product> getProductsByPriceRange(BigDecimal min, BigDecimal max){
		return repository.findByPriceRange(min, max);	
	}
}
