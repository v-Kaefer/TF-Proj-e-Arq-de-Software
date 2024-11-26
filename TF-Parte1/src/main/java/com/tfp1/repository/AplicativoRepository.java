package com.tfp1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfp1.model.Aplicativo;

public interface AplicativoRepository extends JpaRepository<Aplicativo, Long> {
    
}
