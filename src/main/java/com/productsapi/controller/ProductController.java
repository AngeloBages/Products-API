package com.productsapi.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productsapi.model.Category;
import com.productsapi.model.Product;
import com.productsapi.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Product>> getAllProducts() {
		Iterable<Product> products = productService.getAllProducts();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
		return new ResponseEntity<>(products, headers, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") int id){
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(productService.getProduct(id));
	}
	
	@PostMapping
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		productService.addProduct(product);
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body("Product Successfully Created! ProductID: " + product.getId());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable("id") int id) {
		product.setId(id);
		productService.updateProduct(product);
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body("Product Successfully Updated! Product: " + product);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
		productService.deleteProduct(id);
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body("Product Successfully Deleted!");
	}
	
	@GetMapping(params = "category")
	public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam("category") Category category){
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(productService.getProductsByCategory(category));
	}
	
	@GetMapping(params = { "min", "max" })
    public ResponseEntity<List<Product>> getByPriceRange(@RequestParam("min") BigDecimal min, @RequestParam("max") BigDecimal max) {
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(productService.getProductsByPriceRange(min, max));
    }
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
		return ResponseEntity.status(HttpStatusCode.valueOf(404))
				.contentType(MediaType.APPLICATION_JSON)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		return ResponseEntity.internalServerError().body(ex.getMessage());
	}
}
