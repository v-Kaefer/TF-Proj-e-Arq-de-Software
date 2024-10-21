package progarqsoft.tfinal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progarqsoft.tfinal.model.Pagamento;
import progarqsoft.tfinal.service.ProcessarPagamentoUseCase;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {
    private final ProcessarPagamentoUseCase processarPagamentoUseCase;

    @Autowired
    public PagamentoController(ProcessarPagamentoUseCase processarPagamentoUseCase) {
        this.processarPagamentoUseCase = processarPagamentoUseCase;
    }

    @PostMapping
    public ResponseEntity<Pagamento> processarPagamento(@RequestBody ProcessarPagamentoRequest request) {
        Pagamento pagamento = processarPagamentoUseCase.processarPagamento(request.getAssinaturaId(), request.getValorPago(), request.getPromocao());
        return ResponseEntity.ok(pagamento);
    }
}