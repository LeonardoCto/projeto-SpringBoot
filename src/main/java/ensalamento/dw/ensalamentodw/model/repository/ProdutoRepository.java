package ensalamento.dw.ensalamentodw.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ensalamento.dw.ensalamentodw.model.entidade.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
