package progarqsoft.tfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progarqsoft.tfinal.model.Assinatura;
import progarqsoft.tfinal.model.Cliente;
import progarqsoft.tfinal.model.Aplicativo;
import progarqsoft.tfinal.repository.AssinaturaRepository;
import progarqsoft.tfinal.repository.ClienteRepository;
import progarqsoft.tfinal.repository.AplicativoRepository;

import java.time.LocalDate;

@Service
public class CriarAssinaturaUseCase {
    private final AssinaturaRepository assinaturaRepository;
    private final ClienteRepository clienteRepository;
    private final AplicativoRepository aplicativoRepository;

    @Autowired
    public CriarAssinaturaUseCase(AssinaturaRepository assinaturaRepository, ClienteRepository clienteRepository, AplicativoRepository aplicativoRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.clienteRepository = clienteRepository;
        this.aplicativoRepository = aplicativoRepository;
    }

    public Assinatura execute(Long clienteId, Long aplicativoId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        Aplicativo aplicativo = aplicativoRepository.findById(aplicativoId).orElseThrow(() -> new IllegalArgumentException("Aplicativo não encontrado"));

        Assinatura assinatura = new Assinatura();
        assinatura.setCliente(cliente);
        assinatura.setAplicativo(aplicativo);
        assinatura.setInicioVigencia(LocalDate.now());
        assinatura.setFimVigencia(LocalDate.now().plusDays(7)); // 7 dias grátis

        return assinaturaRepository.save(assinatura);
    }
}