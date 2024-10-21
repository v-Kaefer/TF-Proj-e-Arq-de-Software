package progarqsoft.tfinal.model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "assinatura_id")
    private Assinatura assinatura;

    private Float valorPago;
    private LocalDate dataPagamento;
    private String promocao;

    // Getters e Setters
public Long getCodigo() {
    return codigo;
}

public void setCodigo(Long codigo) {
    this.codigo = codigo;
}

public Assinatura getAssinatura() {
    return assinatura;
}

public void setAssinatura(Assinatura assinatura) {
    this.assinatura = assinatura;
}

public Float getValorPago() {
    return valorPago;
}

public void setValorPago(Float valorPago) {
    this.valorPago = valorPago;
}

public LocalDate getDataPagamento() {
    return dataPagamento;
}

public void setDataPagamento(LocalDate dataPagamento) {
    this.dataPagamento = dataPagamento;
}

public String getPromocao() {
    return promocao;
}

public void setPromocao(String promocao) {
    this.promocao = promocao;
}
}