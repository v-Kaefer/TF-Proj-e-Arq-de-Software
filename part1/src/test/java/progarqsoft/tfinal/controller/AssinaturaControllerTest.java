package progarqsoft.tfinal.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import progarqsoft.tfinal.model.Aplicativo;
import progarqsoft.tfinal.model.Cliente;
import progarqsoft.tfinal.repository.AplicativoRepository;
import progarqsoft.tfinal.repository.ClienteRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AssinaturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AplicativoRepository aplicativoRepository;

    private Long clienteId;
    private Long aplicativoId;

    @BeforeEach
    public void setUp() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setEmail("cliente@teste.com");
        cliente = clienteRepository.save(cliente);
        clienteId = cliente.getCodigo();

        Aplicativo aplicativo = new Aplicativo();
        aplicativo.setNome("Aplicativo Teste");
        aplicativo.setCustoMensal(10.0f);
        aplicativo = aplicativoRepository.save(aplicativo);
        aplicativoId = aplicativo.getCodigo();
    }

    @Test
    public void criarAssinaturaTest() throws Exception {
        String jsonRequest = String.format("{\"clienteId\":%d,\"aplicativoId\":%d}", clienteId, aplicativoId);

        mockMvc.perform(post("/api/assinaturas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());
    }
}