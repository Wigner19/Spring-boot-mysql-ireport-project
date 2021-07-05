package com.ti.formproject.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ti.formproject.entities.Product;
import com.ti.formproject.repositories.ProductRepository;
import com.ti.formproject.services.exceptions.ClientAlreadyExistsException;
import com.ti.formproject.services.exceptions.DatabaseException;
import com.ti.formproject.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> client = repository.findById(id);
		return client.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}
	
	public Product insert(Product obj) {
		List<Product> list = repository.findAll();
		for (Product x : list) {
			if (x.equals(obj)) {
				throw new ClientAlreadyExistsException();
			}
		}
		return repository.save(obj);
	}
	
	public Product update(Long id, Product obj) {
		try {
			Optional<Product> product = repository.findById(id);
			updateData(product, obj);
			return repository.save(product.get());
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Product not found");
		}
	}

	private void updateData(Optional<Product> product, Product obj) {
		if (obj.getName() != null) {
			product.get().setName(obj.getName());			
		}
		
		if(obj.getPrice() != null) {
			product.get().setPrice(obj.getPrice());
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);			
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Product not found");
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
