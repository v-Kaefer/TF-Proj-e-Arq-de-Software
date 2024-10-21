package progarqsoft.tfinal.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import progarqsoft.tfinal.model.Pagamento;
import progarqsoft.tfinal.repository.AplicativoRepository;
import progarqsoft.tfinal.repository.AssinaturaRepository;
import progarqsoft.tfinal.repository.ClienteRepository;

@SpringBootTest
public class ProcessarPagamentoUseCaseTest {

    @Autowired
    private ProcessarPagamentoUseCase processarPagamentoUseCase;

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AplicativoRepository aplicativoRepository;

    @Test
    public void processarPagamentoTest() {
        Long assinaturaId = 1L;
        Float valorPago = 10.0f;
        String promocao = "PAGUE_30_GANHE_45";

        Pagamento pagamento = processarPagamentoUseCase.processarPagamento(assinaturaId, valorPago, promocao);

        assertNotNull(pagamento);
        assertNotNull(pagamento.getCodigo());
    }
}