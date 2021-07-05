package com.ti.formproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.formproject.entities.Order;
import com.ti.formproject.repositories.OrderRepository;
import com.ti.formproject.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> client = repository.findById(id);
		return client.orElseThrow(() -> new ResourceNotFoundException("Order not found"));
	}
}
