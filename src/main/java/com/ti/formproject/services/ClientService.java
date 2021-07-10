package com.ti.formproject.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ti.formproject.entities.Client;
import com.ti.formproject.repositories.ClientRepository;
import com.ti.formproject.services.exceptions.AlreadyRegisteredException;
import com.ti.formproject.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public List<Client> findAll() {
		return repository.findAll();
	}
	
	public Client findById(Long id) {
		Optional<Client> client = repository.findById(id);
		return client.orElseThrow(() -> new ResourceNotFoundException("Client not found"));
	}
	
	public Client insert(Client obj) {
		alreadyExists(obj);
		return repository.save(obj);
	}
	
	public Client update(Long id, Client obj) {
		try {
			Optional<Client> client = repository.findById(id);
			updateData(client, obj);
			return repository.save(obj);			
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Client not found!");
		}
	}

	private void updateData(Optional<Client> client, Client obj) {
		alreadyExists(obj);
		client.get().setName(obj.getName());			
		client.get().setEmail(obj.getEmail());			
		client.get().setPassword(obj.getPassword());			
	}
	
	public void alreadyExists(Client obj) {
		List<Client> list = repository.findAll();
		for (Client x : list) {
			if (x.equals(obj)) {
				throw new AlreadyRegisteredException();
			}
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);			
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Client not found");
		}
	}
}
