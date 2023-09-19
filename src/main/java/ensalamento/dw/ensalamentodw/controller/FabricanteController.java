package ensalamento.dw.ensalamentodw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ensalamento.dw.ensalamentodw.exception.CampoInvalidoException;
import ensalamento.dw.ensalamentodw.model.entidade.Fabricante;
import ensalamento.dw.ensalamentodw.service.FabricanteService;

@RestController
@RequestMapping(path = "/api/fabricantes") // shorthand for @Controller and @ResponseBody rolled together
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:5500" }, maxAge = 3600)
public class FabricanteController {

	@Autowired
	private FabricanteService fabricanteService;

	@PostMapping
	public Fabricante salvar(@RequestBody Fabricante novoFabricante) throws CampoInvalidoException {
		return fabricanteService.inserir(novoFabricante);
	}

	@GetMapping(path="/todos")
	public List<Fabricante> listarTodos() {
		List<Fabricante> fabricantes = fabricanteService.listarTodos();
		return fabricantes;
	}

}
