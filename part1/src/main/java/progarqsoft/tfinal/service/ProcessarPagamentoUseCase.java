package progarqsoft.tfinal.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progarqsoft.tfinal.model.Assinatura;
import progarqsoft.tfinal.model.Pagamento;
import progarqsoft.tfinal.repository.AssinaturaRepository;
import progarqsoft.tfinal.repository.PagamentoRepository;

@Service
public class ProcessarPagamentoUseCase {
    private final AssinaturaRepository assinaturaRepository;
    private final PagamentoRepository pagamentoRepository;

    @Autowired
    public ProcessarPagamentoUseCase(AssinaturaRepository assinaturaRepository, PagamentoRepository pagamentoRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    public Pagamento processarPagamento(Long assinaturaId, Float valorPago, String promocao) {
        Assinatura assinatura = assinaturaRepository.findById(assinaturaId).orElseThrow(() -> new IllegalArgumentException("Assinatura não encontrada"));

        LocalDate novaDataValidade = calcularNovaDataValidade(assinatura, valorPago, promocao);

        Pagamento pagamento = new Pagamento();
        pagamento.setAssinatura(assinatura);
        pagamento.setValorPago(valorPago);
        pagamento.setDataPagamento(LocalDate.now());
        pagamento.setPromocao(promocao);

        assinatura.setFimVigencia(novaDataValidade);
        assinaturaRepository.save(assinatura);
        return pagamentoRepository.save(pagamento);
    }

    private LocalDate calcularNovaDataValidade(Assinatura assinatura, Float valorPago, String promocao) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate fimVigenciaAtual = assinatura.getFimVigencia();

        if (promocao.equals("ANUAL_40")) {
            return fimVigenciaAtual.isBefore(dataAtual) ? dataAtual.plusDays(365) : fimVigenciaAtual.plusDays(365);
        } else if (promocao.equals("PAGUE_30_GANHE_45")) {
            return fimVigenciaAtual.isBefore(dataAtual) ? dataAtual.plusDays(45) : fimVigenciaAtual.plusDays(45);
        } else if (valorPago.equals(assinatura.getAplicativo().getCustoMensal())) {
            return fimVigenciaAtual.isBefore(dataAtual) ? dataAtual.plusDays(30) : fimVigenciaAtual.plusDays(30);
        } else {
            throw new IllegalArgumentException("Valor do pagamento incorreto ou promoção inválida");
        }
    }

    
}