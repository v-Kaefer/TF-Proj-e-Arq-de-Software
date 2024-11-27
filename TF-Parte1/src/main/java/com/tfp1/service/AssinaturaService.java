package com.tfp1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tfp1.model.Assinatura;
import com.tfp1.repository.AssinaturaRepository;

import java.time.LocalDate;
import java.util.List;


@Service
public class AssinaturaService {
    @Autowired
    private AssinaturaRepository assinaturaRepository;

    public List<Assinatura> listarAssinaturas() {
        return assinaturaRepository.findAll();
    }

    public List<Assinatura> listarAssinaturasPorTipo(String tipo) {
        return switch (tipo.toUpperCase()) {
            case "ATIVAS" -> assinaturaRepository.findAll().stream()
                .filter(a -> a.getFimVigencia().isAfter(LocalDate.now()))
                .toList();
            case "CANCELADAS" -> assinaturaRepository.findAll().stream()
                .filter(a -> a.getFimVigencia().isBefore(LocalDate.now()))
                .toList();
            default -> assinaturaRepository.findAll();
        };
    }

    public Assinatura criarAssinatura(Long codCliente, Long codAplicativo) {

        Assinatura assinatura = new Assinatura();
        // Inicializar a assinatura com lógica de 7 dias grátis
        // Lógica detalhada aqui
        assinatura.setCliente(cliente);
        assinatura.setAplicativo(aplicativo);
        assinatura.setInicioVigencia(LocalDate.now());
        assinatura.setFimVigencia(LocalDate.now().plusDays(7)); // 7 dias grátis
        return assinaturaRepository.save(assinatura);
    }
}