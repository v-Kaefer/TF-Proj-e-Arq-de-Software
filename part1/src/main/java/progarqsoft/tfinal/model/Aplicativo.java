package progarqsoft.tfinal.model;

@Entity
public class Aplicativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private Float custoMensal;

    // Getters e Setters
}
