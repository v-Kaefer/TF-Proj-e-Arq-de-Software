package progarqsoft.tfinal.model;

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
}