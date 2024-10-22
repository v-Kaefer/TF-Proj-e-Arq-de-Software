package progarqsoft.tfinal.service;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import progarqsoft.tfinal.model.Aplicativo;
import progarqsoft.tfinal.model.Assinatura;
import progarqsoft.tfinal.model.Cliente;
import progarqsoft.tfinal.model.Pagamento;
import progarqsoft.tfinal.repository.AplicativoRepository;
import progarqsoft.tfinal.repository.AssinaturaRepository;
import progarqsoft.tfinal.repository.ClienteRepository;
import progarqsoft.tfinal.repository.PagamentoRepository;

@SpringBootTest
@ActiveProfiles("test")
public class ProcessarPagamentoUseCaseTest {

    @Autowired
    private ProcessarPagamentoUseCase processarPagamentoUseCase;

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AplicativoRepository aplicativoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    private Cliente cliente;
    private Aplicativo aplicativo;
    private Assinatura assinatura;

    @BeforeEach
    public void setUp() {
        // Limpar o banco de dados de teste na ordem correta
        pagamentoRepository.deleteAll();
        assinaturaRepository.deleteAll();
        clienteRepository.deleteAll();
        aplicativoRepository.deleteAll();

        // Criar cliente
        cliente = clienteRepository.save(new Cliente("Cliente Teste", "cliente@teste.com"));

        // Criar aplicativo
        aplicativo = aplicativoRepository.save(new Aplicativo("Aplicativo Teste", 10.0f));

        // Criar assinatura
        assinatura = assinaturaRepository.save(new Assinatura(cliente, aplicativo, LocalDate.now(), LocalDate.now().plusDays(7)));
    }

    @Test
    public void processarPagamentoTest() {
        Long assinaturaId = assinatura.getCodigo();
        Float valorPago = 10.0f;
        String promocao = "PAGUE_30_GANHE_45";

        Pagamento pagamento = processarPagamentoUseCase.processarPagamento(assinaturaId, valorPago, promocao);

        assertNotNull(pagamento);
        assertNotNull(pagamento.getCodigo());
    }

    @Test
    public void processarPagamentoAssinaturaAtivaTest() {
        Long assinaturaId = assinatura.getCodigo();
        Float valorPago = 10.0f;
        String promocao = "PAGUE_30_GANHE_45";

        Pagamento pagamento = processarPagamentoUseCase.processarPagamento(assinaturaId, valorPago, promocao);

        assertNotNull(pagamento);
        assertNotNull(pagamento.getCodigo());
        assertEquals(assinatura.getFimVigencia().plusDays(45), assinaturaRepository.findById(assinaturaId).get().getFimVigencia());
    }

    @Test
    public void processarPagamentoAssinaturaExpiradaTest() {
        Assinatura assinaturaExpirada = assinaturaRepository.save(new Assinatura(cliente, aplicativo, LocalDate.now().minusDays(60), LocalDate.now().minusDays(30)));
        Long assinaturaId = assinaturaExpirada.getCodigo();
        Float valorPago = 10.0f;
        String promocao = "PAGUE_30_GANHE_45";

        Pagamento pagamento = processarPagamentoUseCase.processarPagamento(assinaturaId, valorPago, promocao);

        assertNotNull(pagamento);
        assertNotNull(pagamento.getCodigo());
        assertEquals(LocalDate.now().plusDays(45), assinaturaRepository.findById(assinaturaId).get().getFimVigencia());
    }

    @Test
    public void processarPagamentoSemPromocaoParaRenovacaoTest() {
        Long assinaturaId = assinatura.getCodigo();
        Float valorPago = 10.0f;

        // Primeiro pagamento com promoção
        Pagamento pagamento1 = processarPagamentoUseCase.processarPagamento(assinaturaId, valorPago, "PAGUE_30_GANHE_45");
        assertNotNull(pagamento1);
        assertNotNull(pagamento1.getCodigo());

        // Atualizar a data de fim de vigência para refletir o pagamento com promoção
        assinatura.setFimVigencia(assinatura.getFimVigencia().plusDays(45));
        assinaturaRepository.save(assinatura);

        // Segundo pagamento sem promoção
        Pagamento pagamento2 = processarPagamentoUseCase.processarPagamento(assinaturaId, valorPago, null);
        assertNotNull(pagamento2);
        assertNotNull(pagamento2.getCodigo());
        assertEquals(assinatura.getFimVigencia().plusDays(30), assinaturaRepository.findById(assinaturaId).get().getFimVigencia());
    }

