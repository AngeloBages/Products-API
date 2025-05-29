package com.productsapi.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.productsapi.model.Category;
import com.productsapi.model.Product;
import com.productsapi.repository.interfaces.ProductGateway;
import com.productsapi.repository.interfaces.ProductJpaRepository;

@Repository
public class ProductJpaAdapter implements ProductGateway{

	private final ProductJpaRepository repository;
	
	public ProductJpaAdapter(ProductJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Optional<Product> findById(Integer id) {
		return repository.findById(id);
	}
	
	@Override
	public Iterable<Product> findAll() {
		return repository.findAll();
	}

	@Override
	public Product add(Product product) {
		return repository.save(product);
	}

	@Override
	public void update(Product product) {
		repository.save(product);
	}

	@Override
	public void removeById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return repository.findByCategory(category);
	}

	@Override
	public List<Product> findByPriceRange(BigDecimal min, BigDecimal max) {
		return repository.findByPriceRange(min, max);
	}
}
