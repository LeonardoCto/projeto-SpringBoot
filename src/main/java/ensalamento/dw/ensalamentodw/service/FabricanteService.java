package ensalamento.dw.ensalamentodw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ensalamento.dw.ensalamentodw.exception.CampoInvalidoException;
import ensalamento.dw.ensalamentodw.model.entidade.Fabricante;
import ensalamento.dw.ensalamentodw.model.repository.FabricanteRepository;

@Service
public class FabricanteService {

	@Autowired
	private FabricanteRepository fabricanteRepository;

	public Fabricante inserir(Fabricante novoFabricante) throws CampoInvalidoException {
		return fabricanteRepository.save(novoFabricante);
	}

	public List<Fabricante> listarTodos() {
		return fabricanteRepository.findAll();
	}

}
