package com.ti.formproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.formproject.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
