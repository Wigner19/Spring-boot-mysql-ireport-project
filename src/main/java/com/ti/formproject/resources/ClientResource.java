package com.ti.formproject.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.formproject.entities.Client;

@RestController
public class ClientResource {

	@RequestMapping(value = "/")
	public ResponseEntity<Client> findAll() {
		Client c1 = new Client(1L, "Wigner", "wigner12@gmail.com", "123456789");
		return ResponseEntity.ok().body(c1);
	}
}
