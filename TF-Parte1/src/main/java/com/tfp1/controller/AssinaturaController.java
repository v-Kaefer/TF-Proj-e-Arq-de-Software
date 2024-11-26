package progarqsoft.tfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progarqsoft.tfinal.model.Assinatura;
import progarqsoft.tfinal.service.AssinaturaService;

@RestController
@RequestMapping("/api/assinaturas")
public class AssinaturaController {
    @Autowired
    private AssinaturaService assinaturaService;

    @GetMapping
    public List<Assinatura> listarAssinaturas(@RequestParam String tipo) {
        return assinaturaService.listarAssinaturasPorTipo(tipo);
    }

    @PostMapping
    public Assinatura criarAssinatura(@RequestBody Map<String, Long> dados) {
        return assinaturaService.criarAssinatura(dados.get("codigoCliente"), dados.get("codigoAplicativo"));
    }
}

