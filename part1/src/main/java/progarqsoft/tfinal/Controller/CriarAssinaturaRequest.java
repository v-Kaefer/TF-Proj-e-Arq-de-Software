package progarqsoft.tfinal.Controller;

public class CriarAssinaturaRequest {
    private Long clienteId;
    private Long aplicativoId;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getAplicativoId() {
        return aplicativoId;
    }

    public void setAplicativoId(Long aplicativoId) {
        this.aplicativoId = aplicativoId;
    }
}
