package com.ti.formproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.formproject.entities.Client;
import com.ti.formproject.services.ClientService;

@RestController
public class ClientResource {
	
	@Autowired
	private ClientService service;

	@RequestMapping(value = "/clients")
	public ResponseEntity<List<Client>> findAll() {
		List<Client> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
