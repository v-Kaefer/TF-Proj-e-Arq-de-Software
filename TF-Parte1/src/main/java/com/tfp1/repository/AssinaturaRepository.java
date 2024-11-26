package com.tfp1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfp1.model.Assinatura;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    List<Assinatura> findByClienteCodigo(Long codCliente);
    List<Assinatura> findByAplicativoCodigo(Long codAplicativo);
}

