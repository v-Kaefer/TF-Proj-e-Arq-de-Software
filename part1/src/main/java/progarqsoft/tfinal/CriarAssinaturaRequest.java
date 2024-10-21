package progarqsoft.tfinal;

import progarqsoft.tfinal.model.Assinatura;
import progarqsoft.tfinal.service.CriarAssinaturaUseCase;

@RestController
@RequestMapping("/api/assinaturas")
public class AssinaturaController {
    private final CriarAssinaturaUseCase criarAssinaturaUseCase;

    public AssinaturaController(CriarAssinaturaUseCase criarAssinaturaUseCase) {
        this.criarAssinaturaUseCase = criarAssinaturaUseCase;
    }

    @PostMapping
    public ResponseEntity<Assinatura> criarAssinatura(@RequestBody CriarAssinaturaRequest request) {
        Assinatura assinatura = criarAssinaturaUseCase.execute(request.getClienteId(), request.getAplicativoId());
        return ResponseEntity.ok(assinatura);
    }
}

// CriarAssinaturaRequest.java
public class CriarAssinaturaRequest {
    private int clienteId;
    private int aplicativoId;

    // Getters e Setters
}
