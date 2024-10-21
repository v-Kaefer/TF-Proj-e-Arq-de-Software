package progarqsoft.tfinal;

import progarqsoft.tfinal.model.Aplicativo;
import progarqsoft.tfinal.model.Assinatura;
import progarqsoft.tfinal.model.Cliente;
import progarqsoft.tfinal.repository.AplicativoRepository;
import progarqsoft.tfinal.repository.AssinaturaRepository;
import progarqsoft.tfinal.repository.ClienteRepository;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final ClienteRepository clienteRepository;
    private final AplicativoRepository aplicativoRepository;
    private final AssinaturaRepository assinaturaRepository;

    public DatabaseInitializer(ClienteRepository clienteRepository, AplicativoRepository aplicativoRepository, AssinaturaRepository assinaturaRepository) {
        this.clienteRepository = clienteRepository;
        this.aplicativoRepository = aplicativoRepository;
        this.assinaturaRepository = assinaturaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (clienteRepository.count() == 0) {
            clienteRepository.saveAll(List.of(
                new Cliente("Cliente 1", "cliente1@example.com"),
                new Cliente("Cliente 2", "cliente2@example.com")
                // Adicione mais clientes
            ));
        }

        if (aplicativoRepository.count() == 0) {
            aplicativoRepository.saveAll(List.of(
                new Aplicativo("App 1", 10.0f),
                new Aplicativo("App 2", 20.0f)
                // Adicione mais aplicativos
            ));
        }

        if (assinaturaRepository.count() == 0) {
            assinaturaRepository.saveAll(List.of(
                new Assinatura(clienteRepository.findById(1L).get(), aplicativoRepository.findById(1L).get(), LocalDate.now(), LocalDate.now().plusDays(30)),
                new Assinatura(clienteRepository.findById(2L).get(), aplicativoRepository.findById(2L).get(), LocalDate.now(), LocalDate.now().plusDays(30))
                // Adicione mais assinaturas
            ));
        }
    }
}
