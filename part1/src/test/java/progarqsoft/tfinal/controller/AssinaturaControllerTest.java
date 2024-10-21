package progarqsoft.tfinal.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser // Simula um usuário autenticado com Spring Security (Spring Boot).
public class AssinaturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void criarAssinaturaTest() throws Exception {
        String jsonRequest = "{\"clienteId\":1,\"aplicativoId\":1}";

        mockMvc.perform(post("/api/assinaturas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());
    }
}