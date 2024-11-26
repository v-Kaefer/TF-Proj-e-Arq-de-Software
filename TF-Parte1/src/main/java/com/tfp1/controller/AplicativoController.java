package progarqsoft.tfinal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progarqsoft.tfinal.model.Aplicativo;
import progarqsoft.tfinal.repository.AplicativoRepository;

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
        aplicativo.setCustoMensal(custo.get("custo"));
        return aplicativoRepository.save(aplicativo);
    }
}
