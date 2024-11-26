package com.engsoft2.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.engsoft2.projeto.model.Pagamento;
import com.engsoft2.projeto.repository.PagamentoRepository;
import com.engsoft2.projeto.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    @Autowired
    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public Pagamento buscarPorId(Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com ID: " + id));
    }

    public Pagamento criar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento atualizar(Long id, Pagamento pagamento) {
        Pagamento pagamentoExistente = buscarPorId(id);
        pagamentoExistente.setValor(pagamento.getValor());
        pagamentoExistente.setData(pagamento.getData());
        pagamentoExistente.setAssinatura(pagamento.getAssinatura());
        return pagamentoRepository.save(pagamentoExistente);
    }

    public void deletar(Long id) {
        Pagamento pagamento = buscarPorId(id);
        pagamentoRepository.delete(pagamento);
    }
}
