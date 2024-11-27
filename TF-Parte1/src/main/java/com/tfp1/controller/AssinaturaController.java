package com.tfp1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tfp1.model.Assinatura;
import com.tfp1.service.AssinaturaService;

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

