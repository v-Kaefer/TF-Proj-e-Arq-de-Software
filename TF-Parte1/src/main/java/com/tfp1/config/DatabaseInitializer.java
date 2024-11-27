package com.tfp1.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.tfp1.model.Aplicativo;
import com.tfp1.model.Assinatura;
import com.tfp1.model.Cliente;
import com.tfp1.repository.AplicativoRepository;
import com.tfp1.repository.AssinaturaRepository;
import com.tfp1.repository.ClienteRepository;

@Configuration
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AplicativoRepository aplicativoRepository;

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (clienteRepository.count() == 0) {
            List<Cliente> clientes = List.of(
                new Cliente("Cliente 1", "cliente1@example.com"),
                new Cliente("Cliente 2", "cliente2@example.com"),
                new Cliente("Cliente 3", "cliente3@example.com"),
                new Cliente("Cliente 4", "cliente4@example.com"),
                new Cliente("Cliente 5", "cliente5@example.com"),
                new Cliente("Cliente 6", "cliente6@example.com"),
                new Cliente("Cliente 7", "cliente7@example.com"),
                new Cliente("Cliente 8", "cliente8@example.com"),
                new Cliente("Cliente 9", "cliente9@example.com"),
                new Cliente("Cliente 10", "cliente10@example.com")
            );
            clienteRepository.saveAll(clientes);
        }

        if (aplicativoRepository.count() == 0) {
            List<Aplicativo> aplicativos = List.of(
                new Aplicativo("App 1", 10.0f),
                new Aplicativo("App 2", 20.0f),
                new Aplicativo("App 3", 30.0f),
                new Aplicativo("App 4", 40.0f),
                new Aplicativo("App 5", 50.0f)
            );
            aplicativoRepository.saveAll(aplicativos);
        }

        if (assinaturaRepository.count() == 0) {
            List<Assinatura> assinaturas = List.of(
                // 5 assinaturas ativas
                new Assinatura(clienteRepository.findById(1L).get(), aplicativoRepository.findById(1L).get(), LocalDate.now(), LocalDate.now().plusDays(30)),
                new Assinatura(clienteRepository.findById(2L).get(), aplicativoRepository.findById(2L).get(), LocalDate.now(), LocalDate.now().plusDays(30)),
                new Assinatura(clienteRepository.findById(3L).get(), aplicativoRepository.findById(3L).get(), LocalDate.now(), LocalDate.now().plusDays(30)),
                new Assinatura(clienteRepository.findById(4L).get(), aplicativoRepository.findById(4L).get(), LocalDate.now(), LocalDate.now().plusDays(30)),
                new Assinatura(clienteRepository.findById(5L).get(), aplicativoRepository.findById(5L).get(), LocalDate.now(), LocalDate.now().plusDays(30)),
            
                // 4 assinaturas expiradas [Testes de ProcessarPagamentoUseCase]
                new Assinatura(clienteRepository.findById(6L).get(), aplicativoRepository.findById(1L).get(), LocalDate.now().minusDays(60), LocalDate.now().minusDays(30)),
                new Assinatura(clienteRepository.findById(7L).get(), aplicativoRepository.findById(2L).get(), LocalDate.now().minusDays(60), LocalDate.now().minusDays(30)),
                new Assinatura(clienteRepository.findById(8L).get(), aplicativoRepository.findById(3L).get(), LocalDate.now().minusDays(60), LocalDate.now().minusDays(30)),
                new Assinatura(clienteRepository.findById(9L).get(), aplicativoRepository.findById(4L).get(), LocalDate.now().minusDays(60), LocalDate.now().minusDays(30))
                
            );
            assinaturaRepository.saveAll(assinaturas);
        }
    }
}