package progarqsoft.tfinal.model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
public class Assinatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "aplicativo_id")
    private Aplicativo aplicativo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;

    // Getters e Setters
}
