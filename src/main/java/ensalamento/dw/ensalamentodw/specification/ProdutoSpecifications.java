package ensalamento.dw.ensalamentodw.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import ensalamento.dw.ensalamentodw.model.entidade.Produto;
import ensalamento.dw.ensalamentodw.seletor.ProdutoSeletor;
import jakarta.persistence.criteria.Predicate;

public class ProdutoSpecifications {

	public static Specification<Produto> comFiltros(ProdutoSeletor seletor) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (seletor.getNome() != null) {
				predicates.add(cb.like(cb.lower(root.get("nome")), "%" + seletor.getNome().toLowerCase() + "%"));
			}

			if (seletor.getFabricante() != null) {
				predicates.add(
						cb.like(cb.lower(root.get("fabricante")), "%" + seletor.getFabricante().toLowerCase() + "%"));
			}
			if (seletor.getPesoMinimo() != null && seletor.getPesoMaximo() != null) {
				predicates.add(cb.between(root.get("peso"), seletor.getPesoMinimo(), +seletor.getPesoMaximo()));
			} else if (seletor.getPesoMinimo() != null) {
				// WHERE peso >= min
				predicates.add(cb.greaterThanOrEqualTo(root.get("peso"), seletor.getPesoMinimo()));
			} else if (seletor.getPesoMaximo() != null) {
				// WHERE peso <= max
				predicates.add(cb.lessThanOrEqualTo(root.get("peso"), seletor.getPesoMaximo()));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}

}
