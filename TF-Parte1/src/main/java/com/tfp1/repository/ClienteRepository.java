package com.tfp1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfp1.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
