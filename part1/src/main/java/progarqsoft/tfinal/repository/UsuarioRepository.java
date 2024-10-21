package progarqsoft.tfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import progarqsoft.tfinal.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
