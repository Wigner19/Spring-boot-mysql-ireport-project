package com.ti.formproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.formproject.entities.Product;
import com.ti.formproject.services.ProductService;

@RestController
public class ProductResource {
	
	@Autowired
	private ProductService service;

	@RequestMapping(value = "/products")
	public ResponseEntity<List<Product>> findAll() {
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
