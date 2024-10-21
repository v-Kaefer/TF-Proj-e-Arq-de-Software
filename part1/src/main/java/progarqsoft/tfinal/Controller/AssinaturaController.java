package progarqsoft.tfinal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progarqsoft.tfinal.model.Assinatura;
import progarqsoft.tfinal.service.CriarAssinaturaUseCase;

@RestController
@RequestMapping("/api/assinaturas")
public class AssinaturaController {
    private final CriarAssinaturaUseCase criarAssinaturaUseCase;

    @Autowired
    public AssinaturaController(CriarAssinaturaUseCase criarAssinaturaUseCase) {
        this.criarAssinaturaUseCase = criarAssinaturaUseCase;
    }

    @PostMapping
    public ResponseEntity<Assinatura> criarAssinatura(@RequestBody CriarAssinaturaRequest request) {
        Assinatura assinatura = criarAssinaturaUseCase.execute(request.getClienteId(), request.getAplicativoId());
        return ResponseEntity.ok(assinatura);
    }
}
