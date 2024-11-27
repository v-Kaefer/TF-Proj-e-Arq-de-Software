package com.tfp1.service;

import com.tfp1.model.Cliente;
import com.tfp1.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteServiceTest {

    @Test
    public void testSalvarCliente() {
        ClienteRepository repo = Mockito.mock(ClienteRepository.class);
        Cliente cliente = new Cliente("Test", "test@example.com");

        Mockito.when(repo.save(cliente)).thenReturn(cliente);
        assertEquals(cliente, repo.save(cliente));
    }
}
