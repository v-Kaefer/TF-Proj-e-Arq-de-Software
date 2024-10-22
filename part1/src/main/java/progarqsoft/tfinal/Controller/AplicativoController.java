package progarqsoft.tfinal.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progarqsoft.tfinal.model.Aplicativo;
import progarqsoft.tfinal.repository.AplicativoRepository;

@RestController
@RequestMapping("/api/aplicativos")
public class AplicativoController {
    private final AplicativoRepository aplicativoRepository;

    @Autowired
    public AplicativoController(AplicativoRepository aplicativoRepository) {
        this.aplicativoRepository = aplicativoRepository;
    }

    @GetMapping
    public List<Aplicativo> listarAplicativos() {
        return aplicativoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Aplicativo> criarAplicativo(@RequestBody Aplicativo aplicativo) {
        Aplicativo novoAplicativo = aplicativoRepository.save(aplicativo);
        return ResponseEntity.ok(novoAplicativo);
    }
}