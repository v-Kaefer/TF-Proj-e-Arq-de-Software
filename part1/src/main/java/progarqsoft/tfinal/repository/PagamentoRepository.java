package progarqsoft.tfinal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import progarqsoft.tfinal.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByAssinaturaClienteCodigo(Long codigo);

}
