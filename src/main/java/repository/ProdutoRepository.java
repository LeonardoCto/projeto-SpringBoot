package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entidade.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
