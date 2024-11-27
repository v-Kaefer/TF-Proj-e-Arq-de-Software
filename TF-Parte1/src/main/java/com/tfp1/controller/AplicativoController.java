package com.tfp1.controller;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfp1.model.Aplicativo;
import com.tfp1.repository.AplicativoRepository;

@RestController
@RequestMapping("/api/aplicativos")
public class AplicativoController {
    @Autowired
    private AplicativoRepository aplicativoRepository;

    @GetMapping
    public List<Aplicativo> listarAplicativos() {
        return aplicativoRepository.findAll();
    }

    @PostMapping("/atualizacusto/{idAplicativo}")
    public Aplicativo atualizarCusto(@PathVariable Long idAplicativo, @RequestBody Map<String, Double> custo) {
        Aplicativo aplicativo = aplicativoRepository.findById(idAplicativo)
            .orElseThrow(() -> new EntityNotFoundException("Aplicativo não encontrado"));
        aplicativo.setCustoMensal(custo.get("custo").floatValue());
        return aplicativoRepository.save(aplicativo);
    }
}