    @Test
    public void processarPagamentoParaNovoClienteComPromocaoTest() {
        Cliente novoCliente = clienteRepository.save(new Cliente("Novo Cliente", "novo@cliente.com"));
        Assinatura novaAssinatura = assinaturaRepository.save(new Assinatura(novoCliente, aplicativo, LocalDate.now(), LocalDate.now().plusDays(7)));

        Long assinaturaId = novaAssinatura.getCodigo();
        Float valorPago = 10.0f;
        String promocao = "PAGUE_30_GANHE_45";

        Pagamento pagamento = processarPagamentoUseCase.processarPagamento(assinaturaId, valorPago, promocao);

        assertNotNull(pagamento);
        assertNotNull(pagamento.getCodigo());
        assertEquals(novaAssinatura.getFimVigencia().plusDays(45), assinaturaRepository.findById(assinaturaId).get().getFimVigencia());
    }

    @Test
    public void processarPagamentoComPromocaoAnualTest() {
        Long assinaturaId = assinatura.getCodigo();
        Float valorPago = 40.0f;
        String promocao = "ANUAL_40";

        // Primeiro pagamento com promoção anual
        Pagamento pagamento1 = processarPagamentoUseCase.processarPagamento(assinaturaId, valorPago, promocao);
        assertNotNull(pagamento1);
        assertNotNull(pagamento1.getCodigo());
        assertEquals(assinatura.getFimVigencia().plusDays(365), assinaturaRepository.findById(assinaturaId).get().getFimVigencia());

        // Simular passagem de 3 meses
        assinatura.setFimVigencia(LocalDate.now().plusMonths(3));
        assinaturaRepository.save(assinatura);

        // Tentativa de renovação com promoção anual após 3 meses
        Pagamento pagamento2 = processarPagamentoUseCase.processarPagamento(assinaturaId, valorPago, promocao);
        assertNotNull(pagamento2);
        assertNotNull(pagamento2.getCodigo());
        assertEquals(assinatura.getFimVigencia().plusDays(365), assinaturaRepository.findById(assinaturaId).get().getFimVigencia());

        // Simular passagem de mais 1 mês (total 4 meses)
        assinatura.setFimVigencia(LocalDate.now().plusMonths(4));
        assinaturaRepository.save(assinatura);

        // Tentativa de renovação com promoção anual após 4 meses (deve falhar e cobrar 80)
        Pagamento pagamento3 = processarPagamentoUseCase.processarPagamento(assinaturaId, 80.0f, null);
        assertNotNull(pagamento3);
        assertNotNull(pagamento3.getCodigo());
        assertEquals(assinatura.getFimVigencia().plusDays(365), assinaturaRepository.findById(assinaturaId).get().getFimVigencia());
    }

    @Test
    public void processarPagamentoComPromocaoAnualNoTerceiroMesTest() {
        Long assinaturaId = assinatura.getCodigo();
        Float valorPago = 40.0f;
        String promocao = "ANUAL_40";

        // Primeiro pagamento com promoção anual
        Pagamento pagamento1 = processarPagamentoUseCase.processarPagamento(assinaturaId, valorPago, promocao);
        assertNotNull(pagamento1);
        assertNotNull(pagamento1.getCodigo());
        assertEquals(assinatura.getFimVigencia().plusDays(365), assinaturaRepository.findById(assinaturaId).get().getFimVigencia());

        // Simular passagem de 3 meses
        assinatura.setFimVigencia(LocalDate.now().plusMonths(3));
        assinaturaRepository.save(assinatura);

        // Tentativa de renovação com promoção anual no terceiro mês
        Pagamento pagamento2 = processarPagamentoUseCase.processarPagamento(assinaturaId, valorPago, promocao);
        assertNotNull(pagamento2);
        assertNotNull(pagamento2.getCodigo());
        assertEquals(assinatura.getFimVigencia().plusDays(365), assinaturaRepository.findById(assinaturaId).get().getFimVigencia());
    }
}