package com.ti.formproject.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ti.formproject.entities.Client;
import com.ti.formproject.repositories.ClientRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private ClientRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		Client c1 = new Client(null, "Wigner", "wigner12@gmail.com", "123456789");
		Client c2 = new Client(null, "Luiz", "luiz@gmail.com", "1239");
		Client c3 = new Client(null, "Andre", "andre@gmail.com", "123456");
	
		repository.saveAll(Arrays.asList(c1, c2, c3));
	}

}
