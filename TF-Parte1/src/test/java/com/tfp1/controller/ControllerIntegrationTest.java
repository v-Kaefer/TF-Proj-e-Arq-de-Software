package test.java.com.tfp1.controller;

import com.tfp1.projeto.model.Recurso; // Substitua por uma classe do seu modelo
import com.tfp1.projeto.repository.RecursoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RecursoRepository repository; // Substitua pelo repositório correto.

    @Test
    void testGetAllRecursos() throws Exception {
        // Simula criação de recurso no banco de dados H2
        Recurso recurso = new Recurso();
        recurso.setNome("Teste");
        repository.save(recurso);

        mockMvc.perform(get("/recursos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Teste"));
    }
}
