package ensalamento.dw.ensalamentodw.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ensalamento.dw.ensalamentodw.model.entidade.Fabricante;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long>, JpaSpecificationExecutor<Fabricante>{

}
