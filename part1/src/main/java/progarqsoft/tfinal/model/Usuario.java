package progarqsoft.tfinal.model;

import javax.persistence.*;

@Entity
public class Usuario {
    @Id
    private String usuario;
    private String senha;

    // Getters e Setters
public String getUsuario() {
    return usuario;
}

public void setUsuario(String usuario) {
    this.usuario = usuario;
}

public String getSenha() {
    return senha;
}

public void setSenha(String senha) {
    this.senha = senha;
}
}
