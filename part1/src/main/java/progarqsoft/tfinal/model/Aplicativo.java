package progarqsoft.tfinal.model;

import javax.persistence.*;

@Entity
public class Aplicativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private Float custoMensal;

    public Aplicativo() {}

    public Aplicativo(String nome, Float custoMensal) {
        this.nome = nome;
        this.custoMensal = custoMensal;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getCustoMensal() {
        return custoMensal;
    }

    public void setCustoMensal(Float custoMensal) {
        this.custoMensal = custoMensal;
    }
}
