package com.tfp1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfp1.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    public List<Pagamento> findByAssinaturaClienteCodigo(Long codigo);

}
