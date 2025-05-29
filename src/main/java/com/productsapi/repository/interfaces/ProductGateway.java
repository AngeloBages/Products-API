package com.productsapi.repository.interfaces;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.productsapi.model.Category;
import com.productsapi.model.Product;

public interface ProductGateway {

	Optional<Product> findById(Integer id);
	Iterable<Product> findAll();
	Product add(Product product);
	void update(Product product);
	void removeById(Integer id);
	List<Product> findByCategory(Category category);
	List<Product> findByPriceRange(BigDecimal min, BigDecimal max);
}
