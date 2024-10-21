package progarqsoft.tfinal.Controller;

import progarqsoft.tfinal.CriarAssinaturaUseCase;
import progarqsoft.tfinal.model.Assinatura;

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
    private Long clienteId;
    private Long aplicativoId;

    // Getters e Setters
}
