package progarqsoft.tfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import progarqsoft.tfinal.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
