package com.ti.formproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.formproject.entities.OrderItem;
import com.ti.formproject.services.OrderItemService;

@RestController
public class OrderItemResource {
	
	@Autowired
	private OrderItemService service;

	@RequestMapping(value = "/items")
	public ResponseEntity<List<OrderItem>> findAll() {
		List<OrderItem> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
