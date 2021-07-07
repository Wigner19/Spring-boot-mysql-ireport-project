package com.ti.formproject.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ti.formproject.entities.Client;
import com.ti.formproject.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	
	@Autowired
	private ClientService service;

//	@GetMapping
//	public ResponseEntity<List<Client>> findAll() {
//		List<Client> list = service.findAll();
//		return ResponseEntity.ok().body(list);
//	}
	
	@GetMapping
	public ModelAndView findAll() {
		List<Client> list = service.findAll();
		ModelAndView mav = new ModelAndView("clients");
		mav.addObject("list", list);
		return mav;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		Client client = service.findById(id);
		return ResponseEntity.ok().body(client);
	}
	
	@PostMapping
	public ResponseEntity<Client> insert(@RequestBody Client obj) {
		Client client = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(client);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client obj) {
		Client client = service.update(id, obj);
		return ResponseEntity.ok().body(client);
	}
	
	@GetMapping(value = "/delete")
	public void delete(@PathParam(value="client_user") Long client_user, HttpServletResponse httpResponse) throws IOException {
		service.delete(client_user);
		httpResponse.sendRedirect("/clients");
	}
}
