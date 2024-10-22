package progarqsoft.tfinal.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/registrarpagamento")
    public ResponseEntity<Map<String, Object>> registrarPagamento(@RequestBody ProcessarPagamentoRequest request) {
        try {
            Pagamento pagamento = processarPagamentoUseCase.processarPagamento(request.getAssinaturaId(), request.getValorPago(), request.getPromocao());
            Map<String, Object> response = new HashMap<>();
            response.put("status", "PAGAMENTO_OK");
            response.put("data", pagamento.getDataPagamento());
            response.put("valorEstornado", 0); // Ajuste conforme necessário
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "VALOR_INCORRETO");
            response.put("data", null);
            response.put("valorEstornado", request.getValorPago());
            return ResponseEntity.badRequest().body(response);
        }
    }
}