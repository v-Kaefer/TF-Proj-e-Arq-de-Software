package main.java.com.tfp1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfp1.model.Pagamento;
import com.tfp1.repository.PagamentoRepository;

import main.java.com.tfp1.exception.ResourceNotFoundException;

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
        pagamentoExistente.setValorPago(pagamento.getValorPago());
        pagamentoExistente.setDataPagamento(pagamento.getDataPagamento());
        pagamentoExistente.setAssinatura(pagamento.getAssinatura());
        return pagamentoRepository.save(pagamentoExistente);
    }

    public void deletar(Long id) {
        Pagamento pagamento = buscarPorId(id);
        pagamentoRepository.delete(pagamento);
    }
}
