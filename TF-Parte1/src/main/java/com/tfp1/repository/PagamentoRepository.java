package com.tfp1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfp1.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
