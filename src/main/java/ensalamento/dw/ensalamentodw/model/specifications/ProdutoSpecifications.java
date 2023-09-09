package ensalamento.dw.ensalamentodw.model.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import ensalamento.dw.ensalamentodw.model.entidade.Produto;
import ensalamento.dw.ensalamentodw.model.seletor.ProdutoSeletor;
import jakarta.persistence.criteria.Predicate;

public class ProdutoSpecifications {
	/*
	 * 
	 * root: é uma referência à entidade raiz que você está consultando. No contexto
	 * do JPA, isso representa a tabela do banco de dados correspondente à entidade
	 * Produto.
	 * 
	 * query: O parâmetro query representa a consulta JPA que está sendo construída.
	 * Ele é usado para adicionar cláusulas WHERE, JOIN, ORDER BY, entre outras, à
	 * consulta.
	 * 
	 * cb (CriteriaBuilder): é uma interface que oferece métodos para construção de
	 * cláusulas de consulta de forma programática. Você usa métodos deste objeto
	 * para criar expressões de predicado, funções agregadas e outras partes da
	 * consulta.
	 * 
	 */
	public static Specification<Produto> comFiltros(ProdutoSeletor seletor) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (seletor.getNome() != null) {
				// WHERE/AND COLUNA OPERADOR VALOR
				// WHERE nome like %Café%
				predicates.add(cb.like(cb.lower(root.get("nome")), "%" + seletor.getNome().toLowerCase() + "%"));
			}

			// TODO como filtrar por "FABRICANTES.NOME"?
			// https://stackoverflow.com/questions/6396877/openjpa-criteriabuilder-nested-object-property-fetch
			if (seletor.getFabricante() != null) {
				// WHERE p.fabricante like '%Rider%'
				// WHERE f.nome like '%Rider%'
				// JPQL = Java Persistence Query Language
				predicates.add(
						cb.like(root.join("fabricanteDoProduto").get("nome"), "%" + seletor.getFabricante() + "%"));
			}

			if (seletor.getPesoMinimo() != null && seletor.getPesoMaximo() != null) {
				// WHERE peso BETWEEN min AND max
				predicates.add(cb.between(root.get("peso"), seletor.getPesoMinimo(), seletor.getPesoMaximo()));
			} else if (seletor.getPesoMinimo() != null) {
				// WHERE peso >= min
				predicates.add(cb.greaterThanOrEqualTo(root.get("peso"), seletor.getPesoMinimo()));
			} else if (seletor.getPesoMaximo() != null) {
				// WHERE peso <= max
				predicates.add(cb.lessThanOrEqualTo(root.get("peso"), seletor.getPesoMaximo()));
			}

//            private Double valorMinimo;
//            private Double valorMaximo;
			if (seletor.getValorMinimo() != null && seletor.getValorMaximo() != null) {
				// WHERE valor BETWEEN min AND max
				predicates.add(cb.between(root.get("valor"), seletor.getValorMinimo(), seletor.getValorMaximo()));
			} else if (seletor.getValorMinimo() != null) {
				// WHERE valor >= min
				predicates.add(cb.greaterThanOrEqualTo(root.get("valor"), seletor.getValorMinimo()));
			} else if (seletor.getValorMaximo() != null) {
				// WHERE peso <= max
				predicates.add(cb.lessThanOrEqualTo(root.get("valor"), seletor.getValorMaximo()));
			}

//            private LocalDate dataCadastroInicial;
//            private LocalDate dataCadastroFinal;
			// COMO FILTRAR POR "PRODUTO.DATA"
			if (seletor.getDataCadastroInicial() != null & seletor.getDataCadastroFinal() != null) {
				if (seletor.getDataCadastroInicial() != null && seletor.getDataCadastroFinal() != null) {
					// WHERE data BETWEEN min AND max
					predicates.add(cb.between(root.get("data"), seletor.getDataCadastroInicial(),
							seletor.getDataCadastroFinal()));
				}
			}

			// CNPJ
			if (seletor.getFabricante() != null) {
				predicates.add(
						cb.like(root.join("fabricanteDoProduto").get("cnpj"), "%" + seletor.getFabricante() + "%"));
				if (seletor.getCnpjFabricante() != null && seletor.getCnpjFabricante().isEmpty()) {
					// WHERE p.fabricante like '%Rider%'
					// WHERE f.nome like '%Rider%'
					// JPQL = Java Persistence Query Language
					predicates.add(cb.equal(root.join("fabricanteDoProduto").get("cnpj"), seletor.getCnpjFabricante()));
				}
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}