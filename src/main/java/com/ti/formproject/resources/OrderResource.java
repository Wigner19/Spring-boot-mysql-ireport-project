package com.ti.formproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.formproject.entities.Order;
import com.ti.formproject.services.OrderService;

@RestController
public class OrderResource {
	
	@Autowired
	private OrderService service;

	@RequestMapping(value = "/orders")
	public ResponseEntity<List<Order>> findAll() {
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
