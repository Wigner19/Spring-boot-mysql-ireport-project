package com.ti.formproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.formproject.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
