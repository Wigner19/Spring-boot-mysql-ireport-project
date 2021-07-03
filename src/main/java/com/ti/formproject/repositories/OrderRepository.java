package com.ti.formproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.formproject.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
