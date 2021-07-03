package com.ti.formproject.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ti.formproject.entities.Client;
import com.ti.formproject.entities.Order;
import com.ti.formproject.repositories.ClientRepository;
import com.ti.formproject.repositories.OrderRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Client c1 = new Client(null, "Wigner", "wigner12@gmail.com", "123456789");
		Client c2 = new Client(null, "Luiz", "luiz@gmail.com", "1239");
		Client c3 = new Client(null, "Andre", "andre@gmail.com", "123456");
	
		Order o1 = new Order(null, Instant.parse("2021-07-22T15:12:22Z"), c1);
		Order o2 = new Order(null, Instant.parse("2021-05-15T02:02:12Z"), c2);
		Order o3 = new Order(null, Instant.parse("2021-02-20T00:22:52Z"), c3);
		Order o4 = new Order(null, Instant.parse("2021-12-07T17:52:02Z"), c1);
		
		clientRepository.saveAll(Arrays.asList(c1, c2, c3));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4));
	}

}
