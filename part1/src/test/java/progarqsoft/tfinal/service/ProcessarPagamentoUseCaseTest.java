package progarqsoft.tfinal.service;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import progarqsoft.tfinal.model.Aplicativo;
import progarqsoft.tfinal.model.Assinatura;
import progarqsoft.tfinal.model.Cliente;
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

    @BeforeEach
    public void setUp() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setEmail("cliente@teste.com");
        clienteRepository.save(cliente);

        Aplicativo aplicativo = new Aplicativo();
        aplicativo.setNome("Aplicativo Teste");
        aplicativo.setCustoMensal(10.0f);
        aplicativoRepository.save(aplicativo);

        Assinatura assinatura = new Assinatura();
        assinatura.setCliente(cliente);
        assinatura.setAplicativo(aplicativo);
        assinatura.setInicioVigencia(LocalDate.now());
        assinatura.setFimVigencia(LocalDate.now().plusDays(7));
        assinaturaRepository.save(assinatura);
    }

    @Test
    public void processarPagamentoTest() {
        Assinatura assinatura = assinaturaRepository.findAll().get(0);
        Long assinaturaId = assinatura.getCodigo();
        Float valorPago = 10.0f;
        String promocao = "PAGUE_30_GANHE_45";

        Pagamento pagamento = processarPagamentoUseCase.processarPagamento(assinaturaId, valorPago, promocao);

        assertNotNull(pagamento);
        assertNotNull(pagamento.getCodigo());
    }
}