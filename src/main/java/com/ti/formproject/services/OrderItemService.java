package com.ti.formproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.formproject.entities.OrderItem;
import com.ti.formproject.repositories.OrderItemRepository;
import com.ti.formproject.services.exceptions.ResourceNotFoundException;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository repository;
	
	public List<OrderItem> findAll() {
		return repository.findAll();
	}
	
	public OrderItem findById(Long id) {
		Optional<OrderItem> client = repository.findById(id);
		return client.orElseThrow(() -> new ResourceNotFoundException("OrderItem not found"));
	}
}
