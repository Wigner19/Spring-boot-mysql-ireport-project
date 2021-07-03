package com.ti.formproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.formproject.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
