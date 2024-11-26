package com.engsoft2.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.engsoft2.projeto.model.Aplicativo;
import com.engsoft2.projeto.repository.AplicativoRepository;
import com.engsoft2.projeto.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class AplicativoService {

    private final AplicativoRepository aplicativoRepository;

    @Autowired
    public AplicativoService(AplicativoRepository aplicativoRepository) {
        this.aplicativoRepository = aplicativoRepository;
    }

    public List<Aplicativo> listarTodos() {
        return aplicativoRepository.findAll();
    }

    public Aplicativo buscarPorId(Long id) {
        return aplicativoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aplicativo não encontrado com ID: " + id));
    }

    public Aplicativo criar(Aplicativo aplicativo) {
        return aplicativoRepository.save(aplicativo);
    }

    public Aplicativo atualizar(Long id, Aplicativo aplicativo) {
        Aplicativo aplicativoExistente = buscarPorId(id);
        aplicativoExistente.setNome(aplicativo.getNome());
        aplicativoExistente.setCustoMensal(aplicativo.getCustoMensal());
        return aplicativoRepository.save(aplicativoExistente);
    }

    public void deletar(Long id) {
        Aplicativo aplicativo = buscarPorId(id);
        aplicativoRepository.delete(aplicativo);
    }
}
