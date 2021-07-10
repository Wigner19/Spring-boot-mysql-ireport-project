package com.ti.formproject.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ti.formproject.entities.Client;
import com.ti.formproject.entities.Product;
import com.ti.formproject.repositories.ClientRepository;
import com.ti.formproject.repositories.ProductRepository;

@Configuration
@Profile("dev")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Client c1 = new Client(null, "Wigner", "wigner12@gmail.com", "123456789");
		Client c2 = new Client(null, "Luiz", "luiz@gmail.com", "1239");
		Client c3 = new Client(null, "Andre", "andre@gmail.com", "123456");
		Client c4 = new Client(null, "José", "jose@gmail.com", "123456");
	
		clientRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
		
		Product p1 = new Product(null, "TV", 2500.00);
		Product p2 = new Product(null, "Galaxy Note 20 Ultra", 5000.00);
		Product p3 = new Product(null, "Water bottle", 2.50);
		Product p4 = new Product(null, "Netflix", 22.50);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
	}

}
